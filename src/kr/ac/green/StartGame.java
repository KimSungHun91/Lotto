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
		// 레이블에 이미지 넣기
		lblImg = new JLabel();
		lblImg.setIcon(new ImageIcon("LottoImg.png"));

		lbltext = new JLabel("횟수를 입력하세요");

		tfInput = new JTextField(5);

		btnOk = new JButton("확인");
		btnCancel = new JButton("취소");
	}

	private void setDisplay() {
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lbltext);
		pnlCenter.add(tfInput);
		// 공백 설정
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
		// ok버튼 눌렀을 때
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String s = tfInput.getText(); // 입력받은 문자열 뽑아냄

				if (!isDigit(s)) { // isDigit이 false이면(조건에 맞지않는 문자열일 경우)
					JOptionPane.showMessageDialog(null, "1부터 5까지의 숫자만 입력해주세요"); // 알림창
					tfInput.setText(""); // tfInput 초기화
					tfInput.requestFocus(); // tfInput에 커서
				} else { // isDigit이 true이면
					num = Integer.parseInt(s); // 입력받은 문자열을 int타입으로 변경
					new ChooseMyNumForm(num); // ChooseMyNumForm을 호출
					dispose(); // 창 닫기
				}
			}
		});
		// 취소버튼을 눌렀을 때의 액션리스너
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0); // 닫기
			}
		});
	}

	// 입력한 문자열이 조건에 맞는지 판단하는 메소드
	public boolean isDigit(String s) { // 파라미터로 입력받은 문자열을 판단한다
		boolean output = false;
		if (s.length() == 1) { // 문자열의 길이가 1일 경우

			char tmp = s.charAt(0); // s의 인덱스0에 있는 문자를 tmp에 저장

			if (!('1' <= tmp && tmp <= '5')) { // tmp가 1이상이면서 5이하가 아닌 경우
				output = false; // false
			} else { // tmp가 1이상이면서 5이하이면
				output = true; // true
			}
		} else { // 문자열의 길이가 1이 아닐 경우
			output = false; // false
		}
		return output; // 리턴값
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
