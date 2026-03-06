public interface SortedList {

    public int size();

    public void add(String string);

    public void add(Node node);

    public Node getFirst();

    public Node getLast();

    public Node get(int index);

    public boolean isPresent(String string);

    public boolean removeFirst();

    public boolean removeLast();

    public boolean remove(int index);

    public boolean remove(String string);

    public void orderAscending();

    public void orderDescending();

    public void print();
}