package com.gome.storefeedback.service.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class ECCApiTest {
    @Test
    public void saveXMLTest() throws Exception{
        File file = new File("FI172.xml");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/storefeedback/n/ecc/saveXML");
        post.setEntity(new InputStreamEntity(new FileInputStream(file), ContentType.create("text/plain", "UTF-8")));
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine());
        
    }
}
/*String url = "_http://127.0.0.1:8080/saletarget/n/api/xml";
HttpPost httpPost = new HttpPost(url);
File file = new File("MM086.xml");
httpPost.setEntity(new InputStreamEntity(new FileInputStream(file), ContentType.create("text/plain", "UTF-8")));
CloseableHttpClient httpclient = HttpClients.createDefault();
CloseableHttpResponse response = httpclient.execute(httpPost);
System.out.println(response.getStatusLine());*/