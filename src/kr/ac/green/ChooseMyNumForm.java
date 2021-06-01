package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class ChooseMyNumForm extends JFrame implements ActionListener {
	private JTextArea taInfo;
	private JButton btnStart;
	private JButton btnCancel;
	private JButton btnReset;
	private JButton btnAuto;
	// LottoPnlForm이 각각의 객체니까 배열로 각각 저장해야한다
	private LottoPnlForm[] lottoPnls;
	private Collection<Integer> colTemp = new ArrayList<Integer>();
	private ArrayList<Collection<Integer>> listChooseNums = new ArrayList<>();
	private Vector<String> automanualList = new Vector<String>(); // 자동수동인지
	private int count;

	public ChooseMyNumForm(int count) {
		this.count = count;
		init();
		setDiplay();
		addListeners();
		showFrame();
	}

	public ArrayList<Collection<Integer>> getListChooseNums() {
		return listChooseNums;
	}

	public Vector<String> getAutomanualList() {
		return automanualList;
	}

	private void init() {

		taInfo = new JTextArea();
		taInfo.setEditable(false);
		taInfo.setText("1.  자동인지 수동인지 선택하세요.\n\n" + "2.  자동 일 경우 번호를 선택하지 않아도 됩니다.\n\n"
				+ "3.  수동 일 경우 번호를 6개 모두 선택 해야 합니다.\n\n" + "4.  번호 선택 완료 후  시작 버튼을 누르면 당첨 여부가 나옵니다.\n\n"
				+ "5.  리셋 버튼을 누를 시 모든 정보가 초기화됩니다.");

		btnStart = new JButton("시작");
		btnCancel = new JButton("취소");
		btnReset = new JButton("리셋");
		btnAuto = new JButton("모두자동");
	}

	private void setDiplay() {
		// 패널서쪽, 설명란
		JPanel pnlWest = new JPanel();

		pnlWest.add(taInfo);
		pnlWest.setBackground(Color.WHITE);
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "# PlayInfo");
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		pnlWest.setBorder(tBorder);

		// 패널센터, 로또 번호와 자동여부 선택이 있는 패널(LottoPnlForm)을 추가
		JPanel pnlCenter = new JPanel(new GridLayout(1, 0));
		// 입력 받은 값만큼 MakePanel 배열을 생성
		lottoPnls = new LottoPnlForm[count];
		for (int i = 0; i < count; i++) {
			// 패널을 생성하고
			LottoPnlForm pnl = new LottoPnlForm(i);
			// 화면에 배치
			pnlCenter.add(pnl);
			// 참조배열에 생성한 패널 각각 저장
			lottoPnls[i] = pnl;
			// test = makePanels[i].col;
		}

		// 패널남쪽, 버튼 3가지
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// 패널버튼, 버튼 3가지 한 레이블에 담았어요
		JPanel pnlbtn = new JPanel();
		pnlbtn.add(btnStart);
		pnlbtn.add(btnAuto);
		pnlbtn.add(btnReset);
		pnlbtn.add(btnCancel);

		pnlSouth.add(pnlbtn);

		// 테두리 공백 필요 할 수도 있을거 같아서 메인에 달았어요
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlWest, BorderLayout.WEST);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));

		add(pnlMain, BorderLayout.CENTER);
	}

	private void addListeners() {
		btnReset.addActionListener(this);
		btnCancel.addActionListener(this);
		btnStart.addActionListener(this);
		btnAuto.addActionListener(this);
	}

	private void showFrame() {
		setTitle("Lotto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int isSix = 0;
		Object src = e.getSource();
		if (src == btnStart) {
			for (LottoPnlForm pnl : lottoPnls) {
				if (pnl.getSelectNums().size() == 6) {
					isSix++;
					if (isSix == count) {
						// 시작버튼 누르면 결과화면 객체 생성
						for (int i = 0; i < count; i++) {
							colTemp = lottoPnls[i].getSelectNums();
							listChooseNums.add(colTemp);
							// System.out.println(listTest+ "?");
							automanualList.add(lottoPnls[i].getAutomanual());
						}
						new LottoResult(count, this);
						setVisible(false);
					}
				}
			}
			if (isSix != count) {
				JOptionPane.showMessageDialog(null, "숫자를 모두 선택해 주세요");
			}
		}
		if (src == btnCancel) {
			new StartGame();
			dispose();
		}
		if (src == btnReset) {
			int result = JOptionPane.showConfirmDialog(null, "초기화 하시겠습니까?", "Warning", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				for (LottoPnlForm pnl : lottoPnls) {
					pnl.clear(true);
				}
			}
		}
		if (src == btnAuto) {
			for (LottoPnlForm pnl : lottoPnls) {
				pnl.clear(true);
				pnl.getRbtnAuto().setSelected(true);
				for (int i = 0; i < 45; i++) {
					pnl.getCbs()[i].setEnabled(false);
				}
				Integer[] randArr = new MyNums().getNums();
				for (int i = 0; i < randArr.length; i++) {
					pnl.getSelectNums().add(randArr[i]);
				}
			}
		}
	}
}
