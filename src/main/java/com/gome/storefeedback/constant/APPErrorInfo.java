package com.gome.storefeedback.constant;

/**
 * APP请求错误信息
 * 
 * @author Yan.Kai
 * @date 2014年9月25日下午3:33:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface APPErrorInfo {
    public static final String ERRORCODE = "error_code";
    public static final String ERRORMSG = "error_msg";

    public static final String E10000 = "10000";
    public static final String M10000 = "服务器异常";

    public static final String E10001 = "10001";
    public static final String M10001 = "服务器解析出错";

    public static final String E10002 = "10002";
    public static final String M10002 = "该手机号无注册权限,请与相关管理人员联系";

    public static final String E10003 = "10003";
    public static final String M10003 = "密码不正确";

    public static final String E10004 = "10004";
    public static final String M10004 = "尚未注册，请注册";

    public static final String E10005 = "10005";
    public static final String M10005 = "回执码错误";

    public static final String E10006 = "10006";
    public static final String M10006 = "该手机号已经被另一台手机注册,请与相关管理人员联系";

    public static final String E10007 = "10007";
    public static final String M10007 = "签到失败,与配送中心距离太远";

    public static final String E10008 = "10008";
    public static final String M10008 = "您的设备已被管理员停用";

    public static final String E10009 = "10009";
    public static final String M10009 = "签到失败,二维码值不对";

    public static final String E10010 = "10010";
    public static final String M10010 = "此手机号有重复,请联系管理员";

    public static final String E10011 = "11011";
    public static final String M10011 = "您查找商品不存在";

    public static final String E10012 = "11012";
    public static final String M10012 = "请输入商品编码";

    public static final String E11020 = "11020";
    public static final String M11020 = "订单号不存在";

    public static final String E11021 = "11021";
    public static final String M11021 = "请输入订单号";

    public static final String E11022 = "11022";
    public static final String M11022 = "请选择不补货原因";
    
    public static final String E11023 = "11023";
    public static final String M11023 = "请选择不补货原因";
    
    public static final String E11051 = "11051";
    public static final String M11051 = "审批人账号不正确";
    
    public static final String E11052 = "11052";
    public static final String M11052 = "请填写审批原因";
    
    public static final String E11053 = "11053";
    public static final String M11053 = "请填选择要处理的缺断货信息";

    public static final String E11054 = "11054";
    public static final String M11054 = "前日处理时间已结束，请等待当日数据更新";
    
    public static final String E11055 = "11055";
    public static final String M11055 = "考核时间选择不正确,请重新选择";
    
    public static final String E11056 = "11056";
    public static final String M11056 = "当前只能处理本周数据";
    
    public static final String E11057 = "11057";
    public static final String M11057 = "当前用户无权查看";
    
    public static final String E11058 = "11058";
    public static final String M11058 = "输入订单号与所选条目不匹配，请重新输入";
    
    public static final String E11059 = "11059";
    public static final String M11059 = "输入订单号格式不正确，请重新输入";
    
    public static final String E11060 = "11060";
    public static final String M11060 = "您没有配置品类职务对应关系，无权限查看";
    
    public static final String E11061 = "11061";
    public static final String M11061 = "您没有考核相关信息";
    
    public static final String E11062 = "11062";
    public static final String M11062 = "周五数据维护，请稍后处理";
}
