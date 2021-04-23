package model;

public class PremiumReview extends TypeReview {

    private PremiumReview(Builder builder) {
        super(builder);
    }

    public static class Builder extends TypeReview.Builder{
        public TypeReview build(){ return new PremiumReview(this); }
    }

}
