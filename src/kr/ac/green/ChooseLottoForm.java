/*
 * 이 클래스 제가 만들었어요!!
 */

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
	// 체크 박스 배열.. 레이블로 해도 되용
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
		lblInfo = new JLabel("설명");
		
		btnStart = new JButton("시작");
		btnCancel = new JButton("취소");
		btnReset = new JButton("리셋");
	}
	
	private void setDiplay() {
		// 패널서쪽, 설명란
		JPanel pnlWest = new JPanel();
		pnlWest.add(lblInfo);
		
		// 패널센터, 로또 번호와 자동여부 선택
		JPanel pnlCenter = new JPanel(new GridLayout(1, 0));
		for (int i = 0; i < count; i++) {
			pnlCenter.add(getPnl());
		}
		
		// 패널남쪽, 버튼 3가지
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// 패널버튼, 버튼 3가지 한 레이블에 담았어요
		JPanel pnlbtn = new JPanel();
		pnlbtn.add(btnStart);
		pnlbtn.add(btnReset);
		pnlbtn.add(btnCancel);
		pnlSouth.add(pnlbtn);
		
		// 테두리 공백 필요 할 수도 있을거 같아서 메인에 달았어요
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlWest, BorderLayout.WEST);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		
		add(pnlMain, BorderLayout.CENTER);
	}
	
	// 로또 번호 선택하는 패널 생성하는 메소드에요
	// 여러개가 필요해서 메소드에 넣고 호출하게 만들었어요..
	private JPanel getPnl() {
		// 체크박스 배열로 했는데 1로 보이게 어케할지
		// 모르겠어요 배열이 0부터 시작이라서 박스번호가 0부터 시작해요
		
		// 그리고 위에 init() 메소드에 cb 넣어서하니까
		// 값이 한번밖에 안되서 계속 만들어야할거같아서
		// 체크박스랑 각종 필요한 버튼들도 여기넣고 메소드 호출되면
		// 계속 생성되게 했어요.. 이게맞는건지..
		
		// 체크박스 크기 0~44까지로 배열 생성
		cb = new JCheckBox[45];
		
		// 반복문에 넣고 체크박스 계속만들어요
		for (int i = 0; i < 45; i++) {
			int n = i + 1;
			String text = String.valueOf(n); 
			cb[i] = new JCheckBox(text);
		}
		
		// JRadioButton 만들구요, 그룹하고..
		rbtnAuto = new JRadioButton("자동", true);
		rbtnManual = new JRadioButton("수동");
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnAuto);
		group.add(rbtnManual);
		
		// 체크박스에서 선택한 번호 보여줄 텍스트필드 만들었구요, 수정불가..
		lblMyNum = new JTextField();
		lblMyNum.setEditable(false);		
		
		// 패널을 보더레이아웃으로 북, 중, 남으로 넣었구요
		JPanel pnlLottoForm = new JPanel(new BorderLayout());
		// 북쪽에 그리드레이아웃으로 로또번호 들어갈 패널에 체크박스 추가
		JPanel pnlLottoNum = new JPanel(new GridLayout(0, 7));
		for (JCheckBox cbs : cb) {
			pnlLottoNum.add(cbs);
		}
		// 남쪽에 자동 수동 설정할 버튼 넣었구요
		JPanel pnlRbtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlRbtn.add(rbtnAuto);
		pnlRbtn.add(rbtnManual);
		pnlLottoForm.add(pnlLottoNum, BorderLayout.NORTH);
		// 센터에는 그냥 레이블 넣었어요 꽉차게
		pnlLottoForm.add(lblMyNum, BorderLayout.CENTER);
		pnlLottoForm.add(pnlRbtn, BorderLayout.SOUTH);
		// 타이틀보더 써서 이쁘게 만들었어요
		TitledBorder tBorder = new TitledBorder(new LineBorder(Color.GRAY, 1), "Check you Number");
		tBorder.setTitleFont(new Font(Font.DIALOG, Font.BOLD, 13));
		pnlLottoForm.setBorder(tBorder);

		// 위에 정보 다 넣은 패널 리턴했구요
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
			// 시작버튼 누르면 결과화면 객체 생성
			new LottoResult1();
			setVisible(false);
		} if (src == btnCancel) {
			new StartGame();
			dispose();
		}
	}
}
