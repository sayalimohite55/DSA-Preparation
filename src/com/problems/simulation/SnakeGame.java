package com.problems.simulation;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
* Question:
* Design a Snake game that is played on a device with screen size height x width.
* Play the game online if you are not familiar with the game.
* The snake is initially positioned in the top left corner (0, 0) with a length of 1 unit.
* You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food
* that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
* Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until
* the snake eats the first piece of food.
* When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied
* by the snake. The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a
* space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).
* Implement the SnakeGame class:
*   SnakeGame(int width, int height, int[][] food)
*       - Initializes the object with a screen of size height x width and the positions of the food.
*   int move(String direction)
*       - Returns the score of the game after applying one direction move by the snake.
*         If the game is over, return -1.
* */

public class SnakeGame {
    static class Pair{
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Deque<Pair> snake;
    Set<Pair> snakeCellsSet;
    int height;
    int width;
    int[][] food;
    int foodIndex;

    /*
    * Initialise datastructures here
    *
    * @param width - width of the game board
    * @param height - height of the game board
    * @param food - Array of food co-ordinates that occur on grid pne after another
    * */
    SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodIndex = 0;

        snake = new LinkedList<>();
        snakeCellsSet = new HashSet<>();

        //Add start index of snake which is 0,0
        Pair pair = new Pair(0, 0);
        snake.offerFirst(pair);
        snakeCellsSet.add(pair);
    }

    /*
    * Moves snake in given direction by one cell
    *
    * @param direction- "U" - Up, "D" - Down, "L" - Left, "R" - Right
    * @return - score of the game and -1 if game terminated
    * */
    int move(String direction) {
        Pair snakeHead = snake.peekFirst();
        int rowIndex = snakeHead.key;
        int columnIndex = snakeHead.value;

        switch(direction) {
            case "R":
                columnIndex++;
                break;
            case "L":
                columnIndex--;
                break;
            case "U":
                rowIndex--;
                break;
            case "D":
                rowIndex++;
                break;
        }

        /*
        * Use cases:
        * 1. Check boundaries (snake hits either of the walls of grid)
        * 2. Check if it's a food game (Snake eats food and score increases by 1)
        * 3. Snake is moved by 1 cell (Check if snake bites itself)
        * */
        boolean hitsBoundary = rowIndex < 0 || rowIndex == this.height || columnIndex < 0 || columnIndex == this.width;
        if(hitsBoundary)
            return -1;

        Pair newCell = new Pair(rowIndex, columnIndex);
        if(foodIndex < food.length && (rowIndex == food[foodIndex][0] && columnIndex == food[foodIndex][1])) {
            foodIndex++;
        } else {
            //Update snake grid values
            Pair snakeTail = snake.pollLast();
            snakeCellsSet.remove(snakeTail);

            //Check id snake bites itself
            if(snakeCellsSet.contains(newCell))
                return -1;
        }

        //Add new cell to snake parts
        snake.offerFirst(newCell);
        snakeCellsSet.add(newCell);
        return snake.size()-1;
    }

    public static void main(String args[]) {
        //Example 1
        SnakeGame game = new SnakeGame(3,2,new int[][]{{1,2},{0,1}});
        String[] instructions = new String[]{"R","D","R","U","L","U"};
        System.out.println("\nResult: ");
        for(String instruction: instructions)
            System.out.print(game.move(instruction)+ " ");

        //Example 2
        game = new SnakeGame(3,3,new int[][]{{2,0},{0,0},{0,2},{2,2}});
        instructions = new String[]{"D","D","R","U","U","L","D","R","R","U","L","D"};
        System.out.println("\nResult: ");
        for(String instruction: instructions)
            System.out.print(game.move(instruction)+ " ");

        //Example 3
        game = new SnakeGame(3,3,new int[][]{{0,1},{0,2},{1,2},{2,2},{2,1},{2,0},{1,0}});
        instructions = new String[]{"R","R","D","D","L","L","U","U","R","R","D","D","L","L","U","R","U","L","D"};
        System.out.println("\nResult: ");
        for(String instruction: instructions)
            System.out.print(game.move(instruction)+ " ");

        /*
        * Time Complexity = o(1)
        *   - Because we are using dequeue we need constant time o(1) for add/remove operations
        *   - As we are using HashSet we need constant look up time o(1)
        *
        * Space Complexity = o(width * height + n)
        *   - n is length of food array
        *   - Queue and Set will store width * height no of cells
        * */
    }
}
