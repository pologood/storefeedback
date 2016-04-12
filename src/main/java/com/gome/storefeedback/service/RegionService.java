package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * "大区" 业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:17:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface RegionService {

	/**
	 * 添加大区
	 * 
	 * @param Region
	 * @throws BaseException
	 */
	public void insertRegion(Region region) throws BaseException;

	/**
	 * 更新大区
	 * 
	 * @param Region
	 * @throws BaseException
	 */
	public void updateRegion(Region region) throws BaseException;

	/**
	 * 删除大区
	 * 
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteRegion(Map<String, Object> inMap) throws BaseException;

	/**
	 * 通过大区编码查找大区
	 * 
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public Region findRegionByCode(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 查找大区列表
	 * 
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List<Region> findRegion(Map<String, Object> inMap)
			throws BaseException;

	/**
	 * 分页查询
	 * 
	 * @param inMap
	 * @param param
	 * @return Page
	 * @throws BaseException
	 */
	public Page<Region> findRegionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException;
	
	/**
     * 批量插入大区
     * @param parseXML2Division
     */
    public void insertBatchRegion(List<Region> regions) throws BaseException;
}
