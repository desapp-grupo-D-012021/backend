package model;

public class Review extends TypeReview {

    private Boolean spoilerAlert;
    private String city;

    public static class Builder extends TypeReview.Builder{

        private Boolean spoilerAlert;
        private String city;


        public Builder withSpoilerAlert(Boolean spoilerAlert){
            this.spoilerAlert = spoilerAlert;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Review build(){
            return new Review(this);
        }
    }
    private Review(final Builder builder) {
        super(builder);
        this.spoilerAlert = builder.spoilerAlert;
        this.city = builder.city;
    }
}
