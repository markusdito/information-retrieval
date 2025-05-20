package utils;

import java.util.*;

import utils.InvertedIndex;

public class Query {
    private static Map<String, ArrayList<Integer>> index = InvertedIndex.getIndex();

    public static void searchOR(String[] words) {
        Set<Integer> num = new HashSet<>();

        for (String word : words) {
            word = word.toLowerCase();
            if (index.containsKey(word)) {
                ArrayList<Integer> docIds = index.get(word);
//                System.out.printf("\nKata %s ditemukan pada: \n", word);
                for (int docId : docIds) {
                    num.add(docId + 1);
//                    System.out.print(docId + 1 + " ");
                }
            } else {
//                System.out.println("\nKata tidak ditemukan.");
            }
        }
        System.out.println("\nKata ditemukan di dokumen: ");
        System.out.println(num);
    }

    //TODO: Fix print index
    public static void searchAND(String[] words) {
        List<Integer> num = new ArrayList<>();
        boolean isFound = false;

        for (String word : words) {
            word = word.toLowerCase();
            if (index.containsKey(word)) {
                List<Integer> docIds = index.get(word);
                if (num.isEmpty()) {
                    num.addAll(docIds);
                } else {
                    num.retainAll(docIds);
                }
                isFound = true;
            } else {
                isFound = false;
                break;
            }
        }
        System.out.println("\nKata ditemukan di dokumen: ");
        if (isFound) {
            for (int id : num) {
                System.err.print((id + 1) + " ");
            }
        } else {
            System.out.println("[]");
        }
    }
}
