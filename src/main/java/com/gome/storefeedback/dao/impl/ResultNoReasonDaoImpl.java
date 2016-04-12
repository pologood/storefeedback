package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.ResultNoReasonDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.ResultNoReason;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * ResultNoReasonDao实现
 * 
 * @author
 * @date 2015年07月22日 16时05分35秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("resultNoReasonDao")
public class ResultNoReasonDaoImpl extends BaseDaoImpl<ResultNoReason, ResultNoReason> implements ResultNoReasonDao {

    @Override
    public void batchResultNoReason(List<ResultNoReason> resultNoReasons) throws BaseException {
        try {
            this.insert("Mapper.ResultNoReason.batch", resultNoReasons);
        } catch (Exception e) {
            throw new BaseException("batch resultNoReason error.", e);
        }
    }

    @Override
    public void insertResultNoReason(ResultNoReason resultNoReason) throws BaseException {
        try {
            this.insert("Mapper.ResultNoReason.insert", resultNoReason);
        } catch (Exception e) {
            throw new BaseException("insert resultNoReason error.", e);
        }
    }

    @Override
    public void updateResultNoReason(ResultNoReason resultNoReason) throws BaseException {
        try {
            this.update("Mapper.ResultNoReason.updateByPK", resultNoReason);
        } catch (Exception e) {
            throw new BaseException("update resultNoReason error.", e);
        }
    }

    @Override
    public void deleteResultNoReason(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.ResultNoReason.deleteByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("delete resultNoReason error.", e);
        }
    }

    @Override
    public ResultNoReason findResultNoReasonById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.ResultNoReason.selectByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("findResultNoReasonById error.", e);
        }
    }

    @Override
    public List findResultNoReason(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.ResultNoReason.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findResultNoReason error.", e);
        }
    }

    @Override
    public Page findResultNoReasonByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.ResultNoReason.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findResultNoReasonByPage error.", e);
        }
    }

    @Override
    public List findCombo(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.ResultNoReason.interfacelist", inMap);
        } catch (Exception e) {
            throw new BaseException("findCombo error.", e);
        }
    }

}
