import java.util.Scanner; import java.util.Random;

public class Checkout {
    Queue<Customer> superExpress;
    Queue<Customer>[] expressCounters;
    Queue<Customer>[] standardCounters;
    final int NUM_EXPRESS_COUNTERS = 2;
    Random rng = new Random();

    //User input attributes
    int numStandLines;
    int numSuper;
    int numExp;
    int maxItems;
    int maxSimTime;
    double arrivalRate;

    //Tracked Statistics
    int[] totalTimes; //The total wait times at each counter
    int[] totalCustomers; //The total number of customers at each counter
    int averageWaitTimeTotal; //The average wait time across all counters
    int currentTime;

    public Checkout() {
        superExpress = new Queue<Customer>("Super Express Counter ");

        expressCounters = new Queue[NUM_EXPRESS_COUNTERS];
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++) {
            expressCounters[i] = new Queue<Customer>("Express Counter " + i);
        }
        autoPrompt();
        //promptData();
        standardCounters = new Queue[numStandLines];
        for (int i = 0; i < numStandLines; i++) {
            standardCounters[i] = new Queue<Customer>("Standard Counter " + i);
        }

        totalTimes = new int[1 + NUM_EXPRESS_COUNTERS + numStandLines];
        totalCustomers = new int[1 + NUM_EXPRESS_COUNTERS + numStandLines];

        for (int i = 0; i < 1 + NUM_EXPRESS_COUNTERS + numStandLines; i++)
        {
            totalTimes[i] = 0; totalCustomers[i] = 0;
        }

        averageWaitTimeTotal = 0;
    }

    private void promptData() {
        Scanner textInput = new Scanner(System.in);

        System.out.println("Input the number of standard counters: ");
        numStandLines = textInput.nextInt();
        System.out.println("Input an item limit for the super express counters: ");
        numSuper = textInput.nextInt();
        System.out.println("Input an item limit for the express counters: ");
        numExp = textInput.nextInt();
        System.out.println("Input the amount of customers appearing per hour: ");
        arrivalRate = textInput.nextDouble();
        System.out.println("Input the max amount of items a customer can have: ");
        maxItems = textInput.nextInt();
        System.out.println("Input the maximum simulation time minutes: ");
        maxSimTime = textInput.nextInt() * 60;

    }

    private void autoPrompt()
    {
        numStandLines = 2;
        numSuper = 5;
        numExp = 7;
        arrivalRate = 60;
        maxItems = 10;
        maxSimTime = 240 * 60;
    }

    private void printStats()
    {
        for (int i = 0; i < totalCustomers.length; i++)
        {
            System.out.println(totalCustomers[i]);
        }
        //Super Express Stats
        /*

        for (int i = 1; i < 1 + NUM_EXPRESS_COUNTERS + numStandLines; i++)
        {

        }

         */
    }

    public void runSimulation()
    {
        for (int i = 0; i < maxSimTime; i++) //Time is tracked in seconds.
        {
            currentTime = i;
            //Check for customer arrival -> if a customer arrives add them to the queue they belong in
            checkArrival();
            //Process customers at counters
            processQueues();
            //Track Statistics
        }

        printStats();
    }

    public void checkArrival()
    {
        if (rng.nextInt(3600) + 1 < arrivalRate)
        {
            Customer newCustomer = new Customer(rng.nextInt(maxItems) + 1, currentTime);
            addCustomer(newCustomer);
        }
    }

    public void addCustomer(Customer customer)
    {
        int smallestStandardQueue = 0;
        int lowestStandardSize = standardCounters[0].size();
        if (numStandLines > 1)
        {
            for (int i = 1; i < numStandLines; i++)
            {
                if (standardCounters[i].size() < lowestStandardSize)
                {
                    smallestStandardQueue = i;
                    lowestStandardSize = standardCounters[i].size();
                }
            }
        }

        int smallestExpressQueue = 0;
        int lowestExpressSize = expressCounters[0].size();
        for (int i = 1; i < NUM_EXPRESS_COUNTERS; i++)
        {
            if (expressCounters[i].size() < lowestExpressSize)
            {
                smallestExpressQueue = i;
                lowestExpressSize = expressCounters[i].size();
            }
        }

        if (customer.getItems() > numSuper || ((lowestExpressSize == 0 || lowestStandardSize == 0) && superExpress.size() > 0))
        {
            if (customer.getItems() > numExp || (lowestStandardSize == 0 && lowestExpressSize != 0))
            {

                standardCounters[smallestStandardQueue].add(customer);
            }
            else
            {

                expressCounters[smallestExpressQueue].add(customer);
            }
        }
        else
        {
            superExpress.add(customer);
        }
    }

    public void processQueues()
    {
        Customer currentCustomer;
        if (superExpress.hasNext())
        {
            currentCustomer = superExpress.peek();
            if (currentCustomer.checkoutItem())
            {
                superExpress.poll();
                totalCustomers[0] += 1;
                totalTimes[0] += currentTime - currentCustomer.getArrivalTime();
            }
        }
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++)
        {
            if (expressCounters[i].hasNext())
            {
                currentCustomer = expressCounters[i].peek();
                if (currentCustomer.checkoutItem())
                {
                    expressCounters[i].poll();
                    totalCustomers[1+i] += 1;
                    totalTimes[1+i] += currentTime - currentCustomer.getArrivalTime();
                }
            }
        }
        for (int i = 0; i < numStandLines; i++)
        {
            if (standardCounters[i].hasNext())
            {
                currentCustomer = standardCounters[i].peek();
                if (currentCustomer.checkoutItem())
                {
                    standardCounters[i].poll();
                    totalCustomers[1+NUM_EXPRESS_COUNTERS+i] += 1;
                    totalTimes[1+NUM_EXPRESS_COUNTERS+i] += currentTime - currentCustomer.getArrivalTime();
                }
            }
        }
    }

}
