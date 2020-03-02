// pages/healthVideo/healthVideo.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    date:null,
    list:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.date)
    this.setData({
      date:options.date
    })
    var that=this
    wx.request({
      url: "http://127.0.0.1:8080/getVideos",
      method: "POST",
      data:{
        date:options.date
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": wx.getStorageSync("sessionId")
      },
      success: function (res) {
        that.setData({
          list:res.data.list
        })
        console.log(that.data.list)
      },
    })
  },

  click:function(e){
    console.log(e.currentTarget.id,this.data.list)
    var id=e.currentTarget.id
    app.globalData.videoTitle=this.data.list[id].title
    app.globalData.videoContent=this.data.list[id].content
    app.globalData.videoUrl=this.data.list[id].url
    console.log(app.globalData.videoUrl)
    wx.navigateTo({
      url: 'video/video',
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