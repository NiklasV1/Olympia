public enum Medal {
    GOLD, SILVER, BRONZE;

    @Override
    public String toString() {
        return switch (this) {
            case GOLD -> "gold";
            case SILVER -> "silver";
            case BRONZE -> "bronze";
        };
    }
}
