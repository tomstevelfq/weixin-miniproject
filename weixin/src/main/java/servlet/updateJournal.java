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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/updateJournal")
public class updateJournal extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String content=req.getParameter("content");
        String title=req.getParameter("title");
        String date=req.getParameter("date");
        String imgUrls=req.getParameter("imgUrls");
        String text=req.getParameter("text");
        text=text.replace("\n","  ");
        user u= (user) req.getSession().getAttribute("weixinUser");
        String userId=u.getUserId();
        int id=Integer.parseInt(req.getParameter("id"));


        try {
            dao.updateJournal(new journal(title,date,content,imgUrls,text,id,userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out=resp.getWriter();
        out.write("success");
        out.close();
    }
}
