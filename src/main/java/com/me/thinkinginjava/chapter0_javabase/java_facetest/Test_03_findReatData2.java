package com.me.thinkinginjava.chapter0_javabase.java_facetest;

import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 使用二分查找出元素在一个有序数组中第一次和最后一次出现的次数
 *
 * @author BloodFly
 *
 */
public class Test_03_findReatData2 {

	public static void main(String[] args) {
		int[] arr = { 2, 2, 3, 4, 4, 4, 4, 5, 5 };
		int k = 6;
		int firstindex = getFirtIndex(arr, k);
		System.out.println("第一次出现的位置为：" + firstindex);
		int lastindex = getLastIndex(arr, k);
		System.out.println("最后一次出现的位置为：" + lastindex);
		System.out.println("元素：" + k + "一共出现了 " + (lastindex - firstindex + 1));
	}

	/**
	 * 向左边靠近 用普通循环查找目标在数组中第一次出现的位置 如果中间元素>目标，目标在中间元素的右边，将left设置中间元素的下标+1
	 * 其他情况（中间元素<目标或中间元素=目标），将right设置中间元素的下标 当left=right时，如果中间元素不等于目标，则retrun-1
	 * 否则return left
	 *
	 * @param arr
	 * @param i
	 * @return
	 */
	private static int getFirtIndex(int[] arr, int k) {
		int left, right, mid;
		int len = arr.length - 1;
		left = mid = 0;
		right = len;
		while (left != right) {
			mid = (left + right) / 2;
			// 目标在中间元素右边
			if (arr[mid] < k) {
				left = mid + 1;
			} else {
				// 目标在中间元素的左边或处于中间元素的位置，需判断前面是否有重复的
				right = mid;
			}
			if (left == right && arr[left] != k) {
				return -1;
			}
		}
		// left要么等于目标元素，要么小于目标元素，肯定为第一次出现的位置
		// 最后等于目标元素
		return left;
	}

	/**
	 * 使用普通函数二分查找法查找元素最后出现的位置 向右边靠近
	 *
	 * @param arr
	 * @param k
	 * @return
	 */
	private static int getLastIndex(int[] arr, int k) {
		int left, right, mid, len;
		len = arr.length;
		left = mid = 0;
		right = len - 1;

		while (left != right) {
			// 保证取到中间靠后的位置
			mid = (left + right + 1) / 2;
			// 目标元素在中间元素的左边时，right = mid -1
			if (arr[mid] > k) {
				right = mid - 1;
			} else {
				left = mid;
			}
			if (left == right && arr[right] != k) {
				return -1;
			}
		}
		return right;
	}
}
