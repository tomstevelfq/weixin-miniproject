package servlet;

import beans.journalGroup;
import beans.user;
import com.alibaba.fastjson.JSON;
import dao.dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/retJournals")
public class retJournals extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String date=req.getParameter("date");
        user u= (user) req.getSession().getAttribute("weixinUser");
        String userId=u.getUserId();
        journalGroup group=new journalGroup();
        try {
            group= dao.getJournals(date,userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String str=JSON.toJSONString(group);
        PrintWriter out=resp.getWriter();
        out.write(str);
        out.close();
    }
}
