package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LottoTrace extends JDialog implements ActionListener {
	private ChooseMyNumForm chooseMyNumForm;
	private Collection<Integer> colTemp = new ArrayList<Integer>();
	private LottoNums lottonum;
	private JLabel lbltext;
	private JTextField tfText;
	private JButton btn1st;
	private JButton btn2nd;
	private String info;

	public LottoTrace(ChooseMyNumForm chooseMyNumForm) {
		this.chooseMyNumForm = chooseMyNumForm;
		init();
		setDisplay();
		addListener();
		showDlog();
	}

	private void init() {
		info = "선택 하세요";

		btn1st = new JButton("1등 찾기");
		btn2nd = new JButton("2등 찾기");

		lbltext = new JLabel("", JLabel.CENTER);
		lbltext.setText(info);

		tfText = new JTextField(20);
		tfText.setText(info);
		tfText.setEditable(false);

	}

	private void setDisplay() {
		JPanel pnlMain = new JPanel(new BorderLayout());
		JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlCenter.add(tfText);

		pnlSouth.add(btn1st);
		pnlSouth.add(btn2nd);

		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		add(pnlMain);
	}

	private void addListener() {
		btn1st.addActionListener(this);
		btn2nd.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == btn1st) {
			int gameCount = 0;
			int end = 0;

			while (end == 0) {
				gameCount++;
				Integer[] excBonus = Arrays.copyOfRange(new MyNums().getNums(), 0, LottoNums.BONUS);
				List<Integer> goalList = new ArrayList<Integer>(Arrays.asList(excBonus));
				for (int i = 0; i < chooseMyNumForm.getListChooseNums().size(); i++) {

					colTemp = chooseMyNumForm.getListChooseNums().get(i);
					ArrayList<Integer> arrChooseNums = new ArrayList<>(colTemp);
					// System.out.println(l);
					// System.out.println(test3 +"내 숫자");
					// System.out.println(goalList + "당첨 숫자");

					// 맞는 숫자 찾기
					arrChooseNums.retainAll(goalList);

					if (arrChooseNums.size() == 6) {
						end = 1;
						info = (i + 1) + "번째 게임의 " + gameCount + "번째에 당첨!";
					}
				}
			}
		}

		if (src == btn2nd) {
			int gameCount = 0;
			int end = 0;
			while (end == 0) {
				gameCount++;
				lottonum = new LottoNums();
				Integer[] excBonus = Arrays.copyOfRange(lottonum.getNums(), 0, LottoNums.BONUS);

				List<Integer> goalList = new ArrayList<Integer>(Arrays.asList(excBonus));
				for (int i = 0; i < chooseMyNumForm.getListChooseNums().size(); i++) {

					colTemp = chooseMyNumForm.getListChooseNums().get(i);
					ArrayList<Integer> arrChooseNums = new ArrayList<>(colTemp);
					// System.out.println(l);
					// System.out.println(test3 +"내 숫자");
					// System.out.println(goalList + "당첨 숫자");
					// System.out.println(lottonum.getBonusNum() + "보너스 숫자");

					// 맞는 숫자 찾기
					Integer[] arrForBouns = arrChooseNums.toArray(new Integer[0]);
					arrChooseNums.retainAll(goalList);
					if (arrChooseNums.size() == 5) {
						int indexOfBonus = Arrays.binarySearch(arrForBouns, lottonum.getBonusNum());
						if (indexOfBonus >= 0) {
							end = 1;
							info = (i + 1) + "번째 게임의 " + gameCount + "번째에 당첨!";
						}
					}
				}
			}
		}
		tfText.setText(info);
	}

	private void showDlog() {
		setTitle("Trace");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

}
