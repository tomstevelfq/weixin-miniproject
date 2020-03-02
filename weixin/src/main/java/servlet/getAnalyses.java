package servlet;

import beans.healthAnalyseGroup;
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

@WebServlet("/getAnalyses")
public class getAnalyses extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value=req.getParameter("value");
        List<String> li= Arrays.asList(value.split(" "));
        healthAnalyseGroup g=new healthAnalyseGroup();
        try {
            g= dao.getAnalyses(li);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str= JSON.toJSONString(g);
        PrintWriter out=resp.getWriter();
        out.write(str);
        out.close();
    }
}
