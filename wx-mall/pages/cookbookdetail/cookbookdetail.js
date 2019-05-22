// pages/cookbookdetail/cookbookdetail.js
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:'小米',
    foodlist: [],
    mutilist:[],
    seasanlist:[],
    carbohydrate: {},
    protein: {},
    fat: {},
    makelist: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        name:options.name
      })
    this.getdetailinfo();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  getdetailinfo: function(){
    var that = this;
    var data = new Object();
    util.request(api.Cookbookdetail, { mfoodName: that.data.name}).then(function (res) {
      if (res.errno === 0) {
        data.mutilist = that.deelData(res.data[0].nutrients) ;
        data.foodlist = that.deelData(res.data[0].foodMaterial);
        data.makelist = that.deelstep(res.data[0].prepareMaterial);
        that.setData(data);
        that.setData({
          carbohydrate: that.data.mutilist[3],
          protein: that.data.mutilist[1],
          fat: that.data.mutilist[2]
        });
      }
    });
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
  findmuti: function(){
    console.log(this.data.mutilist)
    wx.navigateTo({
      url: '/pages/cookbookmuti/cookbookmuti?list=' + JSON.stringify(this.data.mutilist),
    })
  },
  deelData: function(event){
    var sumlist=new Array();
    var resultlist=new Array();
    sumlist = event.split(",")
    for (var i = 0; i < sumlist.length;i=i+2){
      var obj = new Object();
      obj['name'] = sumlist[i];
      obj['number']= sumlist[i+1];
      resultlist.push(obj);
    }
    return resultlist;
  },
  deelstep: function (event){
    var sumlist = new Array();
    var resultlist = new Array();
    sumlist = event.split(",")
    return sumlist;
  }
})