package christmas;

public class OutputView {
    public static void printMenu(String[][] orderedMenu) {
        System.out.println("<주문 메뉴>");
        for(int i = 0; i < orderedMenu.length; i++) {
            System.out.println(orderedMenu[i][0] + " " + orderedMenu[i][1] + "개");
        }
    }

    public static void printBeforeSale(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(totalPrice + "원");
    }

    public static void printFreeChampain() {
        System.out.println("<증정 메뉴>");
        if(SaleEnum.valueOf("freeChampain").getFreeBool()) { System.out.println("샴페인 1개");  return; }
        System.out.println("없음");
    }

    public static void printSaleProduct() {
        System.out.println("<혜택 내역>");
        boolean checkSalePrice = false;
        for(SaleEnum sale : SaleEnum.values()) {
            if(sale.getSalePrice() != 0) {
                System.out.println(sale.getDescription() + ": -" + sale.getSalePrice() + "원");
                checkSalePrice = true;
            }
        }
        if(!checkSalePrice) {
            System.out.println("없음");
        }
    }

    public static void printSaleAndPay(int totalSale, int totalPrice) {
        System.out.println("<총혜택 금액>");
        System.out.println("-" + totalSale + "원");
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println((totalPrice - totalSale) + "원");
    }

    public static void printBadge(BadgeEnum badgeEnum) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeEnum.getBadgeDescription());
    }
}
