package servlet;

import dao.dao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/healthAnalyse")
public class healthAnalyse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String contents=req.getParameter("contents");
        String tags=req.getParameter("tags");
        String text=req.getParameter("text");
        try {
            dao.addHealthAnalyse(new beans.healthAnalyse(contents,tags,text));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out=resp.getWriter();
        out.write("success");
        out.close();
    }
}
