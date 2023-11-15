package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static int inputVisitDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)");
        try {
            int visitDate = Integer.parseInt(Console.readLine());
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

}
