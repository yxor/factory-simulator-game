import java.util.Random;


public class Worker {
    private String name;
    private int salary;
    private int production;
    private int daysSpent;
    private boolean working;
    private Random r = new Random();


    Worker(String name)
    {
        this.name = name;
        this.daysSpent = 0;
        this.working = true;
        this.salary = 20 + r.nextInt(30);
        this.production = 5 + r.nextInt(30);
    }

    public void grow()
    {
        this.daysSpent++;
        this.production += -1 + r.nextInt(3);
        if(20 + r.nextInt(1000) < this.daysSpent)
        {
            this.fire();
        }
    }
    

    public void fire()
    {
        this.working = false;
    }

    public int getSalary()
    {
        return this.salary;
    }
    public int getProduction()
    {
        return this.production;
    }
    public boolean isWorking()
    {
        return this.working;
    }


    public String toString(){
        return String.format(
                "Name:\"%s\"\tDays spent:%2d\t" +
                 "Production:$%2d\tSalary:$%2d"
                , this.name, this.daysSpent, this.production, this.salary
        );
    }
}