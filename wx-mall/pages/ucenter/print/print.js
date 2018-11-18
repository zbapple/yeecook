var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');



var app = getApp();

Page({
  data: {
    printList: [],
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    var self = this;
    this.getprintList();
  },
  getprintList() {
    let that = this;
    var tmpPrint;
    util.request(api.PrintList).then(function (res) {
      if (res.errno === 0) {

        if (res.data.data != undefined) {
          tmpPrint = res.data.data;
        } else {
          tmpPrint = [];
        }

        that.setData({
          printList: tmpPrint
        });
      }
    });
  },
  printItem(event) {
    let that = this;
    let print = event.currentTarget.dataset.print;
    var touchTime = that.data.touch_end - that.data.touch_start;
    //如果按下时间大于350为长按
    if (touchTime > 350) {
      wx.showModal({
        title: '',
        content: '确定要打印？',
        success: function (res) {
          if (res.confirm) {
            util.request(api.Print, { batchName: print.batchName ,batchId: print.batchId }).then(function (res) {
              if (res.errno === 0) {
                wx.showToast({
                  title: res.errmsg,
                  icon: 'success',
                  duration: 2000,
                  complete: function () {
                    that.getprintList();
                  }
                });
              } else {
                util.showErrorToast(res.errmsg);
              }
            });
          }
        }
      });
    } else {
     
    }

  },
  onLoad: function (options) {
    this.getprintList();
  },
  onReady: function () {

  },
  onShow: function () {

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭
  },
  //按下事件开始  
  touchStart: function (e) {
    let that = this;
    that.setData({
      touch_start: e.timeStamp
    })
  },
  //按下事件结束  
  touchEnd: function (e) {
    let that = this;
    that.setData({
      touch_end: e.timeStamp
    })
  },
})