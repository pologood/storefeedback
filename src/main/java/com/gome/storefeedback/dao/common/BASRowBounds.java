package com.gome.storefeedback.dao.common;

import org.apache.ibatis.session.RowBounds;
/**
 * @author Zhang.Mingji
 * @date 2014-1-22上午9:21:42
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class BASRowBounds extends RowBounds {

    public final static int NO_ROW_OFFSET = 0;
    public final static int NO_ROW_LIMIT = Integer.MAX_VALUE;
    public final static RowBounds DEFAULT = new RowBounds();
    private int offset;
    private int limit;
    private String orderKey;
    private String orderType;
    private String orderBy;
    
    
    
    /**
     * 获取orderKey
     * @return orderKey
     */
    public String getOrderKey() {
        return orderKey;
    }

    /**
     * 设置orderKey
     * @param orderKey orderKey
     */
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    /**
     * 获取orderType
     * @return orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置orderType
     * @param orderType orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public BASRowBounds() {
      this.offset = NO_ROW_OFFSET;
      this.limit = NO_ROW_LIMIT;
    }

    public BASRowBounds(int offset, int limit,String orderKey,String orderType,String orderBy) {
      this.offset = offset;
      this.limit = limit;
      this.orderKey=orderKey;
      this.orderType=orderType;
      this.orderBy = orderBy;
    }

    public int getOffset() {
      return offset;
    }

    public int getLimit() {
      return limit;
    }

    /**
     * 获取orderBy
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置orderBy
     * @param orderBy orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    
}
