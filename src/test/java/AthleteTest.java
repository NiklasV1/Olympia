import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Athlete(10, "John Doe", null));

        Team team = new Team(20, "Test", "TST");
        assertDoesNotThrow(() -> new Athlete(30, "John Doe", team));
    }

    @Test
    void getTeam() {
        Team team = new Team(20, "Test", "TST");
        Athlete athlete = new Athlete(30, "John Doe", team);

        assertEquals(team, athlete.getTeam());
    }

    @Test
    void testToString() {
        Team team = new Team(20, "Test", "TST");
        Athlete athlete = new Athlete(30, "John Doe", team);

        assertEquals("John Doe (TST)", athlete.toString());
    }
}