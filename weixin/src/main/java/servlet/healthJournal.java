package servlet;

import beans.journal;
import beans.user;
import dao.dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/healthJournal")
public class healthJournal extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("接收到请求");
        System.out.println(req.getParameter("content"));
        String title=req.getParameter("title");
        String date=req.getParameter("date");
        String content=req.getParameter("content");
        String imgUrls=req.getParameter("imgUrls");
        String text=req.getParameter("text");
        text=text.replace("\n","  ");
        user u=(user)req.getSession().getAttribute("weixinUser");
        String userId=u.getUserId();
        System.out.println(text);
        try {
            dao.saveJournal(new journal(title,date,content,imgUrls,text,userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
