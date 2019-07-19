 // pages/cloud/cloud.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */ 
  data: {
    banner: [],
    itemlist: []
  },
   

  /**
   * 生命周期函数--监听页面加载
   */ 
  onLoad: function (options) { 
    
  },
  getIndexData: function () {
    let that=this;
    // util.request(api.Cloudclassroombaner, {},'POST', 'application/json').then(function (res) {
    //   console.log(res);
    // if (res.errno === 0) {
    //   data.banner = res.data.banner
    //   that.setData(data);
    // }
    // });
    util.request(api.Cloudclassroomlist, { page:1, size:1}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          itemlist:res.data.data,
          banner:res.data.data
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
    this.getIndexData();
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
    let that = this;
    util.request(api.Cloudclassroomlist, { page: 1, size: 1 }, 'POST', 'application/json').then(function (res) {
      wx.stopPullDownRefresh();
      if (res.errno === 0) {
        that.setData({
          itemlist: res.data.data,
          banner: res.data.data
        });
      }
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

  },
  tocart: function(){
    wx.navigateTo({
      url: '/pages/cart/cart'
    })
  },
  Tocloudetail: function(event){
    wx.navigateTo({
      url: '/pages/clouddetail/clouddetail?id=' + event.currentTarget.dataset.id 
    })
  }
})