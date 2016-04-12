package com.gome.storefeedback.controller;

import java.io.File;
import java.io.FileOutputStream;
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
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 商品控制器
 * 
 * @author Ma.Mingyang
 * @date 2015年2月9日下午12:27:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

	private GoodsService goodsService;

	public GoodsService getGoodsService() {
		return goodsService;
	}

	@Autowired
	public void setGoodsService(
			@Qualifier("goodsService") GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	@RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String findByPage(Goods goods, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> inMap = new HashMap<String, Object>();
		if (null != goods.getGoodsCode() && !"".equals(goods.getGoodsCode())) {
			inMap.put("goodsCode", goods.getGoodsCode());
		} else {
			inMap.put("goodsCode", null);
		}
		if (null != goods.getGoodsBarcode()
				&& !"".equals(goods.getGoodsBarcode())) {
			inMap.put("goodsBarcode", goods.getGoodsBarcode());
		} else {
			inMap.put("goodsBarcode", null);
		}
		if (null != goods.getGoodsName() && !"".equals(goods.getGoodsName())) {
			inMap.put("goodsName", goods.getGoodsName());
		} else {
			inMap.put("goodsName", null);
		}

		PaginationParameters param = new PaginationParameters(request, response);
		Page<Goods> goodsPage = this.goodsService.findGoodsByPage(inMap, param);

		Map<String, Object> result = new HashMap<String, Object>();
		if (null == goodsPage) {
			result.put("total", 0);
			result.put("rows", new ArrayList<Goods>());
		} else {
			result.put("total", goodsPage.getTotalSize());
			result.put("rows", goodsPage.getDataList());
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
	@RequestMapping(value = "/xmlMD001", produces = "text/plain;charset=UTF-8")
	public void saveGoods(HttpServletRequest req) throws DocumentException,
			IOException, BaseException {
		// goodsService.insertBatchGoods(this.parseXML(req));
		// 回写数据
		DataBack db = new DataBack();
		// header部分信息
		HashMap<String, String> headerMap = new HashMap<String, String>();
		List<Goods> parseXML = null;
		// 错误信息map
		Map<String, String> detailsMap = new HashMap<String, String>();
		try {
			parseXML = this.parseXML(req, headerMap);
			goodsService.insertBatchGoods(parseXML);
			db.setDetailResult(DataBack.DETAILRESULT_SUCCESS);
			detailsMap.put("ok", "解析并插入成功");
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
		db.setHeaderMessageId(UUIDUtil.getUUID().toString());// 任意的32位值即可
		db.setHeaderDtsend("");
		db.setDetails(detailsMap);
		// 数据回写
		DataBackUtil.response2XML(db);
	}

	/**
	 * 解析xml
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected List<Goods> parseXML(HttpServletRequest req,
			HashMap<String, String> headerMap) throws DocumentException,
			IOException {
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

		// Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("interface_id", interface_id);
		headerMap.put("message_id", message_id);
		headerMap.put("sender", sender);
		headerMap.put("receiver", receiver);
		headerMap.put("dtsend", dtsend);
		// 解析数据部分
		List<Element> itemList = rootElement
				.selectNodes("//XML_DATA/MD001/ITEMS/ITEM");// ok
		// 封装结果数据
		List<Goods> resultList = new ArrayList<Goods>();
		// 遍历
		for (Iterator<Element> iterator = itemList.iterator(); iterator
				.hasNext();) {
			Goods goods = new Goods();
			Element element = (Element) iterator.next();
			goods.setGoodsCode(element.element("MATNR").getTextTrim());// 商品编码
			goods.setGoodsBarcode(element.element("EAN11").getTextTrim());// 商品条码
			goods.setGoodsName(element.element("MAKTX").getTextTrim());// 商品名称
			goods.setSpecificationsModel(element.element("GROES").getTextTrim());// 规格型号
			goods.setGoodsCategory(element.element("MATKL").getTextTrim());// 商品分类
			goods.setGoodsBrand(element.element("PRDHA").getTextTrim());// 品牌
			goods.setExtendedWarrantyPriceCap(element.element("INHBR")
					.getTextTrim());// 延保价格下限INHBR
			goods.setExtendedWarrantyPriceFloor(element.element("INHAL")
					.getTextTrim());// 延保价格上限
			goods.setOutputTaxRate(element.element("TAKLV").getTextTrim());// TAKLV销项税率
			goods.setUnitsOfMeasurement(element.element("MEINS").getTextTrim());// 计量单位
			goods.setUnitOfMeasureText(element.element("MSEHL").getTextTrim());// 度量单位文本
			goods.setLotId(element.element("XCHPF").getTextTrim());// 批次标识****
			goods.setWhetherBusinessGifts(Integer.parseInt(element
					.element("SFZP").getTextTrim()));// 是否经营赠品
			goods.setProductAttributes(element.element("ZZSPSXZ").getTextTrim());// 商品属性
			goods.setGoodsClass(element.element("MTART").getTextTrim());// 商品类型
			goods.setPlaceOfOrigin(element.element("ZZSPCD").getTextTrim());// 产地
			goods.setGoodsWeight(element.element("BRGEW").getTextTrim());// 重量（含包装)
			goods.setGoodsHeight(element.element("HOEHE").getTextTrim());// 高（mm）
			goods.setCategoryLevel1(element.element("CLASS1").getTextTrim());// 一级分类
			goods.setCategoryLevel2(element.element("CLASS2").getTextTrim());// 二级分类
			goods.setCategoryLevel3(element.element("CLASS3").getTextTrim());// 三级分类
			goods.setSelling(element.element("ZZSPMD").getTextTrim());// 产品卖点
			goods.setUpdateFlag(element.element("UPDATE_FLAG")
					.getTextTrim());// 更新标志

			resultList.add(goods);

		}
		return resultList;

	}

	/**
	 * 生产xml，目前是假数据
	 * 
	 * @throws BaseException
	 */
	protected void createXML() throws BaseException {
		Document doc = DocumentHelper.createDocument();
		// root
		Element root = doc.addElement("ns0:MT_FEEDBACK_Out");
		root.addAttribute("xmlns:ns0", "http://gome.com/GSM/COMMON/Outbound");
		// HEADER
		Element first = root.addElement("HEADER");
		first.addElement("INTERFACE_ID").addText("id1");
		first.addElement("MESSAGE_ID").addText("MESSAGE_ID");
		first.addElement("SENDER").addText("GSM");
		first.addElement("RECEIVER").addText("ECC");
		first.addElement("DTSEND").addText("1");
		// DETAIL
		Element second = root.addElement("DETAIL");
		second.addElement("INTERFACE_O").addText("id2");
		second.addElement("MESSAGE_O").addText("name2");
		second.addElement("RESULT").addText("name2");
		Element details = second.addElement("DETAILS");
		details.addElement("KEY").addText("key1");
		details.addElement("MESSAGE").addText("MESSAGE1");

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(
					"d:/a.xml")), outputFormat);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			throw new BaseException("return value error!");
		}
	}

}
