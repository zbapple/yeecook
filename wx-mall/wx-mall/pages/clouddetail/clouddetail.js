// pages/clouddetail/clouddetail.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
Page({ 

  /**
   * 页面的初始数据 
   */
  data: {
    showplay:false,
    id:'',
    videoAdress:'', 
    videoCoverPic:'',
    videoDescribe:'',
    videoTitle:'', 
    videoSubtitle:'',
    goodsId:'',
    counterprice:'',
    listpicurl:'',
    name:'',
    retailprice:'',
    list:[],
    total:''
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
  getIndexData: function () {
    let that = this;
    util.request(api.Cloudclassroomdetail, { cloudid:that.data.id},'POST', 'application/json').then(function (res) {
    if (res.errno === 0) {
      that.setData({
        videoAdress: res.data.videoAdress,
        videoCoverPic: res.data.videoCoverPic,
        videoDescribe: res.data.videoDescribe,
        videoSubtitle: res.data.videoSubtitle,
        videoTitle: res.data.videoTitle
      });
    }
    });
    util.request(api.Cloudclassroomgoodinfo, { videoid: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          counterprice: res.data[0].counterprice,
          goodsId: res.data[0].goodsId,
          listpicurl: res.data[0].listpicurl,
          name: res.data[0].name,
          retailprice: res.data[0].retailprice
        });
      }
    });
    util.request(api.Usercommentsinfo, { videoid: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          total: res.data.total,
          list: res.data.userCommentsVoList
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
    let p = new Promise(function (resolve, reject){
      util.request(api.Cloudclassroomdetail, { cloudid: that.data.id }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            videoAdress: res.data.videoAdress,
            videoCoverPic: res.data.videoCoverPic,
            videoDescribe: res.data.videoDescribe,
            videoSubtitle: res.data.videoSubtitle,
            videoTitle: res.data.videoTitle
          });
        }
        resolve();
      });
    });
    p.then(function(){
      util.request(api.Cloudclassroomgoodinfo, { videoid: that.data.id }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            counterprice: res.data[0].counterprice,
            goodsId: res.data[0].goodsId,
            listpicurl: res.data[0].listpicurl,
            name: res.data[0].name,
            retailprice: res.data[0].retailprice
          });
        }
        return;
      });
    }).then(function(){
      util.request(api.Usercommentsinfo, { videoid: that.data.id }, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            total: res.data.total,
            list: res.data.userCommentsVoList
          });
        }
        wx.stopPullDownRefresh();
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
  buyshop: function(){

  },
  play: function(){
    var that=this;
    var videoplay = wx.createVideoContext('yeecook-video')
    videoplay.play()
    that.setData({
      showplay: true
    });
  },
  pause: function(){
    this.setData({
      showplay: false
    });
  },
  videoplay: function(){
    this.setData({
      showplay: true
    });
  },
  Toshopdetail: function(event){
    wx.navigateTo({
      url: '/pages/shopdetail/shopdetail?id=' + event.currentTarget.dataset.id,
    })
  }
})