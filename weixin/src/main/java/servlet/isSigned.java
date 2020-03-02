package servlet;

import beans.user;
import dao.dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/isSigned")
public class isSigned extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        if(req.getSession().getAttribute("isSigned")==null){
            out.write("true");
            req.getSession().setAttribute("isSigned", Boolean.TRUE);
            String id = req.getSession().getId();//该session对象的编号id
            //手动创建一个存储JSESSIONID的Cookie 为该cookie设置持久化时间
            Cookie cookie = new Cookie("JSESSIONID",id);
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);
            out.close();
        }else{
            out.write("false");
            out.close();
        }
    }
}
