package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * "门店" 数据访问层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:17:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface StoreDao {

	/**
	 * 添加门店
	 * 
	 * @param Store
	 * @throws BaseException
	 */
	public void insertStore(Store store) throws BaseException;

	/**
	 * 更新门店
	 * 
	 * @param Store
	 * @throws BaseException
	 */
	public void updateStore(Store store) throws BaseException;

	/**
	 * 删除门店
	 * 
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteStore(Map<String, Object> inMap) throws BaseException;

	/**
	 * 通过门店编码查找门店
	 * 
	 * @param inMap
	 * @return Store
	 * @throws BaseException
	 */
	public Store findStoreByCode(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 查找门店列表
	 * 
	 * @param inMap
	 * @return list
	 * @throws BaseException
	 */
	public List<Store> findStore(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 分页查询
	 * 
	 * @param inMap
	 * @param param
	 * @return Page
	 * @throws BaseException
	 */
	public Page<Store> findStoreByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException;
	
	/**
     * 批量插入门店
     * @param stores
     * @throws BaseException
     */
    public void insertBatchStore(List<Store> stores) throws BaseException;
}
