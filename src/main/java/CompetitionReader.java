import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CompetitionReader {
    Set<Athlete> athletes;

    public CompetitionReader(Set<Athlete> athletes) {
        if (athletes.size() < 3) {
            throw new IllegalArgumentException();
        }
        this.athletes = athletes;
    }

    public Competition read(String input) throws IOException {
        try {
            String[] inputFields = input.split(";");

            int id = Integer.parseInt(inputFields[0]);
            String name = inputFields[1];
            LocalDateTime date = LocalDateTime.parse(inputFields[2], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            List<Athlete> result = parseAthleteIDs(inputFields[3]);

            return new Competition(id, name, date, result);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private List<Athlete> parseAthleteIDs(String largeIDString) throws IOException {
        List<Athlete> result = new ArrayList<>();
        String[] splitIDs = largeIDString.split(",");

        for (String idString : splitIDs) {
            int id = Integer.parseInt(idString);
            Athlete selectedAthlete = null;

            for (Athlete athlete : athletes) {
                if (athlete.getId() == id) {
                    selectedAthlete = athlete;
                }
            }

            if (selectedAthlete == null) {
                throw new IOException();
            }

            result.add(selectedAthlete);
        }

        return result;
    }
}
