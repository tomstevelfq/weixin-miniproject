// pages/healthAnalyse/analyse/analyse.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:null,
    content:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      title:app.globalData.analyseTitle,
      content:app.globalData.analyseContent
    })

    var index = 0
    var flag = 0
    var content1 = this.data.content
    var content2 = ""
    while (content1.indexOf("<img") != -1) {
      var index = content1.indexOf("<img") + 4
      content2 = content2 + content1.slice(0, index) + " style='width:100%' "
      content1 = content1.slice(index)
      console.log(index, content2)
    }
    content2 = content2 + content1
    console.log(content2)
    this.setData({
      content: content2
    })
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