let app=getApp()
Page({
  data: {
    formats: {},
    readOnly: false,
    placeholder: '开始输入...',
    editorHeight: 300,
    keyboardHeight: 0,
    isIOS: false,
    html:"",
    url:"",
    title:"",
    imgUrls:[],
    id:null,
    isUpdate:false
  },
  readOnlyChange() {
    this.setData({
      readOnly: !this.data.readOnly
    })
  },
  onLoad(option) {
    console.log("hahaha",option)
    const that=this
    if(option.id==null){
      app.globalData.html = null
      this.setData({
        title: null,
        id: null,
        imgUrls: [],
        isUpdate: false
      })
    }
    if(option.id!=null&&option.title!=null)
    {
      console.log("!=null")
      this.setData({
        title: option.title,
        id: option.id,
        imgUrls: app.globalData.imgUrls,
        isUpdate:true
      })
    }

    console.log("test",this.data.title,this.data.id,this.data.imgUrls)
    const platform = wx.getSystemInfoSync().platform
    const isIOS = platform === 'ios'
    this.setData({ isIOS})
    this.updatePosition(0)
    let keyboardHeight = 0
    wx.onKeyboardHeightChange(res => {
      if (res.height === keyboardHeight) return
      const duration = res.height > 0 ? res.duration * 1000 : 0
      keyboardHeight = res.height
      setTimeout(() => {
        wx.pageScrollTo({
          scrollTop: 0,
          success() {
            that.updatePosition(keyboardHeight)
            that.editorCtx.scrollIntoView()
          }
        })
      }, duration)

    })
  },
  updatePosition(keyboardHeight) {
    const toolbarHeight = 50
    const { windowHeight, platform } = wx.getSystemInfoSync()
    let editorHeight = keyboardHeight > 0 ? (windowHeight - keyboardHeight - toolbarHeight) : windowHeight
    this.setData({ editorHeight, keyboardHeight })
  },
  calNavigationBarAndStatusBar() {
    const systemInfo = wx.getSystemInfoSync()
    const { statusBarHeight, platform } = systemInfo
    const isIOS = platform === 'ios'
    const navigationBarHeight = isIOS ? 44 : 48
    return statusBarHeight + navigationBarHeight
  },
  onEditorReady() {
    const that = this
    wx.createSelectorQuery().select('#editor').context(function (res) {
      that.editorCtx = res.context
    }).exec()

    setTimeout(()=>{
      console.log(app.globalData.html)
      that.editorCtx.setContents({
        html:app.globalData.html,
      })
    },1000)
  },
  blur() {
    this.editorCtx.blur()
  },
  format(e) {
    let { name, value } = e.target.dataset
    if (!name) return
    // console.log('format', name, value)
    this.editorCtx.format(name, value)

  },
  onStatusChange(e) {
    const formats = e.detail
    this.setData({ formats })
  },
  insertDivider() {
    this.editorCtx.insertDivider({
      success: function () {
        console.log('insert divider success')
      }
    })
  },
  clear() {
    this.editorCtx.clear({
      success: function (res) {
        console.log("clear success")
      }
    })
  },
  removeFormat() {
    this.editorCtx.removeFormat()
  },
  insertDate() {
    const date = new Date()
    const formatDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
    this.editorCtx.insertText({
      text: formatDate
    })
  },
  insertImage() {
    const that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        wx.uploadFile({
          url: 'http://127.0.0.1:8080/recPicture',
          header: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Cookie": wx.getStorageSync("sessionId")
          },
          filePath: res.tempFilePaths[0],
          name: '321',
          success(res){
            console.log("成功",res.data)
            that.setData({
              url:res.data
            })
            let urls=that.data.imgUrls
            urls.push(res.data)
            that.setData({
              imgUrls:urls
            })
            that.editorCtx.insertImage({
              src: that.data.url,
              data: {
                id: 'abcd',
                role: 'god'
              },
              width: '100%',
              success: function () {
                console.log('insert image success')
                console.log(that.editorCtx.context)
              }
            })
          }
        })
      }
    })
  },

  getNowFormatDate: function () {
    var date = new Date();
    var seperator1 = ".";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();

    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
  },

  save(){
    const that=this
    this.editorCtx.getContents({
      success: (res) => {
        console.log("text",res.text)
        var timestamp = Date.parse(new Date());
        var date = new Date(timestamp);

        that.setData({
          html:res.html,
        })

        if(that.data.isUpdate==false)
        {
          wx.request({
            url: "http://127.0.0.1:8080/healthJournal",
            method: "POST",
            data: {
              content: that.data.html,
              title: that.data.title,
              date: this.getNowFormatDate(),
              imgUrls: JSON.stringify(that.data.imgUrls),
              text: res.text
            },
            header: {
              "Content-Type": "application/x-www-form-urlencoded",
              "Cookie": wx.getStorageSync("sessionId")
            },
            success: function (res) {
              console.log(res.data);
              wx.showToast({
                title: '保存成功',
                icon: 'success',
                duration: 2000
              })
            },
          })
        }

        else{
          let urls=[]
          console.log("钱",this.data.imgUrls)
          console.log(this.data.html)
          for(let item of this.data.imgUrls){
            if(this.data.html.indexOf(item)!=-1){
              urls.push(item)
            }
          }
          console.log("后", this.data.imgUrls)


          this.setData({
            imgUrls:urls
          })
          
          wx.request({
            url: "http://127.0.0.1:8080/updateJournal",
            method: "POST",
            data: {
              content: that.data.html,
              title: that.data.title,
              date: this.getNowFormatDate(),
              imgUrls: JSON.stringify(that.data.imgUrls),
              text: res.text,
              id:that.data.id
            },
            header: {
              "Content-Type": "application/x-www-form-urlencoded",
              "Cookie": wx.getStorageSync("sessionId")
            },
            success: function (res) {
              console.log(res.data);
         
                wx.showToast({
                  title: '保存成功',
                  icon: 'success',
                  duration: 2000
                })
           
            },
          })
        }
    
      },
    })

    console.log("this is"+that.data.html)
  },

  title(e){
    this.setData({
      title:e.detail.value
    })
  }
})
