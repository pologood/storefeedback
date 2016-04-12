package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 该接口只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月7日上午10:58:28
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface PersonDao {

	/**
	 * 添加人员
	 * @param person
	 * @throws BaseException
	 */
	public void insertPerson(Person person) throws BaseException;
	/**
	 * 更新人员
	 * @param person
	 * @throws BaseException
	 */
	public void updatePerson(Person person) throws BaseException;
	/**
	 * 删除人员
	 * @param inMap
	 * @throws BaseException
	 */
	public void deletePerson(Map<String, Object> inMap) throws BaseException;
	/**
	 * 通过Id查找人员
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public Person findPersonById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 查找人员列表
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List findPerson(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询
	 * @param inMap
	 * @param param
	 * @return
	 * @throws BaseException
	 */
	public Page findPersonByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
