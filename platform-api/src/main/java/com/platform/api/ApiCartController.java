package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.cache.J2CacheUtils;
import com.platform.dao.ApiCouponMapper;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/api/cart")
public class ApiCartController extends ApiBaseAction {
    @Autowired
    private ApiCartService cartService;
    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiProductService productService;
    @Autowired
    private ApiGoodsSpecificationService goodsSpecificationService;
    @Autowired
    private ApiAddressService addressService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiCouponMapper apiCouponMapper;
    @Autowired
    private ApiMealService mealService;
    @Autowired
    private ApiStroeService stroeService;
    @Autowired
    private ApiFootprintService footprintService;
    /**
     * 获取购物车中的数据
     */
    @ApiOperation(value = "获取购物车中的数据")
    @PostMapping("getCart")
    public Object getCart(@LoginUser UserVo loginUser) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        param.put("stroeid",0);
        List<CartVo> cartList = cartService.queryList(param);
        //获取购物车统计信息
        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (CartVo cartItem : cartList) {
            goodsCount += cartItem.getNumber();
            goodsAmount = goodsAmount.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
            if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                checkedGoodsCount += cartItem.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
            }
//            if(cartItem.getStroeid()==null){
//
//            }else{
//                resultObj.put("flag",1);
//            }
        }
        // 获取优惠信息提示
        Map couponParam = new HashMap();
        couponParam.put("enabled", true);
        Integer[] send_types = new Integer[]{0, 7};
        couponParam.put("send_types", send_types);
        List<CouponInfoVo> couponInfoList = new ArrayList();
        List<CouponVo> couponVos = apiCouponService.queryList(couponParam);
        if (null != couponVos && couponVos.size() > 0) {
            CouponInfoVo fullCutVo = new CouponInfoVo();
            BigDecimal fullCutDec = new BigDecimal(0);
            BigDecimal minAmount = new BigDecimal(100000);
            for (CouponVo couponVo : couponVos) {
                BigDecimal difDec = couponVo.getMin_goods_amount().subtract(checkedGoodsAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (couponVo.getSend_type() == 0 && difDec.doubleValue() > 0.0
                        && minAmount.compareTo(couponVo.getMin_goods_amount()) > 0) {
                    fullCutDec = couponVo.getType_money();
                    minAmount = couponVo.getMin_goods_amount();
                    fullCutVo.setType(1);
                    fullCutVo.setMsg(couponVo.getName() + "，还差" + difDec + "元");
                } else if (couponVo.getSend_type() == 0 && difDec.doubleValue() < 0.0 && fullCutDec.compareTo(couponVo.getType_money()) < 0) {
                    fullCutDec = couponVo.getType_money();
                    fullCutVo.setType(0);
                    fullCutVo.setMsg("可使用满减券" + couponVo.getName());
                }
                if (couponVo.getSend_type() == 7 && difDec.doubleValue() > 0.0) {
                    CouponInfoVo cpVo = new CouponInfoVo();
                    cpVo.setMsg("满￥" + couponVo.getMin_amount() + "元免配送费，还差" + difDec + "元");
                    cpVo.setType(1);
                    couponInfoList.add(cpVo);
                } else if (couponVo.getSend_type() == 7) {
                    CouponInfoVo cpVo = new CouponInfoVo();
                    cpVo.setMsg("满￥" + couponVo.getMin_amount() + "元免配送费");
                    couponInfoList.add(cpVo);
                }
            }
            if (!StringUtils.isNullOrEmpty(fullCutVo.getMsg())) {
                couponInfoList.add(fullCutVo);
            }
        }
        resultObj.put("couponInfoList", couponInfoList);
        resultObj.put("cartList", cartList);
        //
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
        //
        resultObj.put("cartTotal", cartTotal);
        return resultObj;
    }
    @ApiOperation(value = "获取门店购物车中的数据")
    @PostMapping("getMealCart")
    public  Object getMealCart(@LoginUser UserVo loginUser,Integer stroeid){
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        param.put("stroeid",stroeid);
        List<CartVo> cartList = cartService.queryList(param);
       //获取购物车统计信息
        Integer mealCount = 0;
        BigDecimal mealsAmount = new BigDecimal(0.00);
        Integer deliveryfee=0;
        List<Map> cartst=new ArrayList();
        for (CartVo cartItem : cartList) {
            mealCount += cartItem.getNumber();
            mealsAmount = mealsAmount.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
            deliveryfee=cartItem.getDeliveryfee();
            Integer nume=cartItem.getStroeid();
            if(stroeid==nume){
                Map  cartlt=new HashMap();
                    cartlt.put("goods_name",cartItem.getGoods_name());
                    cartlt.put("number",cartItem.getNumber());
                    cartlt.put("retail_price",cartItem.getRetail_price());
                    cartlt.put("id",cartItem.getMealid());
                    cartlt.put("list_pic_url",cartItem.getList_pic_url());
                cartst.add(cartlt);
            }else{
                resultObj.put("flg",1);
                return resultObj;
            }
        }
        FootprintVo footprintEntity = new FootprintVo();
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy MM dd ");
//        Date date=new Date();
//        Long addtimen=(new);
        footprintEntity.setAdd_time(System.currentTimeMillis() / 1000);
        footprintEntity.setStroeid(stroeid);
        footprintEntity.setChecked(1);
        footprintEntity.setUser_id(loginUser.getUserId());
        footprintService.save(footprintEntity);
        resultObj.put("cartst",cartst);
        resultObj.put("cartList",cartList);
        Map<String, Object> cartTotal1 = new HashMap();
        cartTotal1.put("mealCount", mealCount);
        cartTotal1.put("mealsAmount", mealsAmount);
        cartTotal1.put("deliveryfee",deliveryfee);
        resultObj.put("cartTotal1",cartTotal1);
        return resultObj;
    }
    /**
     * 获取购物车信息，所有对购物车的增删改操作，都要重新返回购物车的信息
     */
    @ApiOperation(value = "获取购物车信息")
    @PostMapping("index")
    public Object index(@LoginUser UserVo loginUser) {
        return toResponsSuccess(getCart(loginUser));
    }

    private String[] getSpecificationIdsArray(String ids) {
        String[] idsArray = null;
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] tempArray = ids.split("_");
            if (null != tempArray && tempArray.length > 0) {
                idsArray = tempArray;
            }
        }
        return idsArray;
    }

    /**
     * 添加商品到购物车
     */
    @ApiOperation(value = "添加商品到购物车")
    @PostMapping("add")
    public Object add(@LoginUser UserVo loginUser,Integer goodsId,Integer productId,Integer number,Integer Specificationid) {

        //判断商品是否可以购买
        GoodsVo goodsInfo = goodsService.queryObject(goodsId);
        //前端无约束
        if (null == goodsInfo || goodsInfo.getIs_delete() == 1 || goodsInfo.getIs_on_sale() != 1) {
            return this.toResponsObject(400, "商品已下架", "");
        }
        //取得规格的信息,判断规格库存
//        ProductVo productInfo = productService.queryObject(productId);
        GoodsSpecificationVo goodSpecification=goodsSpecificationService.queryObject(Specificationid);
        if(Specificationid!=null){
            if (null == goodSpecification || goodSpecification.getSpecificationnumber() < number) {
                return this.toResponsObject(400, "库存不足", "");
            }
        }


//        if (null == productInfo || productInfo.getGoods_number() < number) {
//            return this.toResponsObject(400, "库存不足", "");
//        }

        //判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
//        cartParam.put("product_id", productId);
        cartParam.put("goods_specifition_ids",Specificationid);
        cartParam.put("user_id", loginUser.getUserId());
        List<CartVo> cartInfoList = cartService.queryList(cartParam);
        CartVo cartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        if (null == cartInfo) {
            if(Specificationid==null){
                cartInfo = new CartVo();
                cartInfo.setGoods_id(goodsId);
                cartInfo.setGoods_name(goodsInfo.getName());
                cartInfo.setList_pic_url(goodsInfo.getList_pic_url());
                cartInfo.setNumber(number);
                cartInfo.setSession_id("1");
                cartInfo.setUser_id(loginUser.getUserId());
                cartInfo.setRetail_price(goodsInfo.getRetail_price());
                cartInfo.setMarket_price(goodsInfo.getMarket_price());
                cartInfo.setStroeid(0);
                cartInfo.setChecked(1);
                cartService.save(cartInfo);
//                cartInfo.setNumber(cartInfo.getNumber() + number);
//                cartService.update(cartInfo);
                return toResponsSuccess(getCart(loginUser));
            }


                //添加操作
                //添加规格名和值
//            String[] goodsSepcifitionValue = null;
//            if (null != productInfo.getGoods_specification_ids() && productInfo.getGoods_specification_ids().length() > 0) {
//                Map specificationParam = new HashMap();
//                String[] idsArray = getSpecificationIdsArray(productInfo.getGoods_specification_ids());
//                specificationParam.put("ids", idsArray);
//                specificationParam.put("goods_id", goodsId);
//                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
//                goodsSepcifitionValue = new String[specificationEntities.size()];
//                for (int i = 0; i < specificationEntities.size(); i++) {
//                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
//                }
//            }
                cartInfo = new CartVo();

                cartInfo.setGoods_id(goodsId);
                cartInfo.setProduct_id(Specificationid);
//            cartInfo.setGoods_sn(productInfo.getGoods_sn());
                cartInfo.setGoods_name(goodsInfo.getName());
                cartInfo.setList_pic_url(goodsInfo.getList_pic_url());
                cartInfo.setNumber(number);
                cartInfo.setSession_id("1");
                cartInfo.setUser_id(loginUser.getUserId());
                cartInfo.setRetail_price(goodSpecification.getSpecificationPrice());
                cartInfo.setMarket_price(goodSpecification.getSpecificationPrice());
                cartInfo.setStroeid(0);
//            if (null != goodsSepcifitionValue) {
//                cartInfo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
//            }
                cartInfo.setSpecificationid(String.valueOf(goodSpecification.getId()));
                cartInfo.setChecked(1);
                cartService.save(cartInfo);

        } else {
            //如果已经存在购物车中，则数量增加
            if(Specificationid!=null){
                if (goodSpecification.getSpecificationnumber() < (number + cartInfo.getNumber())) {
                    return this.toResponsObject(400, "库存不足", "");
                }
            }
            Integer spec=Integer.valueOf(Specificationid);
            Integer cartspe=Integer.valueOf(cartInfo.getSpecificationid());
            if(spec==cartspe){
                cartInfo.setId(cartInfo.getId());
                cartInfo.setNumber(cartInfo.getNumber() + number);
                cartService.update(cartInfo);
            }else {
                cartInfo = new CartVo();
                cartInfo.setGoods_id(goodsId);
                cartInfo.setGoods_name(goodsInfo.getName());
                cartInfo.setList_pic_url(goodsInfo.getList_pic_url());
                cartInfo.setNumber(number);
                cartInfo.setSession_id("1");
                cartInfo.setUser_id(loginUser.getUserId());
                cartInfo.setRetail_price(goodsInfo.getRetail_price());
                cartInfo.setMarket_price(goodsInfo.getMarket_price());
                cartInfo.setStroeid(0);
                cartInfo.setChecked(1);
                cartInfo.setSpecificationid(String.valueOf(goodSpecification.getId()));
                cartService.save(cartInfo);
            }
        }
        return toResponsSuccess(getCart(loginUser));
    }
    /**
     * 门店点添加到购物车
     */
    @ApiOperation(value = "添加商品到门店购物车")
    @PostMapping("addstroe")
    public  Object addstroe(@LoginUser UserVo loginUser){
        JSONObject jsonParam = getJsonRequest();
        Integer stroeid1=jsonParam.getInteger("stroeid");
        Integer mealid=jsonParam.getInteger("mealid");
        Integer number=jsonParam.getInteger("number");
        MealVo mealEntity=mealService.queryObject(mealid);
        Map addmap=new HashMap();
        addmap.put("stroeid",stroeid1);
        addmap.put("user_id",loginUser.getUserId());
        addmap.put("mealid",mealid);
        List<CartVo> cartVoList=cartService.queryList(addmap);
        CartVo cartVo=null !=cartVoList && cartVoList.size()>0 ? cartVoList.get(0):null;
        if(null == cartVo){
            cartVo=new CartVo();
            cartVo.setStroeid(stroeid1);
            cartVo.setGoods_id(mealEntity.getId());
            cartVo.setGoods_name(mealEntity.getMealName());
            cartVo.setNumber(number);
            cartVo.setList_pic_url(mealEntity.getMealPriue());
            cartVo.setRetail_price(mealEntity.getMealPice());
            cartVo.setSession_id("1");
            cartVo.setUser_id(loginUser.getUserId());
            cartVo.setChecked(1);
            cartVo.setMealid(mealEntity.getId());
            cartService.mealsave(cartVo);

        }else{
            Integer num=cartVo.getNumber();
            cartVo.setNumber(num + number);
            cartService.update(cartVo);
        }
        Integer stroeid=0;
        List<CartVo> cartVoList1=cartService.queryList(addmap);
        for(CartVo cartVo1:cartVoList1){
            stroeid=cartVo1.getStroeid();
        }
        return toResponsSuccess(getMealCart(loginUser,stroeid));
    }
    /**
     * 减少商品到购物车
     */
    @ApiOperation(value = "减少商品到购物车")
    @PostMapping("minus")
    public Object minus(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
//        Integer productId = jsonParam.getInteger("productId");
        Integer Specificationid = jsonParam.getInteger("Specificationid");
        Integer number = jsonParam.getInteger("number");
        //判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
        cartParam.put("Specificationid", Specificationid);
        cartParam.put("user_id", loginUser.getUserId());
        List<CartVo> cartInfoList = cartService.queryList(cartParam);
        CartVo cartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        int cart_num = 0;
        if (null != cartInfo) {
            if (cartInfo.getNumber() > number) {
                cartInfo.setNumber(cartInfo.getNumber() - number);
                cartService.update(cartInfo);
                cart_num = cartInfo.getNumber();
            } else if (cartInfo.getNumber() == 1) {
                cartService.delete(cartInfo.getId());
                cart_num = 0;
            }
        }
        return toResponsSuccess(cart_num);
    }
    /**
     * 减少购物车门店餐单商品
     */
    @ApiOperation(value = "减少门店商品到购物车")
    @PostMapping("minusstroe")
    public Object minusstroe(@LoginUser UserVo loginUser){
        JSONObject jsonParam = getJsonRequest();
        Integer stroeid=jsonParam.getInteger("stroeid");
        Integer mealid=jsonParam.getInteger("mealid");
        Integer number=jsonParam.getInteger("number");
        Map cartParam = new HashMap();
        cartParam.put("stroeid", stroeid);
        cartParam.put("mealid", mealid);
        cartParam.put("user_id", loginUser.getUserId());
        List<CartVo> cartInfoList = cartService.queryList(cartParam);
        CartVo cartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        int cart_num = 0;
        if (null != cartInfo) {
            if (cartInfo.getNumber() > number) {
                cartInfo.setNumber(cartInfo.getNumber() - number);
                cartService.update(cartInfo);
                cart_num = cartInfo.getNumber();
            } else if (cartInfo.getNumber() == 1) {
                cartService.delete(cartInfo.getId());
                cart_num = 0;
            }
        }
        return  toResponsSuccess(cart_num);
    }
    /**
     * 更新指定的购物车信息
     */
    @ApiOperation(value = "更新指定的购物车信息")
    @PostMapping("update")
    public Object update( @LoginUser UserVo loginUser ) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer Specificationid = jsonParam.getInteger("Specificationid");
        Integer number = jsonParam.getInteger("number");
        Integer id = jsonParam.getInteger("id");
        //取得规格的信息,判断规格库存
        GoodsSpecificationVo goodSpecification=goodsSpecificationService.queryObject(Specificationid);
        if(Specificationid!=null){
            if (null == goodSpecification || goodSpecification.getSpecificationnumber() < number) {
                return this.toResponsObject(400, "库存不足", "");
            }
        }

        //判断是否已经存在Specificationid购物车商品
        CartVo cartInfo = cartService.queryObject(id);
            //只是更新number
            if(Specificationid!=null){
            if (cartInfo.getSpecificationid().equals(Specificationid)) {
                cartInfo.setId(id);
                cartInfo.setNumber(number);
                cartService.update(cartInfo);
                return toResponsSuccess(getCart(loginUser));
            }
        }


        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
        cartParam.put("Specificationid", Specificationid);
        List<CartVo> cartInfoList = cartService.queryList(cartParam);
        CartVo newcartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        if (null == newcartInfo) {
            if(Specificationid==null){
                cartInfo.setId(id);
                cartInfo.setNumber(number);
                cartInfo.setRetail_price(cartInfo.getRetail_price());
                cartInfo.setMarket_price(cartInfo.getMarket_price());
                cartService.update(cartInfo);
                return toResponsSuccess(getCart(loginUser));
            }
            //添加操作
            //添加规格名和值
//            String[] goodsSepcifitionValue = null;
//            if (null != productInfo.getGoods_specification_ids()) {
//                Map specificationParam = new HashMap();
//                specificationParam.put("ids", productInfo.getGoods_specification_ids());
//                specificationParam.put("goodsId", goodsId);
//                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
//                goodsSepcifitionValue = new String[specificationEntities.size()];
//                for (int i = 0; i < specificationEntities.size(); i++) {
//                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
//                }
//            }
            cartInfo.setSpecificationid(String.valueOf(Specificationid));
//            cartInfo.setGoods_sn(productInfo.getGoods_sn());
            cartInfo.setNumber(number);
            cartInfo.setRetail_price(goodSpecification.getSpecificationPrice());
            cartInfo.setMarket_price(goodSpecification.getSpecificationPrice());
//            if (null != goodsSepcifitionValue) {
//                cartInfo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
//            }
            cartInfo.setSpecificationid(String.valueOf(Specificationid));
            cartService.update(cartInfo);
        } else {
            //合并购物车已有的product信息，删除已有的数据
            Integer newNumber = number + newcartInfo.getNumber();
            if(Specificationid!=null){
                if (null == goodSpecification || goodSpecification.getSpecificationnumber() < newNumber) {
                    return this.toResponsObject(400, "库存不足", "");
                }
            }
//            cartService.delete(newcartInfo.getId());
                if(Specificationid==null){
                    cartInfo.setId(id);
                    cartInfo.setNumber(number);
                    cartInfo.setRetail_price(cartInfo.getRetail_price());
                    cartInfo.setMarket_price(cartInfo.getMarket_price());
                    cartService.update(cartInfo);
                    return toResponsSuccess(getCart(loginUser));
                }
//
            //添加规格名和值
//            String[] goodsSepcifitionValue = null;
////            if (null != goodSpecification.getId()) {
////                Map specificationParam = new HashMap();
////                specificationParam.put("ids", goodSpecification.getId());
////                specificationParam.put("goodsId", goodsId);
////                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
////                goodsSepcifitionValue = new String[specificationEntities.size()];
////                for (int i = 0; i < specificationEntities.size(); i++) {
////                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
////                }
////            }
            cartInfo.setSpecificationid(String.valueOf(Specificationid));
//            cartInfo.setGoods_sn(productInfo.getGoods_sn());
            cartInfo.setNumber(number);
            cartInfo.setRetail_price(goodSpecification.getSpecificationPrice());
            cartInfo.setMarket_price(goodSpecification.getSpecificationPrice());
//            if (null != goodsSepcifitionValue) {
//                cartInfo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
//            }
            cartService.update(cartInfo);
        }
        return toResponsSuccess(getCart(loginUser));
    }

    /**
     * 是否选择商品，如果已经选择，则取消选择，批量操作
     */
    @ApiOperation(value = "是否选择商品")
    @PostMapping("checked")
    public Object checked(@LoginUser UserVo loginUser, String Specificationid, Integer isChecked,Integer goodsid) {
        if(Specificationid!=null && goodsid!=null){
            CartVo cartVo=new CartVo();
            cartVo.setSpecificationid(Specificationid);
            cartVo.setGoods_id(goodsid);
            cartVo.setChecked(isChecked);
            cartVo.setUser_id(loginUser.getUserId());
            cartService.updateusercart(cartVo);
        }
        if(Specificationid!=null){
            if(goodsid!=null && Specificationid.equals("null")){
                if(isChecked==1){
                    CartVo cartVo=new CartVo();
                    cartVo.setGoods_id(goodsid);
                    cartVo.setChecked(isChecked);
                    cartVo.setUser_id(loginUser.getUserId());
                    cartService.updateusercart(cartVo);
                }else{
                    CartVo cartVo=new CartVo();
                    cartVo.setGoods_id(goodsid);
                    cartVo.setChecked(isChecked);
                    cartVo.setUser_id(loginUser.getUserId());
                    cartService.updateusercart(cartVo);
                }
            }
        }
        if(Specificationid==null && goodsid==null){
            if(isChecked==1){
                CartVo cartVo=new CartVo();
                cartVo.setChecked(1);
                cartVo.setUser_id(loginUser.getUserId());
                cartService.updateusercart(cartVo);
            }else {
                CartVo cartVo=new CartVo();
                cartVo.setChecked(0);
                cartVo.setUser_id(loginUser.getUserId());
                cartService.updateusercart(cartVo);
            }
        }
            return toResponsSuccess(getCart(loginUser));
    }

    //删除选中的购物车商品，批量删除
    @ApiOperation(value = "删除商品")
    @PostMapping("delete")
    public Object delete(@LoginUser UserVo loginUser) {
            CartVo cartVo=new CartVo();
            cartVo.setUser_id(loginUser.getUserId());
            cartService.deleteusercart(cartVo);
        return toResponsSuccess(getCart(loginUser));
    }

    //  获取购物车商品的总件件数
    @ApiOperation(value = "获取购物车商品的总件件数")
    @PostMapping("goodscount")
    public Object goodscount(@LoginUser UserVo loginUser) {
        if (null == loginUser || null == loginUser.getUserId()) {
            return toResponsFail("未登录");
        }
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        List<CartVo> cartList = cartService.queryList(param);
        //获取购物车统计信息
        Integer goodsCount = 0;
        for (CartVo cartItem : cartList) {
            goodsCount += cartItem.getNumber();
        }
        resultObj.put("cartList", cartList);
        //
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        //
        resultObj.put("cartTotal", cartTotal);
        return toResponsSuccess(resultObj);
    }

    /**
     * 订单提交前的检验和填写相关订单信息
     */
    @ApiOperation(value = "订单提交前的检验和填写相关订单信息")
    @PostMapping("checkout")
    public Object checkout(@LoginUser UserVo loginUser, Integer couponId, @RequestParam(defaultValue = "cart") String type,Integer addressId) {
        Map<String, Object> resultObj = new HashMap();
        //根据收货地址计算运费

        BigDecimal freightPrice = new BigDecimal(0.00);
        //默认收货地址
        if(null!=addressId&&addressId>0){
            AddressVo addressVo=addressService.queryObject(addressId);
            if (null == addressVo ) {
                resultObj.put("checkedAddress", new AddressVo());
            } else {
                resultObj.put("checkedAddress", addressVo);
            }
        }else {
            Map param = new HashMap();
            param.put("user_id", loginUser.getUserId());
            List addressEntities = addressService.queryList(param);

            if (null == addressEntities || addressEntities.size() == 0) {
                resultObj.put("checkedAddress", new AddressVo());
            } else {
                resultObj.put("checkedAddress", addressEntities.get(0));
            }
        }
        // * 获取要购买的商品和总价
        ArrayList checkedGoodsList = new ArrayList();
        BigDecimal goodsTotalPrice;
        if (type.equals("cart")) {
            Map<String, Object> cartData = (Map<String, Object>) this.getCart(loginUser);

            for (CartVo cartEntity : (List<CartVo>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == 1) {
                     Integer value=cartEntity.getGoods_id();
                    GoodsVo goodsVolist=goodsService.queryObject(value);
                    String companyname=goodsVolist.getSuppliername();
                    resultObj.put("companyname",companyname);
                    checkedGoodsList.add(cartEntity);
                }
            }
            goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");
        } else { // 是直接购买的
            BuyGoodsVo goodsVO = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId() + "");
//            ProductVo productInfo = productService.queryObject(goodsVO.getProductId());
            GoodsSpecificationVo goodsSpecificationVo=goodsSpecificationService.queryObject(goodsVO.getSpecificationid());
            if(goodsSpecificationVo==null){
                GoodsVo goodsVolist=goodsService.queryObject(goodsVO.getGoodsId());
                String companyname=goodsVolist.getSuppliername();
                resultObj.put("companyname",companyname);
                goodsTotalPrice = goodsVolist.getRetail_price().multiply(new BigDecimal(goodsVO.getNumber()));
                CartVo cartVo = new CartVo();
                cartVo.setGoods_name(goodsVolist.getName());
                cartVo.setNumber(goodsVO.getNumber());
                cartVo.setRetail_price(goodsVolist.getRetail_price());
                cartVo.setList_pic_url(goodsVolist.getList_pic_url());
                checkedGoodsList.add(cartVo);
                //获取可用的优惠券信息
                BigDecimal couponPrice = new BigDecimal(0.00);
                if (couponId != null && couponId != 0) {
                    CouponVo couponVo = apiCouponMapper.getUserCoupon(couponId);
                    if (couponVo != null) {
                        couponPrice = couponVo.getType_money();
                    }
                }

                //订单的总价
                BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);

                //
                BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

                resultObj.put("freightPrice", freightPrice);

                resultObj.put("couponPrice", couponPrice);
                resultObj.put("checkedGoodsList", checkedGoodsList);
                resultObj.put("goodsTotalPrice", goodsTotalPrice);
                resultObj.put("orderTotalPrice", orderTotalPrice);
                resultObj.put("actualPrice", actualPrice);
                return toResponsSuccess(resultObj);
            }
                Integer value=goodsSpecificationVo.getGoods_id();
                GoodsVo goodsVolist=goodsService.queryObject(value);
                String companyname=goodsVolist.getSuppliername();
                resultObj.put("companyname",companyname);


            //计算订单的费用
            //商品总价
            goodsTotalPrice = goodsSpecificationVo.getSpecificationPrice().multiply(new BigDecimal(goodsVO.getNumber()));
            //添加规格名和值
//            String[] goodsSepcifitionValue = null;
//            if (null != productInfo.getGoods_specification_ids()) {
//                Map specificationParam = new HashMap();
//                specificationParam.put("ids", (productInfo.getGoods_specification_ids()+",").split(","));
//                specificationParam.put("goodsId", goodsVO.getGoodsId());
//                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
//                goodsSepcifitionValue = new String[specificationEntities.size()];
//                for (int i = 0; i < specificationEntities.size(); i++) {
//                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
//                }
//            }
            CartVo cartVo = new CartVo();
            cartVo.setGoods_name(goodsVolist.getName());
            cartVo.setNumber(goodsVO.getNumber());
            cartVo.setRetail_price(goodsSpecificationVo.getSpecificationPrice());
            cartVo.setList_pic_url(goodsSpecificationVo.getPic_url());
//            cartVo.setGoods_specifition_ids(String.valueOf(goodsSpecificationVo.getId()));
//            if (null != goodsSepcifitionValue) {
//                cartVo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
//            }
            checkedGoodsList.add(cartVo);
        }


        //获取可用的优惠券信息
        BigDecimal couponPrice = new BigDecimal(0.00);
        if (couponId != null && couponId != 0) {
            CouponVo couponVo = apiCouponMapper.getUserCoupon(couponId);
            if (couponVo != null) {
                couponPrice = couponVo.getType_money();
            }
        }

        //订单的总价
        BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);

        //
        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

        resultObj.put("freightPrice", freightPrice);

        resultObj.put("couponPrice", couponPrice);
        resultObj.put("checkedGoodsList", checkedGoodsList);
        resultObj.put("goodsTotalPrice", goodsTotalPrice);
        resultObj.put("orderTotalPrice", orderTotalPrice);
        resultObj.put("actualPrice", actualPrice);
        return toResponsSuccess(resultObj);
    }
    /**
     * 订单提交前的检验和填写相关订单信息
     */
    @ApiOperation(value = "餐单订单提交前的检验和填写相关订单信息")
    @PostMapping("checklet")
    public Object checklet(@LoginUser UserVo loginUser, Integer couponId, @RequestParam(defaultValue = "cart") String type,Integer addressId,Integer stroeid){
        Map<String, Object> resultObj = new HashMap();
        //根据收货地址计算运费

        BigDecimal freightPrice = new BigDecimal(0.00);
        //默认收货地址
        if(null!=addressId&&addressId>0){
            AddressVo addressVo=addressService.queryObject(addressId);
            if (null == addressVo ) {
                resultObj.put("checkedAddress", new AddressVo());
            } else {
                resultObj.put("checkedAddress", addressVo);
            }
        }else {
            Map param = new HashMap();
            param.put("user_id", loginUser.getUserId());
            List addressEntities = addressService.queryList(param);

            if (null == addressEntities || addressEntities.size() == 0) {
                resultObj.put("checkedAddress", new AddressVo());
            } else {
                resultObj.put("checkedAddress", addressEntities.get(0));
            }
        }
        // * 获取要购买的商品和总价
        ArrayList checkedGoodsList = new ArrayList();
        BigDecimal goodsTotalPrice;
        if (type.equals("cart")) {
            Map<String, Object> cartData = (Map<String, Object>) this.getMealCart(loginUser,stroeid);
            for (CartVo cartEntity : (List<CartVo>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == 1) {
                    checkedGoodsList.add(cartEntity);
                }
            }
//            get("cartTotal")).get("mealsAmount")
            goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal1")).get("mealsAmount");
        } else { // 是直接购买的
            BuMealVo buMealVo = (BuMealVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "mealname" + loginUser.getUserId() + "");
            MealVo mealinfo = mealService.queryObject(buMealVo.getMealid());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = mealinfo.getMealPice().multiply(new BigDecimal(buMealVo.getNumber()));
            //添加规格名和值
//            String[] goodsSepcifitionValue = null;
//            if (null != productInfo.getGoods_specification_ids()) {
//                Map specificationParam = new HashMap();
//                specificationParam.put("ids", (productInfo.getGoods_specification_ids()+",").split(","));
//                specificationParam.put("goodsId", goodsVO.getGoodsId());
//                List<GoodsSpecificationVo> specificationEntities = goodsSpecificationService.queryList(specificationParam);
//                goodsSepcifitionValue = new String[specificationEntities.size()];
//                for (int i = 0; i < specificationEntities.size(); i++) {
//                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
//                }
//            }
            CartVo cartVo = new CartVo();
            cartVo.setGoods_name(mealinfo.getMealName());
            cartVo.setNumber(buMealVo.getNumber());
            cartVo.setRetail_price(mealinfo.getMealPice());
            cartVo.setList_pic_url(mealinfo.getMealPriue());
//            cartVo.setGoods_specifition_ids(mealinfo.getGoods_specification_ids());
//            if (null != goodsSepcifitionValue) {
//                cartVo.setGoods_specifition_name_value(StringUtils.join(goodsSepcifitionValue, ";"));
//            }
            checkedGoodsList.add(cartVo);
        }


//        //获取可用的优惠券信息
//        BigDecimal couponPrice = new BigDecimal(0.00);
//        if (couponId != null && couponId != 0) {
//            CouponVo couponVo = apiCouponMapper.getUserCoupon(couponId);
//            if (couponVo != null) {
//                couponPrice = couponVo.getType_money();
//            }
//        }

        //订单的总价
//        BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);
//
//        //
//        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额
        StroeVo stroelist=stroeService.queryObject(stroeid);
         Double deliveryfee=stroelist.getDeliveryfee();
         String name=stroelist.getStoreName();
        resultObj.put("freightPrice", deliveryfee);
        resultObj.put("stroename",name);

//        resultObj.put("couponPrice", couponPrice);
        resultObj.put("checkedGoodsList", checkedGoodsList);
        resultObj.put("goodsTotalPrice", goodsTotalPrice);
//        resultObj.put("orderTotalPrice", orderTotalPrice);
//        resultObj.put("actualPrice", actualPrice);
        return toResponsSuccess(resultObj);
    }
    /**
     * 选择优惠券列表
     */
    @ApiOperation(value = "选择优惠券列表")
    @PostMapping("checkedCouponList")
    public Object checkedCouponList(@LoginUser UserVo loginUser) {
        //
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        List<CouponVo> couponVos = apiCouponService.queryUserCouponList(param);
        if (null != couponVos && couponVos.size() > 0) {
            // 获取要购买的商品
            Map<String, Object> cartData = (Map<String, Object>) this.getCart(loginUser);
            List<CartVo> checkedGoodsList = new ArrayList();
            List<Integer> checkedGoodsIds = new ArrayList();
            for (CartVo cartEntity : (List<CartVo>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == 1) {
                    checkedGoodsList.add(cartEntity);
                    checkedGoodsIds.add(cartEntity.getId());
                }
            }
            // 计算订单的费用
            BigDecimal goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");  //商品总价
            // 如果没有用户优惠券直接返回新用户优惠券
            for (CouponVo couponVo : couponVos) {
                if (couponVo.getMin_amount().compareTo(goodsTotalPrice) <= 0) {
                    couponVo.setEnabled(1);
                }
            }
        }
        return toResponsSuccess(couponVos);
    }
    @ApiOperation(value = "购物车清空")
    @PostMapping("deleteall")
    public Object deleteall(@LoginUser UserVo loginuser){
        Map<String,Object> result=new HashMap<>();
        JSONObject deletjosn=this.getJsonRequest();
        Integer stroeid=deletjosn.getInteger("stroeid");
        cartService.deleteAll(loginuser.getUserId(),stroeid);
        result.put("flag",1);
        return result;
    }
}
