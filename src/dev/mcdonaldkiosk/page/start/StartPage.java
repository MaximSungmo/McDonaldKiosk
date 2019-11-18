package dev.mcdonaldkiosk.page.start;

import javax.swing.ImageIcon;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.ImageTextPanel;
import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.KioskPageType;
import dev.mcdonaldkiosk.page.PageData;
import dev.mcdonaldkiosk.util.Display;

/**
 * Class Role : 주문이 처음 시작될 때 보여지는 View 컴포넌트 이다.
 * <p>
 * 주문시작 페이지(StartPage)와 주문 종료 페이지(EndPage)의 공통 컴포넌트인 ImageTextPanel이 사용한다.
 * <p>
 * KioskPage자체가 JPanel임.
 */
public class StartPage extends KioskPage {

    /**
     * 생성자를 통해 Super 인 KioskPage에 다음 페이지에 대한 객체를 생성함. 동일한 기능을 하는 메소드는 부모의 기능으로 처리
     */
    public StartPage() {
        // 다음 페이지에 대한 정의. Builder 패턴을 사용, 추가 설명은 PageData 클래스 참고.
        super(new PageData.Builder()
                .nextPageType(KioskPageType.EAT_PLACE_PAGE)
                .build());
        // 페이지에 텍스트를 붙임
        addImageTextPanel();
        // 다음 페이지를 설정
        setNextPage();
    }

    /**
     * 만들어진 ImageTextPanel을 현 페이지(JPanel)에 붙임.
     */
    private void addImageTextPanel() {
        this.add(createImageTextPanel());
    }

    /**
     * 이미지, 텍스트 패널 생성
     * 이미지아이콘과 텍스트를 지정하여 ImageTextPanel을 생성
     *
     * @return
     */
    private ImageTextPanel createImageTextPanel() {
        final ImageTextPanel imgTextPanel = new ImageTextPanel(new ImageIcon("image/bg_info2.jpg"),
                "주문하시려면 화면을 터치하세요");
        imgTextPanel.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT);
        imgTextPanel.setLocation(0, 0);

        return imgTextPanel;
    }

    /**
     * 람다식 활용함.
     * 클릭 시 동작, KioskPage의 nextPage를 mainFrame에 붙임으로써
     */
    private void setNextPage() {
        this.setOnClickListener(() -> StartPage.this.loadNextPage());
    }
}