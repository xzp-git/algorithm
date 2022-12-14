package 二叉树;

import java.util.ArrayList;

public class Code02_IsBST {
	
	/**
	 * 
	 * @author xzp03
	 * 判断是否是搜索二叉树
	 * 
	 * 搜索二叉树(x)的定义
	 * 	1.左子树是搜索二叉树   右子树是搜索二叉树
	 * 	2.左子树的最大值小于x  右子树的最小值大于x
	 * 
	 * 一、 分析
	 * 根据定义我需要
	 * 从左子树收取两个信息
	 * 	1. 是否是二叉搜索树
	 * 	2. 最大值
	 * 从右子树收取两个信息
	 * 	1. 是否是二叉搜索树
	 * 	2. 最小值
	 * 
	 * 
	 * 由于递归套路 需要要求对每一个树收取的信息都是一样的
	 * 
	 * 所以取公共的最大信息
	 * 
	 * 即
	 * 从左右子树都收取相同的信息
	 * 1. 是否是二叉搜索树
	 * 2. 最大值
	 * 3. 最小值
	 * 
	 * 如果在解题过程中发现，base case的返回值不好处理，可以返回 null 
	 * 让上游处理，上游处理的时候需要小心。例如 本题
	 *
	 * 二、解题
	 * 		
	 * 1. 定义信息体
	 * 
	 * 2. 处理base case
	 *
	 */
	
	public static class Info {
		public boolean isBST;
		public int max;
		public int min;
		
		public Info(boolean i, int ma, int mi) {
			isBST = i;
			max = ma;
			min = mi;
		}
	}
	
	public static boolean isBST (Node head) {
		if(head == null) {
			return true;
		}
		
		return process(head).isBST;
	}
	
	public static Info process(Node x) {
		
		
		if(x == null) {
			return null;
		}
		
//		向左右树要信息
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		
//		构造自己的信息返回
		int max = x.value;
		int min = x.value;
		boolean isBST = true;
		if(leftInfo != null) {
			max = Math.max(leftInfo.max, max);
			min = Math.min(leftInfo.min, min);
			if(!leftInfo.isBST || leftInfo.max >= x.value) {
				isBST = false;
			}
		}
		if(rightInfo != null) {
			max = Math.max(rightInfo.max, max);
			min = Math.min(rightInfo.min, min);
			if(!rightInfo.isBST || rightInfo.min <= x.value) {
				isBST = false;
			}
		}
		
		return new Info(isBST, max, min);
		
	}
	
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}
	public static boolean isBST1(Node head) {
		if (head == null) {
			return true;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return false;
			}
		}
		return true;
	}



		// for test
		public static Node generateRandomBST(int maxLevel, int maxValue) {
			return generate(1, maxLevel, maxValue);
		}

		// for test
		public static Node generate(int level, int maxLevel, int maxValue) {
			if (level > maxLevel || Math.random() < 0.5) {
				return null;
			}
			Node head = new Node((int) (Math.random() * maxValue));
			head.left = generate(level + 1, maxLevel, maxValue);
			head.right = generate(level + 1, maxLevel, maxValue);
			return head;
		}

		public static void main(String[] args) {
			int maxLevel = 4;
			int maxValue = 100;
			int testTimes = 1000000;
			for (int i = 0; i < testTimes; i++) {
				Node head = generateRandomBST(maxLevel, maxValue);
				if (isBST1(head) != isBST(head)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}

}
