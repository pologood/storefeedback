package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.DivisionDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 一级分部数据层接口实现类
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:35:35
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("divisionDao")
public class DivisionDaoImpl extends BaseDaoImpl<Division, Division> implements
		DivisionDao {

	@Override
	public void insertDivision(Division division) throws BaseException {
		try {
			this.insert("Mapper.Division.insert", division);
		} catch (Exception e) {
			throw new BaseException("insert division error.", e);
		}
	}

	@Override
	public void updateDivision(Division division) throws BaseException {
		try {
			this.update("Mapper.Division.update", division);
		} catch (Exception e) {
			throw new BaseException("update division error.", e);
		}
	}

	@Override
	public void deleteDivision(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.Division.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("delete division error.", e);
		}
	}

	@Override
	public List<Division> findDivision(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByParam("Mapper.Division.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findDivision error.", e);
		}
	}

	@Override
	public Page<Division> findDivisionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.Division.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findDivisionByPage error.", e);
		}
	}

	@Override
	public Division findDivisionByCode(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.Division.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findGoodsById error.", e);
		}
	}

    @Override
    public void insertBatchDivision(List<Division> divisions) throws BaseException {
        try {
            this.insert("Mapper.Division.batchInsert", divisions);
        } catch (Exception e) {
            throw new BaseException("insertBatch divisions error.", e);
        }
    }

}
