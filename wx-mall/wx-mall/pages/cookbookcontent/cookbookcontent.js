// pages/cookbookcontent/cookbookcontent.js
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    day:1,
    show:false,
    daylist:[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30],
    nlevel:'',
    deliveryWay:'',
    feedingWay:'',
    liliLength:'',
    list:[],
    listzc:[],//早餐
    zc_length:0,
    zcsum:0,
    listzd:[],//早点
    zd_length:0,
    zdsum:0,
    listwc:[],//午餐
    listwc:0,
    wcsum:0,
    listwd:[],//午点
    listwd:0,
    wdsum:0,
    listwanc:[],//晚餐
    listwanc:0,
    wancsum:0,
    listwand:[],//晚点
    listwand:0,
    wandsum:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getcookbookconentinfo(options.nlevel,this.data.day);
    this.setData({
      nlevel: options.nlevel,
      deliveryWay: options.deliveryWay,
      feedingWay: options.feedingWay,
      liliLength: options.liliLength
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  getcookbookconentinfo: function(mlevel,day){
    var that=this;
    var data = new Object();
    
    util.request(api.Cookbookcontent, { mlevel: mlevel, postpartumTime: day }).then(function (res) {
      if (res.errno === 0) {
        data.list=res.data;
        that.setData(data);
        that.deelData(that.data.list);
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
  minus: function(){
    if(this.data.day>1){
      this.setData({
        day: this.data.day - 1
      });
      this.getcookbookconentinfo(this.data.nlevel, this.data.day);
    }
  },
  add: function(){
    if (this.data.day<30){
      this.setData({
        day: this.data.day + 1
      });
      this.getcookbookconentinfo(this.data.nlevel, this.data.day);
    }
  },
  //处理数据
  
  deelData: function(event){
    var list_zc=new Array();
    var zc_sum=0;
    var list_zd=new Array();
    var zd_sum=0;
    var list_wc=new Array();
    var wc_sum=0;
    var list_wd=new Array();
    var wd_sum=0;
    var list_wanc=new Array();
    var wanc_sum=0;
    var list_wand=new Array();
    var wand_sum=0;
    if(event.length>0){
      var data = new Object();
      for(let i=0;i<event.length;i++){
        if (event[i].mtime=='早餐'){
          list_zc.push(event[i]);
          zc_sum = zc_sum+ event[i].calories;
        } else if (event[i].mtime=='早点'){
          list_zd.push(event[i]);
          zd_sum = zd_sum + event[i].calories;
        } else if (event[i].mtime=='午餐'){
          list_wc.push(event[i]);
          wc_sum = wc_sum + event[i].calories;
        } else if (event[i].mtime=='午点'){
          list_wd.push(event[i]);
          wd_sum = wd_sum + event[i].calories;
        } else if (event[i].mtime=='晚餐'){
          list_wanc.push(event[i]);
          wanc_sum = wanc_sum + event[i].calories;
        } else if (event[i].mtime=='晚点'){
          list_wand.push(event[i]);
          wand_sum = wand_sum + event[i].calories;
        }
      }
      //数字格式操作
      zc_sum = this.Floatnum(zc_sum);
      zd_sum = this.Floatnum(zd_sum);
      wc_sum = this.Floatnum(wc_sum);
      wd_sum = this.Floatnum(wd_sum);
      wanc_sum =this.Floatnum(wanc_sum);
      wand_sum =this.Floatnum(wand_sum);
      this.setData({
        listzc: list_zc,
        zc_length:list_zc.length,
        zcsum:zc_sum,
        listzd: list_zd,
        zd_length:list_zd.length,
        zdsum: zd_sum,
        listwc: list_wc,
        wc_length: list_wc.length,
        wcsum: wc_sum,
        listwd: list_wd,
        wd_length: list_wd.length,
        wdsum:wd_sum,
        listwanc: list_wanc,
        wanc_length: list_wanc.length,
        wancsum:wanc_sum,
        listwand: list_wand,
        wand_length: list_wand.length,
        wandsum:wand_sum
      });
    }
  },
  hidepop:function(){
    this.setData({
      show:false
    })
  },
  showpop: function(event){
    this.setData({
      show:true
    })
  },
  //选择天数
  Popselectday: function(event){
    if (event.currentTarget.dataset.day!=this.data.day){
      this.setData({
        day: event.currentTarget.dataset.day,
        show:false
      })
      this.getcookbookconentinfo(this.data.nlevel, this.data.day);
    }
  },
  //浮点型数字，相加溢出问题
  Floatnum:function(event){
    if (!isNaN(parseFloat(event))) {
      event = parseFloat(event);
      event = event.toFixed(2);
      return event;
    }
  }
})