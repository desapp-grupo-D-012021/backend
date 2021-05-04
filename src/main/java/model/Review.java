package model;

public class Review extends ReviewType {

    private Boolean spoilerAlert;
    private String city;

    public String getCity() {
        return this.city;
    }

    public static class Builder extends ReviewType.Builder{

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
    Review(final Builder builder) {
        super(builder);
        this.spoilerAlert = builder.spoilerAlert;
        this.city = builder.city;
    }
}
