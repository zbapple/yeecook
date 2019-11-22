// pages/takeoutsubmit/takeoutsubmit.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const pay = require('../../services/pay.js');
Page({

  /**
   * 页面的初始数据 
   */
  data: {
    id:'',
    addressId: 0,
    couponId: 0,
    isBuy:false,
    titlenum:0, 
    addrees:1,
    datatime:'',
    selecttime:'',
    listnum:3,
    shopname:'',
    shodw:false,
    listtime:[],
    timehours:0,
    timeminutes:0, 
    ismore:true,
    remark:'',
    checkedAddress:{}, 
    checkedGoodsList:[],
    freightPrice:0,
    actualPrice:0,
    addressname:'',
    stroephone:'',
    selectnumtype:0,
    mobile:''
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.id)
    wx.setNavigationBarTitle({
      title: '提交订单',
    });
    this.setData({
      id:options.id
    });
    this.getTime();
    try {
      wx.removeStorageSync('remarkmessage'); 
    } catch (e) {
      // Do something when catch error
    }
  },
  gettakesubmit:function(){ 
    let that = this;
    var url = api.CartCheckout
    let buyType = this.data.isBuy ? 'buy' : 'cart'
    util.request(api.Checklet, { addressId: that.data.addressId, couponId: that.data.couponId, type: buyType, stroeid:that.data.id }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          checkedGoodsList: res.data.checkedGoodsList,
          checkedAddress: res.data.checkedAddress,
          freightPrice: res.data.freightPrice,
          actualPrice: res.data.goodsTotalPrice,
        }); 
        //设置默认收获地址
        if (that.data.checkedAddress.id) {
          let addressId = that.data.checkedAddress.id;
          if (addressId) {
            that.setData({ addressId: addressId });
          }
        } 
      }
    });
    util.request(api.Stroeaddress, { id: that.data.id }, 'POST', 'application/json').then(function (res) {
      that.setData({
        shopname: res.storeName,
        addressname: res.province + res.city + res.address,
        stroephone: res.stroephone
      });
    });
  },
  submitOrder: function () {
    let that=this;
    let buyType = this.data.isBuy ? 'buy' : 'cart'
    if(that.data.titlenum==0){
      if (this.data.addressId <= 0) {
        util.showErrorToast('请选择收货地址');
        return false;
      }
      util.request(api.Mealsubmit, { addressId: that.data.addressId, couponId: that.data.couponId, type: buyType, num:0 }, 'POST').then(res => {
        if (res.errno === 0) {
          const orderId = res.data.orderInfo.id;
          pay.Mealprepay(parseInt(orderId)).then(res => {
            wx.redirectTo({
              url: '/pages/payResult/payResult?status=1&orderId=' + orderId
            });
          }).catch(res => {
            wx.redirectTo({
              url: '/pages/payResult/payResult?status=0&orderId=' + orderId
            });
          });
        } else {
          util.showErrorToast('下单失败');
        }
      });
    }else{
      if (this.data.mobile==''){
        util.showErrorToast('预留电话不可为空');
      }
      util.request(api.Mealsubmit, { addressId: that.data.addressId, couponId: that.data.couponId, type: buyType,num:1}, 'POST').then(res => {
        if (res.errno === 0) {
          const orderId = res.data.orderInfo.id;
          pay.Mealprepay(parseInt(orderId)).then(res => {
            wx.redirectTo({
              url: '/pages/payResult/payResult?status=1&orderId=' + orderId
            });
          }).catch(res => {
            wx.redirectTo({
              url: '/pages/payResult/payResult?status=0&orderId=' + orderId
            });
          });
        } else {
          util.showErrorToast('下单失败');
        }
      });
    }
  },
  toremark:function(){
    wx.navigateTo({
      url: '/pages/takeoutremark/takeoutremark?remark='+this.data.remark,
    })
  },
  toselectaddress:function(){
    wx.navigateTo({
      url: '/pages/shopping/address/address',
    })
  },
  getaddress: function(){
    
  },
  /**选择菜单 */
  selectmenu:function(event){
    let num = event.currentTarget.dataset.num
    if(num!=this.data.titlenum){
      this.setData({
        titlenum:num
      });
    }
    if(num==1&&this.data.mobile==''){
      wx.showModal({
        title: '提示',
        content: '请输入手机号码',
        success(res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '/pages/auth/mobile/mobile?num=0',
            })
          } else if (res.cancel) {
            
          }
        }
      })
    }
  }, 
  /**计算送达时间 */
  getTime: function () {
    let myDate = new Date();
    let timehours;
    let timeminutes;
    timehours = myDate.getHours();
    timeminutes = myDate.getMinutes();
    timeminutes = timeminutes + 30;
    if (timeminutes >= 60) {
      timeminutes = timeminutes - 60;
      timehours = timehours + 1;
    }
    if (timehours>=24){
      timehours=timehours-24;
    }
    this.setData({
      datatime: timehours + ':' + timeminutes,
      timehours: timehours,
      timeminutes: timeminutes,
      selecttime: timehours + ':' + timeminutes
    });
  },
  /**查看更多 */
  listmore:function(){
    this.setData({
      listnum: this.data.checkedGoodsList.length,
      ismore:false
    })
  },
  /**显示选择时间 */
  selectTime: function(){
    let time = this.data.datatime;
    let list = [time];
    let timehours = this.data.timehours;
    let timeminutes = this.data.timeminutes;
    for(let i=1;i<10;i++){
      timeminutes = timeminutes + 30;
      if (timeminutes >= 60) {
        timeminutes = timeminutes - 60;
        timehours = timehours + 1;
      }
      if (timehours >= 24) {
        timehours = timehours - 24;
      }
      list.push(timehours + ':' + timeminutes);
    }
    this.setData({
      listtime:list,
      shodw:true
    });
  },
  /**选择时间 */
  selectitem: function(event){
    let time=event.currentTarget.dataset.time;
    this.setData({
      selecttime:time,
      shodw:false
    });
  },
  /**关闭 */
  clone: function(){
    this.setData({
      shodw: false
    });
  },
  listmoreclone:function(){
    this.setData({
      ismore: true,
      listnum:3
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
    try {
      let remarkmessage = wx.getStorageSync('remarkmessage');
      if (remarkmessage) {
        this.setData({
          remark: remarkmessage
        });
      }
    } catch (e) {
      // Do something when catch error
    }
    try {
      var addressId1 = wx.getStorageSync('addressId');
      if (addressId1) {
        this.setData({
          addressId: addressId1
        });
      }
    } catch (e) {
      // Do something when catch error
    }
    try{
      let mobile = wx.getStorageSync('mobile');
      if(mobile){
        this.setData({
          mobile: mobile
        });
      }
    }catch(e){};
    this.gettakesubmit();
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
    try {
      wx.removeStorageSync('remarkmessage');
    } catch (e) {
      // Do something when catch error 
    }
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