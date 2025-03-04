package com.problems.linkedLists;

import java.util.*;

/*
* Question: LRU Cache
* Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
*
* Implement the LRUCache class:
* - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
* - int get(int key) Return the value of the key if the key exists, otherwise return -1.
* - void put(int key, int value) Update the value of the key if the key exists.
*   Otherwise, add the key-value pair to the cache.
*   If the number of keys exceeds the capacity from this operation,
*   evict the least recently used key.
* The functions get and put must each run in O(1) average time complexity.
* */
public class LRUCache {
    private static class CustomLRUCache {
        /*
        * Intent:
        * 1. Create cache with capacity
        * 2. When calling get(key) -> Remove key node and add again to maintain sequence of usage
        * 3. when calling put(key,val) -> create node and add it in cache, if capacity breached remove least recently used
        *
        * addNode - add latest accessed/ newly created node at tail side
        * removeNode - Remove least used node from head side
        * */
        private int capacity;
        private class Node {
            int key;
            int val;
            Node next;
            Node prev;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                this.prev = null;
                this.next = null;
            }
        }

        private Node head;
        private Node tail;
        Map<Integer,Node> cacheMap;

        public CustomLRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            cacheMap = new HashMap<>();
            System.out.print("null ");
        }

        public int get(int key) {
            if(!cacheMap.containsKey(key)) {
                System.out.print("-1 ");
                return -1;
            }

            Node node = cacheMap.get(key);
            System.out.print(node.val + " ");

            //This is to make sure we maintain sequence of recent usage
            removeNode(node);
            addNode(node);
            return node.val;
        }

        public void put(int key, int value) {
            if(cacheMap.containsKey(key)) {
                Node node = cacheMap.get(key);
                removeNode(node);

            }

            Node node = new Node(key, value);
            addNode(node);
            cacheMap.put(key,node);

            if (cacheMap.size() > capacity) {
                Node nodeToDelete = head.next;
                removeNode(nodeToDelete);
                cacheMap.remove(nodeToDelete.key);
            }
            System.out.print("null ");
        }

        public void addNode(Node node) {
            node.prev = tail.prev;
            tail.prev.next = node;
            node.next = tail;
            tail.prev = node;
        }

        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private static class BuiltInLRUCache {
        private LinkedHashMap<Integer, Integer> cacheMap;

        public BuiltInLRUCache(int capacity) {
            cacheMap = new LinkedHashMap<>(5, 0.75F, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
            System.out.print("null ");
        }

        public int get(int key) {
            int value = (int)cacheMap.getOrDefault(key, -1);
            System.out.print(value+" ");
            return value;
        }

        public void put(int key, int value) {
            cacheMap.put(key,value);
            System.out.print("null ");
        }
    }
    public static void main(String[] args) {
        /*
         * Input:
         * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
         * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
         *
         * Output:
         * [null, null, null, 1, null, -1, null, -1, 3, 4]
         * */
        System.out.println();
        //Approach I - Custom LRU Cache Implementation using LinkedList and HashMap
        CustomLRUCache customLRUCache = new CustomLRUCache(2);
        customLRUCache.put(1,1);
        customLRUCache.put(2,2);
        customLRUCache.get(1);
        customLRUCache.put(3,3);
        customLRUCache.get(2);
        customLRUCache.put(4,4);
        customLRUCache.get(1);
        customLRUCache.get(3);
        customLRUCache.get(4);

        System.out.println("\n");
        //Approach II - Using built-in class LinkedHashMap
        BuiltInLRUCache builtInLRUCache = new BuiltInLRUCache(2);
        builtInLRUCache.put(1,1);
        builtInLRUCache.put(2,2);
        builtInLRUCache.get(1);
        builtInLRUCache.put(3,3);
        builtInLRUCache.get(2);
        builtInLRUCache.put(4,4);
        builtInLRUCache.get(1);
        builtInLRUCache.get(3);
        builtInLRUCache.get(4);

        /*
        * Time Complexity :
        *   get(key) = o(1)
        *   put(key,value) = o(1)
        * Space Complexity = o(capacity)
        * */
    }
}
