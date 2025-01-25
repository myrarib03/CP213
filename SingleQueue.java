package cp213;

/**
 * A single linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current
     * SingleQueue. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods insert or remove. left and right SingleQueues are
     * empty when done. Nodes are moved alternately from left and right to this
     * SingleQueue.
     *
     * You have two source queues named left and right. Move all nodes from these
     * two queues to the current queue. It does not make a difference if the current
     * queue is empty or not, just get nodes from the right and left queues and add
     * them to the current queue. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
	while (left.front != null || right.front != null) {
	    if (left.front != null) {
		SingleNode<T> leftNode = left.front;
		left.front = left.front.getNext();
		leftNode.setNext(null);

		if (this.rear == null) {
		    this.front = this.rear = leftNode;
		} else {
		    this.rear.setNext(leftNode);
		    this.rear = leftNode;
		}
		this.length++;
		left.length--;
	    }

	    if (right.front != null) {
		SingleNode<T> rightNode = right.front;
		right.front = right.front.getNext();
		rightNode.setNext(null);

		if (this.rear == null) {
		    this.front = this.rear = rightNode;
		} else {
		    this.rear.setNext(rightNode);
		    this.rear = rightNode;
		}
		this.length++;
		right.length--;
	    }
	}

	left.rear = null;
	right.rear = null;
    }

    /**
     * Adds object to the rear of the queue. Increments the queue length.
     *
     * @param object The object to added to the rear of the queue.
     */
    public void insert(final T object) {

	// your code here
	SingleNode<T> newNode = new SingleNode<>(object, null);

	if (this.rear == null) {
	    this.front = this.rear = newNode;
	} else {
	    this.rear.setNext(newNode);
	    this.rear = newNode;
	}
	this.length++;
    }

    /**
     * Returns the front object of the queue and removes that object from the queue.
     * The next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The object at the front of the queue.
     */
    public T remove() {

	// your code here
	if (this.front == null) {
	    return null;
	}

	T frontObject = this.front.getObject();
	this.front = this.front.getNext();
	this.length--;

	if (this.front == null) {
	    this.rear = null;
	}

	return frontObject;
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right
     * SingleQueues. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleQueue is empty when done. Nodes are
     * moved alternately from this SingleQueue to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
	boolean toLeft = true;

	while (this.front != null) {
	    SingleNode<T> nodeToMove = this.front;
	    this.front = this.front.getNext();
	    nodeToMove.setNext(null);

	    if (toLeft) {
		if (left.rear == null) {
		    left.front = nodeToMove;
		} else {
		    left.rear.setNext(nodeToMove);
		}
		left.rear = nodeToMove;
		left.length++;
	    } else {
		if (right.rear == null) {
		    right.front = nodeToMove;
		} else {
		    right.rear.setNext(nodeToMove);
		}
		right.rear = nodeToMove;
		right.length++;
	    }

	    toLeft = !toLeft;
	    this.length--;
	}

	this.rear = null;
    }
}
