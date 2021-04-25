package model;

public class Review extends TypeReview {

    private Boolean spoilerAlert;
    private String city;

    public String getCity() {
        return this.city;
    }

    public static class Builder2 extends TypeReview.Builder{

        private Boolean spoilerAlert;
        private String city;


        public Builder2 withSpoilerAlert(Boolean spoilerAlert){
            this.spoilerAlert = spoilerAlert;
            return this;
        }

        public Builder2 withCity(String city){
            this.city = city;
            return this;
        }


        public Review build(){
            return new Review(this);
        }
    }
    Review(final Builder2 builder) {
        super(builder);
        this.spoilerAlert = builder.spoilerAlert;
        this.city = builder.city;
    }
}
