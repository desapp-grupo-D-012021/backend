package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Review")
public class Review extends ReviewType {

    private Boolean spoilerAlert;
    private String city;

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

    public Review(){
        super();
    }

    public String getCity() {
        return this.city;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public void setSpoilerAlert(Boolean spoilerAlert) {
        this.spoilerAlert = spoilerAlert;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
