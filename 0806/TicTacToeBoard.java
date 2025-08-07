
public class TicTacToeBoard {

    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return checkWin() || isBoardFull();
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void displayBoard() {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  ---------");
            }
        }
        System.out.println();
    }

    public void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        System.out.println("=== 井字遊戲測試 ===\n");

        TicTacToeBoard game = new TicTacToeBoard();

        System.out.println("初始棋盤:");
        game.displayBoard();

        System.out.println("玩家 " + game.getCurrentPlayer() + " 在 (1,1) 放棋:");
        game.makeMove(1, 1);
        game.displayBoard();
        game.switchPlayer();

        System.out.println("玩家 " + game.getCurrentPlayer() + " 在 (0,0) 放棋:");
        game.makeMove(0, 0);
        game.displayBoard();
        game.switchPlayer();

        System.out.println("玩家 " + game.getCurrentPlayer() + " 在 (0,1) 放棋:");
        game.makeMove(0, 1);
        game.displayBoard();
        game.switchPlayer();

        System.out.println("玩家 " + game.getCurrentPlayer() + " 在 (2,2) 放棋:");
        game.makeMove(2, 2);
        game.displayBoard();
        game.switchPlayer();

        System.out.println("玩家 " + game.getCurrentPlayer() + " 在 (2,1) 放棋:");
        game.makeMove(2, 1);
        game.displayBoard();

        if (game.checkWin()) {
            System.out.println("🎉 玩家 " + game.getCurrentPlayer() + " 獲勝！");
        } else {
            game.switchPlayer();
            System.out.println("遊戲繼續...");
        }

        System.out.println("\n=== 測試無效移動 ===");
        System.out.println("嘗試在已佔用位置 (1,1) 放棋:");
        boolean success = game.makeMove(1, 1);
        System.out.println("移動" + (success ? "成功" : "失敗"));

        System.out.println("嘗試在邊界外 (3,3) 放棋:");
        success = game.makeMove(3, 3);
        System.out.println("移動" + (success ? "成功" : "失敗"));
    }

}
