package utils;

import java.util.*;

import utils.InvertedIndex;

public class Query {
    private static Map<String, List<Integer>> index = InvertedIndex.getIndex();
    private static List<String> documents = InvertedIndex.getDocuments();

    public static void searchOR(String[] words) {
        Set<Integer> num = new HashSet<>();

        for (String word : words) {
            word = word.toLowerCase();
            if (index.containsKey(word)) {
                List<Integer> docIds = index.get(word);
//                System.out.println("Kata ditemukan pada:");
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
