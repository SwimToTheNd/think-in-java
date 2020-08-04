package com.me.thinkinginjava.chapter0_javabase.java_facetest;

/**
 * 在一个已经排序好的数组中找出一个数字的重复出现的次数
 * 方法：
 * <p>
 * 1. 先用二分找第一次查找到的位置 , 再分别判断在它前面出现的次数和后面出现的次数
 * <p>
 * 2. 用二分找出第一次出现的位置，找出第二次出现的位置，两者相减+1为重复出现次数
 * @author BloodFly
 *
 */
public class Test_02_findRepeatData {

	public static void main(String[] args) {
		int arr[] = { 0, 0, 1, 1, 1, 5, 5, 6, 7, 7, 22, 22 };
		System.out.println(System.currentTimeMillis());
		int ret = coutRepeatTimes(arr, 22);
		System.out.println(System.currentTimeMillis());
		switch (ret) {
			case -1:
				System.out.println("没有找到！");
				break;
			default:
				System.out.println("重复的次数为：" + ret);
				break;
		}
	}

	/**
	 * 笨办法 1. 先用二分找第一次查找到的位置 2. 再分别判断在它前面出现的次数和后面出现的次数
	 *
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int coutRepeatTimes(int[] arr, int value) {
		int low, up, mid, len, count, i, j;
		len = arr.length - 1;
		low = 0;
		count = 0;
		up = len;
		mid = (low + up) / 2;
		System.out.println("low:" + low + " up:" + up + " mid:" + mid);
		while (arr[mid] != value) {
			if (arr[mid] > value) {
				up = mid - 1;
			} else {
				low = mid + 1;
			}
			mid = (low + up) / 2;
			if (low == up && arr[mid] != value) {
				return -1;
			}
			System.out.println("low:" + low + " up:" + up + " mid:" + mid);
		}
		count++;
		i = mid - 1;
		j = mid + 1;
		boolean iflg = false;
		boolean jflg = false;
		while (true) {
			if (i >= 0) {
				if (arr[i] == value) {
					count++;
					i--;
				} else {
					iflg = true;
				}
			} else {
				iflg = true;
			}
			if (j <= len) {
				if (arr[j] == value) {
					count++;
					j++;
				} else {
					jflg = true;
				}
			} else {
				jflg = true;
			}
			if (iflg && jflg) {
				break;
			}
		}
		return count;
	}

}
