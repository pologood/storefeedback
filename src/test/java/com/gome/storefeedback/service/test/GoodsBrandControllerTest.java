package com.gome.storefeedback.service.test;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.geronimo.mail.util.StringBufferOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
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

import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsBrandService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;

/**
 * 解析xml并插入数据的测试用例
 * 
 * @author Ma.Mingyang
 * @date 2015年2月9日下午12:25:37
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class GoodsBrandControllerTest extends
		AbstractTransactionalSpringContextTestCase {

	@Resource
	private GoodsBrandService service;

	@Test
	@Rollback(false)
	public void testSaveGoods() throws BaseException, DocumentException,
			IOException {
		service.insertBatchGoodsBrand(parseXML());
	}

	/**
	 * 解析xml
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected List<GoodsBrand> parseXML() throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("d:/MD103.xml"));
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
				.setPath("/storefeedback/n/goodsBrand/xmlMD103")
				.build();
		//post.setURI(uri);
		System.out.println(uri+"---------");
		HttpPost post=new HttpPost(uri);
		//HttpPost post=new HttpPost("http://localhost:8080/storefeedback/n/goodsBrand/xmlMD103");
		//HttpEntity entity =new StringEntity(createXML());
		HttpEntity entity=new FileEntity(new File("d:/MD103.xml"));
		post.setEntity(entity);
		
		//post.setHeader("Host", "www.baidu.com");
		
		HttpResponse response = client.execute(post);
		
		//System.out.println("----"+post.getHeaders("Host")[0]+"----");
		
		HttpEntity entity2 = response.getEntity();
		
		System.out.println(EntityUtils.toString(entity2));
		//System.out.println(createXML());
	}
	
	/**
	 * 生产xml，目前是假数据
	 * 
	 * @throws BaseException
	 */
	protected String createXML() throws BaseException {
		Document doc = DocumentHelper.createDocument();
		// root
		Element root = doc.addElement("ns1:MT_MDM_Req");
		root.addAttribute("xmlns:ns1", "http://gome.com/GSM/MDM/Inbound");
		// HEADER
		Element first = root.addElement("HEADER");
		first.addElement("INTERFACE_ID").addText("MD104");
		first.addElement("MESSAGE_ID").addText("123123123123123123123");
		first.addElement("SENDER").addText("GSM");
		first.addElement("RECEIVER").addText("ECC");
		first.addElement("DTSEND").addText("1");
		// XML_DATA
		Element xml_data = root.addElement("XML_DATA");
		Element md103 =xml_data.addElement("MD103");
		Element items =md103.addElement("ITEMS");
		
		Element second = items.addElement("ITEM");
		second.addElement("PRODH").addText("1230006");
		second.addElement("VTEXT").addText("na123me2");
		second.addElement("TEXT1").addText("123");
		second.addElement("ZPINPAI").addText("123");
		second.addElement("UPDATE_FLAG").addText("1");
		/*Element details = second.addElement("DETAILS");
		details.addElement("KEY").addText("key1");
		details.addElement("MESSAGE").addText("MESSAGE1");*/

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		BufferedOutputStream b=new BufferedOutputStream(System.out);
		StringBuffer sb=new StringBuffer();
		try {//将数据字符串放到输出流中
			XMLWriter writer = new XMLWriter(new StringBufferOutputStream(sb), outputFormat);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			throw new BaseException("return value error!");
		}
		return sb.toString();
	}

	/*public static void main(String[] args) throws BaseException {
		createXML();
	}*/
}
