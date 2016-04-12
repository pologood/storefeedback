<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link type="text/css" href="<%=basePath%>resources/jeui/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="<%=basePath%>resources/jeui/themes/icon.css" rel="stylesheet" />

<!-- jq easy ui -->
<script type="text/javascript" src="<%=basePath%>resources/jeui/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/jeui/easyloader.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/jeui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/jeui/local/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth() + 1, // 月份
        "d+" : this.getDate(), // 日
        "h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
        "H+" : this.getHours(), // 小时
        "m+" : this.getMinutes(), // 分
        "s+" : this.getSeconds(), // 秒
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
        "S" : this.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
String.prototype.getStrLength = function() {
    var content = this;
    var len = 0;
    if (null == content || "" == content) {
        return len;
    }
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 255 || this.charCodeAt(i) < 0)
            len += 2;
        else
            len++;
    }
    return len;
};
Array.prototype.clear = function() {
    this.length = 0;
};
Array.prototype.insertAt = function(index, obj) {
    this.splice(index, 0, obj);
};
Array.prototype.removeAt = function(index) {
    this.splice(index, 1);
};
Array.prototype.containElem = function(index) {
    for (var i = 0; i < this.length; i++) {
        if (index == this[i]) {
            return true;
        }
    }
    return false;
};
Array.prototype.remove = function(obj) {
    var index = 0;
    for (var i = 0; i < this.length; i++) {
        if (obj == this[i]) {
            index = i;
        }
    }
    this.removeAt(index);
};

var basePath = "<%=basePath%>";
</script>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=hX5ey9Z4876lHMZavamsIDHj"></script>

