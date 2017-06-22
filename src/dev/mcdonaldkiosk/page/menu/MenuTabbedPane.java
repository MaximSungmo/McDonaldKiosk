package dev.mcdonaldkiosk.page.menu;

import dev.mcdonaldkiosk.page.OrderData;
import dev.mcdonaldkiosk.page.menu.table.BurgerTable;
import dev.mcdonaldkiosk.page.menu.table.DessertTable;
import dev.mcdonaldkiosk.page.menu.table.DrinkTable;
import dev.mcdonaldkiosk.page.menu.table.McCafeTable;
import dev.mcdonaldkiosk.page.menu.table.McLunchTable;
import dev.mcdonaldkiosk.page.menu.table.McMoningTable;
import dev.mcdonaldkiosk.page.menu.table.MenuTable;
import dev.mcdonaldkiosk.page.menu.table.SetMenuTable;
import dev.mcdonaldkiosk.page.menu.table.SnackTable;
import java.util.ArrayList;
import javax.swing.JTabbedPane;

/**
 * Class Role : 메뉴테이블들을 Tab형식으로 제공하는 컴포넌트이다.
 *
 * @author Jaehyeon, Kim
 */
class MenuTabbedPane extends JTabbedPane {

  private final ArrayList<MenuTable> menuTableList = new ArrayList<>();

  MenuTabbedPane(MenuPage menuPage, final OrderData orderData) {
    createMenuTableOfList(menuPage, orderData);
    initTabbedPane();
    setTabTitle();
  }

  private void createMenuTableOfList(MenuPage menuPage, OrderData orderData) {
    menuTableList.add(new BurgerTable(menuPage, orderData, 0, 4));
    menuTableList.add(new SnackTable(menuPage, orderData, 0, 4));
    menuTableList.add(new SetMenuTable(menuPage, orderData, 0, 4));
    menuTableList.add(new McCafeTable(menuPage, orderData, 0, 4));
    menuTableList.add(new McLunchTable(menuPage, orderData, 0, 4));
    menuTableList.add(new DrinkTable(menuPage, orderData, 0, 4));
    menuTableList.add(new McMoningTable(menuPage, orderData, 0, 4));
    menuTableList.add(new DessertTable(menuPage, orderData, 0, 4));
  }

  private void initTabbedPane() {
    this.setTabPlacement(JTabbedPane.TOP); // 상단위치

    for (MenuTable menuTable : menuTableList) {
      this.add(menuTable.getComponent());
    }
  }

  private void setTabTitle() {
    int menuTabIndex = 0;
    for (MenuTable menuTable : menuTableList) {
      this.setTitleAt(menuTabIndex++, menuTable.toString());
    }
  }
}
