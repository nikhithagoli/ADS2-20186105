import java.util.*;
class BSTArrayRepresentation<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int[] size;
	private int[] left;
	private int[] right;
	BSTArrayRepresentation(int n) {
		keys = (Key[]) new Comparable[n];
		values = (Value[]) new Object[n];
		this.size = new int[n];
		left = new int[n];
		right = new int[n];
		for (int i = 0; i < n; i++) {
			left[i] = -1;
			right[i] = -1;
		}
	}

	public int size() {
		return size(0);
	}

	private int size(int index) {
		if (index == -1) {
			return 0;
		}

		return size[index];
	}
	public Key min() {
		if (size() == 0) {
			System.out.println("Empty binary search tree");
		}
		int minIndex = min(0);
		return keys[minIndex];
	}

	private int min(int index) {
		if (left[index] == -1) {
			return index;
		}
		return min(left[index]);
	}

	public Value get(Key key) {
		return get(0, key);
	}

	private Value get(int root, Key key) {
		if (root == -1 || keys[root] == null) {
			return null;
		}
		int cmp  = key.compareTo(keys[root]);
		if (cmp < 0) {
			return get(left[root], key);
		} else if (cmp > 0) {
			return get(right[root], key);
		} else {
			return values[root];
		}
	}

	public void put(Key key, Value value) {
		if (size() == keys.length) {
			System.out.println("Tree is full");
			return;
		}
		put(0, key, value);
			}

	private int put(int root, Key key, Value value) {
		if (root == -1 || keys[root] == null) {
			int next = size();
			keys[next] = key;
			values[next] = value;
			size[next] = 1;
			return next;
		}

		int cmp = key.compareTo(keys[root]);

		if (cmp < 0) {
			left[root] = put(left[root], key, value);
		} else if (cmp > 0) {
			right[root] = put(right[root], key, value);
		} else {
			values[root] = value;
		}

		size[root] = size(left[root]) + 1 + size(right[root]);
		return root;
	}

	public void delete(Key key) {
		int rootIndex = delete(0, key);
	}

	private int delete(int root, Key key) {
		if (root == -1 || keys[root] == null) {
			return -1;
		}

		int cmp = key.compareTo(keys[root]);
		if (cmp < 0) {
			int leftIndex = delete(left[root], key);
			left[root] = leftIndex;
		} else if (cmp > 0) {
			int rightIndex = delete(right[root], key);
			right[root] = rightIndex;
		} else {
			keys[root] = null;
			values[root] = null;
			size[root] = 0;

			if (left[root] == -1) {
				int rightLinkIndex = right[root];
				right[root] = -1;
				return rightLinkIndex;
			} else if (right[root] == -1) {
				int leftLinkIndex = left[root];
				left[root] = -1;
				return leftLinkIndex;
			} else {
				int nextindex = min(right[root]);
				right[nextindex] = deleteMin(right[root], false);
				left[nextindex] = left[root];
				right[root] = -1;
				left[root] = -1;
				root = nextindex;
			}
		}
		size[root] = size(left[root]) + 1 + size(right[root]);
		return root;
	}

	public void deleteMin() {
		int rootIndex = deleteMin(0, true);
	}
	private int deleteMin(int index, boolean setKeyNull) {
		if (index == -1 || keys[index] == null) {
			return -1;
		}

		int leftIndex = deleteMin(left[index], setKeyNull);
		left[index] = leftIndex;

		size[index] = size(left[index]) + 1 + size(right[index]);
		return index;
	}
}

class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		BSTArrayRepresentation<Integer, String> bst = new BSTArrayRepresentation<>(n);
		Stopwatch sw = new Stopwatch();
		while (n > 0) {
			String s = sc.nextLine();
			String[] tokens = s.split(" ");
			switch (tokens[0]) {
			case"put":
				bst.put(Integer.parseInt(tokens[1]), tokens[2]);
				System.out.println(sw.elapsedTime());
				break;
			case"get":
				String val = bst.get(Integer.parseInt(tokens[1]));
				System.out.println(val);
				System.out.println(sw.elapsedTime());
				break;
			case"delete":
				
				bst.delete(Integer.parseInt(tokens[1]));
				System.out.println(sw.elapsedTime());
			}
			n--;
		}
		System.out.println(sw.elapsedTime());
	}
}