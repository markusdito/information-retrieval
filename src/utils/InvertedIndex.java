package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InvertedIndex {

    private static Map<String, ArrayList<Integer>> index = new HashMap<>();

    public static Map<String, ArrayList<Integer>> getIndex() {
        return index;
    }

    public void buatIndex(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("Folder tidak ditemukan atau kosong.");
            return;
        }

        File[] sortedFiles = sortFilesByNumber(files);

        int docId = 0;
        for (File file : sortedFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                // read files content here
                String content = readFile(file);
                String[] words = content.toLowerCase().split("\\W+");

                // do preprocess here
//                System.out.println(Arrays.toString(words));

                for (String word : words) {
                    if (word.isEmpty()) continue;

                    //build index here
                    index.putIfAbsent(word, new ArrayList<>());
                    if (!index.get(word).contains(docId)) {
                        index.get(word).add(docId);
                    }
                }
                docId++;
            }
        }
//        System.out.println(Arrays.toString(files));
    }

    private String readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append(" ");
        }
        reader.close();
        return content.toString();
    }

    private static File[] sortFilesByNumber(File[] files) {
        Arrays.sort(files, Comparator.comparingInt(f -> extractNumber(f.getName())));
        return files;
    }

    private static int extractNumber(String filename) {
        try {
            String nameWithoutExtension = filename.contains(".")
                    ? filename.substring(0, filename.lastIndexOf('.'))
                    : filename;
            return Integer.parseInt(nameWithoutExtension.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}


