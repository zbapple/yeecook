// pages/cookbook/cookbook.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showModalStatus:false,
    srcoll:false,
    srcolltop:false,
    delivery_type:'',
    feeding_type:'',
    scrollTop: 0,
    scrollHeight:0,
    titleHeight:0,
    box_top:'',
    title_position:'',
    title_top:'',
    list: [],
    listlength:true,
    src_img: [{ "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/0150179464907a.jpg"},
      { "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/0150548877f7ec.jpg"},
      {  "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/015142269b09f8.jpg"},
      { "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/015238796618be.jpg"},
      { "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/01531187b23df.jpg"},
      { "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/0153296614819f.jpg"},{
        "src":"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190523/015508534e2e7f.jpg"
      }]
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    var self = this;
    this.getIndexData();
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getIndexData();
  },
  getIndexData: function(){
    let that = this;
    var data = new Object();
    that.setData({
      listlength:true
    })
    util.request(api.Cookbook, { deliveryWay: that.data.delivery_type, feedingWay:that.data.feeding_type}).then(function (res) {
      if (res.errno === 0) {
        data.list = that.ListSort(res.data);
        console.log(res.data.length)
        if(!res.data.length){
          data.listlength=false;
        }
        that.setData(data);
      }
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var query = wx.createSelectorQuery();
    var that = this;
    query.select('#cook_one_moth').boundingClientRect();
    query.exec(function (res) {
      that.setData({
        scrollHeight: res[0].top,
        titleHeight: res[0].height
      });
    })
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
  select: function(){
    if (!this.data.showModalStatus){
      this.showModal();
    }else{
      this.hideModal();
    }
    
  },
  showModal: function () {
    // 显示遮罩层
    var animation = wx.createAnimation({
      duration: 500,
      timingFunction: "linear",
      delay: 0
    })
    this.animation = animation
    animation.height(0).step()
    this.setData({
      animationData: animation.export(),
      showModalStatus: true,
      srcoll: true
    })
    setTimeout(function () {
      animation.height('590rpx').step()
      this.setData({
        animationData: animation.export()
      })
    }.bind(this), 200)
  },
  hideModal: function () {
    // 隐藏遮罩层
    var animation = wx.createAnimation({
      duration: 500,
      timingFunction: "linear",
      delay: 0
    })
    this.animation = animation
    animation.height(0).step()
    this.setData({
      animationData: animation.export(),
    })
    setTimeout(function () {
      animation.height('590rpx').step()
      this.setData({
        animationData: animation.export(),
        showModalStatus: false,
        srcoll: false
      })
    }.bind(this), 200)
  },
  delivery: function(event){
    if (event.target.dataset.type){
      console.log(event.target.dataset.type)
      this.setData({
        delivery_type: event.target.dataset.type
      })
    }
  },
  feeding: function(event){
    if (event.currentTarget.dataset.type){
      console.log(event.currentTarget.dataset.type);
      this.setData({
        feeding_type: event.currentTarget.dataset.type
      })
    }
  },
  reset: function(event){
    this.setData({
      delivery_type:'',
      feeding_type:''
    })
  },
  submit: function(event){
    this.hideModal();
    this.getIndexData();
  },
  //监听屏幕滚动 判断上下滚动
  onPageScroll: function (event) {
    if (event.scrollTop >= this.data.scrollHeight){
      var topnum = this.data.titleHeight+'px';
      this.setData({
        title_position:"fixed",
        title_top:0,
        srcolltop: true
      });
    }else{
      this.setData({
        title_position:'',
        title_top:'',
        srcolltop:false
      });
    }
  },
  //排序数组
  ListSort: function(event){
      if(event.length>0){
        var temp;
        for(var i=0;i<event.length-1;i++){
          for(var j = 0; j<event.length-1-i;j++){
            if (event[j].nlevel > event[j + 1].nlevel){
              temp=event[j];
              event[j]=event[j+1];
              event[j+1]=temp;
            }
          }
        }
      }
      return event;
  }
})