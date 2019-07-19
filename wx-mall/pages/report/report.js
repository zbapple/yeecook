// pages/report/report.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[],
    datetime:'',
    total:''
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getIndexData();
  },
  getIndexData: function () {
    var that = this; 
    util.request(api.Reportlist, {}, 'POST','application/json').then(function (res){
      if (res.errno === 0){
         that.setData({
           list: res.data,
         });
        let put = that.data.list;
         if(put.length>0){
           for (let i = 0; i < put.length;i++){
             put[i].updateTime = that.formatDate(put[i].updateTime);
           }
           that.setData({
             list:put
           });
         }
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
    util.request(api.Reportlist, {}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        wx.stopPullDownRefresh();
        that.setData({
          list: res.data,
        });
        let put = that.data.list;
        if (put.length > 0) {
          for (let i = 0; i < put.length; i++) {
            put[i].updateTime = that.formatDate(put[i].updateTime);
          }
          that.setData({
            list: put
          });
        }
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
  Toscal: function(){
    wx.navigateTo({
      url: '/pages/ucenter/scale/scale',
    })
  },
  Toreportdetail: function(event){
    var obj={
      id:event.currentTarget.dataset.id,
      datemin:event.currentTarget.dataset.datemin
    }
    wx.navigateTo({
      url: '/pages/reportdetail/reportdetail?obj='+JSON.stringify(obj),
    })
  },
  //转换为
  formatDate: function (value) { 
    var date = new Date(value);
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return m + '月' + d + '日';
  }
})