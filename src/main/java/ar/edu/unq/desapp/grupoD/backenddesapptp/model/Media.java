package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING)
public abstract class Media {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String imdbId;
    @Column
    private String title;
    @Column
    private String primaryTitle;
    @Column
    private String originalTitle;
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "media_id")
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "media_id")
    private List<PremiumReview> premiumReviews;

    public Media(String imdbId, String title, String primaryTitle, String originalTitle){
        this.imdbId = imdbId;
        this.title = title;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.reviews = new ArrayList<Review>();
        this.premiumReviews = new ArrayList<PremiumReview>();
    }

    public String getImdbId() {
		return this.imdbId;
	}

    //TODO: add filters (platform, spoiler, type, language, country)
    public List<Review> getReviews() {
        return this.reviews;
    }

    public List<PremiumReview> getPremiumReviews() {
        return this.premiumReviews;
    }

    //TODO: (ws?) method to call reviews & critics
    public List<ReviewType> getComments() {
        List<ReviewType> comments = new ArrayList<ReviewType>();
		comments.addAll(this.getPremiumReviews());
        comments.addAll(this.getReviews());
        return comments;
	}

    public void addReview(Review review) {
		this.reviews.add(review);
	}

    public void addPremiumReview(PremiumReview premiumReview) {
		this.premiumReviews.add(premiumReview);
	}
}
