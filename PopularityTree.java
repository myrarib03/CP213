package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Myra Ribeiro
 * @author David Brown
 * @version 2024-10-15
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedData<T> key) {

	// your code here
	if (node == null) {
	    return null;
	}

	int comparisonResult = node.getData().compareTo(key);
	this.comparisons++;

	if (comparisonResult == 0) {
	    node.getData().incrementCount();
	    return node;
	} else if (comparisonResult > 0) {
	    TreeNode<T> leftSearchResult = this.retrieveAux(node.getLeft(), key);

	    if (leftSearchResult != null) {
		if (node.getLeft().getData().getCount() < leftSearchResult.getData().getCount()) {
		    node.setLeft(leftSearchResult);
		}
		if (node.getData().getCount() < leftSearchResult.getData().getCount()) {
		    TreeNode<T> updatedRoot = this.rotateRight(node);
		    if (node.getData().compareTo(this.root.getData()) == 0) {
			this.root = updatedRoot;
		    }

		    node.updateHeight();
		    updatedRoot.updateHeight();
		}
	    }

	    return leftSearchResult;
	} else {
	    TreeNode<T> rightSearchResult = this.retrieveAux(node.getRight(), key);

	    if (rightSearchResult != null) {
		if (node.getRight().getData().getCount() < rightSearchResult.getData().getCount()) {
		    node.setRight(rightSearchResult);
		}
		if (node.getData().getCount() < rightSearchResult.getData().getCount()) {
		    TreeNode<T> updatedRoot = this.rotateLeft(node);
		    if (node.getData().compareTo(this.root.getData()) == 0) {
			this.root = updatedRoot;
		    }

		    node.updateHeight();
		    updatedRoot.updateHeight();
		}
	    }

	    return rightSearchResult;
	}
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	// your code here
	TreeNode<T> newRoot = parent.getRight();
	parent.setRight(newRoot.getLeft());
	newRoot.setLeft(parent);
	parent.updateHeight();
	newRoot.updateHeight();

	return newRoot;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	// your code here
	TreeNode<T> newRoot = parent.getLeft();
	parent.setLeft(newRoot.getRight());
	newRoot.setRight(parent);
	parent.updateHeight();
	newRoot.updateHeight();

	return newRoot;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {
	// your code here

	if (node == null) {
	    node = new TreeNode<T>(data);
	    this.size++;
	} else {
	    final int result = node.getData().compareTo(data);

	    if (result > 0) {
		node.setLeft(this.insertAux(node.getLeft(), data));
	    } else if (result < 0) {
		node.setRight(this.insertAux(node.getRight(), data));
	    } else {
	    }
	}
	node.updateHeight();
	return node;

    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
	if (node == null) {
	    return true;
	}

	if ((minNode != null && node.getData().compareTo(minNode.getData()) <= 0)
		|| (maxNode != null && node.getData().compareTo(maxNode.getData()) >= 0)) {
	    return false;
	}

	if (node.getLeft() != null && node.getLeft().getData().getCount() > node.getData().getCount()) {
	    return false;
	}

	if (node.getRight() != null && node.getRight().getData().getCount() > node.getData().getCount()) {
	    return false;
	}

	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedData<T> retrieve(CountedData<T> key) {
	// your code here
	TreeNode<T> resultNode = retrieveAux(root, key);
	return (resultNode != null) ? resultNode.getData() : null;
    }
}
