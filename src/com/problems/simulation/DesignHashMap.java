package com.problems.simulation;

import java.util.List;
import java.util.LinkedList;
/*
* Question:
* Implement Hashmap right from the scratch
* Implement - get, put, delete, hash function, collision resolution if two value yields to same hash code.
* */
public class DesignHashMap {
    // Entry class to hold key-value pairs
    private static class Record {
        int key;
        int value;

        public Record(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_SIZE = 30;
    private LinkedList<Record>[] table;
    private int size;

    public DesignHashMap() {
        this.table = new LinkedList[DEFAULT_SIZE];
        this.size = 0;
    }

    public void put(int key, int value) {
        int hashIndex = this.hash(key);
        if (table[hashIndex] == null) {
            table[hashIndex] = new LinkedList<>();
        }

        // Check if key already exists in the list
        for(Record r: table[hashIndex]) {
            if(r.key == key) {
                r.value = value;
                return;
            }
        }

        //Key does not exist already
        Record record = new Record(key,value);
        table[hashIndex].add(record);
        this.size++;

        // Resize the table if load factor is too high (e.g., greater than 0.7)
        if ((1.0 * this.size) / table.length > 0.7) {
            this.resize();
        }
    }

    public int get(int key) {
        int hashIndex = this.hash(key);
        if(table[hashIndex] == null)
            return -1; //Return -1 if key not found

        for(Record r : table[hashIndex]){
            if(r.key == key)
                return r.value;
        }
        return -1; //Return -1 if key not found
    }

    public void remove(int key) {
        int hashIndex = this.hash(key);
        if(table[hashIndex] == null)
            return; //Return if key not found

        // Search and remove the entry if key exists
        LinkedList<Record> list = table[hashIndex];
        for(Record r : list){
            if(r.key == key) {
                list.remove(r);
                this.size--;
                return;
            }
        }
    }

    // Hash function to map key to an index in the table
    private int hash(int key) {
        return key % this.table.length;
    }

    // Resize method to double the table size when load factor exceeds threshold
    private void resize() {
        LinkedList<Record>[] newTable = new LinkedList[this.table.length * 2];
        for(LinkedList<Record> listBucket : table) {
            if(listBucket != null) {
                Record record = listBucket.get(0);
                int index = record.key % newTable.length;
                newTable[index] = listBucket;
            }
        }
        this.table = newTable;
    }

    public int getSize() {
        return this.size;
    }

    public static void main(String[] args) {
        DesignHashMap map = new DesignHashMap();
        map.put(1,12);
        map.put(2,24);
        map.put(4,14);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(4));
    }
}
