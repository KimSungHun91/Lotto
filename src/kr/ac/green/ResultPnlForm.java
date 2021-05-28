package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class ResultPnlForm extends JPanel {
	private JLabel[] lblNums;
	private JLabel lblRank;
//	private LottoResult lottoResult;
//	private List<Integer> myNums;
	private LottoGameInfo gameInfo;

	public ResultPnlForm(LottoGameInfo gameInfo) {
		this.gameInfo = gameInfo;
		init();
	}

	private void init() {
		Dimension lblSize = new Dimension(50, 30);
		Dimension pnlSize = new Dimension(100, 40);
		
		lblNums = new JLabel[6];
		lblRank = new JLabel("?");
		lblRank.setBorder(new LineBorder(Color.BLACK, 1));
		lblRank.setPreferredSize(lblSize);
		lblRank.setOpaque(true);
		lblRank.setBackground(Color.WHITE);
		
		
		for (int i = 0; i < lblNums.length; i++) {
			lblNums[i] = new JLabel("", JLabel.CENTER);
			// [1,2,3,4,5,6]
			lblNums[i].setText("" + gameInfo.getNumbers().get(i));
			
			lblNums[i].setBorder(new LineBorder(Color.BLACK, 1));
			lblNums[i].setPreferredSize(lblSize);
			lblNums[i].setOpaque(true);
			lblNums[i].setBackground(Color.WHITE);
		}

		this.setLayout(new BorderLayout());
		
		JPanel pnlMyNums = new JPanel();
		for (JLabel lbl : lblNums) {
			pnlMyNums.add(lbl);
			pnlMyNums.setPreferredSize(pnlSize);
		}
		this.add(pnlMyNums, BorderLayout.CENTER);
		this.add(lblRank, BorderLayout.EAST);
		
		
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "Check you Number");
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		this.setBorder(tBorder);
	}
}
