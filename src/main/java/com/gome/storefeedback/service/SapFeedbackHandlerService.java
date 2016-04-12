package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SAP商品缺断货处理 Service接口
 * 
 * @author 
 * @date 2015年07月23日 18时35分24秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapFeedbackHandlerService {

    /**
     * SAP商品缺断货处理--补货
     * @param sapFeedbackHandler
     * @throws BaseException
     */
    public void insertHandlerResultYes(List<SapFeedbackHandler> sapFeedbackHandlers ,List<String> orders,String goodsCode) throws BaseException;
    
    /**
     * SAP商品缺断货处理--不补货
     * @param sapFeedbackHandler
     * @throws BaseException
     */
    public void insertHandlerResultNo(List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException;
    
	/**
	 * 批量添加SAP商品缺断货处理
	 * @param sapFeedbackHandler
	 * @throws BaseException
	 */
	public void batchSapFeedbackHandler(List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException;
	
	/**
	 * 添加SAP商品缺断货处理
	 * @param sapFeedbackHandler
	 * @throws BaseException
	 */
	public int insertSapFeedbackHandler(SapFeedbackHandler sapFeedbackHandler) throws BaseException;
	/**
	 * 更新SAP商品缺断货处理
	 * @param sapFeedbackHandler
	 * @throws BaseException
	 */
	public void updateSapFeedbackHandler(SapFeedbackHandler sapFeedbackHandler) throws BaseException;
	/**
	 * 删除SAP商品缺断货处理
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteSapFeedbackHandler(Map<String, Object> inMap) throws BaseException;
	/**
	 * 通过Id查找SAP商品缺断货处理
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public SapFeedbackHandler findSapFeedbackHandlerById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 查找SAP商品缺断货处理列表
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List findSapFeedbackHandler(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询
	 * @param inMap
	 * @param param
	 * @return
	 * @throws BaseException
	 */
	public Page findSapFeedbackHandlerByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

	public int updateSapFeedbackToHr(List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException;
}
