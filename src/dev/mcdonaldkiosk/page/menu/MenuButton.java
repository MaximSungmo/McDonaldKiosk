package dev.mcdonaldkiosk.page.menu;

import dev.mcdonaldkiosk.main.MainFrame;
import dev.mcdonaldkiosk.page.KioskPage;
import dev.mcdonaldkiosk.page.menu.order.MyOrderPanel;
import dev.mcdonaldkiosk.util.ImageEdit;
import dev.mcdonaldkiosk.util.KioskAudioPlayer;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Create by kimjaehyeon 
 * Class Role : 메뉴들은 각각의 버튼으로 구성되어있다. 
 * 해당 메뉴버튼을 담당하는 클래스이다.
 */
public class MenuButton extends JButton {

	private final String PRICE_FONT_COLOR = "red";
	private final String IMG_PATH;
	private final Menu MENU;

	private final KioskAudioPlayer clickBGMPlayer = KioskAudioPlayer.createKioskAudioPlayer("sound/beep.wav");
	private ImageEdit imageEdit = new ImageEdit();

	MenuButton(String imgPath, Menu menu) {
		this.IMG_PATH = imgPath;
		this.MENU = menu;

		initMenuButton();
		setListener();
	}

	private void initMenuButton() {
		this.setIcon(imageEdit.getResizeIcon(IMG_PATH, MainFrame.FRAME_WIDTH / 5, MainFrame.FRAME_HEIGHT / 10));
		this.setText(MENU.toMenuButtonText(PRICE_FONT_COLOR));
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setVerticalTextPosition(SwingConstants.BOTTOM);

		this.setBackground(Color.WHITE);
		this.setBorderPainted(false);
	}

	private void setListener() {
		this.addActionListener((e) -> {
			/* 음원에 관련된건 음원관련 객체에게 맡긴다. */
			clickBGMPlayer.play();

			// TODO : 주문데이터 넣기
//			MyOrderPanel.addOrder(MENU);
		});
	}
}
