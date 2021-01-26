import java.util.List;

public class Array_List<E> implements List<E> {
    private int size = 0;
    private Object elements[];

    public Array_List(){
        this.elements = new Object[5];
    }

    @Override
    public void add(E element){
        if(size == this.elements.length){
            this.increaseCapacity();
        }
        this.elements[size] = element;
        size++;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index){
        return (E) elements[index];
    }

    public void increaseCapacity(){
        Object newElements[] = new Object[this.elements.length * 2];
        for(int i = 0; i < this.elements.length; i++){
            newElements[i] = this.elements[i];
        }
        this.elements = newElements;
    }
}