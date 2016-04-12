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

import com.gome.storefeedback.entity.SecondDivision;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SecondDivisionService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;


/**
 * 二级分部Controller
 * @author Zhang.Jingang
 * @date 2015年2月3日上午10:49:48
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/secondDivision")
public class SecondDivisionController {

    private SecondDivisionService secondDivisionService;

    public SecondDivisionService getSecondDivisionService() {
        return secondDivisionService;
    }

    @Autowired
    public void setSencodDivisionService(@Qualifier("secondDivisionService") SecondDivisionService secondDivisionService) {
        this.secondDivisionService = secondDivisionService;
    }

    @RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findByPage(SecondDivision secondDivision, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> inMap = new HashMap<String, Object>();
       
        PaginationParameters param = new PaginationParameters(request, response);
        Page<SecondDivision> sencondDivisionPage = this.secondDivisionService.findSecondDivisionByPage(inMap, param);

        Map<String, Object> result = new HashMap<String, Object>();
        
        if (null == sencondDivisionPage) {
            result.put("total", 0);
            result.put("rows", new ArrayList<Object>());
        } else {
            result.put("total", sencondDivisionPage.getTotalSize());
            result.put("rows", sencondDivisionPage.getDataList());
        }

        return JsonUtil.javaObjectToJsonString(result);
    }
    
	@RequestMapping(value="/findSencondDivision", produces="text/plain;charset=UTF-8")
	public @ResponseBody String findSecondDivisionById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> inMap = new HashMap<String, Object>();

		SecondDivision entity = this.secondDivisionService.findSecondDivisionByCode(inMap);
		result.put("data", entity);
		return JsonUtil.javaObjectToJsonString(result);
	}
	
	@RequestMapping(value="/findSencondDivisions", produces="text/plain;charset=UTF-8")
    public @ResponseBody String findByDivisionCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        Map<String, Object> inMap = new HashMap<String, Object>();

        String first_division_code = request.getParameter("first_division_code");
        inMap.put("first_division_code", first_division_code);
        List<SecondDivision> secondDivisions = secondDivisionService.findSecondDivision(inMap);
        return JsonUtil.javaObjectToJsonString(secondDivisions);
    }
	
	/**
     * 接收xml数据，并写反馈
     * 暂不知道请求传入的情况，暂时写成 InputStream
     */
    @RequestMapping(value = "/xmlMD114", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    void saveSencondDivisions(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        DataBack dataBack = new DataBack("MD114");
        
        try {
            // 获取输入流
            is = request.getInputStream();
            // 解析 xml 数据 数据入库
            this.secondDivisionService.insertBatchSecondDivision(parseXML2SecondDivision(is, dataBack));
            // 数据入库成功 回写数据
            try {
                dataBack.setDetailResult(dataBack.DETAILRESULT_SUCCESS);
                DataBackUtil.response2XML(dataBack);
            } catch (Exception e) {
                dataBack.setDetailResult(dataBack.DETAILRESULT_FAILURE);
                dataBack.addDetalisItem("499", "回写数据失败");
                System.err.println("ERROR：SencondDivision 保存数据成功 回写反馈失败。 Location:{InterfaceID:"+dataBack.getDetailInterfaceO()+",MessageId:"+dataBack.getDetailMessageO()+"}");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            dataBack.setDetailResult("499");
            dataBack.addDetalisItem("failure", "HTTP错误:获取输入流是错误");
        } catch (DocumentException e) {
            // xml 解析错误
            dataBack.setDetailResult(dataBack.DETAILRESULT_FAILURE);
            dataBack.addDetalisItem("499", "xml 错误");
            e.printStackTrace();
        } catch (BaseException e) {
            // 数据入库失败 回滚数据
            dataBack.setDetailResult(dataBack.DETAILRESULT_FAILURE);
            dataBack.addDetalisItem("599", "数据写入失败");
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
     * @param feedback
     * @return
     */
    @SuppressWarnings("unchecked")
    private static List<SecondDivision> parseXML2SecondDivision(InputStream is, DataBack dataBack) throws DocumentException{
        List<SecondDivision> result = new ArrayList<SecondDivision>();
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
            
            dataBack.setDetailMessageO(messageId);
        }

        List<Node> items = root.selectNodes("//ITEM");
        
        for(Node item : items){
            SecondDivision secondDivision = new SecondDivision();
            String secondDivisionCode = item.selectSingleNode("VKGRP").getText();
            String secondDivisionDes = item.selectSingleNode("BEZEI").getText();
            String firstDivisionCode = item.selectSingleNode("VKBUR").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            secondDivision.setSecond_division_code(secondDivisionCode);
            secondDivision.setSecond_division_des(secondDivisionDes);
            secondDivision.setFirst_division_code(firstDivisionCode);
            secondDivision.setUpdate_flag(updateFlag);
            result.add(secondDivision);
        }
        return result;
    }
}