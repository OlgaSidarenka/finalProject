package test.sidarenka.alien.validator;

import com.sidarenka.alien.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginValidatorTest {
    private UserValidator userValidator;

    @BeforeClass
    public void setUp() {
        userValidator = new UserValidator();

    }

    @DataProvider(name = "validLoginProvider")
    public Object[][] dataForValidLogin() {
        return new Object[][]{{"Daniil_S", true},
                {"sergey123", true},
                {"GEORGY", true},
                {"tim_2008", true},
        };
    }

    @Test(dataProvider = "validLoginProvider")
    public void loginValidatorTestPositive(String login, boolean expected) {
        boolean actual = userValidator.isLoginValid(login);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] invalidLoginProvider() {
        return new Object[][]{{"Nik", false},
                {"nik@sergeev", false},
                {"NikolayIvanovichPetrov", false},
                {"123andry", false},
        };
    }

    @Test(dataProvider = "invalidLoginProvider")
    public void emailValidatorTestNegative(String login, boolean expected) {
        boolean actual = userValidator.isLoginValid(login);
        Assert.assertEquals(actual, expected);
    }
}


