import java.util.Scanner;

public class Main {
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    static boolean search(Node root, int val) {
        while (root != null) {
            if (val == root.val) return true;
            root = val < root.val ? root.left : root.right;
        }
        return false;
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root = null;
        while (sc.hasNextInt()) {
            int op = sc.nextInt();
            if (op == 1) {
                root = insert(root, sc.nextInt());
            } else if (op == 2) {
                System.out.println(search(root, sc.nextInt()) ? "Found" : "Not Found");
            } else if (op == 3) {
                inorder(root);
                System.out.println();
            } else if (op == 4) {
                break;
            }
        }
        sc.close();
    }
}
