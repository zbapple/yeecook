// pages/ucenter/scale/scale.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../services/user.js');
var app = getApp();
const plugin = requirePlugin("yeecookscale") 
var setInter;
Page({

  /**
   * 页面的初始数据 
   */
  data: { 
    setInter:'',
    start:true,
    count:false,
    start_show: true,
    bluetooth: false,
    scla_error: false,
    measure:false,
    done:0, 
    userheight:0, 
    userbody:'',
    realTimeWeight: "",
    bleState: "空闲",
    state: "paused",
    measureData: null,
    score: "0",
    inputValue: '',
    returnValue: '',
    basicmetabolism:'',
    BMI: '',
    bodyfatrade: '',
    waterrate: '',
    bonemass: '',
    detectiontime: '',
    protein: '',
    skeletamuscle: '',
    subfatpercentage: '',
    updatetime: '',
    visfatgrade: '',
    weight: '',
    skeletalmuscle:''
  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that=this;
    let p1 = new Promise(function (resolve, reject) {
      util.request(api.Userbodyinfo, {}, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          if (res.data.userBodyInfo.length > 0) {
            that.setData({
              userheight: res.data.userBodyInfo[0].userHeight,
              userbody: res.data.userBodyInfo[0].userBirthday
            });
            resolve();
          } else {
            reject();
          }
        } else {
          reject();
        }
      });
    });
    p1.then(function (){
      let obj = {
        height: that.data.userheight,
        gender: 0,
        birthday: that.data.userbody,
        appid: "scsycwlkjyxgs2019",
        secret: "785cf4132e4ed538",
        version: 2
      };
      plugin.init(
        obj, data => {
          let { code, bleState, state, realTimeWeight } = data;
          if (code == 1014 || code == 1015) {
            that.setData({
              start_show: false,
              scla_error: true,
              bluetooth: false,
              measure: false
            });
          };
          if (code >= 1003 && code <= 1009) {
            if (code == 1003) {
              that.setData({
                start_show: false,
                scla_error: false,
                measure: false,
                bluetooth: true
              }); 
            }
            if (code == 1007) {
              that.setData({
                start_show: false,
                scla_error: true,
                bluetooth: false,
                measure:false
              });
            }
            if (code == 1005) {
              if (!that.data.showsult) {
                that.setData({
                  start_show: true,
                  scla_error: false,
                  bluetooth: false,
                  measure: false
                });
              }
            }
            if (code == 1008) { 
                that.setData({
                  measure:true,
                  start_show:false
                });
            }
            if (code == 1006) {

            }
            if (code == 1009) {
              that.setData({
                measure:false,
                start: false,
                scla_error: false,
                bluetooth: false,
                count: true
              });
            }
          }
          if (code == 1010) {
            that.setData({
              done:1,
              basicmetabolism: data.result.measure.bmr,
              BMI: data.result.measure.bmi,
              bodyfatrade: data.result.measure.bodyfat,
              waterrate: data.result.measure.water, 
              bonemass: data.result.measure.bone, 
              detectiontime: data.result.measure.measure_time, 
              protein: data.result.measure.protein, 
              skeletalmuscle: data.result.measure.muscle, 
              subfatpercentage: data.result.measure.subfat, 
              updatetime: data.result.measure.measure_time, 
              visfatgrade: data.result.measure.visfat, 
              weight: data.result.measure.weight,
            });
          }
        });
      plugin.start();
    });
    p1.catch(function(){
      wx.navigateTo({
        url: '/pages/ucenter/bodyadd/bodyadd?name=1', 
      })
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
    var that = this;
    setInter = setInterval(function () {
      if (that.data.done == 1) {
        that.setData({
          done:0
        });
        util.request(api.Userhealthreport, { basicmetabolism: that.data.basicmetabolism, BMI: that.data.BMI, bodyfatrade: that.data.bodyfatrade, waterrate: that.data.waterrate, bonemass: that.data.bonemass, detectiontime: that.data.detectiontime, protein: that.data.protein, skeletalmuscle: that.data.skeletalmuscle, subfatpercentage: that.data.subfatpercentage, updatetime: that.data.updatetime, visfatgrade: that.data.visfatgrade, weight: that.data.weight }, 'POST', 'application/json').then(function (res) {
          clearInterval(setInter);
          wx.redirectTo({
            url: '/pages/report/report',
          })
        });
      } 
    }, 1000);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    plugin.stop();
    clearInterval(setInter);
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },
  Adduserhealthreport: function(){

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
  custom: function(){
    
  },
  //重新称量体重
  againscal: function(){
    plugin.restart();
  },
  addbodyinfo: function(){
  }
})