package utils;

import java.util.*;

import utils.InvertedIndex;

public class Query {
    private static Map<String, ArrayList<Integer>> index = InvertedIndex.getIndex();

    public static void searchOR(String[] words) {
        ArrayList<Integer> num = new ArrayList<>();

        for (String word : words) { //O(n)
            word = word.toLowerCase();
            if (index.containsKey(word)) {
                ArrayList<Integer> docIds = index.get(word);
                Iterator<Integer> iterator = docIds.iterator();

                while (iterator.hasNext()) {
                    int docId = iterator.next();
                    int docNumber = docId + 1;

                    if (!num.contains(docNumber)) {
                        num.add(docNumber);
                    }
                }
            }
        }

        Collections.sort(num);

        System.out.println("\nKata ditemukan di dokumen: ");
        System.out.println(num);
    }


    public static void searchOROG(String[] words) {
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

    public static void searchAND(String[] words) {
        List<Integer> num = new ArrayList<>();
        boolean isFound = false;

        for (String word : words) {
            word = word.toLowerCase();
//            System.out.println(index);
            if (index.containsKey(word)) {
                List<Integer> docIds = index.get(word);
                System.out.println("AND" + word + docIds);
                if (num.isEmpty()) {
                    num.addAll(docIds);
                } else {
                    filterDifference(num, docIds);
                }
                isFound = true;
            } else {
                System.out.println(word + "[]");
                isFound = false;
            }
        }

        System.out.println("\nKata ditemukan di dokumen: ");
        if (isFound && !num.isEmpty()) {
            for (int id : num) {
                System.out.print((id + 1) + " ");
            }
        } else {
            System.out.println("[]");
        }
    }


    public static void filterDifference(List<Integer> base, List<Integer> filter) {
        Iterator<Integer> iterator = base.iterator();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
            if (!filter.contains(item)) {
                iterator.remove();
            }
        }
    }


    public static void searchANDOG(String[] words) {
        List<Integer> num = new ArrayList<>();
        boolean isFound = false;

        for (String word : words) {
            word = word.toLowerCase();
            if (index.containsKey(word)) {
                List<Integer> docIds = index.get(word);
                System.out.println("OG" + docIds);
                if (num.isEmpty()) {
                    num.addAll(docIds);
                } else {
                    num.retainAll(docIds);
                }
                isFound = true;
            } else {
                System.out.println("[]");
                isFound = false;
                break;
            }
        }
        System.out.println("\nKata ditemukan di dokumen: ");
        if (isFound) {
            for (int id : num) {
                System.out.print((id + 1) + " ");
            }
        } else {
            System.out.println("[]");
        }
    }
}
