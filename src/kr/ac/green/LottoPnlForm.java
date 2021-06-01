package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class LottoPnlForm extends JPanel implements ActionListener {
	private JTextField tfMyNum;
	private JCheckBox[] cbs;
	private JRadioButton rbtnAuto;
	private JRadioButton rbtnManual;
	private String automanual = "수동";
	private int order;
	private int selectCount = 0;
	private List<Integer> selectNums = new ArrayList<Integer>();

	public LottoPnlForm(int order) {
		this.order = order;
		init();
		addListeners();
	}

	public List<Integer> getSelectNums() {
		return selectNums;
	}

	public String getAutomanual() {
		return automanual;
	}

	public JCheckBox[] getCbs() {
		return cbs;
	}

	public JRadioButton getRbtnAuto() {
		return rbtnAuto;
	}

	private void init() {
		cbs = new JCheckBox[45];
		// 반복문에 넣고 체크박스 계속만들어요
		for (int i = 0; i < cbs.length; i++) {
			int n = i + 1;
			String text = String.valueOf(n);
			cbs[i] = new JCheckBox(text);
		}

		// JRadioButton 만들구요, 그룹하고..
		rbtnAuto = new JRadioButton("자동");
		rbtnManual = new JRadioButton("수동", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnAuto);
		group.add(rbtnManual);

		// 체크박스에서 선택한 번호 보여줄 텍스트필드 만들었구요, 수정불가..
		tfMyNum = new JTextField(30);
		tfMyNum.setEditable(false);

		// 패널을 보더레이아웃으로 북, 중, 남으로 넣었구요
		this.setLayout(new BorderLayout());
		// 북쪽에 그리드레이아웃으로 로또번호 들어갈 패널에 체크박스 추가
		JPanel pnlLottoNum = new JPanel(new GridLayout(0, 7));
		for (JCheckBox cbs : cbs) {
			pnlLottoNum.add(cbs);
		}

		// 남쪽에 자동 수동 설정할 버튼 넣었구요
		JPanel pnlRbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlRbtn.add(rbtnAuto);
		pnlRbtn.add(rbtnManual);

		this.add(pnlLottoNum, BorderLayout.NORTH);
		// 센터에는 그냥 레이블 넣었어요 꽉차게
		this.add(tfMyNum, BorderLayout.CENTER);
		this.add(pnlRbtn, BorderLayout.SOUTH);

		// 타이틀보더 써서 이쁘게 만들었어요
		int title = order + 65;
		char charTitle = (char) title;
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "" + charTitle);
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		this.setBorder(tBorder);
	}

	public void addListeners() {
		rbtnAuto.addActionListener(this);
		rbtnManual.addActionListener(this);

		ItemListener listener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ie) {
				Object src = ie.getSource();
				int result = ie.getStateChange();

				for (int i = 0; i < cbs.length; i++) {
					if (cbs[i] == src) {
						if (result == ItemEvent.SELECTED) {
							// System.out.println((i + 1) + "선택됨");
							selectNums.add(i + 1);

						} else if (result == ItemEvent.DESELECTED) {
							// System.out.println((i + 1) + "해제됨");
							// col.remove(i + 1);
							selectNums.remove(Integer.valueOf(i + 1));
						}
						Collections.sort(selectNums);
						tfMyNum.setText("" + selectNums);
					}
				}
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					selectCount++; // 한번 체크될때마다 1증가
				} else {
					selectCount--; // 체크 해제될때마다 1감소
				}
				if (selectCount > 6) { // 6개 이상 선택하려고 할 경우
					JOptionPane.showMessageDialog(null, "6개 이상 선택할 수 없습니다"); // 경고창
				}

			}
		};
		// 모든 JCheckBox에 addItemListener 추가
		for (JCheckBox cbs : cbs) {
			cbs.addItemListener(listener);
		}
	}

	// 초기화 하는 메소드, 파라미터로 리셋 버튼일때와
	// 자동 수동 버튼일 때 구분해줌
	public void clear(boolean isReset) {
		selectNums.removeAll(selectNums);
		for (int i = 0; i < 45; i++) {
			cbs[i].setSelected(false);
		}
		for (int i = 0; i < 45; i++) {
			cbs[i].setEnabled(true);
		}
		if (isReset) {
			rbtnManual.setSelected(true);
		}
		tfMyNum.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == rbtnAuto) {
			automanual = "자동";
			clear(false);
			for (int i = 0; i < 45; i++) {
				cbs[i].setEnabled(false);
			}
			tfMyNum.setText("");
			Integer[] randArr = new MyNums().getNums();

			for (int i = 0; i < randArr.length; i++) {
				selectNums.add(randArr[i]);
			}
			// System.out.println(col);
		}
		if (src == rbtnManual) {
			clear(false);
			for (int i = 0; i < 45; i++) {
				cbs[i].setEnabled(true);
			}
		}
	}
}
