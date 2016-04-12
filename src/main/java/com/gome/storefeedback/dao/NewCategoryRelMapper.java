package com.gome.storefeedback.dao;

import com.gome.storefeedback.entity.NewCategoryRelKey;

public interface NewCategoryRelMapper {
    int deleteByPrimaryKey(NewCategoryRelKey key);

    int insert(NewCategoryRelKey record);

    int insertSelective(NewCategoryRelKey record);
}