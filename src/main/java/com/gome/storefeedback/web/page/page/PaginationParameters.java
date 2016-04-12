package com.gome.storefeedback.web.page.page;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Zhang.Mingji
 * @date 2014-1-22上午9:33:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class PaginationParameters implements Serializable{
    
    
    /**  */
    private static final long serialVersionUID = 6389084728061045097L;
    private String orderKey;
    private String orderType;
    private String orderBy = null; //对于复杂的排序用这个 例如 order by time desc name asc
    private int alreadyLoadCount;//已经加载的数据数量
    
    
    public int getAlreadyLoadCount() {
		return alreadyLoadCount;
	}

	public void setAlreadyLoadCount(int alreadyLoadCount) {
		this.alreadyLoadCount = alreadyLoadCount;
	}

    //  
    private String _page_div;

    // 当前页
    private int currentPageNumber;

    // 每页记录数
    private int pageMaxSize = 20;

    
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

    /**
     * 获取currentPageNumber
     * 
     * @return currentPageNumber
     */
    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    /**
     * 设置currentPageNumber
     * 
     * @param currentPageNumber currentPageNumber
     */
    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    /**
     * 获取_page_div
     * 
     * @return _page_div
     */
    public String get_page_div() {
        return _page_div;
    }

    /**
     * 设置_page_div
     * 
     * @param _page_div _page_div
     */
    public void set_page_div(String _page_div) {
        this._page_div = _page_div;
    }

    /**
     * 构造函数。
     * 
     * @param pageNumber 页码。
     * @param pageMaxSize 每页最大数据条数。
     */
    public PaginationParameters(int pageNumber, int pageMaxSize) {
        this.currentPageNumber = pageNumber;
        this.pageMaxSize = pageMaxSize;
    }

    /**
     * 初始化
     * 
     * @param request
     */
    public PaginationParameters(HttpServletRequest request) {
        String currentPageNumber = request.getParameter("_hidden_currentPageNumber");
        if (null == currentPageNumber || "".equals(currentPageNumber)) {
            currentPageNumber = PageCommonCreator.FIRST_PAGE_NUMBER_STRING;
        }
        String _page_div = request.getParameter("_page_div");
        this.setCurrentPageNumber(Integer.parseInt(currentPageNumber));
        String size = request.getParameter("pageMaxSize");
        if(size == null || size.trim().equals("")) {
            this.setPageMaxSize(pageMaxSize);
        }else {
            this.setPageMaxSize(Integer.parseInt(size));
        }
        this.setOrderKey(request.getParameter("_orderKey"));
        this.setOrderType(request.getParameter("_orderType"));
        this.setOrderBy(request.getParameter("_orderBy"));
        this.set_page_div(_page_div);
    }
    
    /**
     * 初始化
     * 
     * @param request
     */
    public PaginationParameters(HttpServletRequest request,HttpServletResponse response) {
        String currentPageNumber = request.getParameter("page");
        if (null == currentPageNumber || "".equals(currentPageNumber)) {
            currentPageNumber = PageCommonCreator.FIRST_PAGE_NUMBER_STRING;
        }
        this.setCurrentPageNumber(Integer.parseInt(currentPageNumber));
        String size = request.getParameter("rows");
        if(size == null || size.trim().equals("")) {
            this.setPageMaxSize(pageMaxSize);
        }else {
            this.setPageMaxSize(Integer.parseInt(size));
        }
        this.setOrderKey(request.getParameter("_orderKey"));
        this.setOrderType(request.getParameter("_orderType"));
        this.setOrderBy(request.getParameter("_orderBy"));
    }

    /**
     * 构造函数。
     * 
     * @param pageNumber 页码。
     */
    public PaginationParameters(int pageNumber) {
        this.currentPageNumber = pageNumber;
    }

    /**
     * 第一页
     */
    public PaginationParameters() {
        this(1);
    }

    public int getPageMaxSize() {
        return pageMaxSize;
    }

    public void setPageMaxSize(int pageMaxSize) {
        this.pageMaxSize = pageMaxSize;
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
