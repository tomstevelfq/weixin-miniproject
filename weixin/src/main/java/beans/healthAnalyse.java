package beans;

public class healthAnalyse {
    private String contents;
    private String tags;
    private String text;
    private int id;

    public healthAnalyse(String a,String b,String text){
        this.contents=a;
        this.tags=b;
        this.text=text;
    }

    public healthAnalyse(String a,String b,int c,String text){
        this.contents=a;
        this.tags=b;
        this.id=c;
        this.text=text;
    }

    public String getContents() {
        return contents;
    }

    public String getTags() {
        return tags;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "healthAnalyse [contents=" + contents + ", text=" + text +", tags=" + tags + ", id=" + id +"]";
    }
}
