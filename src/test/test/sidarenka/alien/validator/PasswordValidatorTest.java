package test.sidarenka.alien.validator;

import com.sidarenka.alien.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordValidatorTest {
    private UserValidator userValidator;
    @BeforeClass
    public void setUp() {
        userValidator = new UserValidator();

    }

    @DataProvider(name = "validPasswordProvider")
    public Object[][] dataForValidPassword() {
        return new Object[][]{{"Danik_S2008", true},
                {"Sergey-123", true},
                {"nik8@Sergeev", true},
                {"1GEORGY%_main", true},
                {"Tim#2008_march", true},
        };
    }

    @Test(dataProvider = "validPasswordProvider")
    public void loginValidatorTestPositive(String password, boolean expected) {
        boolean actual = userValidator.isPasswordValid(password);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] invalidPasswordProvider() {
        return new Object[][]{{"Nik", false},
                {"nik@sergeev", false},
                {"NikolayIvanovichPetrov", false},
                {"123andry", false},
        };
    }

    @Test(dataProvider = "invalidPasswordProvider")
    public void PasswordValidatorTestNegative(String password, boolean expected) {
        boolean actual = userValidator.isPasswordValid(password);
        Assert.assertEquals(actual, expected);
    }
}
