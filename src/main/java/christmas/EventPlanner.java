package christmas;

import camp.nextstep.edu.missionutils.Console;

public class EventPlanner {

    private static int visitDate;
    private static String[][] orderedMenu;
    private static int totalPrice;
    private static int totalSale;
    private static BadgeEnum mBadge;

    public static void main() {
        visitDate = inputVisitDate();
        orderedMenu = inputMenuSplit();

        System.out.println("12월 "+ visitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리보기!");

        menuOrder();
        Output();
    }

    public static int inputVisitDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)");
        String vDate = Console.readLine();
        try {
            visitDate = Integer.parseInt(vDate);
            return visitDate;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static String[][] inputMenuSplit() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menuStr = Console.readLine();
        String[] menuSplit = menuStr.split("-|,");
        String[][] orderedMenu = new String[menuSplit.length / 2][2];

        for (int i = 0; i < menuSplit.length; i += 2) {
            orderedMenu[i / 2][0] = menuSplit[i];
            orderedMenu[i / 2][1] = menuSplit[i + 1];
        }

        return orderedMenu;
    }

    public static void menuOrder() {
        int menuPrice;
        for(int i = 0; i < orderedMenu.length; i++) {
            for (MenuEnum menu : MenuEnum.values()) {
                if (orderedMenu[i][0].equals(menu.getDescription())) {
                    try { menuPrice = Integer.parseInt(orderedMenu[i][1]);} catch(NumberFormatException e) { throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");}
                    totalPrice += menu.getPrice() * Integer.parseInt(orderedMenu[i][1]);
                }
            }
        }

        if(totalPrice >= 10000) {
            reservDate();
            if(totalPrice >= 120000) {
                SaleEnum.valueOf("freeChampain").setSalePrice(25000);
                SaleEnum.valueOf("freeChampain").setFreeBool(true);
            }
            totalSale = sale_process();
        }
    }

    public static void reservDate() {
        if(visitDate  >= 1 && visitDate <= 25) { SaleEnum.valueOf("sale_chr").setSalePrice(1000 + (visitDate - 1) * 100); }
        int sale_weekend = 0, sale_weekday = 0;
        for(int i = 0; i < orderedMenu.length; i++) {
            for (MenuEnum menu : MenuEnum.values()) {
                if ((visitDate % 7 == (1 | 2)) && (menu.getPrice() == (5000 | 15000))) { sale_weekend += 2023; }
                if ((visitDate % 7 > 2 | visitDate % 7 == 0) && (menu.getPrice() == (55000 | 54000 | 35000 | 25000))) { sale_weekday += 2023; }
            }
        }
        SaleEnum.valueOf("sale_weekday").setSalePrice(sale_weekday);
        SaleEnum.valueOf("sale_weekend").setSalePrice(sale_weekend);
        if(visitDate % 7 == 3 | visitDate == 25) { SaleEnum.valueOf("sale_special").setSalePrice(1000); }
    }

    public static int sale_process() {
        int total_sale = SaleEnum.valueOf("sale_chr").getSalePrice() + SaleEnum.valueOf("sale_weekday").getSalePrice() + SaleEnum.valueOf("sale_weekend").getSalePrice() + SaleEnum.valueOf("sale_special").getSalePrice();
        mBadge = BadgeEnum.NONE;
        if(total_sale >= 20000) {
            mBadge = BadgeEnum.SANTA;
        }
        else if(total_sale >= 10000) {
            mBadge = BadgeEnum.TREE;
        }
        else if(total_sale >= 5000) {
            mBadge = BadgeEnum.STAR;
        }
        return total_sale;
    }

    public static void Output() {
        System.out.println("<주문 메뉴>");
        for(int i = 0; i < orderedMenu.length; i++) {
            System.out.println(orderedMenu[i][0] + " " + orderedMenu[i][1] + "개");
        }
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(totalPrice + "원");
        System.out.println("<증정 메뉴>");
        if(SaleEnum.valueOf("freeChampain").getFreeBool()) { System.out.println("샴페인 1개"); }
        else { System.out.println("없음"); }
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
        System.out.println("<총혜택 금액>");
        System.out.println("-" + totalSale + "원");
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println((totalPrice - totalSale) + "원");
        System.out.println("<12월 이벤트 배지>");
        System.out.println(mBadge.getBadgeDescription());
    }

}
