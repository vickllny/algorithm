package com.vickllny;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        //广度优先遍历（Breadth-First Search, BFS）
        List<String> list = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(this);

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node == null){
                    continue;
                }
                list.add(String.valueOf(node.val));
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }

        return String.join(",", list);
    }

    /**
     * 先序递归
     * @param root
     */
    public static void preOrderRecur(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    /**
     * 先序非递归
     * @param root
     */
    public static void preOrderUnRecur(TreeNode root){
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        stack.push(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            if(cur != null){
                System.out.print(cur.val + " ");
                //先右后左
                if(cur.right != null) stack.push(cur.right);
                if(cur.left != null) stack.push(cur.left);
            }
        }
        System.out.print(" ");

    }

    /**
     * 中序递归
     * @param root
     */
    public static void inOrderRecur(TreeNode root){
        if(root == null){
            return;
        }
        inOrderRecur(root.left);
        System.out.print(root.val + " ");
        inOrderRecur(root.right);
    }

    /**
     * 中序非递归
     * @param root
     */
    public static void inOrderUnRecur(TreeNode root){
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
        }
        System.out.println(" ");
    }

    public static void postOrderRecur(TreeNode root){
        if(root == null){
            return;
        }
        postOrderRecur(root.left);
        postOrderRecur(root.right);
        System.out.print(root.val + " ");
    }

    public static void postOrderUnRecur(TreeNode root){
        if(root == null){
            return;
        }
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            if(node != null){
                stack2.push(node);
                //先右后左
                if(node.left != null) stack1.push(node.left);
                if(node.right != null) stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop().val + " ");
        }

        System.out.println(" ");
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
        //递归序 3 9 9 9 3 20 15 15 15 20 7 7 7 20 3
        /**
         *           3
         *     9          20
         *           15         7
         *
         *
         *
         *
         */

        //先序：第一次出现的元素顺序 3 9 20 15 7
        //中序：第二次出现时打印 9 3 15 20 7
        //后序：第三次出现时打印顺序 9 15 7 20 3

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);


        root.right = new TreeNode(3);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(10);

        /**
         *                  1
         *            2             3
         *       4        5       8    9
         *              6    7             10
         */

        preOrderRecur(root);
        System.out.println(" ");
        inOrderRecur(root);
//        preOrderUnRecur(root);
//        System.out.println(" ");
//        postOrderRecur(root);
//        System.out.println(" ");
//        postOrderUnRecur(root);
    }

}
