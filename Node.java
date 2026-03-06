public class Node {

    private String string;
    private Node next;
    private Node prev;

    public Node(String string) {
        this.string = string;
        this.next = null;
        this.prev = null;
    }

    public String getString() {
        return string;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}