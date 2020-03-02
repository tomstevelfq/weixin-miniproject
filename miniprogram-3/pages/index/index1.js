// pages/index/index1.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    indexViews:null,
    list:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("sessionId",wx.getStorageSync("sessionId"))

    if(app.globalData.indexViews!=null){
      this.setData({
        indexViews: app.globalData.indexViews
      })
    }else{
      app.getInfoCallBack = res=>{
        this.setData({
          indexViews:res
        })
        console.log("indexViews",this.data.indexViews)
        var views=res.list
        var date = new Date()
        console.log("test", date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate())
        for(let item of views){
          var date = new Date()
          if(item.date==date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate()){
            item.today="今天"
          }else{
            console.log(item.date.split(".")[0], item.date.split(".")[1] - 1, item.date.split(".")[2])
            date.setFullYear(item.date.split(".")[0], item.date.split(".")[1] - 1, item.date.split(".")[2])
            console.log("date",date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate())
            if(date.getDay()==0){
              item.today="星期日"
            }
            if (date.getDay() == 1) {
              item.today = "星期一"
            }
            if (date.getDay() == 2) {
              item.today = "星期二"
            }
            if (date.getDay() == 3) {
              item.today = "星期三"
            }
            if (date.getDay() == 4) {
              item.today = "星期四"
            }
            if (date.getDay() == 5) {
              item.today = "星期五"
            }
            if (date.getDay() == 6) {
              item.today = "星期六"
            }

          }
          if(item.exampleContent!=null&&item.exampleContent!=""){
            console.log("item")
            var str=item.exampleContent
            if(str.indexOf("src=")!=-1){
              var x = str.indexOf("src=") + 5
              console.log(x)
              if(str.indexOf(".jpg")!=-1){
                var y = str.indexOf(".jpg") + 4
              }
              else if(str.indexOf(".png")!=-1){
                var y=str.indexOf(".png")+4
              }
              else if (str.indexOf(".webp") != -1) {
                var y = str.indexOf(".webp") + 5
              }
              item.exampleImage=str.substr(x,y-x)
              console.log("item",item.exampleImage)
            }
            
          
          }
          if(item.journalText==null||item.journalText==""){
            item.journalText="你今天还没有日志"
          }
          if (item.journalTitle == null || item.journalTitle == "") {
            item.journalTitle = "写日志"
            item.journalImage="/pages/images/empty.png"
          }else{
            item.journalImage = JSON.parse(item.journalImage)[0]
          }
          if (item.videoTitle == null || item.videoTitle == "") {
            item.videoTitle = "视频"
            item.videoImage = "/pages/images/empty.png"
          }
          if (item.videoContent == null || item.videoContent == "") {
            item.videoContent = "视频空空如也"
          }
          if (item.exampleTitle == null || item.exampleTitle == "") {
            item.exampleTitle = "案例"
            item.exampleImage = "/pages/images/empty.png"
          }
          if (item.exampleText == null || item.exampleText == "") {
            item.exampleText = "案例空空如也"
          }
          if(item.exampleText!=null&&item.exampleText!=""){
            if (item.exampleText.length > 20){
              item.exampleText = item.exampleText.substr(0, 12)+"..."
            }
          }
          if (item.journalText != null && item.journalText != "") {
            if (item.journalText.length > 20) {
              item.journalText = item.journalText.substr(0, 12) + "..."
            }
          }
          if (item.videoContent != null && item.videoContent != "") {
            if (item.videoContent.length > 20) {
              item.videoContent = item.videoContent.substr(0, 12) + "..."
            }
          }
             //console.log("hahhahaha",JSON.parse(item.journalImage))
          
        }
        this.setData({
          list:views
        })
        console.log("list",this.data.list)
      }
    }
    

    console.log("index",this.data.indexViews)
    
    // wx.request({
    //   url: "http://127.0.0.1:8080/getIndexViews",
    //   method: "POST",
    //   header: {
    //     "Content-Type": "application/x-www-form-urlencoded",
    //     "Cookie": wx.getStorageSync("sessionId")
    //   },
    //   success: function (res) {
    //     console.log(res.data);
    //   },
    // })
  },

  sign:function(e){
    var that = this
    if(this.data.list[e.currentTarget.id].today=="今天"){
      wx.request({
        url: "http://127.0.0.1:8080/isSigned",
        method: "POST",

        header: {
          "Content-Type": "application/x-www-form-urlencoded",
          "Cookie": wx.getStorageSync("sessionId")
        },
        success: function (res) {
          if (res.data == "true") {
            wx.showToast({
              title: '签到成功',
              icon: 'success',
              duration: 2000
            })
          } else {
            wx.showToast({
              title: '已签到',
              icon: 'success',
              duration: 2000
            })
          }
        },
      })

    }
  },

  setIndexViews:function(data){
    this.setData({
      indexViews:data
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
    if(app.globalData.flag!=0){
      var that = this
      wx.request({
        url: "http://127.0.0.1:8080/getIndexViews",
        method: "POST",
        header: {
          "Content-Type": "application/x-www-form-urlencoded",
          "Cookie": wx.getStorageSync("sessionId")
        },
        success: function (res) {

          var views = res.data.list
          var date = new Date()
          console.log("test", date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate())
          for (let item of views) {
            var date = new Date()
            if (item.date == date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate()) {
              item.today = "今天"
            } else {
              console.log(item.date.split(".")[0], item.date.split(".")[1] - 1, item.date.split(".")[2])
              date.setFullYear(item.date.split(".")[0], item.date.split(".")[1] - 1, item.date.split(".")[2])
              console.log("date", date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate())
              if (date.getDay() == 0) {
                item.today = "星期日"
              }
              if (date.getDay() == 1) {
                item.today = "星期一"
              }
              if (date.getDay() == 2) {
                item.today = "星期二"
              }
              if (date.getDay() == 3) {
                item.today = "星期三"
              }
              if (date.getDay() == 4) {
                item.today = "星期四"
              }
              if (date.getDay() == 5) {
                item.today = "星期五"
              }
              if (date.getDay() == 6) {
                item.today = "星期六"
              }

            }
            if (item.exampleContent != null && item.exampleContent != "") {
              console.log("item")
              var str = item.exampleContent
              if (str.indexOf("src=") != -1) {
                console.log("stritemmm")
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
                item.exampleImage = str.substr(x, y - x)
                console.log("item", item.exampleImage)
              }


            }
            if (item.journalText == null || item.journalText == "") {
              item.journalText = "你今天还没有日志"
            }
            if (item.journalTitle == null || item.journalTitle == "") {
              item.journalTitle = "写日志"
              item.journalImage = "/pages/images/empty.png"
            } else {
              item.journalImage = JSON.parse(item.journalImage)[0]
            }
            if (item.videoTitle == null || item.videoTitle == "") {
              item.videoTitle = "视频"
              item.videoImage = "/pages/images/empty.png"
            }
            if (item.videoContent == null || item.videoContent == "") {
              item.videoContent = "视频空空如也"
            }
            if (item.exampleTitle == null || item.exampleTitle == "") {
              item.exampleTitle = "案例"
              item.exampleImage = "/pages/images/empty.png"
            }
            if (item.exampleText == null || item.exampleText == "") {
              item.exampleText = "案例空空如也"
            }
            if (item.exampleText != null && item.exampleText != "") {
              if (item.exampleText.length > 20) {
                item.exampleText = item.exampleText.substr(0, 12) + "..."
              }
            }
            if (item.journalText != null && item.journalText != "") {
              if (item.journalText.length > 20) {
                item.journalText = item.journalText.substr(0, 12) + "..."
              }
            }
            if (item.videoContent != null && item.videoContent != "") {
              if (item.videoContent.length > 20) {
                item.videoContent = item.videoContent.substr(0, 12) + "..."
              }
            }
            //console.log("hahhahaha",JSON.parse(item.journalImage))

          }
          that.setData({
            list: views
          })
          console.log("list", that.data.list)
        }
      })
    }
    app.globalData.flag=1
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

  click:function(e){
    console.log("hahahah",e)
    wx.navigateTo({
      url: '/pages/healthJournal/healthJournal?date='+e.currentTarget.id,
    });
  },

  clickVideo:function(e){
    console.log(e.currentTarget.id)
    wx.navigateTo({
      url: '/pages/healthVideo/healthVideo?date=' + e.currentTarget.id,
    });
  },

  clickExample:function(e){

    wx.navigateTo({
      url: '/pages/healthExample/healthExample?date='+e.currentTarget.id,
    });
  }

})