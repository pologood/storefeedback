package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.DivisionDao;
import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.DivisionService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 分部业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午2:20:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("divisionService")
public class DivisionServiceImpl implements DivisionService {

	private DivisionDao divisionDao;

	public DivisionDao getDivisionDao() {
		return divisionDao;
	}

	@Autowired
	public void setDivisionDao(@Qualifier("divisionDao") DivisionDao divisionDao) {
		this.divisionDao = divisionDao;
	}

	@Override
	public void insertDivision(Division division) throws BaseException {
		this.divisionDao.insertDivision(division);
	}

	@Override
	public void updateDivision(Division division) throws BaseException {
		this.divisionDao.updateDivision(division);
	}

	@Override
	public void deleteDivision(Map<String, Object> inMap) throws BaseException {
		this.divisionDao.deleteDivision(inMap);
	}

	@Override
	public Division findDivisionByCode(Map<String, Object> inMap)
			throws BaseException {
		return this.divisionDao.findDivisionByCode(inMap);
	}

	@Override
	public List<Division> findDivision(Map<String, Object> inMap)
			throws BaseException {
		return this.divisionDao.findDivision(inMap);
	}

	@Override
	public Page<Division> findDivisionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.divisionDao.findDivisionByPage(inMap, param);
	}

    @Override
    public void insertBatchDivision(List<Division> divisions) throws BaseException {
        for (Division division : divisions) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("division_code", division.getDivision_code());
            Division oldDivision = this.findDivisionByCode(inMap);
            if (null == oldDivision) {
                // Insert
                division.setUpdate_flag("C");
                this.insertDivision(division);
            } else {
                // Delete
                if (division.getUpdate_flag() != null && "D".equals(division.getUpdate_flag())) {
                    this.deleteDivision(inMap);
                } else {
                    // Update
                    division.setUpdate_flag("M");
                    this.updateDivision(division);
                }
            }
        }
    }

}
