public class BST<T> {
    class Node {
        T value;
        Node left;
        Node right;
        Node parent;

        Node(T value, Node parent) {
            this.value = value;
        }
    }

    Node root;

    BST() {
        this.root = null;
    }

    public void insert(T value) {
        if(this.root == null) {
            this.root = new Node(value, null);
        }

        this.insert(value, this.root);
    }

    private void insert(T value, Node node) {
        // returns -1 then node value is less
        if(node.value.compareTo(value) < 0) {
            if(node.right == null) {
                node.right = new Node(value, node);
            }
            else{
                this.insert(value, node.right);
            }
        }
        // return 1 then node value is greater
        else if(node.value.compareTo(value) > 0) {
            if(node.left == null) {
                node.left = new Node(value, node);
            }
            else{
                this.insert(value, node.left);
            }
        }
    }

    public boolean contains(T value) {
        return node.value.equals(value) ? true; contains(value, this.root);
    }

    private boolean contains(T value, Node node) {
        // value does not exist
        if(node == null) {
            return false;
        }
        // returns -1 then node value is less go right
        else if(node.value.compareTo(value) < 0) {
            return contains(value, node.right);
        }
        // return 1 then node value is greater go left
        else if(node.value.compareTo(value) > 0) {
            return contains(value, node.left);
        } 
        // Values are equal{
        else{
            return true;
        }
        
    }

    public void remove(T value) {
        if(this.contains(value)) {
            this.remove(T value, this.root);
        }
    }

    private void remove(T value, Node node) {
        // returns -1 then node value is less go right
        if(node.value.compareTo(value) < 0) {
            return contains(value, node.right);
        }
        // return 1 then node value is greater go left
        else if(node.value.compareTo(value) > 0) {
            return contains(value, node.left);
        } 
        // Values are equal{
        else{
            // Node has no children
            if(node.left == null && node.right == null){
                // check if node was left or right child
                if(node.parent.left.equals(value))
                    node.parent.left = null
                else 
                    node.parent.right = null;
            }
            // Node has one child
            else if(node.left == null) {
                if(node.parent.left.equals(value))
                    node.parent.left = node.right
                else 
                    node.parent.right = node.right;
            }
            else if (node.right == null) {
                if(node.parent.left.equals(value))
                    node.parent.left = node.left;
                else 
                    node.parent.right = node.left;
            }
            // Node has two children. Replace with min Value of the left
            else {
                Node replace = this.removeMin(node.right);
                node.value = replace.value;
                this.remove(node.value, replace);
            }
        }
    }

    private Node removeMin(Node node) {
        Node current = node;
        while(current.left != null) {
            current = current.left;
        }
        return current;
    }

    public T findMin() {
        Node current = this.root;
        while(current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public T findMax() {
        Node current = this.root;
        while(current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
