package dao;


import beans.*;
import servlet.healthTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class dao {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/weixin?useSSL=false&serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "182838";
    static Statement stmt;
    static Connection conn;
    private static journal j;

    static{
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt=conn.createStatement();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public static void saveJournal(journal j) throws SQLException {
        dao.j = j;
        String sql="insert into journal(title,date,content,imgUrls,text,id,user) values('"+j.getTitle()+"','"+j.getDate()+"','"+j.getContent()+"','"+j.getImgUrls()+"','"+j.getText()+"','"+j.getId()+"','"+j.getUser()+"')";
        int rows=stmt.executeUpdate(sql);
        System.out.println(rows);
    }

    public static journalGroup getJournals(String date,String userId) throws SQLException {
        String sql = "select * from journal where date='"+date+"' and user='"+userId+"' order by id desc";
        ResultSet rs = stmt.executeQuery(sql);
        journalGroup group = new journalGroup();
        while (rs.next()) {
            group.getList().add(new journal(rs.getString("title"), rs.getString("date"), rs.getString("content"), rs.getString("imgUrls"), rs.getString("text"), rs.getInt("id"),rs.getString("user")));
        }

        return group;
    }

    public static journal getJournal(String date,String userId) throws SQLException {
        String sql = "select * from journal where date='"+date+"' and user='"+userId+"'";
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            return new journal(rs.getString("title"),rs.getString("date"),rs.getString("content"),rs.getString("imgUrls"),rs.getString("text"),rs.getString("user"));
        }
        return new journal();
    }

    public static void updateJournal(journal j) throws SQLException{
        String sql="update journal set title='"+j.getTitle()+"',date='"+j.getDate()+"',content='"+j.getContent()+"',imgUrls='"+j.getImgUrls()+"',text='"+j.getText()+"' where id='"+j.getId()+"'";
        int rows=stmt.executeUpdate(sql);
        System.out.println(rows);
    }

    public static void deleteJournal(int id) throws SQLException{
        String sql="delete from journal where id='"+id+"'";
        stmt.executeUpdate(sql);
    }

    public static void addHealthAnalyse(healthAnalyse health) throws SQLException {
        String sql="insert into analyse(content,tags,text) values('"+health.getContents()+"','"+health.getTags()+"','"+health.getText()+"')";
        stmt.executeUpdate(sql);
    }

    public static healthAnalyseGroup getAnalyses(List<String> l) throws SQLException {
        String sql="select * from analyse";
        ResultSet rs=stmt.executeQuery(sql);
        healthAnalyseGroup g=new healthAnalyseGroup();
        while(rs.next()){
            boolean flag=true;
            for(String item:l){
                if(!rs.getString("tags").contains(item)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                g.getList().add(new healthAnalyse(rs.getString("content"),rs.getString("tags"),rs.getString("text")));
            }
        }
        return g;
    }

    public static void addHealthExample(healthExample h) throws SQLException {
        String sql="insert into example(content,title,date,text) values('"+h.getContent()+"','"+h.getTitle()+"','"+h.getDate()+"','"+h.getText()+"')";
        stmt.executeUpdate(sql);
    }

    public static healthExample getExample(String date) throws SQLException {
        String sql="select * from example where date='"+date+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            return new healthExample(rs.getString("content"),rs.getString("title"),rs.getString("date"),rs.getString("text"));
        }
        return new healthExample();
    }

    public static healthExampleGroup getExamples(String date) throws SQLException {
        String sql="select * from example where date='"+date+"'";
        ResultSet rs=stmt.executeQuery(sql);
        healthExampleGroup g=new healthExampleGroup();
        while(rs.next()){
            g.getList().add(new healthExample(rs.getString("content"),rs.getString("title"),rs.getString("date"),rs.getString("text")));
        }
        return g;
    }

    public static void addHealthTest(healTest h) throws SQLException {
        String sql="insert into test(rate,words,image) values('"+h.getRate()+"','"+h.getWords()+"','"+h.getImage()+"')";
        stmt.executeUpdate(sql);
    }

    public static void addMenu(menu m) throws SQLException {
        String sql="insert into menu(content,tags,title,text) values('"+m.getContent()+"','"+m.getTags()+"','"+m.getTitle()+"','"+m.getText()+"')";
        stmt.executeUpdate(sql);
    }

    public static void addSign(sign s) throws SQLException {
        ResultSet rs=stmt.executeQuery("select * from sign where date='"+s.getDate()+"'");
        if(rs.next()){
            stmt.executeUpdate("delete from sign where date='"+s.getDate()+"'");
        }
        String sql="insert into sign(date,image,title) values('"+s.getDate()+"','"+s.getImage()+"','"+s.getTitle()+"')";
        stmt.executeUpdate(sql);
    }

    public static sign getSign(String date) throws SQLException {
        String sql="select * from sign where date='"+date+"'";
        ResultSet rs=stmt.executeQuery(sql);

        if(rs.next()){
            return new sign(rs.getString("date"),rs.getString("image"),rs.getString("title"));
        }
        return new sign();
    }

    public static void addVideo(video s) throws SQLException {
        String sql="insert into video(url,title,content,date,img) values('"+s.getUrl()+"','"+s.getTitle()+"','"+s.getContent()+"','"+s.getDate()+"','"+s.getImg()+"')";
        stmt.executeUpdate(sql);
    }

    public static video getVideo(String date) throws SQLException {
        String sql="select * from video where date='"+date+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            return new video(rs.getString("title"),rs.getString("content"),rs.getString("url"),rs.getString("date"),rs.getString("img"));
        }
        return new video();
    }

    public static videoGroup getVideos(String date) throws SQLException {
        String sql="select * from video where date='"+date+"'";
        ResultSet rs=stmt.executeQuery(sql);
        videoGroup g=new videoGroup();
        while(rs.next()){
            g.getList().add(new video(rs.getString("title"),rs.getString("content"),rs.getString("url"),rs.getString("date"),rs.getString("img")));
        }
        return g;
    }

    public static void addUser(user u) throws SQLException {
        String sql="insert into user(userId) values('"+u.getUserId()+"')";
        stmt.executeUpdate(sql);
    }

    public static boolean getUser(String id) throws SQLException {
        String sql = "select * from user where userId = '"+id+"'";
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isSigned(String userId) throws SQLException {
        String sql="select * from sign where userId='"+userId+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            if(Boolean.parseBoolean(rs.getString("isSigned"))){
                return true;
            }else{
                stmt.executeUpdate("update sign set isSigned='1' where userId=userId");
                return false;
            }
        }
        return true;
    }

    public static boolean getDate(String date) throws SQLException {
        String sql = "select * from date where date = '"+date+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }

    public static void addDate(String date) throws SQLException {
        String sql="insert into date(date) values('"+date+"')";
        stmt.executeUpdate(sql);
    }

    public static indexViewsGroup getIndexViewsGroup() throws SQLException {
        String sql="select * from date";
        ResultSet rs=stmt.executeQuery(sql);
        List<indexViews> list=new ArrayList<>();
        while(rs.next()){
            list.add(new indexViews(rs.getString("date")));
        }
        list.sort(Comparator.comparing(indexViews::getDate));
        List<indexViews> list1=new ArrayList<>();
        for(int i=list.size()-1;i>=0;i--){
            list1.add(list.get(i));
        }
        indexViewsGroup g=new indexViewsGroup();
        g.setList(list1);
        return g;
    }

    public static healthTestGroup getTest(double rate) throws SQLException {
        String sql="select * from test";
        ResultSet rs=stmt.executeQuery(sql);
        healthTestGroup g=new healthTestGroup();
        while(rs.next()){
            if(Double.parseDouble(rs.getString("rate"))>rate){
                g.getList().add(new healTest(Double.parseDouble(rs.getString("rate")),rs.getString("words"),rs.getString("image")));
                break;
            }
        }
        return g;
    }

    public static menuGroup getMenus(List<String> l) throws SQLException {
        String sql="select * from menu";
        ResultSet rs=stmt.executeQuery(sql);
        menuGroup g=new menuGroup();
        while(rs.next()){
            boolean flag=true;
            for(String item:l){
                if(!rs.getString("tags").contains(item)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                g.getList().add(new menu(rs.getString("content"),rs.getString("title"),rs.getString("tags"),rs.getString("text")));
            }
        }
        return g;
    }

}
