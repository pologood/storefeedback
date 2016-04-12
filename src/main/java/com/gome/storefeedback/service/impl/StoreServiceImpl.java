package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.StoreDao;
import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.StoreService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 分部业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午2:20:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

	private StoreDao storeDao;

	public StoreDao getStoreDao() {
		return storeDao;
	}

	@Autowired
	public void setStoreDao(@Qualifier("storeDao") StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public void insertStore(Store store) throws BaseException {
		this.storeDao.insertStore(store);
	}

	@Override
	public void updateStore(Store store) throws BaseException {
		this.storeDao.updateStore(store);
	}

	@Override
	public void deleteStore(Map<String, Object> inMap) throws BaseException {
		this.storeDao.deleteStore(inMap);
	}

	@Override
	public Store findStoreByCode(Map<String, Object> inMap)
			throws BaseException {
		return this.storeDao.findStoreByCode(inMap);
	}

	@Override
	public List<Store> findStore(Map<String, Object> inMap)
			throws BaseException {
		return this.storeDao.findStore(inMap);
	}

	@Override
	public Page<Store> findStoreByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.storeDao.findStoreByPage(inMap, param);
	}

    @Override
    public void insertBatchStore(List<Store> stores) throws BaseException {
        for (Store store : stores) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("store_code", store.getStore_code());
            Store oldStore = this.findStoreByCode(inMap);
            if (null == oldStore) {
                // Insert
                store.setUpdate_flag("C");
                this.insertStore(store);
            } else {
                // Delete
                if (store.getUpdate_flag() != null && "D".equals(store.getUpdate_flag())) {
                    this.deleteStore(inMap);
                } else {
                    // Update
                    store.setUpdate_flag("M");
                    this.updateStore(store);
                }
            }
        }
        }

}
