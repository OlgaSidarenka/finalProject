package test.sidarenka.alien.util;

import com.sidarenka.alien.util.PasswordDecoder;
import com.sidarenka.alien.util.PasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordDecoderTest {
    @Test
    public void passwordDecoderTest() {
        String password = new String("Inspector75");
        String encodedPassword = PasswordEncoder.encodePassword(password);
        String decodedPassword = PasswordDecoder.decodePassword(encodedPassword);
        Assert.assertEquals(password, decodedPassword);
    }
}
