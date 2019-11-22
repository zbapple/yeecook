// pages/shopindex/shopindex.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sidebarnum:0, 
    scrolltop: 0,
    sidebar: ["热销"],
    banner: [],
    GoodsList: [],
    cartnum:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({
      title: '商城',
    });
    this.getIndexData();
  }, 
  search: function () {
    wx.navigateTo({
      url: '/pages/searchindex/searchindex', 
    })
  },
  ToshopDetail: function (event) {
    wx.navigateTo({
      url: '/pages/shopdetail/shopdetail?id=' + event.currentTarget.dataset.id,
    })
  },
  getIndexData: function () {
    let that = this;
    var data = new Object();
    util.request(api.CatalogList).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          sidebar: res.data.currentCategory.subCategoryList,
          GoodsList: res.data.goodsVo
        });
      }
    });
    util.request(api.CartList).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          cartnum: res.data.cartTotal.goodsCount
        });       
      }
    });
    util.request(api.IndexUrlBanner).then(function (res) {
      if (res.errno === 0) {
        data.banner = res.data.banner
        that.setData(data);
      }
    });
  },
  getSidebarData:function(id){
    let that=this;
    util.request(api.CatalogCurrent,{id:id},'POST').then(function (res) {
      if(res.errno==0){
        that.setData({
          GoodsList: res.data.goodsVo
        });
      }
    });
  },
  getCartnum: function(){
    let that=this;
    util.request(api.CartList).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          cartnum: res.data.cartTotal.goodsCount
        });
      }
    });
  },
  tocart: function () {
    wx.navigateTo({
      url: '/pages/cart/cart' 
    })
  },
  /**侧边栏 */
  onsidebar: function (event) {
    let that = this;
    let oldsidebarnum = that.data.sidebarnum;
    let name = event.currentTarget.dataset.name;
    let id=event.currentTarget.dataset.id;
    let changelist = [];
    that.setData({
      sidebarnum: event.currentTarget.dataset.num,
      sidebarname: name
    });
    if (oldsidebarnum != that.data.sidebarnum) {
      that.setData({
        list: changelist,
        scrolltop: 0
      });
      that.getSidebarData(id);
    }

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
    this.getCartnum();
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

  }
})