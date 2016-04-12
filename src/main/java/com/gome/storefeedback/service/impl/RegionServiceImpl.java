package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.RegionDao;
import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.RegionService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 大区 业务层接口
 * 
 * @author Ma.Mingyang
 * @date 2015年2月2日下午2:20:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("regionService")
public class RegionServiceImpl implements RegionService {

	private RegionDao regionDao;

	public RegionDao getRegionDao() {
		return regionDao;
	}

	@Autowired
	public void setRegionDao(@Qualifier("regionDao") RegionDao regionDao) {
		this.regionDao = regionDao;
	}

	@Override
	public void insertRegion(Region region) throws BaseException {
		this.regionDao.insertRegion(region);
	}

	@Override
	public void updateRegion(Region region) throws BaseException {
		this.regionDao.updateRegion(region);
	}

	@Override
	public void deleteRegion(Map<String, Object> inMap) throws BaseException {
		this.regionDao.deleteRegion(inMap);
	}

	@Override
	public Region findRegionByCode(Map<String, Object> inMap)
			throws BaseException {
		return this.regionDao.findRegionByCode(inMap);
	}

	@Override
	public List<Region> findRegion(Map<String, Object> inMap)
			throws BaseException {
		return this.regionDao.findRegion(inMap);
	}

	@Override
	public Page<Region> findRegionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.regionDao.findRegionByPage(inMap, param);
	}

    @Override
    public void insertBatchRegion(List<Region> regions) throws BaseException {
        for (Region region : regions) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("region_code", region.getRegion_code());
            Region oldRegion = this.findRegionByCode(inMap);
            if (null == oldRegion) {
                // Insert
                region.setUpdate_flag("C");
                this.insertRegion(region);
            } else {
                // Delete
                if (region.getUpdate_flag() != null && "D".equals(region.getUpdate_flag())) {
                    this.deleteRegion(inMap);
                } else {
                    // Update
                    region.setUpdate_flag("M");
                    this.updateRegion(region);
                }
            }
        }
    }

}
