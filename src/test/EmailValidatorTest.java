import com.sidarenka.alien.validator.EmailValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmailValidatorTest {
    private EmailValidator validator;
    @BeforeClass
    public void setUp() {
        validator = new EmailValidator();

    }
    @DataProvider(name = "validEmailProvider")
    public Object[][] dataForValidEmail() {
        return new Object[][]{new String[]{
                "j4web@mail.ru", "j4web-100@yandex.ru",
                "j4web.100@gmail.com", "j4web111@j4web.ru",
                "j4web-100@j4web.net", "j4web.100@j4web.com.ru",
                "j4web@1.com", "j4web@gmail.com.com",
                }
        };
    }
    @Test(dataProvider = "validEmailProvider")
    public void validEmailTest(String[] emails) {
        for(String email: emails) {
            boolean valid = validator.isEmailValid(email);
            System.out.println("Email " + email + " is valid: " + valid);
            Assert.assertEquals(true, valid);
        }
    }

    @DataProvider
    public Object[][] invalidEmailProvider() {
        return new Object[][]{
                {new String[]{"j4web", "j4web@.com.my",
                "j4web123@gmail.a", "j4web123@.com",
                "j4web123@.com.com", ".j4web@j4web.ru",
                "j4web()*@gmail.com", "j4web@%*.com",
                "j4web..2002@gmail.com", "j4web.@gmail.com",
                "j4web@j4web@gmail.com", "j4web@gmail.com.1a"
                        }
                }
        };
    }
    @Test(dataProvider = "invalidEmailProvider")
    public void invalidEmailTest(String[] emails) {
        for(String email: emails) {
            boolean valid = validator.isEmailValid(email);
            System.out.println("Email " + email + " is valid: " + valid);
            Assert.assertEquals(false, valid);
        }
    }


}
