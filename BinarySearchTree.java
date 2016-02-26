import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * Created by Pratik on 2/20/2016.
 */
public class BinarySearchTree {

    Node<Integer> root;
    BinarySearchTree(Integer i) {
        root = new Node<>(i);
    }

    BinarySearchTree() {

    }

    public Node<Integer> makeBinarySearchTree(int[] array) {
        if (array.length > 0) {
            return createBST(array, 0, array.length - 1);
        }
        return null;
    }

    private Node<Integer> createBST(int[] array, int start, int end) {
        if (start <= end) {
            int mid = (start + end)/2;
            Node<Integer> node = new Node<>(array[mid]);
            node.left = createBST(array, start, mid - 1);
            node.right = createBST(array, mid + 1, end);
            return node;
        }
        return null;
    }


    public boolean isValidBST(Node<Integer> root) {
        if (root != null) {
            return checkBST(root, null, null);
        }
        return true;
    }

    private boolean checkBST(Node<Integer> node, Integer min, Integer max) {
        if (node != null) {

            if ((min != null) && (node.data <= min))
                return false;

            if ((max != null) && (node.data > max))
                return false;

            return (checkBST(node.left, min, node.data) && checkBST(node.right, node.data, max));
        }
        return true;
    }

    public ArrayList<LinkedList<Node<Integer>>> levelLL (Node<Integer> root) {
        if (root != null) {
            ArrayList<LinkedList<Node<Integer>>> result = new ArrayList<>();
            createLevelLL(root, result, 0);
            return result;
        }
        return null;
    }

    private void createLevelLL(Node<Integer> node, ArrayList<LinkedList<Node<Integer>>> res, int level) {

        if (node != null) {
            LinkedList<Node<Integer>> list;
            if (res.size() <= level) {
                list = new LinkedList<>();
                res.add(level, list);
            } else {
                list = res.get(level);
            }
            list.add(node);
            createLevelLL(node.left, res, level + 1);
            createLevelLL(node.right, res, level + 1);
        }
    }

    public NodeP<Integer> findSuccessor(NodeP<Integer> root) {
        NodeP<Integer> node = null;
        if (root != null) {
            if (root.right != null) {
                node = root.right;
                while(node.left != null) {
                    node = node.left;
                }
            } else {
                node = root.parent;
                while(root != null && root == node.right) {
                    root = node;
                    node = node.parent;
                }
            }
        }
        return node;
    }


    public Node<Integer> findCommonAn(Node<Integer> root, Node<Integer> p, Node<Integer> q) {

        if (covers(root, p) && covers(root, q)) {
            boolean pOnLeft = covers(root.left, p);
            boolean qOnLeft = covers(root.left, q);

            if (pOnLeft && qOnLeft) {
                return findCommonAn(root.left, p, q);
            } else if (!pOnLeft && !qOnLeft) {
                return findCommonAn(root.right, p, q);
            }
            return root;
        }
        return null;
    }

    private boolean covers(Node root, Node p) {
        if (root != null) {
            if (root == p)
                return true;
            return covers(root.left, p) || covers(root.right, p);
        }
        return false;
    }




    public NodeP<String> practice(NodeP<String> head) {
        NodeP<String> node;
        if (head.right != null) {
            node = head.right;
            while(node.left != null)
                node = node.left;
            return node;
        } else {
            node = head.parent;
            while(node.parent != null && node == node.parent.right) {
                node = node.parent;
            }
            return node;
        }
    }










    public void printInOrder(Node head) {
        if(head != null) {
            printInOrder(head.left);
            System.out.println(head.data);
            printInOrder(head.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binTree = new BinarySearchTree();
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
        binTree.root = binTree.makeBinarySearchTree(array);
        binTree.printInOrder(binTree.root);
//        binTree.root.left.data = 100;
//        System.out.println(binTree.isValidBST(binTree.root));

//        ArrayList<LinkedList<Node<Integer>>> arr = binTree.levelLL(binTree.root);
//
//        System.out.println(arr.size());
//
//        ListIterator<LinkedList<Node<Integer>>> LI1 = arr.listIterator();
//
//        while(LI1.hasNext()) {
//            ListIterator<Node<Integer>> LI2 = LI1.next().listIterator();
//            while(LI2.hasNext()) {
//                System.out.print(LI2.next().data + "->");
//            }
//            System.out.print("NULL\n");
//        }

//        System.out.println(binTree.findCommonAn(binTree.root, binTree.root.left.left, binTree.root.left.right).data);

    }
}

class NodeP<E> {
    E data;
    NodeP<E> left;
    NodeP<E> right;
    NodeP<E> parent;

    NodeP() {

    }


    NodeP(E e, NodeP<E> parent) {
        data = e;
        left = null;
        right = null;
        this.parent = parent;
    }
}
