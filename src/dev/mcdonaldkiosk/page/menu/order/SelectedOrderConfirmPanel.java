package dev.mcdonaldkiosk.page.menu.order;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.KioskPageLoader;
import dev.mcdonaldkiosk.page.OrderData;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Class Role : 선택된 주문확인 패널로서 이용중 선택하였던 주문의 정보를 확인할 수 있다.
 * 
 * 상단 텍스트툴바 패널(TextToolbarPanel) 에서는 식사장소, 결제장소 확인이 가능하다.
 * 중간 주문리스트 패널(OrderListPanel) 에서는 선택하였던 메뉴의 리스트 확인이 가능하다.
 * 하단 주문데이터확인 패널(OrderDataConfirmPanel) 에서는 주문수량, 주문금액, 총 칼로리 확인이 가능하다.
 *
 * @author Jaehyeon, Kim
 * @see TextToolbarPanel,OrderListPanel,OrderDataConfirmPanel
 */
public class SelectedOrderConfirmPanel extends JPanel {

  /* 주문 장바구니 리스트 */
  private final TextToolbarPanel textToolbarPanel;
  private final OrderListPanel orderListPanel;
  private final OrderDataConfirmPanel orderDataConfirmPanel;

  private final OrderData orderData;

  public SelectedOrderConfirmPanel(KioskPageLoader kioskPageLoader, OrderData orderData) {
    this.orderData = orderData;

    textToolbarPanel = new TextToolbarPanel();
    orderListPanel = new OrderListPanel();
    orderDataConfirmPanel = new OrderDataConfirmPanel(kioskPageLoader, orderData);

    // 초기화
    initMyOrderPanel();
    addTextToTextToolbar();
  }

  private void initMyOrderPanel() {
    this.setLayout(new BorderLayout());
    this.add(textToolbarPanel.getPanel(), BorderLayout.NORTH);
    this.add(orderListPanel, BorderLayout.CENTER);
    this.add(orderDataConfirmPanel, BorderLayout.SOUTH);
  }

  // 텍스트툴바에 텍스트 추가
  private void addTextToTextToolbar() {
    textToolbarPanel.addText((LangCheck.isKorean() ? "식사 장소 : " : "Eating place : ") + orderData.getEatingPlace())
        .addText((LangCheck.isKorean() ? "결제 장소 : " : "Payment Place : ") + orderData.getPaymentPlace());
  }

  // 새로고침
//  public void reflesh() {
//    orderListPanel.setJListMenu(orderData.getOrderMenuArray());
//    orderDataConfirmPanel.refresh();
//  }
}
