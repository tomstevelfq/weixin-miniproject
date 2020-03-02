package beans;

public class healthExample {
    private String content;
    private String title;
    private String date;
    private int id;
    private String text;


    public healthExample(){}

    public healthExample(String a,String b,String c,String text){
        this.content=a;
        this.title=b;
        this.date=c;
        this.text=text;
    }

    public healthExample(String a,String b,String c,int id){
        this.content=a;
        this.title=b;
        this.date=c;
        this.id=id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "healthExample [content=" + content + ", text=" + text +", title=" + title + ", date=" + date + ", id=" + id + "]";
    }
}
