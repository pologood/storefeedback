package com.gome.storefeedback.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.UserService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 用户service接口 测试用例
 * @author Ma.Mingyang
 * @date 2015年1月22日下午5:43:42
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class UserServiceTest extends AbstractTransactionalSpringContextTestCase {

	@Resource
	private UserService userService;
	@Before
	public void init(){
		userService=(UserService) this.getBeanByName("userService");
	}
	@Resource
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * 添加用户
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testInsertUser() throws BaseException {
		
		User user =new User();
		user.setId(UUIDUtil.getUUID().toString());
		user.setIsAdmin(1);
		user.setStartus(1);
		user.setName("admin");
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encodePassword("admin", null));
		System.out.println(passwordEncoder.encodePassword("admin", null).length()+"----");
		System.out.println(passwordEncoder.encodePassword("admin", null)+"----");
		
//		userService.insertUser(user);
	}
	/**
	 * 删除用户
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testDeleteUser() throws BaseException {
		Map<String, Object> inMap =new HashMap<String, Object>();
		inMap.put("id", "619eb185108b4b23b75b6ec32eb6fac2");
		userService.deleteUser(inMap);
	}
	/**
	 * 修改用户
	 * @throws BaseException
	 */
	@Test
	@Rollback(false)
	public void testUpdateUser() throws BaseException {
		User user=new User();
		user.setId("d5c651dc22b64e59896dc11534d8d5fc");
		user.setName("update");
		userService.updateUser(user);
	}
	/**
	 * 根据id查找
	 * @throws BaseException
	 */
	@Test
	public void testFindUserById() throws BaseException {
		Map<String, Object> inMap =new HashMap<String, Object>();
		inMap.put("id", "d5c651dc22b64e59896dc11534d8d5fc");
		User user = userService.findUserById(inMap);
		System.out.println(user);
	}
	/**
	 * 根据参数查询
	 * @throws BaseException
	 */
	@Test
	public void testFindUserByParam() throws BaseException{
		Map<String, Object> inMap =new HashMap<String, Object>();
		inMap.put("id", "d5c651dc22b64e59896dc11534d8d5fc");
		inMap.put("name", "update1");
		List<User> userList = userService.findUser(inMap);
		System.out.println(userList);
	}
	/**
	 * 分页查询测试
	 * @throws BaseException
	 */
	@Test
	public void testFindByPage() throws BaseException{
		Map<String, Object> inMap =new HashMap<String, Object>();
		inMap.put("name", "update");
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		Page page=userService.findUserByPage(inMap, param);
		System.out.println(page.getTotalSize()+"-----------");
	}

}
