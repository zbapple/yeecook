// pages/takeout/takeout.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
Page({
  /**
   * 页面的初始数据 
   */ 
  data: {
    id:'',
    top:'-50rpx',
    storelist:{},
    list: [],
    sidebar: [],
    cartlist: [], 
    sidebarnum:0, 
    sidebarname:'', 
    scrolltop:0,
    menubarnum:0,
    cartnum:0,
    showcart:false,
    mealdetailshow:false,
    mealdetail: '',
    feightprice:5,
    totalprice:0,
    istrue:false,
    paddingheight:0,
    menubartop:0,
    is_float_menubar:true,
    longitude:'',
    latitude:'',
    disprice:0,
    noCollectImage: "../static/images/icon_collect.png",
    hasCollectImage: "../static/images/icon_collect_checked.png",
    collectBackImage: "/static/images/icon_collect.png"
  }, 

  /**
   * 生命周期函数--监听页面加载
   */ 
  onLoad: function (options) {
    if(JSON.stringify(options)!='{}'){
      let obj = JSON.parse(options.obj);
      wx.setNavigationBarTitle({
        title: obj.name
      })
      this.setData({
        id:obj.id,
        longitude: obj.longitude,
        latitude: obj.latitude
      }); 
    }
    let name = this.data.sidebar[0];
    this.setData({
      sidebarname: name,
      totalprice:this.data.feightprice
    });  
    this.getTakeoutData();
  },
  getTakeoutData:function(){
    let that = this;
    util.request(api.Stroeid, { stroeid: that.data.id, lat:that.data.latitude,lon:that.data.longitude }, 'POST', 'application/json').then(function (res) {
      if(res.flg==1){
        that.setData({
          storelist: res.stroeentityList[0] 
        });
      }
    });
    util.request(api.Stroemenutype, { stroeid: that.data.id }, 'POST', 'application/json').then(function (res) {
      that.setData({
        sidebar: res.typeinfo,
        list: res.mealEntityList
      });
    });
  },
  getmealinfo:function(id){
    let that=this;
    let mealid=id;
    util.request(api.Stroemealinfo, { stroeid: that.data.id, mealid: mealid}, 'POST', 'application/json').then(function (res) {
      that.setData({
        list: res.mealEntityList
      });
    });
  },
  closeAttrOrCollect: function () {
    let that = this;
    if (this.data.openAttr) {
      this.setData({
        openAttr: false,
      });
      if (that.data.userHasCollect == 1) {
        that.setData({
          'collectBackImage': that.data.hasCollectImage
        });
      } else {
        that.setData({
          'collectBackImage': that.data.noCollectImage
        });
      }
    } else {
      //添加或是取消收藏
      util.request(api.Addordeletestroecart, { typeId: 0, stroeid: this.data.id }, "POST", "application/json")
        .then(function (res) {
          let _res = res;
          if (_res.errno == 0) {
            if (_res.data.type == 'add') {
              that.setData({
                'collectBackImage': that.data.hasCollectImage
              });
              wx.showToast({
                title: '收藏成功',
                icon: 'none',
                mask:true,
              })
            } else {
              that.setData({ 
                'collectBackImage': that.data.noCollectImage
              });
              wx.showToast({ 
                title: '已取消收藏',
                icon: 'none',
                mask: true,
              })
            }
          } else {
            wx.showToast({
              image: '/static/images/icon_error.png',
              title: _res.errmsg,
              mask: true
            });
          }

        });
    }

  },
  disprice:function(){
    let disprice=0;
    disprice = this.data.storelist.sendingfee - this.data.totalprice;
    disprice = disprice.toFixed(2);
    this.setData({
      disprice: disprice
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  togroup:function(event){
    let id=event.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/takeoutgroup/takeoutgroup?id='+id,
    })
  },
  /*菜单栏 */
  menubar_select: function(event){
    let that=this;
    let num = event.currentTarget.dataset.num;
    if (that.data.menubarnum!=num){
        that.setData({
          menubarnum:num,
        });
    }
  },
  getMealCart:function(){
    let that=this;
    util.request(api.GetMealCart, { stroeid: that.data.id}).then(function (res) {
      that.setData({
        cartlist: res.cartst,
        cartnum: res.cartTotal1.mealCount,
        totalprice: res.cartTotal1.mealsAmount + res.cartTotal1.deliveryfee
      });
      that.disprice();
    });
  },
  /*侧边栏 */
  onsidebar: function(event){
    let that=this;
    let oldsidebarnum=that.data.sidebarnum;
    let name = event.currentTarget.dataset.name;
    let id = event.currentTarget.dataset.id;
    let changelist=[];
    that.setData({
      sidebarnum: event.currentTarget.dataset.num,
      sidebarname: name
    });
    that.getmealinfo(id);
  },
  /*套餐详情 */
  mealdetail: function(event){
    let obj=event.currentTarget.dataset.obj;
    let that=this;
    that.setData({
      mealdetailshow:true,
      mealdetail:obj,
      istrue:true
    });
  },
  clonemealdetail: function(){
    let that = this;
    that.setData({
      mealdetailshow: false,
      istrue:false
    });
  },
  /* 加入购物车*/
  addcart: function(event){
    let id = event.currentTarget.dataset.id;
    let mealname = event.currentTarget.dataset.mealname;
    let price = event.currentTarget.dataset.price
    let that=this;
    let exist=false;
    let countcart = that.data.cartnum;
    let total = that.data.totalprice;
    util.request(api.Addstroe, { stroeid: that.data.id, mealid: id, number: 1 }, 'POST', 'application/json').then(function (res) {
      if(res.errno==0){
        that.setData({
          cartlist: res.data.cartst,
          cartnum: res.data.cartTotal1.mealCount,
          totalprice: res.data.cartTotal1.mealsAmount + res.data.cartTotal1.deliveryfee
        });
        that.disprice();
      }
    });
  },
  //*减少购物车数量*/
  minuscart:function(event){
    let that=this;
    let id = event.currentTarget.dataset.id;
    let countcart=that.data.cartnum;
    let total = that.data.totalprice;
    util.request(api.Minusstroe, { stroeid: that.data.id, mealid: id, number: 1 }, 'POST', 'application/json').then(function (res) { 
      that.getMealCart();
    });
    if(that.data.cartlist.length==0){
      that.setData({
        istrue:false,
        showcart:false,
        paddingheight: 0 
    })
    }
  },
  /**清空购物车 */
  clearcart: function(){
    let that=this;
    let list=[];
    util.request(api.Cartdeleteall, { stroeid: that.data.id}, 'POST', 'application/json').then(function (res) {
      if(res.flag==1){
        that.setData({
          cartlist:list
        });
      }
    });
  },
  /**展示购物车 */
  showcart:function(){
    let that=this;
    that.setData({
      showcart:true,
      top:'-20rpx',
      istrue:true
    });
  },
  /**关闭购物车 */
  clonecart:function(){ 
    let that = this;
    that.setData({
      showcart: false,
      top: '-50rpx', 
      istrue: false
    });
  },
  /**结算 */
  acdount:function(){
    let that=this;
    let id=that.data.id;
    wx.navigateTo({
      url: '/pages/takeoutsubmit/takeoutsubmit?id='+id
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let that=this;
    var query = wx.createSelectorQuery()
    query.select('.takeout_header').boundingClientRect();
    query.exec(function (res) {
      that.setData({
        menubartop: res[0].bottom
      });
    });
    that.getMealCart();
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
  onPageScroll:function(e){
    if (e.scrollTop > this.data.menubartop){
        this.setData({
          is_float_menubar: false
        });
    } else{
        this.setData({
          is_float_menubar: true
        });
    }
  }
})