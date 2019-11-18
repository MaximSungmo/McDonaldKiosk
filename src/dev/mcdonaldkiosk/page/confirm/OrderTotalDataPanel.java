package dev.mcdonaldkiosk.page.confirm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.menu.Menu;
import dev.mcdonaldkiosk.page.menu.order.KioskOrderData;

/**
 * 주문 확인을 위한 최종 확인 패널
 */
class OrderTotalDataPanel extends JPanel {

    private final KioskOrderData kioskOrderData;

    /**
     * KioskOrderData가 static으로 모든 정보를 입력받은 뒤에는 최종 확인 패널에 데이터로 활용
     *
     * @param kioskOrderData
     */
    OrderTotalDataPanel(KioskOrderData kioskOrderData) {
        this.kioskOrderData = kioskOrderData;

        initPanel();
    }

    /**
     * 패널 초기화
     */
    private void initPanel() {
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(new JList<Menu>(kioskOrderData.getOrderMenuArray())),
                BorderLayout.CENTER);
        this.add(createTotalListPanel(), BorderLayout.SOUTH);
    }

    /**
     * 최종 확인을 위한 오더정보 뿌리기
     *
     * @return
     */
    private JPanel createTotalListPanel() {
        JPanel totalListPanel = new JPanel();
        totalListPanel.setLayout(new GridLayout(0, 4));
        totalListPanel.add(new JLabel((LangCheck.isKorean() ? "나의주문 : " : "Eating place : ") + kioskOrderData.getEatPlace(), JLabel.CENTER));
        totalListPanel.add(new JLabel((LangCheck.isKorean() ? "주문수량 : " : "Order quantity : ") + kioskOrderData.getOrderQuantity(), JLabel.CENTER));
        totalListPanel.add(new JLabel((LangCheck.isKorean() ? "주문금액 : " : "Order amount : ") + kioskOrderData.getOrderAmount(), JLabel.CENTER));
        totalListPanel.add(new JLabel((LangCheck.isKorean() ? "총 칼로리 : " : "Total calories : ") + kioskOrderData.getOrderKCal(), JLabel.CENTER));

        return totalListPanel;
    }
}
