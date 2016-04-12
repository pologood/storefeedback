package com.gome.storefeedback.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.constant.SapFeedbackConstant;
import com.gome.storefeedback.dao.SapFeedbackDicaiDao;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedbackDicai;
import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.SapFeedbackPushSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.FeedbackPushService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackPushService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月29日上午11:12:30
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("feedbackPushService")
public class FeedbackPushServiceImpl implements FeedbackPushService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackPushServiceImpl.class);
    private static final String GMZB = "GMZB";
    private static final Integer IS_GMZB_YES = 1;
    private static final Integer IS_GMZB_NO = 0;

    private static final int SAP_FEEDBACK_PUSH_YES = 1;
    @Resource
    private SapFeedbackService sapFeedbackService;
    @Resource
    private SapFeedbackDicaiDao sapFeedbackDicaiDao;
    @Resource
    private FeedbackPushCategoryConfigService feedbackPushCategoryConfigService;
    @Resource
    private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;
    @Resource
    private EmployeeRemoteService employeeRemoteService;
    @Resource
    private SapFeedbackHandlerService sapFeedbackHandlerService;
    @Resource
    private SapFeedbackPushSender sapFeedbackPushSender;
    @Resource
    private SapFeedbackPushService sapFeedbackPushService;

    /*
     * @Override public void pushByPosition(Date pushDate) throws BaseException
     * { String pushKey = UUIDUtil.getUUID(); logger.info("---------> id:" +
     * pushKey + " 推送缺断货消息 [开始]"); List<FeedbackPushCategoryConfig> categoryList
     * = feedbackPushCategoryConfigService.find(null); if (categoryList != null
     * && categoryList.size() > 0) { logger.info("---------> id:" + pushKey +
     * " 推送缺断货消息 [地采]"); pushByDiCai(pushDate, categoryList, pushKey);
     * logger.info("---------> id:" + pushKey + " 推送缺断货消息 [地采] [成功]");
     * logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采]");
     * pushByJiCai(pushDate, categoryList, pushKey);
     * logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采] [成功]"); } else {
     * logger.info("---------> id:" + pushKey +
     * " 推送缺断货消息 [结束] : { 无可推送的缺断货信息 }"); } logger.info("---------> id:" +
     * pushKey + " 推送缺断货消息 [结束]"); }
     */

    /*
     * @Override public void pushByPosition(Date pushDate) throws BaseException
     * {
     * 
     * // 推送总部业务//找到品类//找到职务//查询出人来//查询出要推送的缺断货记录//推送消息及消息入库//推送标志入库
     * pushGMZBJicai(pushDate);
     * 
     * // 推送分部业务 pushGMFBDicai(pushDate); }
     */

    @Override
    public void pushByPosition(Date pushDate) throws BaseException {
        // 推送总部业务//找到品类//找到职务//查询出人来//查询出要推送的缺断货记录//推送消息及消息入库//推送标志入库
        pushGMZBJicaiBusiness(pushDate);
        // 推送分部业务
        pushGMFBDicaiBusiness(pushDate);
    }

    private void pushGMFBDicaiBusiness(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);
        if (categoryList != null && categoryList.size() > 0) {
            Date now = null;
            try {
                now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(pushDate) + " 00:00:00");
            } catch (ParseException e1) {
                // 日期格式化错误
                throw new BaseException(e1);
            }
            for (FeedbackPushCategoryConfig category : categoryList) {
                Map<String, Object> positionMap = new HashMap<String, Object>();
                positionMap.put("roleId", SapFeedbackConstant.GMFB_BUSINESS);
                positionMap.put("categoryCode", category.getCategoryCode());
                List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                        .findPushPosition(positionMap);
                if (positionList != null && positionList.size() > 0) {
                    Map<String, List<FeedbackPushCategoryPositionConfig>> targetOrgList = new HashMap<String, List<FeedbackPushCategoryPositionConfig>>();
                    for (FeedbackPushCategoryPositionConfig p : positionList) {
                        String key = p.getOrgNumber();//推送部门
                        List<FeedbackPushCategoryPositionConfig> tmp = null;
                        if (targetOrgList.containsKey(key)) {
                            tmp = targetOrgList.get(key);
                            tmp.add(p);
                            targetOrgList.put(key, tmp);
                        } else {
                            tmp = new ArrayList<FeedbackPushCategoryPositionConfig>();
                            tmp.add(p);
                            targetOrgList.put(key, tmp);//每个org_name 对应的FeedbackPushCategoryPositionConfig集合
                        }
                    }
                    for (String targetOrg : targetOrgList.keySet()) {//每个销售组织
                        List<FeedbackPushCategoryPositionConfig> pushList = targetOrgList.get(targetOrg);
                        Map<Integer, Set<String>> srcOrgMap = new HashMap<Integer, Set<String>>();
                        if (pushList != null && pushList.size() > 0) {
                            for (FeedbackPushCategoryPositionConfig p : pushList) {
                                Set<String> set = null;
                                if (srcOrgMap.containsKey(p.getSrcOrgType())) {
                                    set = srcOrgMap.get(p.getSrcOrgType());
                                } else {
                                    set = new HashSet<String>();
                                }
                                set.add(p.getSrcOrgNumber());
                                srcOrgMap.put(p.getSrcOrgType(), set);//每个src_org_type下对应的 src_org_number
                            }
                        }
                        Map<String, Object> pmap = new HashMap<String, Object>();
                        pmap.put("dataDate", now);
                        for (Integer key : srcOrgMap.keySet()) {
                            if (key == 1) {
                                pmap.put("srcOrgType1", srcOrgMap.get(key));
                            } else if (key == 2) {
                                pmap.put("srcOrgType2", srcOrgMap.get(key));
                            }
                        }
                        List<String> categorys = new ArrayList<String>();
                        categorys.add(category.getCategoryCode());
                        pmap.put("categoryCodes", categorys);

                        List pushDicaiList = sapFeedbackDicaiDao.findPushSumDiCaiByCategory(pmap);
                        if (pushDicaiList != null && pushDicaiList.size() > 0) {//判断有没有缺断货信息
                            String positionStr = "";
                            for (FeedbackPushCategoryPositionConfig p : targetOrgList.get(targetOrg)) {
                                positionStr += p.getPositionCode();
                                positionStr += ",";
                            }
                            if (positionStr.trim().length() > 0) {
                                positionStr.substring(0, positionStr.length() - 1);
                                try {
                                    List<Employee> empList = employeeRemoteService
                                            .findEmployeeByMinistrationCodeAndSaleOrg(targetOrg, positionStr, null);//销售组织编码，职务编码
                                    if (empList != null && empList.size() > 0) {
                                        List<SapFeedbackPush> sapFeedbackPushList = new ArrayList<SapFeedbackPush>();
                                        for (Employee emp : empList) {
                                            // insert handler
                                            Date pushTimeStamp = new Date();
                                            SapFeedbackPush sapFeedbackPush = new SapFeedbackPush();
                                            sapFeedbackPush.setId(UUIDUtil.getUUID());
                                            sapFeedbackPush.setPushCategory(category.getCategoryCode());
                                            sapFeedbackPush.setPushContent(DateTimeUtil.formatDate(pushDate) + " "
                                                    + category.getCategoryName() + " 有缺断货信息");
                                            sapFeedbackPush.setPushDataDate(pushDate);
                                            sapFeedbackPush.setPushEmpId(emp.getId());
                                            sapFeedbackPush.setPushEmpName(emp.getEmployeeName());
                                            sapFeedbackPush.setPushEmpNumber(emp.getEmployeeId());
                                            sapFeedbackPush.setPushOrg(targetOrg);
                                            // sapFeedbackPush.setPushPosition(pushPosition);
                                            sapFeedbackPush.setPushTime(pushTimeStamp);
                                            sapFeedbackPush.setRoleId(SapFeedbackConstant.GMFB_BUSINESS);
                                            sapFeedbackPushList.add(sapFeedbackPush);
                                            sendMessage(emp.getEmployeeId(), sapFeedbackPush.getPushContent());
                                            sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushList);
                                        }
                                    } else {
                                        // 没有可以推送的员工
                                    }
                                } catch (Exception e) {
                                    // 没有可以推送的人员
                                    throw new BaseException(e);
                                }
                            }
                        } else {
                            // 当天没有缺断货信息
                        }
                    }
                } else {
                    // 没有职位
                }
            }
        } else {
            // 没有需要推送的品类
        }
    }

    private void pushGMZBJicaiBusiness(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);//品类列表
        if (categoryList != null && categoryList.size() > 0) {
            for (FeedbackPushCategoryConfig category : categoryList) {
                Date now = null;
                try {
                    now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(pushDate) + " 00:00:00");
                } catch (ParseException e1) {
                    // 日期格式化错误
                    // return;
                    throw new BaseException(e1);
                }
                Map<String, Object> jMap = new HashMap<String, Object>();
                jMap.put("dataDate", now);
                jMap.put("categoryCode", category.getCategoryCode());
                List<SapFeedbackDicai> sapPushList = sapFeedbackDicaiDao.findPushJiCaiByCategory(jMap);//品类对应分部
                if (sapPushList != null && sapPushList.size() > 0) {
                    Map<String, Object> positionMap = new HashMap<String, Object>();

                    positionMap.put("roleId", SapFeedbackConstant.GMZB_BUSINESS);
                    positionMap.put("categoryCode", category.getCategoryCode());

                    List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                            .findPushPosition(positionMap);//品类职务
                    List<Employee> empList = null;
                    if (positionList != null && positionList.size() > 0) {
                        String positionStr = "";
                        for (FeedbackPushCategoryPositionConfig p : positionList) {
                            positionStr += p.getPositionCode();
                            positionStr += ",";
                        }
                        if (positionStr.trim().length() > 0) {
                        	positionStr = positionStr.substring(0, positionStr.length() - 1);
                            try {
                                empList = employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(
                                        SapFeedbackConstant.GMZB, positionStr, null);
                                if (empList != null && empList.size() > 0) {
                                    List<SapFeedbackPush> sapFeedbackPushList = new ArrayList<SapFeedbackPush>();
                                    for (Employee emp : empList) {
                                        // insert handler
                                        Date pushTimeStamp = new Date();
                                        SapFeedbackPush sapFeedbackPush = new SapFeedbackPush();
                                        sapFeedbackPush.setId(UUIDUtil.getUUID());
                                        sapFeedbackPush.setPushCategory(category.getCategoryCode());
                                        sapFeedbackPush.setPushContent(DateTimeUtil.formatDate(pushDate) + " "
                                                + category.getCategoryName() + " 有缺断货信息");
                                        sapFeedbackPush.setPushDataDate(pushDate);
                                        sapFeedbackPush.setPushEmpId(emp.getId());
                                        sapFeedbackPush.setPushEmpName(emp.getEmployeeName());
                                        sapFeedbackPush.setPushEmpNumber(emp.getEmployeeId());
                                        sapFeedbackPush.setPushOrg(SapFeedbackConstant.GMZB);
                                        // sapFeedbackPush.setPushPosition(pushPosition);
                                        sapFeedbackPush.setPushTime(pushTimeStamp);
                                        sapFeedbackPush.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
                                        sapFeedbackPushList.add(sapFeedbackPush);
                                        sendMessage(emp.getEmployeeId(), sapFeedbackPush.getPushContent());
                                        sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushList);
                                    }
                                } else {
                                    // 没有要推送的人员
                                }
                            } catch (Exception e) {
                                // 没有可以推送的职位
                                return;
                            }
                        }
                    } else {
                        // 没有要推送的集采业务
                    }

                } else {
                    // 当天 该品类没有缺断货信息
                }
            }
        } else {
            // 没有需要推送的品类
        }
    }

    private void pushGMFBDicai(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);
        if (categoryList == null || categoryList.size() < 1) {
            // 没有需要推送的品类
            return;
        }
        for (FeedbackPushCategoryConfig category : categoryList) {

            Map<String, Object> positionMap = new HashMap<String, Object>();

            positionMap.put("roleId", SapFeedbackConstant.GMFB_BUSINESS);
            positionMap.put("categoryCode", category.getCategoryCode());

            List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                    .findPushPosition(positionMap);

            if (positionList != null && positionList.size() > 0) {
                Map<String, List<FeedbackPushCategoryPositionConfig>> targetOrgList = new HashMap<String,

                List<FeedbackPushCategoryPositionConfig>>();
                for (FeedbackPushCategoryPositionConfig p : positionList) {
                    String key = p.getOrgName();
                    List<FeedbackPushCategoryPositionConfig> tmp = null;
                    if (targetOrgList.containsKey(key)) {
                        tmp = targetOrgList.get(key);
                        tmp.add(p);
                        targetOrgList.put(key, tmp);
                    } else {
                        tmp = new ArrayList<FeedbackPushCategoryPositionConfig>();
                        tmp.add(p);
                        targetOrgList.put(key, tmp);
                    }
                }

                if (targetOrgList != null) {
                    for (String targetOrg : targetOrgList.keySet()) {

                        List<Employee> empList = null;
                        String positionStr = "";
                        for (FeedbackPushCategoryPositionConfig p : targetOrgList.get(targetOrg)) {
                            positionStr += p.getPositionCode();
                            positionStr += ",";
                        }
                        if (positionStr.trim().length() > 0) {
                            positionStr.substring(0, positionStr.length() - 1);
                            try {
                                empList = employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(targetOrg,
                                        positionStr, null);
                            } catch (Exception e) {
                                // 没有可以推送的职位
                                return;
                            }
                        }
                        if (empList == null || empList.size() < 1) {
                            // 没有可以推送的员工
                            return;
                        }
                        Date now;
                        try {
                            now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.getCurrentDate() + " 00:00:00");
                        } catch (ParseException e) {
                            return;
                        }
                        Map<String, Object> dMap = new HashMap<String, Object>();
                        dMap.put("dataDate", now);

                        // 这里需要处理关系

                        dMap.put("categoryCode", category.getCategoryCode());
                        dMap.put("srcOrgType", new ArrayList<String>());
                        dMap.put("srcOrgList", new ArrayList<String>());

                        List<SapFeedbackDicai> sapPushList = sapFeedbackDicaiDao.findPushDiCaiByCategory(dMap);
                        if (sapPushList != null && sapPushList.size() > 0) {
                            List<SapFeedbackPush> sapFeedbackPushList = new ArrayList<SapFeedbackPush>();
                            for (Employee emp : empList) {
                                // insert handler
                                Date pushTimeStamp = new Date();
                                SapFeedbackPush sapFeedbackPush = new SapFeedbackPush();
                                sapFeedbackPush.setId(UUIDUtil.getUUID());
                                sapFeedbackPush.setPushCategory(category.getCategoryCode());
                                sapFeedbackPush.setPushContent(DateTimeUtil.formatDate(pushDate) + " "
                                        + category.getCategoryName() + " 有 " + sapPushList.size() + "条缺断货信息");
                                sapFeedbackPush.setPushDataDate(pushDate);
                                sapFeedbackPush.setPushEmpId(emp.getId());
                                sapFeedbackPush.setPushEmpName(emp.getEmployeeName());
                                sapFeedbackPush.setPushEmpNumber(emp.getEmployeeId());
                                sapFeedbackPush.setPushOrg(SapFeedbackConstant.GMZB);
                                // sapFeedbackPush.setPushPosition(pushPosition);
                                sapFeedbackPush.setPushTime(pushTimeStamp);
                                sapFeedbackPush.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
                                sapFeedbackPushList.add(sapFeedbackPush);
                                sendMessage(emp.getEmployeeId(), //
                                        sapFeedbackPush.getPushContent());
                                sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushList);

                                List<SapFeedbackHandler> sapFeedbackHandlerList = new ArrayList<SapFeedbackHandler>();
                                for (SapFeedbackDicai sapPush : sapPushList) {
                                    SapFeedbackHandler sapFeedbackHandler = new SapFeedbackHandler();
                                    sapFeedbackHandler.setRequest(sapPush.getRequest());
                                    sapFeedbackHandler.setDatapakid(sapPush.getDatapakid());
                                    sapFeedbackHandler.setRecord(sapPush.getRecord());
                                    sapFeedbackHandler.setPushTime(pushTimeStamp);
                                    sapFeedbackHandler.setIsHandler(1);
                                    sapFeedbackHandler.setId(sapPush.findPK());
                                    sapFeedbackHandlerList.add(sapFeedbackHandler);
                                }
                                sapFeedbackHandlerService.batchSapFeedbackHandler(sapFeedbackHandlerList);
                            }
                        } else {
                            // 没有可以推送的缺断货记录
                            return;
                        }
                    }
                } else {
                    // 无推送职位
                    return;
                }
            } else {
                // 没有可以推送的职位
                return;
            }

        }
    }

    private void pushGMZBJicai(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);
        if (categoryList == null || categoryList.size() < 1) {
            // 没有需要推送的品类
            return;
        }
        for (FeedbackPushCategoryConfig category : categoryList) {

            Map<String, Object> positionMap = new HashMap<String, Object>();

            positionMap.put("roleId", SapFeedbackConstant.GMZB_BUSINESS);
            positionMap.put("categoryCode", category.getCategoryCode());
            positionMap.put("orgNumber", SapFeedbackConstant.GMZB);
            positionMap.put("isGMZB", SapFeedbackConstant.GOME_BUY_ZB_JICAI);

            List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                    .findPushPosition(positionMap);
            List<Employee> empList = null;
            if (positionList != null && positionList.size() > 0) {
                String positionStr = "";
                for (FeedbackPushCategoryPositionConfig p : positionList) {
                    positionStr += p.getPositionCode();
                    positionStr += ",";
                }
                if (positionStr.trim().length() > 0) {
                    positionStr.substring(0, positionStr.length() - 1);
                    try {
                        empList = employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(
                                SapFeedbackConstant.GMZB, positionStr, null);
                    } catch (Exception e) {
                        // 没有可以推送的职位
                        return;
                    }
                }
            } else {
                // 没有可以推送的职位
                return;
            }

            if (empList == null || empList.size() < 1) {
                // 没有可以推送的员工
                return;
            }

            Date now;
            try {
                now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.getCurrentDate() + " 00:00:00");
            } catch (ParseException e) {
                return;
            }
            Map<String, Object> jMap = new HashMap<String, Object>();
            jMap.put("dataDate", now);
            jMap.put("categoryCode", category.getCategoryCode());
            List<SapFeedbackDicai> sapPushList = sapFeedbackDicaiDao.findPushJiCaiByCategory(jMap);
            if (sapPushList != null && sapPushList.size() > 0) {
                List<SapFeedbackPush> sapFeedbackPushList = new ArrayList<SapFeedbackPush>();
                for (Employee emp : empList) {
                    // insert handler
                    Date pushTimeStamp = new Date();
                    SapFeedbackPush sapFeedbackPush = new SapFeedbackPush();
                    sapFeedbackPush.setId(UUIDUtil.getUUID());
                    sapFeedbackPush.setPushCategory(category.getCategoryCode());
                    sapFeedbackPush.setPushContent(DateTimeUtil.formatDate(pushDate) + " " + category.getCategoryName()
                            + " 有 " + sapPushList.size() + "条缺断货信息");
                    sapFeedbackPush.setPushDataDate(pushDate);
                    sapFeedbackPush.setPushEmpId(emp.getId());
                    sapFeedbackPush.setPushEmpName(emp.getEmployeeName());
                    sapFeedbackPush.setPushEmpNumber(emp.getEmployeeId());
                    sapFeedbackPush.setPushOrg(SapFeedbackConstant.GMZB);
                    // sapFeedbackPush.setPushPosition(pushPosition);
                    sapFeedbackPush.setPushTime(pushTimeStamp);
                    sapFeedbackPush.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
                    sapFeedbackPushList.add(sapFeedbackPush);
                    sendMessage(emp.getEmployeeId(), //
                            sapFeedbackPush.getPushContent());
                    sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushList);

                    List<SapFeedbackHandler> sapFeedbackHandlerList = new ArrayList<SapFeedbackHandler>();
                    for (SapFeedbackDicai sapPush : sapPushList) {
                        SapFeedbackHandler sapFeedbackHandler = new SapFeedbackHandler();
                        sapFeedbackHandler.setRequest(sapPush.getRequest());
                        sapFeedbackHandler.setDatapakid(sapPush.getDatapakid());
                        sapFeedbackHandler.setRecord(sapPush.getRecord());
                        sapFeedbackHandler.setPushTime(pushTimeStamp);
                        sapFeedbackHandler.setIsHandler(1);
                        sapFeedbackHandler.setId(sapPush.findPK());
                        sapFeedbackHandlerList.add(sapFeedbackHandler);
                    }
                    sapFeedbackHandlerService.batchSapFeedbackHandler(sapFeedbackHandlerList);
                }
            } else {
                // 没有可以推送的缺断货记录
                return;
            }
        }
    }

    public void pushGMZBBusinessByParams() throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);
        for (FeedbackPushCategoryConfig category : categoryList) {
            pushByParams(SapFeedbackConstant.GMZB_BUSINESS,//
                    category.getCategoryCode(),//
                    SapFeedbackConstant.GOME_BUYTYPE_JICAI);
        }
    }

    private void pushByParams(int roleId, String category, String buyType) throws BaseException {
        Map<String, Object> positionMap = new HashMap<String, Object>();

        List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                .findPushPosition(positionMap);
    }

    public void pushByParams(int roleId, String category, String buyType, String srcOrg) {

        // roleId,
        // categoryCode,
        // srcOrgNumber

    }

    private void pushByJiCai(Date pushDate, List<FeedbackPushCategoryConfig> categoryList, String pushKey)
            throws BaseException {
        for (FeedbackPushCategoryConfig category : categoryList) {
            pushJiCaiByCategory(pushDate, pushKey, category);
        }
    }

    private void pushByDiCai(Date pushDate, List<FeedbackPushCategoryConfig> categoryList, String pushKey)
            throws BaseException {
        for (FeedbackPushCategoryConfig category : categoryList) {
            pushDiCaiByCategory(pushDate, pushKey, category);
        }
    }

    private void pushJiCaiByCategory(Date pushDate, String pushKey, FeedbackPushCategoryConfig category)
            throws BaseException {
        logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采] [" + category.getCategoryName() + "] [开始]");
        // 1.查询【集采】【某品类】【某天】的缺断货信息
        // 1.1组织查询条件 inMap
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("categoryCode", category.getCategoryCode());
        inMap.put("dataDate", pushDate);
        // 1.2执行查询
        List<SapFeedbackDicai> sapFeedbackPKs = sapFeedbackDicaiDao.findPushJiCaiByCategory(inMap);
        // 1.3获取查询结果条数
        int pushNum = sapFeedbackPKs.size();
        logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采] [" + category.getCategoryName() + "] 查询到的缺断货信息条数为:"
                + pushNum);

        // 2.查询【集采】【某品类】的推送职位
        // 2.1组织查询条件 positionMap
        Map<String, Object> positionMap = new HashMap<String, Object>();
        positionMap.put("isGMZB", IS_GMZB_YES);
        // positionMap.put("GMZB", GMZB);
        positionMap.put("categoryCode", category.getCategoryCode());
        List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                .findPushPosition(positionMap);
        logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采] [" + category.getCategoryName() + "] 查询到的职位信息:"
                + JsonUtil.javaObjectToJsonString(positionList));
        if (positionList != null && positionList.size() > 0) {

            for (FeedbackPushCategoryPositionConfig p : positionList) {
                List<Employee> empList = null;
                try {
                    empList = getEmpInfo(GMZB, p);
                } catch (Exception e) {

                }
                if (empList != null) {
                    pushJicaiMsg(pushDate, category, empList, sapFeedbackPKs, p);
                }
            }

            // String positionStr = "";
            // for (FeedbackPushCategoryPositionConfig p : positionList) {
            // positionStr += p.getPositionCode();
            // positionStr += ",";
            // }
            // if (positionStr.trim().length() > 0) {
            // positionStr.substring(0, positionStr.length() - 1);
            // List<Employee> empList =
            // employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(GMZB,
            // positionStr, null);
            // logger.debug("---------> id:" + pushKey + " 推送缺断货消息 [集采] [" +
            // category.getCategoryName()
            // + "] 查询到的人员信息:" + JsonUtil.javaObjectToJsonString(empList));
            // if (empList != null) {
            // pushJicaiMsg(pushDate, category, empList, sapFeedbackPKs);
            // }
            // }
        } else {
            logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采] [" + category.getCategoryName()
                    + "] [结束] : 没有要推送的职位");
        }
        logger.info("---------> id:" + pushKey + " 推送缺断货消息 [集采] [" + category.getCategoryName() + "] [结束]");
    }

    private void pushDiCaiByCategory(Date pushDate, String pushKey, FeedbackPushCategoryConfig category)
            throws BaseException {
        logger.info("---------> id:" + pushKey + " 推送缺断货消息 [地采] [" + category.getCategoryName() + "] [开始]");
        // 1.查询【地采】【某品类】【某日期】要推送的缺断货信息
        // 1.1 构造查询条件
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("categoryCode", category.getCategoryCode());
        inMap.put("dataDate", pushDate);
        // 1.2执行查询
        List<SapFeedbackDicai> sapFeedbackDicais = sapFeedbackDicaiDao.findPushDiCaiByCategory(inMap);

        Map<String, List<SapFeedbackDicai>> pushDeptCategory = new HashMap<String, List<SapFeedbackDicai>>();

        for (SapFeedbackDicai sapFeedbackDicai : sapFeedbackDicais) {
            String key = sapFeedbackDicai.getSecondDivisionCode();
            if (sapFeedbackDicai.getSecondDivisionCode() == null) {
                key = sapFeedbackDicai.getDivisionCode();
            }
            List<SapFeedbackDicai> tmp = null;
            if (pushDeptCategory.containsKey(key)) {
                tmp = pushDeptCategory.get(key);
                tmp.add(sapFeedbackDicai);
                pushDeptCategory.put(key, tmp);
            } else {
                tmp = new ArrayList<SapFeedbackDicai>();
                tmp.add(sapFeedbackDicai);
                pushDeptCategory.put(key, tmp);
            }
        }

        for (String orgNumber : pushDeptCategory.keySet()) {
            Map<String, Object> positionMap = new HashMap<String, Object>();
            positionMap.put("isGMZB", IS_GMZB_NO);
            positionMap.put("orgNumber", orgNumber);
            positionMap.put("categoryCode", category.getCategoryCode());
            List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                    .findPushPosition(positionMap);
            if (positionList != null && positionList.size() > 0) {
                for (FeedbackPushCategoryPositionConfig p : positionList) {
                    List<Employee> empList = null;
                    try {
                        empList = getEmpInfo(orgNumber, p);
                    } catch (Exception e) {

                    }

                    if (empList != null && empList.size() > 0) {
                        pushSapFeedbackMsg(pushDate, category, empList, pushDeptCategory.get(orgNumber), p);
                    }
                }
                // String positionStr = "";
                // for (FeedbackPushCategoryPositionConfig p : positionList) {
                // positionStr += p.getPositionCode();
                // positionStr += ",";
                // }
                // if (positionStr.trim().length() > 0) {
                // positionStr.substring(0, positionStr.length() - 1);
                // List<Employee> empList =
                // employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(orgNumber,
                // positionStr, null);
                //
                // pushSapFeedbackMsg(pushDate, category, empList,
                // pushDeptCategory.get(orgNumber));
                //
                // }
            } else {
                logger.info("---------> id:" + pushKey + " 推送缺断货消息 [地采] [" + category.getCategoryName()
                        + "] [结束] : 没有要推送的职位");
            }
        }
        logger.info("---------> id:" + pushKey + " 推送缺断货消息 [地采] [" + category.getCategoryName() + "] [结束]");
    }

    private void pushSapFeedbackMsg(Date pushDate, FeedbackPushCategoryConfig category, List<Employee> empList,
            List<SapFeedbackDicai> sapFeedbackDicaiList, FeedbackPushCategoryPositionConfig p) throws BaseException {
        for (Employee emp : empList) {
            sendMessage(
                    emp.getEmployeeId(), //
                    DateTimeUtil.formatDate(pushDate) + " " + category.getCategoryName() + " 有 "
                            + sapFeedbackDicaiList.size() + "条缺断货信息");
            List<SapFeedbackPush> sapFeedbackPushs = new ArrayList<SapFeedbackPush>();
            for (SapFeedbackDicai sapFeedbackDicai : sapFeedbackDicaiList) {
                SapFeedbackHandler sapFeedbackHandler = new SapFeedbackHandler();
                sapFeedbackHandler.setRequest(sapFeedbackDicai.getRequest());
                sapFeedbackHandler.setDatapakid(sapFeedbackDicai.getDatapakid());
                sapFeedbackHandler.setRecord(sapFeedbackDicai.getRecord());
                sapFeedbackHandler.setIsPush(SAP_FEEDBACK_PUSH_YES);
                sapFeedbackHandler.setPushTime(new Date());
                sapFeedbackHandler.buildPK();
                sapFeedbackHandlerService.insertSapFeedbackHandler(sapFeedbackHandler);
                logger.info("---------> 缺断货信息推送保存推送状态和推送时间:" + JsonUtil.javaObjectToJsonString(sapFeedbackHandler));
                SapFeedbackPush sapFeedbackPush = new SapFeedbackPush();
                sapFeedbackPush.setId(sapFeedbackHandler.getId());
                sapFeedbackPush.setPushOrg(p.getOrgNumber());
                sapFeedbackPush.setPushPosition(p.getPositionCode());
                sapFeedbackPush.setPushEmpId(emp.getId());
                sapFeedbackPush.setPushEmpNumber(emp.getEmployeeId());
                sapFeedbackPush.setPushEmpName(emp.getEmployeeName());
                sapFeedbackPush.setPushTime(pushDate);
                sapFeedbackPush.setRoleId(p.getRoleId());
                sapFeedbackPushs.add(sapFeedbackPush);
            }

            if (sapFeedbackPushs != null) {
                sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushs);
            }
            logger.info("---------> 缺断货信息推送保存推送人:" + JsonUtil.javaObjectToJsonString(sapFeedbackPushs));
        }
    }

    private void sendMessage(String empId, String content) throws BaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", "10000");
        map.put("employeeId", empId);
        map.put("title", BusinessGlossary.INFORM_TITLE_FEEDBACK);
        map.put("content", content);
        map.put("type", BusinessGlossary.INFORM_TYPE_FEEDBACK);
        map.put("sender", BusinessGlossary.APP_STOREFEEDBACK);
        String msg = JsonUtil.javaObjectToJsonString(map);
        sapFeedbackPushSender.send(msg);
        logger.debug("---------> 缺断货信息推送:" + msg);
    }

    private void pushJicaiMsg(Date pushDate, FeedbackPushCategoryConfig category, List<Employee> empList,
            List<SapFeedbackDicai> deptSapFeedbackDicaiList, FeedbackPushCategoryPositionConfig p) throws BaseException {
        for (Employee emp : empList) {
            List<SapFeedbackPush> sapFeedbackPushs = new ArrayList<SapFeedbackPush>();
            for (SapFeedbackDicai sapFeedback : deptSapFeedbackDicaiList) {
                SapFeedbackHandler sapFeedbackHandler = new SapFeedbackHandler();
                sapFeedbackHandler.setRequest(sapFeedback.getRequest());
                sapFeedbackHandler.setDatapakid(sapFeedback.getDatapakid());
                sapFeedbackHandler.setRecord(sapFeedback.getRecord());
                sapFeedbackHandler.setIsPush(SAP_FEEDBACK_PUSH_YES);
                sapFeedbackHandler.setPushTime(new Date());
                sapFeedbackHandler.setId(sapFeedback.findPK());
                sapFeedbackHandlerService.insertSapFeedbackHandler(sapFeedbackHandler);
                logger.debug("---------> 缺断货信息推送保存推送状态和推送时间:" + JsonUtil.javaObjectToJsonString(sapFeedbackHandler));
                SapFeedbackPush sapFeedbackPush = new SapFeedbackPush();
                sapFeedbackPush.setId(sapFeedbackHandler.getId());
                sapFeedbackPush.setPushOrg(GMZB);
                // sapFeedbackPush.setPushPosition("");
                sapFeedbackPush.setPushEmpId(emp.getId());
                sapFeedbackPush.setPushEmpNumber(emp.getEmployeeId());
                sapFeedbackPush.setPushEmpName(emp.getEmployeeName());
                sapFeedbackPush.setPushTime(pushDate);
                // sapFeedbackPush.setRoleId(roleId);
                sapFeedbackPushs.add(sapFeedbackPush);
            }
            if (sapFeedbackPushs != null) {
                sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushs);
            }
            logger.debug("---------> 缺断货信息推送保存推送人:" + JsonUtil.javaObjectToJsonString(sapFeedbackPushs));

            sendMessage(emp.getEmployeeId(), //
                    DateTimeUtil.formatDate(pushDate) + " " + category.getCategoryName() + " 有 "
                            + deptSapFeedbackDicaiList.size() + "条缺断货信息");
        }
    }

    private List<Employee> getEmpInfo(String orgNumber, FeedbackPushCategoryPositionConfig p) throws BaseException {
        List<Employee> empList;
        empList = employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(orgNumber, p.getPositionCode(), null);
        return empList;
    }

}
