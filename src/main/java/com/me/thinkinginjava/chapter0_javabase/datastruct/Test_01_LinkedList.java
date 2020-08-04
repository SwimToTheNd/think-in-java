package com.me.thinkinginjava.chapter0_javabase.datastruct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class Test_01_LinkedList {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("112");
		linkedList.add("223");
		linkedList.add("333");
		linkedList.add("443");
		linkedList.add("333");
		linkedList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		Iterator<String> iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("==============");

		// LinkedList<String> linkedList2 = new LinkedList<String>();
		// 1. 使用HashSet去除重复
		// Set<String> set = new HashSet<String>();
		// set.addAll(linkedList);
		// Iterator<String> iterator2 = set.iterator();
		// while (iterator2.hasNext()) {
		// System.out.println(iterator2.next());
		// }
		// System.out.println("==============");
		// linkedList.removeAll(linkedList);
		// iterator = linkedList.iterator();
		// while (iterator.hasNext()) {
		// System.out.println(iterator.next());
		// }
		// System.out.println("==============");
		// linkedList.addAll(set);
		// iterator = linkedList.iterator();
		// while (iterator.hasNext()) {
		// System.out.println(iterator.next());
		// }
		// System.out.println("==============");
		// 2. 使用contains方法
		LinkedList<String> linkedList2 = new LinkedList<String>();
		iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			String tmp = iterator.next();
			if (!linkedList2.contains(tmp)) {
				linkedList2.add(tmp);
			}
		}
		iterator = linkedList2.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		int[] arr = { 11, 2, 5, 33, 2, 4, 5, 11, 3 }; // 9
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i : arr) {
			list.add(i);
		}
		System.out.println(list);
		// list.sort(new Comparator<Integer>() {
		// @Override
		// public int compare(Integer o1, Integer o2) {
		// return o1.compareTo(o2);
		// }
		// });
		// System.out.println("+++++++++++++++++++++++++++");
		// System.out.println(list);

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		bSearch(arr, 0, arr.length - 1, 3, arrayList);
		System.out.println(arrayList);

	}

	// 1 2 3 4 5
	public static void bSearch(int[] arr, int low, int up, int value, ArrayList<Integer> arrayList) {
		int mid = (low + up) / 2;
		System.out.println(low + " " + up + " " + mid);
		if (arr[mid] == value) {
			arrayList.add(mid);
		}
		if (low < mid)
			bSearch(arr, low, mid - 1, value, arrayList);
		if (up > mid)
			bSearch(arr, mid + 1, up, value, arrayList);
	}

}
