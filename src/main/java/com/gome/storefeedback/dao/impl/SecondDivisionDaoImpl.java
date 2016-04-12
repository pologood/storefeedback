package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.SecondDivisionDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SecondDivision;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 二级分部数据层接口实现类
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:35:35
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("secondDivisionDao")
public class SecondDivisionDaoImpl extends BaseDaoImpl<SecondDivision, SecondDivision> implements
		SecondDivisionDao {

	@Override
	public void insertSecondDivision(SecondDivision secondDivision) throws BaseException {
		try {
			this.insert("Mapper.SecondDivision.insert", secondDivision);
		} catch (Exception e) {
			throw new BaseException("insert SecondDivision error.", e);
		}
	}

	@Override
	public void updateSecondDivision(SecondDivision secondDivision) throws BaseException {
		try {
			this.update("Mapper.SecondDivision.update", secondDivision);
		} catch (Exception e) {
			throw new BaseException("update SecondDivision error.", e);
		}
	}

	@Override
	public void deleteSecondDivision(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.SecondDivision.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("delete SecondDivision error.", e);
		}
	}

	@Override
	public List<SecondDivision> findSecondDivision(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByParam("Mapper.SecondDivision.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findSecondDivision error.", e);
		}
	}

	@Override
	public Page<SecondDivision> findSecondDivisionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.SecondDivision.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findSecondDivisionByPage error.", e);
		}
	}

	@Override
	public SecondDivision findSecondDivisionByCode(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.SecondDivision.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findGoodsById error.", e);
		}
	}

    @Override
    public void insertBatchSecondDivision(List<SecondDivision> secondDivisions) throws BaseException {
        try {
            this.insert("Mapper.SecondDivision.batchInsert", secondDivisions);
        } catch (Exception e) {
            throw new BaseException("insertBatch secondDivisions error.", e);
        }
    }

}
