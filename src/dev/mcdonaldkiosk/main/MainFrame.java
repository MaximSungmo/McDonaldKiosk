package dev.mcdonaldkiosk.main;

import javax.swing.JFrame;

import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.start.StartPage;
import dev.mcdonaldkiosk.util.Display; // Util에서 Display에 대한 Constant 값을 가져온다.

/**
 * Class Role : 프로그램이 구동되면 보여지는 프레임이다.
 */
public class MainFrame extends JFrame {

    /**
     * 생성자를 통한 JFrame 기본 설정 정의
     */
    MainFrame() {
        init(); // 기본 설정에 관한 init 파일 정의 및 실행
        setLocationByCenter(); // 윈도우에서의 위치를 센터에 배치
        addPage(new StartPage()); // 첫 페이지를 삽입
    }

    /**
     * 첫 시작 시 프레임에 대한 사이즈, 프레임 속성을 정의
     */
    private void init() {
        this.setLayout(null);
        this.setTitle("ORDER HERE!");
        this.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 센터에 배치하기 위한 Display에 대한 계산하기
     */
    private void setLocationByCenter() {
        this.setLocation(Display.WINDOWS_HALF_WIDTH / 2, 0);
    }

    /**
     * 페이지를 동적으로 추가할 시 사용되는 메소드를 Frame에 선언
     * ContentPane에서의 변경
     *
     * @param page
     */
    public void attachPage(KioskPage page) {
        removeAllComponents();
        addPage(page);
        refresh();
    }

    /**
     * 메소드 실행 후 페이지에 붙은 모든 컴포넌트 제거하기
     * 삭제하는 위치인 ContentPane에서의 removeAll 진행
     */
    private void removeAllComponents() {
        this.getContentPane().removeAll();
    }

    /**
     * 페이지 추가에 대한 메소드 정의 type은 kioskPage만 받을 수 있도록 함.
     * 붙이는 위치는 ContentPane
     *
     * @param page
     */
    private void addPage(KioskPage page) {
        page.setMainFrame(this);
        this.getContentPane().add(page);
    }

    /**
     * 페이지 추가에 컴포넌트 제거 후 대하여 컴포넌트의 변화가 있을 시 GUI 내용 갱신 및 다시 그리기 작업.
     */
    private void refresh() {
        this.revalidate();  // JComponent의 메소드 revalidate()
        this.repaint(); // JComponent의 repaint()
    }
}