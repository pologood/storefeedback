package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SAP缺断货信息关系 Service接口
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapFeedbackPushService {

    /**
     * 批量添加SAP缺断货信息关系
     * 
     * @param sapFeedbackPush
     * @throws BaseException
     */
    public void batchSapFeedbackPush(List<SapFeedbackPush> sapFeedbackPushs) throws BaseException;

    /**
     * 添加SAP缺断货信息关系
     * 
     * @param sapFeedbackPush
     * @throws BaseException
     */
    public void insertSapFeedbackPush(SapFeedbackPush sapFeedbackPush) throws BaseException;

    /**
     * 更新SAP缺断货信息关系
     * 
     * @param sapFeedbackPush
     * @throws BaseException
     */
    public void updateSapFeedbackPush(SapFeedbackPush sapFeedbackPush) throws BaseException;

    /**
     * 删除SAP缺断货信息关系
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteSapFeedbackPush(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找SAP缺断货信息关系
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public SapFeedbackPush findSapFeedbackPushById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找SAP缺断货信息关系列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findSapFeedbackPush(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findSapFeedbackPushByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

}
