package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackCheckPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SAP缺断货信息关系Dao接口
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapFeedbackCheckPushDao {

    /**
     * 批量添加SAP缺断货信息关系
     * 
     * @param SapFeedbackCheckPush
     * @throws BaseException
     */
    public void batchSapFeedbackCheckPush(List<SapFeedbackCheckPush> SapFeedbackCheckPushs) throws BaseException;

    /**
     * 添加SAP缺断货信息关系
     * 
     * @param SapFeedbackCheckPush
     * @throws BaseException
     */
    public void insertSapFeedbackCheckPush(SapFeedbackCheckPush SapFeedbackCheckPush) throws BaseException;

    /**
     * 更新SAP缺断货信息关系
     * 
     * @param SapFeedbackCheckPush
     * @throws BaseException
     */
    public void updateSapFeedbackCheckPush(SapFeedbackCheckPush SapFeedbackCheckPush) throws BaseException;

    /**
     * 删除SAP缺断货信息关系
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteSapFeedbackCheckPush(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找SAP缺断货信息关系
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public SapFeedbackCheckPush findSapFeedbackCheckPushById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找SAP缺断货信息关系列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findSapFeedbackCheckPush(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findSapFeedbackCheckPushByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
