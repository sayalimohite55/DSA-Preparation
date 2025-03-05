package com.problems.linkedLists;

/*
* Question:
* You are given two non-empty linked lists representing two non-negative integers.
* The digits are stored in reverse order, and each of their nodes contains a single digit.
* Add the two numbers and return the sum as a linked list.
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
*
* Input: l1 = [2,4,3], l2 = [5,6,4]
* Output: [7,0,8]
* Explanation: 342 + 465 = 807.
*
* The number of nodes in each linked list is in the range [1, 100].
* 0 <= Node.val <= 9
* It is guaranteed that the list represents a number that does not have leading zeros.
* */
public class AddTwoNumbers {
    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node createList(int[] arr) {
        Node head = null, temp = null;
        for(int i=0;i<arr.length; i++) {
            Node newNode = new Node(arr[i]);
            if(head == null) {
                head = temp = newNode;
            } else {
                temp.next = newNode;
                temp = newNode;
            }
        }
        return head;
    }

    private static Node appendNode(Node head, int value) {
        Node newNode = new Node(value);
        if(head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while(temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
        return head;
    }

    private static void printList(Node node) {
        System.out.println();
        while(node != null) {
            System.out.print("-> " + node.val);
            node = node.next;
        }
    }

    private static Node addTwoNumbers(Node l1, Node l2) {
        Node head = null;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0) {
            if(l1 != null && l2 != null) {
                int sum = l1.val + l2.val + carry;
                carry = sum / 10;
                sum = sum%10;

                head = appendNode(head, sum);

                l1 = l1.next;
                l2 = l2.next;
            } else if(l1 != null) {
                int sum = l1.val + carry;
                carry = sum / 10;
                sum = sum%10;

                head = appendNode(head, sum);

                l1 = l1.next;
            } else if( l2 != null) {
                int sum = l2.val + carry;
                carry = sum / 10;
                sum = sum%10;

                head = appendNode(head, sum);
                l2 = l2.next;
            } else { //Carry exists
                head = appendNode(head, carry);
                carry = 0;
            }
        }
        return head;
        /*
        * Time Complexity : o(max(m,n)) --> where m is length of list1 and n is length of list2
        * Space Complexity: o(1)
        * */
    }

    public static void main(String[] args) {
        //Use case 1: add 243 + 564 => 708
        Node list1 = createList(new int[]{2,4,3});
        Node list2 = createList(new int[]{5,6,4});

        Node output = addTwoNumbers(list1, list2);
        printList(output);

        //Use case 2: add 0 + 0 => 0
        list1 = createList(new int[]{0});
        list2 = createList(new int[]{0});

        output = addTwoNumbers(list1, list2);
        printList(output);

        //Use case 3: add 9999999 + 9999 => 89990001
        list1 = createList(new int[]{9,9,9,9,9,9,9});
        list2 = createList(new int[]{9,9,9,9});

        output = addTwoNumbers(list1, list2);
        printList(output);
    }
}
