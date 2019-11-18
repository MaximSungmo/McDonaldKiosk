package dev.mcdonaldkiosk.page.payment.place;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.CommonGuidePanel;
import dev.mcdonaldkiosk.page.ImageTextButton;
import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.KioskPageType;
import dev.mcdonaldkiosk.page.PageData;
import dev.mcdonaldkiosk.util.Display;

/**
 * 결제장소 선택 페이지
 */
public class PaymentPlacePage extends KioskPage {

    /**
     * CommonGuidePanel을 부착한다.
     */
    private final CommonGuidePanel PAYMENT_SELECT_PANEL = new CommonGuidePanel(
            LangCheck.isKorean() ? "어디에서 지불하시겠습니까?" : "WHERE DO YOU WANT TO PAY?", 0, 2);

    private final ImageTextButton COUNTER_BTN = new ImageTextButton();
    private final ImageTextButton KIOSK_BTN = new ImageTextButton();

    private ActionListener placeListener = null;

    /**
     * 생성자를 통해 이전, 다음 페이지를 빌드.
     */
    public PaymentPlacePage() {
        super(new PageData.Builder()
                .nextPageType(KioskPageType.MENU_PAGE)
                .previousPageType(KioskPageType.EAT_PLACE_PAGE)
                .build());

        initPage();
        initPaymentSelectPanel();
        initListeners();
        setListeners();
    }

    /**
     * 현재 페이지의 기본 설정
     */
    private void initPage() {
        this.setBackgroundImg("image/bg_green.png");
        this.showBackBtn();
    }

    /**
     * 페이먼트를 선택하는 곳의 버튼 2개를 부착.
     */
    private void initPaymentSelectPanel() {
        initKioskButton();
        PAYMENT_SELECT_PANEL.addItem(COUNTER_BTN, KIOSK_BTN);

        this.add(PAYMENT_SELECT_PANEL);
    }

    /**
     * 페이먼트 선택하는 곳의 2개의 버튼 설정
     */
    private void initKioskButton() {
        final int BUTTON_WIDTH = Display.WINDOWS_HALF_WIDTH * 4 / 15;
        final int BUTTON_HEIGHT = Display.WINDOWS_AVALIABLE_HEIGHT * 1 / 5;

        COUNTER_BTN.setText(LangCheck.isKorean() ? "카운터에서 결제" : "PAYMENT AT THE COUNTER");
        COUNTER_BTN.setResizedImg(new ImageIcon("image/counter.jpg"), BUTTON_WIDTH, BUTTON_HEIGHT);

        KIOSK_BTN.setText(
                "<html><center>" + (LangCheck.isKorean() ? "바로 결제<br>(카드 가능)"
                        : "DIRECT PAYMENT IN KIOSK<br>(CARD)")
                        + "</center></html>");
        KIOSK_BTN.setResizedImg(new ImageIcon("image/kiosk.jpg"), BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * 버튼의 이벤트에 따라 해당 버튼의 소스를 가져옴.
     * 해당 버튼이 어떤 변수주소를 가지는 지에 따라 이벤트 리스너를 다르게 부착.
     */
    private void initListeners() {
        this.placeListener = (eventSource) -> {
            Object source = eventSource.getSource();

            // KioskOrderData의 전역 페이지(PaymentPlace) 변수를 셋팅
            if (source.equals(COUNTER_BTN)) {
                KioskPage.getKioskOrderData().setPaymentPlace(PaymentPlace.COUNTER);
            } else if (source.equals(KIOSK_BTN)) {
                KioskPage.getKioskOrderData().setPaymentPlace(PaymentPlace.KIOSK);
            }

            this.loadNextPage();
        };
    }

    /**
     * 페이먼트 장소에 따른 동일한 이벤트 리스너 부착.( 값 셋팅하기 위한 분기처리)
     */
    private void setListeners() {
        COUNTER_BTN.addActionListener(placeListener);

        KIOSK_BTN.addActionListener(placeListener);
    }
}
