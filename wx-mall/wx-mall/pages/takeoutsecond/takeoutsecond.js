// pages/takeoutsecond/takeoutsecond.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
Page({

  /** 
   * 页面的初始数据
   */
  data: {
    latitude: '',
    longitude: '',
    type:'',
    storelist:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (JSON.stringify(options)!='{}'){
      let obj = JSON.parse(options.obj);
      this.setData({
        latitude: obj.latitude,
        longitude: obj.longitude,
        type:obj.type
      });
      wx.setNavigationBarTitle({ 
        title: obj.name
      })
    }
    this.getTakeoutsecondData();
  },
  getTakeoutsecondData(){
    let that = this;
    util.request(api.Stroelist, { lat: that.data.latitude, lon: that.data.longitude, storeType:that.data.type }, 'POST', 'application/json').then(function (res) {
      if (res.flg == 1) {
        that.setData({
          storelist: res.stroeEntityList
        });
      }
    });
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
        that.getTakeoutsecondData();
      },
      fail: function (res) {

      },
      complete: function () {

      }
    })
  },
  tocart: function () { 
    wx.navigateTo({
      url: '/pages/cart/cart'
    })
  },
  search: function () {
    wx.navigateTo({
      url: '/pages/searchindex/searchindex',
    })
  },
  tomerchant: function (event) {
    let that=this;
    let obj = {
      name: event.currentTarget.dataset.name,
      id:event.currentTarget.dataset.id,
      longitude: that.data.longitude,
      latitude: that.data.latitude
    }
    wx.navigateTo({
      url: '/pages/takeout/takeout?obj=' + JSON.stringify(obj)
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