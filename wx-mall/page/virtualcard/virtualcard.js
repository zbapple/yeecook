var WxParse = require('../../lib/wxParse/wxParse.js');
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
// 引入coolsite360交互配置设定
require('coolsite.config.js');

// 获取全局应用程序实例对象
var app = getApp();

// 创建页面实例对象
Page({
  /**
   * 页面名称
   */
  name: "virtualcard",
  /**
   * 页面的初始数据
   */
  data: {
      serveInfoVos:[],
      cardVo:{},
      goodsVo:{},
      activatedState:false,
      deliveryRules:1,
      serveInfoId:0,
      activationCode:"",
      desc:"",
      gzDesc:"",
      price:0 ,
      productId:0,
      imgUrl:"",
      goodsHtml:"",
      isjk:false,
      isgz:false,
      type:0
  },
    /**
   * 生命周期函数--监听页面加载
   */
  onLoad (options) {
    // 注册coolsite360交互模块
    app.coolsite360.register(this);
   
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow () {
    // 执行coolsite360交互组件展示
    app.coolsite360.onShow(this);
    this.sdDataList();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh () {
    
  },
  sdDataList: function () {
    let that = this;
    util.request(api.CardQuery).then(function (res) {
      if (res.errno === 0) {
       
        that.setData({
          serveInfoVos: res.data.serveInfoVos,
          cardVo: res.data.cardVo,
          goodsVo: res.data.goodsVo,
          activatedState: res.data.activatedState,
          deliveryRules: res.data.deliveryRules,
          serveInfoId: 0,
          activationCode: "",
          desc: res.data.desc,
          gzDesc: res.data.gzDesc,
          price: res.data.price,
          productId: res.data.productId,
          imgUrl: res.data.goodsVo.primary_pic_url,
          goodsHtml: res.data.goodsHtml,
          'cardVo.serveNextTime': util.formatTime(new Date(res.data.cardVo.serveNextTime)),
          'cardVo.servevalidtime': util.formatTime(new Date(res.data.cardVo.servevalidtime)),
          'cardVo.serveLastTime': util.formatTime(new Date(res.data.cardVo.serveLastTime)),
        })
        if (res.data.cardVo.servevalidtime) {
          that.setData({
            'cardVo.serveLastTime':"暂无配送记录"
          })
        }
        WxParse.wxParse('goodsDetail', 'html', res.data.goodsHtml, that);
      }
    });
  },
  /**
   * 直接购买
   */
  buyGoods: function () {
    var that = this;
    // 直接购买激活
    util.request(api.BuyAdd, { goodsId: this.data.goodsVo.id, number: 1, productId: this.data.productId }, "POST", 'application/json')
      .then(function (res) {
        let _res = res;
        if (_res.errno == 0) {
          that.setData({
            openAttr: !that.data.openAttr,
          });
          wx.navigateTo({
            url: '/pages/shopping/checkout/checkout?isBuy=true',
          })
        } else {
          wx.showToast({
            image: '/static/images/icon_error.png',
            title: _res.errmsg,
            mask: true
          });
        }

      });
  },
   
  //以下为自定义点击事件
  
  tap_e99413d7:function(e){
    //触发coolsite360交互事件 每月一号
    app.coolsite360.fireEvent(e,this);
    let that = this;
    that.setData({
      isgz: true,
      deliveryRules:1
    })
  },
  
  tap_da5c6e8e:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_9f584986:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_d5e62053:function(e){
    //触发coolsite360交互事件 每月中旬
    app.coolsite360.fireEvent(e,this);
    let that = this;
    that.setData({
      isgz: true,
      deliveryRules: 2
    })
  },
  formSubmit: function (e) {
    // console.log('form发生了submit事件，携带数据为：', e.detail.value);
    let { activationCode } = e.detail.value;
    if (!activationCode || activationCode.length<12) {
      wx.showToast({
        title: '请输入有效激活码!',
        image: '/static/images/icon_error.png',
        mask: true
      })
      return;
    }
    if (!this.data.isjk){
      wx.showToast({
        title: '请选择卡类型',
        image: '/static/images/icon_error.png',
        mask: true
      })
      wx.pageScrollTo({
        scrollTop: 250
      })
      return;
    }
    if (!this.data.isgz) {
      wx.showToast({
        title: '请选择配送规则',
        image: '/static/images/icon_error.png',
        mask: true
      })
      wx.pageScrollTo({
        scrollTop: 250
      })
      return;
    }
    util.request(api.CardActivation, { activationCode: activationCode, serveInfoId: this.data.serveInfoId, deliveryRules: this.data.deliveryRules }, "POST")
      .then(function (res) {
        let _res = res;
        if (_res.errno == 0) {
          wx.navigateTo({
            url: '../successful1/successful1',
          })
        } else {
          wx.showToast({
            image: '/static/images/icon_error.png',
            title: _res.errmsg,
            mask: true
          });
        }

      });

  },
  tap_7943c15b:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_0d185810:function(e){
    //触发coolsite360交互事件 年卡
    let that = this;
    that.setData({
      isjk: true,
      deliveryRules: 3,
      serveInfoId: that.data.serveInfoVos[0].id,
      gzDesc: that.data.serveInfoVos[0].desc, 
      type: that.data.serveInfoVos[0].type, 
      price: that.data.serveInfoVos[0].price,
      productId: that.data.serveInfoVos[0].productId,
      imgUrl: that.data.serveInfoVos[0].imgUrl
    })
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_43265a36:function(e){
    //触发coolsite360交互事件 季卡
    let that = this;
    that.setData({
      isjk: true,
      deliveryRules: 1,
      serveInfoId: that.data.serveInfoVos[2].id,
      gzDesc: that.data.serveInfoVos[2].desc,
      price: that.data.serveInfoVos[2].price,
      type: that.data.serveInfoVos[2].type, 
      productId: that.data.serveInfoVos[2].productId,
      imgUrl: that.data.serveInfoVos[2].imgUrl
    })
    app.coolsite360.fireEvent(e,this);

  },
  
  tap_7a6ea42a:function(e){
    //触发coolsite360交互事件 半年卡
    let that = this;
    that.setData({
      isjk: true,
      deliveryRules: 2,
      serveInfoId: that.data.serveInfoVos[1].id,
      gzDesc: that.data.serveInfoVos[1].desc,
      price: that.data.serveInfoVos[1].price,
      type: that.data.serveInfoVos[1].type, 
      productId: that.data.serveInfoVos[1].productId,
      imgUrl: that.data.serveInfoVos[1].imgUrl
    })
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_94f41255:function(e){
    //触发coolsite360交互事件 
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_04d8e2f8:function(e){
    //触发coolsite360交互事件 月底
    app.coolsite360.fireEvent(e,this);
    let that = this;
    that.setData({
      isgz: true,
      deliveryRules: 3
    })
  },
  
  tap_b67e4f3b:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_7984292f:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_b812d32a:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_04c5bbcd:function(e){ 
    //触发coolsite360交互事件 直接购买激活

    if (!this.data.isjk) {
      wx.showToast({
        title: '请选择卡类型',
        image: '/static/images/icon_error.png',
        mask: true
      })
      wx.pageScrollTo({
        scrollTop: 250
      })
      return;
    }
    if (!this.data.isgz) {
      wx.showToast({
        title: '请选择配送规则',
        image: '/static/images/icon_error.png',
        mask: true
      })
      wx.pageScrollTo({
        scrollTop: 250
      })
      return;
    }
    this.buyGoods();
  },
  
  tap_9f5b44e9:function(e){
    //触发coolsite360交互事件
    app.coolsite360.fireEvent(e,this);
  },
  
  tap_ae3cc534:function(e){
    //触发coolsite360交互事件 激活码激活
    app.coolsite360.fireEvent(e,this);
  },
  
})

