package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.MQErrorInfo;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * 入库操作失败记录 Service接口
 * 
 * @author
 * @date 2015年04月01日 15时48分14秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface MQErrorInfoService {

    /**
     * 添加入库操作失败记录
     * 
     * @param mQErrorInfo
     * @throws BaseException
     */
    public void insertMQErrorInfo(MQErrorInfo mQErrorInfo) throws BaseException;

    /**
     * 删除入库操作失败记录
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteMQErrorInfo(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找入库操作失败记录
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public MQErrorInfo findMQErrorInfoById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找入库操作失败记录列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findMQErrorInfo(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findMQErrorInfoByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

}
