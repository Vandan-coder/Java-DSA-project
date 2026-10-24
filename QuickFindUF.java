public class QuickFindUF<T> {
    // Stores the component id for each site.
    private final int parent[]; // parent[i] = component parententifier of i

    // Creates a new union-find structure with n separate components.
    public QuickFindUF(int n) {
        // Allocate storage for all component parents.
        parent = new int[n];
        // Initialize each site so it is its own component.
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Returns the component parententifier for p.
    public int find(int p) {
        return parent[p];
    }

    // Merges the components containing p and q.
    public void union(int p, int q) {
        // Remember the component parents before rewriting any entries.
        int pparent = parent[p];
        // Capture the target component parent.
        int qparent = parent[q];
        // Scan every site and update the ones that belong to p's component.
        for (int i = 0; i < parent.length; i++) {
            // If this site belongs to p's component, move it to q's component.
            if (parent[i] == pparent) {
                parent[i] = qparent;
            }
        }
    }

    // Checks whether p and q are in the same component.
    public boolean connected(int p, int q) {
        return parent[p] == parent[q];
    }

    // Demonstrates the Quick-Find implementation with a few sample operations.
    public static void main(String[] args) {
        // Create a union-find structure with 10 elements.
        QuickFindUF<Integer> uf = new QuickFindUF<>(10);
        // Join 1 and 2.
        uf.union(1, 2);
        // Join 2 and 3.
        uf.union(2, 3);
        // This should print true because 1 and 3 were connected.
        System.out.println(uf.connected(1, 3)); // true
        // This should print false because 4 was never connected.
        System.out.println(uf.connected(1, 4)); // false
    }
}