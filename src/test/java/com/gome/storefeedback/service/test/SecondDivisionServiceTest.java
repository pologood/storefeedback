package com.gome.storefeedback.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.SecondDivision;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SecondDivisionService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 二级分部测试用例
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午3:15:24
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class SecondDivisionServiceTest extends
		AbstractTransactionalSpringContextTestCase {

	@Resource
	private SecondDivisionService service;

	/**
	 * 添加
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testInsertSecondDivision() throws BaseException {
		SecondDivision s = new SecondDivision();
		s.setFirst_division_code("1");
		s.setSecond_division_code("2");
		s.setSecond_division_des("二级分部");
		service.insertSecondDivision(s);
	}
	/**
	 * 批量添加
	 * @throws BaseException
	 */
	@Test
    @Rollback(false)
    public void testInsertSecondDivisions() throws BaseException {
	    List<SecondDivision> secondDivisions = new ArrayList<SecondDivision>();
        SecondDivision s1 = new SecondDivision();
        s1.setSecond_division_code("01");
        s1.setSecond_division_des("01");
        SecondDivision s2 = new SecondDivision();
        s2.setSecond_division_code("02");
        s2.setSecond_division_des("02");
        secondDivisions.add(s1);
        secondDivisions.add(s2);
        service.insertBatchSecondDivision(secondDivisions);
    }
	/**
	 * 更新
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testUpdateSecondDivision() throws BaseException {
		SecondDivision s = new SecondDivision();
		s.setFirst_division_code("1");
		s.setSecond_division_code("2");
		s.setSecond_division_des("二级分部更新");
		service.updateSecondDivision(s);
	}

	/**
	 * 删除
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testDeleteSecondDivision() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("division_code", "1");
		service.deleteSecondDivision(inMap);
	}

	/**
	 * 主键查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindSecondDivisionByCode() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("second_division_code", "2");
		SecondDivision code = service.findSecondDivisionByCode(inMap);
		System.out.println(code + "--------");
	}

	/**
	 * 查询多个
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindSecondDivision() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("first_division_code", "1");
		List<SecondDivision> division = service.findSecondDivision(inMap);
		System.out.println(division + "----");
	}

	/**
	 * 分页查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindSecondDivisionByPage() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		Page<SecondDivision> byPage = service.findSecondDivisionByPage(inMap,
				param);
		System.out.println(byPage.getTotalPageSize() + "----------");
		fail("Not yet implemented");
	}

}
