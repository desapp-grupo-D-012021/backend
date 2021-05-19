package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


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
    private int id;

    @Column(name = "text")
    private String text;
    @Column(name = "extendedText")
    private String extendedText;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd")
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

        public Builder withPlatform(String platform){
            this.platform = platform;
            return this;
        }

        public Builder withUserNameInPlatform(String userNameInPlatform) {
            this.userNameInPlatform = userNameInPlatform;
            return this;
        }

        public Builder withLanguage(String language){
            this.language = language;
            return this;
        }

        public Builder withLikes(Integer likes){
            this.likes = likes;
            return  this;
        }

        public Builder withDislikes(Integer dislikes){
            this.dislikes = dislikes;
            return  this;
        }
        public abstract ReviewType build();

    }

    protected ReviewType(final Builder builder){
        this.text = builder.text;
        this.extendedText = builder.extendedText;
        this.rating = builder.rating;
        this.publicationDate = builder.publicationDate;
        this.platform = builder.platform;
        this.userNameInPlatform = builder.userNameInPlatform;
        this.language = builder.language;
        this.likes = builder.likes;
        this.dislikes = builder.dislikes;

    }

    public ReviewType(){
        super();
        this.likes = 0;
        this.dislikes = 0;
    }

    public Integer getRating(){
        return this.rating;
    }

    public Integer getLikes(){
        return this.likes;
    }

    public void ratePositevely(){
        this.likes++;
    }

    public Integer getDislikes(){
        return this.dislikes;
    }

    public void rateNegatively(){
        this.dislikes++;
    }
}
