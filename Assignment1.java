import java.util.*;

class BSTNode {
    BSTNode left, right;
    int data;

    public BSTNode(int data) {
        this.data = data;
    }
}

public class Assignment1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BSTNode root = createTree();

        System.out.println();
        System.out.print("INORDER of entered tree: ");
        inOrder(root);
        System.out.println();

        String cont;
        do {
            int choice;
            System.out.println("Enter 1 to Insert");
            System.out.println("Enter 2 to Delete");
            System.out.println("Enter 3 to Display (InOrder)");
            System.out.println("Enter 4 to Search");
            System.out.println("Enter 5 for BFS (Level Order Transversal)");
            System.out.println("Enter 6 for PreOrder");
            System.out.println("Enter 7 for PostOrder");
            System.out.println("Enter 8 to get Minimum Element");
            System.out.println("Enter 9 to get Maximum Element");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    while (true) {
                        // Creation of BST
                        int data;
                        System.out.print("Enter data: "); // Inputting data
                        data = sc.nextInt();
                        if (data == -1)
                            break;
                        root = insertNode(root, data);
                    }
                    break;

                case 2:
                    deleteKey(sc, root);
                    break;

                case 3:
                    System.out.println();
                    System.out.print("InOrder traversal: \n");
                    inOrder(root);
                    System.out.println();
                    break;

                case 4:
                    searchKey(sc, root);
                    break;

                case 5:

                    break;

                case 6:
                    System.out.println();
                    System.out.println("PreOrder Traversal \n");
                    preOrder(root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println();
                    System.out.println("PostOrder Traversal \n");
                    postOrder(root);
                    System.out.println();
                    break;

                case 8:
                    System.out.println();
                    System.out.println("Minimum Element: " + minElementRec(root));

                case 9:
                    System.out.println();
                    System.out.println("Maximum Element: " + maxElementRec(root));
            }

            System.out.println();
            sc.nextLine();
            System.out.print("Do you want to continue: ");
            cont = sc.nextLine();

            if (!cont.equalsIgnoreCase("y")) {
                System.out.println("THANKYOU");
            }

            System.out.println();
        } while (Objects.equals(cont, "y") || Objects.equals(cont, "Y"));
    }

    // recursive
    static void deleteKey(Scanner sc, BSTNode root) {
        int del;
        System.out.print("Enter element to be deleted: ");
        del = sc.nextInt();
        System.out.println();
        System.out.print("Before Deletion Inorder: ");
        inOrder(root);
        System.out.println();
        System.out.print("After Deletion Inorder: ");
        deleteBSTUtil(root, del);
        inOrder(root);
        System.out.println();
    }

    // recursive
    static void searchKey(Scanner sc, BSTNode root) {
        int key;
        System.out.print("Enter key: ");
        key = sc.nextInt();
        if (searchBSTUtil(root, key) != null)
            System.out.println("Key exists");
        else
            System.out.println("Key doesn't exist");
        System.out.println();
    }

    static BSTNode createTree() { // This fn is used to create Tree RECURSIVELY
        Scanner sc = new Scanner(System.in);
        Integer data = null;

        BSTNode root = null; // Declaration of NODE

        System.out.print("Enter data: "); // Inputting data
        data = sc.nextInt();
        if (data == -1)
            return null;
        root = new BSTNode(data); // Creation of ROOT NODE

        while (true) { // Creation of BST
            System.out.print("Enter data: "); // Inputting data
            data = sc.nextInt();
            if (data == -1)
                break;
            root = insertNode(root, data);
        }
        return root;
    }

    // recursive
    static BSTNode insertNode(BSTNode root, int val) {
        if (root == null)
            return new BSTNode(val); // Base case of inserting node
        if (val < root.data)
            root.left = insertNode(root.left, val); // If val < root.data then we recursively call Left Sub Tree
        else
            root.right = insertNode(root.right, val); // If val > root.data then we recursively call Right Sub Tree
        return root;
    }

    // recursive
    static void inOrder(BSTNode root) { // INORDER TRAVERSAL: L->N->R
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    // recursive
    static void preOrder(BSTNode root) {
        if (root == null)
            return;

        System.out.print(root.data + "  ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // recursive
    static void postOrder(BSTNode root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + "  ");
    }

    // recursive
    static int minElementRec(BSTNode root) {
        if (root.right == null)
            return root.data;
        return minElementRec(root.right);
    }

    // recursive
    static int maxElementRec(BSTNode root) {
        if (root.left == null)
            return root.data;
        return maxElementRec(root.left);
    }

    // iterative
    static void insertIter(BSTNode root, int key) {

        if (root == null) {
            root = new BSTNode(key);
            return;
        }
        BSTNode tempNode = root;
        while (tempNode != null) {
            if (tempNode.data == key) {
                break;
            } else if (key < tempNode.data) {
                if (tempNode.left == null)
                    tempNode.left = new BSTNode(key);
                else
                    tempNode = tempNode.left;
            } else {
                if (tempNode.right == null)
                    tempNode.right = new BSTNode(key);
                else
                    tempNode = tempNode.right;
            }
        }
    }

    



    // iterative
    static boolean searchKeyIter(BSTNode root, int key) {
        BSTNode tempNode = root;
        while (tempNode != null) {
            if (tempNode.data == key)
                return true;
            else if (tempNode.data < key) {
                tempNode = tempNode.right;
            } else {
                tempNode = tempNode.left;
            }
        }
        return false;
    }

    // iterative
    static int minElement(BSTNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // iterative
    static int maxElement(BSTNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    static BSTNode searchBSTUtil(BSTNode root, int key) { // Searching in BST

        // Base Case
        if (root == null)
            return null;

        // Return key if found
        if (root.data == key)
            return root;

        // If key is less than root then Search in Left of BST
        if (root.data > key)
            return searchBSTUtil(root.left, key);

        // Else search in Right of BST
        return searchBSTUtil(root.right, key);
    }

    static BSTNode deleteBSTUtil(BSTNode root, int key) {
        if (key < root.data)
            root.left = deleteBSTUtil(root.left, key);
        else if (key > root.data)
            root.right = deleteBSTUtil(root.right, key);
        else {
            if (root.left == null) {
                BSTNode temp = root.right;
                root = null;
                return temp;
            } else if (root.right == null) {
                BSTNode temp = root.left;
                root = null;
                return temp;
            } else {
                BSTNode temp = inorderSuccessor(root.right);
                root.data = temp.data;
                root.right = deleteBSTUtil(root.right, temp.data);
            }
        }
        return root;
    }

    static BSTNode inorderSuccessor(BSTNode root) {
        BSTNode current = root;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

}
