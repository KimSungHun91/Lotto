package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	// MakePanel�� ������ ��ü�ϱ� �迭�� ���� �����ؾ��Ѵ�
	
	private LottoPnlForm[] lottoPnlInfo;
//	Collection<Integer> test = new ArrayList<Integer>();
//	public Integer[] test2;
	
	private int count;

	public ChooseMyNumForm(int count) {
		this.count = count;
		init();
		setDiplay();
		addListeners();
		showFrame();
	}
	
	private void init() {

		taInfo = new JTextArea();
		taInfo.setEditable(false);
		taInfo.setText(
			"1.  �ڵ����� �������� �����ϼ���.\n\n"
			+ "2.  �ڵ� �� ��� ��ȣ�� �������� �ʾƵ� �˴ϴ�.\n\n"
			+ "3.  ���� �� ��� ��ȣ�� 6�� ��� ���� �ؾ� �մϴ�.\n\n"
			+ "4.  ��ȣ ���� �Ϸ� ��  ���� ��ư�� ������ ��÷ ���ΰ� ���ɴϴ�.\n\n"
			+ "5.  ���� ��ư�� ���� �� ��� ������ �ʱ�ȭ�˴ϴ�."
		);

		btnStart = new JButton("����");
		btnCancel = new JButton("���");
		btnReset = new JButton("����");
	}

	private void setDiplay() {
		// �гμ���, �����
		JPanel pnlWest = new JPanel();

		pnlWest.add(taInfo);
		pnlWest.setBackground(Color.WHITE);
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "# PlayInfo");
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		pnlWest.setBorder(tBorder);

		// �гμ���, �ζ� ��ȣ�� �ڵ����� ������ �ִ� �г�(LottoPnlForm)�� �߰�
		JPanel pnlCenter = new JPanel(new GridLayout(1, 0));
		
		// �Է� ���� ����ŭ MakePanel �迭�� ����
		lottoPnlInfo = new LottoPnlForm[count];
		for (int i = 0; i < count; i++) {
			// �г��� �����ϰ�
			LottoPnlForm pnl = new LottoPnlForm(i+1);
			// ȭ�鿡 ��ġ
			pnlCenter.add(pnl);
			// �����迭�� ������ �г� ���� ����
			lottoPnlInfo[i] = pnl;
//			test = makePanels[i].col;
		}
		
		// �гγ���, ��ư 3����
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// �гι�ư, ��ư 3���� �� ���̺� ��Ҿ��
		JPanel pnlbtn = new JPanel();
		pnlbtn.add(btnStart);
		pnlbtn.add(btnReset);
		pnlbtn.add(btnCancel);
		pnlSouth.add(pnlbtn);

		// �׵θ� ���� �ʿ� �� ���� ������ ���Ƽ� ���ο� �޾Ҿ��
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
	}

	private void showFrame() {
		setTitle("Lotto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == btnStart) {
			
			// ���۹�ư ������ ���ȭ�� ��ü ����
//			for (int i = 0; i < count; i++) {
//				test = makePanels[i].getCol();
//				test2 = test.toArray(new Integer[0]);
				
//			}
//			List<List<Integer>> myNumbers = new ArrayList<>();
			
			List<LottoGameInfo> gameInfos = new ArrayList<>();
			// [  ���ӹ�ȣ, �ڵ�/����, �ζǹ�ȣ  ]
			for (LottoPnlForm lpf : lottoPnlInfo) {
				gameInfos.add(lpf.getGameInfo());
			}
			new LottoResult(count, gameInfos);
			setVisible(false);
		}
		if (src == btnCancel) {
			new StartGame();
			dispose();
		}
		if (src == btnReset) {
			for (LottoPnlForm pnl : lottoPnlInfo) {
				pnl.clear(true);
			}
		}
	}
}
