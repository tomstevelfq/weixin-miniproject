// pages/healthMenu/healthMenu.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value: null,
    list: null,
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

  },


  input: function (e) {
    this.setData({
      value: e.detail.value
    })
  },

  click:function(e){
    var id=e.currentTarget.id
    app.globalData.menuContent=this.data.list[id].content
    app.globalData.menuTitle = this.data.list[id].title
    wx.navigateTo({
      url: 'menu/menu',
    })
  },

  search: function () {
    console.log(this.data.value)
    var that = this
    wx.request({
      url: "http://127.0.0.1:8080/getMenus",
      method: "POST",
      data: {
        value: that.data.value
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": wx.getStorageSync("sessionId")
      },
      success: function (res) {
        var views=res.data.list
        for(let item of views){
          if (item.text != null && item.text != "") {
            if (item.text.length > 20) {
              item.text = item.text.substr(0, 20) + "..."
            }

            if (item.content != null && item.content != "") {
              console.log("item")
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
                item.image = str.substr(x, y - x)
                console.log("item", item.image)
              }


            }
          }
        }
        that.setData({
          list: views
        })
        console.log(that.data.list)
      },
    })
  }

})