package edu.isu.cs.cs3308.structures.impl;



public class SinglyLinkedList<E> {
    /**
     *
     *
     * This is my node class, every node points to the one in front of it, but not the one
     * behind it.
     */

    public class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next){
            this.value = value;
        }

        public void setNext(Node<E> next){
            this.next = next;
        }

        public Node<E> getNext(){
            return next;
        }

        public E getValue(){
            return value;
        }
    }
    public Node<E> head = null;
    public Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList(){}



    // get the first node of the list

    public E first() {
        if(isEmpty()){
            return null;
        }
        return head.getValue();
    }
    // get the last node of the list

    public E last() {
        if(isEmpty()){
            return null;
        }
        return tail.getValue();
    }
    // add a node to the end of the list
    public void addLast(E element) {
        if(element != null){
            Node<E> newNode = new Node<>(element, null);
            if(isEmpty()){
                head = newNode;

            }
            else{
                tail.setNext((newNode));
            }
            tail = newNode;
            size++;
        }

    }
    // add a node to the start of the list
    public void addFirst(E element) {
        if(element != null){
            Node<E> temp = new Node<>(element, head);
            if(size == 0){
                tail = temp;
                head = temp;
            }
            else{
                temp.next = head;
                head = temp;

            }
            size++;
        }
    }
    // removes the head node or the first node
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        E firstVal = head.getValue();
        head = head.getNext();
        if(size == 0){
            tail = null;
        }
        size--;
        return firstVal;
    }
    // removes the tail node or last node
    public E removeLast() {
        if(head == null){
            return null;
        }
        if(head.next == null){
            head = null;
            size--;
        }
        Node<E> secondLast = head;
        while(secondLast.next.next != null){
            secondLast = secondLast.next;
        }
        Node<E> temp = secondLast.getNext();
        secondLast.next = null;
        size--;
        return tail.getValue();
    }
    // adds a node at the specified index
    public void insert(E element, int index) {
        Node<E> newNode = new Node(element, null);
        if(element != null && index >= 0){
            if(head == null && index !=0){}
            else if(head == null && index == 0){
                head = newNode;
                size++;
            }
            if(index == 0){
                newNode.next = head;
                head = newNode;
                size++;
            }
            Node<E> current = head;
            Node<E> prev = null;
            int i = 0;
            while(i < index && current!=null){
                prev = current;
                current = current.next;
                i++;
            }
            newNode.next = current;
            prev.next = newNode;
            size++;
        }

    }
    // removes a node at the specified index
    public E remove(int index) {
        if(index >= size){
            return null;
        }

        if(head == null){
            return null;
        }
        Node<E> temp = head;
        if(index == 0){
            head = temp.next;
            temp = null;
            return temp.getValue();
        }
        if(index < 0){
            return null;
        }
        for(int i=0; temp!=null && i<index-1; i++){
            temp = temp.next;
        }
        if(temp==null || temp.next == null){
            return null;
        }
        Node<E> next = temp.next.next;
        Node<E> del = temp.getNext();
        temp.next = next;
        size--;
        return del.getValue();
    }

    public E remove(E item)
    {
        if(isEmpty() || item == null)
        {
            return null;
        }
        Node<E> current = head;

        if(current == item)
        {
            removeFirst();
        }
        while(current.getNext() != null)
        {
            if(current.getNext().getValue() == item)
            {
                Node<E> toRemove = current.getNext();
                current.setNext(toRemove.getNext());
                toRemove.setNext(null);
                size--;
                return toRemove.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean isIn(E element)
    {
        Node<E> current = head;
        for(int i = 0; i < size; i++)
        {
            if(current.getValue().equals(element))
            {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    // gets the index of a node
    public E get(int index) {
        Node<E> current = head;
        int i = 0;
        while(current != null){
            if(i == index){
                return current.getValue();
            }
            i++;
            current = current.next;
        }
        return null;
    }
    // returns size of list
    public int size() {
        return size;
    }
    // checks if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }
    // prints values of every node in the list
    public void printList() {
        Node<E> current = head;
        while(current != null){
            System.out.println(current.getValue());
            current = current.next;
        }
    }

}
