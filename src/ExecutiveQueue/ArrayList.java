package ExecutiveQueue;

public class ArrayList<T>
{
    T[] collection;
    int size;

    public ArrayList()
    {
        collection = (T[]) new Object[10];
        size = 0;
    }

    public void add(T inputData)
    {
        if (size == collection.length)
        {
            T[] temp = (T[]) new Object[size*2];
            for (int i = 0; i < size; i++)
            {
                temp[i] = collection[i];
            }
            collection = temp;
        }
        collection[size] = inputData;
        size++;
    }

    public T get(int index)
    {
        if (index < size)
        {
            return collection[index];
        }
        else
        {
            return null;
        }
    }

    public void remove(T item)
    {
        int index = find(item);
        if (index >= 0)
        {
            for (int i = index; i < size - 1; i++)
            {
                collection[i] = collection[i + 1];
            }
            size--;
        }
    }

    public int find(T item)
    {
        for (int i = 0; i < size; i++)
        {
            if (collection[i].equals(item))
            {
                return i;
            }
        }
        return -1;
    }

    public int size()
    {
        return size;
    }

}
