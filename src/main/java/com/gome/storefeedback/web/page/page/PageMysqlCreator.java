package com.gome.storefeedback.web.page.page;

/**
 * 
 * @author Zhang.Mingji
 * @date 2014-1-22上午9:33:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class PageMysqlCreator extends PageCommonCreator{


    //TODO 设置当前数据库类型
     public static DataBaseType CURRENT_DATABASE_TYPE = PageMysqlCreator.DataBaseType.MYSQL;
    //public static DataBaseType CURRENT_DATABASE_TYPE = PageCreator.DataBaseType.ORACLE;

    public enum DataBaseType {
        MYSQL, ORACLE
    }

    private DataBaseType dataBaseType;

    /**
     * 获取dataBaseType
     * 
     * @return dataBaseType
     */
    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    /**
     * 设置dataBaseType
     * 
     * @param dataBaseType dataBaseType
     */
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    /**
     * 得到翻页第一个参数
     * @param currentPageNum
     * @param totalSize
     * @param pageMaxSize
     * @return
     */
    public static int getFirstPageValue( PaginationParameters paginationParameters ,int totalSize) {
        int firstPageValue = 0;
        int currentPageNum=paginationParameters.getCurrentPageNumber();
        int pageMaxSize=paginationParameters.getPageMaxSize();
        switch (PageMysqlCreator.CURRENT_DATABASE_TYPE) {
        case MYSQL:
        case ORACLE:
        default:
            // 当前页-1后，与每页显示记录数相乘；
            firstPageValue = (currentPageNum - 1) * pageMaxSize;
            //如果超出最大页
            if (firstPageValue > totalSize) {
                //最后一页
                //设置当前页为最后一页
                currentPageNum=totalSize/pageMaxSize;
                paginationParameters.setCurrentPageNumber(currentPageNum);
                firstPageValue = totalSize - pageMaxSize;
            }
            //如果小于零，赋值为0
            if (firstPageValue < 0) {
                firstPageValue = 0;
                paginationParameters.setCurrentPageNumber(PageMysqlCreator.FIRST_PAGE_NUMBER);
            }
            break;
        }
        return firstPageValue;
    }

    /**
     * 得到翻页第二个参数
     * @param firstPageValue
     * @param totalSize
     * @param pageMaxSize
     * @return
     */
    public static int getSeondPageValue(int firstPageValue, int totalSize,
            int pageMaxSize) {
        int secondPageNum = 0;
        switch (PageMysqlCreator.CURRENT_DATABASE_TYPE) {
        case MYSQL:
            secondPageNum = pageMaxSize;
            break;
        case ORACLE:
            secondPageNum = firstPageValue + pageMaxSize;
            break;
        default:
            break;
        }
        return secondPageNum;
    }
}
