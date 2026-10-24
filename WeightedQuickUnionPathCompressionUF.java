public class WeightedQuickUnionPathCompressionUF<T> {
    private final int[] parent; // parent[i] = parent of i
    private final int[] size;   // size[i] = number of sites in subtree rooted at i
    private int count;          // number of components

    public WeightedQuickUnionPathCompressionUF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // Path compression: make every other node in path point to its grandparent
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // Make smaller root point to larger one.
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionPathCompressionUF<Integer> uf = new WeightedQuickUnionPathCompressionUF<>(10);
        uf.union(1, 2);
        uf.union(2, 3);
        System.out.println(uf.connected(1, 3)); // true 
        System.out.println(uf.connected(1, 4)); // false
    }
}