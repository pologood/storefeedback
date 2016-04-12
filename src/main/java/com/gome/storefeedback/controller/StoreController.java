package com.gome.storefeedback.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.StoreService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 门店管理
 * 
 * @author Ma.Mingyang
 * @date 2015年2月3日上午9:29:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/store")
public class StoreController {

	// 注入
	@Resource
	private StoreService storeService;

	/**
	 * 分页查询门店
	 * 
	 * @param store
	 * @param req
	 * @param res
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String findByPage(Store store, HttpServletRequest req,
			HttpServletResponse res) throws BaseException {

		// 结果map
		Map<String, Object> result = new HashMap<String, Object>();
		// 参数map
		Map<String, Object> inMap = new HashMap<String, Object>();
		// 初始化
		PaginationParameters param = new PaginationParameters(req, res);
		// 组装查询条件
		if (StringUtils.isNotBlank(store.getStore_name())) {// 门店名称
			inMap.put("store_name", store.getStore_name());
		}
		if (StringUtils.isNotBlank(store.getStore_code())) {// 门店编码
			inMap.put("store_code", store.getStore_code());
		}
		if (StringUtils.isNotBlank(store.getCompany_code())) {// 公司编码
			inMap.put("company_code", store.getCompany_code());
		}
		if (StringUtils.isNotBlank(store.getStore_address())) {// 门店地址
			inMap.put("store_address", store.getStore_address());
		}

		Page<Store> recordByPage = storeService.findStoreByPage(inMap, param);
		if (null == recordByPage) {
			result.put("total", 0);
			result.put("rows", new ArrayList<Store>());
		} else {
			result.put("total", recordByPage.getTotalSize());
			result.put("rows", recordByPage.getDataList());
		}

		return JsonUtil.javaObjectToJsonString(result);
	}
	
	@RequestMapping(value="/findStores", produces="text/plain;charset=UTF-8")
    public @ResponseBody String findByDivisionCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> inMap = new HashMap<String, Object>();
        String first_division_code = request.getParameter("first_division_code");
        String second_division_code = request.getParameter("second_division_code");
        inMap.put("first_division_code", first_division_code);
        inMap.put("second_division_code", second_division_code);
        List<Store> stores = storeService.findStore(inMap);
        return JsonUtil.javaObjectToJsonString(stores);
    }
	
	 /**
     * 接收xml数据，并写反馈 暂不知道请求传入的情况，暂时写成 InputStream
     */
    @RequestMapping(value = "/xmlMD005", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    void saveStores(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = null;
        DataBack dataBack = new DataBack("MD005");

        try {
            // 获取输入流
            is = request.getInputStream();
            // 解析 xml 数据 数据入库
            this.storeService.insertBatchStore(parseXML2Store(is, dataBack));
            // 数据入库成功 回写数据
            try {
                dataBack.setDetailResult(dataBack.DETAILRESULT_SUCCESS);
                DataBackUtil.response2XML(dataBack);
            } catch (Exception e) {
                dataBack.setDetailResult(dataBack.DETAILRESULT_FAILURE);
                dataBack.addDetalisItem("499", "回写数据失败");
                System.err.println("ERROR：Store 保存数据成功 回写反馈失败。 Location:{InterfaceID:" + dataBack.getDetailInterfaceO()
                        + ",MessageId:" + dataBack.getDetailMessageO() + "}");
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
    private static List<Store> parseXML2Store(InputStream is, DataBack dataBack) throws DocumentException {
        List<Store> result = new ArrayList<Store>();
        SAXReader reader = new SAXReader();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();

        Map<String, String> header = null;
        List<Node> headers = root.selectNodes("//HEADER");

        for (Node node : headers) {
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

        for (Node item : items) {
            Store store = new Store();
            String saleOrg = item.selectSingleNode("VKORG").getText();
            String storeCode = item.selectSingleNode("WERKS").getText();
            String companyCode = item.selectSingleNode("BUKRS").getText();
            String storeName = item.selectSingleNode("NAME1").getText();
            String secondDivisionCode = item.selectSingleNode("ZVKGRP").getText();
            String secondDivisionDes = item.selectSingleNode("ZBEZEI").getText();
            String divisionCode = item.selectSingleNode("ZVKBUR").getText();
            String divisionDes = item.selectSingleNode("ZBEZEI2").getText();
            String storeAddress = item.selectSingleNode("STRAS").getText();
            String storeTel = item.selectSingleNode("TELF1").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            store.setSale_org(saleOrg);
            store.setStore_code(storeCode);
            store.setCompany_code(companyCode);
            store.setStore_name(storeName);
            store.setSecond_division_code(secondDivisionCode);
            store.setSecond_division_des(secondDivisionDes);
            store.setDivision_code(divisionCode);
            store.setDivision_des(divisionDes);
            store.setStore_address(storeAddress);
            store.setStore_tel(storeTel);
            store.setUpdate_flag(updateFlag);
            result.add(store);
        }
        return result;
    }
}
