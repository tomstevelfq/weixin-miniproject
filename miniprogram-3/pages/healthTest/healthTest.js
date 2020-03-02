// pages/healthTest/healthTest.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    height:null,
    weight:null,
    list:null
  },

  height:function(e){
    this.setData({
      height:e.detail.value
    })
  },

  weight: function (e) {
    this.setData({
      weight: e.detail.value
    })
  },

  test: function () {
    var that = this
    console.log(this.data.value, that.data.height, that.data.weight, that.data.height / that.data.weight)
    wx.request({
      url: "http://127.0.0.1:8080/getTest",
      method: "POST",
      data: {
        value: that.data.height/that.data.weight
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": wx.getStorageSync("sessionId")
      },
      success: function (res) {
        that.setData({
          list: res.data.list
        })
      },
    })
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

  }
})