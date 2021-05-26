package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PremiumReview")
public class PremiumReview extends ReviewType {

    private PremiumReview(Builder builder) {
        super(builder);
    }

    public static class Builder extends ReviewType.Builder{
        public ReviewType build(){ return new PremiumReview(this); }
    }

}
