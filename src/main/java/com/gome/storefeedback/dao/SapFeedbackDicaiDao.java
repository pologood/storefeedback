package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackDicai;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SAP缺断货信息Dao接口
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapFeedbackDicaiDao {

    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException ;

    public List findPushDiCaiByCategory(Map<String, Object> inMap)throws BaseException ;

    public List findPushSumDiCaiByCategory(Map<String, Object> inMap)throws BaseException ;
}
