package 二叉树;

public class Code_lowestAncestor {
	
	
	// 寻找 二叉树 x 中 a节点 与 b节点的 最低公共祖先
	
	
	/**
	 *  分析 
	 *  x 不是 祖先  
	 *  要信息 左边 发现 的祖先? 右边发现 的祖先?
	 *  x是祖先
	 *  要信息 左边发现 A  右边发现 B 或者 x == B
	 *  	  左边发现B  右边发现A 或者 x == A
	 *  
	 *  整合信息
	 *   1. found A
	 *   2. found B
	 *   3. ancestor 
	 * 
	 */
	
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v, Node l, Node r) {
			value = v;
			left = l;
			right = r;
		}
	}
	
	public static class Info{
		public boolean foundA;
		public boolean foundB;
		public Node ancestor;
		
		public Info(boolean fa, boolean fb, Node an) {
			foundA = fa;
			foundB = fb;
			ancestor = an;
		}
	}
	
	public static Node lowestAncestor(Node head, Node a, Node b) {
		return process(head, a, b).ancestor;
	}
	
	public static Info process(Node x, Node a, Node b) {
		if (x == null) {
			return new Info(false, false, null);
		}
		
		Info  leftInfo = process(x.left, a, b);
		Info rightInfo = process(x.right, a, b);
		
		boolean foundA = (x == a) || leftInfo.foundA || rightInfo.foundA;
		boolean foundB = (x == b) || leftInfo.foundB || rightInfo.foundB;
		Node ans = null;
		if(leftInfo.ancestor != null) {
			ans = leftInfo.ancestor;
		}else if(rightInfo.ancestor != null) {
			ans = rightInfo.ancestor;
		}else {
			if(foundA && foundB) {
				ans = x;
			}
		}
		return new Info(foundA, foundB, ans);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
