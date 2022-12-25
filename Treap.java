/**
 * @author Kuo Zhang
 */

import java.util.Random;
import java.util.Stack;


public class Treap<E extends Comparable<E>> {
    protected static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;


        public Node(E data, int priority) {
            if (data == null) {
                throw new IllegalArgumentException("Invalid Data");
            } else {
                this.data = data;
                this.priority = priority;
                this.left = null;
                this.right = null;
            }
        }


        public Node<E> rotateRight() {
            Node<E> current = this;
            if (this.left == null) {
                return current;
            } else {
                Node<E> child = current.left;
                current.left = child.right;
                child.right = current;
                return child;

            }
        }


        public Node<E> rotateLeft() {
            Node<E> current = this;
            if (this.right == null) {
                return current;
            } else {
                Node<E> child = current.right;
                current.right = child.left;
                child.left = current;
                return child;
            }
        }

        public String toString() {
            return "(key=" + this.data + ", priority=" + this.priority + ")";
        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    /*
     * creates an empty treap
     */
    public Treap() {
        priorityGenerator = new Random();
        root = null;
    }


    public Treap(long seed) {
        this();
        this.priorityGenerator.setSeed(seed);
    }


    boolean add(E key, int priority) {
        if (root == null) {
            root = new Node<E>(key, priority);
            return true;
        } else {
            Node<E> temp = new Node<E>(key, priority);
            Stack<Node<E>> stack = new Stack<Node<E>>();
            Node<E> current = root;
            while (current != null) {
                int comparison = current.data.compareTo(key);
                if (comparison == 0) {
                    return false;
                } else {
                    if (comparison < 0) {
                        stack.push(current);
                        if (current.right == null) {
                            current.right = temp;
                            reheap(temp, stack);
                            return true;
                        } else {
                            current = current.right;
                        }
                    } else {
                        stack.push(current);
                        if (current.left == null) {
                            current.left = temp;
                            reheap(temp, stack);
                            return true;
                        } else {
                            current = current.left;
                        }
                    }
                }
            }
            return false;
        }
    }

    boolean add(E key) {
        return add(key, priorityGenerator.nextInt());
    }

    /*
     * restores the heap invariant
     */
    public void reheap(Node<E> current, Stack<Node<E>> tool) {
        while (!tool.isEmpty()) {
            Node<E> parent = tool.pop();
            if (parent.priority < current.priority) {
                if (parent.data.compareTo(current.data) > 0) {
                    current = parent.rotateRight();
                } else {
                    current = parent.rotateLeft();
                }
                if (!tool.isEmpty()) {
                    if (tool.peek().left == parent) {
                        tool.peek().left = current;
                    } else {
                        tool.peek().right = current;
                    }
                } else {
                    this.root = current;
                }
            } else {
                break;
            }
        }
    }

    public boolean delete(E key) {
        if (root == null || !find(key)) {
            return false;
        } else {
            root = delete(root, key);
            return true;
        }
    }


    private Node<E> delete(Node<E> root, E key) {
        if (root == null) {
            return root;
        } else {
            if (root.data.compareTo(key) < 0) {
                root.right = delete(root.right, key);
            } else {
                if (root.data.compareTo(key) > 0) {
                    root.left = delete(root.left, key);
                } else {
                    if (root.right == null) {
                        root = root.left;
                    } else if (root.left == null) {
                        root = root.right;
                    } else {
                        if (root.right.priority < root.right.priority) {
                            root = root.rotateRight();
                            root.right = delete(root.right, key);
                        } else {
                            root = root.rotateLeft();
                            root.left = delete(root.left, key);
                        }
                    }
                }
            }
        }
        return root;
    }

    /*
     * Finds a node with the given key in the treap rooted
     * at root and returns true if it finds it and false otherwise.
     */
    private boolean find(Node<E> root, E key) {
        if (root == null) {
            return false;
        } else {
            int compResult = key.compareTo(root.data);
            if (compResult == 0) {
                return true;
            } else if (compResult < 0) {
                return find(root.left, key);
            } else {
                return find(root.right, key);
            }
        }
    }

    /*
     * Finds a node with the given key in the treap and returns true if it
     * finds it and false otherwise.
     */
    public boolean find(E key) {
        if (find(root, key) != false) {
            return find(root, key);
        } else {
            return false;
        }
    }

    /*
     * Carries out a preorder traversal of the tree and returns a representation of the nodes as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
            return;
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();
        testTree.add(4, 19);
        testTree.add(6, 70);
        testTree.add(1, 84);
        testTree.add(3, 12);
        testTree.add(5, 83);
        testTree.add(7, 26);
        System.out.println(testTree);
    }
}
