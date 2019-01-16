package test.sidarenka.alien.validator;

import com.sidarenka.alien.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmailValidatorTest {
    private UserValidator userValidator;

    @BeforeClass
    public void setUp() {
        userValidator = new UserValidator();

    }

    @DataProvider(name = "validEmailProvider")
    public Object[][] dataForValidEmail() {
        return new Object[][]{{"j4web@mail.ru", true},
                {"j4web_100@yandex.ru", true},
                {"georgy@gmail.com", true},
                {"j4web@gmail.com", true},
                {"j4web-100@j4web.com", true},
        };
    }

    @Test(dataProvider = "validEmailProvider")
    public void emailValidatorTestPositive(String email, boolean expected) {
        boolean actual = userValidator.isEmailValid(email);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] invalidEmailProvider() {
        return new Object[][]{{"j4web", false},
                {"J4web@.com.my", false},
                {"j4web@%*.com", false},
                {"Timon..2002@gmail.com", false},
                {"j4web@j4web@gmail.com", false},
                {"j4web()*@gmail.com", false},
                {"j4web123@.com", false},
        };
    }

    @Test(dataProvider = "invalidEmailProvider")
    public void emailValidatorTestNegative(String email, boolean expected) {
        boolean actual = userValidator.isEmailValid(email);
        Assert.assertEquals(actual, expected);
    }
}
