package com.sidarenka.alien.validator;

/**
 * The Class ReviewValidator.
 */
public class ReviewValidator {

    /** The Constant ALIEN_REVIEW_PATTERN. */
    private static final String ALIEN_REVIEW_PATTERN="[\\w\\s\\p{Punct}]{2,1000}";

    /**
     * Validate review data.
     *
     * @param textReview the text review
     * @return true, if successful
     */
    public static boolean validateReviewData(String textReview){
        return (textReview.matches(ALIEN_REVIEW_PATTERN));
    }
}
