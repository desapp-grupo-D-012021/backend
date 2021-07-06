package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


@Entity
@Table(name = "Reviews_Builder")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="discriminator",
        discriminatorType=DiscriminatorType.STRING
)
public abstract class ReviewType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "text")
    private String text;
    @Column(name = "extendedText")
    private String extendedText;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @Column(name = "platform")
    private String platform;
    @Column(name = "username")
    private String userNameInPlatform;
    @Column(name = "language")
    private String language;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name = "receive_reports")
    private Integer receiveReports;

    private String city;

    private Boolean spoilerAlert;

    private Boolean isPremium;
    @Transient
    private ArrayList<String> reports;

    @ManyToOne
    @JoinColumn(name="media_id")
    @JsonIgnore
    private Media media;


    public abstract static class Builder {
        private String text;
        private String extendedText;
        private Integer rating;
        private LocalDate publicationDate;
        private String platform;
        private String userNameInPlatform;
        private String language;
        private Integer likes;
        private Integer dislikes;
        private ArrayList<String> reports;
        private Integer receiveReports;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withExtendedText(String extendedText) {
            this.extendedText = extendedText;
            return this;
        }

        public Builder withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder withPublicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withPlatform(String platform) {
            this.platform = platform;
            return this;
        }

        public Builder withUserNameInPlatform(String userNameInPlatform) {
            this.userNameInPlatform = userNameInPlatform;
            return this;
        }

        public Builder withLanguage(String language) {
            this.language = language;
            return this;
        }

        public Builder withLikes(Integer likes) {
            this.likes = likes;
            return this;
        }

        public Builder withDislikes(Integer dislikes) {
            this.dislikes = dislikes;
            return this;
        }

        public Builder withReport(ArrayList<String> reports) {
            this.reports = reports;
            return this;
        }

        public Builder withReceiveReports(Integer i) {
            this.receiveReports = i;
            return this;
        }

        public abstract ReviewType build();

    }

    protected ReviewType(final Builder builder) {
        this.text = builder.text;
        this.extendedText = builder.extendedText;
        this.rating = builder.rating;
        this.publicationDate = builder.publicationDate;
        this.platform = builder.platform;
        this.userNameInPlatform = builder.userNameInPlatform;
        this.language = builder.language;
        this.likes = builder.likes;
        this.dislikes = builder.dislikes;
        this.reports = builder.reports;
        this.receiveReports = builder.receiveReports;

    }

    public ReviewType() {
        this.likes = 0;
        this.dislikes = 0;
        this.receiveReports = 0;
        this.reports = new ArrayList<String>();
        this.isPremium = null;
    }

    public Integer getRating() {
        return this.rating;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void ratePositevely() {
        this.likes++;
    }

    public Integer getDislikes() {
        return this.dislikes;
    }

    public void rateNegatively() {
        this.dislikes++;
    }

    public String getText() {
        return text;
    }

    public String getExtendedText() {
        return extendedText;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getPlatform() {
        return platform;
    }

    public String getUserNameInPlatform() {
        return userNameInPlatform;
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<String> getReports() {
        return reports;
    }

    public int getId() {
        return this.id;
    }

    public Integer getReceiveReports() {
        return receiveReports;
    }

    public void setReceiveReports(Integer receiveReports) {
        this.receiveReports = receiveReports;
    }

    public boolean isPremium() {
        return this.isPremium;
    }

    public void setPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public Boolean getSpoilerAlert() {
        return this.spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMedia(Media media){
        this.media = media;
    }

    public Media getMedia(){
        return this.media;
    }

    public void addReport(ReportType type){
        this.reports.add(type.toString());
        this.receiveReports++;
    }

    public boolean hasReport(){
        return this.receiveReports > 0;
    }

    public enum  ReportType{
        OFFENSIVE("Ofensive"),
        BAD_WORDS("Bad Words"),
        SPOILER("Spoiler"),
        SENSELESS("Senseless");

        private final String name;

        private ReportType (String s) {
            name = s;
        }

        public String toString() {
            return this.name;
        }
    }
}
