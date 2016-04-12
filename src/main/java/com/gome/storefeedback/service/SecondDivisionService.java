package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.entity.SecondDivision;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 二级分部 业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:24:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SecondDivisionService {

	/**
	 * 添加二级分部
	 * 
	 * @param SecondDivision
	 * @throws BaseException
	 */
	public void insertSecondDivision(SecondDivision secondDivision)
			throws BaseException;

	/**
	 * 更新二级分部
	 * 
	 * @param SecondDivision
	 * @throws BaseException
	 */
	public void updateSecondDivision(SecondDivision secondDivision)
			throws BaseException;

	/**
	 * 删除二级分部
	 * 
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteSecondDivision(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 通过二级分部编码查找二级分部
	 * 
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public SecondDivision findSecondDivisionByCode(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 查找二级分部列表
	 * 
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List<SecondDivision> findSecondDivision(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 分页查询
	 * 
	 * @param inMap
	 * @param param
	 * @return Page
	 * @throws BaseException
	 */
	public Page<SecondDivision> findSecondDivisionByPage(
			Map<String, Object> inMap, PaginationParameters param)
			throws BaseException;
	/**
     * 批量插入二级分部
     * @param secondDivisions
     */
    public void insertBatchSecondDivision(List<SecondDivision> secondDivisions) throws BaseException;
}
