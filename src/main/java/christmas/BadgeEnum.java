package christmas;

public enum BadgeEnum {
        STAR("별"),
        TREE("트리"),
        SANTA("산타"),
        NONE("없음");

        private final String description;

        BadgeEnum(String description) {
                this.description = description;
        }

        public String getBadgeDescription() {
                return description;
        }
}
