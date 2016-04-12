package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.RegionDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;


/**
 * 大区数据层接口实现类
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:35:35
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region, Region> implements RegionDao {

    @Override
    public void insertRegion(Region region) throws BaseException {
        try {
            this.insert("Mapper.Region.insert", region);
        } catch (Exception e) {
            throw new BaseException("insert Region error.", e);
        }
    }

    @Override
    public void updateRegion(Region region) throws BaseException {
        try {
            this.update("Mapper.Region.update", region);
        } catch (Exception e) {
            throw new BaseException("update Region error.", e);
        }
    }

    @Override
    public void deleteRegion(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.Region.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete Region error.", e);
        }
    }


    @Override
    public List<Region> findRegion(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.Region.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findRegion error.", e);
        }
    }

    @Override
    public Page<Region> findRegionByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.Region.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findRegionByPage error.", e);
        }
    }

	@Override
	public Region findRegionByCode(Map<String, Object> inMap)
			throws BaseException {
		try {
            return this.findByID("Mapper.Region.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoodsById error.", e);
        }
	}

    @Override
    public void insertBatchRegion(List<Region> regions) throws BaseException {
        try {
            this.insert("Mapper.Region.batchInsert", regions);
        } catch (Exception e) {
            throw new BaseException("insertBatch regions error.", e);
        }
    }

}
