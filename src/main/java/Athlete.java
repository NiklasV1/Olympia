public class Athlete extends OlympicEntity{
    private final Team team;

    public Athlete(int id, String name, Team team) {
        super(id, name);
        if (team == null) {
            throw new IllegalArgumentException();
        }
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return super.getName() + "(" + team.getAbbreviation() + ")";
    }
}
