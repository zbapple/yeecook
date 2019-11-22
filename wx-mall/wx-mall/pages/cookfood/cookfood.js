const app = getApp();
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    circle: { 
      left_one: '',
      right_one: '',
      left_two: '',
      right_two: '',
      left_three: '',
      right_three: '',
      one:0,
      two:0,
      three:0, 
    },
    id:'',
    foodingredients:[],
    Dishesdishesinfo:'',
    dishesStepsVos:[],
    show:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let obj = {}; 
    this.setData({
      id:options.id
    });
    this.getIndexData();
    
  },
  getIndexData(){
    let that=this;
    util.request(api.Dishesdishesinfo, { dishesid:that.data.id}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          Dishesdishesinfo:res.data[0]
        });
      }
    });
    util.request(api.DishesSteps, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
          that.setData({
            dishesStepsVos: res.data.dishesStepsVos
          });
      }
    });
    util.request(api.Nutrientelementsproportion, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
      
      if (res.errno === 0) {
        let obj={};
        let three = that.calculate(res.data.ratioprotein);//蛋白质
        let one = that.calculate(res.data.ratoco2);//碳水化合物
        let two = that.calculate(res.data.ratiofat);//脂肪
        obj.left_one = one.left;
        obj.right_one = one.right;
        obj.left_two = two.left;
        obj.right_two = two.right;
        obj.left_three = three.left;
        obj.right_three = three.right;
        obj.three = (res.data.ratioprotein * 100).toFixed(2);
        obj.one = (res.data.ratoco2 * 100).toFixed(2);
        obj.two = (res.data.ratiofat * 100).toFixed(2);
        that.setData({
          circle: obj,
        });
      }
    });
    util.request(api.Foodingredientsinfo, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
          that.setData({
            foodingredients:res.data,
            show: true
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
    let that = this;
    let p=new Promise(function(resolve,reject){
      util.request(api.Dishesdishesinfo, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            Dishesdishesinfo: res.data[0]
          });
        }
        resolve();
      });
    });
    p.then(function(){
      util.request(api.DishesSteps, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            dishesStepsVos: res.data.dishesStepsVos
          });
        }
        return 
      }).then(function(){
        util.request(api.Nutrientelementsproportion, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {

          if (res.errno === 0) {
            let obj = {};
            let three = that.calculate(res.data.ratioprotein);//蛋白质
            let one = that.calculate(res.data.ratoco2);//碳水化合物
            let two = that.calculate(res.data.ratiofat);//脂肪
            obj.left_one = one.left;
            obj.right_one = one.right;
            obj.left_two = two.left;
            obj.right_two = two.right;
            obj.left_three = three.left;
            obj.right_three = three.right;
            obj.three = (res.data.ratioprotein * 100).toFixed(2);
            obj.one = (res.data.ratoco2 * 100).toFixed(2);
            obj.two = (res.data.ratiofat * 100).toFixed(2);
            that.setData({
              circle: obj,
            });
          }
          return;
        });
      }).then(function(){
        util.request(api.Foodingredientsinfo, { dishesid: that.data.id }, 'POST', 'application/json').then(function (res) {
          wx.stopPullDownRefresh();
          if (res.errno === 0) {
            that.setData({
              foodingredients: res.data,
              show: true
            });
          }
        });
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
  calculate: function(num){
    let obj={};
    let angel=num*360;
    if(angel>=180){
      obj.right='0deg';
      obj.left =angel-360+'deg';
    }else{
      obj.left ='-180deg';
      obj.right=angel-180+'deg';
  
    }
    return obj;
  },
  assigned: function(obj){
    this.setData({
      circle:obj
    });
  },
  tomutrition: function(event){
    wx.navigateTo({
      url: '/pages/cookfoodmutrition/cookfoodmutrition?id=' + event.currentTarget.dataset.id,
    })
  }
})