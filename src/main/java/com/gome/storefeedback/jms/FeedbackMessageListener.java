package com.gome.storefeedback.jms;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.common.CustomizedPropertyPlaceholderConfigurer;
import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackMessagePushRecordService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.OauthUtil;

public class FeedbackMessageListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackMessageListener.class);

    private static String url="";
    
    static{
        String ip = CustomizedPropertyPlaceholderConfigurer.getContextProperty("msg.push.ip").toString().trim();
        String port=CustomizedPropertyPlaceholderConfigurer.getContextProperty("msg.push.port").toString().trim();
        String path=CustomizedPropertyPlaceholderConfigurer.getContextProperty("msg.push.path").toString().trim();
        url="http://"+ip+":"+port+path;
    }
    
    @Resource
    public FeedbackMessagePushRecordService feedbackMessagePushRecordService;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage receiveMessage = (TextMessage) message;
                String jsonStr = receiveMessage.getText();
                logger.info("----接收到反馈通知消息推送记录消息： " + jsonStr + "--------------------");
                // 推送消息 http请求 如果成功 则保存 一下消息内容
                FeedbackMessagePushRecord entity = (FeedbackMessagePushRecord) JsonUtil.getJsonStringToObject(jsonStr,
                        FeedbackMessagePushRecord.class);
                if(sendFeedbackMessagePushRecord(entity)){
                    this.feedbackMessagePushRecordService.insertFeedbackMessagePushRecord(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean sendFeedbackMessagePushRecord(FeedbackMessagePushRecord messageEntity) throws ClientProtocolException, BaseException, IOException{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("companyId", "10000");
        map.put("employeeId", messageEntity.getNotificationEmployeeId());
        map.put("title",messageEntity.getTitle());
        map.put("content",messageEntity.getContent());
        map.put("type", messageEntity.getType()+"");
        map.put("sender", BusinessGlossary.APP_STOREFEEDBACK);
        return sendPost(map);
    }
    private static boolean sendPost(Map<String,Object> map) throws BaseException, ClientProtocolException, IOException{
        String pValue = JsonUtil.javaObjectToJsonString(map);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("notification", pValue));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            System.out.println(url);
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println(EntityUtils.toString(responseEntity));
            return true;
        } finally{
            if(response!=null){
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(httpclient!=null){
                try {
                    httpclient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
