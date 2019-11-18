package dev.mcdonaldkiosk.page.menu.order;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.menu.MenuPage;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 주문 데이터 확인을 위한 패널
 */
class OrderDataConfirmPanel extends JPanel {

    private final JLabel ORDER_TEXT_LABEL = new JLabel();
    private final JLabel ORDER_DATA_LABEL = new JLabel();
    private final JButton CANCLE_BTN = new JButton();
    private final JButton PAYMENT_BTN = new JButton();

    private final Color BG_COLOR = Color.WHITE;
    private final Color CANCLE_BTN_COLOR = Color.GRAY;
    private final Color PAYMENT_BTN_COLR = Color.ORANGE;

    private MenuPage menuPage;
    private final KioskOrderData kioskOrderData;

    /**
     * 메뉴 페이지 위에 주문 확인 패널
     *
     * @param menuPage
     * @param kioskOrderData
     */
    OrderDataConfirmPanel(MenuPage menuPage, KioskOrderData kioskOrderData) {
        this.menuPage = menuPage;
        this.kioskOrderData = kioskOrderData;

        initOrderConfirmPanel();

        initTextLabel();
        initDataLabel();
        initButton();

        setListener();
    }

    /**
     * 주문 확인 패널.(주문수량 금액 칼로리, 주문 취소, 진행)
     */
    private void initOrderConfirmPanel() {
        this.setLayout(new GridLayout(0, 4));
        this.setBackground(BG_COLOR);
        this.add(ORDER_TEXT_LABEL);
        this.add(ORDER_DATA_LABEL);
        this.add(CANCLE_BTN);
        this.add(PAYMENT_BTN);
    }

    /**
     * 텍스트 설정
     */
    private void initTextLabel() {
        ORDER_TEXT_LABEL.setText(LangCheck.isKorean()
                ? "<html>주문수량<br>주문금액<br>총 칼로리</html>"
                : "<html>Order quantity<br>Order amount<br>Total calories</html>");
        ORDER_TEXT_LABEL.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * 라벨 설정
     */
    private void initDataLabel() {
        ORDER_DATA_LABEL.setText("<html>" + kioskOrderData.getOrderQuantity() +
                "<br>" + kioskOrderData.getOrderAmount() +
                "<br>" + kioskOrderData.getOrderKCal() + "</html>");
        ORDER_DATA_LABEL.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * 버튼 설정
     */
    private void initButton() {
        CANCLE_BTN.setText(LangCheck.isKorean() ? "주문취소" : "CANCEL ORDER");
        CANCLE_BTN.setBackground(CANCLE_BTN_COLOR);

        PAYMENT_BTN.setText(LangCheck.isKorean() ? "주문진행" : "COMPLETE ORDER");
        PAYMENT_BTN.setBackground(PAYMENT_BTN_COLR);
    }

    /**
     * 취소 버튼, 주문진행버튼 클릭에 따른 이벤트 발생
     */
    private void setListener() {
        CANCLE_BTN.addActionListener((e) -> {
            kioskOrderData.clearMenu();
            menuPage.reflesh();
        });

        PAYMENT_BTN.addActionListener((e) -> {
            if (kioskOrderData.getOrderQuantity() == 0) {
//        KioskAudioPlayer.newInstance(
//            LangCheck.isKorean() ? "sound/order.wav" : "sound/order_eng.wav").play();
            } else {
                menuPage.nextPage();
            }
        });
    }

    /**
     * 주문 수량 금액, 총칼로리 동적 변경
     */
    void refresh() {
        ORDER_DATA_LABEL.setText("<html>" + kioskOrderData.getOrderQuantity() +
                "<br>" + kioskOrderData.getOrderAmount() +
                "<br>" + kioskOrderData.getOrderKCal() + "</html>");
    }
}
