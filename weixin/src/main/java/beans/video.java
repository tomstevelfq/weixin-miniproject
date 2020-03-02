package beans;

public class video {
    private String title;
    private String content;
    private String url;
    private int id;
    private String date;
    private String img;

    public video(){}

    public video(String title,String content,String url,String date,String img){
        this.title=title;
        this.content=content;
        this.url=url;
        this.date=date;
        this.img=img;
    }

    public video(String title,String content,String url,String date,String img,int id){
        this.title=title;
        this.content=content;
        this.url=url;
        this.date=date;
        this.id=id;
        this.img=img;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "video [title=" +title + ", img=" + img + ", content=" + content + ", date" +date + ", id=" + id +", url=" + url + "]";
    }
}
