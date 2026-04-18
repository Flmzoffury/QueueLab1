package GroceryQueue;

public class Customer
{
    int beginingItems;
    int items;
    int arrivalTime;
    int elapsedProcessTime;

    public Customer(int inputItems, int inputTime)
    {
        beginingItems = inputItems;
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
            elapsedProcessTime += 1;
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

    public int getElapsedProcessTime()
    {
        return elapsedProcessTime;
    }

    public int getBeginingItems()
    {
        return beginingItems;
    }
}
