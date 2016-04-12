package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.LackCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午5:41:30
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface LackCategoryDao {

	/**
	 * 添加缺货类别
	 * @param lackCategory
	 * @throws BaseException
	 */
	public void insertLackCategory(LackCategory lackCategory) throws BaseException;
	/**
	 * 更新缺货类别
	 * @param lackCategory
	 * @throws BaseException
	 */
	public void updateLackCategory(LackCategory lackCategory) throws BaseException;
	/**
	 * 删除缺货类别
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteLackCategory(Map<String, Object> inMap) throws BaseException;
	/**
	 * 通过Id查找缺货类别
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public LackCategory findLackCategoryById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 查找缺货类别列表
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List findLackCategory(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询
	 * @param inMap
	 * @param param
	 * @return
	 * @throws BaseException
	 */
	public Page findLackCategoryByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
