package com.gome.storefeedback.web.page.interceptor;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.gome.storefeedback.util.ReflectUtil;
import com.gome.storefeedback.web.page.page.PageCommonCreator;


/**
 * @author Zhang.Mingji
 * @date 2014-1-22上午10:27:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class DiclectResultSetHandlerInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        FastResultSetHandler resultSet = (FastResultSetHandler) invocation
                .getTarget();

        RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(resultSet,
                "rowBounds");
        // get data info
        if (rowBounds.getLimit() > 0
                && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
            ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
            // get total num
        } else if (PageCommonCreator.FIND_PAGE_SIZE_TAG == rowBounds.getLimit()
                && PageCommonCreator.FIND_PAGE_SIZE_TAG == rowBounds.getOffset()) {
            ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil
                    .getFieldValue(resultSet, "mappedStatement");

            MappedStatement n_mappedStatement = new MappedStatement.Builder(
                    mappedStatement.getConfiguration(),
                    mappedStatement.getId(), mappedStatement.getSqlSource(), mappedStatement.getSqlCommandType()).build();

            List<ResultMap> ResultMaps = (List<ResultMap>) ReflectUtil
                    .getFieldValue(mappedStatement, "resultMaps");
            List<ResultMap> nResultMaps = createResultMaps(mappedStatement,
                    ResultMaps);
            ReflectUtil.setFieldValue(n_mappedStatement, "resultMaps",
                    nResultMaps);
            ReflectUtil.setFieldValue(resultSet, "mappedStatement",
                    n_mappedStatement);
            
        }
        return invocation.proceed();
    }

    /**
     * 复制ResultMap
     * 
     * @param mappedStatement
     * @param inList
     * @return
     */
    private List<ResultMap> createResultMaps(MappedStatement mappedStatement,
            List<ResultMap> inList) {
        List<ResultMap> resultMaps_backup = new ArrayList<ResultMap>();
        List<ResultMapping> rmList=new ArrayList<ResultMapping>();
        for (ResultMap rm : inList) {
            ResultMap.Builder resultMapBuilder = new ResultMap.Builder(
                    mappedStatement.getConfiguration(), rm.getId(),
                    Integer.class, rmList);
            ResultMap nrm = resultMapBuilder.build();
            resultMaps_backup.add(nrm);
        }
        return resultMaps_backup;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
