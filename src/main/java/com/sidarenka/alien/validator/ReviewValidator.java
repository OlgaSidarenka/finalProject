package com.sidarenka.alien.validator;

public class ReviewValidator {

    private static final String ALIEN_REVIEW_PATTERN="[\\w\\s\\p{Punct}]{2,300}";

    public static boolean validateReviewData(String textReview){
        return (textReview.matches(ALIEN_REVIEW_PATTERN));
    }
}
