package com.gome.storefeedback.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SAP缺断货信息Dao接口
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface CheckSapFeedbackDao {

    /**
     * 批量添加SAP缺断货信息
     * 
     * @param sapFeedback
     * @throws BaseException
     */
    public void batchSapFeedback(List<SapFeedback> sapFeedbacks) throws BaseException;

    /**
     * 添加SAP缺断货信息
     * 
     * @param sapFeedback
     * @throws BaseException
     */
    public void insertSapFeedback(SapFeedback sapFeedback) throws BaseException;

    /**
     * 更新SAP缺断货信息
     * 
     * @param sapFeedback
     * @throws BaseException
     */
    public void updateSapFeedback(SapFeedback sapFeedback) throws BaseException;

    /**
     * 删除SAP缺断货信息
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteSapFeedback(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找SAP缺断货信息
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public SapFeedback findSapFeedbackById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找SAP缺断货信息列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findSapFeedback(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findSapFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 分页查询2
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findByParams(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException ;

    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException ;

	public List findBrandList(Map<String, Object> inMap) throws BaseException ;

	public void updateBatchSapFeedbackHisByAppeal(List<FeedbackAppeal> resultList) throws BaseException;

	public void updateBatchSapFeedbackHisByToOa(List<FeedbackToOa> resultList) throws BaseException;

	public void updateSapFeedbackHisByAppeal(FeedbackAppeal feedbackAppeal) throws BaseException;

	public void batchInsert(ArrayList<SapFeedbackCheckEmp> sapFeedbackCheckList) throws BaseException;

	public List<Map<String, Object>> getCheckByEmp(Map<String, Object> inMap) throws BaseException;

}
