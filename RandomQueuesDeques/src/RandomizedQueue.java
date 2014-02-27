import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] items;   
   private int size;


   public RandomizedQueue() {
       items = (Item[]) new Object[1];
       size = 0;

   }
   public boolean isEmpty() {
       return size <= 0;
   }
   public int size() {
       return size;
   }
   public void enqueue(Item item) {
       if (item == null) {
           throw new NullPointerException();
       }

       if (size >= items.length) {
           resize(2 * items.length);
       }
       items[size] = item;
       size++;
   }
   public Item dequeue() {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       int randIndex = StdRandom.uniform(0, size);     
       Item item = items[randIndex];
       for (int i = randIndex; i < size - 1; i++) {
           items[i] = items[i + 1];
       }
       
       size--;
       if (size <= items.length/4 && items.length > 4) {
           resize(items.length/2);
       }
       return item;
   }
   
   public Item sample() {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException();
       }
       int randIndex = StdRandom.uniform(0, items.length);
       while (items[randIndex] == null) {
           randIndex = StdRandom.uniform(0, items.length);
       }
       return items[randIndex];
   }
   public Iterator<Item> iterator() {
       return new RandIterator();
   }
   
   private class RandIterator implements Iterator<Item> {
       private Item[] values;
       private int pos;
       private int len;
       
       public RandIterator() {
           values = (Item[]) new Object[size];
           pos = 0;
           len = items.length;
           for (int i = 0; i < size; i++) {
               values[i] = items[i];
           }
           StdRandom.shuffle(values);
       }
       
       
       public boolean hasNext() {
           return pos != size;
       }
       
       
       public void remove() { 
           throw new UnsupportedOperationException(); 
       }
       
       
       public Item next() {
           if (!hasNext()) {
               throw new java.util.NoSuchElementException();
           }
           Item item = values[pos];
           pos++;
           return item;
       }
   }
   
   
   private void resize(int capacity) {
       Item[] copy = (Item[]) new Object[capacity];

       for (int i = 0; i < items.length && i < capacity; i++) {
           copy[i] = items[i];
       }
       items = copy;
   }
   
   public static void main(String[] args) {
     RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
     randQueue.enqueue("1");
     randQueue.enqueue("2");
     randQueue.enqueue("3");
     randQueue.enqueue("4");
       StdOut.println(randQueue.dequeue());
       
       StdOut.println(randQueue.dequeue());
       StdOut.println(randQueue.dequeue());
       
       StdOut.println(randQueue.dequeue());
   }
}
