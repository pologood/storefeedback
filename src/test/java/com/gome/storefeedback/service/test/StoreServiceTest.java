package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.StoreService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 门店 测试用例
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午3:24:02
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class StoreServiceTest extends
		AbstractTransactionalSpringContextTestCase {

	@Resource
	private StoreService service;

	/**
	 * 添加
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testInsertStore() throws BaseException {
		Store s = new Store();
		s.setDivision_code("1");
		s.setSecond_division_code("2");
		s.setStore_code("s1");// 门店编码
		s.setStore_name("门店1");
		service.insertStore(s);
	}
	/**
	 * 批量添加
	 * @throws BaseException
	 */
	@Test
    @Rollback(false)
    public void testInsertStores() throws BaseException {
	    List<Store> stores = new ArrayList<Store>();
        Store s1 = new Store();
        s1.setStore_code("1");
        s1.setStore_name("1");
        Store s2 = new Store();
        s2.setStore_code("2");
        s2.setStore_name("2");
        stores.add(s1);
        stores.add(s2);
        service.insertBatchStore(stores);
    }
	/**
	 * 更新
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testUpdateStore() throws BaseException {
		Store s = new Store();
		s.setDivision_code("1");
		s.setSecond_division_code("2");
		s.setStore_code("s1");// 门店编码
		s.setStore_name("门店更新");
		service.updateStore(s);
	}

	/**
	 * 删除
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testDeleteStore() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("store_code", "s1");
		service.deleteStore(inMap);

	}

	/**
	 * 主键查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindStoreByCode() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("store_code", "s1");
		Store findStoreByCode = service.findStoreByCode(inMap);
		System.out.println(findStoreByCode + "---------");
	}

	/**
	 * 查询多个
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindStore() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("first_division_code", "1");
		List<Store> store = service.findStore(inMap);
		System.out.println(store + "-------");
	}

	/**
	 * 分页查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindStoreByPage() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		Page<Store> page = service.findStoreByPage(inMap, param);
		System.out.println(page.getTotalPageSize() + "-----");
	}

}
