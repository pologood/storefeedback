package com.gome.storefeedback.service;

import java.util.List;

import com.gome.gsm.entity.org.Employee;
import com.gome.gsm.entity.org.EmployeeExt;
import com.gome.storefeedback.exception.BaseException;

/**
 * 员工远程调用服务
 * 
 * @author Zhang.Mingji
 * @date 2015年3月4日下午7:20:51
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface EmployeeRemoteService {

    /**
     * 通过员工Id查找员工
     * 
     * @param eId 员工Id
     * @return
     * @throws BaseException
     */
    public Employee findEmployeeById(String eId) throws BaseException;

    /**
     * 通过员工编号查找业务相关人员
     * 
     * @param eId 员工Id
     * @return
     * @throws BaseException
     */
    public List<Employee> findBusinessEmployee(String eId, String nameQueryString) throws BaseException;

    /**
     * 通过员工ID查找门店人员
     * 
     * @param eId
     * @return
     * @throws BaseException
     */
    public List<Employee> findStoreEmployee(String eId, String nameQueryString) throws BaseException;

    /**
     * 通过职务编码
     * 
     * @param regionCode 大区代码
     * @param subSection 分布编码
     * @param ministrationIds 职务编码
     * @return
     * @throws BaseException
     */
    public List<Employee> findEmployeeByMinistrationCode(String regionCode, String subSection, String ministrationIds,
            String nameQueryString) throws BaseException;

    /**
     * 通过销售组织查询员工
     * 
     * @param saleOrg 销售组织编码
     * @param ministrationIds 职务编码
     * @param nameQueryString 查询字符串
     * @return
     * @throws BaseException
     */
    public List<Employee> findEmployeeByMinistrationCodeAndSaleOrg(String saleOrg, String ministrationIds,
            String nameQueryString) throws BaseException;

    /**
     * 通过门店编码查询员工
     * 
     * @param storeCodes
     * @param ministrationIds
     * @param nameQueryString
     * @return
     * @throws BaseException
     */
    public List<EmployeeExt> findEmployeeByMinistrationCodeAndStoreCode(String storeCodes, String ministrationIds,
            String nameQueryString) throws BaseException;
    
    /**
     * 根据域账号查询员工信息
     * @param adAccount
     * @return
     * @throws BaseException
     */
    public Employee findEmpByAdAccount(String adAccount) throws BaseException;
}
