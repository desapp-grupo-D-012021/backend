package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

public class ReviewSearchCriteria {
    private String city;
    private String platform;
    private String language;
    private boolean spoilerAlert;
    private boolean isPremium;

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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

}
