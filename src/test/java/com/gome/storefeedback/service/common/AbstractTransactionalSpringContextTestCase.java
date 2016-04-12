package com.gome.storefeedback.service.common;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * @author Zhang.Mingji
 * @date 2014年5月21日上午10:58:40
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:/spring/spring-*.xml"
})
public class AbstractTransactionalSpringContextTestCase extends AbstractTransactionalJUnit4SpringContextTests{

	/**
     * 初始化Bean
     * 
     * @param name
     * @return
     * @throws BeansException
     */
    public Object getBeanByName(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
}
