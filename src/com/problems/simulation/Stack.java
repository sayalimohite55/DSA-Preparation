package com.problems.simulation;

/*
* Question:
* Create stack data structure from scratch without using any of the collections classes
* Implement stack push , pop , unlimited size, and stack top
* */
public class Stack {
    int[] arr;
    int size, top;

    public Stack() {
        this.size = 10;
        this.top = -1;
        this.arr = new int[size];
    }

    public void push(int val) {
        if(this.isFull()) {
            //Increase size dynamically
            int[] temp = new int[this.size];
            System.arraycopy(this.arr, 0, temp, 0, this.size);

            this.size = this.size * 2;
            arr = new int[this.size];
            System.arraycopy(temp, 0, this.arr, 0, temp.length);
            System.out.println("Increased stack size dynamically");
        }
        this.arr[++top] = val;
    }

    public int pop() {
        return this.arr[this.top--];
    }

    public int top() {
        return this.arr[this.top];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    private boolean isFull() {
        return this.size == top+1;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        int[] array = new int[]{1,2,3,0,0,4,5,6,7,9,1,1,2,3,0,7,8,9,0,1};

        for(int i: array) {
            if(i==0)
                System.out.print(stack.top()+" ");
            else
                stack.push(i);
        }

        System.out.println("\nLet's empty our stack: ");
        while(!stack.isEmpty())
            System.out.print(stack.pop()+" ");
    }
}
