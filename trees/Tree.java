public class Tree<T> {

    class Node {
        T value;
        Node parent;
        ArrayList children = new ArrayList<Node>();

        Node(T value) {
            this.value = value;
        }
    }

    private Node root;
    private int height = 0;

    Tree() {
        this.root = null;
    }

    public boolean insert(T value, T parent) {
        // Tree root
        if (parent == null) {
            this.root = new Node(value);
            return true;
        }
        // Search 
        Node node = search(parent);
        // Check exists
        if (node != null) {
            parent.children.insert(new Node(value));
            return true;
        }

        return false;
    }

    public Node search(T value) {
        return depthFirstSearch(this.root, value);
    }

    private Node depthFirstSearch(Node searchNode, T value) {
        // Base Case One
        if (searchNode.value.equals(value)) {
            return Node;
        }
        // Base Case Two
        else if (searchNode.children.isEmpty()) {
            return null;
        }
        // Recursive Case
        else {
            Node returnNode = null;
            for(int i = 0; i < searchNode.children.size(); i++) {
                Node node = depthFirstSearch(searchNode.children.get(i), value);
                returnNode = node != null: node ? returnNode;
            }

            return returnNode;
        }
    }

    public Node breadthFirstSearch(T value) {
        Queue q = new Queue<Node>();
        q.add(this.root);

        while (!q.peek() != null) {
            Node node = q.poll();

            if (node.value.equals(value)) {
                return node;
            }
            else {
                for(int i = 0; i < node.children.size(); i++) {
                    q.add(node.children.get(i));
                }
            }
        }

        return null;
    }
}
