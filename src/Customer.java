public class Customer
{
    int items;
    int arrivalTime;
    int elapsedProcessTime;

    public Customer(int inputItems, int inputTime)
    {
        items = inputItems;
        arrivalTime = inputTime;
        elapsedProcessTime = 0;
    }

    public int getItems()
    {
        return items;
    }

    public boolean checkoutItem()
    {
        if (items > 0 && elapsedProcessTime > 0 && elapsedProcessTime%5 == 0)
        {
            items--;
        }
        else
        {
            elapsedProcessTime += 1;
        }
        return items == 0;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }
}
