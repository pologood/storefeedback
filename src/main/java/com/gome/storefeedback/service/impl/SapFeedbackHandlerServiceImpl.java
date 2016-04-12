package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.service.SapFeedbackResultOrderService;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.dao.SapFeedbackHandlerDao;
import com.gome.storefeedback.dao.SapFeedbackResultOrderDao;
import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.entity.SapFeedbackResultOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackHandlerService实现
 * 
 * @author
 * @date 2015年07月23日 18时35分24秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("sapFeedbackHandlerService")
public class SapFeedbackHandlerServiceImpl implements SapFeedbackHandlerService {

    // @Resource
    // private SapOrderService sapOrderService;
    //
    @Resource
    private SapFeedbackResultOrderService sapFeedbackResultOrderService;

    private SapFeedbackHandlerDao sapFeedbackHandlerDao;

    public SapFeedbackHandlerDao getSapFeedbackHandlerDao() {
        return sapFeedbackHandlerDao;
    }

    @Autowired
    public void setSapFeedbackHandlerDao(@Qualifier("sapFeedbackHandlerDao") SapFeedbackHandlerDao sapFeedbackHandlerDao) {
        this.sapFeedbackHandlerDao = sapFeedbackHandlerDao;
    }

    @Override
    public void insertHandlerResultYes(List<SapFeedbackHandler> sapFeedbackHandlers, List<String> orders,String goodsCode)
            throws BaseException {
        if (sapFeedbackHandlers != null && sapFeedbackHandlers.size() > 0) {
            for (SapFeedbackHandler sapFH : sapFeedbackHandlers) {
                Map<String, Object> inMap = new HashMap<String, Object>();
                inMap.put("request", sapFH.getRequest());
                inMap.put("datapakid", sapFH.getDatapakid());
                inMap.put("record", sapFH.getRecord());
                List<SapFeedbackHandler> oldEntity = sapFeedbackHandlerDao.findSapFeedbackHandler(inMap);
                if (oldEntity != null && oldEntity.size() > 0) {
                    sapFH.setId(oldEntity.get(0).getId());
                    sapFeedbackHandlerDao.updateSapFeedbackHandler(sapFH);
                } else {
                    sapFH.buildPK();
                    sapFeedbackHandlerDao.insertSapFeedbackHandler(sapFH);
                }
                List<SapFeedbackResultOrder> sapFeedbackResultOrders = new ArrayList<SapFeedbackResultOrder>();
                for (String orderId : orders) {
                    SapFeedbackResultOrder sapFeedbackResultOrder = new SapFeedbackResultOrder();
                    sapFeedbackResultOrder.setId(sapFH.getId());
                    sapFeedbackResultOrder.setOrderId(orderId.split("\\+")[0]);
                     sapFeedbackResultOrder.setSapOrderId(Long.valueOf(goodsCode));
                    sapFeedbackResultOrders.add(sapFeedbackResultOrder);
                }
                sapFeedbackResultOrderService.batchSapFeedbackResultOrder(sapFeedbackResultOrders);
            }
        }
    }

    @Override
    public void insertHandlerResultNo(List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException {
        if (sapFeedbackHandlers != null && sapFeedbackHandlers.size() > 0) {
            for (SapFeedbackHandler sapFH : sapFeedbackHandlers) {
                Map<String, Object> inMap = new HashMap<String, Object>();
                inMap.put("request", sapFH.getRequest());
                inMap.put("datapakid", sapFH.getDatapakid());
                inMap.put("record", sapFH.getRecord());
                List<SapFeedbackHandler> oldEntity = sapFeedbackHandlerDao.findSapFeedbackHandler(inMap);
                if (oldEntity != null && oldEntity.size() > 0) {
                    sapFH.setId(oldEntity.get(0).getId());
                    sapFeedbackHandlerDao.updateSapFeedbackHandler(sapFH);
                } else {
                    sapFH.buildPK();
                    sapFeedbackHandlerDao.insertSapFeedbackHandler(sapFH);
                }
            }
        }
    }

    @Override
    public void batchSapFeedbackHandler(List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException {
        this.sapFeedbackHandlerDao.batchSapFeedbackHandler(sapFeedbackHandlers);
    }

    @Override
    public int insertSapFeedbackHandler(SapFeedbackHandler sapFeedbackHandler) throws BaseException {
        return this.sapFeedbackHandlerDao.insertSapFeedbackHandler(sapFeedbackHandler);
    }

    @Override
    public void updateSapFeedbackHandler(SapFeedbackHandler sapFeedbackHandler) throws BaseException {
        this.sapFeedbackHandlerDao.updateSapFeedbackHandler(sapFeedbackHandler);
    }

    @Override
    public void deleteSapFeedbackHandler(Map<String, Object> inMap) throws BaseException {
        this.sapFeedbackHandlerDao.deleteSapFeedbackHandler(inMap);
    }

    @Override
    public SapFeedbackHandler findSapFeedbackHandlerById(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackHandlerDao.findSapFeedbackHandlerById(inMap);
    }

    @Override
    public List findSapFeedbackHandler(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackHandlerDao.findSapFeedbackHandler(inMap);
    }

    @Override
    public Page findSapFeedbackHandlerByPage(Map<String, Object> inMap, PaginationParameters param)
            throws BaseException {
        return this.sapFeedbackHandlerDao.findSapFeedbackHandlerByPage(inMap, param);
    }

	@Override
	public int updateSapFeedbackToHr(
			List<SapFeedbackHandler> sapFeedbackHandlers) throws BaseException {
		return this.sapFeedbackHandlerDao.updateSapFeedbackToHr(sapFeedbackHandlers);
	}

}
