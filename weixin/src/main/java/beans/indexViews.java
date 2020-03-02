package beans;

public class indexViews {
    private String date;
    private String signImage;
    private String signTitle;
    private String journalTitle;
    private String journalContent;
    private String journalImage;
    private String journalEditDate;
    private String journalText;

    private String videoTitle;
    private String videoContent;
    private String videoImage;
    private String videoUrl;

    private String exampleTitle;
    private String exampleContent;
    private String exampleImage;
    private String exampleText;

    public indexViews(String date){
        this.date=date;
    }

    public String getJournalText() {
        return journalText;
    }

    public void setJournalText(String journalText) {
        this.journalText = journalText;
    }

    public String getDate() {
        return date;
    }

    public String getSignImage() {
        return signImage;
    }

    public String getSignTitle() {
        return signTitle;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public String getJournalContent() {
        return journalContent;
    }

    public String getJournalImage() {
        return journalImage;
    }

    public String getJournalEditDate() {
        return journalEditDate;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public String getExampleTitle() {
        return exampleTitle;
    }

    public String getExampleContent() {
        return exampleContent;
    }

    public String getExampleImage() {
        return exampleImage;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSignImage(String signImage) {
        this.signImage = signImage;
    }

    public void setSignTitle(String signTitle) {
        this.signTitle = signTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public void setJournalContent(String journalContent) {
        this.journalContent = journalContent;
    }

    public void setJournalImage(String journalImage) {
        this.journalImage = journalImage;
    }

    public void setJournalEditDate(String journalEditDate) {
        this.journalEditDate = journalEditDate;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public void setExampleTitle(String exampleTitle) {
        this.exampleTitle = exampleTitle;
    }

    public void setExampleContent(String exampleContent) {
        this.exampleContent = exampleContent;
    }

    public void setExampleImage(String exampleImage) {
        this.exampleImage = exampleImage;
    }

    public String getExampleText() {
        return exampleText;
    }

    public void setExampleText(String exampleText) {
        this.exampleText = exampleText;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "indexViews [date=" +date +", videoUrl=" +videoUrl +", exampleText=" + exampleText +", journalText=" + journalText + ", signImage=" + signImage + ", signTitle=" + signTitle + ", journalTitle=" + journalTitle + ", journalContent=" + journalContent + ", journalImage=" + journalImage+ ", journalEditDate=" + journalEditDate + ", videoTitle=" + videoTitle + ", videoContent=" + videoContent +", videoImage=" + videoImage + ", exampleTitle=" + exampleTitle +", exampleContent=" + exampleContent + ", exampleImage=" + exampleImage + "]";
    }
}
