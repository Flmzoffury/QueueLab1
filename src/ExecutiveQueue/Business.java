package ExecutiveQueue;

import java.util.Scanner;

public class Business {
    //For future attempt: use a hashmap instead of arraylist.
    ArrayList<Department> departments;
    Department unemployed;
    ArrayList<Executive> executives;

    public Business() {
        departments = new ArrayList<Department>();
        executives = new ArrayList<Executive>();
        unemployed = new Department("");
    }

    public void addDept(Department inputDept)
    {
        if (!(departments.find(inputDept) >= 0))
        {
            departments.add(inputDept);
        }
    }

    public void addExec(Executive inputExec)
    {
        if (!(executives.find(inputExec) >= 0))
        {
            executives.add(inputExec);
            unemployed.add(inputExec);
        }
    }

    public void moveExec(String deptName, String execName) {
        if (deptName.equals(executives.get(executives.find(new Executive(execName))).getDepartmentName()))
        {
            System.out.println(execName + " is already in that department");
            return;
        }
        if (deptName.equals("") || departments.find(new Department(deptName)) >= 0)
        {
            if (executives.find(new Executive(execName)) >= 0)
            {
                Executive movedExec = executives.get(executives.find(new Executive(execName)));

                if (movedExec.getDepartmentName().equals(""))
                {
                    unemployed.remove(movedExec.getName());
                }
                else
                {
                    departments.get(departments.find(new Department(movedExec.getDepartmentName()))).remove(movedExec.getName());
                }

                if (deptName.equals(""))
                {
                    unemployed.add(movedExec);
                    movedExec.setDepartmentName("");
                    System.out.println(execName + " has quit");
                }
                else
                {
                    departments.get(departments.find(new Department(deptName))).add(movedExec);
                    movedExec.setDepartmentName(departments.get(departments.find(new Department(deptName))).getName());
                    System.out.println(execName + " added to " + deptName);
                }
            }
            else
            {
                System.out.println("Invalid executive name.");
            }
        }
        else
        {
            System.out.println("Invalid department name.");
        }

    }

    public void payroll()
    {
        for (int i = 0; i < departments.size(); i++)
        {
            System.out.println(departments.get(i).getName() + ":");
            departments.get(i).printPayroll();
        }

    }

    public int getSalary(String execName)
    {
        if (executives.find(new Executive (execName)) >= 0) {
            return getExecDept(execName).getSalary(execName);
        }
        return 0;
    }

    private Department getExecDept(String execName)
    {
        return departments.get(departments.find(new Department(executives.get(executives.find(new Executive(execName))).getDepartmentName())));
    }


}
