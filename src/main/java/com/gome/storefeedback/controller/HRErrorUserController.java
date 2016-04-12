package com.gome.storefeedback.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToHrService;

@Controller
@RequestMapping("/hr")
public class HRErrorUserController {
	@Resource
	private SapFeedbackCheckEmpToHrService sapFeedbackCheckEmpToHrService;
	
	@RequestMapping(value = "/saveXML", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
	void saveXML(HttpServletRequest request, HttpServletResponse response) throws BaseException{
		try {
			xmlHandler(request);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private void xmlHandler(HttpServletRequest request) throws IOException, DocumentException,
    BaseException {
		InputStream is = null;
		try {
		    is = request.getInputStream();
		    SAXReader reader = new SAXReader();
		    Document doc = reader.read(is);
		
		    List<CheckSapFeedbackEmpCount> xmlData = new ArrayList<CheckSapFeedbackEmpCount>();
        	
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
        	} 
        	//HR 返回的数据插入到 扫描推送表 hr_scan_bytime
        	if(xmlData != null && xmlData.size() > 0){
        		sapFeedbackCheckEmpToHrService.deleteFlag();
        		sapFeedbackCheckEmpToHrService.insertToHrScan(xmlData);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
