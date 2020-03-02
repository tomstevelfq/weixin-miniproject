// pages/healthExample/healthExample.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    date:null,
    list:null,
    list1:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      date:options.date
    })
    var that = this
    wx.request({
      url: "http://127.0.0.1:8080/getExamples",
      method: "POST",
      data: {
        date: options.date
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": wx.getStorageSync("sessionId")
      },
      success: function (res) {
        that.setData({
          list: res.data.list
        })
        console.log(res.data.list)
        var views = res.data.list
        for (let item of views) {
          if (item.text != null && item.text != "") {
            if (item.text.length > 20) {
              item.text = item.text.substr(0, 20)+"..."
            }
          }
          
          var str = item.content
          if (str.indexOf("src=") != -1) {
            var x = str.indexOf("src=") + 5
            console.log(x)
            if (str.indexOf(".jpg") != -1) {
              var y = str.indexOf(".jpg") + 4
            }
            else if (str.indexOf(".png") != -1) {
              var y = str.indexOf(".png") + 4
            }
            else if (str.indexOf(".webp") != -1) {
              var y = str.indexOf(".webp") + 5
            }
          }
          item.exampleImage = str.substr(x, y - x)
        }
        that.setData({
          list1: views
        })
        console.log("list", that.data.list1)
      },
    })
  },

  click: function (e) {
    console.log(e.currentTarget.id, this.data.list)
    var id = e.currentTarget.id
    app.globalData.exampleTitle = this.data.list[id].title
    app.globalData.exampleContent = this.data.list[id].content
    wx.navigateTo({
      url: 'example/example',
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