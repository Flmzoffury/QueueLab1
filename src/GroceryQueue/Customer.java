package GroceryQueue;

public class Customer
{
    /**
     * The items the customer came to the line with
     */
    int beginningItems;
    /**
     * How many items the customer still needs to check out
     */
    int items;
    /**
     * Time of arrival for the customer
     */
    int arrivalTime;
    /**
     * The amount of time the customer has been processed at the counter
     */
    int elapsedProcessTime;

    public Customer(int inputItems, int inputTime)
    {
        beginningItems = inputItems;
        items = inputItems;
        arrivalTime = inputTime;
        elapsedProcessTime = 0;
    }

    /**
     * Get the current value of items
     * @return items
     */
    public int getItems()
    {
        return items;
    }

    /**
     * Progresses checkout for the customer, removes an item if the elapsed time is a multiple of 5
     * @return true if the customer is done checking out, false otherwise
     */
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

    /**
     * Gets arrivalTime
     * @return arrivalTime
     */
    public int getArrivalTime()
    {
        return arrivalTime;
    }

    /**
     * Gets elapsedProcessTime
     * @return elapsedProcessTime
     */
    public int getElapsedProcessTime()
    {
        return elapsedProcessTime;
    }

    /**
     * Gets beginningItems
     * @return beginningItems
     */
    public int getBeginningItems()
    {
        return beginningItems;
    }
}
