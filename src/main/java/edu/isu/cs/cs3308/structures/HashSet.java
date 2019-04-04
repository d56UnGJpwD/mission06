package edu.isu.cs.cs3308.structures;
import edu.isu.cs.cs3308.structures.impl.SinglyLinkedList;
import java.util.Iterator;


//I used these websites so I could make sure I was following appropriate logic
//https://www.javamadesoeasy.com/2015/02/set-custom-implementation.html
//http://chinthasjavablog.blogspot.com/2014/01/how-to-create-our-own-hashset.html
//https://stackoverflow.com/questions/20379676/implementing-own-hashset
//https://blog.miyozinc.com/algorithms/custom-hashset-implementation-in-java/
//http://robertovormittag.net/how-to-implement-a-simple-hashset-in-java/

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

    public int getMaxSize()
    {
        return maxSize;
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
        Iterator<E> it = new Iterator<E>()
        {
            private int index = 0;
            private int pos = 0;

            @Override
            public boolean hasNext()
            {
                int temp = index;
                while(temp < maxSize)
                {
                    temp++;
                    if(temp>=maxSize)
                    {
                        return false;
                    }
                    if(buckets[temp] != null && buckets[temp].isEmpty() != true)
                    {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public E next()
            {
                if(buckets[index] == null || pos >= buckets[index].size() - 1)
                {
                    while(index < maxSize)
                    {
                        if(index>= maxSize - 1)
                        {
                            System.out.println("Out Of Range\n");
                            break;
                        }
                        pos = 0;
                        index++;
                        if(buckets[index] != null && buckets[index].isEmpty() != true)
                        {
                            return buckets[index].get(pos);
                        }
                    }
                    return null;
                }
                else
                {
                    pos++;
                    return buckets[index].get(pos);
                }
            }
        };
        return it;
    }

    @Override
    public boolean isEmpty()
    {
        if(size == 0)
        {
            return true;
        }
        return false;
    }

    public void printAll()
    {
        for(int i = 0; i < maxSize; i++)
        {
            System.out.println("Bucket[" + i + "]: ");
            SinglyLinkedList<E> temp = buckets[i];
            if(temp != null)
            {
                String line = "";
                for(int j = 0; j < temp.size(); j++)
                {
                    line += temp.get(j).toString() + ", ";
                }
                System.out.println(line);
            }
            System.out.println("\n");
        }
    }

    @Override
    public void addAll(Set<E> s)
    {
        Iterator<E> it = s.iterator();
        while(it.hasNext() == true)
        {
            E e = it.next();
            if(contains(e) == false)
            {
                add(e);
            }
        }
    }

    @Override
    public void retainAll(Set<E> s)
    {
        Iterator<E> it = s.iterator();
        while(it.hasNext() == true)
        {
            E e = it.next();
            if(contains(e) == false)
            {
                remove(e);
            }
        }
    }

    @Override
    public void removeAll(Set<E> s)
    {
        Iterator<E> it = s.iterator();
        while(it.hasNext() == true)
        {
            E e = it.next();
            if(contains(e) == true)
            {
                remove(e);
            }
        }
    }
}
