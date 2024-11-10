import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionTest {

    @Test
    void testConstructor() {
        LocalDateTime date = getProperDate();

        List<Athlete> properList = getProperList();
        List<Athlete> shortList = getShortList();
        List<Athlete> duplicateList = getDuplicateList();

        // Test with good arguments
        assertDoesNotThrow(() -> new Competition(100, "Competition", date, properList));

        // Test with null arguments
        assertThrows(IllegalArgumentException.class, () -> new Competition(100, "Competition", null, properList));
        assertThrows(IllegalArgumentException.class, () -> new Competition(100, "Competition", date, null));

        // Test with too short list
        assertThrows(IllegalArgumentException.class, () -> new Competition(100, "Competition", date, shortList));

        // Test with list with duplicate athletes
        assertThrows(IllegalArgumentException.class, () -> new Competition(100, "Competition", date, duplicateList));
    }

    @Test
    void getDate() {
        LocalDateTime date = getProperDate();
        List<Athlete> properList = getProperList();

        Competition competition = new Competition(100, "Competition", date, properList);

        assertEquals(date, competition.getDate());
    }

    @Test
    void getResult() {
        LocalDateTime date = LocalDateTime.of(2024, 11, 10, 14, 57, 0);
        List<Athlete> properList = getProperList();

        Competition competition = new Competition(100, "Competition", date, properList);

        assertEquals(properList, competition.getResult());
    }

    @Test
    void getMedalist() {
        LocalDateTime date = LocalDateTime.of(2024, 11, 10, 14, 57, 0);
        List<Athlete> properList = getProperList();

        Competition competition = new Competition(100, "Competition", date, properList);

        // Test default case
        assertEquals(11, competition.getMedalist(Medal.GOLD).getId());
        assertEquals(12, competition.getMedalist(Medal.SILVER).getId());
        assertEquals(21, competition.getMedalist(Medal.BRONZE).getId());

        // Test Exception
        assertThrows(IllegalArgumentException.class, () -> competition.getMedalist(null));
    }

    @Test
    void getBestCompetitor() {
        LocalDateTime date = LocalDateTime.of(2024, 11, 10, 14, 57, 0);

        Team team1 = new Team(10, "Team1", "T1");
        Team team2 = new Team(20, "Team2", "T2");
        Team team3 = new Team(30, "Team3", "T3");

        Athlete athlete1 = new Athlete(11, "Ath1", team1);
        Athlete athlete2 = new Athlete(12, "Ath2", team1);
        Athlete athlete3 = new Athlete(21, "Ath3", team2);

        List<Athlete> properList = new ArrayList<>();
        properList.add(athlete1);
        properList.add(athlete2);
        properList.add(athlete3);

        Competition competition = new Competition(100, "Competition", date, properList);

        // Test normal case
        assertEquals(athlete1, competition.getBestCompetitor(team1).orElseThrow());
        assertEquals(athlete3, competition.getBestCompetitor(team2).orElseThrow());

        // Test case without athlete for requested team
        assertTrue(competition.getBestCompetitor(team3).isEmpty());

        // Test Exception
        assertThrows(IllegalArgumentException.class, () -> competition.getBestCompetitor(null));
    }

    @Test
    void testToString() {
        LocalDateTime date = getProperDate();
        List<Athlete> properList = getProperList();

        Competition competition = new Competition(100, "Competition", date, properList);
        assertEquals("10.11.2024 14:57 - Competition - gold: Ath1 (T1), silver: Ath2 (T1), bronze: Ath3 (T2)", competition.toString());
    }

    private static List<Athlete> getProperList() {
        List<Athlete> properList = new ArrayList<>();

        Team team1 = new Team(10, "Team1", "T1");
        Team team2 = new Team(20, "Team2", "T2");
        Team team3 = new Team(30, "Team3", "T3");
        Team team4 = new Team(40, "Team4", "T4");

        properList.add(new Athlete(11, "Ath1", team1));
        properList.add(new Athlete(12, "Ath2", team1));
        properList.add(new Athlete(21, "Ath3", team2));
        properList.add(new Athlete(31, "Ath4", team3));
        properList.add(new Athlete(41, "Ath5", team4));

        return properList;
    }

    private static List<Athlete> getShortList() {
        List<Athlete> shortList = new ArrayList<>();

        Team team1 = new Team(10, "Team1", "T1");
        Team team2 = new Team(20, "Team2", "T2");

        shortList.add(new Athlete(11, "Ath1", team1));
        shortList.add(new Athlete(21, "Ath2", team2));

        return shortList;
    }

    private static List<Athlete> getDuplicateList() {
        List<Athlete> duplicateList = new ArrayList<>();

        Team team1 = new Team(10, "Team1", "T1");
        Team team2 = new Team(20, "Team2", "T2");

        Athlete athlete1 = new Athlete(11, "Ath1", team1);
        Athlete athlete2 = new Athlete(21, "Ath2", team2);

        duplicateList.add(athlete1);
        duplicateList.add(athlete2);
        duplicateList.add(athlete1);

        return duplicateList;
    }

    private static LocalDateTime getProperDate() {
        return LocalDateTime.of(2024, 11, 10, 14, 57, 0);
    }
}