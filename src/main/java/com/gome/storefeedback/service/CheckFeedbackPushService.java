package com.gome.storefeedback.service;

import java.util.Date;

import com.gome.storefeedback.exception.BaseException;

public interface CheckFeedbackPushService {

    /**
     * @param pushDate
     * @throws BaseException
     */
    public void pushCheckFeedback(Date pushDate) throws BaseException;

}
