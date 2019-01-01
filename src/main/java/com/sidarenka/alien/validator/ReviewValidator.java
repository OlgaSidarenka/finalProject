package com.sidarenka.alien.validator;

public class ReviewValidator {

    private static final String ALIEN_REVIEW_PATTERN="[\\w\\s]{1,300}";


    public static boolean validateReviewData(String textRview){
        return (textRview.matches(ALIEN_REVIEW_PATTERN));
    }
}
