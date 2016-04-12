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

import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.RegionService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 大区Controller
 * @author Zhang.Jingang
 * @date 2015年2月3日上午10:50:03
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;

    public RegionService getRegionService() {
        return regionService;
    }

    @Autowired
    public void setRegionService(@Qualifier("regionService") RegionService regionService) {
        this.regionService = regionService;
    }
    @RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findByPage(Region region, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> inMap = new HashMap<String, Object>();
       
        PaginationParameters param = new PaginationParameters(request, response);
        Page<Region> regionPage = this.regionService.findRegionByPage(inMap, param);

        Map<String, Object> result = new HashMap<String, Object>();
        
        if (null == regionPage) {
            result.put("total", 0);
            result.put("rows", new ArrayList<Object>());
        } else {
            result.put("total", regionPage.getTotalSize());
            result.put("rows", regionPage.getDataList());
        }

        return JsonUtil.javaObjectToJsonString(result);
    }
    
    @RequestMapping(value="/findRegion", produces="text/plain;charset=UTF-8")
    public @ResponseBody String findRegionById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();

        Region entity = this.regionService.findRegionByCode(inMap);
        result.put("data", entity);
        return JsonUtil.javaObjectToJsonString(result);
    }
    @RequestMapping(value = "/findRegions", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findRegions(Region region, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> inMap = new HashMap<String, Object>();
        if (null != region.getRegion_code() && !"".equals(region.getRegion_code())) {
            inMap.put("region_code", region.getRegion_code());
        } else {
            inMap.put("region_code", null);
        }
        if (null != region.getRegion_des() && !"".equals(region.getRegion_des())) {
            inMap.put("region_des", region.getRegion_des());
        } else {
            inMap.put("region_des", null);
        }
        if (null != region.getUpdate_flag() && !"".equals(region.getUpdate_flag())) {
            inMap.put("update_flag", region.getUpdate_flag());
        } else {
            inMap.put("update_flag", null);
        }
        
        List<Region> regions = regionService.findRegion(inMap);
        return JsonUtil.javaObjectToJsonString(regions);
    }
    
    /**
     * 接收xml数据，并写反馈
     * 暂不知道请求传入的情况，暂时写成 InputStream
     */
    @RequestMapping(value = "/xmlMD112", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    void saveRegions(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        DataBack dataBack = new DataBack("MD112");
        
        try {
            // 获取输入流
            is = request.getInputStream();
            // 解析 xml 数据 数据入库
            this.regionService.insertBatchRegion(parseXML2Region(is, dataBack));
            // 数据入库成功 回写数据
            try {
                dataBack.setDetailResult(dataBack.DETAILRESULT_SUCCESS);
                DataBackUtil.response2XML(dataBack);
            } catch (Exception e) {
                dataBack.setDetailResult(dataBack.DETAILRESULT_FAILURE);
                dataBack.addDetalisItem("499", "回写数据失败");
                System.err.println("ERROR：Region 保存数据成功 回写反馈失败。 Location:{InterfaceID:"+dataBack.getDetailInterfaceO()+",MessageId:"+dataBack.getDetailMessageO()+"}");
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
    private static List<Region> parseXML2Region(InputStream is, DataBack dataBack) throws DocumentException{
        List<Region> result = new ArrayList<Region>();
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
            Region region = new Region();
            String regionCode = item.selectSingleNode("BZIRK").getText();
            String regionDes = item.selectSingleNode("BZTXT").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            region.setRegion_code(regionCode);
            region.setRegion_des(regionDes);
            region.setUpdate_flag(updateFlag);
            result.add(region);
        }
        return result;
    }
}
