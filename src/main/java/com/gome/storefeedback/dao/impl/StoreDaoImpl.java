package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.StoreDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 门店数据层接口实现类
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:35:35
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<Store, Store> implements
		StoreDao {

	@Override
	public void insertStore(Store store) throws BaseException {
		try {
			this.insert("Mapper.Store.insert", store);
		} catch (Exception e) {
			throw new BaseException("insert Store error.", e);
		}
	}

	@Override
	public void updateStore(Store store) throws BaseException {
		try {
			this.update("Mapper.Store.update", store);
		} catch (Exception e) {
			throw new BaseException("update Store error.", e);
		}
	}

	@Override
	public void deleteStore(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.Store.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("delete Store error.", e);
		}
	}

	@Override
	public List<Store> findStore(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByParam("Mapper.Store.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findStore error.", e);
		}
	}

	@Override
	public Page<Store> findStoreByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.Store.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findStoreByPage error.", e);
		}
	}

	@Override
	public Store findStoreByCode(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.Store.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findGoodsById error.", e);
		}
	}

    @Override
    public void insertBatchStore(List<Store> stores) throws BaseException {
        try {
            this.insert("Mapper.Store.batchInsert", stores);
        } catch (Exception e) {
            throw new BaseException("insertBatch stores error.", e);
        }
    }

}
