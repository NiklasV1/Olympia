import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionReaderTest {

    @Test
    void testConstructor() {
        // Normal case
        assertDoesNotThrow(() -> new CompetitionReader(getProperAthleteSet()));

        // Exception
        assertThrows(IllegalArgumentException.class, () -> new CompetitionReader(null));
        assertThrows(IllegalArgumentException.class, () -> new CompetitionReader(getShortAthleteSet()));
    }

    @Test
    void read() throws IOException {
        CompetitionReader competitionReader = new CompetitionReader(getProperAthleteSet());

        // Test normal case
        assertDoesNotThrow(() -> competitionReader.read("100;Competition;10.11.2024 16:00;11,21,31,12,41"));

        Competition competition = competitionReader.read("100;Competition;10.11.2024 16:00;11,21,31,12,41");

        assertEquals(100, competition.getId());
        assertEquals("Competition", competition.getName());
        assertEquals(LocalDateTime.of(2024,11,10,16,0,0), competition.getDate());

        assertEquals(11, competition.getResult().get(0).getId());
        assertEquals(21, competition.getResult().get(1).getId());
        assertEquals(31, competition.getResult().get(2).getId());
        assertEquals(12, competition.getResult().get(3).getId());
        assertEquals(41, competition.getResult().get(4).getId());

        // Test Exception
        assertThrows(IOException.class, () -> competitionReader.read("a;Competition;10.11.2024 16:00;11,21,31,12,41"));
        assertThrows(IOException.class, () -> competitionReader.read("100;;10.11.2024 16:00;11,21,31,12,41"));
        assertThrows(IOException.class, () -> competitionReader.read("100;Competition;10.20.2024 16:00;11,21,31,12,41"));
        assertThrows(IOException.class, () -> competitionReader.read("100;Competition;10.11.2024 25:00;11,21,31,12,41"));
        assertThrows(IOException.class, () -> competitionReader.read("100;Competition;10.11.2024 16:00;11,21,31,12,41,10000"));
        assertThrows(IOException.class, () -> competitionReader.read("100;Competition;10.11.2024 16:00;"));
        assertThrows(IOException.class, () -> competitionReader.read(""));
        assertThrows(IOException.class, () -> competitionReader.read(null));
    }

    private static Set<Athlete> getProperAthleteSet() {
        Set<Athlete> athletes = new HashSet<>();

        Team team1 = new Team(10, "Team1", "T1");
        Team team2 = new Team(20, "Team2", "T2");
        Team team3 = new Team(30, "Team3", "T3");
        Team team4 = new Team(40, "Team4", "T4");

        athletes.add(new Athlete(11, "Ath1", team1));
        athletes.add(new Athlete(12, "Ath2", team1));
        athletes.add(new Athlete(21, "Ath3", team2));
        athletes.add(new Athlete(31, "Ath4", team3));
        athletes.add(new Athlete(41, "Ath5", team4));

        return athletes;
    }

    private static Set<Athlete> getShortAthleteSet() {
        Set<Athlete> athletes = new HashSet<>();

        Team team1 = new Team(10, "Team1", "T1");
        Team team2 = new Team(20, "Team2", "T2");

        athletes.add(new Athlete(11, "Ath1", team1));
        athletes.add(new Athlete(21, "Ath2", team2));

        return athletes;
    }
}