package edu.isu.cs.cs3308.structures;
import edu.isu.cs.cs3308.structures.impl.SinglyLinkedList;
import java.util.Iterator;


//I used this website so I could make sure I was following similar logic
//https://www.javamadesoeasy.com/2015/02/set-custom-implementation.html

public class HashSet<E> implements Set<E>
{
    private int maxSize;
    private int size;
    private SinglyLinkedList<E>[] buckets;

    public HashSet(int max)
    {
        buckets = new SinglyLinkedList[max];
        maxSize = max;
    }

    public void put(int key, E element)
    {
        if(contains(element) == false)
        {
            if(buckets[key % maxSize] == null)
            {
                buckets[key % maxSize] = new SinglyLinkedList<>();
            }
            buckets[hash(element) % maxSize].addLast(element);
            size++;
        }
    }

    private int hash(E e)
    {
        return Math.abs(e.hashCode());
    }

    @Override
    public void add(E e)
    {
        put(hash(e), e);
    }


    @Override
    public void remove(E e)
    {
        if(contains(e) == true)
        {
            buckets[hash(e) % maxSize].remove(e);
            size--;
        }
    }

    @Override
    public boolean contains(E e)
    {
        if(buckets[hash(e) % maxSize] == null)
        {
            return false;
        }
        return buckets[hash(e) % maxSize].isIn(e);
    }

    @Override
    public Iterator iterator()
    {
        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public void addAll(Set s)
    {

    }

    @Override
    public void retainAll(Set s)
    {

    }

    @Override
    public void removeAll(Set s)
    {

    }
}
