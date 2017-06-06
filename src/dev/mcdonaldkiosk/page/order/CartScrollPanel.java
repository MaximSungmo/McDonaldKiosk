package dev.mcdonaldkiosk.page.order;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import dev.mcdonaldkiosk.lang.LangCheck;
import dev.mcdonaldkiosk.main.MainFrame;

/**
 * 장바구니 패널의 선택메뉴 리스트 스크롤
 */
class CartScrollPanel extends JScrollPane {

	public static final JList<OrderData> J_LIST = new JList<>();
	private final OrderDataList ORDER_DATA_LIST;

	CartScrollPanel(OrderDataList orderDataList) {
		ORDER_DATA_LIST = orderDataList;

		initOrderScrollPanel();
		initJList();

		scrollDown();
	}

	private void initOrderScrollPanel() {
		this.setViewportView(J_LIST);
		this.setBorder(BorderFactory.createTitledBorder(LangCheck.isKorean() ? "선택메뉴" : "Select Menu")); // 경계설정
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로바정책
	}

	private void initJList() {
		J_LIST.setListData(ORDER_DATA_LIST.getVector());
		J_LIST.setSize(MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT / 10 + MainFrame.FRAME_HEIGHT / 40);
		J_LIST.setLocation(0, MainFrame.FRAME_HEIGHT / 40);
	}

	void setJListOrderList() {
		J_LIST.setListData(ORDER_DATA_LIST.getVector());
	}

	// 스크롤을 최하단으로 내린다.
	void scrollDown() {
		getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
	}
}
