import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedalTest {

    @Test
    void testToString() {
        assertEquals("gold", Medal.GOLD.toString());
        assertEquals("silver", Medal.SILVER.toString());
        assertEquals("bronze", Medal.BRONZE.toString());
    }
}