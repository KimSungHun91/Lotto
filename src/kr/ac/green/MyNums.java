package kr.ac.green;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

class MyNums {
	// ��ȣ �迭
	private Integer[] nums;

	// �ڵ�
	public MyNums() {
		nums = autoSelect(6);
	}

	public MyNums(Integer... nums) {
		Arrays.sort(nums);
		this.nums = nums;
	}

	// ��ȣ �ڵ�����
	protected Integer[] autoSelect(int count) {
		Random r = new Random();
		HashSet<Integer> set = new HashSet<Integer>();

		while (set.size() < count) {
			set.add(r.nextInt(45) + 1);
		}
		Integer[] nums = set.toArray(new Integer[0]);
		Arrays.sort(nums, 0, 6);
		return nums;
	}

	public Integer[] getNums() {
		return nums;
	}

	public void setNums(Integer... nums) {
		this.nums = nums;
	}

	@Override
	public String toString() {
		return Arrays.toString(nums);
	}
}
