package dev.mcdonaldkiosk.page;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dev.mcdonaldkiosk.main.MainFrame;
import dev.mcdonaldkiosk.page.menu.order.KioskOrderData;
import dev.mcdonaldkiosk.util.Display;
import dev.mcdonaldkiosk.util.KioskAudioPlayer;

/**
 * Class Role : MainFrame 구성에 사용되는 View 컴포넌트 이다.
 * <p>
 * View 컴포넌트 특징
 * 1. 로딩시 사운드가 실행
 * 2. 배경이미지 설정
 * 3. 이전버튼(BackButton) 제공
 * 4. NextPage 페이지 설정 제공
 */
public abstract class KioskPage extends JPanel {

    protected interface OnClickListener {
        public void onClick();
    }

    private OnClickListener onClickListener = null;

    private static final KioskOrderData kioskOrderData = new KioskOrderData();
    private PageData pageData;

    private MainFrame mainFrame;
    private final BackButton backBtn = new BackButton();
    private String bgPath;

    /**
     * 기본 생성자
     */
    KioskPage() {
    }

    /**
     * 생성자
     *
     * @param pageData
     */
    public KioskPage(PageData pageData) {
        this.pageData = pageData;

        initKioskPage();
//        playKioskVoice(pageData.getAudioPath());
        setBackBtnListener();
        setMouseListener();
    }

    /**
     * 페이지 구성 시 사이즈, 위치, 레이아웃 지정 기본 값
     */
    private void initKioskPage() {
        this.setLayout(null);
        this.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT);
        this.setLocation(0, 0);
    }

    private void setBackBtnListener() {
        backBtn.addActionListener(e -> loadPreviousPage());
    }

    /**
     * 클릭 시 동작할 리스너를 셋팅함.
     *
     * @param listener
     */
    protected void setOnClickListener(final OnClickListener listener) {
        onClickListener = listener;
    }

    private void setMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (onClickListener != null) {
                    onClickListener.onClick();
                }
            }
        });
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    protected BackButton getBackButton() {
        return backBtn;
    }

    // 배경이미지 설정
    protected void setBackgroundImg(final String bgPath) {
        if (bgPath != null) {
            this.bgPath = bgPath;
        }
    }

    protected void showBackBtn() {
        setBackBtnZOrderByTop();
        this.add(backBtn);
    }

    /* BackButton Z-Order를 상위로 올린다.  */
    private void setBackBtnZOrderByTop() {
        this.setComponentZOrder(backBtn, 0);
    }

    private boolean isBgImgEmpty() {
        return bgPath != null;
    }

    /* 배경이미지 설정 */
    @Override
    protected void paintComponent(final Graphics g) {
        if (isBgImgEmpty()) {
            try {
                BufferedImage bufferImg = ImageIO.read(new File(bgPath));
                super.paintComponent(g);
                g.drawImage(bufferImg, 0, 0, Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 자식의 생성자로 셋팅된 pageData의 다음 페이지 타입을 가져온 후 MainFrame(JFrame)에 붙임.
     */
    protected void loadNextPage() {
        KioskPageType pageType = pageData.getNextPageType();
        if (pageType != KioskPageType.EMPTY_PAGE) {
            mainFrame.attachPage(pageType.createKioskPage());
        }
    }

    protected void loadPreviousPage() {
        KioskPageType pageType = pageData.getPreviousPageType();
        if (pageType != KioskPageType.EMPTY_PAGE) {
            mainFrame.attachPage(pageType.createKioskPage());
        }
    }

    protected void reloadPage() {
        try {
            mainFrame.attachPage(this.getClass().newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 키오스크 주문 데이터의 정보를 가져옴
     *
     * @return
     */
    protected static KioskOrderData getKioskOrderData() {
        return kioskOrderData;
    }
}