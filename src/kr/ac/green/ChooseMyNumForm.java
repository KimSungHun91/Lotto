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
	// LottoPnlForm�� ������ ��ü�ϱ� �迭�� ���� �����ؾ��Ѵ�
	private LottoPnlForm[] lottoPnls;
	private Collection<Integer> colTemp = new ArrayList<Integer>();
	private ArrayList<Collection<Integer>> listChooseNums = new ArrayList<>();
	private Vector<String> automanualList = new Vector<String>(); // �ڵ���������
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
		taInfo.setText("1.  �ڵ����� �������� �����ϼ���.\n\n" + "2.  �ڵ� �� ��� ��ȣ�� �������� �ʾƵ� �˴ϴ�.\n\n"
				+ "3.  ���� �� ��� ��ȣ�� 6�� ��� ���� �ؾ� �մϴ�.\n\n" + "4.  ��ȣ ���� �Ϸ� ��  ���� ��ư�� ������ ��÷ ���ΰ� ���ɴϴ�.\n\n"
				+ "5.  ���� ��ư�� ���� �� ��� ������ �ʱ�ȭ�˴ϴ�.");

		btnStart = new JButton("����");
		btnCancel = new JButton("���");
		btnReset = new JButton("����");
		btnAuto = new JButton("����ڵ�");
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
		lottoPnls = new LottoPnlForm[count];
		for (int i = 0; i < count; i++) {
			// �г��� �����ϰ�
			LottoPnlForm pnl = new LottoPnlForm(i);
			// ȭ�鿡 ��ġ
			pnlCenter.add(pnl);
			// �����迭�� ������ �г� ���� ����
			lottoPnls[i] = pnl;
			// test = makePanels[i].col;
		}

		// �гγ���, ��ư 3����
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// �гι�ư, ��ư 3���� �� ���̺� ��Ҿ��
		JPanel pnlbtn = new JPanel();
		pnlbtn.add(btnStart);
		pnlbtn.add(btnAuto);
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
						// ���۹�ư ������ ���ȭ�� ��ü ����
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
				JOptionPane.showMessageDialog(null, "���ڸ� ��� ������ �ּ���");
			}
		}
		if (src == btnCancel) {
			new StartGame();
			dispose();
		}
		if (src == btnReset) {
			int result = JOptionPane.showConfirmDialog(null, "�ʱ�ȭ �Ͻðڽ��ϱ�?", "Warning", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				for (LottoPnlForm pnl : lottoPnls) {
					pnl.clear(true);
				}
			}
		}
		if (src == btnAuto) {
			
			for (LottoPnlForm pnl : lottoPnls) {
				pnl.autoSelect();
			}
			
			// for (LottoPnlForm pnl : lottoPnls) {
			// pnl.clear(true);
			// pnl.getRbtnAuto().setSelected(true);
			// for (int i = 0; i < 45; i++) {
			// pnl.getCbs()[i].setEnabled(false);
			// }
			// Integer[] randArr = new MyNums().getNums();
			// for (int i = 0; i < randArr.length; i++) {
			// pnl.getSelectNums().add(randArr[i]);
			// }
			// }
		}
	}
}
