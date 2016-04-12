package com.gome.storefeedback.common;

/**
 * 业务字典常量
 * 
 * @author Zhang.Mingji
 * @date 2014年8月8日上午10:28:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class BusinessGlossary {

    public static final int INFORM_TYPE_BUSINESS = 106; // 业务通知
    public static final String INFORM_TITLE_BUSINESS = "业务通知";

    public static final int INFORM_TYPE_STORES = 107; // 门店通知
    public static final String INFORM_TITLE_STORES = "门店通知";

    public static final int INFORM_TYPE_FEEDBACK = 109; // 缺断货通知
    public static final String INFORM_TITLE_FEEDBACK = "缺断货通知";

    public static final String APP_STOREFEEDBACK = "storefeedback";

    public static final String ACCESS_TOKEN = "accesstoken";

    public static final String EMPLOYEE = "employee";

    /**
     * 错误代码key
     */
    public static final String ERROR_CODE = "error_code";
    /**
     * 错误消息key
     */
    public static final String ERROR_MESSAGE = "error_msg";

    /**
     * token模块错误代码
     */
    public static final String EC6001 = "6001";
    /**
     * token模块错误消息
     */
    public static final String EM6001 = "您的登录已失效，请重新登录";
}
