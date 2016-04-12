package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.PersonDao;
import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.PersonService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 该类只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月7日上午11:20:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

	private PersonDao personDao;
	
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	@Autowired
	public void setPersonDao(@Qualifier("personDao")PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public void insertPerson(Person person) throws BaseException {
		this.personDao.insertPerson(person);
	}

	@Override
	public void updatePerson(Person person) throws BaseException {
		this.personDao.updatePerson(person);
	}

	@Override
	public void deletePerson(Map<String, Object> inMap) throws BaseException {
		this.personDao.deletePerson(inMap);
	}

	@Override
	public Person findPersonById(Map<String, Object> inMap)
			throws BaseException {
		return this.personDao.findPersonById(inMap);
	}

	@Override
	public List findPerson(Map<String, Object> inMap) throws BaseException {
		return this.personDao.findPerson(inMap);
	}

	@Override
	public Page findPersonByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.personDao.findPersonByPage(inMap, param);
	}

}
