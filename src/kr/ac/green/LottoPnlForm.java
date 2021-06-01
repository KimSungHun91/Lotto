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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private String automanual = "����";
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
		// �ݺ����� �ְ� üũ�ڽ� ��Ӹ�����
		for (int i = 0; i < cbs.length; i++) {
			int n = i + 1;
			String text = String.valueOf(n);
			cbs[i] = new JCheckBox(text);
		}

		// JRadioButton ���鱸��, �׷��ϰ�..
		rbtnAuto = new JRadioButton("�ڵ�");
		rbtnManual = new JRadioButton("����", true);
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnAuto);
		group.add(rbtnManual);

		// üũ�ڽ����� ������ ��ȣ ������ �ؽ�Ʈ�ʵ� ���������, �����Ұ�..
		tfMyNum = new JTextField(30);
		tfMyNum.setEditable(false);

		// �г��� �������̾ƿ����� ��, ��, ������ �־�����
		this.setLayout(new BorderLayout());
		// ���ʿ� �׸��巹�̾ƿ����� �ζǹ�ȣ �� �гο� üũ�ڽ� �߰�
		JPanel pnlLottoNum = new JPanel(new GridLayout(0, 7));
		for (JCheckBox cbs : cbs) {
			pnlLottoNum.add(cbs);
		}

		// ���ʿ� �ڵ� ���� ������ ��ư �־�����
		JPanel pnlRbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlRbtn.add(rbtnAuto);
		pnlRbtn.add(rbtnManual);

		this.add(pnlLottoNum, BorderLayout.NORTH);
		// ���Ϳ��� �׳� ���̺� �־���� ������
		this.add(tfMyNum, BorderLayout.CENTER);
		this.add(pnlRbtn, BorderLayout.SOUTH);

		// Ÿ��Ʋ���� �Ἥ �̻ڰ� ��������
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
							// System.out.println((i + 1) + "���õ�");
							selectNums.add(i + 1);

						} else if (result == ItemEvent.DESELECTED) {
							// System.out.println((i + 1) + "������");
							// col.remove(i + 1);
							selectNums.remove(Integer.valueOf(i + 1));
						}
						Collections.sort(selectNums);
						tfMyNum.setText("" + selectNums);
					}
				}
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					selectCount++; // �ѹ� üũ�ɶ����� 1����
				} else {
					selectCount--; // üũ �����ɶ����� 1����
				}
				if (selectCount > 6) { // 6�� �̻� �����Ϸ��� �� ���
					JOptionPane.showMessageDialog(null, "6�� �̻� ������ �� �����ϴ�"); // ���â
				}

			}
		};
		// ��� JCheckBox�� addItemListener �߰�
		for (JCheckBox cbs : cbs) {
			cbs.addItemListener(listener);
		}
	}

	// �ʱ�ȭ �ϴ� �޼ҵ�, �Ķ���ͷ� ���� ��ư�϶���
	// �ڵ� ���� ��ư�� �� ��������
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

	public void autoSelect() {
		// List ����� 6�� �ƴϸ�
		if (selectNums.size() > 0 && selectNums.size() < 6) {
			automanual = "���ڵ�";
			for (int i = 0; i < 45; i++) {
				cbs[i].setEnabled(false);
			}
			// ���ο� ���� ��ȣ ����
			MyNums myNums = new MyNums(selectNums);
			HashSet<Integer> TempNums = myNums.getSemiAutoNums();
			Integer[] semiAutoNums = TempNums.toArray(new Integer[0]);
			Arrays.sort(semiAutoNums, 0, 6);
			selectNums.removeAll(selectNums);
			// ������ȣ�� List�� �־���
			for (int i = 0; i < semiAutoNums.length; i++) {
				selectNums.add(semiAutoNums[i]);
				tfMyNum.setText("" + selectNums);
				rbtnAuto.setSelected(true);
			}
		}
		if (selectNums.size() == 0) {
			automanual = "�ڵ�";
			clear(false);
			for (int i = 0; i < 45; i++) {
				cbs[i].setEnabled(false);
			}
			Integer[] randArr = new MyNums().getNums();
			for (int i = 0; i < randArr.length; i++) {
				selectNums.add(randArr[i]);
				tfMyNum.setText("" + selectNums);
				rbtnAuto.setSelected(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == rbtnAuto) {
			autoSelect();
		}
		if (src == rbtnManual) {
			clear(false);
			for (int i = 0; i < 45; i++) {
				cbs[i].setEnabled(true);
			}
		}
	}
}
