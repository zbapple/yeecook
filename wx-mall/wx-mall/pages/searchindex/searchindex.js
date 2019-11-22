// pages/searchindex/searchindex.js
Page({

  /** 
   * 页面的初始数据
   */
  data: {
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  search:function(event){
    let name=event;
  },
  confirm:function(event){
    let that=this;
    let list=this.data.list;
    list.push(event.detail.value);
    this.setData({
      list:list
    });
    try {
      wx.setStorageSync('searchlist', list);
    } catch (e) {

    }
  },
  detele:function(){
      let that=this;
      let list=[];
      that.setData({
        list:list
      });
      try {
        wx.removeStorageSync('searchlist');
      } catch (e) {
        // Do something when catch error
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
    try {
      let searchlist = wx.getStorageSync('searchlist');
      if (searchlist) {
        this.setData({
          list: searchlist
        });
      }
    } catch (e) {
      // Do something when catch error
    }
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