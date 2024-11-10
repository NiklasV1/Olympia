import javax.swing.*;
import java.util.Objects;

public abstract class OlympicEntity implements Comparable<OlympicEntity> {
    private final int id;
    private final String name;

    public OlympicEntity(int id, String name) {
        if (name == null || id < 1) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            OlympicEntity entity = (OlympicEntity) obj;
            return entity.getId() == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(OlympicEntity o) {
        return switch (Integer.compare(id, o.getId())) {
            case -1 -> -1;
            case 1 -> 1;
            default -> 0;
        };
    }
}
