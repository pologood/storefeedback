package com.gome.storefeedback.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.service.PersonService;
import com.gome.storefeedback.util.JsonUtil;

/**
 * 该类只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月8日上午9:03:23
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/person")
public class PersonController {

	private PersonService personService;
	
	
	public PersonService getPersonService() {
		return personService;
	}

	@Autowired
	public void setPersonService(@Qualifier("personService")PersonService personService) {
		this.personService = personService;
	}


	@RequestMapping(value="/findPerson", produces="text/plain;charset=UTF-8")
	public @ResponseBody String findPersonById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("id", request.getParameter("pId"));
		System.out.println(request.getParameter("name"));
		Person p = this.personService.findPersonById(inMap);
		result.put("data", p);
		return JsonUtil.javaObjectToJsonString(result);
	}
}
