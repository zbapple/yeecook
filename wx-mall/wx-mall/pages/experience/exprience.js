// pages/experience/exprience.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    items: [{ name: "shun", value: '顺产' }, { name: "pou", value: '剖宫产' }, { name: "liu", value: '流产（俗称小产）' }],
    itemnum: [{ name: "one", value: '第1胎' }, { name: "two", value: '第2胎' }, { name: "three", value: '第3胎' }, { name: "four", value: '3胎以上' }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
  heightnum: function(){
    wx.navigateTo({
      url: '/pages/ucenter/scale/scale'
    })
  }
})