package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

public class ReviewSearchCriteria {

    private String platform;
    private Boolean spoilerAlert;
    private ReviewType reviewType;
    private String language;
    private String city;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
