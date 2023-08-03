import java.util.Scanner;

public class MazeRunnerGame {
	
    // Printing the Array
	private static final char[][] maze = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', 'P', '.', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '.', '#', '#'},
            {'.', '.', '.', '.', '.', '.', '#'},
            {'#', '#', '#', '.', '#', '#', '#'},
            {'.', '.', '.', '.', '.', 'E', '#'},
            {'#', '#', '#', '#', '#', '#', '#'}
    };
	
	
	// ANOTHER MATRIX TO EXPLORE THE GAME;
	
	
//    private static final char[][] maze = {
//            {'#', '#', '#', '#', '#', '#', '#'},
//            {'#', 'P', '.', '.', '.', '.', '#'},
//            {'#', '#', '#', '#', '.', '#', '#'},
//            {'.', '.', '.', '.', '.', '.', '#'},
//            {'#', '#', '#', '.', '#', '#', '#'},
//            {'.', '.', 'E', '.', '.', '#', '#'},
//            {'#', '#', '#', '#', '#', '#', '#'}
//    };

    // Globally defining player's position coordinates
    
    private static int playerX;
    private static int playerY;

    // Globally defining game variables
    
    private static int numberOfSteps;
    private static int score;
    private static int highScore;
    private static long startTime;
    private static final long timeLimit = 15000; // 15 seconds


    // Scanner for user input
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
    	showMainMenu();
    }

    private static void showMainMenu() {
        char choice;
        do {
            System.out.println("\n**** Main Menu ****");
            System.out.println("a. Play Game");
            System.out.println("b. Instructions");
            System.out.println("c. Credits");
            System.out.println("d. High Score");
            System.out.println("e. Exit");
            System.out.print("\nSelect an option: ");
            choice = scanner.next().charAt(0);
            
            scanner.nextLine(); // Clearing the newline character

            switch (choice) {
                case 'a':
                    startNewGame();
                    playGame();
                    break;
                case 'b':
                    showInstructions();
                    break;
                case 'c':
                    showCredits();
                    break;
                case 'd':
                    showHighScore();
                    break;
                case 'e':
                    exitGame();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 'e');
    }    

    private static void initializeMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'P') {
                    playerX = i;
                    playerY = j;
                    return; // returning only one starting position in the maze
                }
            }
        }
    }

    private static void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if the player has won or not
    
    private static char hasPlayerWon() {
        return maze[playerX][playerY];
    }

    // Method to check the players next Position
    
    private static char isValidMove(char direction) {
        int newX = playerX;
        int newY = playerY;

        switch (direction) {
            case 'W':
            case 'w':
                newX--;
                break;
            case 'A':
            case 'a':
                newY--;
                break;
            case 'S':
            case 's':
                newX++;
                break;
            case 'D':
            case 'd':
                newY++;
                break;
            default:
                return '#'; // returning Invalid move
        }

        if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[newX].length) {
            return maze[newX][newY];
        } else {
            return '#'; // returning Out of maze boundaries
        }
    }

    private static void movePlayer(char direction) {
        int newRow = playerX;
        int newCol = playerY;

        switch (direction) {
            case 'W':
            case 'w':
                newRow--;
                break;
            case 'A':
            case 'a':
                newCol--;
                break;
            case 'S':
            case 's':
                newRow++;
                break;
            case 'D':
            case 'd':
                newCol++;
                break;
            default:
                System.out.println("Invalid move. Please enter W/A/S/D.");
                return;
        }

        char nextPosition = isValidMove(direction);

        if (nextPosition == 'E') {
            numberOfSteps++;
            maze[playerX][playerY] = '.'; // Updating the previous position with '.'
            playerX = newRow;
            playerY = newCol;
            return; // Exiting the method when the player reaches the exit
        }

        if (nextPosition == '.') {
            numberOfSteps++;
            maze[playerX][playerY] = '.'; // Updating the previous position with '.'
            playerX = newRow;
            playerY = newCol;
            maze[playerX][playerY] = 'P'; // Updating player position in the maze
        } else if (nextPosition == '#') {
            System.out.println("Invalid move. Cannot move there.");
            score--;
        }
    }

    private static void startNewGame() {
        initializeMaze();
        numberOfSteps = 0;
        playerX = 1; // Set player's X position to the default row
        playerY = 1; // Set player's Y position to the default column
    }


    private static void showInstructions() {
        
        System.out.println("Instructions:");
        System.out.println("\n To play this game, you will have 10 seconds \n to reach Exit. To move you can use the provided inputs.");
    }

    private static void showCredits() {
        
        System.out.println("Credits:");
        System.out.println("\nThis game is created completely by Akhmas Balouch 21-10832, hope you loved it!");
    }

    private static void showHighScore() {
        System.out.println("High Score: " + highScore);
    }
    
    private static void askToPlayAgain() {
        char choice;
        do {
            System.out.print("Play again? (Y/N): ");
            choice = scanner.next().charAt(0);
            scanner.nextLine(); // Clear the newline character

            if (choice == 'Y' || choice == 'y') {
                startNewGame();
                playGame();
            } else if (choice == 'N' || choice == 'n') {
                System.out.println("Thank you for playing!");
                showMainMenu();
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        } while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n');
    }

    private static void exitGame() {
        System.out.println("Exiting the game. Goodbye!");
        System.exit(0);
    }

    
    private static void playGame() {
        initializeMaze();
        numberOfSteps = 0;
        score = 0;

        startTime = System.currentTimeMillis(); // Reset the start time when the game starts

        char move;
        do {
            printMaze();
            System.out.println("Number of Steps: " + numberOfSteps);
            System.out.println("Score: " + score);
            System.out.println("Enter your move (W/A/S/D): ");
            
            
            // Starting the timer thread
            Thread timerThread = new Thread(() -> {
                try {
                    Thread.sleep(timeLimit); // Sleep for the time limit duration
                    if (!Thread.interrupted()) {
                        System.out.println("\nTime's up! You didn't enter a move.");
                        exitGame();
                    }
                } catch (InterruptedException e) {
                    // Do nothing if the thread is interrupted
                }
            });
            timerThread.start();

            move = scanner.next().charAt(0);
            scanner.nextLine(); // Clear the newline character

            // Stop the timer thread if the player entered a move within the time limit
            if (timerThread.isAlive()) {
                timerThread.interrupt();
            }

            movePlayer(move);

            char position = hasPlayerWon();
            score += 10;
            if (position == 'E') {
                long endTime = System.currentTimeMillis();
                long timeTaken = (endTime - startTime) / 1000; // Calculate the time taken in seconds
                System.out.println("\nCongratulations! You reached the exit in " + timeTaken + " seconds!");
                break;
            }
        } while (true);

        displayResult();
        System.out.println();
        askToPlayAgain();
    }

    public static void displayResult() {
        System.out.println("Total Steps: " + numberOfSteps);
        System.out.println("Final Score: " + score);
        if (score > highScore) {
            highScore = score;
            System.out.println("New High Score: " + highScore);
        }
    }
}
