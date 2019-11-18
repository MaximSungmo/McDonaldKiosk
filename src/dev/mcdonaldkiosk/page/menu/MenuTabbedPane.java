package dev.mcdonaldkiosk.page.menu;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.menu.order.KioskOrderData;
import dev.mcdonaldkiosk.page.menu.table.BurgerTable;
import dev.mcdonaldkiosk.page.menu.table.DessertTable;
import dev.mcdonaldkiosk.page.menu.table.DrinkTable;
import dev.mcdonaldkiosk.page.menu.table.McCafeTable;
import dev.mcdonaldkiosk.page.menu.table.McLunchTable;
import dev.mcdonaldkiosk.page.menu.table.McMoningTable;
import dev.mcdonaldkiosk.page.menu.table.MenuTable;
import dev.mcdonaldkiosk.page.menu.table.SetMenuTable;
import dev.mcdonaldkiosk.page.menu.table.SnackTable;

/**
 * 메뉴테이블들을 Tab형식으로 제공하는 컴포넌트이다.
 */
class MenuTabbedPane extends JTabbedPane {

    private final ArrayList<MenuTable> menuTableList = new ArrayList<>();

    /**
     * 메뉴페이지에 각종 메뉴탭을 부착하는 생성자.
     *
     * @param menuPage
     * @param kioskOrderData
     */
    MenuTabbedPane(MenuPage menuPage, final KioskOrderData kioskOrderData) {
        createMenuTableOfList(menuPage, kioskOrderData);
        initTabbedPane();
        setTabTitle();
    }

    /**
     * 메뉴 페이지에 메뉴탭 부착
     *
     * @param menuPage
     * @param kioskOrderData
     */
    private void createMenuTableOfList(MenuPage menuPage, KioskOrderData kioskOrderData) {
        menuTableList.add(new BurgerTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new SnackTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new SetMenuTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new McCafeTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new McLunchTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new DrinkTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new McMoningTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
        menuTableList.add(new DessertTable(menuPage, kioskOrderData, 0, LangCheck.isKorean() ? 4 : 3));
    }

    /**
     * 상단에 위치한 메뉴 탭 추가. FOR문으로 ADD
     */
    private void initTabbedPane() {
        this.setTabPlacement(JTabbedPane.TOP); // 상단위치

        for (MenuTable menuTable : menuTableList) {
            this.add(menuTable.getComponent());
        }
    }

    /**
     * 메뉴 탭에 해당하는 메뉴탭글자
     */
    private void setTabTitle() {
        int menuTabIndex = 0;
        for (MenuTable menuTable : menuTableList) {
            this.setTitleAt(menuTabIndex++, menuTable.toString());
        }
    }
}
