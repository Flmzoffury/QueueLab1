package GroceryQueue;

public class Queue<T>
{
    String queueName;
    int size;
    int largestSize;

    public LinkedList<T> line;

    public Queue(String name)
    {
        line = new LinkedList<T>();
        queueName = name;
        size = 0;
        largestSize = 0;
    }

    public void add(T inputData)
    {
        line.add(inputData);
        size++;
        if (size > largestSize)
        {
            largestSize = size;
        }
    }

    public T peek()
    {
        return line.getHead().getData();
    }

    public T poll()
    {
        T temp = line.getHead().getData();
        line.removeHead();
        size--;
        return temp;
    }

    public void print() {
        for (int i = 0; i < line.getSize(); i++)
        {
            System.out.print("[" + this.peek().toString() + "] ");
            this.add(this.poll());
        }
        System.out.println("\n");
    }

    public boolean find(T searchData) {
            boolean found = false;

            for (int i = 0; i < line.getSize(); i++)
            {
                if (this.peek() == searchData)
                {
                    found = true;
                }
                this.add(this.poll());
            }

            return found;
    }

    public void insert(T data, int pos) {
            if (line.getSize() > pos)
            {
                for (int i = 0; i < pos; i++)
                {
                    this.add(this.poll());
                }
                this.add(data);
                for (int i = pos; i < line.getSize() - 1; i++)
                {
                    this.add(this.poll());
                }

            }
            else
            {
                this.add(data);
            }
    }

    public int size()
    {
        return size;
    }

    public int getLargestSize()
    {
        return largestSize;
    }

    public boolean hasNext()
    {
        return size != 0;
    }
}