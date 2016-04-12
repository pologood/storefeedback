package com.gome.storefeedback.service.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.MQErrorInfo;
import com.gome.storefeedback.service.MQErrorInfoService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * MQErrorInfoService 测试
 * 
 * @author Gong.ZhiBin
 * @date 2015年04月01日 15时48分14秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class MQErrorInfoServiceTest extends AbstractTransactionalSpringContextTestCase {

    private MQErrorInfoService mQErrorInfoService = null;

    @Before
    public void init() {
        mQErrorInfoService = (MQErrorInfoService) this.getBeanByName("mQErrorInfoService");
    }

    @Test
    @Rollback(false)
    public void insertMQErrorInfoTest() throws Exception {
        MQErrorInfo mQErrorInfo = new MQErrorInfo();
        mQErrorInfo.setId(UUIDUtil.getUUID());
        mQErrorInfo.setCreateTime(new Date());
        mQErrorInfo.setContent("setContent");
        mQErrorInfo.setType("feedback");
        mQErrorInfo.setError("setError");
        mQErrorInfoService.insertMQErrorInfo(mQErrorInfo);
    }

    @Test
    @Rollback(false)
    public void deleteMQErrorInfoTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", "97ecd8153e414667acbea679453860b4");
        mQErrorInfoService.deleteMQErrorInfo(inMap);
    }

    @Test
    @Rollback(false)
    public void findMQErrorInfoByIdTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", "97ecd8153e414667acbea679453860b4");
        MQErrorInfo mQErrorInfo = mQErrorInfoService.findMQErrorInfoById(inMap);
        System.out.println(mQErrorInfo);
    }

    @Test
    @Rollback(false)
    public void findMQErrorInfoTest() throws Exception {
        List mQErrorInfos = mQErrorInfoService.findMQErrorInfo(null);
        System.out.println(mQErrorInfos.size());
    }

    @Test
    @Rollback(false)
    public void findMQErrorInfoByPageTest() throws Exception {
        Page page = mQErrorInfoService.findMQErrorInfoByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
