package com.gome.storefeedback.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.CheckSapFeedbackDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackToOa;
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
@Component("checkSapFeedbackDao")
public class CheckSapFeedbackDaoImpl extends BaseDaoImpl<SapFeedback, SapFeedback> implements CheckSapFeedbackDao {

    public static final String SELECT_KEY = "selectKey";
    public static final String DIVISIONSUM_VALUE = "divisionSum";
    public static final String GOODSSUM_VALUE = "goodsSum";
    public static final String BRANDSUM_VALUE = "brandSum";
    public static final String CATEGORYSUM_VALUE = "categorySum";

    @Override
    public void batchSapFeedback(List<SapFeedback> sapFeedbacks) throws BaseException {
        try {
            this.insert("Mapper.CheckSapFeedback.batch", sapFeedbacks);
        } catch (Exception e) {
            throw new BaseException("batch sapFeedback error.", e);
        }
    }

    @Override
    public void insertSapFeedback(SapFeedback sapFeedback) throws BaseException {
        try {
            this.insert("Mapper.CheckSapFeedback.insert", sapFeedback);
        } catch (Exception e) {
            throw new BaseException("insert sapFeedback error.", e);
        }
    }

    @Override
    public void updateSapFeedback(SapFeedback sapFeedback) throws BaseException {
        try {
            this.update("Mapper.CheckSapFeedback.updateByPK", sapFeedback);
        } catch (Exception e) {
            throw new BaseException("update sapFeedback error.", e);
        }
    }

    @Override
    public void deleteSapFeedback(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.CheckSapFeedback.deleteByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("delete sapFeedback error.", e);
        }
    }

    @Override
    public SapFeedback findSapFeedbackById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.CheckSapFeedback.selectByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackById error.", e);
        }
    }

    @Override
    public List findSapFeedback(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.CheckSapFeedback.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedback error.", e);
        }
    }

    @Override
    public Page findSapFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.CheckSapFeedback.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackByPage error.", e);
        }
    }

    @Override
    public Page findByParams(Map<String, Object> inMap, PaginationParameters param) throws BaseException {

        try {
            if (CheckSapFeedbackDaoImpl.CATEGORYSUM_VALUE.equals(inMap.get(CheckSapFeedbackDaoImpl.SELECT_KEY).toString().trim())) {
                // 品类
                List dataList = this.findByParam("Mapper.CheckSapFeedback." + CheckSapFeedbackDaoImpl.CATEGORYSUM_VALUE, inMap);
                Page page = new Page();
                page.setDataList(dataList);
                return page;
            } else if (CheckSapFeedbackDaoImpl.BRANDSUM_VALUE.equals(inMap.get(CheckSapFeedbackDaoImpl.SELECT_KEY).toString()
                    .trim())) {
                // 品牌
                return this.findByPage("Mapper.CheckSapFeedback." + CheckSapFeedbackDaoImpl.BRANDSUM_VALUE, inMap, param);
            } else if (CheckSapFeedbackDaoImpl.GOODSSUM_VALUE.equals(inMap.get(CheckSapFeedbackDaoImpl.SELECT_KEY).toString()
                    .trim())) {
                // 型号
                return this.findByPage("Mapper.CheckSapFeedback." + CheckSapFeedbackDaoImpl.GOODSSUM_VALUE, inMap, param);
            } else if (CheckSapFeedbackDaoImpl.DIVISIONSUM_VALUE.equals(inMap.get(CheckSapFeedbackDaoImpl.SELECT_KEY).toString()
                    .trim())) {
                // 分部
                List dataListSrc = this.findByParam("Mapper.CheckSapFeedback." + CheckSapFeedbackDaoImpl.DIVISIONSUM_VALUE, inMap);
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
            return this.findByPage("Mapper.CheckSapFeedback.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findByParam error.", e);
        }
    }

    @Override
    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
             return
             this.findByParam("Mapper.CheckSapFeedback.findPushJiCaiByCategory",
             inMap);
        } catch (Exception e) {
            throw new BaseException("findPushJiCaiByCategory error.", e);
        }
    }

    @Override
    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        try {
             return
             this.findByParam("Mapper.CheckSapFeedback.findPushDiCaiByCategory",
             inMap);
        } catch (Exception e) {
            throw new BaseException("findPushDiCaiByCategory error.", e);
        }
    }

	@Override
	public List findBrandList(Map<String, Object> inMap) throws BaseException {
		return this.findByParam("Mapper.CheckSapFeedback.findBrandList", inMap);
	}

	@Override
	public void updateBatchSapFeedbackHisByAppeal(List<FeedbackAppeal> list) throws BaseException {
		try {
			this.update("Mapper.CheckSapFeedback.updateBatchSapFeedbackHisByAppeal",list);
		} catch (BaseException e) {
			throw new BaseException("updateBatchSapFeedbackHis error.", e);
		}
	}

	@Override
	public void updateBatchSapFeedbackHisByToOa(List<FeedbackToOa> list)
			throws BaseException {
		try {
			this.update("Mapper.CheckSapFeedback.updateBatchSapFeedbackHisByToOa",list);
		} catch (BaseException e) {
			throw new BaseException("updateBatchSapFeedbackHisByToOa error.", e);
		}
	}

	@Override
	public void updateSapFeedbackHisByAppeal(FeedbackAppeal feedbackAppeal)
			throws BaseException {
		try {
			this.update("Mapper.CheckSapFeedback.updateSapFeedbackHisByAppeal",feedbackAppeal);
		} catch (BaseException e) {
			throw new BaseException("updateSapFeedbackHisByAppeal error.", e);
		}
	}

	@Override
	public void batchInsert(ArrayList<SapFeedbackCheckEmp> list)
			throws BaseException {
		try {
			this.insert("Mapper.CheckSapFeedback.batchInsert",list);
		} catch (BaseException e) {
			throw new BaseException("CheckSapFeedback.batchInsert error.", e);
		}
	}

	@Override
	public List<Map<String, Object>> getCheckByEmp(Map<String, Object> inMap) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
