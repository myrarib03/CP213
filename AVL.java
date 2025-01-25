package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Myra Ribeiro
 * @author David Brown
 * @version 2024-10-15
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {
	// your code here
	if (node == null) {
	    return 0;
	}
	int leftHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
	int rightHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();
	return leftHeight - rightHeight;
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {
	// your code here
	int balanceFactor = balance(node);
	if (balanceFactor > 1) {
	    if (balance(node.getLeft()) < 0) {
		node.setLeft(rotateLeft(node.getLeft()));
	    }
	    return rotateRight(node);
	}
	if (balanceFactor < -1) {
	    if (balance(node.getRight()) > 0) {
		node.setRight(rotateRight(node.getRight()));
	    }
	    return rotateLeft(node);
	}
	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	// your code here
	TreeNode<T> newRoot = node.getRight();
	node.setRight(newRoot.getLeft());
	newRoot.setLeft(node);
	node.updateHeight();
	newRoot.updateHeight();

	return newRoot;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// your code here
	TreeNode<T> newRoot = node.getLeft();
	node.setLeft(newRoot.getRight());
	newRoot.setRight(node);
	node.updateHeight();
	newRoot.updateHeight();

	return newRoot;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

	// your code here
	if (node == null) {
	    data.setCount(1);
	    return new TreeNode<>(data);
	}

	int comparison = data.compareTo(node.getData());

	if (comparison < 0) {
	    node.setLeft(insertAux(node.getLeft(), data));
	} else if (comparison > 0) {
	    node.setRight(insertAux(node.getRight(), data));
	} else {
	    node.getData().incrementCount();
	}
	node.updateHeight();
	return rebalance(node);
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
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
	boolean isValidBST = (minNode == null || node.getData().compareTo(minNode.getData()) > 0)
		&& (maxNode == null || node.getData().compareTo(maxNode.getData()) < 0);

	if (!isValidBST) {
	    return false;
	}
	int balanceFactor = balance(node);
	if (balanceFactor < -1 || balanceFactor > 1) {
	    return false;
	}
	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedData<T> data) {
	if (node == null) {
	    return null;
	}
	int comparison = data.compareTo(node.getData());
	if (comparison < 0) {
	    node.setLeft(removeAux(node.getLeft(), data));
	} else if (comparison > 0) {
	    node.setRight(removeAux(node.getRight(), data));
	} else {
	    if (node.getData().getCount() > 1) {
		node.getData().decrementCount();
	    } else {
		if (node.getLeft() == null) {
		    return node.getRight();
		} else if (node.getRight() == null) {
		    return node.getLeft();
		} else {
		    TreeNode<T> successor = node.getRight();
		    while (successor.getLeft() != null) {
			successor = successor.getLeft();
		    }
		    CountedData<T> successorData = successor.getData().copy();
		    node.getData().setCount(successorData.getCount());
		    node.getData().compareTo(successorData);
		    node.setRight(removeAux(node.getRight(), successorData));
		}
	    }
	}
	node.updateHeight();
	return rebalance(node);
    }
}