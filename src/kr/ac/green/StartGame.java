package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StartGame extends JFrame {
	private JLabel lbltext;
	private JTextField tfInput;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblImg;
	private int num;

	public StartGame() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		// ���̺� �̹��� �ֱ�
		lblImg = new JLabel();
		lblImg.setIcon(new ImageIcon("LottoImg.png"));

		lbltext = new JLabel("Ƚ���� �Է��ϼ���");

		tfInput = new JTextField(5);

		btnOk = new JButton("Ȯ��");
		btnCancel = new JButton("���");
	}

	private void setDisplay() {
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lbltext);
		pnlCenter.add(tfInput);
		// ���� ����
		Insets insets = new Insets(20, 20, 20, 20);
		pnlCenter.setBorder(new EmptyBorder(insets));

		JPanel pnlBottom = new JPanel();
		pnlBottom.add(btnOk);
		pnlBottom.add(btnCancel);

		add(lblImg, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);

	}

	private void addListeners() {
		// ok��ư ������ ��
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String s = tfInput.getText(); // �Է¹��� ���ڿ� �̾Ƴ�

				if (!isDigit(s)) { // isDigit�� false�̸�(���ǿ� �����ʴ� ���ڿ��� ���)
					JOptionPane.showMessageDialog(null, "1���� 5������ ���ڸ� �Է����ּ���"); // �˸�â
					tfInput.setText(""); // tfInput �ʱ�ȭ
					tfInput.requestFocus(); // tfInput�� Ŀ��
				} else { // isDigit�� true�̸�
					num = Integer.parseInt(s); // �Է¹��� ���ڿ��� intŸ������ ����
					new ChooseMyNumForm(num); // ChooseMyNumForm�� ȣ��
					dispose(); // â �ݱ�
				}
			}
		});
		// ��ҹ�ư�� ������ ���� �׼Ǹ�����
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0); // �ݱ�
			}
		});
	}

	// �Է��� ���ڿ��� ���ǿ� �´��� �Ǵ��ϴ� �޼ҵ�
	public boolean isDigit(String s) { // �Ķ���ͷ� �Է¹��� ���ڿ��� �Ǵ��Ѵ�
		boolean output = false;
		if (s.length() == 1) { // ���ڿ��� ���̰� 1�� ���

			char tmp = s.charAt(0); // s�� �ε���0�� �ִ� ���ڸ� tmp�� ����

			if (!('1' <= tmp && tmp <= '5')) { // tmp�� 1�̻��̸鼭 5���ϰ� �ƴ� ���
				output = false; // false
			} else { // tmp�� 1�̻��̸鼭 5�����̸�
				output = true; // true
			}
		} else { // ���ڿ��� ���̰� 1�� �ƴ� ���
			output = false; // false
		}
		return output; // ���ϰ�
	}

	private void showFrame() {
		setTitle("Lotto Game");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new StartGame();
	}
}
