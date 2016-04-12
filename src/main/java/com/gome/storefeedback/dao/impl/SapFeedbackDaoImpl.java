package com.gome.storefeedback.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackDao实现
 * 
 * @author
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapFeedbackDao")
public class SapFeedbackDaoImpl extends BaseDaoImpl<SapFeedback, SapFeedback> implements SapFeedbackDao {

    public static final String SELECT_KEY = "selectKey";
    public static final String DIVISIONSUM_VALUE = "divisionSum";
    public static final String GOODSSUM_VALUE = "goodsSum";
    public static final String BRANDSUM_VALUE = "brandSum";
    public static final String CATEGORYSUM_VALUE = "categorySum";

    @Override
    public void batchSapFeedback(List<SapFeedback> sapFeedbacks) throws BaseException {
        try {
            this.insert("Mapper.SapFeedback.batch", sapFeedbacks);
        } catch (Exception e) {
            throw new BaseException("batch sapFeedback error.", e);
        }
    }

    @Override
    public void insertSapFeedback(SapFeedback sapFeedback) throws BaseException {
        try {
            this.insert("Mapper.SapFeedback.insert", sapFeedback);
        } catch (Exception e) {
            throw new BaseException("insert sapFeedback error.", e);
        }
    }

    @Override
    public void updateSapFeedback(SapFeedback sapFeedback) throws BaseException {
        try {
            this.update("Mapper.SapFeedback.updateByPK", sapFeedback);
        } catch (Exception e) {
            throw new BaseException("update sapFeedback error.", e);
        }
    }

    @Override
    public void deleteSapFeedback(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.SapFeedback.deleteByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("delete sapFeedback error.", e);
        }
    }

    @Override
    public SapFeedback findSapFeedbackById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.SapFeedback.selectByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackById error.", e);
        }
    }

    @Override
    public List findSapFeedback(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapFeedback.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedback error.", e);
        }
    }

    @Override
    public Page findSapFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.SapFeedback.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackByPage error.", e);
        }
    }

    @Override
    public Page findByParams(Map<String, Object> inMap, PaginationParameters param) throws BaseException {

        try {
            if (SapFeedbackDaoImpl.CATEGORYSUM_VALUE.equals(inMap.get(SapFeedbackDaoImpl.SELECT_KEY).toString().trim())) {
                // 品类
                List dataList = this.findByParam("Mapper.SapFeedback." + SapFeedbackDaoImpl.CATEGORYSUM_VALUE, inMap);
                Page page = new Page();
                page.setDataList(dataList);
                return page;
            } else if (SapFeedbackDaoImpl.BRANDSUM_VALUE.equals(inMap.get(SapFeedbackDaoImpl.SELECT_KEY).toString()
                    .trim())) {
                // 品牌
                return this.findByPage("Mapper.SapFeedback." + SapFeedbackDaoImpl.BRANDSUM_VALUE, inMap, param);
            } else if (SapFeedbackDaoImpl.GOODSSUM_VALUE.equals(inMap.get(SapFeedbackDaoImpl.SELECT_KEY).toString()
                    .trim())) {
                // 型号
                return this.findByPage("Mapper.SapFeedback." + SapFeedbackDaoImpl.GOODSSUM_VALUE, inMap, param);
            } else if (SapFeedbackDaoImpl.DIVISIONSUM_VALUE.equals(inMap.get(SapFeedbackDaoImpl.SELECT_KEY).toString()
                    .trim())) {
                // 分部
                List dataListSrc = this.findByParam("Mapper.SapFeedback." + SapFeedbackDaoImpl.DIVISIONSUM_VALUE, inMap);
                List dataList=new ArrayList();
                Map<String,Object> map =new LinkedHashMap<String,Object>();
                if(dataListSrc!=null&&dataListSrc.size()>0){
                    for(int i=0;i<dataListSrc.size();i++){
                        map.put(((Map<String,Object>)(dataListSrc.get(i))).get("id").toString(), dataListSrc.get(i));
                    }
                    dataList=new ArrayList(map.values());
                }
                Page page = new Page();
                page.setDataList(dataList);
                return page;
            }
            return this.findByPage("Mapper.SapFeedback.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findByParam error.", e);
        }
    }

    @Override
    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
            return new ArrayList();
            // return
            // this.findByParam("Mapper.SapFeedback.findPushJiCaiByCategory",
            // inMap);
        } catch (Exception e) {
            throw new BaseException("findPushJiCaiByCategory error.", e);
        }
    }

    @Override
    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
            return new ArrayList();
            // return
            // this.findByParam("Mapper.SapFeedback.findPushDiCaiByCategory",
            // inMap);
        } catch (Exception e) {
            throw new BaseException("findPushDiCaiByCategory error.", e);
        }
    }

    /**
     * 把BW推送的缺断货数据关联empNumber之后插入到zmm_ds62_toHr表，每月推送数据给HR的数据来源
     * @param currentDateTime
     * @throws BaseException
     */
	@Override
	public int insertIntoHr(@Param("list") List<SapFeedbackCheckEmp> sapFeedbackCheckEmps) throws BaseException {
		try {
            return this.insert("Mapper.SapFeedback.insertIntoHr", sapFeedbackCheckEmps);
        } catch (Exception e) {
            throw new BaseException("SapFeedback.insertIntoHr error.", e);
        }
	}
}
