// pages/takeoutindex/takeoutindex.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: { 
    banner: [],
    storelist:[],
    latitude:'',
    longitude:''
  },

  /** 
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that=this;
    if (JSON.stringify(options) != '{}'){
      let sceneid = decodeURIComponent(options.scene);
      wx.setStorageSync('sceneid', sceneid);
    }else{
      wx.setStorageSync('sceneid','');
    }
    //自动定位
    wx.getLocation({
      type: 'gcj02', //返回可以用于wx.openLocation的经纬度
      altitude: 'true',
      success(res) {
        that.setData({
          latitude: res.latitude, 
          longitude: res.longitude
        });
        that.getData();
        that.getStore();
      },
      fail: function () {
         
      },
      complete: function () {
      }
    });

  },
  getData:function(){
    let that = this;
    util.request(api.IndexUrlBanner).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          banner: res.data.banner
        });
      }
    });   
  },
  getStore:function(){
    let that=this;
    util.request(api.Stroelist, { lat: that.data.latitude, lon: that.data.longitude }, 'POST', 'application/json').then(function (res) {
      if(res.flg==1){ 
        that.setData({
          storelist: res.stroeEntityList 
        });
      }
    });
  },
  search:function(){
    wx.navigateTo({
      url: '/pages/searchindex/searchindex',
    })
  },
  //手动定位
  getLocation: function () {
    let that=this;
    wx.chooseLocation({
      success: function (res) {
        var name = res.name;
        var address = res.address;
        var latitude = res.latitude;
        var longitude = res.longitude;
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude
        });
        that.getStore();
      },
      fail: function (res) {

      },
      complete: function () {

      }
    })
  },
  //订餐的二级页面跳转
  Totakeoutsecond: function(event){ 
    let that=this;
    let obj={ 
      name: event.currentTarget.dataset.name,
      latitude:that.data.latitude,  
      longitude:that.data.longitude,
      type:event.currentTarget.dataset.type
    }
    wx.navigateTo({
      url: '/pages/takeoutsecond/takeoutsecond?obj='+JSON.stringify(obj)
    })
  },
  tomerchant: function(event){
    let that=this;
    let obj = {
      name: event.currentTarget.dataset.name,
      id: event.currentTarget.dataset.id,
      longitude: that.data.longitude,
      latitude: that.data.latitude
    }
    wx.navigateTo({
      url: '/pages/takeout/takeout?obj=' + JSON.stringify(obj)
    })
  },
  showdetail: function(){
    
  },
  /**购物车 */
  tocart: function () {
    wx.navigateTo({
      url: '/pages/cart/cart'
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },
 
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})