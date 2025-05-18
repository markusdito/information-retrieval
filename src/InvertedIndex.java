import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {

  private Map<String, List<Integer>> index = new HashMap<>();
    private List<String> documents = new ArrayList<>();

    public void buatIndex(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("Folder tidak ditemukan atau kosong.");
            return;
        }

        int docId = 0;
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = readFile(file);
                documents.add(content);

                String[] words = content.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (word.isEmpty()) continue;

                    index.putIfAbsent(word, new ArrayList<>());
                    if (!index.get(word).contains(docId)) {
                        index.get(word).add(docId);
                    }
                }
                docId++;
            }
        }

        System.out.println(Arrays.toString(files));
    }

    public void search(String word) {
        word = word.toLowerCase();
        if (index.containsKey(word)) {
            List<Integer> docIds = index.get(word);
            System.out.println("Kata ditemukan di dokumen: " + docIds);
            for (int id : docIds) {
                System.out.println("Doc " + (id) + ": " + documents.get(id));
            }
        } else {
            System.out.println("Kata tidak ditemukan.");
        }
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
}


