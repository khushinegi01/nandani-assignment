public class SortedLinkedList implements SortedList {

    private Node head;
    private Node tail;
    private int size;
    private boolean ascending = true;

    public int size() {
        return size;
    }

    public void add(String string) {

        if (string == null) return;

        if (isPresent(string)) return;

        Node node = new Node(string);
        add(node);
    }

    public void add(Node node) {

        if (node == null) return;

        node.setNext(null);
        node.setPrev(null);

        if (head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }

        Node current = head;

        while (current != null) {

            int cmp = node.getString().compareToIgnoreCase(current.getString());

            if ((ascending && cmp < 0) || (!ascending && cmp > 0)) {

                node.setNext(current);
                node.setPrev(current.getPrev());

                if (current.getPrev() != null) {
                    current.getPrev().setNext(node);
                } else {
                    head = node;
                }

                current.setPrev(node);

                size++;
                return;
            }

            current = current.getNext();
        }

        tail.setNext(node);
        node.setPrev(tail);
        node.setNext(null);
        tail = node;

        size++;
    }

    public Node getFirst() {
        return head;
    }

    public Node getLast() {
        return tail;
    }

    public Node get(int index) {

        if (index < 0 || index >= size) return null;

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public boolean isPresent(String string) {

        Node current = head;

        while (current != null) {

            if (current.getString().equalsIgnoreCase(string)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public boolean removeFirst() {

        if (head == null) return false;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        }

        size--;
        return true;
    }

    public boolean removeLast() {

        if (tail == null) return false;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
            }
        }

        size--;
        return true;
    }

    public boolean remove(int index) {

        if (index < 0 || index >= size) return false;

        if (index == 0) return removeFirst();

        if (index == size - 1) return removeLast();

        Node node = get(index);

        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());

        size--;
        return true;
    }

    public boolean remove(String string) {

        Node current = head;
        int index = 0;

        while (current != null) {

            if (current.getString().equalsIgnoreCase(string)) {
                return remove(index);
            }

            current = current.getNext();
            index++;
        }

        return false;
    }

    public void orderAscending() {

        if (ascending) return;

        ascending = true;
        reverse();
    }

    public void orderDescending() {

        if (!ascending) return;

        ascending = false;
        reverse();
    }

    private void reverse() {

        Node current = head;
        Node temp = null;

        while (current != null) {

            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);

            current = current.getPrev();
        }

        temp = head;
        head = tail;
        tail = temp;
    }

    public void print() {

        Node current = head;

        while (current != null) {
            System.out.println(current.getString());
            current = current.getNext();
        }
    }
    
}