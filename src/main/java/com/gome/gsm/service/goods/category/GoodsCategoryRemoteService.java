package com.gome.gsm.service.goods.category;

import java.util.List;

import com.gome.storefeedback.exception.BaseException;

/**
 * 远程方法调用：品类
 * 
 * @author Gong.ZhiBin
 * @date 2015年3月19日下午8:45:49
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface GoodsCategoryRemoteService {

    /**
     * 获取十大品类
     * 
     * @return
     * @throws BaseException
     */
    public List getBaseCategory() throws BaseException;
}
