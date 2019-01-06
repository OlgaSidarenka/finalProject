package test.sidarenka.alien.validator;

import com.sidarenka.alien.validator.AlienValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AlienValidatorTest {
    @DataProvider(name = "alienDescription")
    public Object[][] dataForAlienDescription() {
        return new Object[][]{{"Very fanny alien.", true},
                {"Good and kind!!!", true},
                {"I want give 5 for this alien", true},
                {"H", false},
                {"", false},
        };
    }

    @DataProvider(name = "alienData")
    public Object[][] dataForAlien() {
        return new Object[][]{{"", "", "", false},
                {"Norm", "Green planet",  "T", false},
                {"", "Green planet", "I like it", false},
                {"Pet", "Very long cartoon's name", "I like it", false},
                {"Garry","Garry and Poll", "Very funny!!!", true},
                {"Norm", "Green planet", "I like it.", true},
                {"Big Make", "Green planet", "I have seen it 5 times!", true},
        };
    }

    @Test(dataProvider="alienDescription")
    public void  validateAlienDescriptionTest(String alienDescription,boolean expected){
        boolean actual=AlienValidator.validateAlienDescription(alienDescription);
        Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider="alienData")
    public void  validateAlienDataTest(String alienName, String alienHomeland, String alienDescription,boolean expected){
        boolean actual=AlienValidator.validateAlienData(alienName, alienHomeland,alienDescription);
        Assert.assertEquals(actual,expected);
    }

}
