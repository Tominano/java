package array;

public class Array {
	
	private int[] data;
	private int cap, size;
	
	public Array(int cap) {
		data = new int[cap];
		this.cap = cap;
		size = 0;
	}
	
	public Array() {
		this(4);
	}
	
	public void add(int x) {
		if(size == cap)
			extend();
		data[size++] = x;
	}
	
	public int get(int pos) {
		if(pos >= size)
			throw new IllegalArgumentException("invalid pos");
		return data[pos];
	}
	
	/**
	 * Beállítja a pos-adik elemet x-re.
     * 0-tól indexel!
     * @param pos a változtatandó pozíció
     * @param x az új érték 
	 */
	public void set(int pos, int x) {
		if(pos >= size)
			throw new IllegalArgumentException("invalid pos");
		data[pos] = x;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<size;++i) {
			if(i > 0)
				sb.append(',');
			sb.append(data[i]);
		}
		sb.append("] cap: ").append(cap);
		return sb.toString();
	}
	
	private void extend() {
		cap *= 2;
		int[] new_data = new int[cap];
		for(int i=0; i<size; ++i)
			new_data[i] = data[i];
		data = new_data;
	}
	
}