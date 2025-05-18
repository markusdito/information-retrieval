
import java.io.IOException;
import java.util.Scanner;

public class InvertexIndexMain {
    public static void main(String[] args) throws IOException {
        InvertedIndex index = new InvertedIndex();
        String folderPath = "Koleksi";

        index.buatIndex(folderPath);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nMasukkan kata yang ingin dicari: ");
        String query = scanner.nextLine().trim();

        if (!query.isEmpty()) {
            index.search(query);
        } else {
            System.out.println("Input tidak boleh kosong.");
        }

        scanner.close();
    }
}