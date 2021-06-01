package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class ResultPnlForm extends JPanel {
	private JLabel[] lblNums;
	private JLabel lblRank;
	private JLabel lblAutoMan;
	private ChooseMyNumForm myNums;
	private int order;
	private Integer[] colToArr = new Integer[6];
	private LottoResult lresult;

	public ResultPnlForm(int order, ChooseMyNumForm myNums, LottoResult lresult) {
		this.myNums = myNums;
		this.order = order;
		this.lresult = lresult;
		init();
	}

	private void init() {
		Dimension lblSize = new Dimension(50, 30);
		Dimension pnlSize = new Dimension(100, 40);

		lblAutoMan = new JLabel(myNums.getAutomanualList().get(order), JLabel.CENTER);
		lblNums = new JLabel[6];
		lblRank = new JLabel("", JLabel.CENTER);
		lblRank.setBorder(new LineBorder(Color.BLACK, 1));
		lblRank.setPreferredSize(lblSize);
		lblRank.setOpaque(true);
		lblRank.setBackground(Color.WHITE);

		for (int i = 0; i < lblNums.length; i++) {
			lblNums[i] = new JLabel("", JLabel.CENTER);
			lblNums[i].setBorder(new LineBorder(Color.BLACK, 1));
			lblNums[i].setPreferredSize(lblSize);
			lblNums[i].setOpaque(true);
			lblNums[i].setBackground(Color.WHITE);

			Collection<Integer> colTemp = myNums.getListChooseNums().get(order);
			colToArr = colTemp.toArray(new Integer[0]);
			lblNums[i].setText("" + colToArr[i]);
		}

		this.setLayout(new BorderLayout());

		JPanel pnlMyNums = new JPanel();
		for (JLabel lbl : lblNums) {
			pnlMyNums.add(lbl);
			pnlMyNums.setPreferredSize(pnlSize);
		}

		lblRank.setText(showResult());

		JPanel pnlAuto = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlAuto.add(lblAutoMan);

		this.add(pnlAuto, BorderLayout.WEST);
		this.add(pnlMyNums, BorderLayout.CENTER);
		this.add(lblRank, BorderLayout.EAST);

		int title = 65 + order;
		char charTitle = (char) title;
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "" + charTitle);
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		this.setBorder(tBorder);

	}

	private String showResult() {
		Integer[] excBonus = Arrays.copyOfRange(lresult.getWinNum(), 0, LottoNums.BONUS);

		List<Integer> goalList = new Vector<Integer>(Arrays.asList(excBonus));

		List<Integer> myList = new Vector<Integer>(Arrays.asList(colToArr));
		myList.retainAll(goalList);

		for (int i = 0; i < lblNums.length; i++) {
			for (int j = 0; j < myList.size(); j++) {
				String str = lblNums[i].getText();

				if (Integer.parseInt(str) == myList.get(j)) {
					lblNums[i].setBackground(Color.YELLOW);
				}
			}
		}

		int k = lresult.getWinNum()[6];
		for (int i = 0; i < lblNums.length; i++) {
			String str = lblNums[i].getText();
			if (Integer.parseInt(str) == k) {
				lblNums[i].setBackground(Color.RED);
			}
		}
		int count = myList.size();
		int rank = 0;

		switch (count) {
		case 6:
			rank = 1;
			break;
		case 5:
			int indexOfBonus = Arrays.binarySearch(colToArr, lresult.getGoalNums().getBonusNum());
			if (indexOfBonus >= 0) {
				rank = 2;
			} else {
				rank = 3;
			}
			break;
		case 4:
			rank = 4;
			break;
		case 3:
			rank = 5;
			break;
		}
		String result;
		if (rank == 0) {
			result = "²Î";
		} else {
			result = rank + "µî";
		}
		return result;
	}
}
