package com.gome.storefeedback.controller.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.gsm.entity.org.Employee;
import com.gome.gsm.entity.org.Position;
import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.constant.NewCateGoryCnstant;
import com.gome.storefeedback.entity.LackDivision;
import com.gome.storefeedback.entity.NewImg;
import com.gome.storefeedback.entity.NewModel;
import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.LackDivisionService;
import com.gome.storefeedback.service.NewImgService;
import com.gome.storefeedback.service.NewModelService;
import com.gome.storefeedback.service.StoreService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.CollectionUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.util.UUIDUtil;

@Controller
@RequestMapping("/newModelsReport")
public class NewModelsReportContorller {
	 private static final Logger logger = LoggerFactory.getLogger(NewModelsReportContorller.class);
	 /** 新品类 */
    @Resource
    private NewModelService newModelService;
    @Resource
    private LogMessageSender logMessageSender;
    @Resource 
    private NewImgService newImgService;
    @Resource
    private LackDivisionService lackDivisionService;
    @Resource
	private EmployeeRemoteService employeeRemoteService;
    @Resource
	private StoreService storeService;
    /**
     * 初始化新型号提报
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/initModelsReport")
    public @ResponseBody
    String initModelsReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> result = new HashMap<String,Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
       // Map<String, Object> stsList = new HashMap<String, Object>();
        JSONArray jsonArray  = new JSONArray();
        JSONObject jsonObject  = new JSONObject();

        if (AppUtil.requestHandler(request, pmap, result)) {
        	//初始化返回app現有品類
        	try {
        	List<LackDivision> statusList  = lackDivisionService.lackDivisionList();
            	if(statusList != null){
            		for(LackDivision statu : statusList){
            			//stsList.put(statu.getDivisionCode(), statu.getDivisionName());
            			//初始化现有品类
            			JSONObject jsonObg  = new JSONObject();
            			jsonObg.put(NewCateGoryCnstant.CATEGORY_CODE, statu.getDivisionCode());
            			jsonObg.put(NewCateGoryCnstant.CATEGPRY_DESC, statu.getDivisionName());
            			jsonArray.add(jsonObg);
            		}
            		jsonObject.put("data", jsonArray);
            	}	
        	}catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        	logger.debug(/*JsonUtil.javaObjectToJsonString(stsList)*/jsonObject.toString());
        	return GzipAESUtil.compressThenEncryptAES(/*JsonUtil.javaObjectToJsonString(stsList)*/jsonObject.toString());
        }else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
        	logger.debug(JsonUtil.javaObjectToJsonString(result));
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }
    
	 /**
     * 插入型号类提报
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/subModelsReport")
    public @ResponseBody
    String subModelReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
        	
            try {
            	Employee emp =employeeRemoteService.findEmployeeById(pmap.get("storeFeedbackUserId").toString());
            	if(emp != null){
            		List<Position> positions = emp.getPositions();
            			for(Position pos :positions){
            				String comp = pos.getPositionCode().substring(0, 4);
            				
            				Map<String, Object> inMap = new HashMap<String, Object>();
            				inMap.put("store_code", comp);
            				Store store = storeService.findStoreByCode(inMap);
            				if(store != null){
            					pmap.put(NewCateGoryCnstant.REPORT_COMPANY_ID, store.getDivision_code());
            				}else{
            					pmap.put(NewCateGoryCnstant.REPORT_COMPANY_ID, comp);
            				}
            				
            				//pmap.put(NewCateGoryCnstant.REPORT_COMPANY_ID, comp);
            		}
            	}
            	
            	NewModel newModel = NewModelsReportContorller.mapToNewNewModel(pmap);
            	newModel.setIsHandle(NewCateGoryCnstant.IS_HANDLE_N);
            	//newModel.setIsImport(NewCateGoryCnstant.IS_HANDLE_M);
            	newModel.setId(UUIDUtil.getUUID());
            	  try{
            		newModelService.insert(newModel);
                  }catch(BaseException e){
                  	logger.error(e.getMessage(), e);
                      result.put(APPErrorInfo.ERRORCODE, "11046");
              	    result.put(APPErrorInfo.ERRORMSG, e);
              	    return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
                  }
                // 存储图片
                List<NewImg> imgList  = mapToImgList(pmap);
                if(!CollectionUtil.isEmpty(imgList) && imgList.size()>2){
                	for(NewImg img : imgList){
                		img.setId(UUIDUtil.getUUID());
                		img.setCategoryModelId(newModel.getId());
                		newImgService.insert(img);
                	}
                }else{
                	result.put(APPErrorInfo.ERRORCODE, "11045");
           	       result.put(APPErrorInfo.ERRORMSG, "图片必须传三张");
           	      return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
                }
                
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                result.put(APPErrorInfo.ERRORCODE, "11046");
        	    result.put(APPErrorInfo.ERRORMSG, e);
                AppUtil.exceptionHandler(result);
            }
        }
        logger.debug(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }
    
    /**
     * 检验提报的新型号是否存在
     * @param newCategory
     * @return
     * @throws Exception
     */
    private int checkNewModel(NewModel newModel) throws Exception {
    	return newModelService.checkNewModel(newModel);
    }
    
    /**
     * 提报图片转换
     * @param pmap
     * @return
     */
    private  List<NewImg> mapToImgList(Map<String, Object> pmap) {
    	List<NewImg> imgList  = new ArrayList<NewImg>();
	   	 if (pmap.get(NewCateGoryCnstant.IMG_1) != null) {
	   		 NewImg img  = new NewImg();
	   		 img.setImgInfo(pmap.get(NewCateGoryCnstant.IMG_1).toString());
	            img.setSts(NewCateGoryCnstant.STS_A);
	            img.setType(NewCateGoryCnstant.MODEL);
	            imgList.add(img);
	   	 }
	   	 if (pmap.get(NewCateGoryCnstant.IMG_2) != null) {
	   		 NewImg img  = new NewImg();
	   		 img.setImgInfo(pmap.get(NewCateGoryCnstant.IMG_2).toString());
	            img.setSts(NewCateGoryCnstant.STS_A);
	            img.setType(NewCateGoryCnstant.MODEL);
	            imgList.add(img);
	   	 }
	   	 if (pmap.get(NewCateGoryCnstant.IMG_3) != null) {
	   		 NewImg img  = new NewImg();
	   		 img.setImgInfo(pmap.get(NewCateGoryCnstant.IMG_3).toString());
	            img.setSts(NewCateGoryCnstant.STS_A);
	            img.setType(NewCateGoryCnstant.MODEL);
	            imgList.add(img);
	   	 }    
    	return imgList;
    }
    /**
     * 提报的新品类转换
     * @param pmap
     * @return
     */
    private static NewModel mapToNewNewModel(Map<String, Object> pmap) {
    	NewModel newModel = new NewModel();
    	//提报新品类---品类编码不填
    	if(pmap.get(NewCateGoryCnstant.CATEGORY_CODE) != null){
    		newModel.setCategoryCode(pmap.get(NewCateGoryCnstant.CATEGORY_CODE).toString());
    	}
    	//品类描述--提报一般填写描述
    	if(pmap.get(NewCateGoryCnstant.CATEGPRY_DESC) != null){
    		newModel.setCategoryDesc(pmap.get(NewCateGoryCnstant.CATEGPRY_DESC).toString());
    	}
    	//提报--新型号编码可以不写
    	if(pmap.get(NewCateGoryCnstant.MODEL_CODE) != null){
    		newModel.setModelCode(pmap.get(NewCateGoryCnstant.MODEL_CODE).toString());
    	}
    	if(pmap.get(NewCateGoryCnstant.MAIN_DESC) != null){
    		newModel.setModelDesc(pmap.get(NewCateGoryCnstant.MAIN_DESC).toString());
    	}
    	//提报品类主要描述
    	if(pmap.get(NewCateGoryCnstant.MAIN_DESC) != null){
    		newModel.setMainDesc(pmap.get(NewCateGoryCnstant.MAIN_DESC).toString());
    	}
    	
    	//经销商
    	if(pmap.get(NewCateGoryCnstant.DEALER) != null){
    		newModel.setDealer(pmap.get(NewCateGoryCnstant.DEALER).toString());
    	}
    	//提报人公司编码  从 accesstoken中获得
    	if(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID) != null){
    		newModel.setReportCompanyId(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());
    	}
    	//提报人编码
    	if(pmap.get(NewCateGoryCnstant.EMP_ID) != null){
    		newModel.setReportEmployeeId(pmap.get(NewCateGoryCnstant.EMP_ID).toString());
    	}
    	//提报人姓名
    	if(pmap.get(NewCateGoryCnstant.EMPLOYEE_NAME) != null){
    		newModel.setReportEmployeeName(pmap.get(NewCateGoryCnstant.EMPLOYEE_NAME).toString());
    	}
    	
    	if(pmap.get(NewCateGoryCnstant.BRAND_CODE) != null){
    		newModel.setBrandCode(pmap.get(NewCateGoryCnstant.BRAND_CODE).toString());
    	}
    	
    	if(pmap.get(NewCateGoryCnstant.BRAND_NAME) != null){
    		newModel.setBrandName(pmap.get(NewCateGoryCnstant.BRAND_NAME).toString());
    	}
    	//提报时间
    	newModel.setCreateTime(new Date());
        return newModel;
    }
}
