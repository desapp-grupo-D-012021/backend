package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Media {

    private String imdbId;
    private String title;
    private String primaryTitle;
    private String originalTitle;
    private List<Review> reviews;
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
    public List<TypeReview> getComments() {
        List<TypeReview> comments = new ArrayList<TypeReview>();
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
