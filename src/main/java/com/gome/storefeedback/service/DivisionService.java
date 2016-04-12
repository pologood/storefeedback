package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * "一级分部" 业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:17:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface DivisionService {

	/**
	 * 添加一级分部
	 * 
	 * @param Division
	 * @throws BaseException
	 */
	public void insertDivision(Division division) throws BaseException;

	/**
	 * 更新一级分部
	 * 
	 * @param Division
	 * @throws BaseException
	 */
	public void updateDivision(Division division) throws BaseException;

	/**
	 * 删除一级分部
	 * 
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteDivision(Map<String, Object> inMap) throws BaseException;

	/**
	 * 通过一级分部编码查找一级分部
	 * 
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public Division findDivisionByCode(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 查找一级分部列表
	 * 
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List<Division> findDivision(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 分页查询
	 * 
	 * @param inMap
	 * @param param
	 * @return Page
	 * @throws BaseException
	 */
	public Page<Division> findDivisionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException;
	/**
	 * 批量插入分部
	 * @param parseXML2Division
	 */
    public void insertBatchDivision(List<Division> divisions) throws BaseException;
}
