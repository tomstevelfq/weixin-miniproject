package beans;

import java.util.ArrayList;
import java.util.List;

public class journal {
    private String title;
    private String date;
    private String content;
    private String imgUrls;
    private String text;
    private String user;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public journal(String a, String b, String c, String d, String e, int id,String user){
        this.title=a;
        this.date=b;
        this.content=c;
        this.imgUrls=d;
        this.text=e;
        this.id=id;
        this.user=user;
    }

    public journal(String a, String b, String c, String d, String e,String user){
        this.title=a;
        this.date=b;
        this.content=c;
        this.imgUrls=d;
        this.text=e;
        this.user=user;
    }

    public journal(){}

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "journal [title=" + title +", user=" + user+ ", date=" + date + ", content=" + content + ", imgUrls=" + imgUrls + ", text=" + text + ", id=" + id + "]";
    }
}
