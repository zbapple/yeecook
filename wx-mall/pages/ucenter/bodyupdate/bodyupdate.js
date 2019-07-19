 // pages/ucenter/bodyupdate/bodyupdate.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
Page({

  /** 
   * 页面的初始数据
   */
  data: {
    date:'',
    height:'',
    weight:''
  }, 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // this.getIndexData();
  },
  getIndexData: function () {
    let that = this;
    util.request(api.Userbodyinfo,{},'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          weight: res.data.bodyinfolist.goalweight,
          height: res.data.bodyinfolist.userheight,
          date: res.data.userBodyInfo[0].userBirthday
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
  chageheight: function(e){
    this.setData({
      height:e.detail.value
    });
  },
  bindDateChange: function (e) {
    this.setData({
      date: e.detail.value
    })
  },
  chageweight: function(e){
    this.setData({
      weight: e.detail.value
    });
  },
  update: function () {
    let that = this;
    let date = new Date(that.data.date)
    util.request(api.Userbodyupdate, { goalWeight: that.data.weight, userHeight: that.data.height, userBirthday: date},'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        wx.switchTab({
          url: '/pages/ucenter/index/index',
        })
      }
    }); 
    }
})