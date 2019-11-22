// pages/takeoutgroup/takeoutgroup.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const pay = require('../../services/pay.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mobile:'',
    checkedAddress:{},
    addressId: 0,
    couponId: 0,
    checkedAddress:[],
    shopname:'',
    stroephone:'',
    addressname:'',
    titlenum:0, 
    shodw:false,
    datatime: '',
    selecttime: '', 
    remark: '',
    usefood:'',
    bookday:'',
    istype:false,
    items: [
      { name: 'zhong', value: '中' }, 
      { name: 'wan', value: '晚' },
    ],
    lisechecked:[],
    booktype:50,
    listtype:[30,40,50,55,65,78,89,90,100]
  },
  selecttpeshow:function(){
    this.setData({
      istype:true
    });
  }, 
  selecttpeclone:function(){
    this.setData({ 
      istype: false
    });
  },
  checkboxChange:function(event){
    let that=this;
    this.setData({
      lisechecked: event.detail.value
    });
    let numtype = 0;
    if (that.data.lisechecked.length == 2) {
      numtype = 2;
    }
    if (that.data.lisechecked.length == 1) {
      if (that.data.lisechecked[0].value == 'wan') {
        numtype = 1;
      } else {
        numtype = 0;
      }
    }
    this.setData({ 
      selectnumtype:numtype
    });
  },
  toremark: function () {
    wx.navigateTo({
      url: '/pages/takeoutremark/takeoutremark?remark=' + this.data.remark,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({
      title: '订餐计划',
    });
    this.setData({
      id:options.id
    });
    this.getTime();
  },
  getGroupData:function(){
    let that=this;
    let buyType = this.data.isBuy ? 'buy' : 'cart'
    util.request(api.Checklet, { addressId: that.data.addressId, couponId: that.data.couponId, type: buyType, stroeid: that.data.id }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          checkedAddress: res.data.checkedAddress, 
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
  group_pay:function(){
    let that=this;
    let numtype=0;
    if(that.data.lisechecked.length==2){
      numtype=2;
    }
    if (that.data.lisechecked.length==1){
      if (that.data.lisechecked[0].value=='wan'){
        numtype=1;
      }else{
        numtype=0;
      }
    }
    if (that.data.addressId <= 0 && that.data.titlenum==0) {
        util.showErrorToast('请选择收货地址');
        return false;
      }
    if (that.data.usefood==''){
      util.showErrorToast('请输入用餐人数');
      return false;
    }
    if (that.data.bookday == '') {
      util.showErrorToast('请输入预定天数');
      return false;
    }
    if (that.data.lisechecked.length==0){
      util.showErrorToast('请选择套餐类型');
      return false;
    }
    util.request(api.MeanuGroup, { num: that.data.titlenum, population: that.data.usefood, specification: that.data.booktype, fate: that.data.bookday, chacke: numtype, addressId: that.data.addressId}, 'POST').then(function (res) {
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
  },
  bindf:function(event){
    this.setData({
      usefood: event.detail.value
    });
  },
  bindbookday: function (event){
    this.setData({
      bookday: event.detail.value
    });
  },
  selectTime: function () {
    let time = this.data.datatime;
    let list = [time];
    let timehours = this.data.timehours;
    let timeminutes = parseInt(this.data.timeminutes);
    for (let i = 1; i < 10; i++) {
      timeminutes = timeminutes + 30;
      if (timeminutes >= 60) {
        timeminutes = timeminutes - 60;
        timehours = timehours + 1;
        if(timeminutes<10){
          timeminutes='0'+timeminutes;
        }
      }
      if (timehours >= 24) {
        timehours = timehours - 24;
      }
      list.push(timehours + ':' + timeminutes);
    }
    this.setData({
      listtime: list,
      shodw: true
    });
  },
  selecttype:function(event){
    let num = event.currentTarget.dataset.num;
    this.setData({
      booktype: num,
      istype: false
    });
  },
  /**选择时间 */
  selectitem: function (event) {
    let time = event.currentTarget.dataset.time;
    this.setData({
      selecttime: time,
      shodw: false
    });
  },
  /**关闭 */
  clone: function () {
    this.setData({
      shodw: false
    }); 
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
      if(timeminutes<9){
        timeminutes='0'+timeminutes;
      }
      timehours = timehours + 1;
    }
    if (timehours >= 24) {
      timehours = timehours - 24;
    }
    this.setData({
      datatime: timehours + ':' + timeminutes,
      timehours: timehours,
      timeminutes: timeminutes,
      selecttime: timehours + ':' + timeminutes
    });
  },
  /**选择菜单 */
  selectmenu: function (event) {
    let num = event.currentTarget.dataset.num
    if (num != this.data.titlenum) {
      this.setData({
        titlenum: num
      });
    }
    if (num == 1 && this.data.mobile == '') {
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
    try {
      let mobile = wx.getStorageSync('mobile');
      if (mobile) {
        this.setData({
          mobile: mobile
        });
      }
    } catch (e) { };
    this.getGroupData();
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