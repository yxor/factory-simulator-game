import java.util.ListIterator;
import java.util.Scanner;

enum Commands {
    NEXT_DAY("Next Day"),
    SHOW_WORKERS("Show Workers"),
    FIRE_WORKER("Fire A Worker"),
    QUIT("Quit Game");

    String name;
    Commands(String label)
    {
        this.name = label;
    }

    @Override
    public String toString() {
        return this.name;
    }
}


class ConsoleManager
{
    private static Factory factory;

    private static void setup()
    {
        Scanner s = new Scanner(System.in);
        String name;
        System.out.print("Welcome to factory simulator!\nplease enter your factory's name:\n>>");
        name = s.nextLine();
        factory = new Factory(name, 1000);
    }

    static void mainLoop()
    {
        setup();
        Commands command;
        while(factory.isWorking())
        {
            showStats();
            showCommands();
            command = getCommand();
            handleCommand(command);
        }
    }


    private static Commands getCommand()
    {
        while(true) {
            System.out.print("Please pick a command from the list:\n>>");
            try {
                Scanner s = new Scanner(System.in);
                return Commands.values()[s.nextInt()];
            } catch (Exception e) {
                System.out.println("Error: Invalid command");
            }
        }
    }

    private static int getChoice(String promptText)
    {
        while(true) {
            System.out.print(promptText);
            try {
                Scanner s = new Scanner(System.in);
                return s.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Invalid choice");
            }
        }
    }

    private static void handleCommand(Commands command)
    {
        switch (command)
        {
            case NEXT_DAY:
                factory.nextDay();
                break;
            case SHOW_WORKERS:
                showWorkers();
                break;
            case FIRE_WORKER:
                int id = getChoice("Please provide the Worker ID:\n>>");
                factory.fireWorker(id);
                break;
            case QUIT:
                factory.stopWorking();
                break;
            default:
                break;
        }
    }
    private static void showCommands()
    {
        int counter = 0;
        for(Commands c: Commands.values())
        {
            System.out.printf("%d) %s\n", counter++, c);
        }
    }

    private static void showWorkers()
    {
        ListIterator workers = factory.getWorkersIterator();
        System.out.printf("You have %d Workers\n", factory.getPopulation());
        while(workers.hasNext())
        {
            System.out.printf("%d)%s\n", workers.nextIndex(), workers.next());
        }
    }

    private static void showStats()
    {
        String stats = String.format(
                "Factory: %s\n"+
                        "Days passed:%2d\tpopulation:%2d\t" +
                        "Your Balance:$%4d",
                factory.getName(), factory.getDayCount(), factory.getPopulation(), factory.getBalance());
        System.out.println(stats);
    }
}
