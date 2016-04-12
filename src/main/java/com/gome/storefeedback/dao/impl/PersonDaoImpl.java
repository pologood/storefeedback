package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.PersonDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 该类只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月7日上午11:17:13
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person, Person> implements PersonDao {

	@Override
	public void insertPerson(Person person) throws BaseException {
		try {
			this.insert("Mapper.Person.insert", person);
		} catch (Exception e) {
			throw new BaseException("insert person error.", e);
		}
	}

	@Override
	public void updatePerson(Person person) throws BaseException {
		try {
			this.update("Mapper.Person.update", person);
		} catch (Exception e) {
			throw new BaseException("update person error.", e);
		}
	}

	@Override
	public void deletePerson(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.Person.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("delete person error.", e);
		}
	}

	@Override
	public Person findPersonById(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.Person.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findPersonById error.", e);
		}
	}

	@Override
	public List findPerson(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.Person.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findPerson error.", e);
		}
	}

	@Override
	public Page findPersonByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.Person.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findPersonByPage error.", e);
		}
	}

}
