package main.rpn;

/**
 * Class of StackAsLists that implements the Interface Stack as a LinkedList.
 * @author max
 * @version 0.1
 * @param <T> Type of the Objects of the Data/Content in the Stack.
 */
public class StackAsList<T> implements Stack<T> {

    /**
     * Latest Node in the LinkedList.
     */
    private Node current;

    /**
     * Constructor of StackAsList.
     */
    public StackAsList(){
    }

    /**
     * Method to push an Object on top of the Stack.
     * @param elm Object that gets pushed on the Stack.
     */
    @Override
    public void push(T elm) {
        Node n = new Node();
        n.data = elm;
        n.previous = current;
        current = n;

    }

    /**
     * Method to pop an Object from the top of the Stack and return it.
     * @return Object that was stored on the top of the Stack.
     */
    @Override
    public T pop() {
        if(current != null) {
            T data = current.data;
            current = current.previous;
            return data;
        }else{
            //throw new IndexOutOfBoundsException();
            return null;
        }
    }

    /**
     * Method to convert the content of the current Node to a String.
     * @return String of the content of the current Node.
     */
    public String toString(){
        return (String) current.data;
    }

    /**
     * Class of the Node that made the content of the LinkedList.
     */
    private class Node{
        /**
         * Data/Content of the Node.
         */
        public T data;
        /**
         * Node before this Node in the LinkedList.
         */
        public Node previous;

        /**
         * Constructor of Node.
         */
        public Node(){
        }

    }
}
