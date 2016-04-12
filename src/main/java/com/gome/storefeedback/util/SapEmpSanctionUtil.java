package com.gome.storefeedback.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

import com.gome.storefeedback.common.CustomizedPropertyPlaceholderConfigurer;
import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.exception.BaseException;

public class SapEmpSanctionUtil {
	//测试url
	private static String url = "http://10.128.11.10:50000/sap/xi/adapter_plain?namespace=http://gome.com/GSM/HCM/Outbound&interface=SI_HCM_Out_Asy&service=BC_GSM&party=&agency=&scheme=&QOS=EO&sap-user=GSMUSER&sap-password=GSMUSER&sap-client=001&sap-language=EN";
	static {
//		实际url，在配置文件中定义
        url = CustomizedPropertyPlaceholderConfigurer.getContextProperty("sap.hr.url").toString().trim();
    }
	
	/**
     * 回写方法入口
     * 
     * @param dataBack
     * @throws BaseException
     * @throws IOException
     */
    // public static void response2XML(SapEmpSanction sapEmpSanction) throws BaseException{
    public static List<CheckSapFeedbackEmpCount> response2XML(SapEmpSanction sapEmpSanction) {
        Document doc = createDocument4dataBack(sapEmpSanction);
        List<CheckSapFeedbackEmpCount> xmlData = new ArrayList<CheckSapFeedbackEmpCount>();
        try {
        	xmlData = sendPost(doc.asXML());
        	
        } catch (IOException e) {
            // throw new BaseException("回调url连接错误");
            e.printStackTrace();
        }
        return xmlData;
    }

    /**
     * 回写xml数据 HttpClient
     * 
     * @param doc
     * @return
     * @throws IOException
     */
    private static List<CheckSapFeedbackEmpCount> sendPost(String asXML) throws IOException {
    	Document doc = null;
    	List<CheckSapFeedbackEmpCount> xmlData = null;
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(asXML);
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
	        CloseableHttpResponse response = httpclient.execute(httpPost);
	        int status = response.getStatusLine().getStatusCode();
	        if (status == 200) {
	        	/*HttpEntity entity = response.getEntity();
//	        	long contentLength = entity.getContentLength();//判断是否有内容没有返回，有待验证
//	        	if(contentLength != 0){
        		// 得到实体输入流
        		InputStream inSm = entity.getContent();
        		BufferedReader br = new BufferedReader(new InputStreamReader(
        				inSm,"GBK"));
        		String xmlString ="";
		        for (String temp = br.readLine(); temp != null; xmlString += temp, temp = br
		        .readLine());
        		// 去除字符串中的换行符，制表符，回车符。
		        if(xmlString != ""){
		        	InputStream stream2 = new ByteArrayInputStream(xmlString
		        			.getBytes("UTF-8"));
		        	
		        	SAXReader saxReader = new SAXReader();
		        	saxReader.setEncoding("UTF-8");
		        	doc = (Document) saxReader.read(new InputSource(stream2));
		        	//获取 HR 返回值，如果包含 员工编码 和 月份，封装进List<CheckSapFeedbackEmpCount> (插入定时扫描推送 HR 表格)
		        	xmlData = new ArrayList<CheckSapFeedbackEmpCount>();
		        	
		        	Element rootElement = doc.getRootElement();
		        	Element resData = rootElement.element("DETAIL");
		        	String result = resData.element("RESULT").getTextTrim();
		        	if("F".equals(result)){
		        		List details = resData.elements("DETAILS");//包含数据的集合节点
		        		for (Object object : details) {//获取数据节点
		        			Element element = (Element) object;
		        			Node checkSapFeedbackEmpCount = element.selectSingleNode("KEY");
		        			String str = checkSapFeedbackEmpCount.getText().trim();
		        			String[] splits = str.split("\\#");
		        			String empNumber = splits[0];
		        			String sanctionMonth = splits[1];
		        			CheckSapFeedbackEmpCount checkSapFeedbackEmpCount2 = new CheckSapFeedbackEmpCount();
		        			checkSapFeedbackEmpCount2.setEmpNumber(empNumber);
		        			checkSapFeedbackEmpCount2.setSanctionMonth(sanctionMonth);
		        			
		        			xmlData.add(checkSapFeedbackEmpCount2);
		        		}
		        	} else {
		        		return null;
		        	}
		        }*/
	        	System.out.println("连接PI接口成功");
		    }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return xmlData;
    }

    /**
     * 回写xml数据 URLConnection
     * 
     * @param doc
     * @return
     * @throws IOException
     */
    @Deprecated
    private static String sendPost(Document doc) throws IOException {
        OutputStream out = null;
        BufferedReader in = null;
        XMLWriter writer = null;
        String result = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            // conn.setRequestProperty("user-agent",
            // "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            // 发送请求参数
            writer = new XMLWriter(out);
            writer.write(doc);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("http post result : " + result);
        return result;
    }

    /**
     * 构造回写的xml数据
     * 
     * @param dataBack
     * @return Document
     */
    private static Document createDocument4dataBack(SapEmpSanction sapEmpSanction) {
        System.out.println(sapEmpSanction + "-----------------");
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("ns0:MT_HCM_Req");
        root.add(new Namespace("ns0", "http://gome.com/GSM/HCM/Outbound"));

        Element headerElement = DocumentHelper.createElement("HEADER");
        headerElement.addElement("INTERFACE_ID").setText(sapEmpSanction.getHeaderInterfaceId());
        headerElement.addElement("MESSAGE_ID").setText(sapEmpSanction.getHeaderMessageId());//
        headerElement.addElement("SENDER").setText(sapEmpSanction.getHeaderSender());
        headerElement.addElement("RECEIVER").setText(sapEmpSanction.getHeaderReceiver());
        headerElement.addElement("DTSEND").setText(sapEmpSanction.getHeaderDtsend());
        root.add(headerElement);

        Element xmlDataElement = root.addElement("XML_DATA");
        if(!sapEmpSanction.getXmlData().isEmpty()){
        	for(CheckSapFeedbackEmpCount checkSapFeedbackEmpCount : sapEmpSanction.getXmlData()){
        		Element HCM055 = xmlDataElement.addElement("HCM055");
        		HCM055.addElement("EMP_NUMBER").setText(checkSapFeedbackEmpCount.getEmpNumber());
        		HCM055.addElement("SANCTION_MONTH").setText(checkSapFeedbackEmpCount.getSanctionMonth());
        		HCM055.addElement("SANCTION_MONEY").setText(checkSapFeedbackEmpCount.getSanctionMoney());
        		HCM055.addElement("SANCTION_POINTS").setText(checkSapFeedbackEmpCount.getSanctionPoints());
        	}
        } 
        return doc;
    }
}
