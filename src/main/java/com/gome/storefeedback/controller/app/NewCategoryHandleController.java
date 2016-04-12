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

import com.gome.gsm.entity.org.Employee;
import com.gome.gsm.entity.org.Position;
import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.constant.NewCateGoryCnstant;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.LackDivision;
import com.gome.storefeedback.entity.NewCategory;
import com.gome.storefeedback.entity.NewCategoryStatus;
import com.gome.storefeedback.entity.NewImg;
import com.gome.storefeedback.entity.NewModel;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.CategoryPositionService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.service.LackDivisionService;
import com.gome.storefeedback.service.NewCategoryService;
import com.gome.storefeedback.service.NewCategoryStatusService;
import com.gome.storefeedback.service.NewImgService;
import com.gome.storefeedback.service.NewModelService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.CollectionUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;



@Controller
@RequestMapping("/newCategoryHandle")
public class NewCategoryHandleController {
	private static final Logger logger = LoggerFactory.getLogger(NewCategoryHandleController.class);
	@Resource 
	private NewCategoryStatusService newCategoryStatusService;
	@Resource
	private LogMessageSender logMessageSender;
	@Resource
    private NewCategoryService newCategoryService;
	@Resource
	private NewImgService newImgService;
	@Resource
    private LackDivisionService lackDivisionService;
	@Resource
    private NewModelService newModelService;
	@Resource
	private EmployeeRemoteService employeeRemoteService;
	@Resource
    private CategoryPositionService categoryPositionService;
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
    @RequestMapping(value = "/initNewCategoryHandle")
    public @ResponseBody
    String initNewCategoryHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> result = new HashMap<String,Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        JSONObject jsonObg = new JSONObject();
        if (AppUtil.requestHandler(request, pmap, result)) {
        	try {
        		Employee emp =employeeRemoteService.findEmployeeById(pmap.get("storeFeedbackUserId").toString());
            	if(emp != null){
            		List<Position> positions = emp.getPositions();
            			for(Position pos :positions){
            				String comp = pos.getPositionCode().substring(0, 4);
            				pmap.put(NewCateGoryCnstant.REPORT_COMPANY_ID, comp);
            		}
            	}
        		//组装不补货原因 返回给app端
            	Map<String, Object> obj = new HashMap<String,Object>();
            	obj.put("tableName", NewCateGoryCnstant.NEW_CATEGORY);
            	obj.put("columnName", NewCateGoryCnstant.IMPORT_DESC);
            	obj.put("sts", NewCateGoryCnstant.STS_A);
            	List<NewCategoryStatus> statusList  = newCategoryStatusService.selectByEntity(obj);
            	JSONArray jsonArraysts = new JSONArray();
            	if(!CollectionUtil.isEmpty(statusList)){
            		for(NewCategoryStatus statu : statusList){
            			JSONObject jsonObject = new JSONObject();
                    	jsonObject.put(NewCateGoryCnstant.NOT_IMPORT_CODE, statu.getId());
                    	jsonObject.put(NewCateGoryCnstant.NOT_IMPORT_DESC, statu.getReasonDesc());
                    	jsonArraysts.add(jsonObject);
            		}
            	}
            	//组装提报信息
            	JSONArray jsonArrays = new JSONArray();
            	if(this.valBoos(pmap)){ ///当前等录入如果是业务总监返回--新品类提报
            		Map<String, Object> cate = new HashMap<String,Object>();
                	cate.put("reportCompanyId", pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());
                	cate.put("isHandle", NewCateGoryCnstant.IS_HANDLE_Y);
                	//cate.put("categoryCode", NewCateGoryCnstant.CATEGORY_CODE);
                	List <NewCategory> cateList  = newCategoryService.selectList(cate);
            		if(!CollectionUtil.isEmpty(cateList)){
                		for(NewCategory gory : cateList){
                			JSONObject jsonObgoryt = new JSONObject();
                			jsonObgoryt.put(NewCateGoryCnstant.ID, gory.getId());
                			jsonObgoryt.put(NewCateGoryCnstant.EMP_ID, gory.getReportEmployeeId());
                			jsonObgoryt.put(NewCateGoryCnstant.REPORT_EMP_NAME, gory.getReportEmployeeName());
                			jsonObgoryt.put(NewCateGoryCnstant.CATEGORY_CODE, gory.getCategoryCode());
                			jsonObgoryt.put(NewCateGoryCnstant.CATEGPRY_DESC, gory.getCategoryDesc());
                			jsonObgoryt.put(NewCateGoryCnstant.MODEL_CODE, gory.getModelCode());
                			jsonObgoryt.put(NewCateGoryCnstant.MODEL_DESC, gory.getModelDesc());
                			jsonObgoryt.put(NewCateGoryCnstant.DEALER, gory.getDealer());
                			jsonObgoryt.put(NewCateGoryCnstant.MAIN_DESC, gory.getMainDesc());
                			jsonObgoryt.put(NewCateGoryCnstant.CATEGORY_OR_MODEL, NewCateGoryCnstant.CATE_GORY);
                			if(StringUtils.equals(gory.getIsHandle(), NewCateGoryCnstant.IS_HANDLE_M)){
                				jsonObgoryt.put(NewCateGoryCnstant.SHOW_COLOR, "1");
                			}else{
                				jsonObgoryt.put(NewCateGoryCnstant.SHOW_COLOR, "0");
                			}
                			jsonArrays.add(jsonObgoryt);
                		}
                	}
            	}
            	if(valseBoos(pmap)){ ///当前等录入如果是业务主管返回--新新型号
            		Map<String, Object> catemodel = new HashMap<String,Object>();
            		catemodel.put("reportCompanyId", pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());
            		catemodel.put("isHandle", NewCateGoryCnstant.IS_HANDLE_Y);
            		catemodel.put("categoryCode", pmap.get(NewCateGoryCnstant.CATEGORY_CODE));
            		List<NewModel> modelList  = newModelService.selectList(catemodel);
            		//组装提报信息
            		if(!CollectionUtil.isEmpty(modelList)){
            			for(NewModel mod : modelList){
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
            				jsonObgoryt.put(NewCateGoryCnstant.CATEGORY_OR_MODEL, NewCateGoryCnstant.MODEL);
            				if(StringUtils.equals(mod.getIsHandle(), NewCateGoryCnstant.IS_HANDLE_M)){
                				jsonObgoryt.put(NewCateGoryCnstant.SHOW_COLOR, "1");
                			}else{
                				jsonObgoryt.put(NewCateGoryCnstant.SHOW_COLOR, "0");
                			}
            				jsonArrays.add(jsonObgoryt);
            			}
            		}
            	}
            	
            	if(!valBoos(pmap) && !valseBoos(pmap)){
            		 result.put(APPErrorInfo.ERRORCODE, "11044");
              	     result.put(APPErrorInfo.ERRORMSG, "对不起，您没有权限处理提报信息！");
              	     return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            	}
            	jsonObg.put("reasons", jsonArraysts);
            	jsonObg.put("newCategorys", jsonArrays);
            	
            	logger.debug(/*JsonUtil.javaObjectToJsonString(jsonObg)*/jsonObg.toString());
        	}catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        	return GzipAESUtil.compressThenEncryptAES(/*JsonUtil.javaObjectToJsonString(mapresult)*/jsonObg.toString());
        }else{
        	logger.debug(JsonUtil.javaObjectToJsonString(result));
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }
    /**
     * 判断是否是业务总监
     * @param pmap
     * @return
     * @throws BaseException
     */
    public boolean valBoos( Map<String, Object> pmap) throws BaseException{
    	Employee emp =employeeRemoteService.findEmployeeById(pmap.get("storeFeedbackUserId").toString());
    	if(emp != null){
    		List<Position> positions = emp.getPositions();
    		Map<String, Object> epmap = new HashMap<String, Object>();
    		String code = NewCateGoryCnstant.CATEGORY_CODE_000000;
    		epmap.put("categoryCode", code);
    		List<CategoryPosition> list   = categoryPositionService.findCategoryPositionAll(epmap);
    		if(list != null && list.size() > 0){
    			for(CategoryPosition position : list){
    				for(Position pos :positions){
    					if(pos.getPositionCode() != null){
    						pmap.put("reportCompanyId",pos.getPositionCode().substring(0, 4));
    			    	}
    					if(StringUtils.equals(pos.getMinistrationId(), position.getPositionCode())){
    						return true;
    					}else{
    						return false;
    					}
    				}
    			}
    		}
    	}
		return false;
    	
    }
    /**
     * 判断是否是业务主管
     * @return
     */
    public boolean valseBoos(Map<String, Object> pmap) throws BaseException{
    	Employee emp =employeeRemoteService.findEmployeeById(pmap.get("storeFeedbackUserId").toString());
		if (emp != null) {
			List<Position> positions = emp.getPositions();
			Map<String, Object> epmap = new HashMap<String, Object>();
			// String code = NewCateGoryCnstant.CATEGORY_CODE_000000;
			for (Position pos : positions) {
				if (pos.getPositionCode() != null) {
					pmap.put("reportCompanyId", pos.getPositionCode().substring(0, 4));
				}
				epmap.put("positionCode", pos.getMinistrationId());
				List<CategoryPosition> list = categoryPositionService.findCategoryPositionNotBoos(epmap);
				String categoryCode = "";
				if (list != null && list.size() > 0) {
					for (CategoryPosition position : list) {
						categoryCode  =position.getCategoryCode()  +","+ categoryCode;
					}
					pmap.put(NewCateGoryCnstant.CATEGORY_CODE, categoryCode.substring(0, categoryCode.length()-1));
				}
				if (pmap.get(NewCateGoryCnstant.CATEGORY_CODE) != null) {
					return true;
				} else {
					return false;
				}
				
			}
		}
    	return false;
    }
    /**
     * 给app端返回图片
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/initImgHandle")
    public @ResponseBody
    String initImgHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> result = new HashMap<String,Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> iList = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
        	if(StringUtils.equals(pmap.get(NewCateGoryCnstant.CATEGORY_OR_MODEL).toString(), NewCateGoryCnstant.CATE_GORY)){
        		try {
        			Map<String, Object> img = new HashMap<String, Object>();
        			img.put("type", NewCateGoryCnstant.CATE_GORY);  
        			img.put("categoryModelId", pmap.get(NewCateGoryCnstant.ID).toString());
        			List<NewImg> imgList = newImgService.selectByEntity(img);
        			if(!CollectionUtil.isEmpty(imgList)){
        				
        				iList.put("CategoryModelId", imgList.get(0).getCategoryModelId());
        				/*for(int i=0;i<imgList.size();i++){
                  		  iList.put(NewCateGoryCnstant.IMG+i+1, imgList.get(i).getImgInfo());
                  		}*/
        				iList.put(NewCateGoryCnstant.IMG_3, imgList.get(2).getImgInfo());
        				iList.put(NewCateGoryCnstant.IMG_2, imgList.get(1).getImgInfo());
        				iList.put(NewCateGoryCnstant.IMG_1, imgList.get(0).getImgInfo());
        			}
        		}catch (BaseException e) {
        			logger.error(e.getMessage(), e);
        			AppUtil.exceptionHandler(result);
        		}
			} else if (StringUtils.equals(pmap.get(NewCateGoryCnstant.CATEGORY_OR_MODEL).toString(),NewCateGoryCnstant.MODEL)) {
				try {

					Map<String, Object> img = new HashMap<String, Object>();
					img.put("type", NewCateGoryCnstant.MODEL);
					img.put("categoryModelId", pmap.get(NewCateGoryCnstant.ID).toString());
					List<NewImg> imgList = newImgService.selectByEntity(img);
					if (!CollectionUtil.isEmpty(imgList)) {
						iList.put("CategoryModelId", imgList.get(0).getCategoryModelId());
						/*for(int i=0;i<imgList.size();i++){
							int ss = i+1;
		            		 iList.put(NewCateGoryCnstant.IMG+ss, imgList.get(i).getImgInfo());
		            	}*/
						iList.put(NewCateGoryCnstant.IMG_3, imgList.get(2).getImgInfo());
						iList.put(NewCateGoryCnstant.IMG_2, imgList.get(1).getImgInfo());
						iList.put(NewCateGoryCnstant.IMG_1, imgList.get(0).getImgInfo());
					}

				} catch (BaseException e) {
					logger.error(e.getMessage(), e);
					AppUtil.exceptionHandler(result);
				}
			}else{
        	  logger.debug(JsonUtil.javaObjectToJsonString(result));
        	        	
        	}
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(iList));
        }else{
        	logger.debug(JsonUtil.javaObjectToJsonString(result));
        	return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }
    /**
     * 处理新品类提报
     * @param request11034
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/subNewCategoryHandle")
    public @ResponseBody String subNewCategoryHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 Map<String, Object> result = new HashMap<String, Object>();
         Map<String, Object> pmap = new HashMap<String, Object>();
         if (AppUtil.requestHandler(request, pmap, result)) {
        	Employee emp =employeeRemoteService.findEmployeeById(pmap.get("storeFeedbackUserId").toString());
            	if(emp != null){
            		List<Position> positions = emp.getPositions();
            			for(Position pos :positions){
            				String comp = pos.getPositionCode().substring(0, 4);
            				pmap.put(NewCateGoryCnstant.REPORT_COMPANY_ID, comp);
            		}
            	}
        	 if(StringUtils.equals(pmap.get(NewCateGoryCnstant.CATEGORY_OR_MODEL).toString(), NewCateGoryCnstant.CATE_GORY)){
        		 try {
                  	NewCategory newCategoryHandler = NewCategoryHandleController.mapToNewCategory(pmap);
                  	if(StringUtils.isNotBlank(newCategoryHandler.getModelCode())){ //已经输入品类编码 处理完成
                  		newCategoryHandler.setIsHandle(NewCateGoryCnstant.IS_HANDLE_Y);
                  	}else{
                  		newCategoryHandler.setIsHandle(NewCateGoryCnstant.IS_HANDLE_M);	//已处理但是没有输入品类编码  还需处理一次
                  	}
                  	if(StringUtils.equals(newCategoryHandler.getIsImport(), NewCateGoryCnstant.IS_IMPORT_N)){
                  		newCategoryHandler.setIsHandle(NewCateGoryCnstant.IS_HANDLE_Y);
                  	}
                  	if(StringUtils.equals(newCategoryHandler.getIsImport(), NewCateGoryCnstant.IS_IMPORT_N) && StringUtils.isBlank(newCategoryHandler.getNotImportDesc())){
               		  result.put(APPErrorInfo.ERRORCODE, "11034");
               	      result.put(APPErrorInfo.ERRORMSG, "请填写不引进原因号");
               	      return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
                  	}
                  	LackDivision division  = new LackDivision();
                  	division.setDivisionCode(newCategoryHandler.getCategoryCode());
                  	Map<String, Object> inMap = new HashMap<String, Object>();
                	if(StringUtils.isNotBlank(newCategoryHandler.getModelCode())){
                		String cond = "";
                		cond = goodCode(newCategoryHandler.getModelCode());
                		inMap.put("goodsCode", cond);
                		Goods goods = goodsService.findGoodsById(inMap);
                		
                		//校验新品类是否存在
                		if(StringUtils.equals(newCategoryHandler.getIsImport(), NewCateGoryCnstant.IS_IMPORT_Y) &&  goods == null){
                			result.put(APPErrorInfo.ERRORCODE, "11032");
                			result.put(APPErrorInfo.ERRORMSG, "商品编码不存在，请核查后重新输入");
                			return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
                		}
                	}
                  	//lackDivisionService.checkNewCategory(division);
     	             newCategoryService.updateByPrimaryKeySelective(newCategoryHandler);//更新处理信息
     	             if(StringUtils.isNotBlank(newCategoryHandler.getCategoryCode()) && StringUtils.equals(newCategoryHandler.getIsImport(), NewCateGoryCnstant.IS_HANDLE_Y)){
     	            	 division.setCategoryCode("1");
     	            	 division.setCategoryName("门店");
     	            	 division.setDivisionCode(newCategoryHandler.getCategoryCode());
     	            	 division.setDivisionName(newCategoryHandler.getCategoryDesc());
     	            	 lackDivisionService.insert(division);
     	             }
     	                
     	             AppUtil.succussHandler(result);
     	             logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
     	                     JsonUtil.javaObjectToJsonString(result)));
                  } catch (BaseException e) {
                      logger.error(e.getMessage(), e);
                      AppUtil.exceptionHandler(result);
                  }
              }else if(StringUtils.equals(pmap.get(NewCateGoryCnstant.CATEGORY_OR_MODEL).toString(), NewCateGoryCnstant.MODEL)){
                      try {
                      	NewModel mewModel = NewCategoryHandleController.mapToNewModels(pmap);
                      	
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
         	          	      result.put(APPErrorInfo.ERRORMSG, "商品编码不存在，请核查后重新输入");
         	          	      return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
         	             	}
         	             }
         	             	
                      	if(StringUtils.isBlank(mewModel.getModelCode())){
                      		mewModel.setIsHandle(NewCateGoryCnstant.IS_HANDLE_M);
                      	}else{
                      		mewModel.setIsHandle(NewCateGoryCnstant.IS_HANDLE_Y);
                      	}
                      	
                      	if(StringUtils.equals(mewModel.getIsImport(), NewCateGoryCnstant.IS_IMPORT_N)){
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
                  logger.debug(JsonUtil.javaObjectToJsonString(result));
                  return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
             }
              logger.debug(JsonUtil.javaObjectToJsonString(result));
              return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result)); 
        	 } else{
        		 result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E10001);
          	      result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M10001);
          	      return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
         }
            
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
          int count = 18- goodCode.length();
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
     * 处理的新品类转换
     * @param pmap
     * @return
     */
    private static NewCategory mapToNewCategory(Map<String, Object> pmap) {
    	NewCategory newCategory = new NewCategory();
    	if(pmap.get(NewCateGoryCnstant.ID) != null){
    		newCategory.setId(pmap.get(NewCateGoryCnstant.ID).toString());
    	}
    	//处理意见 --是否引入
    	if(pmap.get(NewCateGoryCnstant.IS_IMPORT) != null){
    		newCategory.setIsImport(pmap.get(NewCateGoryCnstant.IS_IMPORT).toString());
    	}
    	//提报新品类---品类编码不填
    	if(pmap.get(NewCateGoryCnstant.CATEGORY_CODE) != null){
    		newCategory.setCategoryCode(pmap.get(NewCateGoryCnstant.CATEGORY_CODE).toString());
    	}
    	//品类描述--提报一般填写描述
    	if(pmap.get(NewCateGoryCnstant.CATEGPRY_DESC) != null){
    		newCategory.setCategoryDesc(pmap.get(NewCateGoryCnstant.CATEGPRY_DESC).toString());
    	}
    	//提报--新型号编码可以不写
    	if(pmap.get(NewCateGoryCnstant.ITME_CODE) != null){
    		newCategory.setModelCode(pmap.get(NewCateGoryCnstant.ITME_CODE).toString());
    	}
    	//提报品类主要描述
    	if(pmap.get(NewCateGoryCnstant.MAIN_DESC) != null){
    		newCategory.setMainDesc(pmap.get(NewCateGoryCnstant.MAIN_DESC).toString());
    	}
    	//不引进原因编码
    	if(pmap.get(NewCateGoryCnstant.NOT_IMPORT_CODE) != null){
    		newCategory.setNotImportCode(pmap.get(NewCateGoryCnstant.NOT_IMPORT_CODE).toString());
    	}
    	//不引进原因描述
    	if(pmap.get(NewCateGoryCnstant.NOT_IMPORT_DESC) != null){
    		newCategory.setNotImportDesc(pmap.get(NewCateGoryCnstant.NOT_IMPORT_DESC).toString());
    	}
    	
    	//提报人公司编码  从 accesstoken中获得
    	if(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID) != null){
    		newCategory.setHandleCompanyId(pmap.get(NewCateGoryCnstant.REPORT_COMPANY_ID).toString());
    	}
    	//提报人编码
    	if(pmap.get(NewCateGoryCnstant.EMP_ID) != null){
    		newCategory.setHandleEmployeeId(pmap.get(NewCateGoryCnstant.EMP_ID).toString());
    	}
    	//处理人姓名
    	if(pmap.get(NewCateGoryCnstant.EMPLOYEE_NAME) != null){
    		newCategory.setHandleEmployeeName(pmap.get(NewCateGoryCnstant.EMPLOYEE_NAME).toString());
    	}
    	//处理时间
    	newCategory.setHandleTime(new Date());
    	//引入时间 
    	newCategory.setImportTime(new Date());
        return newCategory;
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
    	if(pmap.get(NewCateGoryCnstant.ITME_CODE) != null){
    		newModel.setModelCode(pmap.get(NewCateGoryCnstant.ITME_CODE).toString());
    	}
    	if(pmap.get(NewCateGoryCnstant.MODEL_DESC) != null){
    		newModel.setModelDesc(pmap.get(NewCateGoryCnstant.MODEL_DESC).toString());
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
