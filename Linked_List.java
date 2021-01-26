import java.util.List;

public class Linked_List<E> implements List<E> {

    private Node<E> head;
    private int length;

    private class Node<E>{

        E data;
        Node<E> next;

        private Node(E data){
            this.data = data;
            Node<E> next =null;
        }
    }

    public Linked_List(){
        this.head = null;
        this.length=0;
    }

    @Override
    public int size(){
        return length;
    }


    @Override
    public void add(E node){
        Node<E> nextNode = new Node<E>(node);
        Node<E> curr = head;
        if(head == null){
            head = new Node<E>(node);
            length++;
        }else{
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = nextNode;
            length++;
        }
    }

    public void add(E node, int pos){
        if(pos == 0){
            Node<E> newNode = new Node<E>(node);
            newNode.next = head;
            head = newNode;
            length ++;
        }else {
            Node<E> prev = head;
            for(int i = 0; i < pos - 1; i ++){
                prev = prev.next;
            }
            Node<E> newNode = new Node<E>(node);
            newNode.next = prev.next;
            prev.next = newNode;
            length ++;
        }
    }

    @Override
    public E get(int position){
        Node<E> curr = head;
        for(int i = 0; i < position && curr.next != null; i ++){
            curr = curr.next;
        }
        if(curr == null)
        {
            return null;
        }else{
            return curr.data;
        }
    }

    public Node<E> remove(int position){
        if(position == 0){
            Node<E> node = head;
            head = head.next;
            length--;
            return node;
        }else{
            Node<E> prev = head;
            for(int i = 0; i < position - 1; i ++){
                prev = prev.next;
            }
            Node<E> node = prev.next;
            prev.next = node.next;
            length--;
            return node;
        }
    }

    public Node<E> reverse(){
        Node<E> curr = head;

        Node<E> prev = null, next = head;
        while(curr != null){
            next = next.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        head = prev;

        return prev;

    }

}
