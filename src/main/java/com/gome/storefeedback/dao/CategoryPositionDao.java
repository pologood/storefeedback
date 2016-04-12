package com.gome.storefeedback.dao;


import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月17日下午3:48:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface CategoryPositionDao {
	/**
	 * 删除品类职务
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
    int deleteCategoryPosition(Map<String,Object> inMap) throws BaseException;
    /**
     * 添加品类职务
     * @param record
     * @return
     * @throws BaseException
     */
    int insertCategoryPosition(CategoryPosition record) throws BaseException;
    /**
     * 查找品类职务
     * @param inMap
     * @return
     * @throws BaseException
     */
    List findCategoryPosition(Map<String,Object> inMap) throws BaseException;
    /**
     * 查找所有的职务
     * @param inMap
     * @return
     * @throws BaseException
     */
    List<CategoryPosition> findCategoryPositionAll(Map<String,Object> inMap) throws BaseException;
    /**
     * 通过ID查找品类职务
     * @param inMap
     * @return
     * @throws BaseException
     */
    CategoryPosition findCategoryPositionById(Map<String,Object> inMap) throws BaseException;
    /**
     * 更新品类职务
     * @param record
     * @return
     * @throws BaseException
     */
    int updateCategoryPosition(CategoryPosition record) throws BaseException;
    /**
     * 分页查找品类职务
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    Page findCategoryPositionByPage(Map<String,Object> inMap, PaginationParameters param) throws BaseException;
    
	List<CategoryPosition> findCategoryPositionNotBoos(Map<String, Object> inMap) throws BaseException;
}