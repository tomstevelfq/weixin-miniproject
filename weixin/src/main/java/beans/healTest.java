package beans;

public class healTest {
    private double rate;
    private String words;
    private String image;
    private int id;

    public healTest(double a,String words,String image){
        this.rate=a;
        this.words=words;
        this.image=image;
    }

    public healTest(double a,String words,String image,int id){
        this.rate=a;
        this.words=words;
        this.image=image;
        this.id=id;
    }

    public double getRate() {
        return rate;
    }

    public String getWords() {
        return words;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "healTest [rate=" + rate + ", words=" + words + ", image=" + image +", id=" + id +"]";
    }

}
