package servlet;

import beans.*;
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

@WebServlet("/getIndexViews")
public class getIndexViews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        indexViewsGroup g=new indexViewsGroup();
        user u= (user) req.getSession().getAttribute("weixinUser");
        String userId=u.getUserId();
        System.out.println(u);
        try {
            g= dao.getIndexViewsGroup();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            for(indexViews item:g.getList()){
                sign s=dao.getSign(item.getDate());
                video v=dao.getVideo(item.getDate());
                healthExample e=dao.getExample(item.getDate());
                journal j=dao.getJournal(item.getDate(),userId);
                item.setSignTitle(s.getTitle());
                item.setSignImage(s.getImage());
                item.setExampleContent(e.getContent());
                item.setExampleTitle(e.getTitle());
                item.setExampleText(e.getText());
                item.setVideoContent(v.getContent());
                item.setVideoImage(v.getImg());
                item.setVideoUrl(v.getUrl());
                item.setVideoTitle(v.getTitle());
                item.setJournalContent(j.getContent());
                item.setJournalTitle(j.getTitle());
                item.setJournalImage(j.getImgUrls());
                item.setJournalText(j.getText());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        resp.setContentType("application/json;charset=utf-8");
        String str= JSON.toJSONString(g);
        PrintWriter out=resp.getWriter();
        out.write(str);
        out.close();
    }
}
