import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Competition extends OlympicEntity{
    private final LocalDateTime date;
    private final List<Athlete> result;

    public Competition(int id, String name, LocalDateTime date, List<Athlete> result) {
        super(id, name);
        if (date == null || result == null) {
            throw new IllegalArgumentException();
        }
        checkList(result);
        this.date = date;
        this.result = result;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Athlete> getResult() {
        return result;
    }

    public Athlete getMedalist(Medal medal) {
        if (medal == null) {
            throw new IllegalArgumentException();
        }
        return switch (medal) {
            case GOLD -> result.get(0);
            case SILVER -> result.get(1);
            case BRONZE -> result.get(2);
        };
    }

    public Optional<Athlete> getBestCompetitor(Team team) {
        if (team == null) {
            throw new IllegalArgumentException();
        }
        Optional<Athlete> returnValue = Optional.empty();
        for (Athlete athlete : result) {
            if (athlete.getTeam().equals(team)) {
                returnValue = Optional.of(athlete);
                break;
            }
        }
        return returnValue;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
        " - " +
        super.getName() +
        " - " +
        "gold: " + getMedalist(Medal.GOLD).toString() +
        ", silver: " + getMedalist(Medal.SILVER).toString() +
        ", bronze: " + getMedalist(Medal.BRONZE).toString();
    }

    private void checkList(List<Athlete> result) {
        if (result.size()<3) {
            throw new IllegalArgumentException();
        }
        for (Athlete athlete : result) {
            int counter = 0;
            for (Athlete a : result) {
                if (a.equals(athlete)) {
                    counter++;
                }
            }
            if (counter > 1) {
                throw new IllegalArgumentException();
            }
        }
    }
}
