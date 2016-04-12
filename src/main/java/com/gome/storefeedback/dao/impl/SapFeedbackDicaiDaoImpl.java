package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackDicaiDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedbackDicai;
import com.gome.storefeedback.exception.BaseException;

/**
 * 
 * feedbackPushDao实现
 * 
 * @author
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapFeedbackDicaiDao")
public class SapFeedbackDicaiDaoImpl extends BaseDaoImpl<SapFeedbackDicai, SapFeedbackDicai> implements SapFeedbackDicaiDao {

    @Override
    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapFeedbackDicai.findPushJiCaiByCategory", inMap);
        } catch (Exception e) {
            throw new BaseException("findPushJiCaiByCategory error.", e);
        }
    }

    @Override
    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapFeedbackDicai.findPushDiCaiByCategory", inMap);
        } catch (Exception e) {
            throw new BaseException("findPushDiCaiByCategory error.", e);
        }
    }

    @Override
    public List findPushSumDiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapFeedbackDicai.findPushSumDiCaiByCategory", inMap);
        } catch (Exception e) {
            throw new BaseException("findPushDiCaiByCategory error.", e);
        }
    }
}
