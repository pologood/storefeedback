package com.gome.storefeedback.dao;

import com.gome.storefeedback.entity.FeedbackAanctionConfig;

public interface FeedbackAanctionConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(FeedbackAanctionConfig record);

    int insertSelective(FeedbackAanctionConfig record);

    FeedbackAanctionConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FeedbackAanctionConfig record);

    int updateByPrimaryKey(FeedbackAanctionConfig record);
}