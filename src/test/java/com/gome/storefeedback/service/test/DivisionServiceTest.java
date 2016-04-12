package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.DivisionService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 测试用例
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午2:58:23
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class DivisionServiceTest extends
		AbstractTransactionalSpringContextTestCase {

	@Resource
	private DivisionService divisionService;

	/**
	 * 添加
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testInsertDivision() throws BaseException {
		Division div = new Division();
		div.setDivision_code("1");
		div.setDivision_desc("一级分部");
		div.setRegion_code("001");
		div.setUpdate_flag("1");
		divisionService.insertDivision(div);
	}
	
	/**
	 * 批量添加
	 * 
	 * @throws BaseException
	 */
	@Test
    @Rollback(false)
    public void testInsertBatchDivision() throws BaseException {
	    List<Division> divs = new ArrayList<Division>();
        Division div1 = new Division();
        div1.setDivision_code("8");
        div1.setDivision_desc("8");
        Division div2 = new Division();
        div2.setDivision_code("9");
        div2.setDivision_desc("9");
        divs.add(div1);
        divs.add(div2);
        divisionService.insertBatchDivision(divs);
    }
	/**
	 * 更新
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testUpdateDivision() throws BaseException {
		Division div = new Division();
		div.setDivision_code("1");
		div.setDivision_desc("一级分部更新");
		divisionService.updateDivision(div);
	}

	/**
	 * 删除
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testDeleteDivision() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("division_code", "1");
		divisionService.deleteDivision(inMap);

	}

	/**
	 * 主键查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindDivisionByCode() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("division_code", "1");
		Division findDivisionByCode = divisionService.findDivisionByCode(inMap);
		System.out.println(findDivisionByCode + "---------");

	}

	/**
	 * 参数查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindDivision() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("division_code", "1");
		List<Division> findDivision = divisionService.findDivision(inMap);
		System.out.println(findDivision);

	}

	/**
	 * 分页查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindDivisionByPage() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		Page<Division> byPage = divisionService
				.findDivisionByPage(inMap, param);
		System.out.println(byPage.getTotalPageSize() + "---------");

	}

}
