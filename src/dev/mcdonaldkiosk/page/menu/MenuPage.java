package dev.mcdonaldkiosk.page.menu;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.KioskPageType;
import dev.mcdonaldkiosk.page.PageData;
import dev.mcdonaldkiosk.page.menu.order.SelectedOrderConfirmPanel;
import dev.mcdonaldkiosk.util.Display;
import dev.mcdonaldkiosk.util.ImageEdit;

/**
 * 메뉴를 선택할 수 있는 메뉴페이지의 구성을 가지고 있다.
 * 기본 레이아웃은 BorderLayout 이며
 * 상단 이미지 베너 라벨, 중간 메뉴 탭(MenuTabbedPane) 페인, 하단 선택된 주문확인 패널(SelectedOrderConfirmPanel)로 구성되어 있다.
 */
public class MenuPage extends KioskPage {

    private final JPanel menuPagePanel = new JPanel();

    private final ImageIcon bannerImageIcon = new ImageIcon("image/banner_top.jpg");
    private final MenuTabbedPane menuTabbedPane;
    private final SelectedOrderConfirmPanel selectedOrderConfirmPanel;

    /**
     * 메뉴 페이지에 대한 생성자, 이전, 다음 페이지 빌드, 메뉴Tab, 선택메뉴 패널 추가.
     */
    public MenuPage() {
        super(new PageData.Builder()
                .nextPageType(KioskPageType.CONFIRM_PAGE)
                .previousPageType(KioskPageType.PAYMENT_PLACE_PAGE)
                .build());

        menuTabbedPane = new MenuTabbedPane(this, KioskPage.getKioskOrderData());
        selectedOrderConfirmPanel = new SelectedOrderConfirmPanel(this, KioskPage.getKioskOrderData());

        initPage();
        initManuPagePanel();
        setLayout();
        reflesh();
    }

    /**
     * 현 페이지 기본 값 설정
     */
    private void initPage() {
        this.showBackBtn();
        this.add(menuPagePanel);
    }

    /**
     * 현 페이지의 메뉴페이지패널 기본 값 설정
     */
    private void initManuPagePanel() {
        menuPagePanel.setLayout(new BorderLayout());
        menuPagePanel.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT);
        menuPagePanel.setLocation(0, 0);

        menuPagePanel.add(createImageLabel(bannerImageIcon), BorderLayout.NORTH); // 상단 배너
        menuPagePanel.add(menuTabbedPane, BorderLayout.CENTER); // 메뉴에 관한 테이블
        menuPagePanel.add(selectedOrderConfirmPanel, BorderLayout.SOUTH); // 주문 클릭된 아이템 하단의 선택메뉴

    }

    /**
     * 상단 배너에 JLabel로 부착
     * @param imageIcon
     * @return
     */
    private JLabel createImageLabel(final ImageIcon imageIcon) {
        return new JLabel(
                ImageEdit.getResizeIcon(imageIcon, Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT
                        / 5));
    }

    /**
     * 뒤로가기 버튼을 누르면 KioskOrderData의 메뉴만 초기화.
     * 만일 모두 초기화한다면 식사장소와 결제장소를 앞서 선택한 것이 모두 사라질 수 있기 때문.
     */
    private void setLayout() {
        this.getBackButton().addActionListener((e) -> KioskPage.getKioskOrderData().clearMenu());
    }

    /**
     *
     */
    public void reflesh() {
        selectedOrderConfirmPanel.reflesh();
    }

    public void nextPage() {
        this.loadNextPage();
    }
}
