package servlet;

import beans.user;
import com.alibaba.fastjson.JSONObject;
import tools.HttpRequested;
import dao.dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/weixinLogin")
public class weixinLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code=req.getParameter("code");

        //小程序的appid
        String appId = "wx8403485c68c612fb";
        // 小程序的secret
        String appsecret = "c34fa46122e18ab389897c40ba6e94f1";

        //向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + appId + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=authorization_code";

        // 发送请求
        String sr = HttpRequested.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

        JSONObject jsonObject = JSONObject.parseObject(sr);

        //System.out.println(jsonObject.get("openid"));

        String id= (String) jsonObject.get("openid");

        try {
            if(dao.getUser(id)==false){
                dao.addUser(new user(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getSession().setAttribute("weixinUser",new user(id));
    }
}
