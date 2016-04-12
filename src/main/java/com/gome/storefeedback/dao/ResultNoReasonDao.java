package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.ResultNoReason;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * 不补货进原因Dao接口
 * 
 * @author
 * @date 2015年07月22日 16时05分35秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface ResultNoReasonDao {

    /**
     * 批量添加不补货进原因
     * 
     * @param resultNoReason
     * @throws BaseException
     */
    public void batchResultNoReason(List<ResultNoReason> resultNoReasons) throws BaseException;

    /**
     * 添加不补货进原因
     * 
     * @param resultNoReason
     * @throws BaseException
     */
    public void insertResultNoReason(ResultNoReason resultNoReason) throws BaseException;

    /**
     * 更新不补货进原因
     * 
     * @param resultNoReason
     * @throws BaseException
     */
    public void updateResultNoReason(ResultNoReason resultNoReason) throws BaseException;

    /**
     * 删除不补货进原因
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteResultNoReason(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找不补货进原因
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public ResultNoReason findResultNoReasonById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找不补货进原因列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findResultNoReason(Map<String, Object> inMap) throws BaseException;

    /**
     * 接口 查找不补货进原因列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findCombo(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findResultNoReasonByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
