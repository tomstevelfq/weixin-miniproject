// pages/healthJournal/healthJournal.js
let app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:null,
    date:null,
    hidden:false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("onload hahahha")
    this.setData({
      date:options.date
    })
    var date=new Date()
    console.log("datee",this.data.date)
    if(this.data.date != date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate()){
      this.setData({
        hidden:true
      })
      console.log("!=")
    }

    if(options.id="editor"){
      const that = this
      wx.request({
        url: "http://127.0.0.1:8080/retJournals",
        method: "POST",
        data: {
          date: that.data.date
        },
        header: {
          "Content-Type": "application/x-www-form-urlencoded",
          "Cookie": wx.getStorageSync("sessionId")
        },
        success: function (res) {
          console.log('resdata', res.data.list);
          let li = res.data.list
          console.log("log", res.data.list)
          li.forEach((item, index) => {
            item.imgUrls = JSON.parse(item.imgUrls)
            if (item.text.length > 30) {
              item.text = item.text.slice(0, 30) + "..."
            }
          })
          that.setData({
            list: li
          })
          console.log(that.data.list)
        },
      })
    }
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
    console.log("onshow",this.data.date)
    const that=this
    wx.request({
      url: "http://127.0.0.1:8080/retJournals",
      method: "POST",
      data:{
        date:that.data.date
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": wx.getStorageSync("sessionId")
      },
      success: function (res) {
        console.log('resdata',res.data.list);
        let li=res.data.list
        console.log("log",res.data.list)
        li.forEach((item,index)=>{
          item.imgUrls=JSON.parse(item.imgUrls)
           if(item.text.length>30){
             item.text=item.text.slice(0,30)+"..."
           }
        })
        that.setData({
          list:li
        })
        console.log(that.data.list)
      },
    })
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

  save(){
    wx.navigateTo({
      url: "editor/editor",
    })
  },

  delete(e){
    let li=this.data.list
    let eid=li[e.currentTarget.id].id
    console.log(eid)
    li.splice(e.currentTarget.id,1)
    this.setData({
      list:li
    })
    const that=this
    wx.request({
      url: "http://127.0.0.1:8080/deleteJournal",
      method: "POST",
      data: {
        id:eid
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": wx.getStorageSync("sessionId")
      },
      success: function (res) {
        console.log(res.data);
      },
    })
  },

  edit:function(e){
    console.log("hahah")
    let id=e.currentTarget.id
    app.globalData.html = this.data.list[id].content
    app.globalData.imgUrls = this.data.list[id].imgUrls
    console.log(this.data.list[id].content)
    wx.navigateTo({
      url: "editor/editor?id=" + this.data.list[id].id + '&title=' + this.data.list[id].title,
    })
  }
})