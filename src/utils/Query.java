package utils;

import java.util.*;

import utils.InvertedIndex;

public class Query {
    private static Map<String, List<Integer>> index = InvertedIndex.getIndex();
    private static List<String> documents = InvertedIndex.getDocuments();

    public static void searchOR(String[] words) {
//        word = word.toLowerCase();
        Set<Integer> num = new HashSet<>();

        for (String word : words) {
            word = word.toLowerCase();
            if (index.containsKey(word)) {
                List<Integer> docIds = index.get(word);
//                System.out.println("\nKata ditemukan di dokumen(print semua): ");
                for (int docId : docIds) {
//                    System.out.print(docId + 1 + " ");
                    num.add(docId + 1);
                }
//            System.out.println();
//            for (int id : docIds) {
//                System.out.println("Doc " + (id + 1) + ": " + documents.get(id));
//            }
            } else {
                System.out.println("\nKata tidak ditemukan.");
            }
        }
        System.out.println("\nKata ditemukan di dokumen(gabungan): ");
        System.out.println(num);
    }
}
