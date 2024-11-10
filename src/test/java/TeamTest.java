import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Team(10, "test", null));
        assertThrows(IllegalArgumentException.class, () -> new Team(10, "test", ""));

        assertDoesNotThrow(() -> new Team(10, "test", "TST"));
    }

    @Test
    void getAbbreviation() {
        Team testTeam = new Team(10, "Test", "TST");

        assertEquals("TST", testTeam.getAbbreviation());
    }

    @Test
    void testToString() {
        Team testTeam = new Team(10, "Test", "TST");

        assertEquals("10 Test (TST)", testTeam.toString());
    }
}