 var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../services/user.js');
var app = getApp();
 
Page({
    data: {
        userInfo: {},
        hasMobile: '', 
        cweight:'--',
        mweight:'--', 
        height:'--'  
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数     
    },
    getIndexData: function(){ 
      let that=this;
      util.request(api.Userbodyinfo, {}, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
            that.setData({
              cweight: res.data.bodyinfolist.weight,
              mweight: res.data.bodyinfolist.goalweight,
              height: res.data.bodyinfolist.userheight
            });
        }
      }); 
    },
    onReady: function () {

    }, 
    onShow: function () {
        let userInfo = wx.getStorageSync('userInfo');
        let token = wx.getStorageSync('token'); 
        let mobile = wx.getStorageSync('mobile');
        // 页面显示
        if (token) { 
          app.globalData.userInfo = userInfo.userInfo;
          app.globalData.token = token;
          this.getIndexData();
        }else{
          wx.removeStorageSync('userInfo');
        }
        let tel = mobile;
        let tel1;
        if(tel){
          tel = "" + tel;
          tel1 = tel.replace(tel.substring(3, 7), "****");
        }else{
          tel1='';
        }
        this.setData({
            userInfo: app.globalData.userInfo,
            hasMobile: tel1
        });

    }, 
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭
    },
    bindGetUserInfo(e) { 
      let token = wx.getStorageSync('token');
      if (token) {
        return false;
      }
    
        if (e.detail.userInfo){
            //用户按了允许授权按钮
            wx.showLoading({
                title: '加载中...',
            });
            user.loginByWeixin(e.detail).then(res => {
                let userInfo = wx.getStorageSync('userInfo');
              let mobile = wx.getStorageSync('mobile');
              let tel = mobile;
              let tel1;
              if (tel) {
                tel = "" + tel;
                tel1 = tel.replace(tel.substring(3, 7), "****");
              } else {
                tel1 ='';
              }
                this.setData({
                  userInfo: userInfo.userInfo,
                  hasMobile: tel1
                });
                app.globalData.userInfo = userInfo.userInfo;
                app.globalData.token = res.data.openid;
                this.getIndexData();
            }).catch((err) => {
            }); 
        } else {
            //用户按了拒绝按钮
            wx.showModal({
                title: '警告通知',
                content: '您点击了拒绝授权,将无法正常显示个人信息,点击确定重新获取授权。',
                success: function (res) {
                    if (res.confirm) {
                        wx.openSetting({
                            success: (res) => {
                                if (res.authSetting["scope.userInfo"]) {////如果用户重新同意了授权登录
                                    user.loginByWeixin(e.detail).then(res => {
                                      let userInfo = wx.getStorageSync('userInfo');
                                      this.setData({
                                        userInfo: userInfo.userInfo
                                      });
                                      app.globalData.userInfo = userInfo.userInfo;
                                      app.globalData.token = res.data.openid;
                                    }).catch((err) => {
                                    });
                                }
                            }
                        })
                    }
                }
            });
        }
    },
    exitLogin: function () {
        wx.showModal({
            title: '',
            confirmColor: '#b4282d',
            content: '退出登录？',
            success: function (res) {
                if (res.confirm) {
                    wx.removeStorageSync('token');
                    wx.removeStorageSync('userInfo');
                    wx.switchTab({
                        url: '/pages/index/index'
                    });
                }
            }
        })
    },
    /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
    onPullDownRefresh: function () {
      var self = this;
      let that = this;
      util.request(api.Userbodyinfo, {}, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          wx.stopPullDownRefresh();
          that.setData({
            cweight: res.data.bodyinfolist.weight,
            mweight: res.data.bodyinfolist.goalweight,
            height: res.data.bodyinfolist.userheight
          });
        }
      }); 
    },
    bodyupdata: function(event){
      if (event.currentTarget.dataset.weight=='--'){
        wx.navigateTo({
          url: '/pages/ucenter/bodyadd/bodyadd?name=0'
        })
      }else{
        wx.navigateTo({
          url: '/pages/ucenter/bodyupdate/bodyupdate'
        })
      }
  },
  Tophone:function(){
    wx.navigateTo({
      url: '/pages/auth/mobile/mobile'
    })
  }
})