package kr.ac.green;

class LottoNums extends MyNums {
	// Bonus ¹øÈ£ INDEX
	public static final int BONUS = 6;

	public LottoNums() {
		setNums(autoSelect(7));
	}

	public LottoNums(Integer... nums) {
		super(nums);
	}

	public Integer getBonusNum() {
		return getNums()[BONUS];
	}
}