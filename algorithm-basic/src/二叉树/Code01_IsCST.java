package 二叉树;


public class Code01_IsCST {
	
	
	/**
	 * 使用二叉树递归套路 判断 二叉树是否是完全二叉树
	 * @param args
	 */
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;
		
		public Info(boolean f, boolean c, int h) {
			isFull = f;
			isCBT = c;
			height = h;
		}
	}
	
	
	
	public static boolean isCST(Node head) {
		return process(head).isCBT;
	}
	
	public static Info process(Node x) {
		
		if(x == null) {
			return new Info(true, true, 0);
		}
		
	    Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		
		boolean isCST = isFull;
//		boolean isCST = false;
		
//		if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
//			isCST = true;
//		}
		
		if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
			isCST = true;
		}
		if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
			isCST = true;
		}
		if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
			isCST = true;
		}
		return new Info(isFull, isCST, height);
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
