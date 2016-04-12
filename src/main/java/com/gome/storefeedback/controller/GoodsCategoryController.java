package com.gome.storefeedback.controller;

import java.io.IOException;
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

import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsCategoryService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 商品分类控制器
 * 
 * @author Ma.Mingyang
 * @date 2015年2月9日下午12:28:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

	private GoodsCategoryService goodsCategoryService;

	public GoodsCategoryService getGoodsCategoryService() {
		return goodsCategoryService;
	}

	@Autowired
	public void setGoodsCategoryService(
			@Qualifier("goodsCategoryService") GoodsCategoryService goodsCategoryService) {
		this.goodsCategoryService = goodsCategoryService;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String findByPage(GoodsCategory goodsCategory, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> inMap = new HashMap<String, Object>();
		if (null != goodsCategory.getClassCode()
				&& !"".equals(goodsCategory.getClassCode())) {
			inMap.put("classCode", goodsCategory.getClassCode());
		} else {
			inMap.put("classCode", null);
		}
		if (null != goodsCategory.getClassName()
				&& !"".equals(goodsCategory.getClassName())) {
			inMap.put("className", goodsCategory.getClassName());
		} else {
			inMap.put("className", null);
		}

		PaginationParameters param = new PaginationParameters(request, response);
		Page<GoodsCategory> goodsCategoryPage = this.goodsCategoryService
				.findGoodsCategoryByPage(inMap, param);

		Map<String, Object> result = new HashMap<String, Object>();
		if (null == goodsCategoryPage) {
			result.put("total", 0);
			result.put("rows", new ArrayList<GoodsCategory>());
		} else {
			result.put("total", goodsCategoryPage.getTotalSize());
			result.put("rows", goodsCategoryPage.getDataList());
		}

		return JsonUtil.javaObjectToJsonString(result);
	}

	/**
	 * 请求发送xml数据并插入数据库
	 * @param req
	 * @throws DocumentException
	 * @throws IOException
	 * @throws BaseException
	 */
	@RequestMapping(value="/xmlMD014",produces = "text/plain;charset=UTF-8")
	public void saveGoods(HttpServletRequest req) throws BaseException {
		//回写数据
		DataBack db=new DataBack();
		//header部分信息
		HashMap<String,String> headerMap = new HashMap<String,String>();
		List<GoodsCategory> parseXML=null;
		//错误信息map
		Map<String, String> detailsMap=new HashMap<String, String>();
		try {
			parseXML = this.parseXML(req,headerMap);
			goodsCategoryService.insertBatchGoodsCategory(parseXML);
			db.setDetailResult(DataBack.DETAILRESULT_SUCCESS);
		} catch (DocumentException e) {
			db.setDetailResult(DataBack.DETAILRESULT_FAILURE);
			detailsMap.put("xml数据错误", "解析xml数据失败");
			e.printStackTrace();
		} catch (IOException e) {
			//db.setDetailResult("F");
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
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected List<GoodsCategory> parseXML(HttpServletRequest req,HashMap<String, String> headerMap)
			throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		// Document document = reader.read(new File("d:/MD014.xml"));
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

		//headerMap = new HashMap<String, String>();
		headerMap.put("interface_id", interface_id);
		headerMap.put("message_id", message_id);
		headerMap.put("sender", sender);
		headerMap.put("receiver", receiver);
		headerMap.put("dtsend", dtsend);
		// 解析数据部分
		List<Element> itemList = rootElement
				.selectNodes("//XML_DATA/MD014/ITEMS/ITEM");// ok
		// 封装结果数据
		List<GoodsCategory> resultList = new ArrayList<GoodsCategory>();
		// 遍历
		for (Iterator<Element> iterator = itemList.iterator(); iterator
				.hasNext();) {
			GoodsCategory goods = new GoodsCategory();
			Element element = (Element) iterator.next();
			goods.setClassCode(element.element("CLASS").getTextTrim());// 分类代码
			goods.setClassName(element.element("KSCHL").getTextTrim());// 分类名称 
			goods.setClassLevel(element.element("ZJB").getTextTrim());// 分类级别
			goods.setParentClassCode(element.element("CLASS2").getTextTrim());// 上级分类代码 
			goods.setIsLeaf(Integer.parseInt(element.element("ZMJ").getTextTrim()));// 是否末级
			goods.setDivisionCode(element.element("ABTNR").getTextTrim());// 事业部代码 
			goods.setDivisionName(element.element("VTEXT")
					.getTextTrim());//  事业部名称
			goods.setUpdateFlag(element.element("UPDATE_FLAG")
					.getTextTrim());// 更新标志

			resultList.add(goods);

		}
		return resultList;

	}
}
