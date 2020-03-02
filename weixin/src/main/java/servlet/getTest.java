package servlet;

import beans.healthTestGroup;

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

@WebServlet("/getTest")
public class getTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value=req.getParameter("value");
        healthTestGroup g=new healthTestGroup();
        try {
            g=dao.getTest(Double.parseDouble(value));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String str= JSON.toJSONString(g);
        PrintWriter out=resp.getWriter();
        out.write(str);
        out.close();
    }
}
