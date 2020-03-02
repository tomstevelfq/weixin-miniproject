package servlet;

import beans.menuGroup;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/getMenus")
public class getMenus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value=req.getParameter("value");
        List<String> l= Arrays.asList(value.split(" "));
        menuGroup g=new menuGroup();
        try {
            g= dao.getMenus(l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str= JSON.toJSONString(g);
        PrintWriter out=resp.getWriter();
        out.write(str);
        out.close();
    }
}
