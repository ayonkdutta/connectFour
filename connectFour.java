import java.util.Scanner;
class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String[][] board = new String[6][7];
    private static String letter = " ";


    public static void Show() { // creates board layout with lines
        System.out.println("───────────────────────────");
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " | "
                + board[0][3] + " | " + board[0][4] + " | " + board[0][5] + " | " + board[0][6]);
        System.out.println("───────────────────────────");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " | "
                + board[1][3] + " | " + board[1][4] + " | " + board[1][5]+" | " + board[1][6]);
        System.out.println("───────────────────────────");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " | "
                + board[2][3] + " | " + board[2][4] + " | " + board[2][5]+" | " + board[2][6]);
        System.out.println("───────────────────────────");
        System.out.println(board[3][0] + " | " + board[3][1] + " | " + board[3][2] + " | "
                + board[3][3] + " | " + board[3][4] + " | " + board[3][5]+" | " + board[3][6]);
        System.out.println("───────────────────────────");
        System.out.println(board[4][0] + " | " + board[4][1] + " | " + board[4][2] + " | "
                + board[4][3] + " | " + board[4][4] + " | " + board[4][5]+" | " + board[4][6]);
        System.out.println("───────────────────────────");
        System.out.println(board[5][0] + " | " + board[5][1] + " | " + board[5][2] + " | "
                + board[5][3] + " | " + board[5][4] + " | " + board[5][5]+" | " + board[5][6]);
        System.out.println("───────────────────────────");


    }

    public static void Player() {
        System.out.println();
        int columns = sc.nextInt() - 1;
        int rows = board.length - 1;
        if (columns > 6 || columns <= -1) {
            System.out.println("Please enter a valid number!");
            Player();
        }

        while (!board[rows][columns].equals(" ") || board[rows][columns].equals("●") || board[rows][columns].equals("○")) {
            rows--;
            if (rows == 0) {
                break;
            }
        }
        if (board[0][columns] != " ") {
            System.out.println("No space! Pick a different column!");
            Player();
        } else {
            board[rows][columns] = "○";
            Show();
        }
    }

    public static boolean Tie() { // checks if game results in a tie
        boolean Tie = true;
        boolean Break = false;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j].equals(" ")) {
                    Tie = false;
                    Break = true;
                    break;
                }
            }
            if (Break)
                break;
        }
        return Tie;
    }

    public static boolean checkWin() { //checks various orientations for a win
        int count = 0;
        for (int i = 0; i < board.length; i++) { // vertical
            for (int j = 0; j < board[0].length-3; j++) {
                if (!board[i][j].equals(" ")
                        && board[i][j].equals(board[i][j+1])
                        && board[i][j].equals(board[i][j+2])
                        && board[i][j].equals(board[i][j+3])) {
                    letter = board[i][j];
                    return true;
                }
            }
        }

        for (int i = 0; i < board.length-3; i++) { // horizontal
            for (int j = 0; j < board[0].length; j++) {
                if (!board[i][j].equals(" ")
                        && board[i][j].equals(board[i+1][j])
                        && board[i][j].equals(board[i+2][j])
                        && board[i][j].equals(board[i+3][j])) {
                    letter = board[i][j];
                    return true;
                }
            }
        }
        for (int i = 0; i < board.length-3; i++) { // diagonal backwards
            for (int j = 0; j < board[0].length-3; j++) {
                if (!board[i][j].equals(" ")
                        && board[i][j].equals(board[i+1][j+1])
                        && board[i][j].equals(board[i+2][j+2])
                        && board[i][j].equals(board[i+3][j+3])) {
                    letter = board[i][j];
                    return true;
                }
            }
        }
        for (int i = board.length-1; i > 3; i--) { // diagonal forwards
            for (int j= 0; j < board[0].length-3; j++) {
                if (!board[i][j].equals(" ")
                        && board[i][j].equals(board[i-1][j+1])
                        && board[i][j].equals(board[i-2][j+2])
                        && board[i][j].equals(board[i-3][j+3])) {
                    letter = board[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    public static void Computer() {
        System.out.println();
        System.out.println("Computer's Turn");
        int y = y();
        int x = x(y);

        if (!(board[x][y].equals("○") || board[x][y].equals("●"))) {
            board[x][y] = "●";
        } else {
            while(board[x][y].equals("○") || board[x][y].equals("●")){
                x--;
                if(x==0){
                    break;
                }
            }
            if (!(board[x][y].equals("○") || board[x][y].equals("●"))) {
                board[x][y] = "●";
            } else {
                Computer();
            }
        }

        Show();
    }
    public static int y(){
        return (int)(Math.random() * 7);
    }
    public static int x(int y){
        int temp = board.length-1;
        while(board[temp][y] != " "){
            temp--;
            if(temp==0){
                break;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println("Computer is ● you are ○");
        System.out.println("Enter column (y)");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }

        while (!checkWin()) {
            Player();
            if (checkWin() || Tie())
                break;
            Computer();
            if (checkWin() || Tie())
                break;

        }
        if (Tie()) {
            System.out.println("Tie!");
        }
        if (checkWin() && letter.equals("●")) {
            System.out.println("Computer wins!");
        }
        if (checkWin() && letter.equals("○")) {
            System.out.println("You win!");
        }
    }

}

