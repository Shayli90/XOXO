package com.example.xoxoshayli;

public class modelXO
{
    public int[][] board = new int[3][3];
    /*
      0 0 0
      0 0 0
      0 0 0
     */
    private int currentPlayer = 1; // 1 or -1
    private int turnCounter = 0;

    private boolean gameOVer = false;

    public boolean checkWin() {
        // length is the number of rows
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                sum1 += board[i][j];
                sum2 += board[j][i];
            }
            if (sum1 == -3 || sum1 == 3 || sum2 == -3 || sum2 == 3)
            {
                gameOVer = true;
                return true;
            }
            sum1 = 0;
            sum2 = 0;
        }
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != 0) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != 0))
        {
            gameOVer = true;
            return true;
        }
        return false;
    }

    public boolean isEmpty(int row, int col)
    {
        return board[row][col] == 0;
    }

    public void restartGame()
    {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = 0;
            }
        gameOVer = false;
        currentPlayer = 1;
        turnCounter = 0;
    }

    public void makeMove(int row, int col)
    {
        board[row][col] = currentPlayer;
        if (currentPlayer == 1)
            currentPlayer = -1;
        else
            currentPlayer = 1;
        turnCounter++;
    }

    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean checkTie()
    {
        return turnCounter == 9;
    }

    public int TurnCount()
    {
        return turnCounter;
    }
}