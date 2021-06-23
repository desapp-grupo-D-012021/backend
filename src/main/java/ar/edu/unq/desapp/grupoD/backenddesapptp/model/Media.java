package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="mediaType", discriminatorType=DiscriminatorType.STRING)
public abstract class Media implements Serializable {

    @Id
    @NotNull
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private String imdbId;
    @Column
    private String title;
    @Column
    private String primaryTitle;
    @Column
    private String originalTitle;
    @Column
    private String genre;
    @Column
    private int year;
    /*
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "media_id")
    private List<String> actors;
    */
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "media_id")
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "media_id")
    private List<PremiumReview> premiumReviews;

    public Media() {}

    public Media(String imdbId, String title, String primaryTitle, String originalTitle, String genre, int year){
        this.imdbId = imdbId;
        this.title = title;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.genre = genre;
        this.year = year;
        //this.actors = new ArrayList<String>();
        this.reviews = new ArrayList<Review>();
        this.premiumReviews = new ArrayList<PremiumReview>();
    }

    public String getImdbId() {
		return this.imdbId;
	}

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setPremiumReviews(List<PremiumReview> premiumReviews) {
        this.premiumReviews = premiumReviews;
    }

    //TODO: add filters (platform, spoiler, type, language, country)
    public List<Review> getReviews() {
        return this.reviews;
    }

    public List<PremiumReview> getPremiumReviews() {
        return this.premiumReviews;
    }

    //TODO: (ws?) method to call reviews & critics
    /*
    public List<ReviewType> getComments() {
        List<ReviewType> comments = new ArrayList<ReviewType>();
		comments.addAll(this.getPremiumReviews());
        comments.addAll(this.getReviews());
        return comments;
	}
	*/

    public void addReview(Review review) {
		this.reviews.add(review);
	}

    public void addPremiumReview(PremiumReview premiumReview) {
		this.premiumReviews.add(premiumReview);
	}
}
