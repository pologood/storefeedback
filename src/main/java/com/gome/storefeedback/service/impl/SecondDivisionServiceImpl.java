package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.SecondDivisionDao;
import com.gome.storefeedback.entity.SecondDivision;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SecondDivisionService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 二级分部业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午2:20:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("secondDivisionService")
public class SecondDivisionServiceImpl implements SecondDivisionService {

    private SecondDivisionDao secondDivisionDao;

    public SecondDivisionDao getSecondDivisionDao() {
        return secondDivisionDao;
    }

    @Autowired
    public void setSecondDivisionDao(@Qualifier("secondDivisionDao") SecondDivisionDao secondDivisionDao) {
        this.secondDivisionDao = secondDivisionDao;
    }

    @Override
    public void insertSecondDivision(SecondDivision secondDivision) throws BaseException {
        this.secondDivisionDao.insertSecondDivision(secondDivision);
    }

    @Override
    public void updateSecondDivision(SecondDivision secondDivision) throws BaseException {
        this.secondDivisionDao.updateSecondDivision(secondDivision);
    }

    @Override
    public void deleteSecondDivision(Map<String, Object> inMap) throws BaseException {
        this.secondDivisionDao.deleteSecondDivision(inMap);
    }

    @Override
    public SecondDivision findSecondDivisionByCode(Map<String, Object> inMap) throws BaseException {
        return this.secondDivisionDao.findSecondDivisionByCode(inMap);
    }

    @Override
    public List<SecondDivision> findSecondDivision(Map<String, Object> inMap) throws BaseException {
        return this.secondDivisionDao.findSecondDivision(inMap);
    }

    @Override
    public Page<SecondDivision> findSecondDivisionByPage(Map<String, Object> inMap, PaginationParameters param)
            throws BaseException {
        return this.secondDivisionDao.findSecondDivisionByPage(inMap, param);
    }

    @Override
    public void insertBatchSecondDivision(List<SecondDivision> secondDivisions) throws BaseException {
        for (SecondDivision secondDivision : secondDivisions) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("second_division_code", secondDivision.getSecond_division_code());
            SecondDivision oldSencondDivision = this.findSecondDivisionByCode(inMap);
            if (null == oldSencondDivision) {
                // Insert
                secondDivision.setUpdate_flag("C");
                this.insertSecondDivision(secondDivision);
            } else {
                // Delete
                if (secondDivision.getUpdate_flag() != null && "D".equals(secondDivision.getUpdate_flag())) {
                    this.deleteSecondDivision(inMap);
                } else {
                    // Update
                    secondDivision.setUpdate_flag("M");
                    this.updateSecondDivision(secondDivision);
                }
            }
        }
    }

}
