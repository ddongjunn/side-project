import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    public void setUp() {
        Main.productCodes = Main.getProductCodes();
    }

    @Test
    void testGetProductCodes() {
        HashSet<String> productCodes = Main.getProductCodes();
        assertEquals(21, productCodes.size());

        for (String code : productCodes) {
            assertEquals(9, code.length());
            assertTrue(code.matches("\\d{9}"));
        }
    }

    @Test
    public void testIsProductCode() {
        StringTokenizer validInput = new StringTokenizer("111111111");
        StringTokenizer invalidInput = new StringTokenizer("1234567");

        assertTrue(Main.isProductCode(validInput));
        assertFalse(Main.isProductCode(invalidInput));
    }

    @Test
    void testIsAvailableProductCode() {
        Main.productCodes = Main.getProductCodes();
        assertTrue(Main.isAvailableProductCode("111111111"));
        assertFalse(Main.isAvailableProductCode("222222222"));
    }

    @Test
    void testIsShopCode() {
        assertTrue(Main.isShopCode("ABCDEF"));
        assertFalse(Main.isShopCode("123456"));
        assertFalse(Main.isShopCode("ABCDE"));
    }
}
