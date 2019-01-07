import com.sidarenka.alien.validator.EmailValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmailValidatorTest {
    private EmailValidator emailValidator;

    @BeforeClass
    public void setUp() {
        emailValidator = new EmailValidator();

    }

    @DataProvider(name = "validEmailProvider")
    public Object[][] dataForValidEmail() {
        return new Object[][]{{"j4web@mail.ru", true},
                {"j4web_100@yandex.ru", true},
                {"GEORGY@gmail.com", true},
                {"j4web@gmail.com", true},
                {"j4web-100@j4web.com", true},
        };
    }

    @Test(dataProvider = "validEmailProvider")
    public void emailValidaterTestPositive(String email, boolean expected) {
        boolean actual = emailValidator.isEmailValid(email);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] invalidEmailProvider() {
        return new Object[][]{{"j4web", false},
                {"j4web@.com.my", false},
                {"j4web@%*.com", false},
                {"j4web..2002@gmail.com", false},
                {"j4web@j4web@gmail.com", false},
                {"j4web()*@gmail.com", false},
                {"j4web123@.com", false},
                {"j4web.@gmail.com", false},
        };
    }

    @Test(dataProvider = "invalidEmailProvider")
    public void emailValidaterTestNegative(String email, boolean expected) {
        boolean actual = emailValidator.isEmailValid(email);
        Assert.assertEquals(actual, expected);
    }
}
