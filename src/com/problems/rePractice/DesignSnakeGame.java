package com.problems.rePractice;

import com.problems.simulation.SnakeGame;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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
public class DesignSnakeGame {

    public static class Pair {
        private final int i;
        private final int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private final int width;
    private final int height;
    private final int[][] food;
    private int foodIndex;
    private final HashSet<Pair> snakeBody;
    private final Deque<Pair> snake;

    /*
     * Initialise datastructures here
     *
     * @param width - width of the game board
     * @param height - height of the game board
     * @param food - Array of food co-ordinates that occur on grid pne after another
     * */
    public DesignSnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodIndex = 0;

        snakeBody = new HashSet<>();
        snake = new LinkedList<>();

        //Add snake head
        Pair pair = new Pair(0,0);
        snakeBody.add(pair);
        snake.offerFirst(pair);
    }

    /*
     * Moves snake in given direction by one cell
     *
     * @param direction- "U" - Up, "D" - Down, "L" - Left, "R" - Right
     * @return - score of the game and -1 if game terminated
     * */
    int move(String direction) {
        /*
        * Below can be the number of use cases when a snake moves:
        * 1. Snake hits any of the boundaries
        * 2. Snake bites itself
        * 3. Snake eats food and increases by 1
        * 4. Snake just moves from one place to another by 1 unit
        * */
        Pair snakeHead = snake.peekFirst();
        int currentRow = snakeHead.i;
        int currentCol = snakeHead.j;

        switch(direction) {
            case "U":
                currentRow --;
                break;

            case "D":
                currentRow ++;
                break;

            case "L":
                currentCol --;
                break;

            case "R":
                currentCol ++;
                break;
        }

        Pair currentCell = new Pair(currentRow,currentCol);

        //Check if snake hits boundaries
        if(currentRow < 0 || currentCol < 0 || currentRow == height || currentCol == width)
            return -1;
        else if(foodIndex < food.length && (currentRow == food[foodIndex][0] && currentCol == food[foodIndex][1])) {
            //Check if its a food move
            foodIndex++;
        } else {
            //Snake is just moving
            Pair snakeTail = snake.pollLast();
            snakeBody.remove(snakeTail);

            //check if snake bites itself
            if(snakeBody.contains(currentCell))
                return -1;
        }
        //Snake moves freely
        snake.offerFirst(currentCell);
        snakeBody.add(currentCell);

        return snakeBody.size()-1;
    }

    public static void main(String args[]) {
        //Example 1
        DesignSnakeGame game = new DesignSnakeGame(3,2,new int[][]{{1,2},{0,1}});
        String[] instructions = new String[]{"R","D","R","U","L","U"};
        System.out.println("\nResult: ");
        for(String instruction: instructions)
            System.out.print(game.move(instruction)+ " ");

        //Example 2
        game = new DesignSnakeGame(3,3,new int[][]{{2,0},{0,0},{0,2},{2,2}});
        instructions = new String[]{"D","D","R","U","U","L","D","R","R","U","L","D"};
        System.out.println("\nResult: ");
        for(String instruction: instructions)
            System.out.print(game.move(instruction)+ " ");

        //Example 3
        game = new DesignSnakeGame(3,3,new int[][]{{0,1},{0,2},{1,2},{2,2},{2,1},{2,0},{1,0}});
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
