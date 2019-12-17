package com.platform.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.annotation.LoginUser;
import com.platform.entity.*;
import com.platform.oss.OSSFactory;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Base64;
import com.platform.utils.CharUtil;
import com.platform.utils.Query;
import com.platform.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController extends ApiBaseAction {
    @Autowired
    private ApiCommentService commentService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiCommentPictureService commentPictureService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;
    @Autowired
    private ApiStroeService stroeService;
    @Autowired
    private ApiOrderGoodsService orderGoodsService;
    @Autowired
    private ApiOrderService orderService;
    @Autowired
    private ApiGoodsService goodsService;

    /**
     * 发表评论
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("post")
    public Object post(@LoginUser UserVo loginUser) {
        Map resultObj = new HashMap();
        JSONObject jsonParam = this.getJsonRequest();
        Integer typeId=jsonParam.getInteger("typeId");
        Integer stroeid=jsonParam.getInteger("stroeid");
        String content = jsonParam.getString("content");
        String orderid=jsonParam.getString("orderid");
            Map ordermap=new HashMap();
            ordermap.put("order_id",orderid);
            List<OrderGoodsVo> orderGoodsVos=orderGoodsService.queryList(ordermap);
            Integer goodsid=0;
            for(OrderGoodsVo orderGoodsVo:orderGoodsVos){
                goodsid=orderGoodsVo.getGoods_id();
            }
            CommentVo commentEntity = new CommentVo();
            commentEntity.setTypeId(typeId);
            commentEntity.setContent(content);
            commentEntity.setStatus(0);
            commentEntity.setAdd_time(System.currentTimeMillis() / 1000);
            commentEntity.setUser_id(loginUser.getUserId());
            commentEntity.setGrade(jsonParam.getDouble("grade"));
            commentEntity.setOrderid(new Integer(orderid));
            commentEntity.setStroeid(stroeid);
            commentEntity.setGoodsid(goodsid);
             commentService.save(commentEntity);
            Integer  insertId=commentEntity.getId();
            Map totalmap=new HashMap();
            totalmap.put("stroeid",stroeid);
            Double avg=commentService.avgGrade(totalmap);
            BigDecimal avgnum=new BigDecimal(avg);
            avg=avgnum.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            StroeVo stroeVo=new StroeVo();
                stroeVo.setStroeGrade(avg);
                stroeVo.setId(stroeid);
                stroeService.update(stroeVo);
            StroeVo  stroeVo1=stroeService.queryObject(stroeid);
            resultObj.put("insertId",insertId);
//        }

        //
//        if (insertId > 0 && null != imagesList && imagesList.size() > 0) {
//            int i = 0;
//            for (Object imgLink : imagesList) {
//                i++;
//                CommentPictureVo pictureVo = new CommentPictureVo();
//                pictureVo.setComment_id(insertId);
//                pictureVo.setPic_url(imgLink.toString());
//                pictureVo.setSort_order(i);
//                commentPictureService.save(pictureVo);
//            }
//        }
//        // 是否领取优惠券
//        if (insertId > 0 && typeId == 0) {
//            // 当前评价的次数
//            Map param = new HashMap();
//            param.put("value_id", valueId);
//            List<CommentVo> commentVos = commentService.queryList(param);
//            boolean hasComment = false;
//            for (CommentVo commentVo : commentVos) {
//                if (commentVo.getUser_id().equals(loginUser.getUserId())
//                        && !commentVo.getId().equals(insertId)) {
//                    hasComment = true;
//                }
//            }
//            if (!hasComment) {
//                Map couponParam = new HashMap();
//                couponParam.put("send_type", 6);
//                CouponVo newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
//                if (null != newCouponConfig
//                        && newCouponConfig.getMin_transmit_num() >= commentVos.size()) {
//                    UserCouponVo userCouponVo = new UserCouponVo();
//                    userCouponVo.setAdd_time(new Date());
//                    userCouponVo.setCoupon_id(newCouponConfig.getId());
//                    userCouponVo.setCoupon_number(CharUtil.getRandomString(12));
//                    userCouponVo.setUser_id(loginUser.getUserId());
//                    apiUserCouponService.save(userCouponVo);
//                    resultObj.put("coupon", newCouponConfig);
//                }
//            }
//        }
        if (insertId > 0) {
            return toResponsObject(0, "评论添加成功", resultObj);
        } else {
            return toResponsFail("评论保存失败");
        }
    }
    @ApiOperation(value = "上传图片")
    @PostMapping("fliepic")
    public Object filepic(@LoginUser UserVo loginUser, MultipartFile images, Integer stroeid,Integer insertId){
        Map<String,Object> result=new HashMap<>();
        Map parmap=new HashMap();
        parmap.put("stroeid",stroeid);
        parmap.put("insertId",insertId);
        parmap.put("userid",loginUser.getUserId());
        List<CommentVo> commentVlist=commentService.queryList(parmap);
        if(commentVlist.size()!=0){
            String url=null;
            try {
                url= OSSFactory.build().upload(images);
            }catch (Exception e){
                result.put("e",e);
                return result;
            }
            CommentPictureVo commentVo=new CommentPictureVo();
            commentVo.setComment_id(insertId);
            commentVo.setPic_url(url);
            commentPictureService.save(commentVo);
            result.put("flag",1);
        }else{
            result.put("flag",0);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "评论回复")
    @PostMapping("reply")
    public Object reply(@LoginUser UserVo loginUser){
        Map<String,Object> result=new HashMap<>();
        JSONObject jsonreply = getJsonRequest();
        Integer insertId=jsonreply.getInteger("insertId");
        Integer typeId = jsonreply.getInteger("typeId");
        String content = jsonreply.getString("content");
        String orderid=jsonreply.getString("orderid");
        CommentVo commentEntity = new CommentVo();
        commentEntity.setTypeId(typeId);
//      commentEntity.setValue_id(valueId);
        commentEntity.setContent(content);
        commentEntity.setStatus(0);
        commentEntity.setAdd_time(System.currentTimeMillis() / 1000);
        commentEntity.setUser_id(loginUser.getUserId());
        commentEntity.setOrderid(new Integer(orderid));
        Integer  serttId= commentService.save(commentEntity);
        result.put("insertId",insertId);
        if(serttId!=null){
            return toResponsObject(0, "评论添加成功", result);
        }else {
            return toResponsFail("评论保存失败");
        }
    }
    /**
     */
    @ApiOperation(value = "评论数量")
    @PostMapping("count")
    public Object count(Integer typeId, Integer valueId) {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("typeId", typeId);
        Integer allCount = commentService.queryTotal(param);
        Integer hasPicCount = commentService.queryhasPicTotal(param);
        //
        resultObj.put("allCount", allCount);
        resultObj.put("hasPicCount", hasPicCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * @param typeId
     * @param
     * @param showType 选择评论的类型 0 全部， 1 只显示图片
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "选择查找评论类型")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(Integer typeId, Integer orderid, Integer showType,
                       @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Integer grader, Integer grade,String sort,String order,Integer stroeid,Integer goodsid) {
        List<CommentVo> commentList = new ArrayList();
        Map param = new HashMap();
        param.put("typeId", typeId);
        param.put("stroeid",stroeid);
        param.put("orderid",orderid);
        param.put("goodsid", goodsid);
        param.put("page", page);
        param.put("limit", size);
        if (StringUtils.isNullOrEmpty(sort)) {
            param.put("order", "desc");
        } else {
            param.put("order", sort);
        }
        if (StringUtils.isNullOrEmpty(order)) {
            param.put("sidx", "id");
        } else {
            param.put("sidx", order);
        }
        if(grade!=null){
            param.put("grade",grade);
        }
        if(grader!=null){
            param.put("grader",grader);
        }
        if (null != showType) {
            param.put("hasPic", 3);
        }
        //查询列表数据
        Query query = new Query(param);
        commentList = commentService.queryList(query);
        int total = commentService.queryTotal(query);
        ApiPageUtils pageUtil = new ApiPageUtils(commentList, total, query.getLimit(), query.getPage());
        //
        Integer commentid=0;
        for (CommentVo commentItem : commentList) {
            commentItem.setContent(commentItem.getContent());
            commentItem.setUser_info(userService.queryObject(commentItem.getUser_id()));
            commentid=commentItem.getId();
            Map paramPicture = new HashMap();
            paramPicture.put("comment_id", commentid);
            List<CommentPictureVo> commentPictureEntities = commentPictureService.queryList(paramPicture);
            commentItem.setPic_list(commentPictureEntities);
        }
        return toResponsSuccess(pageUtil);
    }
    @ApiOperation(value = "显示全部的评论")
    @IgnoreAuth
    @PostMapping("Alllist")
    public Object Alllist(Integer stroeid,Integer goodsid){
        Map<String,Object> result=new HashMap<>();
        Map listmap=new HashMap();
        listmap.put("stroeid",stroeid);
        listmap.put("goodsid", goodsid );
        List<CommentVo> voList=commentService.queryList(listmap);
        if(voList!=null){
            Integer commentid=0;
            for (CommentVo commentItem : voList) {
                commentItem.setContent(commentItem.getContent());
                commentItem.setUser_info(userService.queryObject(commentItem.getUser_id()));
                commentid=commentItem.getId();
                Map paramPicture = new HashMap();
                paramPicture.put("comment_id", commentid);
                List<CommentPictureVo> commentPictureEntities = commentPictureService.queryList(paramPicture);
                commentItem.setPic_list(commentPictureEntities);
            }
            int total = commentService.queryTotal(listmap);
            result.put("total",total);
            result.put("voList",voList);
            return toResponsSuccessForSelect(result);
        }else {
            result.put("voList",voList);
            return toResponsSuccessForSelect(result);
        }
    }
    @ApiOperation(value = "显示")
    @PostMapping("reslis")
    public Object reslis(@LoginUser UserVo loginUser){
        Map<String,Object> result=new HashMap<>();
        JSONObject jsonObject=this.getJsonRequest();
//        Integer stroeid=jsonObject.getInteger("stroeid");
        Integer id=jsonObject.getInteger("id");
        OrderGoodsVo orderGoodsVoList=orderGoodsService.queryObject(id);
        Integer goodsid=orderGoodsVoList.getGoods_id();
        Integer orederid=orderGoodsVoList.getOrder_id();
        OrderVo orderVo=orderService.queryObject(orederid);
        if(orderVo!=null){
            if(orderVo.getShoptype()==1){
                GoodsVo goodsVo=goodsService.queryObject(goodsid);
                if(goodsVo==null){
                    return toResponsMsgSuccess("goodsVo的值为空");
                }
                result.put("picrue",goodsVo.getList_pic_url());
                result.put("name",goodsVo.getName());
            }else{
                Integer storeid=orderVo.getStroeid();
                StroeVo stroeVo=stroeService.queryObject(storeid);
                if(stroeVo==null){
                    return toResponsMsgSuccess("stroeVo的值为空");
                }
                result.put("picrue",stroeVo.getStorePicrue());
                result.put("name",stroeVo.getStoreName());
            }
        }
        return result;
    }
}