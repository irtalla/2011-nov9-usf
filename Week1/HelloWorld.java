public class HelloWorld {
    public static void main (String[] args) {

        noGarbageCollection();
    }
    public static void noGarbageCollection() {
    	List<Garbage> garbage = new LinkedList<Garbage>();
    	int i = 0;
    	while (true) {
    		garbage.add(new Garbage(i++)+""));
    		System.out.println(i);
    	}
    }
}