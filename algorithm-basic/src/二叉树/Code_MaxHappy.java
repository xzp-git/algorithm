package 二叉树;

import java.util.List;
import java.util.ArrayList;

public class Code_MaxHappy {
	
	
	
	/*
	 * 
	 * 多叉树计算快乐值
	 *  邀请了 上级就不能邀请他的下级
	 *  对于某个节点 x 他的最大值 有两个可能性
	 *  1 x 来的快乐值  x来的快乐值由 他下级 不来的快乐值之和
	 *  2 x 不来的快乐值 ------那么他的下级可以来 也可以不来 
	 *  
	 */
	
	public static class Employee{
		public int happy;
		public List<Employee> nexts;
		
		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}
	}
	
	public static int maxHappy(Employee boss) {
		Info allInfo = process(boss);
		return Math.max(allInfo.no, allInfo.yes);
	}
	
	public static class Info{
		public int no;
		public int yes;
		
		public Info(int n, int y) {
			no = n;
			yes = y;
		}
	}
	
	public static Info process(Employee x) {
		if(x == null) {
			return new Info(0, 0);
		}
		int no = 0;
		int yes = x.happy;
		
		for(Employee next : x.nexts) {
			Info nextInfo = process(next);
			no += Math.max(nextInfo.no, nextInfo.yes);
			yes += nextInfo.no;
		}
		return new Info(no, yes);
	}
	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	// 当前来到的节点叫cur，
	// up表示cur的上级是否来，
	// 该函数含义：
	// 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	// 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	public static int process1(Employee cur, boolean up) {
		if (up) { // 如果cur的上级来的话，cur没得选，只能不来
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}
	

	// for test
		public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
			if (Math.random() < 0.02) {
				return null;
			}
			Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
			genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
			return boss;
		}

		// for test
		public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
			if (level > maxLevel) {
				return;
			}
			int nextsSize = (int) (Math.random() * (maxNexts + 1));
			for (int i = 0; i < nextsSize; i++) {
				Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
				e.nexts.add(next);
				genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
			}
		}

		public static void main(String[] args) {
			int maxLevel = 4;
			int maxNexts = 7;
			int maxHappy = 100;
			int testTimes = 100000;
			for (int i = 0; i < testTimes; i++) {
				Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
				if (maxHappy1(boss) != maxHappy(boss)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}

}
