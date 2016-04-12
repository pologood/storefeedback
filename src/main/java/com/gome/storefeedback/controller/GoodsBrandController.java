package com.gome.storefeedback.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsBrandService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 商品品牌控制器
 * 
 * @author Ma.Mingyang
 * @date 2015年2月9日下午12:27:31
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/goodsBrand")
public class GoodsBrandController {

	private GoodsBrandService goodsBrandService;

	public GoodsBrandService getGoodsBrandService() {
		return goodsBrandService;
	}

	@Autowired
	public void setGoodsBrandService(
			@Qualifier("goodsBrandService") GoodsBrandService goodsBrandService) {
		this.goodsBrandService = goodsBrandService;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String findByPage(GoodsBrand goodsBrand, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> inMap = new HashMap<String, Object>();
		if (null != goodsBrand.getBrandCode()
				&& !"".equals(goodsBrand.getBrandCode())) {
			inMap.put("brandCode", goodsBrand.getBrandCode());
		} else {
			inMap.put("brandCode", null);
		}
		if (null != goodsBrand.getBrandClass()
				&& !"".equals(goodsBrand.getBrandClass())) {
			inMap.put("brandClass", goodsBrand.getBrandClass());
		} else {
			inMap.put("brandClass", null);
		}
		if (null != goodsBrand.getCnText()
				&& !"".equals(goodsBrand.getCnText())) {
			inMap.put("cnText", goodsBrand.getCnText());
		} else {
			inMap.put("cnText", null);
		}

		PaginationParameters param = new PaginationParameters(request, response);
		Page<GoodsBrand> goodsBrandPage = this.goodsBrandService
				.findGoodsBrandByPage(inMap, param);

		Map<String, Object> result = new HashMap<String, Object>();
		if (null == goodsBrandPage) {
			result.put("total", 0);
			result.put("rows", new ArrayList());
		} else {
			result.put("total", goodsBrandPage.getTotalSize());
			result.put("rows", goodsBrandPage.getDataList());
		}

		return JsonUtil.javaObjectToJsonString(result);
	}

	/**
	 * 解析req并批量插入
	 * 
	 * @param req
	 * @throws DocumentException
	 * @throws IOException
	 * @throws BaseException
	 */
	@RequestMapping(value="/xmlMD103",produces = "text/plain;charset=UTF-8")
	public void saveGoods(HttpServletRequest req) throws BaseException {
		//回写数据
		DataBack db=new DataBack();
		//header部分信息
		HashMap<String,String> headerMap = new HashMap<String,String>();
		List<GoodsBrand> parseXML=null;
		//错误信息map
		Map<String, String> detailsMap=new HashMap<String, String>();
		try {
			parseXML = this.parseXML(req,headerMap);
			goodsBrandService.insertBatchGoodsBrand(parseXML);
			db.setDetailResult(DataBack.DETAILRESULT_SUCCESS);
		} catch (DocumentException e) {
			db.setDetailResult(DataBack.DETAILRESULT_FAILURE);
			detailsMap.put("xml数据错误", "解析xml数据失败");
			e.printStackTrace();
		} catch (IOException e) {
			db.setDetailResult(DataBack.DETAILRESULT_FAILURE);
			detailsMap.put("找不到xml数据错误", "获取xml数据失败");
			e.printStackTrace();
		}
		
		db.setDetailInterfaceO(headerMap.get("interface_id"));
		db.setDetailMessageO(headerMap.get("message_id"));
		db.setHeaderMessageId(UUIDUtil.getUUID().toString());//任意的32位值即可
		db.setHeaderDtsend("");
		db.setDetails(detailsMap);
		//数据回写
		DataBackUtil.response2XML(db);
	}

	/**
	 * 解析xml
	 * @throws BaseException
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unchecked")
	protected List<GoodsBrand> parseXML(HttpServletRequest req,HashMap<String,String> headerMap)
			throws BaseException, DocumentException, IOException {
		SAXReader reader = new SAXReader();
		// Document document = reader.read(new File("d:/MM001.xml"));
		Document document = reader.read(req.getInputStream());
		Element rootElement = document.getRootElement();
		Element headerElement = (Element) rootElement
				.selectSingleNode("//HEADER");
		// 解析header部分
		String interface_id = headerElement.element("INTERFACE_ID")
				.getTextTrim();
		String message_id = headerElement.element("MESSAGE_ID").getTextTrim();
		String sender = headerElement.element("SENDER").getTextTrim();
		String receiver = headerElement.element("RECEIVER").getTextTrim();
		String dtsend = headerElement.element("DTSEND").getTextTrim();

		//Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("interface_id", interface_id);
		headerMap.put("message_id", message_id);
		headerMap.put("sender", sender);
		headerMap.put("receiver", receiver);
		headerMap.put("dtsend", dtsend);
		// 解析数据部分
		List<Element> itemList = rootElement
				.selectNodes("//XML_DATA/MD103/ITEMS/ITEM");// ok
		// 封装结果数据
		List<GoodsBrand> resultList = new ArrayList<GoodsBrand>();
		// 遍历
		for (Iterator<Element> iterator = itemList.iterator(); iterator
				.hasNext();) {
			GoodsBrand goods = new GoodsBrand();
			Element element = (Element) iterator.next();
			goods.setBrandCode(element.element("PRODH").getTextTrim());// 品牌
			goods.setCnText(element.element("VTEXT").getTextTrim());// 中文描述
			goods.setEnText(element.element("TEXT1").getTextTrim());// 英文描述
			goods.setBrandClass(element.element("ZPINPAI").getTextTrim());// 品牌类型
			goods.setUpdateFlag(element.element("UPDATE_FLAG").getTextTrim());// 更新标志

			resultList.add(goods);

		}
		return resultList;

	}

}
