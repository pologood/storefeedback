package com.gome.storefeedback.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.RegionService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 大区测试用例
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午3:04:39
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class RegionServiceTest extends
		AbstractTransactionalSpringContextTestCase {

	@Resource
	private RegionService regionService;

	/**
	 * 添加
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testInsertRegion() throws BaseException {
		Region region = new Region();
		region.setRegion_code("001");
		region.setRegion_des("大区001");
		region.setUpdate_flag("1");
		regionService.insertRegion(region);
	}
	/**
	 * 批量添加
	 * @throws BaseException
	 */
	@Test
    @Rollback(false)
    public void testInsertRegions() throws BaseException {
	    List<Region> regions = new ArrayList<Region>();
        Region region1 = new Region();
        region1.setRegion_code("1");
        region1.setRegion_des("1");
        Region region2 = new Region();
        region2.setRegion_code("2");
        region2.setRegion_des("2");
        regions.add(region1);
        regions.add(region2);
        regionService.insertBatchRegion(regions);
    }
	/**
	 * 更新
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testUpdateRegion() throws BaseException {
		Region r = new Region();
		r.setRegion_code("001");
		r.setRegion_des("大区001修改");
		regionService.updateRegion(r);
	}

	/**
	 * 删除
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testDeleteRegion() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("region_code", "001");
		regionService.deleteRegion(inMap);
	}

	/**
	 * 主键查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindRegionByCode() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("region_code", "001");
		Region code = regionService.findRegionByCode(inMap);
		System.out.println(code + "----------");
	}

	/**
	 * 批量查询
	 * 
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testFindRegion() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("region_code", "001");
		List<Region> region = regionService.findRegion(inMap);
		System.out.println(region + "----------");
	}

	/**
	 * 分页查询
	 * 
	 * @throws BaseException
	 */
	@Test
	public void testFindRegionByPage() throws BaseException {
		Map<String, Object> inMap = new HashMap<String, Object>();
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		Page<Region> byPage = regionService.findRegionByPage(inMap, param);
		System.out.println(byPage.getTotalPageSize() + "---------");
	}

}
