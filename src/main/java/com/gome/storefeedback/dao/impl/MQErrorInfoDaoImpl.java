package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.MQErrorInfoDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.MQErrorInfo;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * MQErrorInfoDao实现
 * 
 * @author
 * @date 2015年04月01日 15时48分14秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("mQErrorInfoDao")
public class MQErrorInfoDaoImpl extends BaseDaoImpl<MQErrorInfo, MQErrorInfo> implements MQErrorInfoDao {

    @Override
    public void insertMQErrorInfo(MQErrorInfo mQErrorInfo) throws BaseException {
        try {
            this.insert("Mapper.MQErrorInfo.insert", mQErrorInfo);
        } catch (Exception e) {
            throw new BaseException("insert mQErrorInfo error.", e);
        }
    }

    @Override
    public void deleteMQErrorInfo(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.MQErrorInfo.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete mQErrorInfo error.", e);
        }
    }

    @Override
    public MQErrorInfo findMQErrorInfoById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.MQErrorInfo.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findMQErrorInfoById error.", e);
        }
    }

    @Override
    public List findMQErrorInfo(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.MQErrorInfo.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findMQErrorInfo error.", e);
        }
    }

    @Override
    public Page findMQErrorInfoByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.MQErrorInfo.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findMQErrorInfoByPage error.", e);
        }
    }

}
