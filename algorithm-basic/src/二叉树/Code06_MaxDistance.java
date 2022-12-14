package 二叉树;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class Code06_MaxDistance {
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}
	public static int maxDistance1(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = getPrelist(head);
		HashMap<Node, Node> parentMap = getParentMap(head);
		int max = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
			}
		}
		return max;
	}

	public static ArrayList<Node> getPrelist(Node head) {
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		return arr;
	}

	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static HashMap<Node, Node> getParentMap(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		map.put(head, null);
		fillParentMap(head, map);
		return map;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
		HashSet<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		Node lowestAncestor = cur;
		cur = o1;
		int distance1 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance1++;
		}
		cur = o2;
		int distance2 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance2++;
		}
		return distance1 + distance2 - 1;
	}
	
	/**
	 * 	找出二叉树x整棵树两个节点间的最大距离
	 *  分析 
	 * 与x节点无关
	 * 	1）左树最大的距离
	 * 	2）右树最大的距离
	 * 与x节点有关
	 * 	3) x左树与x最远的距离（左树高度）  + x右树与x最远的距离 （右树高度）+ 1
	 *	共三种可能性  三者求最大就是 节点间的最大距离
	 * 解题
	 * 	通过分析  用二叉树递归套路 向左树和右树提要求
	 * 	左树  1. 最大距离  2. 高度
	 *	右树  1. 最大距离  2.高度
	 */
	
	public static class Info{
		public int height;
		public int maxDistance;
		
		public Info(int h, int m) {
			height = h;
			maxDistance = m;
		}
	}
	
	public static int maxDistance(Node head) {
		return process(head).maxDistance;
	}
	
	public static Info process(Node x) {
		if(x == null) { 
			return new Info(0, 0);
		}
		
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height+1);
		
		return new Info(height, maxDistance);
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
				if (maxDistance1(head) != maxDistance(head)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}
	
	
	
	
}
