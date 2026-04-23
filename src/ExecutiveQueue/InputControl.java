package ExecutiveQueue;

import java.util.Scanner;

public class InputControl
{
    Business business;
    Scanner textReader;

    public InputControl()
    {
        business = new Business();
        textReader = new Scanner(System.in);
    }

    /**
     * Takes input for a cycle of the simulation, chooses next method
     * @return
     */
    public boolean take()
    {
        System.out.println("___________________________________");
        System.out.println("Enter a command (enter h for help): ");
        String input = textReader.next().toLowerCase();

        if (input.equals("exit")) {
            return false;
        }
        else if (input.equals("h"))
        {
            printHelp();
        }
        else if (input.equals("add"))
        {
            add();
        }
        else if (input.equals("hire"))
        {
            hire();
        }
        else if (input.equals("join"))
        {
            join();
        }
        else if (input.equals("quit"))
        {
            quit(); //Quit utilizes code similar to join
        }
        else if (input.equals("change"))
        {
            join(); //Due to program structure, join command acts the same as change
        }
        else if (input.equals("payroll"))
        {
            payroll();
        }
        else if (input.equals("salary"))
        {
            salary();
        }
        return true;
    }

    //Each the options resulting from take()

    private void printHelp()
    {
        System.out.println("Commands: ");
        System.out.println("exit: ends the simulation");
        System.out.println("add: create a new department");
        System.out.println("hire: hire a new executive");
        System.out.println("join: move an executive to a department");
        System.out.println("quit: makes an executive quit a department and become unemployed");
        System.out.println("change: move an executive to a new department");
        System.out.println("payroll: prints the salaries of all executives in each department");
        System.out.println("salary: prints the salary of a specific executive");
        System.out.println("Each command will prompt you for any additional information it needs");

    }

    private void add()
    {
        System.out.println("Enter department name: ");
        String input = textReader.next();
        business.addDept(new Department(input));
    }

    private void hire()
    {
        System.out.println("Enter executive's name: ");
        String input = textReader.next();
        business.addExec(new Executive(input));
    }

    private void join()
    {
        System.out.println("Enter executive's name: ");
        String exec = textReader.next();
        System.out.println("Enter deparment's name: ");
        String dept = textReader.next();

        business.moveExec(dept, exec);

    }

    private void quit()
    {
        System.out.println("Enter executive's name: ");
        String exec = textReader.next();

        business.moveExec("", exec);
    }

    private void payroll()
    {
        business.payroll();
    }

    private void salary()
    {
        System.out.println("Enter executive's name: ");
        String input = textReader.next();
        System.out.println(business.getSalary(input));
    }

}
