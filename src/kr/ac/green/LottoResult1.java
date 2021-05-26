package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LottoResult1 extends JFrame {
	
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	
	private LineBorder border;
	
	public LottoResult1() {
		init();
		addListener();
		setDisplay();
		showFrame();
	}
	
	
	
	private void init() {
		lbl1 = new JLabel("2",JLabel.CENTER);
		lbl2 = new JLabel("13",JLabel.CENTER);
		lbl3 = new JLabel("23",JLabel.CENTER);
		lbl4 = new JLabel("33",JLabel.CENTER);
		lbl5 = new JLabel("42",JLabel.CENTER);
		
		border = new LineBorder(Color.BLACK,2);
	}



	private void addListener() {
		// TODO Auto-generated method stub
		
	}



	private void setDisplay() {
		JPanel pnlMain = new JPanel(new BorderLayout());
		
		JPanel pnlNorth = new JPanel();
		
		JPanel pnlCenter = new JPanel(new FlowLayout());
		
		JPanel pnlSouth = new JPanel(new FlowLayout());
		
		JPanel pnlNum1 = new JPanel(new GridLayout(1,0));
		
		
		
		lbl1.setBorder(border);
		lbl2.setBorder(border);
		lbl3.setBorder(border);
		lbl4.setBorder(border);
		lbl5.setBorder(border);
		
		pnlNum1.add(lbl1);
		pnlNum1.add(lbl2);
		pnlNum1.add(lbl3);
		pnlNum1.add(lbl4);
		pnlNum1.add(lbl5);
		
		pnlCenter.add(pnlNum1);
		
		pnlMain.add(pnlNorth,BorderLayout.NORTH);
		pnlMain.add(pnlCenter,BorderLayout.CENTER);
		pnlMain.add(pnlSouth,BorderLayout.SOUTH);
		
		add(pnlMain);
		
		
		
	}



	private void showFrame() {
		setTitle("Lotto");
		pack();
		setLocation(100, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}



	public static void main(String[] args) {
		new LottoResult1();
	}

}
