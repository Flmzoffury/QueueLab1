package ExecutiveQueue;

public class Executive
{
    /**
     * Stores name of the executive to find.
     */
    String name;
    /**
     * Stores the name of the executive's current department
     */
    String departmentName;

    public Executive(String inputName)
    {
        name = inputName;
        departmentName = ""; //"" represents the unemployed queue
    }

    public String getName()
    {
        return name;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Executive)
        {
            Executive temp = (Executive) obj;
            return name.equals(temp.getName());
        }
        return false;
    }

    public void setDepartmentName(String inputDept)
    {
        departmentName = inputDept;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
}
