package servlet;

import beans.videoGroup;
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

@WebServlet("/getVideos")
public class getVideos extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date=req.getParameter("date");
        videoGroup g=new videoGroup();
        try {
           g= dao.getVideos(date);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String str= JSON.toJSONString(g);
        PrintWriter out=resp.getWriter();
        out.write(str);
        out.close();
    }
}
