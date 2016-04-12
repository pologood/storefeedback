package com.gome.storefeedback.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.Division;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.entity.Region;
import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.entity.SecondDivision;
import com.gome.storefeedback.entity.Store;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.DataBackSender;
import com.gome.storefeedback.service.DivisionService;
import com.gome.storefeedback.service.GoodsBrandService;
import com.gome.storefeedback.service.GoodsCategoryService;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.service.RegionService;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.service.SecondDivisionService;
import com.gome.storefeedback.service.StoreService;
import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;


/**
 * 缺断货反馈数据接口
 * @author Zhang.Jingang
 * @date 2015年2月28日下午12:12:16
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/ecc")
public class ECCApiController {
    /** 大区 */
    private RegionService regionService;
    /** 分部大区对照 */
    private DivisionService divisionService;
    /** 一二级分部对照表 */
    private SecondDivisionService sencondDivisionService;
    /** 门店信息 */
    private StoreService storeService;
    /** 商品 */
    private GoodsService goodsService;
    /** 品牌 */
    private GoodsBrandService goodsBrandService;
    /** 商品品类  */
    private GoodsCategoryService goodsCategoryService;    
    /** 采购订单数据  */
    private SapOrderService sapOrderService;
    
    private DataBackSender dataBackSender;
    
    public DataBackSender getDataBackSender() {
        return dataBackSender;
    }

    @Autowired
    public void setDataBackSender(@Qualifier("dataBackSender")DataBackSender dataBackSender) {
        this.dataBackSender = dataBackSender;
    }

    @Autowired
    public void setRegionService(@Qualifier("regionService") RegionService regionService) {
        this.regionService = regionService;
    }

    @Autowired
    public void setDivisionService(@Qualifier("divisionService") DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @Autowired
    public void setSencondDivisionService(
            @Qualifier("secondDivisionService") SecondDivisionService sencondDivisionService) {
        this.sencondDivisionService = sencondDivisionService;
    }

    @Autowired
    public void setStoreService(@Qualifier("storeService") StoreService storeService) {
        this.storeService = storeService;
    }
    
    @Autowired
    public void setGoodsService(@Qualifier("goodsService") GoodsService goodsService) {
        this.goodsService = goodsService;
    }
 
    @Autowired
    public void setGoodsBrandService(@Qualifier("goodsBrandService") GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    @Autowired
    public void setGoodsCategoryService(@Qualifier("goodsCategoryService") GoodsCategoryService goodsCategoryService) {
        this.goodsCategoryService = goodsCategoryService;
    }
    
    @Autowired
    public void setSapOrderService(@Qualifier("sapOrderService") SapOrderService sapOrderService) {
        this.sapOrderService = sapOrderService;
    }

    public RegionService getRegionService() {
        return regionService;
    }

    public DivisionService getDivisionService() {
        return divisionService;
    }

    public SecondDivisionService getSencondDivisionService() {
        return sencondDivisionService;
    }

    public StoreService getStoreService() {
        return storeService;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    public GoodsCategoryService getGoodsCategoryService() {
        return goodsCategoryService;
    }
    
    public SapOrderService getSapOrderService() {
        return sapOrderService;
    }

    @RequestMapping(value = "/saveXML", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    void saveXML(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        DataBack dataBack = new DataBack();
        try {
            xmlHandler(request, dataBack);
            System.out.println("xmlHandler excute---------------------");
            try {
                dataBack.setDetailResult(DataBack.DETAILRESULT_SUCCESS);
                // DataBackUtil.response2XML(dataBack);
            } catch (Exception e) {
                dataBack.setDetailResult(DataBack.DETAILRESULT_FAILURE);
                dataBack.addDetalisItem("499", "回写数据失败");
                System.err.println("ERROR：Region 保存数据成功 回写反馈失败。 Location:{InterfaceID:"
                        + dataBack.getDetailInterfaceO() + ",MessageId:" + dataBack.getDetailMessageO() + "}");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            dataBack.setDetailResult("499");
            dataBack.addDetalisItem("failure", "HTTP错误:获取输入流是错误");
        } catch (DocumentException e) {
            // xml 解析错误
            dataBack.setDetailResult(DataBack.DETAILRESULT_FAILURE);
            dataBack.addDetalisItem("499", "xml 错误");
            e.printStackTrace();
        } catch (BaseException e) {
            // 数据入库失败 回滚数据
            dataBack.setDetailResult(DataBack.DETAILRESULT_FAILURE);
            dataBack.addDetalisItem("599", "数据写入失败");
            e.printStackTrace();
            throw new BaseException();
        } finally {
            this.dataBackSender.send(JsonUtil.javaObjectToJsonString(dataBack));
        }
    }

    /**
     * 解析xml文件 并根据Interface_id分模块保存
     * 
     * @param request
     * @param dataBack
     * @throws IOException
     * @throws DocumentException
     * @throws BaseException
     */
    private void xmlHandler(HttpServletRequest request, DataBack dataBack) throws IOException, DocumentException,
            BaseException {
        InputStream is = null;
        try {
            is = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            FileOutputStream os = null;
            XMLWriter writer = null;
            try {
                // System.out.println("=Document Start==============");
                String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                String fileName = date + "_" + UUIDUtil.getUUID() + ".xml";
                System.out.println("fileName:" + fileName);
                File file = new File(fileName);
                if (!file.exists()) {
                    boolean newfilename = file.createNewFile();
                    System.out.println(file.getAbsolutePath());
                    System.out.println("newfilename:" + newfilename);
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    os = new FileOutputStream(file);
                    format.setEncoding("UTF-8");
                    writer = new XMLWriter(os, format);
                    writer.write(doc);
                    writer.flush();
                }
                // System.out.println("=Document End==============");
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            
            Element root = doc.getRootElement();

            Map<String, String> header = null;
            List<Node> headers = root.selectNodes("//HEADER");

            for (Node node : headers) {
                String interfaceId = node.selectSingleNode("INTERFACE_ID").getText();
                String messageId = node.selectSingleNode("MESSAGE_ID").getText();
                dataBack.setDetailMessageO(messageId);
                dataBack.setDetailInterfaceO(interfaceId);
            }
            
            System.out.println("dataBack.getDetailInterfaceO()");
            List<Node> items = root.selectNodes("//XML_DATA/*/ITEMS/ITEM");
            
            if (dataBack.getDetailInterfaceO() != null && dataBack.getDetailInterfaceO().length() > 0) {
                if ("MD112".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 大区 MD112
                    regionHandler(items);
                    System.out.println("大区 MD112"+dataBack.getDetailMessageO());
                } else if ("MD113".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 分部大区对照 MD113
                    divisionHandler(items);
                    System.out.println("大区 MD113"+dataBack.getDetailMessageO());
                } else if ("MD114".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 一二级分部对照表 MD114
                    sencondDivisionHandler(items);
                    System.out.println("大区 MD114"+dataBack.getDetailMessageO());
                } else if ("MD005".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 门店信息 MD005
                    storeHandler(items);
                    System.out.println("大区 MD005"+dataBack.getDetailMessageO());
                } else if ("MD001".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 商品 MD001
                    goodsHandler(items);
                    System.out.println("商品 MD001"+dataBack.getDetailMessageO());
                }else if ("MD103".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 品牌 MD103
                    goodsBrandHandler(items);
                    System.out.println("品牌 MD103"+dataBack.getDetailMessageO());
                }else if ("MD014".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 商品品类 MD014
                    goodsCategoryHandler(items);
                    System.out.println("商品品类 MD014"+dataBack.getDetailMessageO());
                }
                else if ("FI172".equals(dataBack.getDetailInterfaceO().trim())) {
                    // 采购订单 FI172
                    sapOrderHandler(items);
                    System.out.println("采购订单信息 FI172"+dataBack.getDetailMessageO());
                }
                else{
                    System.out.println("-------------"+dataBack.getDetailMessageO());
                    throw new DocumentException("ERROR：找不到 INTERFACE_ID="+dataBack.getDetailInterfaceO().trim());
                }
            } else {
                System.out.println("==========="+dataBack.getDetailMessageO());
                throw new DocumentException();
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * 批量保存采购订单数据
     * @param items
     * @throws BaseException
     */
    private void sapOrderHandler(List<Node> items) throws BaseException{
        List<SapOrder> result = new ArrayList<SapOrder>();
        for (Node item : items) {
            SapOrder sapOrder = new SapOrder();
            sapOrder.setOrderDate(item.selectSingleNode("BEDAT").getText());// 凭证日期
            sapOrder.setOrderId(item.selectSingleNode("EBELN").getText());// 采购凭证           
            sapOrder.setOrderContent(item.selectSingleNode("EBELP").getText());// 行项目
            sapOrder.setGoodsCode(item.selectSingleNode("MATNR").getText());// 商品
            sapOrder.setGoodsCnText(item.selectSingleNode("TXZ01").getText());// 商品描述
            if(item.selectSingleNode("MENGE").getText()!=null&&item.selectSingleNode("MENGE").getText().toString().trim().length()>0){
                sapOrder.setOrderNum(Long.parseLong(item.selectSingleNode("MENGE").getText().toString().trim()));//数量（采购订单数量）
            }
            if(item.selectSingleNode("DABMG").getText()!=null&&item.selectSingleNode("DABMG").getText().toString().trim().length()>0){
                sapOrder.setOrderToNum(Long.parseLong(item.selectSingleNode("DABMG").getText().toString().trim()));// 到货数量（已收货数量）
            }
            sapOrder.setLastReceiveDate(item.selectSingleNode("BUDAT").getText());// 最近收货日期
            sapOrder.setPlanDate(item.selectSingleNode("EINDT").getText());// 计划交货日期
            if(item.selectSingleNode("ZDABMG").getText()!=null&&item.selectSingleNode("ZDABMG").getText().toString().trim().length()>0){
                sapOrder.setOnTheRoadNum(Long.parseLong(item.selectSingleNode("ZDABMG").getText().toString().trim()));// 在途数量
            }
            sapOrder.setOrderFlag(item.selectSingleNode("ELIKZ").getText());// 交货标识
            sapOrder.setOrderType(item.selectSingleNode("BSART").getText());// 凭证类型
            sapOrder.setPlaceId(item.selectSingleNode("WERKS").getText());// 地点
            sapOrder.setPlaceName(item.selectSingleNode("NAME1").getText());// 地点描述 
            sapOrder.setStockTypeId(item.selectSingleNode("LGORT").getText());// 库位
            sapOrder.setStockTypeName(item.selectSingleNode("LGOBE").getText());//库位描述
            sapOrder.setZcqDes(item.selectSingleNode("ZCQ").getText());
            result.add(sapOrder);
        }
        this.sapOrderService.pi(result);
    }
    /**
     * 批量保存商品品类数据
     * @param items
     * @throws BaseException
     */
    private void goodsCategoryHandler(List<Node> items) throws BaseException{
        List<GoodsCategory> result = new ArrayList<GoodsCategory>();
        for (Node item : items) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setClassCode(item.selectSingleNode("CLASS").getText());// 商品编码
            goodsCategory.setClassName(item.selectSingleNode("KSCHL").getText());// 商品条码
            goodsCategory.setClassLevel(item.selectSingleNode("ZJB").getText());// 商品名称
            goodsCategory.setParentClassCode(item.selectSingleNode("CLASS2").getText());// 规格型号
            String isLeaf = item.selectSingleNode("ZMJ").getText();
            if(isLeaf != null && "X".equals(isLeaf)){
                goodsCategory.setIsLeaf(1);
            }else{
                goodsCategory.setIsLeaf(0);
            }
            // 是否末级
            goodsCategory.setDivisionCode(item.selectSingleNode("ABTNR").getText());// 品牌
            goodsCategory.setDivisionName(item.selectSingleNode("VTEXT").getText());// 延保价格下限INHBR
            goodsCategory.setUpdateFlag(item.selectSingleNode("UPDATE_FLAG").getText());// 延保价格上限
            result.add(goodsCategory);
        }
        this.goodsCategoryService.insertBatchGoodsCategory(result);
    }
    /**
     * 批量保存品牌数据
     * @param items
     * @throws BaseException
     */
    private void goodsBrandHandler(List<Node> items) throws BaseException{
        List<GoodsBrand> result = new ArrayList<GoodsBrand>();
        for (Node item : items) {
            GoodsBrand goodsBrand = new GoodsBrand();
            goodsBrand.setBrandCode(item.selectSingleNode("PRODH").getText());// 品牌
            goodsBrand.setCnText(item.selectSingleNode("VTEXT").getText());// 中文描述
            goodsBrand.setEnText(item.selectSingleNode("TEXT1").getText());// 英文描述
            goodsBrand.setBrandClass(item.selectSingleNode("ZPINPAI").getText());// 品牌类型
            goodsBrand.setUpdateFlag(item.selectSingleNode("UPDATE_FLAG").getText());// 更新标志
            
            result.add(goodsBrand);
        }
        this.goodsBrandService.insertBatchGoodsBrand(result);
    }
    /**
     * 批量保存商品数据
     * @param items
     * @throws BaseException
     */
    private void goodsHandler(List<Node> items) throws BaseException{
        List<Goods> result = new ArrayList<Goods>();
        for (Node item : items) {
            Goods goods = new Goods();
            System.out.println(item.selectSingleNode("MATNR").getText());
            goods.setGoodsCode(item.selectSingleNode("MATNR").getText());// 商品编码
            goods.setGoodsBarcode(item.selectSingleNode("EAN11").getText());// 商品条码
            goods.setGoodsName(item.selectSingleNode("MAKTX").getText());// 商品名称
            goods.setSpecificationsModel(item.selectSingleNode("GROES").getText());// 规格型号
            goods.setGoodsCategory(item.selectSingleNode("MATKL").getText());// 商品分类
            goods.setGoodsBrand(item.selectSingleNode("PRDHA").getText());// 品牌
            goods.setExtendedWarrantyPriceCap(item.selectSingleNode("INHBR").getText());// 延保价格下限INHBR
            goods.setExtendedWarrantyPriceFloor(item.selectSingleNode("INHAL").getText());// 延保价格上限
            goods.setOutputTaxRate(item.selectSingleNode("TAKLV").getText());// TAKLV销项税率
            goods.setUnitsOfMeasurement(item.selectSingleNode("MEINS").getText());// 计量单位
            goods.setUnitOfMeasureText(item.selectSingleNode("MSEHL").getText());// 度量单位文本
            goods.setLotId(item.selectSingleNode("XCHPF").getText());// 批次标识****
            goods.setWhetherBusinessGifts(Integer.parseInt(item.selectSingleNode("SFZP").getText()));
           // 是否经营赠品
            goods.setProductAttributes(item.selectSingleNode("ZZSPSXZ").getText());// 商品属性
            goods.setGoodsClass(item.selectSingleNode("MTART").getText());// 商品类型
            goods.setPlaceOfOrigin(item.selectSingleNode("ZZSPCD").getText());// 产地
            goods.setGoodsWeight(item.selectSingleNode("BRGEW").getText());// 重量（含包装)
            goods.setGoodsHeight(item.selectSingleNode("HOEHE").getText());// 高（mm）
            goods.setCategoryLevel1(item.selectSingleNode("CLASS1").getText());// 一级分类
            goods.setCategoryLevel2(item.selectSingleNode("CLASS2").getText());// 二级分类
            goods.setCategoryLevel3(item.selectSingleNode("CLASS3").getText());// 三级分类
            goods.setSelling(item.selectSingleNode("ZZSPMD").getText());// 产品卖点
            goods.setUpdateFlag(item.selectSingleNode("UPDATE_FLAG")
                    .getText());// 更新标志
            result.add(goods);
        }
        this.goodsService.insertBatchGoods(result);
    }

    /**
     * 批量保存门店数据
     * 
     * @param items
     * @throws BaseException
     * 
     */
    private void storeHandler(List<Node> items) throws BaseException {
        List<Store> result = new ArrayList<Store>();
        Store store = null;
        for (Node item : items) {
            store = new Store();
            String saleOrg = item.selectSingleNode("VKORG").getText();
            String storeCode = item.selectSingleNode("WERKS").getText();
            String companyCode = item.selectSingleNode("BUKRS").getText();
            String storeName = item.selectSingleNode("NAME1").getText();
            String secondDivisionCode = item.selectSingleNode("ZVKGRP").getText();
            String secondDivisionDes = item.selectSingleNode("ZBEZEI").getText();
            String divisionCode = item.selectSingleNode("ZVKBUR").getText();
            String divisionDes = item.selectSingleNode("ZBEZEI2").getText();
            String storeAddress = item.selectSingleNode("STRAS").getText();
            String storeTel = item.selectSingleNode("TELF1").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            store.setSale_org(saleOrg);
            store.setStore_code(storeCode);
            store.setCompany_code(companyCode);
            store.setStore_name(storeName);
            store.setSecond_division_code(secondDivisionCode);
            store.setSecond_division_des(secondDivisionDes);
            store.setDivision_code(divisionCode);
            store.setDivision_des(divisionDes);
            store.setStore_address(storeAddress);
            store.setStore_tel(storeTel);
            store.setUpdate_flag(updateFlag);
            result.add(store);
        }
        this.storeService.insertBatchStore(result);
    }

    /**
     * 批量保存二级分部数据
     * 
     * @param items
     * @throws BaseException
     */
    private void sencondDivisionHandler(List<Node> items) throws BaseException {
        List<SecondDivision> result = new ArrayList<SecondDivision>();
        for (Node item : items) {
            SecondDivision sencondDivision = new SecondDivision();
            String secondDivisionCode = item.selectSingleNode("VKGRP").getText();
            String secondDivisionDes = item.selectSingleNode("BEZEI").getText();
            String firstDivisionCode = item.selectSingleNode("VKBUR").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            sencondDivision.setSecond_division_code(secondDivisionCode);
            sencondDivision.setSecond_division_des(secondDivisionDes);
            sencondDivision.setFirst_division_code(firstDivisionCode);
            sencondDivision.setUpdate_flag(updateFlag);
            result.add(sencondDivision);
        }
        this.sencondDivisionService.insertBatchSecondDivision(result);
    }

    /**
     * 批量保存分部数据
     * 
     * @param items
     * @throws BaseException
     */
    private void divisionHandler(List<Node> items) throws BaseException {
        List<Division> result = new ArrayList<Division>();
        for (Node item : items) {
            Division division = new Division();
            String divisionCode = item.selectSingleNode("VKBUR").getText();
            String divisionDes = item.selectSingleNode("BEZEI").getText();
            String regionCode = item.selectSingleNode("BZIRK").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            division.setDivision_code(divisionCode);
            division.setDivision_desc(divisionDes);
            division.setRegion_code(regionCode);
            division.setUpdate_flag(updateFlag);
            result.add(division);
        }
        this.divisionService.insertBatchDivision(result);
    }

    /**
     * 批量保存大区数据
     * 
     * @param items
     * @throws BaseException
     */
    private void regionHandler(List<Node> items) throws BaseException {
        List<Region> result = new ArrayList<Region>();
        Region region = null;
        for (Node item : items) {
            region = new Region();
            String regionCode = item.selectSingleNode("BZIRK").getText();
            String regionDes = item.selectSingleNode("BZTXT").getText();
            String updateFlag = item.selectSingleNode("UPDATE_FLAG").getText();
            region.setRegion_code(regionCode);
            region.setRegion_des(regionDes);
            region.setUpdate_flag(updateFlag);
            result.add(region);
        }
        this.regionService.insertBatchRegion(result);
    }

   
    
}
