package kr.ac.green;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

class MyNums {
	// 번호 배열
	private Integer[] nums;
	private HashSet<Integer> semiAutoNums;

	// 자동
	public MyNums() {
		nums = autoSelect(6);
	}

	public MyNums(Integer... nums) {
		Arrays.sort(nums);
		this.nums = nums;
	}

	public MyNums(List<Integer> selectNums) {
		semiAutoNums = autoSelect(selectNums);
		System.out.println("3 : " + semiAutoNums);
	}

	// public void init(ArrayList<String> selected) {
	// semiAutoNums = new HashSet<String>();
	// if (selected.size() != 0) {
	// for (int i = 0; i < selected.size(); i++) {
	// semiAutoNums.add(selected.get(i));
	// }
	// }
	// Random r = new Random();
	// for (int i = 0; semiAutoNums.size() < 6; i++) {
	// semiAutoNums.add(String.valueOf(r.nextInt(45) + 1));
	// }
	// strRandom6set = semiAutoNums.toArray(new String[0]);
	// random6 = new Vector<String>(Arrays.asList(strRandom6set));
	// strRandom6 = random6.toArray(new String[0]);
	// }

	// 번호 자동선택
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

	protected HashSet<Integer> autoSelect(List<Integer> selectNums) {
		semiAutoNums = new HashSet<Integer>();

		for (int i = 0; i < selectNums.size(); i++) {
			semiAutoNums.add(selectNums.get(i));
			System.out.println("1 : " + semiAutoNums);
		}

		Random r = new Random();
		while (semiAutoNums.size() < 6) {
			semiAutoNums.add(r.nextInt(45) + 1);
		}
		System.out.println("2 : " + semiAutoNums);
		return semiAutoNums;
	}

	public Integer[] getNums() {
		return nums;
	}

	public HashSet<Integer> getSemiAutoNums() {
		return semiAutoNums;
	}

	public void setNums(Integer... nums) {
		this.nums = nums;
	}

	@Override
	public String toString() {
		return Arrays.toString(nums);
	}
}
