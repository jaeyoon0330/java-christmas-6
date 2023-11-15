package christmas;

public enum MenuEnum {
    SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    SALAD("시저샐러드", 8000),
    STEAK("티본스테이크", 55000),
    LIB("바베큐립", 54000),
    SEA_PASTA("해산물파스타", 35000),
    CHR_PASTA("크리스마스파스타", 25000),
    CAKE("초코케이크", 15000),
    ICECREAM("아이스크림", 5000),
    COKE("제로콜라", 3000),
    WINE("레드와인", 60000),
    CHAMPAIN("샴페인", 25000);

    private final String description;
    private final int price;

    MenuEnum(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
