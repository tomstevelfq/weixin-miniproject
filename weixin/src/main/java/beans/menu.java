package beans;

public class menu {
    private String content;
    private String title;
    private String tags;
    private int id;
    private String text;

    public menu(String a,String b,String c,String text){
        this.content=a;
        this.title=b;
        this.tags=c;
        this.text=text;
    }

    public menu(String a,String b,String c,int id,String text){
        this.content=a;
        this.title=b;
        this.tags=c;
        this.id=id;
        this.text=text;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
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

    public void setTags(String tags) {
        this.tags = tags;
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
        return "menu [title=" + title + ", text=" + text +  ", content=" + content + ", tags=" + tags + ", id=" + id + "]";
    }
}
