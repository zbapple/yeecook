package com.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class CardVo {

   private List<ServeInfoVo> serveInfoVos;
   private ActivationCardVo cardVo;
   private GoodsVo goodsVo;
   private Boolean activatedState=false;
   private int deliveryRules=1;
   private int serveInfoId=0;
   private String activationCode="";
   private String desc="";
   private String gzDesc="";
   private String price="0.00";
   private int productId=0;
   private String imUrl;
   private String goodsHtml;

}
