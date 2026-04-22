package ExecutiveQueue;

public class Executive
{
    String name;
    String departmentName;

    public Executive(String inputName)
    {
        name = inputName;
        departmentName = "";
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
