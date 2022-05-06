package UserInterface;

import Domain.SystemController;

import java.util.Scanner;

public class UserInterface
{
    SystemController systemController;


    public UserInterface()
    {
        this.systemController = new SystemController();
    }

    public void runMenu()
    {
        boolean flag = true;
        while(flag)
        {
            System.out.println("Please pick your preferred option from the menu below:");
            System.out.println("0. Exit");
            System.out.println("1. Login User");
            System.out.println("2. Add Referee");
            System.out.println("3. Assign Game");

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String input = myObj.nextLine();  // Read user input

            switch (input)
            {
                case "0": // exit
                    flag = false;
                    break;

                case "1": // Login User
                    System.out.println("Please enter your userId:");
                    myObj = new Scanner(System.in);
                    String userID = myObj.nextLine();

                    System.out.println("Please enter your user password:");
                    myObj = new Scanner(System.in);
                    String password = myObj.nextLine();

                    systemController.loginUser(userID, password);
                    break;

                case "2": // Add Referee
                    System.out.println("Please enter the Referee name:");
                    myObj = new Scanner(System.in);
                    String name = myObj.nextLine();

                    System.out.println("Please enter the Referee qualification:");
                    myObj = new Scanner(System.in);
                    String qualification = myObj.nextLine();

                    System.out.println("Please enter the Referee leagueId:");
                    myObj = new Scanner(System.in);
                    String leagueId = myObj.nextLine();

                    systemController.addReferee(name, qualification, leagueId);
                    break;

                case "3": // Assign Game
                    System.out.println("Please enter the gameId:");
                    myObj = new Scanner(System.in);
                    String gameId = myObj.nextLine();

                    System.out.println("Please enter the first team in the game:");
                    myObj = new Scanner(System.in);
                    String teamOne = myObj.nextLine();

                    System.out.println("Please enter the second team in the game:");
                    myObj = new Scanner(System.in);
                    String teamTwo = myObj.nextLine();

                    System.out.println("Please enter the game leagueId:");
                    myObj = new Scanner(System.in);
                    String leagueIdOfTheGame = myObj.nextLine();

                    System.out.println("Please enter the Date and time of the game:");
                    myObj = new Scanner(System.in);
                    String dateAndTime = myObj.nextLine();

                    System.out.println("Please enter the Referees in the game:");
                    myObj = new Scanner(System.in);
                    String referees = myObj.nextLine();

                    System.out.println("Please enter the game field:");
                    myObj = new Scanner(System.in);
                    String field = myObj.nextLine();

                    systemController.assignGame(gameId, teamOne, teamTwo, leagueIdOfTheGame, dateAndTime, referees, field);
                    break;

                default:
                    System.out.println("You entered a wrong input!");
            }
        }

    }

}
