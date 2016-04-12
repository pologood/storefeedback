package com.gome.storefeedback.service;

import java.util.Date;

import com.gome.storefeedback.exception.BaseException;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月29日上午11:11:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackPushService {

    /**
     * @param pushDate
     * @throws BaseException
     */
    public void pushByPosition(Date pushDate) throws BaseException;

}
