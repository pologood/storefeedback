package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.NewCategoryStatus;
import com.gome.storefeedback.exception.BaseException;

public interface NewCategoryStatusService {
    
    public List<NewCategoryStatus> selectByEntity(Map<String, Object> obj)  throws BaseException ;;

}
