package com.problems.linkedLists;

/*
* Question:
* Given the head of a singly linked list, reverse the list, and return the reversed list.
* Input: head = [1,2,3,4,5]
* Output: [5,4,3,2,1]
*/
public class ReverseSinglyLinkedList {
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

    private static void printList(Node node) {
        System.out.println();
        while(node != null) {
            System.out.print("-> " + node.val);
            node = node.next;
        }
    }
    private static Node reverseList(Node head) {
        if(head == null || head.next == null)
            return head;

        Node ptr, temp;

        ptr = head;
        head = head.next;
        ptr.next = null;

        while(head != null) {
            temp = head;
            head = head.next;

            temp.next = ptr;
            ptr = temp;
        }
        return ptr;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        Node head = reverseList(createList(new int[] {1,2,3,4,5}));
        printList(head);

        head = reverseList(createList(new int[] {1,2}));
        printList(head);

        head = reverseList(createList(new int[] {}));
        printList(head);
    }
}
