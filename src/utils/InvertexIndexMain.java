package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import utils.Query;

public class InvertexIndexMain {
    public static void main(String[] args) throws IOException {
        InvertedIndex index = new InvertedIndex();
        String folderPath = "Koleksi (copy)";

        index.buatIndex(folderPath);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nMasukkan kata yang ingin dicari: ");
        String input = scanner.nextLine().trim();
        String[] inputSplitted = input.split("\\s+");

        if (!input.isEmpty()) {
            Query.searchOR(inputSplitted);
        } else {
            System.out.println("Input tidak boleh kosong.");
        }

        scanner.close();
    }
}