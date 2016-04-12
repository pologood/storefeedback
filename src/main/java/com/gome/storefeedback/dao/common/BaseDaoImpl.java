package com.gome.storefeedback.dao.common;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PageMysqlCreator;
import com.gome.storefeedback.web.page.page.PaginationParameters;


/**
 * 数据库公用dao接口实现类
 * @author Zhang.Mingji
 * @date 2014-1-22上午9:01:21
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class BaseDaoImpl<T,E> extends SqlSessionDaoSupport{

    // 常用NameSpace操作
    private enum SqlIdSub {
        insert, update, delete, list, findByID, findByPage
    }

    // namespace与id之间连接符
    public static final String NAMESPACE_ID_LINK_TAG = ".";
    // Mapper 前缀
    public static final String MAPPER_NAMESPACE_SUFFIX = "Mapper";

    /**
     * 根据实体名得到Namespace 名称
     * 
     * @param e
     * @return
     */
    private String getNamespaceNameByClass(E e) {
        String nameSpaceName = "";
        nameSpaceName = e.getClass().getSimpleName();
        return this.MAPPER_NAMESPACE_SUFFIX + this.NAMESPACE_ID_LINK_TAG + nameSpaceName;
    }

    /**
     * 通过实体进行查询
     * 
     * @param paramName
     * @param e
     * @return
     * @throws BaseException
     */
    public T findByID(String paramName, Object object) throws BaseException {
        try {
            return (T) this.getSqlSession().selectOne(paramName, object);
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 分页查询
     * 
     * @param paramName
     * @param paramValue
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<T> findByPage(String paramName, Object paramValue, PaginationParameters paginationParameters)
            throws BaseException {
        try {
            int totalDataSize = findTotalDataSize(paramName, paramValue, paginationParameters);
            if(paginationParameters.getAlreadyLoadCount() >= totalDataSize ){
            	return null;
            }
            List dataList = getDataListByPage(paramName, paramValue, paginationParameters, totalDataSize);
            Page<T> page = new Page<T>(paginationParameters.getCurrentPageNumber(),
                    paginationParameters.getPageMaxSize(), dataList, totalDataSize, paginationParameters.get_page_div());
            return page;
        } catch (Exception e) {
            throw new BaseException(e);
        }

    }

    /**
     * 得到分页数据
     * 
     * @param paramName
     * @param paramValue
     * @param paginationParameters
     * @return
     */
    public List<T> getDataListByPage(String paramName, Object paramValue, PaginationParameters paginationParameters,
            int totalDataSize) throws BaseException {
        try {
            // 得到第一个参数
            int firstPageValue = PageMysqlCreator.getFirstPageValue(paginationParameters, totalDataSize);
            // 得到第二个参数
            int secondValue = PageMysqlCreator.getSeondPageValue(firstPageValue, totalDataSize,
                    paginationParameters.getPageMaxSize());
            return getDataListByPage(paramName, paramValue, paginationParameters, firstPageValue, secondValue);
            // this.getSqlSession().selectList(paramName, paramValue, new
            // ESNRowBounds(firstPageValue,
            // secondValue,paginationParameters.getOrderKey(),paginationParameters.getOrderType()));
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 查询翻页信息：（支持行间距选择）
     * 
     * @param paramName
     * @param paramValue
     * @param paginationParameters
     * @param firstPageValue
     * @param secondValue
     * @return
     * @throws BaseException
     */
    public List<T> getDataListByPage(String paramName, Object paramValue, PaginationParameters paginationParameters,
            int firstPageValue, int secondValue) throws BaseException {
        try {
            return this.getSqlSession().selectList(
                    paramName,
                    paramValue,
                    new BASRowBounds(firstPageValue, secondValue, paginationParameters.getOrderKey(),
                            paginationParameters.getOrderType(), paginationParameters.getOrderBy()));
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 得到总共含有的记录数
     * 
     * @param paramName
     * @param paramValue
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public int findTotalDataSize(String paramName, Object paramValue, PaginationParameters paginationParameters)
            throws BaseException {

        try {
            List countDataList = this.getSqlSession().selectList(
                    paramName,
                    paramValue,
                    new BASRowBounds(PageMysqlCreator.FIND_PAGE_SIZE_TAG, PageMysqlCreator.FIND_PAGE_SIZE_TAG,
                            paginationParameters.getOrderKey(), paginationParameters.getOrderType(),
                            paginationParameters.getOrderBy()));
            if(countDataList.size() == 0) {
                return 0;
            }else {
                int count_num = (Integer) countDataList.get(0);
                return count_num;
            }
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 插入数据库
     * 
     * @param paramName
     * @param e
     * @return
     * @throws BaseException
     */
    public int insert(String paramName, Object object) throws BaseException {
        try {
            return this.getSqlSession().insert(paramName, object);
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 通过实体进行查询；
     * 
     * @param paramName
     * @param e
     * @return
     * @throws BaseException
     */
    public List<T> findByParam(String paramName, Object object) throws BaseException {
        try {
            return this.getSqlSession().selectList(paramName, object);
        } catch (Exception e) {
            throw new BaseException(e);
        }

    }

    /**
     * 更新
     * 
     * @param paramName
     * @param e
     * @return
     * @throws BaseException
     */
    public int update(String paramName, Object object) throws BaseException {
        try {
            return this.getSqlSession().update(paramName, object);
        } catch (Exception e) {
            throw new BaseException(e);
        }
        

    }

    /**
     * 删除实体
     * 
     * @param paramName
     * @param e
     * @return
     * @throws BaseException
     */
    public int delete(String paramName, Object object) throws BaseException {
        try {
            return this.getSqlSession().delete(paramName, object);
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 通过实体唯一标示得到实体 {@inheritDoc}
     */
    public T findEntityById(E e) throws BaseException {

        try {
            String paramName = getNamespaceNameByClass(e) + this.NAMESPACE_ID_LINK_TAG + SqlIdSub.findByID.toString();
            return findByID(paramName, e);
        } catch (Exception ex) {
            throw new BaseException(ex);
        }

    }

    /**
     * 分页查询 {@inheritDoc}
     */
    public Page<T> findEntityByPage(E e, PaginationParameters paginationParameters) throws BaseException {
        try {
            String paramName = getNamespaceNameByClass(e) + this.NAMESPACE_ID_LINK_TAG + SqlIdSub.findByPage;
            return (Page<T>) findByPage(paramName, e, paginationParameters);
        } catch (Exception ex) {
            throw new BaseException(ex);
        }
    }

    public List<T> findByEntity(E e) throws BaseException {
        try {
            String paramName = getNamespaceNameByClass(e) + this.NAMESPACE_ID_LINK_TAG + SqlIdSub.list;
            return (List<T>) findByParam(paramName, e);
        } catch (Exception ex) {
            throw new BaseException(ex);
        }
    }

    public int deleteByEntity(E e) throws BaseException {
        try {
            String paramName = getNamespaceNameByClass(e) + this.NAMESPACE_ID_LINK_TAG + SqlIdSub.delete;
            return delete(paramName, e);
        } catch (Exception ex) {
            throw new BaseException(ex);
        }
    }

    public int insertEntity(E e) throws BaseException {
        try {
            String paramName = getNamespaceNameByClass(e) + this.NAMESPACE_ID_LINK_TAG + SqlIdSub.insert;
            return insert(paramName, e);
        } catch (Exception ex) {
            throw new BaseException(ex);
        }
    }

    public int updateEntityById(E e) throws BaseException {
        try {
            String paramName = getNamespaceNameByClass(e) + this.NAMESPACE_ID_LINK_TAG + SqlIdSub.update;
            return update(paramName, e);
        } catch (Exception ex) {
            throw new BaseException(ex);
        }
    }
    
}
