package com.gome.storefeedback.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.XMLWriter;

import com.gome.storefeedback.common.CustomizedPropertyPlaceholderConfigurer;
import com.gome.storefeedback.exception.BaseException;

/**
 * 数据回写工具类
 * 
 * @author Ma.Mingyang
 * @date 2015年2月10日上午11:22:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class DataBackUtil {

    private static String url = "http://10.128.11.6:50000/sap/xi/adapter_plain?namespace=http://gome.com/GSM/COMMON/Outbound&interface=SI_FEEDBACK_Out&service=BC_GSM&party=&agency=&scheme=&QOS=EO&sap-user=GSMUSER&sap-password=GSMUSER&sap-client=001&sap-language=EN";
    static {
        url = CustomizedPropertyPlaceholderConfigurer.getContextProperty("ecc.gms.url").toString().trim();
    }

    /**
     * 回写方法入口
     * 
     * @param dataBack
     * @throws BaseException
     * @throws IOException
     */
    // public static void response2XML(DataBack dataBack) throws BaseException{
    public static void response2XML(DataBack dataBack) {
        Document doc = createDocument4dataBack(dataBack);
        try {
            String sendPost = sendPost(doc.asXML());
            System.out.println("-------------------------------" + sendPost);
        } catch (IOException e) {
            // throw new BaseException("回调url连接错误");
            e.printStackTrace();
        }
    }

    /**
     * 回写xml数据 HttpClient
     * 
     * @param doc
     * @return
     * @throws IOException
     */
    private static String sendPost(String asXML) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(asXML);
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        System.out.println(response.getStatusLine());
        return response.getStatusLine().toString();
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
    private static Document createDocument4dataBack(DataBack dataBack) {
        System.out.println(dataBack + "-----------------");
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("ns0:MT_FEEDBACK_Out");
        root.add(new Namespace("ns0", "http://gome.com/GSM/COMMON/Outbound"));

        Element headerElement = DocumentHelper.createElement("HEADER");
        headerElement.addElement("INTERFACE_ID").setText(dataBack.getHeaderInterfaceId());
        headerElement.addElement("MESSAGE_ID").setText(dataBack.getHeaderMessageId());//
        headerElement.addElement("SENDER").setText(dataBack.getHeaderSender());
        headerElement.addElement("RECEIVER").setText(dataBack.getHeaderReceiver());
        headerElement.addElement("DTSEND").setText(dataBack.getHeaderDtsend());
        root.add(headerElement);

        Element detailElement = root.addElement("DETAIL");
        detailElement.addElement("INTERFACE_O").setText(dataBack.getDetailInterfaceO());
        detailElement.addElement("MESSAGE_O").setText(dataBack.getDetailMessageO());
        detailElement.addElement("RESULT").setText(dataBack.getDetailResult());
        Element detailsElement = detailElement.addElement("DETAILS");
        if (!dataBack.getDetails().isEmpty()) {
            for (String key : dataBack.getDetails().keySet()) {
                detailsElement.addElement("KEY").setText(key);
                detailsElement.addElement("MESSAGE").setText(dataBack.getDetails().get(key));
            }
        } else {
            detailsElement.addElement("KEY");
            detailsElement.addElement("MESSAGE");
        }
        return doc;
    }
}