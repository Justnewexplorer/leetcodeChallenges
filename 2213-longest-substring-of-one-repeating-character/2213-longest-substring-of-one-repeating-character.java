class Solution {

    class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int length;

        Node(char leftChar, char rightChar, int prefix, int suffix, int best, int length) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
            this.length = length;
        }
    }

    Node[] tree;
    String s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s;
        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(1,0,n - 1,queryIndices[i],queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            char ch = s.charAt(l);
            tree[node] = new Node(ch, ch, 1, 1, 1, 1 );
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2],tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r,int index, char ch) {
        if (l == r) {
            tree[node] = new Node(ch, ch,1, 1, 1, 1);
            return;
        }
        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        tree[node] = merge(tree[node * 2],tree[node * 2 + 1]);
    }

    private Node merge(Node L, Node R) {
        char leftChar = L.leftChar;
        char rightChar = R.rightChar;

        int prefix = L.prefix;
        int suffix = R.suffix;

        int best = Math.max(L.best, R.best);

        if (L.rightChar == R.leftChar) {
            best = Math.max(best,L.suffix + R.prefix );

            if (L.prefix == L.length) {
                prefix = L.length + R.prefix;
            }

            if (R.suffix == R.length) {
                suffix = R.length + L.suffix;
            }
        }

        return new Node(leftChar,rightChar,prefix,suffix,best,L.length + R.length);
    }
}