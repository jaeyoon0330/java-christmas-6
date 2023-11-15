package christmas;

import camp.nextstep.edu.missionutils.Console;

public class EventPlanner {

    private static int visitDate;
    private static String[][] orderedMenu;
    private static int totalPrice;
    private static int totalSale;
    private static BadgeEnum mBadge;

    public static void main(int vDate, String[][] orderedStr) {

        System.out.println("12월 "+ vDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리보기!");
        visitDate = vDate;
        orderedMenu = orderedStr;
        menuOrder();
        Output();
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
         OutputView.printMenu(orderedMenu);
         OutputView.printBeforeSale(totalPrice);
         OutputView.printFreeChampain();
         OutputView.printSaleProduct();
         OutputView.printSaleAndPay(totalSale,totalPrice);
         OutputView.printBadge(mBadge);
    }

}
