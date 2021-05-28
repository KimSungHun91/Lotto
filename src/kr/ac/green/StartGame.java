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
	private JLabel lbl;
	private JTextField tf;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblimg;
	int num;
//	LottoPnlForm makePanel = new LottoPnlForm();
	
	public StartGame(){
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		lblimg = new JLabel();
		lblimg.setIcon(new ImageIcon("LottoImg.png"));
		
		lbl = new JLabel("Ƚ���� �Է��ϼ���");
		
		
		tf = new JTextField(5);
		btnOk = new JButton("Ȯ��");
		btnCancel = new JButton("���");
	}

	private void setDisplay() {
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lbl);
		pnlCenter.add(tf);
		
		Insets insets = new Insets(20,20,20,20);
		pnlCenter.setBorder(new EmptyBorder(insets));
		
		JPanel pnlBottom = new JPanel();
		pnlBottom.add(btnOk);
		pnlBottom.add(btnCancel);
		
		add(lblimg, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);
		
	}

	private void addListeners() {

		btnOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				String s = tf.getText();
				
				if(!isDigit(s)){
					JOptionPane.showMessageDialog(null, "1���� 5������ ���ڸ� �Է����ּ���");
					tf.setText("");
					tf.requestFocus();
				}
				else{
					num = Integer.parseInt(s);
					new ChooseMyNumForm(num);
					dispose();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				
				System.exit(0);
			}
		});
	}
	
	
	public boolean isDigit(String s){
		boolean output = false;
		if(s.length() == 1){
			for(int i = 0; i<s.length(); i++){
				char tmp = s.charAt(i);
		
				if(!('1' <= tmp && tmp <= '5')){
					output = false;
				}
				else{
					output = true;
				}
			}
		}
		else{
			output = false;
		}
		return output;
	}

	private void showFrame() {
		setTitle("Lotto Game");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	
	public static void main(String[] args) {
		new StartGame();
	}

}
