public class Node<T>
{
    private T data;
    private Node<T> nextNode;

    public Node(T inputData)
    {
        data = inputData;
    }

    public T getData()
    {
        return data;
    }

    public void setNextNode(Node<T> inputNode)
    {
        nextNode = inputNode;
    }

    public Node<T> getNextNode()
    {
        return nextNode;
    }

    public boolean hasNextNode()
    {
        return nextNode != null;
    }
}
