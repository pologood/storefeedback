package com.gome.storefeedback.dao;

import com.gome.storefeedback.entity.NewModelRelKey;

public interface NewModelRelMapper {
    int deleteByPrimaryKey(NewModelRelKey key);

    int insert(NewModelRelKey record);

    int insertSelective(NewModelRelKey record);
}