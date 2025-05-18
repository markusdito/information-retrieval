package utils;

import java.util.List;
import java.util.Map;

import utils.InvertedIndex;

public class Query {
    private static Map<String, List<Integer>> index = InvertedIndex.getIndex();
    private static List<String> documents = InvertedIndex.getDocuments();
    public static void search(String word) {
        word = word.toLowerCase();
        if (index.containsKey(word)) {
            List<Integer> docIds = index.get(word);
            System.out.println("Kata ditemukan di dokumen: ");
            for (int docId : docIds) {
                System.out.print(docId + 1 + " ");
            }
            System.out.println();
            for (int id : docIds) {
                System.out.println("Doc " + (id + 1) + ": " + documents.get(id));
            }
        } else {
            System.out.println("Kata tidak ditemukan.");
        }
    }
}
