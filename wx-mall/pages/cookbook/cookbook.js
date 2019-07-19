// pages/cookbook/cookbook.js
const app = getApp();
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
Page({

  /**
   * 页面的初始数据
   */
  data: { 
    sorce:'--', 
    weight:'--',
    bmi:'--', 
    pwnum:400, 
    zcpwnum:0,
    zclist: [],
    wcpwnum:0,
    wclist:[],
    wancpwnum:0,
    wanclist:[],
    showfood:true,
    showscal:false,
    showcompute:true,
    number:1,
    userheight:0,
    userbody:'' 
  }, 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) { 
  }, 
  getIndexData: function(){
    //获取当前的时间
    var dateTime =getNowFormatDate();
    var that=this;
    let p1 = new Promise(function (resolve, reject) {
      util.request(api.Userbodyinfo, {}, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
            that.setData({
              sorce: res.data.bodyinfolist.goalweight,
              weight: res.data.bodyinfolist.weight,
              bmi: res.data.bodyinfolist.bmi 
            });
          resolve();
        }else{
          reject();
        }
      });
    });
    p1.then(function(){
      util.request(api.MenuDetailsmenuinfo, { today: dateTime }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          if (res.data.flag == 1) {
            that.setData({
              pwnum: res.data.sum,
              zcpwnum: res.data.countgood,
              zclist: res.data.beabreakfastlist,
              wcpwnum: res.data.couteve,
              wclist: res.data.namemLunchlistap1,
              wancpwnum: res.data.sumcalluch,
              wanclist: res.data.Dinnerlist
            });
          } else {
            that.setData({
              showfood: true,
              showcompute: false
            })
          }
        }
      });
      util.request(api.Userdetectioncycleinfo, { datetime: dateTime }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          if (res.data.flag == 1) {
            if (res.data.state == 0) {
              that.setData({
                showscal: true,
                showfood: false
              });
            } else {
              that.setData({
                number: res.data.cout,
                showscal: false,
                showfood: true
              })
            }
          } else {
            that.setData({
              showscal: false,
              showfood: true
            })
          }
        }
      });
    });
    p1.catch(function () {
    
    });
  }
,
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
    let that=this;
    var dateTime = getNowFormatDate();
    let p1 = new Promise(function (resolve, reject) {
      util.request(api.Userbodyinfo, {}, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            sorce: res.data.bodyinfolist.goalweight,
            weight: res.data.bodyinfolist.weight,
            bmi: res.data.bodyinfolist.bmi
          });
          resolve();
        } else {
          reject();
        }
      });
    });
    p1.then(function () {
      util.request(api.MenuDetailsmenuinfo, { today: dateTime }, 'POST', 'application/json').then(function (res) {
        wx.stopPullDownRefresh();
        if (res.errno === 0) {
          if (res.data.flag == 1) {
            that.setData({
              pwnum: res.data.sum,
              zcpwnum: res.data.countgood,
              zclist: res.data.beabreakfastlist,
              wcpwnum: res.data.couteve,
              wclist: res.data.namemLunchlistap1,
              wancpwnum: res.data.sumcalluch,
              wanclist: res.data.Dinnerlist
            });
          } else {
            that.setData({
              showfood: true,
              showcompute: false
            })
          }
        }
      });
      util.request(api.Userdetectioncycleinfo, { datetime: dateTime }, 'POST', 'application/json').then(function (res) {
        wx.stopPullDownRefresh();
        if (res.errno === 0) {
          if (res.data.flag == 1) {
            if (res.data.state == 0) {
              that.setData({
                showscal: true,
                showfood: false
              });
            } else {
              that.setData({
                number: res.data.cout,
                showscal: false,
                showfood: true
              })
            }
          } else {
            that.setData({
              showscal: false,
              showfood: true
            })
          }
        }
      });
    })
    p1.catch(function(){
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

  },
  toscal:function(event){
      let id=1;
      wx.navigateTo({
        url: '/pages/ucenter/scale/scale', 
      }); 
  },
  toreport: function(){
    wx.navigateTo({
      url: '/pages/report/report', 
    })
  },
  todetail: function(){
    wx.navigateTo({
      url: '/pages/cookdetails/cookdetails',
    })
  }
})
function getNowFormatDate() {
  var date = new Date();
  var seperator1 = "-";
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var strDate = date.getDate(); 
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  var currentdate = year + seperator1 + month + seperator1 + strDate;
  return currentdate;
}