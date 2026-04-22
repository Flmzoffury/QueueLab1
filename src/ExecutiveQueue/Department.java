package ExecutiveQueue;

import GroceryQueue.LinkedList;

public class Department
{
    String deptName;
    int size;
    int largestSize;

    private GroceryQueue.LinkedList<Executive> line;

    public Department(String inputName)
    {
        line = new LinkedList<Executive>();
        deptName = inputName;
        size = 0;
        largestSize = 0;
    }

    public void add(Executive inputExec)
    {
        line.add(inputExec);
        size++;
        if (size > largestSize)
        {
            largestSize = size;
        }
    }

    public Executive peek()
    {
        return line.getHead().getData();
    }

    public Executive poll()
    {
        Executive temp = line.getHead().getData();
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

    public boolean find(Executive searchData) {
        boolean found = false;

        for (int i = 0; i < line.getSize(); i++)
        {
            if (this.peek().equals(searchData))
            {
                found = true;
            }
            this.add(this.poll());
        }

        return found;
    }

    public void insert(Executive data, int pos) {
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

    public String getName()
    {
        return deptName;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Department)
        {
            Department temp = (Department) obj;
            return deptName.equals(temp.getName());
        }
        return false;
    }

    public void remove(String inputExec)
    {
        Executive comparisonExec = new Executive(inputExec);
        if (find(comparisonExec))
        {
            int originalSize = line.getSize();
            for (int i = 0; i < originalSize; i++)
            {
                if (this.peek().equals(comparisonExec))
                {
                    this.poll();
                }
                else
                {
                    this.add(this.poll());
                }
            }
        }
    }

    public void printPayroll()
    {
        Executive currentExec;
        for (int i = 0; i < size; i++)
        {
            currentExec = this.poll();
            this.add(currentExec);
            System.out.println(currentExec.getName() + ": \t" + ((size-i-1)*5000+40000) + "$");

        }
    }

    public int getSalary(String execName)
    {
        if (!deptName.equals("")) {
            int salary = 0;
            Executive currentExec;
            for (int i = 0; i < size; i++) {
                currentExec = this.poll();
                this.add(currentExec);

                if (salary == 0 && currentExec.getName().equals(execName)) {
                    salary = 40000 + 5000 * (size - i - 1);
                }


            }
            return salary;
        }
        return 0;
    }

}
