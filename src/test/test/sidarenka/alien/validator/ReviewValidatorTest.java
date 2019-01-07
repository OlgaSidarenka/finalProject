package test.sidarenka.alien.validator;

import com.sidarenka.alien.validator.ReviewValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReviewValidatorTest {
    @DataProvider(name = "reviewData")
    public Object[][] dataForAlien() {
        return new Object[][]{{"", false},
                {"T", false},
                {"I have seen that cartoon 5 times! I like it", true},
                {"Good", true},
        };
    }

    @Test(dataProvider = "reviewData")
    public void validateReviewTest(String textReview, boolean expected) {
        boolean actual = ReviewValidator.validateReviewData(textReview);
        Assert.assertEquals(actual, expected);
    }
}
