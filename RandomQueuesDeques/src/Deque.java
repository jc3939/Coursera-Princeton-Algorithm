import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
   private class Node {
       private Item item;
       private Node next;
       private Node prev;
       public Node(Item item) {
           this.item = item;
       }

   }
   
   private int size;
   private Node header;
   private Node tail;
   public Deque() {
       header = null;
       tail = null;
       size = 0;
   }
   public boolean isEmpty() {
       return size == 0;
   }
   public int size() {
       return size;
   }
   public void addFirst(Item item) {
       if (item == null) {
           throw new NullPointerException();
       }
       Node newNode = new Node(item);
       newNode.next = header;
       if (header != null) {
           header.prev = newNode;
       }
       header = newNode;
       size++;
       if (tail == null) {
           tail = header;
       }
   }
   public void addLast(Item item) {
       if (item == null) {
           throw new NullPointerException();
       }
       Node newNode = new Node(item);
       newNode.prev = tail;
       if (tail != null) {
           tail.next = newNode;
       }
       tail = newNode;
       size++;   
       if (header == null) {
           header = tail;
       }
   }
   public Item removeFirst() {
       if (header == null) {
           throw new java.util.NoSuchElementException();
       }
       Item result = header.item;
       if (size == 1) {
           header = null;
           tail = null;
           size--;
       }
       else {
           header = header.next;
           header.prev = null;
           size--;
       }
       return result;

   }
   public Item removeLast() {
       if (tail == null) {
           throw new java.util.NoSuchElementException();
       }
       Item result = tail.item;
       if (size == 1) {
           header = null;
           tail = null;
           size--;
       }
       else {
           tail = tail.prev;
           tail.next = null;
           size--;
       }
       return result;
   }
   
   public Iterator<Item> iterator() {
       return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item> {
       private Node current = header;
       
       public boolean hasNext() { 
           return current != null; 
       }
       
       public void remove() { 
           throw new UnsupportedOperationException(); 
       }
       
       public Item next() {
           if (!hasNext()) {
               throw new java.util.NoSuchElementException();
           }
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
   
   
   public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("1");
        deque.addFirst("2");
        StdOut.println(deque.removeLast());
        deque.addFirst("3");
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        deque.addFirst("4");
        StdOut.println(deque.removeLast());
   }
}