package kr.ac.green;

import java.util.List;

public class LottoGameInfo {                          
	private int no;                                   
	private boolean auto;                             
	private List<Integer> numbers;                    

	public LottoGameInfo(int no) {
		this.no = no;
	}
	
	public int getNo() {                              
		return no;                                    
	}                                                 
	public void setNo(int no) {                       
		this.no = no;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public List<Integer> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
}
