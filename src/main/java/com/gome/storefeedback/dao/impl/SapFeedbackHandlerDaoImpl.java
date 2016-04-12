package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackHandlerDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackHandlerDao实现
 * 
 * @author 
 * @date 2015年07月23日 18时35分24秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapFeedbackHandlerDao")
public class SapFeedbackHandlerDaoImpl extends BaseDaoImpl<SapFeedbackHandler, SapFeedbackHandler> implements SapFeedbackHandlerDao {

	@Override
	public void batchSapFeedbackHandler(List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException {
		try {
			this.insert("Mapper.SapFeedbackHandler.batch", sapFeedbackHandlers);
		} catch (Exception e) {
			throw new BaseException("batch sapFeedbackHandler error.", e);
		}
	}
	
	@Override
	public int insertSapFeedbackHandler(SapFeedbackHandler sapFeedbackHandler) throws BaseException {
		try {
			int insert = this.insert("Mapper.SapFeedbackHandler.insert", sapFeedbackHandler);
			return insert;
		} catch (Exception e) {
			throw new BaseException("insert sapFeedbackHandler error.", e);
		}
	}

	@Override
	public void updateSapFeedbackHandler(SapFeedbackHandler sapFeedbackHandler) throws BaseException {
		try {
			this.update("Mapper.SapFeedbackHandler.updateByPK", sapFeedbackHandler);
		} catch (Exception e) {
			throw new BaseException("update sapFeedbackHandler error.", e);
		}
	}

	@Override
	public void deleteSapFeedbackHandler(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.SapFeedbackHandler.deleteByPK", inMap);
		} catch (Exception e) {
			throw new BaseException("delete sapFeedbackHandler error.", e);
		}
	}

	@Override
	public SapFeedbackHandler findSapFeedbackHandlerById(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.SapFeedbackHandler.selectByPK", inMap);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackHandlerById error.", e);
		}
	}

	@Override
	public List findSapFeedbackHandler(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.SapFeedbackHandler.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackHandler error.", e);
		}
	}

	@Override
	public Page findSapFeedbackHandlerByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.SapFeedbackHandler.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackHandlerByPage error.", e);
		}
	}

	@Override
	public int updateSapFeedbackToHr(@Param("list") 
			List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException {
		try {
			return this.update("Mapper.SapFeedbackHandler.updateSapFeedbackToHr", sapFeedbackHandlers);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackHandler.updateSapFeedbackToHr error.", e);
		}
	}

}
