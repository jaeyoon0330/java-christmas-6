package christmas;

public enum SaleEnum {
    sale_chr("크리스마스 디데이 할인",0, false),
    sale_weekday("평일 할인",0, false),
    sale_weekend("주말 할인",0, false),
    sale_special("특별 할인",0, false),
    freeChampain("증정 이벤트",0, false);

    private final String description;
    private int salePrice;
    private boolean checkFreeChampain;

    SaleEnum(String description, int salePrice, boolean checkFreeChampain) {
        this.description = description;
        this.salePrice = salePrice;
        this.checkFreeChampain = checkFreeChampain;
    }

    public String getDescription() {
        return description;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public Boolean getFreeBool() {
        return checkFreeChampain;
    }

    public void setFreeBool(boolean checkFreeChampain) {
        this.checkFreeChampain = checkFreeChampain;
    }
}
