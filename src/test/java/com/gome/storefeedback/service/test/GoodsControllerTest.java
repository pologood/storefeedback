package com.gome.storefeedback.service.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.geronimo.mail.util.StringBufferOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;

/**
 * 测试批量删除
 * 
 * @author Ma.Mingyang
 * @date 2015年2月9日上午10:45:12
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class GoodsControllerTest extends
		AbstractTransactionalSpringContextTestCase {

	@Resource
	private GoodsService goodsService;

	@Test
	@Rollback(false)
	public void testSaveGoods() throws BaseException, DocumentException,
			IOException {
		goodsService.insertBatchGoods(this.parseXML());
	}

	/**
	 * 解析xml
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> parseXML() throws DocumentException, IOException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("d:/MD001.xml"));
		// Document document = reader.read(req.getInputStream());
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

		Map<String, String> headerMap = new HashMap<String, String>();
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
			System.out.println("--------------" + resultList);

		}
		return resultList;

	}

	/*public static void main(String[] args) throws DocumentException,
			IOException {
		// System.out.println(parseXML());

	}*/
	
	/**
	 * 模拟http post请求，调用方法，将xml数据包含的流中
	 * @throws BaseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Test
	public void httpReq() throws BaseException, ClientProtocolException, IOException, URISyntaxException{
		HttpClient client=new DefaultHttpClient();
		//post请求
		
		URI uri=new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080)
				.setPath("/storefeedback/n/goods/xmlMD001")
				.build();
		//post.setURI(uri);
		System.out.println(uri+"---------");
		HttpPost post=new HttpPost(uri);
		//HttpPost post=new HttpPost("http://localhost:8080/storefeedback/n/goodsBrand/xmlMD103");
		//System.out.println(createXML()+"-----------------");
		//HttpEntity entity =new StringEntity(new String(createXML().getBytes(),"gbk"));
		HttpEntity entity=new FileEntity(new File("d:/MD001.xml"));
		//HttpEntity entity=new 
		//new Header("Content-Type", "text/html; charset=GBK");
		//post.setHeader("Content-Type", "text/html; charset=utf-8");
		post.setEntity(entity);
		
		//post.setHeader("Host", "www.baidu.com");
		
		HttpResponse response = client.execute(post);
		
		//System.out.println("----"+post.getHeaders("Host")[0]+"----");
		
		HttpEntity entity2 = response.getEntity();
		
		System.out.println(EntityUtils.toString(entity2)+"-----------");
		//System.out.println(createXML());
	}
	
	/**
	 * 生产xml
	 * 
	 * @throws BaseException
	 */
	protected String createXML() throws BaseException {
		//@Test
		//public void createXML() throws BaseException {
		Document doc = DocumentHelper.createDocument();
		//doc.setXMLEncoding("utf-8");
		// root
		Element root = doc.addElement("ns1:MT_MDM_Req");
		root.addAttribute("xmlns:ns1", "http://gome.com/GSM/MDM/Inbound");
		// HEADER
		Element first = root.addElement("HEADER");
		first.addElement("INTERFACE_ID").addText("MD001");
		first.addElement("MESSAGE_ID").addText("123123123123123123123");
		first.addElement("SENDER").addText("GSM");
		first.addElement("RECEIVER").addText("ECC");
		first.addElement("DTSEND").addText("1");
		// XML_DATA
		Element xml_data = root.addElement("XML_DATA");
		Element md103 =xml_data.addElement("MD014");
		Element items =md103.addElement("ITEMS");
		
		Element second = items.addElement("ITEM");
		second.addElement("CLASS").addText("R3023");
		second.addElement("KSCHL").addText("试试");//乱码
		second.addElement("ZJB").addText("3");
		second.addElement("CLASS2").addText("R30");
		second.addElement("ZMJ").addText("1");
		second.addElement("ABTNR").addText("1");
		second.addElement("VTEXT").addText("1");
		second.addElement("UPDATE_FLAG").addText("1");
		/*Element details = second.addElement("DETAILS");
		details.addElement("KEY").addText("key1");
		details.addElement("MESSAGE").addText("MESSAGE1");*/

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		//outputFormat.setEncoding("utf-8");//编码
		BufferedOutputStream b=new BufferedOutputStream(System.out);
		//PipedOutputStream po=new PipedOutputStream();
		StringBuffer sb=new StringBuffer();
		
		try {//将数据字符串放到输出流中
			//new XMLWriter(stringb);
			XMLWriter writer = new XMLWriter(new StringBufferOutputStream(sb), outputFormat);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			throw new BaseException("return value error!");
		}
		return sb.toString();
		//return null;
	}
}
