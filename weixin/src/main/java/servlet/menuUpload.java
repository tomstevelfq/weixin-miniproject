package servlet;

import beans.menu;
import dao.dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/menuUpload")
public class menuUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String contents=req.getParameter("contents");
        String tags=req.getParameter("tags");
        String title=req.getParameter("title");
        String text=req.getParameter("text");
        menu m=new menu(contents,title,tags,text);

        try {
            dao.addMenu(m);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out=resp.getWriter();
        out.write("success");
        out.close();
    }
}
