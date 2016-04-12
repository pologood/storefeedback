package com.gome.storefeedback.web.page.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.gome.storefeedback.dao.common.BASRowBounds;
import com.gome.storefeedback.util.ReflectUtil;
import com.gome.storefeedback.web.page.page.PageCommonCreator;
import com.gome.storefeedback.web.page.page.PageMysqlCreator;



/**
 * @author Zhang.Mingji
 * @date 2014-1-22上午10:30:25
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DiclectStatementHandlerMysqlInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler statement = (RoutingStatementHandler) invocation
                .getTarget();
        PreparedStatementHandler handler = (PreparedStatementHandler) ReflectUtil
                .getFieldValue(statement, "delegate");
        RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(handler,
                "rowBounds");
        BASRowBounds eSNRowBounds = null;
        if (rowBounds instanceof BASRowBounds) {
            eSNRowBounds = (BASRowBounds) rowBounds;
        }
        if (rowBounds.getLimit() > 0
                && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
            BoundSql boundSql = statement.getBoundSql();
            String sql = boundSql.getSql();
            String orderKey = null;
            String orderType = null;
            String orderBy = null;
            if (null != eSNRowBounds) {
                orderKey = eSNRowBounds.getOrderKey();
                orderType = eSNRowBounds.getOrderType();
                orderBy = eSNRowBounds.getOrderBy();
            }
            sql = getPageSqlFront()
                    + sql
                    + getPageSqlAddition(rowBounds.getOffset(),
                            rowBounds.getLimit(), orderKey, orderType,orderBy);
            ReflectUtil.setFieldValue(boundSql, "sql", sql);
            // ReflectUtil.setFieldValue(handler, "rowBounds", new RowBounds());
        } else if (PageCommonCreator.FIND_PAGE_SIZE_TAG == rowBounds.getLimit()
                && PageCommonCreator.FIND_PAGE_SIZE_TAG == rowBounds.getOffset()) {
            BoundSql boundSql = statement.getBoundSql();
            String sql = boundSql.getSql();
            
            int index = sql.toLowerCase().indexOf(" from ");
            String count_sql = "";
            if(index != -1 && sql.toLowerCase().indexOf("group by") == -1
                    && sql.toLowerCase().indexOf("distinct") == -1
                    && sql.toLowerCase().indexOf("union") == -1
                    && sql.toLowerCase().indexOf("exists")==-1
                    ) {
                count_sql = "select count(1) num " + sql.substring(index,sql.length());
            }else {
                count_sql = "select count(1) num from (" + sql + ") tbl_";
            }
            ReflectUtil.setFieldValue(boundSql, "sql", count_sql);
        }
        return invocation.proceed();
    }

    /**
     * 得到sql前缀
     * 
     * @return
     */
    private String getPageSqlFront() {
        String sql = " ";
        switch (PageMysqlCreator.CURRENT_DATABASE_TYPE) {
        case MYSQL:
            break;
        case ORACLE:
            sql = sql + " select _rn.* from ( select tb_.*,rownum rown from (";
            break;
        default:
            break;
        }
        return sql;
    }

    /**
     * 得到分页语句后半部分
     * 
     * @param firstValue
     * @param secondValue
     * @return
     */
    private String getPageSqlAddition(int firstValue, int secondValue,
            String orderKey, String orderType,String orderBy) {
        String sql = " ";
        if(null != orderBy && !"".equals(orderBy)) {
            if(orderBy.toLowerCase().trim().startsWith("order")) {
                if(orderBy.toLowerCase().indexOf("select") == -1 && 
                   orderBy.toLowerCase().indexOf("update") == -1 &&
                   orderBy.toLowerCase().indexOf("delete") == -1 &&
                   orderBy.toLowerCase().indexOf("drop") == -1) {
                    sql = sql + orderBy;
                }
            }
        }else {
            if (null != orderKey && !"".equals(orderKey)) {
                sql = sql + " order by " + orderKey;
                if (null != orderType && !"".equals(orderType)) {
                    sql = sql + "  " + orderType;
                } else {
                    sql = sql + " asc ";
                }
            }
        }

        switch (PageMysqlCreator.CURRENT_DATABASE_TYPE) {
        case MYSQL:
            sql = sql + " limit " + firstValue + "," + secondValue + "";
            break;
        case ORACLE:
            sql = sql + " ) tb_ ) _rn where _rn.rown>=" + firstValue
                    + " and _rn.rown<" + secondValue;
            break;
        default:
            break;
        }
        return sql;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
