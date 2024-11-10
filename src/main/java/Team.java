public class Team extends OlympicEntity {
    private final String abbreviation;

    public Team(int id, String name, String abbreviation) {
        super(id, name);
        if (abbreviation == null) {
            throw new IllegalArgumentException();
        }
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return super.getId() + super.getName() + "(" + abbreviation + ")";
    }
}
