package de.htw.lab8.main.exercise5;

/**
 * Class LinkedList, is implemented as a singly LinkedList for Strings.
 * @date 2022-01-13
 * @author Lucas, Artus, Robin, Max
 */
public class LinkedList {

    private Node head;

    /**
     * Constructor of the LinkedList class
     */
    public LinkedList(){

    }

    /**
     * Pushed a Node with the String on the LinkedList.
     * @param data the String you want to add to the list
     */
    public void push(String data){
        if(head == null){
            head = new Node(data);
        }else{
            Node currentNode = head;
            while(currentNode.getNext() != null){
                currentNode = currentNode.getNext();
            }
            Node newNode = new Node(data);
            currentNode.setNext(newNode);
        }
    }

    /**
     * Removes a Node from the LinkedList
     * @return the String that was stored in the Node
     */
    public Node pop(){
        Node currentNode = head;

        if(currentNode == null) {
            return null;
        }
        else if(currentNode.getNext() == null){
            Node toReturn = currentNode;
            head = null;
            return toReturn;
        }
        Node nodeBefore = head;
        while(currentNode.getNext() != null){
            nodeBefore = currentNode;
            currentNode = currentNode.getNext();
        }
        nodeBefore.setNext(null);

        return currentNode;
    }

    /**
     * Prints out the Strings of the Node in the LinkedList
     * @return a String containg all elements
     */
    public String toString(){
        String result= "";
        Node currentNode = head;

        if(currentNode != null) {

            result += currentNode.getData();

            while (currentNode.next != null) {
                currentNode = currentNode.getNext();
                result += "-" + currentNode.getData();
            }
        }
        return result;
    }

    /**
     * Reverses the order of a LinkedList
     */
    public void reverse(){
        LinkedList newLinkedList = new LinkedList();

        if(head.getNext() != null) {
            Node popNode = this.pop();
            String nextData = popNode.getData();
            newLinkedList.push(nextData);

            while (null != head.getNext()) {
                popNode = this.pop();
                nextData = popNode.getData();
                newLinkedList.push(nextData);
            }

            //handle the head data here
            newLinkedList.push(head.getData());

            this.head = newLinkedList.head;
        }
    }

    /**
     * Inner Class Node, is used by LinkedList
     */
    class Node{
        String data;
        Node next;

        /**
         * Constructor of the Node class
         * @param data the Data of the Node as a String
         */
        public Node(String data){
            this.data = data;
        }

        /**
         * @return the Data of the Node as a String
         */
        public String getData(){
            return data;
        }

        /**
         * @return the next Node in the LinkedList
         */
        public Node getNext(){
            return next;
        }

        /**
         * Changes the Data of a Node
         * @param data the String that should be stored
         */
        public void setData(String data){
            this.data = data;
        }

        /**
         * Changes the Next Node of a Node
         * @param next the Node that should be set as next
         */
        public void setNext(Node next){
            this.next = next;
        }
    }
}
