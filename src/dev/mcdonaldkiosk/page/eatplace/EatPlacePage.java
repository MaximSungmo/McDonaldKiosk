package dev.mcdonaldkiosk.page.eatplace;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.lang.Language;
import dev.mcdonaldkiosk.page.CommonGuidePanel;
import dev.mcdonaldkiosk.page.ImageTextButton;
import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.KioskPageType;
import dev.mcdonaldkiosk.page.PageData;
import dev.mcdonaldkiosk.util.Display;

/**
 * 식사장소를 선택할 수 있는 View 컴포넌트.
 * <p>
 * 주문언어 선택을 제공한다.
 * 페이지에 공통으로 가이드 패널(CommonGuidePanel)이 사용된다.
 */
public class EatPlacePage extends KioskPage {

    private enum SelectType {
        EAT_PLACE, LANG;
    }


    /**
     * 생성자, 이전 페이지와 다음 페이지를 빌드
     */
    public EatPlacePage() {
        super(new PageData.Builder()
                .nextPageType(KioskPageType.PAYMENT_PLACE_PAGE)
                .previousPageType(KioskPageType.START_PAGE)
                .build());
        // 현 페이지의 bg 생성
        this.setBackgroundImg("image/bg_green.png");
        // 뒤로가기 버튼 보이기
        this.showBackBtn();

        addEatPlaceGuidePanel();
        addLangGuidePanel();
    }

    /**
     * 가이드패널을 현재 페이지에 붙임.
     * Lang에 따라 영어, 한국어 변경
     */
    private void addEatPlaceGuidePanel() {
        this.add(createGuidePanel(SelectType.EAT_PLACE,
                LangCheck.isKorean() ? "식사하실 장소를 선택해 주세요" : "PLEASE SELECT A PLACE TO EAT"));
    }

    /**
     * Lang 가이드 패널을 붙임
     */
    private void addLangGuidePanel() {
        this.add(createGuidePanel(SelectType.LANG, "PLASE SELECT YOUR LANGUAGE"));
    }

    /**
     * 가이드 패널의 상세 내용을 장소, 언어에 따라 구분하여 add시키며 guidePanel을 별도로 클래스 분리
     *
     * @param type
     * @param text
     * @return
     */
    private CommonGuidePanel createGuidePanel(final SelectType type, final String text) {
        final CommonGuidePanel guidePanel = new CommonGuidePanel(text, 0, 2);
        switch (type) {
            case EAT_PLACE:
                guidePanel.addItem(
                        createPlaceBtn(EatPlace.EAT_IN, LangCheck.isKorean() ? "매장 식사" : "EAT IN", "image/icon_eat.jpg"),
                        createPlaceBtn(EatPlace.TAKE_OUT, LangCheck.isKorean() ? "테이크 아웃(포장)" : "TAKE OUT", "image/icon_take.jpg"));
                break;
            case LANG:
                initLangPanel(guidePanel);
                guidePanel.addItem(
                        createLangBtn(Language.KOREAN, LangCheck.isKorean() ? "한국어" : "KOREAN"),
                        createLangBtn(Language.ENGLISH, "ENGLISH"));
                break;
            default:
        }
        return guidePanel;
    }

    /**
     * 장소 선택에 따라 버튼을 생성하여 마우스이벤트를 작동시키는 버튼 부착.
     *
     * @param place
     * @param text
     * @param imgPath
     * @return
     */
    private ImageTextButton createPlaceBtn(final EatPlace place, final String text, final String imgPath) {
        final ImageTextButton imgTextBtn = new ImageTextButton(text, new ImageIcon(imgPath));
        imgTextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                KioskPage.getKioskOrderData().setEatPlace(place);
                EatPlacePage.this.loadNextPage();
            }
        });

        return imgTextBtn;
    }

    /**
     * 가이드 패널의 위치 및 크기를 설정한다.
     *
     * @param guidePanel
     */
    private void initLangPanel(final CommonGuidePanel guidePanel) {
        final int width = Display.WINDOWS_HALF_WIDTH * 4 / 5;
        guidePanel.setGuidePanelSize(width, Display.WINDOWS_AVALIABLE_HEIGHT * 2 / 15);
        guidePanel.setGuidePanelLocation((Display.WINDOWS_HALF_WIDTH - width) / 2,
                Display.WINDOWS_AVALIABLE_HEIGHT * 3 / 4);
    }

    /**
     * 하단에 붙여질 언어에 종류를 나누며 언어 선택에 따라 lang을 셋팅할 수 있도록 한다.
     * LangCheck은 전역(static)에서 체크를 진행하므로 현재의 셋팅을 통해 어디에서든 동일한 변수에 접근한다.
     *
     * @param lang
     * @param text
     * @return
     */
    private ImageTextButton createLangBtn(final Language lang, final String text) {
        final ImageTextButton imgTextBtn = new ImageTextButton(text);
        imgTextBtn.setForeground(Color.BLACK);
        // 이벤트 추가
        imgTextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                LangCheck.setLang(lang);
                EatPlacePage.this.reloadPage();
            }
        });

        return imgTextBtn;
    }
}