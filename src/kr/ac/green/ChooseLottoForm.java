package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ChooseLottoForm extends JFrame implements ActionListener {
	
	private JLabel lblInfo;
	private JTextField lblMyNum;
	// üũ �ڽ� �迭.. ���̺�� �ص� �ǿ�
	private JCheckBox[] cb;
	
	private JRadioButton rbtnAuto;
	private JRadioButton rbtnManual;
	
	private JButton btnStart;
	private JButton btnCancel;
	private JButton btnReset;
	
	private int count;
	
	public ChooseLottoForm(int count) {
		this.count = count;
		init();
		setDiplay();
		addListeners();
		showFrame();
	}
	
	private void init() {
		lblInfo = new JLabel("����");
		
		btnStart = new JButton("����");
		btnCancel = new JButton("���");
		btnReset = new JButton("����");
	}
	
	private void setDiplay() {
		// �гμ���, �����
		JPanel pnlWest = new JPanel();
		pnlWest.add(lblInfo);
		
		// �гμ���, �ζ� ��ȣ�� �ڵ����� ����
		JPanel pnlCenter = new JPanel(new GridLayout(1, 0));
		for (int i = 0; i < count; i++) {
			pnlCenter.add(getPnl());
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
		
		
		add(pnlMain, BorderLayout.CENTER);
	}
	
	// �ζ� ��ȣ �����ϴ� �г� �����ϴ� �޼ҵ忡��
	// �������� �ʿ��ؼ� �޼ҵ忡 �ְ� ȣ���ϰ� ��������..
	private JPanel getPnl() {
		// üũ�ڽ� �迭�� �ߴµ� 1�� ���̰� ��������
		// �𸣰ھ�� �迭�� 0���� �����̶� �ڽ���ȣ�� 0���� �����ؿ�
		
		// �׸��� ���� init() �޼ҵ忡 cb �־�ϴϱ�
		// ���� �ѹ��ۿ� �ȵǼ� ��� �������ҰŰ��Ƽ�
		// üũ�ڽ��� ���� �ʿ��� ��ư�鵵 ����ְ� �޼ҵ� ȣ��Ǹ�
		// ��� �����ǰ� �߾��.. �̰Ը´°���..
		
		// üũ�ڽ� ũ�� 0~44������ �迭 ����
		cb = new JCheckBox[45];
		
		// �ݺ����� �ְ� üũ�ڽ� ��Ӹ�����
		for (int i = 0; i < 45; i++) {
			int n = i + 1;
			String text = String.valueOf(n); 
			cb[i] = new JCheckBox(text);
		}
		
		// JRadioButton ���鱸��, �׷��ϰ�..
		rbtnAuto = new JRadioButton("�ڵ�", true);
		rbtnManual = new JRadioButton("����");
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnAuto);
		group.add(rbtnManual);
		
		// üũ�ڽ����� ������ ��ȣ ������ �ؽ�Ʈ�ʵ� ���������, �����Ұ�..
		lblMyNum = new JTextField();
		lblMyNum.setEditable(false);		
		
		// �г��� �������̾ƿ����� ��, ��, ������ �־�����
		JPanel pnlLottoForm = new JPanel(new BorderLayout());
		// ���ʿ� �׸��巹�̾ƿ����� �ζǹ�ȣ �� �гο� üũ�ڽ� �߰�
		JPanel pnlLottoNum = new JPanel(new GridLayout(0, 7));
		for (JCheckBox cbs : cb) {
			pnlLottoNum.add(cbs);
		}
		// ���ʿ� �ڵ� ���� ������ ��ư �־�����
		JPanel pnlRbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlRbtn.add(rbtnAuto);
		pnlRbtn.add(rbtnManual);
		pnlLottoForm.add(pnlLottoNum, BorderLayout.NORTH);
		// ���Ϳ��� �׳� ���̺� �־���� ������
		pnlLottoForm.add(lblMyNum, BorderLayout.CENTER);
		pnlLottoForm.add(pnlRbtn, BorderLayout.SOUTH);
		// Ÿ��Ʋ���� �Ἥ �̻ڰ� ��������
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "Check you Number");
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		pnlLottoForm.setBorder(tBorder);

		// ���� ���� �� ���� �г� �����߱���
		return pnlLottoForm;
	}
	
	private void addListeners() {
		btnStart.addActionListener(this);
		btnReset.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	private void showFrame() {
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
			new LottoResult1();
			setVisible(false);
		} if (src == btnCancel) {
			new StartGame();
			dispose();
		}
	}
}
