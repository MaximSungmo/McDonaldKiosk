package dev.mcdonaldkiosk.page.confirm;

import java.awt.Color;
import java.awt.Component;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.CommonGuidePanel;
import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.KioskPageType;
import dev.mcdonaldkiosk.page.PageData;
import dev.mcdonaldkiosk.page.payment.place.PaymentPlace;
import dev.mcdonaldkiosk.util.Display;

/**
 * 주문데이터(KioskOrderData)의 확인 체크를 제공하는 페이지 이다.
 * 디자인이 반영된 ConfirmButton을 사용한다.
 */
public class ConfirmPage extends KioskPage {

    // 가운데 패널
    private final CommonGuidePanel ORDER_CONFIRM_GUIDE_PANEL = new CommonGuidePanel(
            LangCheck.isKorean() ? "주문을 확인해주세요" : "IS THIS ORDER CORRECT?", 0, 1);

    private OrderTotalDataPanel orderTotalDataPanel;

    // 하단 패널
    private final CommonGuidePanel YES_NO_SELECT_PANEL = new CommonGuidePanel(0, 2);
    private final ConfirmButton NO_BUTTON = new ConfirmButton(LangCheck.isKorean() ? "취소" : "NO");
    private final ConfirmButton YES_BUTTON = new ConfirmButton(LangCheck.isKorean() ? "확인" : "YES");

    /**
     * 페이먼트 장소에 따라 다음 페이지를 카드 삽입으로 할 지 완료시킬 지 분기
     */
    public ConfirmPage() {
        super(new PageData.Builder()
                .nextPageType(KioskPage.getKioskOrderData().getPaymentPlace().equals(PaymentPlace.COUNTER)
                        ? KioskPageType.END_PAGE : KioskPageType.PAYMENT_CARD_PAGE)
                .previousPageType(KioskPageType.MENU_PAGE)
                .build());
        orderTotalDataPanel = new OrderTotalDataPanel(KioskPage.getKioskOrderData());

        initPage();
        initOrderTotalListPanel();
        initYesNoSelectPanel();
        setListener();
    }

    /**
     * 현 페이지 기본 설정
     */
    private void initPage() {
        this.setBackgroundImg("image/bg_green.png");

        this.add(ORDER_CONFIRM_GUIDE_PANEL);
        this.add(YES_NO_SELECT_PANEL);
    }

    /**
     * 최종 오더 데이터를 추가한다.
     */
    private void initOrderTotalListPanel() {
        ORDER_CONFIRM_GUIDE_PANEL.setTitleColor(Color.GREEN);
        ORDER_CONFIRM_GUIDE_PANEL.addItem(orderTotalDataPanel);
    }

    /**
     * 확인 취소에 대한 버튼 위치지정 기본 설정
     */
    private void initYesNoSelectPanel() {
        YES_NO_SELECT_PANEL.addItem(NO_BUTTON, YES_BUTTON);

        final int PANEL_WIDTH = Display.WINDOWS_HALF_WIDTH * 2 / 5;
        final int PANEL_HEIGHT = Display.WINDOWS_AVALIABLE_HEIGHT * 2 / 25;
        Component yesNoSelectComp = YES_NO_SELECT_PANEL;
        yesNoSelectComp.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        yesNoSelectComp
                .setLocation((Display.WINDOWS_HALF_WIDTH - PANEL_WIDTH) / 2, Display.WINDOWS_AVALIABLE_HEIGHT
                        * 3 / 4);
    }


    /**
     * 각 버튼에 대한 이벤트 리스너 삽입 취소 시 단순히 뒤로
     */
    private void setListener() {
        this.NO_BUTTON.addActionListener((e) -> this.loadPreviousPage()); // 한줄짜리는 { } 지울수 있음.

        this.YES_BUTTON.addActionListener((e) -> this.loadNextPage());
    }
}
