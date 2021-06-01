package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

class LottoResult extends JDialog implements ActionListener {
	private JLabel lblMain;
	private JLabel lblPlus;
	private JLabel[] lblWinNums = new JLabel[7];
	private Integer[] winNum;
	private JButton btnBack;
	private JButton btnTrace;
	private Color bColor;
	private ResultPnlForm[] resultPanels;
	private ChooseMyNumForm chooseMyNumForm;
	private int count;
	private LottoNums goalNums;

	public LottoResult(int count, ChooseMyNumForm chooseMyNumForm) {
		this.chooseMyNumForm = chooseMyNumForm;
		this.count = count;
		init();
		addListener();
		setDisplay();
		showFrame();
	}

	public int getCount() {
		return count;
	}

	public Integer[] getWinNum() {
		return winNum;
	}

	public LottoNums getGoalNums() {
		return goalNums;
	}

	private void init() {
		lblMain = new JLabel();
		lblMain.setIcon(new ImageIcon("LottoImg.png"));
		lblPlus = new JLabel("+", JLabel.CENTER);
		winNum = new LottoNums().getNums();
		bColor = new Color(236, 25, 42);

		for (int i = 0; i < lblWinNums.length; i++) {
			lblWinNums[i] = new JLabel("" + winNum[i]);
			if (winNum[i] <= 10) {
				lblWinNums[i].setIcon(new ImageIcon("Lotto10.png"));
			} else if (winNum[i] > 10 && winNum[i] <= 20) {
				lblWinNums[i].setIcon(new ImageIcon("Lotto20.png"));
			} else if (winNum[i] > 20 && winNum[i] <= 30) {
				lblWinNums[i].setIcon(new ImageIcon("Lotto30.png"));
			} else if (winNum[i] > 30 && winNum[i] <= 40) {
				lblWinNums[i].setIcon(new ImageIcon("Lotto40.png"));
			} else if (winNum[i] > 40 && winNum[i] <= 45) {
				lblWinNums[i].setIcon(new ImageIcon("Lotto50.png"));
			}
			lblWinNums[i].setHorizontalTextPosition(JLabel.CENTER);
		}

		btnBack = new JButton("돌아가기");
		btnTrace = new JButton("찾기");
	}

	private void addListener() {
		btnBack.addActionListener(this);
		btnTrace.addActionListener(this);
	}

	private void setDisplay() {
		JPanel pnlMain = new JPanel(new BorderLayout());
		JPanel pnlMain2 = new JPanel(new BorderLayout());

		JPanel pnlNorth = new JPanel(new BorderLayout());

		JPanel pnlCenter = new JPanel(new FlowLayout());

		JPanel pnlPlus = new JPanel(new BorderLayout());

		JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JPanel pnlWinNum = new JPanel(new GridLayout(1, 0));

		JPanel pnlSouth = new JPanel(new GridLayout(0, 1));

		resultPanels = new ResultPnlForm[count];
		for (int i = 0; i < count; i++) {
			resultPanels[i] = new ResultPnlForm(i, chooseMyNumForm, this);
			pnlSouth.add(resultPanels[i]);
		}

		pnlPlus.add(lblPlus);
		pnlPlus.setBackground(bColor);

		for (int i = 0; i < lblWinNums.length; i++) {
			if (i == 6) {
				pnlWinNum.add(pnlPlus);
			}
			pnlWinNum.add(lblWinNums[i]);
		}
		pnlCenter.setBackground(bColor);

		pnlButton.add(btnBack);
		pnlButton.add(btnTrace);

		pnlCenter.add(new JLabel("당첨번호 : "), BorderLayout.NORTH);
		pnlNorth.add(lblMain, BorderLayout.CENTER);
		pnlCenter.add(pnlWinNum);

		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		pnlMain2.add(pnlMain, BorderLayout.CENTER);
		pnlMain2.add(pnlButton, BorderLayout.SOUTH);

		add(pnlMain2);
	}

	private void showFrame() {
		setTitle("Lotto");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object scr = e.getSource();
		if (scr == btnBack) {
			dispose();
			new ChooseMyNumForm(count);
		}
		if (scr == btnTrace) {
			new LottoTrace(chooseMyNumForm);
		}
	}
}
