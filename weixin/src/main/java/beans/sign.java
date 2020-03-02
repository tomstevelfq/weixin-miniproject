package beans;

public class sign {
    private String date;
    private String image;
    private String title;
    private boolean isSigned;
    private int id;

    public sign(){}

    public sign(String a,String b,String c){
        this.date=a;
        this.image=b;
        this.title=c;
    }

    public sign(String a,String b,String c,int id){
        this.date=a;
        this.image=b;
        this.title=c;
        this.id=id;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    @Override
    public String toString() {
        return "sign [date=" + date + ", image=" + image + ", title" + title + ", id=" + id + "]";
    }
}
