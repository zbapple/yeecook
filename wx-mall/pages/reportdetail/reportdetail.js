// pages/reportdetail/reportdetail.js 
const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');
Page({
  /**
   * 页面的初始数据 
   */ 
  data: {
    id:'',
    datemin:'',
    avg:'',
    max:'',
    min:'',
    updateTime:'',
    bmi:0,
    weight:0, 
    basicMetabolism:0,
    bodyFatRade:0,
    boneMass:0,
    protein:0,
    subFatPercentage:0,
    visFatGrade:0,
    show:'0',
    bodytype:'',
    weighttype:'',
    bodyFatRadetype:'',
    subFatPercentagetype:'',
    basicMetabolismtype:'',
    visFatGradetype:'',
    bodyWaterRatetype:'',
    proteintype:'',
    boneMasstype:'',
    skeletalMuscle:'',
    skeletalMuscletype:''
  },

  /**
   * 生命周期函数--监听页面加载
   */ 
  onLoad: function (options) { 
    let obj=JSON.parse(options.obj);
    this.setData({
      id:obj.id,
      datemin: obj.datemin
    });
    this.getIndexData();
  }, 
  getIndexData: function () {
    var that = this;
    util.request(api.Userhealthreportdetail, {id:that.data.id}, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          updateTime: that.formatDate(res.data.updateTime),
          bmi:res.data.bmi,
          weight: res.data.weight,
          basicMetabolism: res.data.basicMetabolism,
          bodyFatRade: res.data.bodyFatRade,
          bodyWaterRate: res.data.bodyWaterRate,
          boneMass: res.data.boneMass,
          protein: res.data.protein,
          subFatPercentage: res.data.subFatPercentage,
          visFatGrade: res.data.visFatGrade,
          skeletalMuscle: res.data.skeletalMuscle
        });
        if(res.data.bmi<18.5){
          that.setData({
            bodytype:'轻体重',
            weighttype:0
          });
        }else if(res.data.bmi<24.0){
          that.setData({
            bodytype:'健康体重',
            weighttype: 1
          });
        }else if(res.data.bmi<28.0){
          that.setData({
            bodytype:'超重',
            weighttype: 2
          });
        }else{
          that.setData({
            bodytype:'肥胖',
            weighttype: 2
          });
        }
        if (res.data.bodyFatRade<20){
          that.setData({
            bodyFatRadetype:0
          })
        } else if (res.data.bodyFatRade >= 20 && res.data.bodyFatRade<=30){
          that.setData({
            bodyFatRadetype:1
          });
        }else {
          that.setData({
            bodyFatRadetype: 2
          });
        }
        if (res.data.subFatPercentage <=20) {
          that.setData({
            subFatPercentagetype: 0
          })
        } else if (res.data.subFatPercentage >20 && res.data.subFatPercentage <= 34) {
          that.setData({
            subFatPercentagetype: 1
          });
        } else {
          that.setData({
            subFatPercentagetype: 2
          });
        }
        if (res.data.basicMetabolism <= 1200) {
          that.setData({
            basicMetabolismtype: 0
          })
        } else if (res.data.basicMetabolism > 1200 && res.data.basicMetabolism <= 1400) {
          that.setData({
            basicMetabolismtype: 1
          });
        } else {
          that.setData({
            basicMetabolismtype: 2
          });
        }
        if (res.data.visFatGrade<1){
          that.setData({
            visFatGradetype:0
          })
        } else if (res.data.visFatGrade >= 1 && res.data.visFatGrade<=9){
          that.setData({
            visFatGradetype: 1
          })
        }else{
          that.setData({
            visFatGradetype: 2
          })
        }
        if (res.data.bodyWaterRate < 40) {
          that.setData({
            bodyWaterRatetype: 0
          })
        } else if (res.data.bodyWaterRate >= 40 && res.data.bodyWaterRate<= 60) {
          that.setData({
            bodyWaterRatetype: 1
          })
        } else {
          that.setData({
            bodyWaterRatetype: 2
          })
        }
        if (res.data.protein < 16) {
          that.setData({
            proteintype: 0
          })
        } else if (res.data.protein >= 16 && res.data.protein <= 20) {
          that.setData({
            proteintype: 1
          })
        } else {
          that.setData({
            proteintype: 2
          })
        }
        if (res.data.boneMass < 1.8) {
          that.setData({
            boneMasstype: 0
          })
        } else if (res.data.boneMass >= 1.8 && res.data.boneMass <= 2.5) {
          that.setData({
            boneMasstype: 1
          })
        } else {
          that.setData({
            boneMasstype: 2
          })
        }
        if (res.data.skeletalMuscletype < 25) {
          that.setData({
            skeletalMuscletype: 0
          })
        } else if (res.data.skeletalMuscletype >= 25 && res.data.skeletalMuscletype <= 27) {
          that.setData({
            skeletalMuscletype: 1
          })
        } else {
          that.setData({
            skeletalMuscletype: 2
          })
        }
      }
    });
    util.request(api.Uuserhealthreportmaxuser, { datemin: that.data.datemin }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          avg: res.data.reslut.avg,
          max: res.data.reslut.max,
          min: res.data.reslut.min
        });
      }
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
    var that = this;
    util.request(api.Userhealthreportdetail, { id: that.data.id }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          updateTime: that.formatDate(res.data.updateTime),
          bmi: res.data.bmi,
          weight: res.data.weight,
          basicMetabolism: res.data.basicMetabolism,
          bodyFatRade: res.data.bodyFatRade,
          bodyWaterRate: res.data.bodyWaterRate,
          boneMass: res.data.boneMass,
          protein: res.data.protein,
          subFatPercentage: res.data.subFatPercentage,
          visFatGrade: res.data.visFatGrade,
          skeletalMuscle: res.data.skeletalMuscle
        });
        if (res.data.bmi < 18.5) {
          that.setData({
            bodytype: '轻体重',
            weighttype: 0
          });
        } else if (res.data.bmi < 24.0) {
          that.setData({
            bodytype: '健康体重',
            weighttype: 1
          });
        } else if (res.data.bmi < 28.0) {
          that.setData({
            bodytype: '超重',
            weighttype: 2
          });
        } else {
          that.setData({
            bodytype: '肥胖',
            weighttype: 2
          });
        }
        if (res.data.bodyFatRade < 20) {
          that.setData({
            bodyFatRadetype: 0
          })
        } else if (res.data.bodyFatRade >= 20 && res.data.bodyFatRade <= 30) {
          that.setData({
            bodyFatRadetype: 1
          });
        } else {
          that.setData({
            bodyFatRadetype: 2
          });
        }
        if (res.data.subFatPercentage <= 20) {
          that.setData({
            subFatPercentagetype: 0
          })
        } else if (res.data.subFatPercentage > 20 && res.data.subFatPercentage <= 34) {
          that.setData({
            subFatPercentagetype: 1
          });
        } else {
          that.setData({
            subFatPercentagetype: 2
          });
        }
        if (res.data.basicMetabolism <= 1200) {
          that.setData({
            basicMetabolismtype: 0
          })
        } else if (res.data.basicMetabolism > 1200 && res.data.basicMetabolism <= 1400) {
          that.setData({
            basicMetabolismtype: 1
          });
        } else {
          that.setData({
            basicMetabolismtype: 2
          });
        }
        if (res.data.visFatGrade < 1) {
          that.setData({
            visFatGradetype: 0
          })
        } else if (res.data.visFatGrade >= 1 && res.data.visFatGrade <= 9) {
          that.setData({
            visFatGradetype: 1
          })
        } else {
          that.setData({
            visFatGradetype: 2
          })
        }
        if (res.data.bodyWaterRate < 40) {
          that.setData({
            bodyWaterRatetype: 0
          })
        } else if (res.data.bodyWaterRate >= 40 && res.data.bodyWaterRate <= 60) {
          that.setData({
            bodyWaterRatetype: 1
          })
        } else {
          that.setData({
            bodyWaterRatetype: 2
          })
        }
        if (res.data.protein < 16) {
          that.setData({
            proteintype: 0
          })
        } else if (res.data.protein >= 16 && res.data.protein <= 20) {
          that.setData({
            proteintype: 1
          })
        } else {
          that.setData({
            proteintype: 2
          })
        }
        if (res.data.boneMass < 1.8) {
          that.setData({
            boneMasstype: 0
          })
        } else if (res.data.boneMass >= 1.8 && res.data.boneMass <= 2.5) {
          that.setData({
            boneMasstype: 1
          })
        } else {
          that.setData({
            boneMasstype: 2
          })
        }
        if (res.data.skeletalMuscletype < 25) {
          that.setData({
            skeletalMuscletype: 0
          })
        } else if (res.data.skeletalMuscletype >= 25 && res.data.skeletalMuscletype <= 27) {
          that.setData({
            skeletalMuscletype: 1
          })
        } else {
          that.setData({
            skeletalMuscletype: 2
          })
        }
      }
      wx.stopPullDownRefresh();
    });
    util.request(api.Uuserhealthreportmaxuser, { datemin: that.data.datemin }, 'POST', 'application/json').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          avg: res.data.reslut.avg,
          max: res.data.reslut.max,
          min: res.data.reslut.min
        });
      }
    });
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
  formatDate: function (value) {
    var date = new Date(value);
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return   m + '月' + d+'日';
  },
  todetail: function () {
    wx.navigateTo({
      url: '/pages/cookdetails/cookdetails',
    })
  }
}) 