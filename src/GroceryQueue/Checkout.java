package GroceryQueue;

import java.util.Scanner; import java.util.Random;

public class Checkout {
    //Queues
    Queue<Customer> superExpress;
    Queue<Customer>[] expressCounters;
    Queue<Customer>[] standardCounters;

    //Constant queue length
    final int NUM_EXPRESS_COUNTERS = 2;

    //Random object
    Random rng = new Random();

    //User input attributes
    int numStandLines;
    int numSuper;
    int numExp;
    int maxItems;
    int maxSimTime;
    double arrivalRate;

    //Tracked Statistics
    int[] totalItems;
    int[] totalTimes; //The total wait times at each counter
    int[] totalFreeTime;
    int[] totalCustomers; //The total number of customers at each counter
    int averageWaitTimeTotal; //The average wait time across all counters
    int currentTime;

    //Checkout constructor
    public Checkout() {
        superExpress = new Queue<Customer>("Super Express Counter ");

        expressCounters = new Queue[NUM_EXPRESS_COUNTERS];
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++) {
            expressCounters[i] = new Queue<Customer>("Express Counter " + i);
        }

        //Prompting data for the simulation
        promptData();

        //Construct data based on prompts
        standardCounters = new Queue[numStandLines];
        for (int i = 0; i < numStandLines; i++) {
            standardCounters[i] = new Queue<Customer>("Standard Counter " + i);
        }

        totalTimes = new int[1 + NUM_EXPRESS_COUNTERS + numStandLines];
        totalCustomers = new int[1 + NUM_EXPRESS_COUNTERS + numStandLines];
        totalFreeTime = new int[1 + NUM_EXPRESS_COUNTERS + numStandLines];
        totalItems = new int[1 + NUM_EXPRESS_COUNTERS + numStandLines];

        averageWaitTimeTotal = 0;
    }

    /**
     * Takes user input to use for the simulation
     */
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

    /**
     * Finds and prints the data collected from the simulation
     */
    private void printStats()
    {
        int overallFreeTime = 0;
        int overallWaitTime = 0;
        int overallCustomers = 0;
        int overallItems = 0;

        System.out.println("___________________________________________________________________________________________________");
        System.out.println("Wait time (seconds) statistics:");
        System.out.println("___________________________________________________________________________________________________");
        //Average waiting time for each of the lines
        overallCustomers += totalCustomers[0];
        overallWaitTime += totalTimes[0];
        System.out.println("Average waiting time at the super express counter (seconds): \t" + ((double)totalTimes[0])/(double)totalCustomers[0]);
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++)
        {
            overallCustomers += totalCustomers[i + 1];
            overallWaitTime += totalTimes[i + 1];
            System.out.println("Average waiting time at express counter " + (i+1) + " (seconds): \t" + ((double)totalTimes[i + 1])/(double)totalCustomers[i + 1]);
        }
        for (int i = 0; i < numStandLines; i ++)
        {
            overallCustomers += totalCustomers[i + 1 + NUM_EXPRESS_COUNTERS];
            overallWaitTime += totalTimes[i + 1 + NUM_EXPRESS_COUNTERS];
            System.out.println("Average waiting time at standard counter " + (i+1) + " (seconds): \t" + ((double)totalTimes[i + 1 + NUM_EXPRESS_COUNTERS])/(double)totalCustomers[i + 1 + NUM_EXPRESS_COUNTERS]);
        }

        //Overall average waiting time
        System.out.println("Overall average waiting time (seconds): \t" + ((double)overallWaitTime)/((double)overallCustomers));

        System.out.println("___________________________________________________________________________________________________");
        System.out.println("Line length statistics:");
        System.out.println("___________________________________________________________________________________________________");
        //Maximum length of each line
        System.out.println("Maximum length at the super express counter: \t" + superExpress.getLargestSize());
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++)
        {
            System.out.println("Maximum length at express counter " + (i + 1) +": \t" + expressCounters[i].getLargestSize());
        }
        for (int i = 0; i < numStandLines; i ++)
        {
            System.out.println("Maximum length at standard counter " + (i + 1) + ": \t" + standardCounters[i].getLargestSize());
        }

        System.out.println("___________________________________________________________________________________________________");
        System.out.println("Customer statistics:");
        System.out.println("___________________________________________________________________________________________________");
        //Number of customers per hour for each line and overall
        System.out.println("Average customers per hour at the super express counter: \t" + ((double)totalCustomers[0])/((double)maxSimTime)*3600.0);
        overallCustomers += totalCustomers[0];
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++)
        {
            System.out.println("Average customers per hour at express counter " + (i + 1) +": \t" + ((double)totalCustomers[i+1])/((double)maxSimTime)*3600.0);
            overallCustomers += totalCustomers[i+1];
        }
        for (int i = 0; i < numStandLines; i ++)
        {
            System.out.println("Average customers per hour at standard counter " + (i + 1) + ": \t" + ((double)totalCustomers[i + 1 + NUM_EXPRESS_COUNTERS])/((double)maxSimTime)*3600.0);
            overallCustomers += totalCustomers[i + 1 + NUM_EXPRESS_COUNTERS];
        }
        System.out.println("Average customers per hour overall: \t" + ((double)overallCustomers)/((double)maxSimTime)*3600.0);

        System.out.println("___________________________________________________________________________________________________");
        System.out.println("Item processing statistics:");
        System.out.println("___________________________________________________________________________________________________");
        //Number of items processed per hour for each line and overall
        System.out.println("Items processed per hour at the super express counter: \t" + ((double)totalItems[0])/((double)maxSimTime)*3600.0);
        overallItems += totalItems[0];
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++)
        {
            System.out.println("Items processed per hour at express counter " + (i + 1) +": \t" + ((double)totalItems[i + 1])/((double)maxSimTime)*3600.0);
            overallItems += totalItems[i+1];
        }
        for (int i = 0; i < numStandLines; i ++)
        {
            System.out.println("Items processed per hour at standard counter " + (i + 1) + ": \t" + ((double)totalItems[i + 1 + NUM_EXPRESS_COUNTERS])/((double)maxSimTime)*3600.0);
            overallItems += totalItems[i + 1 + NUM_EXPRESS_COUNTERS];
        }
        System.out.println("Items processed per hour overall: \t" + ((double)overallItems)/((double)maxSimTime)*3600.0);


        System.out.println("___________________________________________________________________________________________________");
        System.out.println("Free time (seconds) statistics:");
        System.out.println("___________________________________________________________________________________________________");
        //Average free time of each counter
        System.out.println("Total free time at the super express counter: \t" + totalFreeTime[0]);
        overallFreeTime += totalFreeTime[0];
        for (int i = 0; i < NUM_EXPRESS_COUNTERS; i++)
        {
            System.out.println("Total free time at express counter " + (i + 1) +": \t" + totalFreeTime[i+1]);
            overallFreeTime += totalFreeTime[i+1];
        }
        for (int i = 0; i < numStandLines; i ++)
        {
            System.out.println("Total free time at standard counter " + (i + 1) + ": \t" + totalFreeTime[i + 1 + NUM_EXPRESS_COUNTERS]);
            overallFreeTime += totalFreeTime[i + 1 + NUM_EXPRESS_COUNTERS];
        }

        //Overall free time
        System.out.println("Total free time among all counters: \t" + overallFreeTime);
        System.out.println("Average free time among counters: \t" + ((double) overallFreeTime)/((double)(1 + NUM_EXPRESS_COUNTERS + numStandLines)));

    }

    public void runSimulation()
    {
        for (int i = 0; i < maxSimTime; i++) //Time is tracked in seconds.
        {
            //Track the current time
            currentTime = i;
            //Check for customer arrival -> if a customer arrives add them to the queue they belong in
            checkArrival();
            //Process customers at counters
            processQueues();
            //Track Statistics
        }
        //Print the stats collected
        printStats();
    }

    /**
     * Checks if a new customer is arriving
     */
    private void checkArrival()
    {
        if (rng.nextInt(3600) + 1 < arrivalRate)
        {
            Customer newCustomer = new Customer(rng.nextInt(maxItems) + 1, currentTime);
            addCustomer(newCustomer);
        }
    }

    /**
     * Adds a customer - for checkArrival()
     * @param customer The customer to be added
     */
    private void addCustomer(Customer customer)
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

    /**
     * Progresses the checkout for active checkout lines and tracks statistics including free time.
     */
    private void processQueues()
    {
        Customer currentCustomer;
        if (superExpress.hasNext())
        {
            currentCustomer = superExpress.peek();
            if (currentCustomer.checkoutItem())
            {
                superExpress.poll();
                totalCustomers[0] += 1;
                totalTimes[0] += currentTime - currentCustomer.getArrivalTime() - currentCustomer.getElapsedProcessTime();
                totalItems[0] += currentCustomer.getBeginningItems();
            }
        }
        else
        {
            totalFreeTime[0] += 1;
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
                    totalTimes[1+i] += currentTime - currentCustomer.getArrivalTime() - currentCustomer.getElapsedProcessTime();
                    totalItems[1+i] += currentCustomer.getBeginningItems();
                }
            }
            else
            {
                totalFreeTime[1+i] += 1;
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
                    totalTimes[1+NUM_EXPRESS_COUNTERS+i] += currentTime - currentCustomer.getArrivalTime() - currentCustomer.getElapsedProcessTime();
                    totalItems[1+NUM_EXPRESS_COUNTERS+i] += currentCustomer.getBeginningItems();
                }
            }
            else
            {
                totalFreeTime[1+NUM_EXPRESS_COUNTERS+i] += 1;
            }
        }
    }

}
