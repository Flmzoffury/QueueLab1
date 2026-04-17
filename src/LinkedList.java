public class LinkedList<T>
{
    Node<T> head;
    int size;

    public LinkedList()
    {
        size = 0;
    }

    public void add(T inputData)
    {
        if (head == null)
        {
            head = new Node<T>(inputData);
        }
        else
        {
            Node<T> tempNode = head;
            while (tempNode.hasNextNode())
            {
                tempNode = tempNode.getNextNode();
            }
            tempNode.setNextNode(new Node<T>(inputData));
        }
        size++;
    }

    public Node<T> getHead()
    {
        return head;
    }

    public void removeHead()
    {
        if (head != null)
        {
            if (head.hasNextNode())
            {
                head = head.getNextNode();
            }
        }
        size--;
    }

    public int getSize()
    {
        return size;
    }

}
