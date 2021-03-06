package com.gome.storefeedback.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.entity.SapFeedbackDicai;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SAP缺断货信息 Service接口
 * 
 * @author
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapFeedbackService {

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

    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException;

    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException;

    /**
     * 把BW推送的缺断货数据关联empNumber之后插入到zmm_ds62_toHr表，每月推送数据给HR的数据来源
     * @param currentDateTime
     * @throws BaseException
     */
	public int insertIntoHr(List<SapFeedbackCheckEmp> list) throws BaseException;

}
