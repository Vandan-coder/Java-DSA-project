public class QuickUnionUF<T> {
    private final int[] parent; // parent[i] = parent of i
    private int count; // number of components

    public QuickUnionUF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
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
        parent[rootP] = rootQ;
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
        QuickUnionUF<Integer> uf = new QuickUnionUF<>(10);
        uf.union(1, 2);
        uf.union(2, 3);
        System.out.println(uf.connected(1, 3)); // true
        System.out.println(uf.connected(1, 4)); // false
    }
}