package model;

import java.time.LocalDate;

public abstract class TypeReview {

    private String text;
    private String extendedText;
    private Integer rating;
    private LocalDate publicationDate;
    private String platform;
    private String userNameInPlatform;
    private String language;

    public Integer getRating(){
        return this.rating;
    }

    public abstract static class Builder {
        private String text;
        private String extendedText;
        private Integer rating;
        private LocalDate publicationDate;
        private String platform;
        private String userNameInPlatform;
        private String language;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withExtendedText(String extendedText) {
            this.extendedText = extendedText;
            return this;
        }

        public Builder withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder withPublicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withPlatform(String platform){
            this.platform = platform;
            return this;
        }

        public Builder withUserNameInPlatform(String userNameInPlatform) {
            this.userNameInPlatform = userNameInPlatform;
            return this;
        }

        public Builder withLanguage(String language){
            this.language = language;
            return this;
        }

        public abstract TypeReview build();

    }

    protected TypeReview(final Builder builder){
        this.text = builder.text;
        this.extendedText = builder.extendedText;
        this.rating = builder.rating;
        this.publicationDate = builder.publicationDate;
        this.platform = builder.platform;
        this.userNameInPlatform = builder.userNameInPlatform;
        this.language = builder.language;
    }
}
