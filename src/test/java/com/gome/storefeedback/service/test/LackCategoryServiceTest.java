package com.gome.storefeedback.service.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.Role;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.entity.LackCategory;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.service.LackCategoryService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午5:49:17
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class LackCategoryServiceTest extends AbstractTransactionalSpringContextTestCase{

	@Test
	@Rollback(false)
	public void insertLackCategoryTest() throws Exception{
		LackCategoryService lcs = (LackCategoryService) this.getBeanByName("lackCategoryService");
		LackCategory lc = new LackCategory();
		lc.setCategoryCode("1");
		lc.setCategoryName("电器");
		lcs.insertLackCategory(lc);
	}
	
	@Test
	public void findByIdTest() throws Exception{
		LackCategoryService ps = (LackCategoryService) this.getBeanByName("lackCategoryService");
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("categoryCode", "1");
		LackCategory lackCategory = ps.findLackCategoryById(inMap);
		System.out.println(lackCategory.getCategoryName());
	}
	
	@Test
	@Rollback(false)
	public void deleteLackCategoryTest() throws Exception{
		LackCategoryService ps = (LackCategoryService) this.getBeanByName("lackCategoryService");
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("categoryCode", "1");
		ps.deleteLackCategory(inMap);
	}
	
	@Test
	public void findLackCategoryTest() throws Exception{
		LackCategoryService ps = (LackCategoryService) this.getBeanByName("lackCategoryService");
		List<LackCategory> list = ps.findLackCategory(null);
		for(LackCategory p : list){
			System.out.println(p.getCategoryName());
		}
	}
	
	@Test
	public void findLackCategoryByPage() throws Exception{
		LackCategoryService ps = (LackCategoryService) this.getBeanByName("lackCategoryService");
		Page page = ps.findLackCategoryByPage(null, new PaginationParameters());
		System.out.println(page.getTotalSize());
		List<LackCategory> list = page.getDataList();
		for(LackCategory p : list){
			System.out.println(p.getCategoryName());
		}
	}
	@Test
	@Rollback(false)
    public void updateTest() throws Exception{
	    LackCategoryService lackCategoryService = (LackCategoryService) this.getBeanByName("lackCategoryService");
        Map<String,Object> inMap = new HashMap<String, Object>();
        inMap.put("categoryCode", "1");
        LackCategory lackCategory = lackCategoryService.findLackCategoryById(inMap);
        lackCategory.setCategoryName("yang");
        lackCategoryService.updateLackCategory(lackCategory);
    }
}
