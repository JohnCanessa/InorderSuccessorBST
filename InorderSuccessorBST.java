import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Represents a node in the binary tree.
 */
class TreeNode {

    // **** class members ****
    int val;
    TreeNode left;
    TreeNode right;

    // **** constructor(s) ****
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // **** ****
    @Override
    public String toString() {
        return "" + this.val;
    }
}


/**
 * LeetCode 285 Inorder Successor in BST
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorBST {


    /**
     * Populate binary tree using values from a first breadth search traversal.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static TreeNode populateTree(Integer[] arr) {

        // **** sanity checks ****
        if (arr == null || arr.length == 0) return null;


        // **** initialization ****
        Queue<TreeNode> treeNodeQ = new LinkedList<>();
        Queue<Integer> integerQ = new LinkedList<>();

        // **** populate integer queue ****
        for (int i = 1; i < arr.length; i++) {
            integerQ.offer(arr[i]);
        }

        // **** prime tree node queue ****
        TreeNode treeNode = new TreeNode(arr[0]);
        treeNodeQ.offer(treeNode);

        // **** ****
        while (!integerQ.isEmpty()) {

            // **** ****
            Integer leftVal = integerQ.isEmpty() ? null : integerQ.poll();
            Integer rightVal = integerQ.isEmpty() ? null : integerQ.poll();
            TreeNode current = treeNodeQ.poll();

            // **** ****
            if (leftVal != null) {
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                treeNodeQ.offer(left);
            }

            // **** ****
            if (rightVal != null) {
                TreeNode right = new TreeNode(rightVal);
                current.right = right;
                treeNodeQ.offer(right);
            }
        }

        // **** return binary tree ****
        return treeNode;
    }


    /**
     * Display node values in a binary tree in order traversal. 
     * Recursive approach.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.toString() + " ");
            inOrder(root.right);
        }
    }


    /**
     * Display node values in a binary tree in reverse order. 
     * Recursive approach.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void reverseOrder(TreeNode root) {
        if (root != null) {
            reverseOrder(root.right);
            System.out.print(root.toString() + " ");
            reverseOrder(root.left);
        }
    }


    /**
     * Find node with specified value.
     * Entry point for recursive call.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static TreeNode find(TreeNode root, int pVal) {

        // **** sanity check(s) ****

        // **** initialization ****
        TreeNode[] arr = new TreeNode[1];

        // **** start recursion ****
        find(root, pVal, arr);

        // **** return result ****
        return arr[0];
    }


    /**
     * Find node with specified value.
     * Recursive call.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void find(TreeNode root, int pVal, TreeNode[] arr) {
        if (root != null) {

            // **** recurse left ****
            find(root.left, pVal, arr);

            // **** check if node with specified value was found ****
            if (root.val == pVal) {
                arr[0] = root;
            }

            // **** recurse right ****
            find(root.right, pVal, arr);
        }
    }

    
    /**
     * Find the in-order successor of the specified node in the BST.
     * Entry point for recursive call.
     * 
     * Runtime: O(n * log(n)) - Space: O(1)
     * 
     * Runtime: 2 ms, faster than 50.36% of Java online submissions.
     * Memory Usage: 40 MB, less than 30.86% of Java online submissions.
     */
    static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        // **** initialization ****
        TreeNode[] arr = new TreeNode[1];

        // **** find inorder successor ****
        inorderSuccessor(root, p, arr);

        // **** return successor to p (if present) ****
        if (p.val != arr[0].val) return arr[0];
        else return null;
    }


    /**
     * Find the in-order successor of the specified node in the BST.
     * Returns inorder successor of specified node.
     * Recursive call.
     * 
     * Runtime: O(n * log(n)) - Space: O(1)
     */
    static private void inorderSuccessor(TreeNode root, TreeNode p, TreeNode[] arr) {
        if (root != null) {

            // **** traverse left sub tree ****
            inorderSuccessor(root.left, p, arr);

            // **** flag we found node p (save it) ****
            if (root.val == p.val) {
                arr[0] = root;

                // ???? ????
                System.out.println("<<< found p: " + arr[0].val);
            } 
            
            // **** flag we found next node after p (replace saved node) ****
            else if (arr[0] != null && arr[0].val == p.val) {
                arr[0] = root;

                // ???? ????
                System.out.println("<<< p's successor: " + arr[0].val);
            }

            // **** traverse right sub tree ****
            inorderSuccessor(root.right, p, arr);
        }
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open a buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read the nodes provided in a breadth first serach format ***
        String[] strArr = br.readLine().trim().split(",");

        // *** read the value of the node of interest ****
        int val = Integer.parseInt(br.readLine().trim());

        // **** close the buffered reader ****
        br.close();

        // **** create and populate integer array (may contain null) ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null"))
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** create and populate binary tree ****
        TreeNode root = populateTree(arr);

        // ???? ????
        System.out.print("main <<< inOrder: ");
        inOrder(root);
        System.out.println();

        // ???? ????
        // System.out.print("main <<< reverseOrder: ");
        // reverseOrder(root);
        // System.out.println();

        // ???? ????
        System.out.println("main <<< pVal: " + val);

        // **** look for the specified node in the BST 
        //      (should always be found) ****
        TreeNode p = find(root, val);

        // ???? ????
        System.out.println("main <<< p: " + p.val);

        // **** compute and display the result ****
        System.out.println("main <<< output: " + inorderSuccessor(root, p));
    }
}