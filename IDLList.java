import java.util.ArrayList;

/**
 * @author Kuo Zhang
 */
public class IDLList<E> {
    //private Node class for Nodes
    private class Node<E> {
        //field for inner class
        E data;
        Node<E> next;
        Node<E> prev;

        //constructor
        public Node(E elem) {
            this.data = elem;
            this.prev = null;
            this.next = null;
        }

        //constructor
        public Node(E elem, Node<E> prev, Node<E> next) {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    //IDLlist field with head pointer tail pointer size and arraylist
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;
    //constructor for IDLList
    public IDLList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<>();
    }

    //add method to add new node
    public boolean add(int index, E elem) {
        //index check, if index is illegal
        if (index >= 0 && index <= size) {  // if index is 0 insert at head.
            if (index == 0) {
                add(elem);
            } else if (index == size)  // index is current length insert at tall
            {
                append(elem);
            } else {
                // get current node by index provided
                Node<E> currentNode = indices.get(index);
                //node have to replace by current node
                Node<E> replaceNode = new Node<>(elem);
                //since replacenode have to take currentnode's place. Have to set its prev next to currentNode prev and next
                replaceNode.next = currentNode;  //replaceNode next is currentNode
                replaceNode.prev = currentNode.prev;//replaceNode previous is currentNode.prev
                currentNode.prev.next = replaceNode; //prevs node of currentNode next node have to be replaceNode
                currentNode.prev = replaceNode; //Then currentnode prev replaced by replacenode
                size++; //size update
                indices.add(index, replaceNode); //add new node to the list
                return true;
            }
        }
        return false;
    }

    // Method add node to the head by element
    public boolean add(E elem) {
        // create a new node at head position
        Node<E> node = new Node<>(elem, null, head);
        //if head is null set node at head
        if (head == null) {
            head = node;
            tail = head;
        } else {  // if head is not null, replace new node to current head node.
            head.prev = node;
            indices.set(0, head);
        } //then set head to new node update indices and size
        head = node;
        indices.add(0, node);
        size++;
        return true;
    }

    // method add node at the last by element
    public boolean append(E elem) {
        //check if head null call add to head.
        if (head == null) {
            add(elem);
            return true;
        } // then create new node to replace last node.
        Node<E> node = new Node<>(elem);
        //set up node relations for new node to replace last node.
        tail.next = node;
        node.prev = tail;
        tail = node;
        //update indices and size
        indices.add(tail);
        size++;
        return true;
    }

    // method get data by index.
    public E get(int index) throws Exception {
        // index check
        if (size > 0) {
            return indices.get(index).data;
        } else {
            throw new Exception("Index out of bounds.");
        }
    }

    //method get head data.
    public E getHead() throws Exception {
        if (size > 0) {
            return head.data;
        }
        throw new Exception("List is empty");
    }

    // method get tail data.
    public E getLast() throws Exception {
        if (size > 0) {
            return tail.data;
        }
        throw new Exception("List is empty");
    }

    //return size of indices
    public int size() {
        return indices.size();
    }

    //method remove head
    public E remove() throws Exception {
        if (size > 0) { // check valid length
            //get head element
            //repeat procedure to set relations for new added node
            Node<E> heads = head;
            head = head.next;
            size--;
            indices.remove(0);
            if (head != null) {
                head.prev = null;
                indices.set(0, head);
            } else {
                tail = null;
            }
            return heads.data;
        } else {
            throw new Exception("Empty list");
        }
    }

    //method remove last node element.
    public E removeLast() throws Exception {
        if (size > 0) {  // check length
            // check if tail have any element
            if (tail == null) {
                return null;
            }
            // create new node for tail, repeat procedure for new node relations to old node
            Node<E> tails = tail;
            tail = tail.prev;
            tail.next = null;
            //update field
            size--;
            indices.remove(tails);
            return tails.data;
        } else {
            throw new Exception("Empty list");
        }
    }

    //method remove element at provided index
    public E removeAt(int index) throws Exception {
        //validation first
        if (index < size && index >= 0 ) {
            if (index == 0) // if index is 0, meaning remove head element
                return remove();
            else if (index == size - 1) // if index is last, remove tail directly
                return removeLast();
            else {
                // create new node set relations accordingly
                Node<E> node = indices.get(index);
                node.prev.next = node.next;
                node.next.prev = node.prev;
                indices.remove(node);
                size--;
                return node.data;
            }
        } else {
            throw new Exception("Index out of bounds");
        }
    }

    //method for remove node by provided elem.
    public boolean remove(E elem) throws Exception {
        //check if provided element exist in the list.
        for (Node<E> element : indices) {
            if (element.data.equals(elem)) { // find if element is equal
                if (element.prev == null) { // condition when element prev is null possibly head
                    head = element.next;
                    size--;
                } else if (element.next == null) { // condition when next is null possibly tail
                    removeLast();
                } else {
                    //if pass set element's next elements relation properly.
                    element.prev.next = element.next;
                    element.next.prev = element.prev;
                }
                //update
                indices.remove(element);
                size--;
                return true;
            }
        }
        return false;
    }

    //method toString of element
    public String toString() {
        Node<E> node = head;
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.data);
            if (node.next != null) {
                result.append(" -> ");
            }
            node = node.next;
        }
        return result.toString();
    }

}
