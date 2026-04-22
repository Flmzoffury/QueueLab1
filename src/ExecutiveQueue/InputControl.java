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

    private void printHelp()
    {
        System.out.println();
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

    public void test()
    {
        for (int i = 0; i < 10; i++)
        {
            business.addDept(new Department("Department#"+i));
            System.out.println("Added Department#"+i);
            business.addExec(new Executive("Exec#"+2*i));
            System.out.println("Added Exec#"+2*i);
            business.addExec(new Executive("Exec#"+(2*i+1)));
            System.out.println("Added Exec#"+(2*i+1));
            business.moveExec("Department#"+i,"Exec#"+2*i);
            business.moveExec("Department#"+i,"Exec#"+2*i+1);
        }
        business.moveExec("", "Executive#5");
        business.getSalary("Executive#4");
        business.payroll();

    }

}
