package model;

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
    public List<TypeReview> getComments() {
        TypeReview comments = new ArrayList<TypeReview>();
		return comments.addAll(premiumReviews).addAll(reviews);
	}

    public void addReview(Review review) {
		reviews.add(review);
	}

    public void addPremiumReview(PremiumReview premiumReview) {
		premiumReviews.add(premiumReview);
	}
}
