package dev.mcdonaldkiosk.util;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * Class Role : 디스플레이 화면 크기의 정보를 제공한다.
 *
 */
public class Display {

  private static final int SCREEN_HEIGHT;
  private static final int WINDOWS_TITLEBAR_HEIGHT;

  public static final int WINDOWS_WIDTH, WINDOWS_HEIGHT;
  public static final int WINDOWS_HALF_WIDTH;
  public static final int WINDOWS_AVALIABLE_HEIGHT;

  static {
    Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize(); // 스크린 사이즈 가져오기
    SCREEN_HEIGHT = (int) dimen.getHeight(); // 높이 측정

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle rec = ge.getMaximumWindowBounds();  // 윈도우 바운드 가져오기
    WINDOWS_WIDTH = (int) rec.getWidth();  // 윈도우 가로 측정
    WINDOWS_HEIGHT = (int) rec.getHeight();  // 윈도우 세로 측정
    
    WINDOWS_HALF_WIDTH = WINDOWS_WIDTH / 2;
    WINDOWS_TITLEBAR_HEIGHT = SCREEN_HEIGHT - WINDOWS_HEIGHT;
    WINDOWS_AVALIABLE_HEIGHT = WINDOWS_HEIGHT - WINDOWS_TITLEBAR_HEIGHT;
  }

  private Display() {
    throw new AssertionError();
  }
}
