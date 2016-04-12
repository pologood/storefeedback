package com.gome.storefeedback.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.DivisionService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;


/**
 * 分部Controller
 * @author Zhang.Jingang
 * @date 2015年2月3日上午10:50:44
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/division")
public class DivisionController {

    private DivisionService divisionService;

    public DivisionService getDivisionService() {
        return divisionService;
    }

    @Autowired
    public void setDivisionService(@Qualifier("divisionService") DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findByPage(Division division, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> inMap = new HashMap<String, Object>();
       
        PaginationParameters param = new PaginationParameters(request, response);
        Page<Division> divisionPage = this.divisionService.findDivisionByPage(inMap, param);

        Map<String, Object> result = new HashMap<String, Object>();
        
        if (null == divisionPage) {
            result.put("total", 0);
            result.put("rows", new ArrayList());
        } else {
            result.put("total", divisionPage.getTotalSize());
            result.put("rows", divisionPage.getDataList());
        }

        return JsonUtil.javaObjectToJsonString(result);
    }
    
	@RequestMapping(value="/findDivision", produces="text/plain;charset=UTF-8")
	public @ResponseBody String findDivisionById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> inMap = new HashMap<String, Object>();

		Division entity = this.divisionService.findDivisionByCode(inMap);
		result.put("data", entity);
		return JsonUtil.javaObjectToJsonString(result);
	}
	
	@RequestMapping(value="/findDivisions", produces="text/plain;charset=UTF-8")
    public @ResponseBody String findByRegionCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> inMap = new HashMap<String, Object>();
        String region_code = request.getParameter("region_code");
        inMap.put("region_code", region_code);
        List<Division> divisions = this.divisionService.findDivision(inMap);
        return JsonUtil.javaObjectToJsonString(divisions);
    }
	
	/**
     * 接收xml数据，并写反馈
     * 暂不知道请求传入的情况，暂时写成 InputStream
     */
    @RequestMapping(value = "/xmlMD113", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    void saveDivisions(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        DataBack databack = new DataBack("MD113");
        
        try {
            // 获取输入流
            is = request.getInputStream();
            // 解析 xml 数据 数据入库
            this.divisionService.insertBatchDivision(parseXML2Division(is, databack));
            // 数据入库成功 回写数据
            try {
                databack.setDetailResult(databack.DETAILRESULT_SUCCESS);
                DataBackUtil.response2XML(databack);
            } catch (Exception e) {
                databack.setDetailResult(databack.DETAILRESULT_FAILURE);
                databack.addDetalisItem("499", "回写数据失败");
                System.err.println("ERROR：Division 保存数据成功 回写反馈失败。 Location:{InterfaceID:"+databack.getDetailInterfaceO()+",MessageId:"+databack.getDetailMessageO()+"}");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            databack.setDetailResult("499");
            databack.addDetalisItem("failure", "HTTP错误:获取输入流是错误");
        } catch (DocumentException e) {
            // xml 解析错误
            databack.setDetailResult(databack.DETAILRESULT_FAILURE);
            databack.addDetalisItem("499", "xml 错误");
            e.printStackTrace();
        } catch (BaseException e) {
            // 数据入库失败 回滚数据
            databack.setDetailResult(databack.DETAILRESULT_FAILURE);
            databack.addDetalisItem("599", "数据写入失败");
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 
     * @param is
     * @param databack
     * @return
     */
    @SuppressWarnings("unchecked")
    private static List<Division> parseXML2Division(InputStream is, DataBack databack) throws DocumentException{
        List<Division> result = new ArrayList<Division>();
        SAXReader reader = new SAXReader();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();
        
        Map<String, String> header = null;
        List<Node> headers = root.selectNodes("//HEADER");
        
        for(Node node : headers){
            header = new HashMap<String, String>();

            header.put("INTERFACE_ID", node.selectSingleNode("INTERFACE_ID").getText());
            String messageId = node.selectSingleNode("MESSAGE_ID").getText();
            header.put("MESSAGE_ID", messageId);
            header.put("SENDER", node.selectSingleNode("SENDER").getText());
            header.put("RECEIVER", node.selectSingleNode("RECEIVER").getText());
            header.put("DTSEND", node.selectSingleNode("DTSEND").getText());
            
            databack.setDetailMessageO(messageId);
        }

        List<Node> items = root.selectNodes("//ITEM");
        
        for(Node item : items){
            Division division = new Division();
            String divisionCode = item.selectSingleNode("VKBUR").getText();
            String divisionDes = item.selectSingleNode("BEZEI").getText();
            String regionCode = item.selectSingleNode("BZIRK").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            division.setDivision_code(divisionCode);
            division.setDivision_desc(divisionDes);
            division.setRegion_code(regionCode);
            division.setUpdate_flag(updateFlag);
            result.add(division);
        }
        return result;
    }
}