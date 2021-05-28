package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

class LottoResult extends JFrame implements ActionListener {

	private JLabel lblMain;
	private JLabel lblPlus;

	private JLabel[] lblWinNums = new JLabel[7];

	private Integer[] winNum;
	private String[] strWinNums = new String[7];

	private TreeSet<Integer> winNums = new TreeSet<>();

	private JButton btnClose;
	private JButton btnTrace;

	private LineBorder border;
	private Color bColor;

	private ResultPnlForm[] resultPanels;
	
	private List<LottoGameInfo> gameInfos;

	private int count;

	
	public LottoResult(int count, List<LottoGameInfo> gameInfos) {
		this.gameInfos = gameInfos;
		this.count = count;
		init();
		addListener();
		setDisplay();
		showFrame();
	}

	private void init() {
		lblMain = new JLabel();
		lblMain.setIcon(new ImageIcon("LottoImg.png"));
		lblPlus = new JLabel("+", JLabel.CENTER);
		border = new LineBorder(Color.BLACK, 1);
		winNum = new LottoNums().getNums();

		for (int i = 0; i < lblWinNums.length; i++) {
			lblWinNums[i] = new JLabel("" + winNum[i]);
			lblWinNums[i].setBorder(border);
		}

		Integer[] winNumF = winNums.toArray(new Integer[0]);
		for (int i = 0; i < winNumF.length; i++) {
			strWinNums[i] = String.valueOf(winNumF[i]);
			lblWinNums[i] = new JLabel(strWinNums[i], JLabel.CENTER);
		}
		btnClose = new JButton("Close");
		btnTrace = new JButton("Trace");
	}

	private void addListener() {
		btnClose.addActionListener(this);
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
			// [[1,2,3,4,5,6], [2,3,4,5,6,7], [3,4,5,6,7,8]]
			// [1,2,3,4,5,6] resultPanels[0]
			// [2,3,4,5,6,7] resultPanels[1]
			// [3,4,5,6,7,8] resultPanels[2]
			resultPanels[i] = new ResultPnlForm(gameInfos.get(i));
			pnlSouth.add(resultPanels[i]);
		}
		
		
		pnlPlus.add(lblPlus);
		pnlPlus.setBackground(bColor);

		for (int i = 0; i < lblWinNums.length; i++) {
			lblWinNums[i].setBorder(border);
			if (i == 6) {
				pnlWinNum.add(pnlPlus);
			}
			pnlWinNum.add(lblWinNums[i]);
		}
		
		
		pnlCenter.setBackground(bColor);

		pnlButton.add(btnClose);
		pnlButton.add(btnTrace);

		pnlCenter.add(new JLabel("´çÃ·¹øÈ£ : "), BorderLayout.NORTH);
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
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object scr = e.getSource();
		if (scr == btnClose) {
			dispose();
			new ChooseMyNumForm(count);
		}
	}
}
