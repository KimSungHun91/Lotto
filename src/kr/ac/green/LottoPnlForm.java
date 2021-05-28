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
	
	// ������ȣ / �ڵ���ȣ ���
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
	
	// �����ڵ� ��ȣ ��� ����
//	public List<Integer> getCol() {
//		return col;
//	}

	private void init() {
		cb = new JCheckBox[45];

		// �ݺ����� �ְ� üũ�ڽ� ��Ӹ�����
		for (int i = 0; i < cb.length; i++) {
			int n = i + 1;
			String text = String.valueOf(n);
			cb[i] = new JCheckBox(text);
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
		for (JCheckBox cbs : cb) {
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
							// System.out.println((i + 1) + "���õ�");
							col.add(i + 1);

						} else if (result == ItemEvent.DESELECTED) {
							// System.out.println((i + 1) + "������");
							col.remove(Integer.valueOf(i+1));
						}
						Collections.sort(col);
						tfMyNum.setText("" + col);
					}
				}
				// JCheckBox cb = (JCheckBox)ie.getSource();

				if (ie.getStateChange() == ItemEvent.SELECTED) {
					numcount++; // �ѹ� üũ�ɶ����� 1����
				} else {
					numcount--; // üũ �����ɶ����� 1����
				}
				if (numcount > 6) { // 6�� �̻� �����Ϸ��� �� ���
					JOptionPane.showMessageDialog(null, "6�� �̻� ������ �� �����ϴ�"); // ���â
				}

			}
		};
		// ��� JCheckBox�� addItemListener �߰�
		for (JCheckBox cbs : cb) {
			cbs.addItemListener(listener);
		}
	}

	// �ʱ�ȭ �ϴ� �޼ҵ�, �Ķ���ͷ� ���� ��ư�϶���
	// �ڵ� ���� ��ư�� �� ��������
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
