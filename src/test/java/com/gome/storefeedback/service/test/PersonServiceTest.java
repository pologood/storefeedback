package com.gome.storefeedback.service.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.service.PersonService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 该类只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月7日上午11:26:11
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class PersonServiceTest extends AbstractTransactionalSpringContextTestCase{

	@Test
	@Rollback(false)
	public void insertPersonTest() throws Exception{
		PersonService ps = (PersonService) this.getBeanByName("personService");
		Person p = new Person();
		p.setAddress("北京市海淀区");
		p.setAge(20);
		p.setCreateTime(new Date());
		p.setName("王五");
		ps.insertPerson(p);
	}
	
	@Test
	public void findByIdTest() throws Exception{
		PersonService ps = (PersonService) this.getBeanByName("personService");
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("id", "6");
		Person person = ps.findPersonById(inMap);
		System.out.println(person.getName());
	}
	
	@Test
	public void deletePersonTest() throws Exception{
		PersonService ps = (PersonService) this.getBeanByName("personService");
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("id", "6");
		ps.deletePerson(inMap);
	}
	
	@Test
	public void findPersonTest() throws Exception{
		PersonService ps = (PersonService) this.getBeanByName("personService");
		List<Person> list = ps.findPerson(null);
		for(Person p : list){
			System.out.println(p.getName());
		}
	}
	
	@Test
	public void findPersonByPage() throws Exception{
		PersonService ps = (PersonService) this.getBeanByName("personService");
		Page page = ps.findPersonByPage(null, new PaginationParameters());
		System.out.println(page.getTotalSize());
		List<Person> list = page.getDataList();
		for(Person p : list){
			System.out.println(p.getName());
		}
	}
	
}
