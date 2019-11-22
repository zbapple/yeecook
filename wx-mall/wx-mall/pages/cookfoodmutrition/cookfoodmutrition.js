// pages/cookfoodmutrition/cookfoodmutrition.js
const app = getApp();
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) { 
      this.setData({
        id:options.id
      });
      this.getIndexData();
  },
  getIndexData: function(){
    var that=this;
    util.request(api.Dishesnutrieninfo, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          list:res.data
        });
      }
    });
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
    let that=this;
    util.request(api.Dishesnutrieninfo, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          list: res.data
        });
      }
      wx.stopPullDownRefresh();
    });
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