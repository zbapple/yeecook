package com.platform.api;

import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.utils.Redeem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(tags = "粮卡")
@RestController
@RequestMapping("/api/card")
public class ApiCardController extends ApiBaseAction {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    ApiActivationCardService apiActivationCardService; //粮卡信息
    @Autowired
    ApiActivationCodeLogService apiActivationCodeLogService;//用户激活粮卡日志
    @Autowired
    ApiActivationGeneratorLogService apiActivationGeneratorLogService;//激活码生成日志
    @Autowired
    ApiActivationGeneratorService apiActivationGeneratorService;
    @Autowired
    ApiServeInfoService apiServeInfoService; //粮卡服务信息
    @Autowired
    ApiServeTypeService apiServeTypeService; //粮卡服务类型
    @Autowired
    ApiGoodsService apiGoodsService;
    @Autowired
    ApiProductService apiProductService;
    @Autowired
    ApiAddressService apiAddressService;
    @Autowired
    private ApiOrderGoodsService orderGoodsService;
    @Autowired
    private ApiGoodsSpecificationService apiGoodsSpecificationService;
    @Autowired
    private ApiOrderController apiOrderController;

    /**
     * 获取用户粮卡信息
     */
    @ApiOperation(value = "获取用户粮卡信息")
    @PostMapping("getCard")
    public Object getCard(@LoginUser UserVo loginUser, @RequestParam(defaultValue = "1") Integer serveType) {
        CardVo cardVo = new CardVo();
        //查询服务类型表
        ServeTypeVo serveTypeVo = apiServeTypeService.queryObject(serveType);
        //根据类型查询出对于的服务商品信息
        GoodsVo goodsVo = apiGoodsService.queryObject(serveTypeVo.getGoodsId());
        //查询出服务类型对应的服务明细规则
        Map queryMap = new HashMap();
        queryMap.put("type", serveType);
        List<ServeInfoVo> serveInfoVos = apiServeInfoService.queryList(queryMap);
        //遍历查询出服务规则对应的产品信息
        //TODO 可以优化到sql减少查询次数
        for (ServeInfoVo serveInfoVo : serveInfoVos) {
            ProductVo productVo = apiProductService.queryObject(serveInfoVo.getProductId());
            ProductVo productVo1 = apiProductService.queryObject(Integer.valueOf(serveInfoVo.getServeProductIds()));
            //TODO 这里只考虑服务关联一个商品的情况，没有考虑关联多个商品的情况
//            GoodsSpecificationVo goodsSpecificationVo = apiGoodsSpecificationService.queryObject(Integer.valueOf(productVo.getGoods_specification_ids()));
            GoodsSpecificationVo goodsSpecificationVo1 = apiGoodsSpecificationService.queryObject(Integer.valueOf(productVo1.getGoods_specification_ids()));
            serveInfoVo.setPrice(productVo.getRetail_price().toString());
            serveInfoVo.setImgUrl(productVo.getList_pic_url());
            serveInfoVo.setProductDesc(productVo1.getGoods_name()+"/"+goodsSpecificationVo1.getValue());
        }
        //根据登录用户查询出该用户已激活开卡信息
        queryMap.clear();
        queryMap.put("userId", loginUser.getUserId());
        List<ActivationCardVo> activationCardVos = apiActivationCardService.queryList(queryMap);
        if (null != activationCardVos && activationCardVos.size() == 1) {
            cardVo.setCardVo(activationCardVos.get(0));
        } else {
            //如果为空用户则没有开卡，设置用户初始信息
            ActivationCardVo activationCardVo = new ActivationCardVo();
            activationCardVo.setActivated(0);
            activationCardVo.setAddressId(0);
            activationCardVo.setDeliveryrules("0");
            activationCardVo.setHaveServeCount(0);
            activationCardVo.setServeTypeId(serveType);
            activationCardVo.setUserId(loginUser.getUserId().intValue());
            activationCardVo.setServeCount(0);
            cardVo.setCardVo(activationCardVo);
        }
        cardVo.setGoodsVo(goodsVo);
        cardVo.setServeInfoVos(serveInfoVos);

        if (cardVo.getCardVo().getActivated() == 1) {
            cardVo.setActivatedState(true);
        } else {
            cardVo.setActivatedState(false);
        }

        if (null != cardVo.getCardVo().getId()) {
            cardVo.setDeliveryRules(Integer.valueOf(cardVo.getCardVo().getDeliveryrules()));

            String productDesc=cardVo.getServeInfoVos().get(2).getProductDesc();
            String desc = productDesc+"*"+(cardVo.getCardVo().getServeCount() - cardVo.getCardVo().getHaveServeCount()) + "次";
            cardVo.setDesc(desc);
        }
        cardVo.setPrice(cardVo.getServeInfoVos().get(2).getPrice());
        cardVo.setGzDesc(cardVo.getServeInfoVos().get(2).getDesc());
        cardVo.setGoodsHtml(goodsVo.getGoods_desc());

        return toResponsSuccess(cardVo);
    }

    /**
     * 激活粮卡
     */
    @ApiOperation(value = "激活粮卡")
    @PostMapping("activationCard")
    @Transactional
    public Object activationCard(@LoginUser UserVo loginUser, String activationCode, Integer serveInfoId, String deliveryRules, Integer orderSn, @RequestParam(defaultValue = "微信小程序") String terminal) {
        //根据serveInfoId获取激活码password
        String passWord;
        Map queryMap = new HashMap();
        queryMap.put("serveInfoId", serveInfoId);
        List<ActivationGeneratorVo> list = apiActivationGeneratorService.queryList(queryMap);
        if (null != list && list.size() == 1) {
            passWord = list.get(0).getPassword();
        } else {
            return toResponsFail("激活码秘钥不存在！");
        }
        //验证激活码是否有效
        if (!Redeem.VerifyCode(activationCode, passWord)) {
            return toResponsFail("激活码无效！");
        }
        //查询是否已经激活
        queryMap.clear();
        queryMap.put("activationCode", activationCode);
        List<ActivationCodeLogVo> logVoList = apiActivationCodeLogService.queryList(queryMap);
        if (null != logVoList && logVoList.size() > 0) {
            return toResponsFail("激活码已使用！");
        }
        //激活服务--step1-查询用户激活服务信息
        ServeInfoVo serveInfoVo = apiServeInfoService.queryObject(serveInfoId);
        if (null == serveInfoVo || serveInfoVo.getId() == null) {
            return toResponsFail("平台已停止该服务，服务代码{" + serveInfoId + "},请联系客服!");
        } else {
            //验证激活时间是否在有效时间范围内
            Date nowDate = new Date();
            Date data = serveInfoVo.getActivationValidity();
            if (nowDate.getTime() > data.getTime()) {
                return toResponsFail("该激活码已过期，服务代码{" + serveInfoId + "},请联系客服!");
            }
        }
        //获取地址信息
        Map param = new HashMap();
        AddressVo addressVo;
        param.put("user_id", loginUser.getUserId());
        List addressEntities = apiAddressService.queryList(param);
        if (null == addressEntities || addressEntities.size() == 0) {
            return toResponsFail("请设置默认配送地址!");
        } else {
            addressVo = (AddressVo) addressEntities.get(0);
        }
        //激活服务--step2-查询用户开卡信息表存在则更新不存在则新增
        queryMap.clear();
        queryMap.put("userId", loginUser.getUserId());
        queryMap.put("serveTypeId", serveInfoVo.getType());
        List<ActivationCardVo> cardVoList = apiActivationCardService.queryList(queryMap);
        ActivationCardVo activationCardVo;
        if (null == cardVoList || cardVoList.size() <= 0) {
            activationCardVo = new ActivationCardVo();
            activationCardVo.setServeTypeId(serveInfoVo.getType()); //服务类型
            activationCardVo.setServeCount(serveInfoVo.getServeCount());//服务次数
            activationCardVo.setActivated(ActivationCardVo.ACTIVATED_OPEN);//激活状态
            activationCardVo.setUserId(loginUser.getUserId().intValue());//激活用户
            activationCardVo.setServeLastTime(null);//最后服务次数
            activationCardVo.setDeliveryrules(deliveryRules);//配送规则
            activationCardVo.setHaveServeCount(0);//已服务次数
            activationCardVo.setAddressId(addressVo.getId().intValue());//地址id
            activationCardVo.setAddressVo(addressVo);//地址信息
            activationCardVo.countServeTime(activationCardVo);//计算时间
            apiActivationCardService.save(activationCardVo); //更新用户激活信息
        } else {
            activationCardVo = cardVoList.get(0);
            activationCardVo.setServeCount(activationCardVo.getServeCount() + serveInfoVo.getServeCount()); //服务次数
            activationCardVo.setActivated(ActivationCardVo.ACTIVATED_OPEN);//激活状态
            activationCardVo.setUserId(loginUser.getUserId().intValue());//激活用户
            activationCardVo.setDeliveryrules(deliveryRules); //配送规则
            activationCardVo.setAddressId(addressVo.getId().intValue());//配送地址
            activationCardVo.setAddressVo(addressVo);//地址信息
            activationCardVo.countServeTime(activationCardVo);//计算时间
            apiActivationCardService.update(activationCardVo); //更新用户激活信息
        }

        ActivationCodeLogVo activationCodeLogVo = new ActivationCodeLogVo();
        activationCodeLogVo.setOrderSn(orderSn+"");
        activationCodeLogVo.setActivationCode(activationCode);
        activationCodeLogVo.setActivateTerminal(terminal);
        activationCodeLogVo.setServeInfoId(serveInfoVo.getId());
        activationCodeLogVo.setServeInfoName(serveInfoVo.getName());
        activationCodeLogVo.setActivationTime(new Date());
        activationCodeLogVo.setUserId(loginUser.getUserId().intValue());
        activationCodeLogVo.setUserName(loginUser.getNickname());
        activationCodeLogVo.setRemark("用户激活ip:" + loginUser.getLast_login_ip());
        apiActivationCodeLogService.save(activationCodeLogVo); //记录激活日志
        logger.info("激活成功：" + activationCode + ";用户id:" + loginUser.getUserId());
        return toResponsMsgSuccess("激活成功");
    }

    /**
     * 直接购买激活激活粮卡
     */
    //TODO 直接购买的配送规则默认为当月第一天，服务类型也是默认为粮卡 1
    public Object activationBayCard(@LoginUser UserVo loginUser, Integer orderId, @RequestParam(defaultValue = "1")String deliveryRules, @RequestParam(defaultValue = "微信小程序激活码激活") String terminal) {



        //直接购买逻辑
        Map queryMap = new HashMap();
        queryMap.put("order_id", orderId);
        List<OrderGoodsVo> orderGoodsVoList = orderGoodsService.queryList(queryMap);
        if (null == orderGoodsVoList || orderGoodsVoList.size() <= 0) {
            return toResponsFail("激活失败找不到付款订单！");
        }
        OrderGoodsVo orderGoodsVo = orderGoodsVoList.get(0);
        Integer productId = orderGoodsVo.getProduct_id();
        queryMap.clear();
        queryMap.put("type", 1);
        queryMap.put("productId", productId);
        List<ServeInfoVo> serveInfoVo = apiServeInfoService.queryList(queryMap);
        if (null == serveInfoVo || serveInfoVo.size() <= 0) {
            return toResponsFail("没找到对于的服务信息！");
        }
        Integer serveInfoId = serveInfoVo.get(0).getId();
        String passWord;
        queryMap.clear();
        queryMap.put("serveInfoId", serveInfoId);
        List<ActivationGeneratorVo> list = apiActivationGeneratorService.queryList(queryMap);
        if (null != list && list.size() == 1) {
            passWord = list.get(0).getPassword();
        } else {
            return toResponsFail("激活码秘钥不存在！");
        }
        String code = Redeem.createOne(list.get(0).getGroupId().byteValue(), passWord, list.get(0).getLength());
        logger.error("订单" + orderId + "激活成功-激活码："+code);
        return activationCard(loginUser, code, serveInfoId, deliveryRules, orderId, "微信小程序直接购买激活");
    }

    public void activationBayCard(Long loginUser,Integer orderId){
        UserVo userVo=new UserVo();
        userVo.setUserId(loginUser);
        activationBayCard(userVo, orderId,"1","微信小程序激活码激活");
        apiOrderController.confirmOrder(orderId);
    }

}
