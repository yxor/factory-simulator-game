import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Factory {
    private String name;
    private List<Worker> workers;
    private int balance;
    private int dayCount;
    private boolean working;
    private String[] lastNames = {
            "Gottesman",
            "Gottfried",
            "Gotthelf",
            "Gottman",
            "Harmel",
            "Harnick",
            "Harris",
            "Harshbarger",
            "Hart",
            "Hartenstein",
            "Harth",
            "Hartman",
            "Hartmann",
            "Hartog",
            "Hartstein",
            "Hartzog",
            "Hasan",
            "Hase",
            "Hasen",
            "Haskell",
            "Haskin",
            "Hason"
    };

    private String[] firstNames = {"Allene",
            "Alleras",
            "Allerie",
            "Alleris",
            "Allerus",
            "Alley",
            "Alleyn",
            "Alleyne",
            "Alli",
            "Labors",
            "Lacagnia",
            "Lacee",
            "Lacefield",
            "Lacey",
            "Lach",
            "Lachance",
            "Lachish",
            "Lachlan",
            "Lachman",
            "Lachus",
            "Lacie",
            "Riella",
            "Ries",
            "Riesman",
            "Riess",
            "Rieth",
            "Riffle",
            "Rifkin",
            "Rigby",
            "Rigdon",
            "Riggall",
            "Riggins",
            "Riggs",
            "Riha",
            "Rihana",
            "Rik",
            "Rika",
    };

    Factory(String name, int initial_funds)
    {
        this.name = name;
        this.workers = new ArrayList<Worker>();
        this.balance = initial_funds;
        this.working = true;
        this.addWorkers(3);

    }

    public String getName(){
        return this.name;
    }

    public int getBalance()
    {
        return this.balance;
    }

    public int getDayCount() { return this.dayCount; }

    public void fireWorker(int index)
    {
        if (this.workers.isEmpty())
            return;
        try{
            this.workers.remove(index);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("ID does not Exist");
        }
    }


    public int getPopulation()
    {
        return this.workers.size();
    }

    public boolean isWorking()
    {
        return this.working;
    }

    public void nextDay()
    {
        this.dayCount++;
        for (Worker w: this.workers)
        {
            this.balance += w.getProduction() - w.getSalary();
            w.grow();
        }
        this.workers.removeIf(worker -> !worker.isWorking());

        if(this.dayCount % 10 == 0) {
            this.addWorkers(3);
            this.balance -= 4 * this.dayCount;
        }

        if (this.balance < 0)
        {
            this.working = false;
        }


    }

    public ListIterator<Worker> getWorkersIterator()
    {
        return this.workers.listIterator();
    }

    private String generateName()
    {
        Random r = new Random();
        String firstName = firstNames[r.nextInt(firstNames.length)];
        String lastName = lastNames[r.nextInt(lastNames.length)];
        return String.format("%s %s", firstName, lastName);
    }

    public void addWorkers(int count)
    {
        for (int i = 0; i < count; i++) {
            this.addWorker(generateName());
        }
    }

    public void addWorker(String name)
    {
        this.workers.add(new Worker(name));
    }

    public void stopWorking()
    {
        this.working = false;
    }



}