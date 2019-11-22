// pages/cookdetails/cookdetails.js
const app = getApp();
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */ 
  data: {
    selected: [
      {
        date: '2018-5-21'
      }, {
        date: '2018-5-22'
      }, {
        date: '2018-5-24'
      }, {
        date: '2018-5-25'
      }
    ],
    zcpwl:0,
    zclist: [],
    zclistadd:[],
    wcpwl:0,
    wclist: [],
    wclistadd:[],
    wancpwl:0,
    wanclist: [],
    wanclistadd:[],
    date:'',
    cateringServiceOrgName:'',
    menuCoverPic:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let date=getNowFormatDate();
    this.setData({
      date:date
    });
    this.getIndexData();
  },
  getIndexData: function () {
    var that=this;
    util.request(api.Usernutritioninfo, {}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          cateringServiceOrgName:res.data.userNutritionMenuVoList[0].cateringServiceOrgName,
          menuCoverPic: res.data.userNutritionMenuVoList[0].menuCoverPic,
          menuName: res.data.userNutritionMenuVoList[0].menuName,
          nutritionPrinciple: res.data.userNutritionMenuVoList[0].nutritionPrinciple
        }) 
        
      } 
    });
    util.request(api.MenuDetailstodayinfo, { todays: that.data.date}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
         let list=res.data;    
          for(let i=0;i<list.length;i++){
            if(list[i].menuType=='0'){
              that.setData({
                zcpwl: list[i].sumcal,
                zclist:list[i].zhengcan,
                zclistadd:list[i].jiacan
              });
            }else if(list[i].menuType=='1'){
              that.setData({
                wcpwl: list[i].sumcal,
                wclist: list[i].zhengcan,
                wclistadd: list[i].jiacan
              });
            }else if(list[i].menuType=='2'){
              that.setData({
                wancpwl: list[i].sumcal,
                wanclist: list[i].zhengcan,
                wanclistadd: list[i].jiacan
              });
            }
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
    var that = this;
    let p = new Promise(function (resolve, reject){
      util.request(api.Usernutritioninfo, {}, 'POST', 'application/json').then(function (res) {
        resolve();
        if (res.errno === 0) {
          that.setData({
            cateringServiceOrgName: res.data.userNutritionMenuVoList[0].cateringServiceOrgName,
            menuCoverPic: res.data.userNutritionMenuVoList[0].menuCoverPic,
            menuName: res.data.userNutritionMenuVoList[0].menuName,
            nutritionPrinciple: res.data.userNutritionMenuVoList[0].nutritionPrinciple
          })

        }
      });
    });
    p.then(function(){
      util.request(api.MenuDetailstodayinfo, { todays: that.data.date }, 'POST', 'application/json').then(function (res) {
        wx.stopPullDownRefresh();
        if (res.errno === 0) {
          let list = res.data;
          for (let i = 0; i < list.length; i++) {
            if (list[i].menuType == '0') {
              that.setData({
                zcpwl: list[i].sumcal,
                zclist: list[i].zhengcan,
                zclistadd: list[i].jiacan
              });
            } else if (list[i].menuType == '1') {
              that.setData({
                wcpwl: list[i].sumcal,
                wclist: list[i].zhengcan,
                wclistadd: list[i].jiacan
              });
            } else if (list[i].menuType == '2') {
              that.setData({
                wancpwl: list[i].sumcal,
                wanclist: list[i].zhengcan,
                wanclistadd: list[i].jiacan
              });
            }
          }
        }
      });
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
  bindselect:function(e) {
    let num = e.detail.ischeck;
  },
  /**
   * 获取选择日期
   */
  bindgetdate:function(e) {
    let time = e.detail.year+'-'+e.detail.month+'-'+e.detail.date;
    var that = this;
    util.request(api.MenuDetailstodayinfo, { todays: time}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        let list = res.data;
        for (let i = 0; i < list.length; i++) {
          if (list[i].menuType == '0') {
            that.setData({
              zcpwl: list[i].sumcal,
              zclist: list[i].zhengcan,
              zclistadd: list[i].jiacan
            });
          } else if (list[i].menuType == '1') {
            that.setData({
              wcpwl: list[i].sumcal,
              wclist: list[i].zhengcan,
              wclistadd: list[i].jiacan
            });
          } else if (list[i].menuType == '2') {
            that.setData({
              wancpwl: list[i].sumcal,
              wanclist: list[i].zhengcan,
              wanclistadd: list[i].jiacan
            });
          }
        }
      }
    });
  },
  Todetail: function(event){
    wx.navigateTo({
      url: '/pages/cookfood/cookfood?id=' + event.currentTarget.dataset.id,
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