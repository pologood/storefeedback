package com.gome.storefeedback.service.test;

import java.util.List;

import org.junit.Test;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年3月4日下午8:21:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class EmployeeRemoteServiceTest extends AbstractTransactionalSpringContextTestCase{

	/**
	 * 通过员工ID查找员工
	 * @throws Exception
	 */
	@Test
	public void findEmployeeByIdTest() throws Exception{
		EmployeeRemoteService erService = (EmployeeRemoteService) this.getBeanByName("employeeRemoteService");
		Employee em=null;
        try {
            em = erService.findEmployeeById("1000000100207");
        } catch (Exception e) {
            e.printStackTrace();
            
        }
		System.out.println(em.getId() + " " + em.getEmployeeName());
	}
	
	/**
	 * 通过员工ID查找业务人员
	 * @throws Exception
	 */
	@Test
	public void findBusinessEmployeeTest() throws Exception{
		EmployeeRemoteService erService = (EmployeeRemoteService) this.getBeanByName("employeeRemoteService");
		List<Employee> list = erService.findBusinessEmployee("1000000100207",null);
		System.out.println(list.size());//1000000001157
	}

	/**
	 * 通过员工ID查找门店人员
	 * @throws Exception
	 */
	@Test
	public void findStoreEmployeeTest() throws Exception{
		EmployeeRemoteService erService = (EmployeeRemoteService) this.getBeanByName("employeeRemoteService");
		List<Employee> list = erService.findStoreEmployee("1000000100207",null);
		System.out.println(list.size());
	}
	
	  @Test
	    public void findEmployeeByMinistrationCodeAndSaleOrgTest() throws Exception{
	      EmployeeRemoteService erService = (EmployeeRemoteService) this.getBeanByName("employeeRemoteService");
	        List<Employee> list = erService.findEmployeeByMinistrationCodeAndSaleOrg("DL00", "C0L5000021,C0L5000018,C0L5000019,C037930021,C037930018,C000931Y18,C00000K311", null);
//	        List<Employee> list = erService.findEmployeeByMinistrationCodeAndSaleOrg("BJDZ", "A0D600FR18", null);
	        System.out.println(list.size());
	        for(Employee e : list){
	            System.out.println(e.getEmployeeName());
	        }
	    }
	  
}
