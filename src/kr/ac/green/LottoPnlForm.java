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
	private JCheckBox[] cb;

	private JRadioButton rbtnAuto;
	private JRadioButton rbtnManual;
	
	private LottoGameInfo lottoGameInfo;
	
	// 수동번호 / 자동번호 결과
	private List<Integer> col = new ArrayList<Integer>();

	public LottoPnlForm(int no) {
		this.lottoGameInfo = new LottoGameInfo(no);
		init();
		addListeners();
	}

	public LottoGameInfo getGameInfo() {
		lottoGameInfo.setNumbers(col);
		return lottoGameInfo;
	}
	
	// 수동자동 번호 결과 게터
//	public List<Integer> getCol() {
//		return col;
//	}

	private void init() {
		cb = new JCheckBox[45];

		// 반복문에 넣고 체크박스 계속만들어요
		for (int i = 0; i < cb.length; i++) {
			int n = i + 1;
			String text = String.valueOf(n);
			cb[i] = new JCheckBox(text);
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
		for (JCheckBox cbs : cb) {
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
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "Check you Number");
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		this.setBorder(tBorder);
	}

	int numcount = 0;

	public void addListeners() {
		rbtnAuto.addActionListener(this);
		rbtnManual.addActionListener(this);

		ItemListener listener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent ie) {

				Object src = ie.getSource();
				int result = ie.getStateChange();

				for (int i = 0; i < cb.length; i++) {
					if (cb[i] == src) {
						if (result == ItemEvent.SELECTED) {
							// System.out.println((i + 1) + "선택됨");
							col.add(i + 1);

						} else if (result == ItemEvent.DESELECTED) {
							// System.out.println((i + 1) + "해제됨");
							col.remove(Integer.valueOf(i+1));
						}
						Collections.sort(col);
						tfMyNum.setText("" + col);
					}
				}
				// JCheckBox cb = (JCheckBox)ie.getSource();

				if (ie.getStateChange() == ItemEvent.SELECTED) {
					numcount++; // 한번 체크될때마다 1증가
				} else {
					numcount--; // 체크 해제될때마다 1감소
				}
				if (numcount > 6) { // 6개 이상 선택하려고 할 경우
					JOptionPane.showMessageDialog(null, "6개 이상 선택할 수 없습니다"); // 경고창
				}

			}
		};
		// 모든 JCheckBox에 addItemListener 추가
		for (JCheckBox cbs : cb) {
			cbs.addItemListener(listener);
		}
	}

	// 초기화 하는 메소드, 파라미터로 리셋 버튼일때와
	// 자동 수동 버튼일 때 구분해줌
	public void clear(boolean isReset) {
		col.removeAll(col);
		tfMyNum.setText("");
		for (int i = 0; i < 45; i++) {
			cb[i].setSelected(false);
		}
		for (int i = 0; i < 45; i++) {
			cb[i].setEnabled(true);
		}
		if (isReset) {
			rbtnManual.setSelected(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == rbtnAuto) {
			clear(false);
			col.removeAll(col);
			for (int i = 0; i < 45; i++) {
				cb[i].setEnabled(false);
			}
			tfMyNum.setText("");
			Integer[] randArr = new MyNums().getNums();

			for (int i = 0; i < randArr.length; i++) {
				col.add(randArr[i]);
			}
			System.out.println(col);
			lottoGameInfo.setAuto(true);
		}
		if (src == rbtnManual) {
			clear(false);
			for (int i = 0; i < 45; i++) {
				cb[i].setEnabled(true);
			}
			lottoGameInfo.setAuto(false);
		}
	}
}
