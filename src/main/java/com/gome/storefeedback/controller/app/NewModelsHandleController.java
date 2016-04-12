package com.gome.storefeedback.controller.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.constant.NewCateGoryCnstant;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.NewCategoryStatus;
import com.gome.storefeedback.entity.NewImg;
import com.gome.storefeedback.entity.NewModel;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.service.NewCategoryStatusService;
import com.gome.storefeedback.service.NewImgService;
import com.gome.storefeedback.service.NewModelService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.CollectionUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;

@Controller
@RequestMapping("/newModelsHandle")
public class NewModelsHandleController {
	private static final Logger logger = LoggerFactory.getLogger(NewModelsHandleController.class);
	@Resource 
	private NewCategoryStatusService newCategoryStatusService;
	@Resource
	private LogMessageSender logMessageSender;
	@Resource
    private NewModelService newModelService;
	@Resource
	private NewImgService newImgService;
	@Resource
	private GoodsService goodsService;
	
	 /**
     * 插入新品类提报
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/initModelsHandle")
    public @ResponseBody
    String initModelsHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> result = new HashMap<String,Object>();
        //Map<String, Map<String, Object>> mapresult = new HashMap<String, Map<String, Object>>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        //Map<String, Object> stsList = new HashMap<String, Object>();
        JSONObject jsonObg = new JSONObject();
        if (AppUtil.requestHandler(request, pmap, result)) {
        	//组装不补货原因
        	//NewCategoryStatus status  = new NewCategoryStatus();
        	Map<String, Object> status = new HashMap<String, Object>();
        	//status.setTableName(NewCateGoryCnstant.MODEL);
        	//status.setColumnName(NewCateGoryCnstant.IS_IMPORT);
        	status.put("tableName", NewCateGoryCnstant.MODEL);
        	status.put("columnName", NewCateGoryCnstant.NOT_IMPORT_DESC);
        	status.put("sts", NewCateGoryCnstant.STS_A);
        	List<NewCategoryStatus> statusList  = newCategoryStatusService.selectByEntity(status);
        	JSONArray jsonArraysts = new JSONArray();
        	if(statusList != null){
        		for(NewCategoryStatus statu : statusList){
        			JSONObject jsonObject = new JSONObject();
                	jsonObject.put(NewCateGoryCnstant.NOT_IMPORT_CODE, statu.getId());
                	jsonObject.put(NewCateGoryCnstant.NOT_IMPORT_DESC, statu.getReasonDesc());
                	jsonArraysts.add(jsonObject);
        		}
        	}
        	
        	//组装提报信息
        	
        	/*NewModel model = new NewModel();
        	model.setReportCompanyId(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());//根据公司id 查询新品提报信息
        	model.setIsHandle(NewCateGoryCnstant.IS_HANDLE_Y);//只展示未处理的
        	*/
        	Map<String, Object> cate = new HashMap<String,Object>();
        	cate.put("reportCompanyId", pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());
        	cate.put("isHandle", NewCateGoryCnstant.IS_HANDLE_Y);
        	JSONArray jsonArrays = new JSONArray();
        	
        	List<NewModel> cateList  = newModelService.selectList(cate);
        	//组装提报信息
        	if(cateList != null){
        		for(NewModel mod : cateList){
        			JSONObject jsonObgoryt = new JSONObject();
        			jsonObgoryt.put(NewCateGoryCnstant.ID, mod.getId());
        			jsonObgoryt.put(NewCateGoryCnstant.EMP_ID, mod.getReportEmployeeId());
        			jsonObgoryt.put(NewCateGoryCnstant.REPORT_EMP_NAME, mod.getReportEmployeeName());
        			jsonObgoryt.put(NewCateGoryCnstant.CATEGORY_CODE, mod.getCategoryCode());
        			jsonObgoryt.put(NewCateGoryCnstant.CATEGPRY_DESC, mod.getCategoryDesc());
        			jsonObgoryt.put(NewCateGoryCnstant.MODEL_CODE, mod.getModelCode());
        			jsonObgoryt.put(NewCateGoryCnstant.MODEL_DESC, mod.getModelDesc());
        			jsonObgoryt.put(NewCateGoryCnstant.DEALER, mod.getDealer());
        			jsonObgoryt.put(NewCateGoryCnstant.MAIN_DESC, mod.getMainDesc());
        			jsonArrays.add(jsonObgoryt);
        		}
        	}
        	 /*mapresult.put("reasons", stsList);
        	 mapresult.put("newCategorys", groyList);*/
        	jsonObg.put("result", jsonArraysts);
        	jsonObg.put("newModels", jsonArrays);
        	
        	logger.debug(/*JsonUtil.javaObjectToJsonString(jsonObg)*/jsonObg.toString());
        	/* mapresult.put("reasons", stsList);
        	logger.debug(JsonUtil.javaObjectToJsonString(mapresult));*/
        	return GzipAESUtil.compressThenEncryptAES(/*JsonUtil.javaObjectToJsonString(mapresult)*/jsonObg.toString());
        }else{
        	logger.debug(JsonUtil.javaObjectToJsonString(result));
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }
    /**
     * 处理新品类提报
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/subModelsHandle")
    public @ResponseBody String subModelsHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 Map<String, Object> result = new HashMap<String, Object>();
         Map<String, Object> pmap = new HashMap<String, Object>();

         if (AppUtil.requestHandler(request, pmap, result)) {
             try {
             	NewModel mewModel = NewModelsHandleController.mapToNewModels(pmap);
             	if(StringUtils.equals(mewModel.getIsImport(), NewCateGoryCnstant.IS_IMPORT_N) && StringUtils.isBlank(mewModel.getNotImportDesc())){
            		  result.put(APPErrorInfo.ERRORCODE, "11040");
            	      result.put(APPErrorInfo.ERRORMSG, "请填写不引进原因号");
            	      return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
               	}
             	Map<String, Object> inMap = new HashMap<String, Object>();
             	if(StringUtils.isNotBlank(mewModel.getModelCode())){
	             	String cond = "";
	             	cond = goodCode(mewModel.getModelCode());
	             	inMap.put("goodsCode", cond);
	             	Goods goods = goodsService.findGoodsById(inMap);
	             	if(goods == null && StringUtils.equals(mewModel.getIsImport(), NewCateGoryCnstant.IS_IMPORT_Y) ){
	             	  result.put(APPErrorInfo.ERRORCODE, "11038");
	          	      result.put(APPErrorInfo.ERRORMSG, "型号编号已存在");
	          	      return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
	             	}
	             }
	             	
             	if(StringUtils.isBlank(mewModel.getModelCode())){
             		mewModel.setIsHandle(NewCateGoryCnstant.IS_HANDLE_M);
             	}else{
             		mewModel.setIsHandle(NewCateGoryCnstant.IS_HANDLE_Y);
             	}
             	newModelService.updateByPrimaryKeySelective(mewModel);//更新处理信息
                 AppUtil.succussHandler(result);
                 logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                         JsonUtil.javaObjectToJsonString(result)));
             } catch (BaseException e) {
                 logger.error(e.getMessage(), e);
                 AppUtil.exceptionHandler(result);
             }
         }
         logger.debug(JsonUtil.javaObjectToJsonString(result));
         return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }
    
    
    /***
     * 返回处理好的员工编码
     * @param empnumber
     */
    public static String goodCode(String goodCode)
    {
        if(goodCode.length()>=18){
            return goodCode;
        }else{
          int count = 8- goodCode.length();
          StringBuffer sb = new StringBuffer();
          for(int i=0;i<count;i++)
          {
              sb.append("0");
          }
          sb.append(goodCode);
          
          return sb.toString();
        }
    }
    /**
     * 给app端返回图片
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/initImgModelsHandle")
    public @ResponseBody
    String initImgHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> result = new HashMap<String,Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> iList = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
        	try {
        		
        	    Map<String, Object> img = new HashMap<String, Object>();
	        	img.put("type", NewCateGoryCnstant.MODEL);  
        	    img.put("categoryModelId", pmap.get(NewCateGoryCnstant.ID).toString());
            	List<NewImg> imgList = newImgService.selectByEntity(img);
            	if(!CollectionUtil.isEmpty(imgList)){
            		iList.put("CategoryModelId", imgList.get(0).getCategoryModelId());
            		for(int i=0;i<imgList.size();i++){
            		  iList.put(NewCateGoryCnstant.IMG+i, imgList.get(i).getImgInfo());
            		}
            	}
            	
        	}catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(iList));
        }else{
        	logger.debug(JsonUtil.javaObjectToJsonString(result));
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }
    
    /**
     * 处理的新品类转换
     * @param pmap
     * @return
     */
    private static NewModel mapToNewModels(Map<String, Object> pmap) {
    	NewModel newModel = new NewModel();
    	if(pmap.get(NewCateGoryCnstant.ID) != null){
    		newModel.setId(pmap.get(NewCateGoryCnstant.ID).toString());
    	}
    	//处理意见 --是否引入
    	if(pmap.get(NewCateGoryCnstant.IS_IMPORT) != null){
    		newModel.setIsImport(pmap.get(NewCateGoryCnstant.IS_IMPORT).toString());
    	}
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
    	
    	//不引进原因编码
    	if(pmap.get(NewCateGoryCnstant.NOT_IMPORT_CODE) != null){
    		newModel.setNotImportCode(pmap.get(NewCateGoryCnstant.NOT_IMPORT_CODE).toString());
    	}
    	//不引进原因描述
    	if(pmap.get(NewCateGoryCnstant.NOT_IMPORT_DESC) != null){
    		newModel.setNotImportDesc(pmap.get(NewCateGoryCnstant.NOT_IMPORT_DESC).toString());
    	}
    	
    	//处理人公司编码  从 accesstoken中获得
    	if(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID) != null){
    		newModel.setHandleCompanyId(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());
    	}
    	//处理人编码
    	if(pmap.get(NewCateGoryCnstant.EMP_ID) != null){
    		newModel.setHandleEmployeeId(pmap.get(NewCateGoryCnstant.EMP_ID).toString());
    	}
    	
    	if(pmap.get(NewCateGoryCnstant.DEALER) != null){
    		newModel.setHandleEmployeeId(pmap.get(NewCateGoryCnstant.DEALER).toString());
    	}
    	//处理人姓名
    	if(pmap.get(NewCateGoryCnstant.EMPLOYEE_NAME) != null){
    		newModel.setHandleEmployeeName(pmap.get(NewCateGoryCnstant.EMPLOYEE_NAME).toString());
    	}
    	//处理时间
    	newModel.setHandleTime(new Date());
    	//引入时间
    	newModel.setImportTime(new Date());
        return newModel;
    }
}
