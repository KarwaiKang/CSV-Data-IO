import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderInJava {
    public static void main(String[] arg) {
        List<Book> books = readBooksFromCSV("src/books.txt");

        for (Book b : books)
            System.out.println(b);
    }

    private static List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            // The line is null if the EOF (End of file) is reached.
            while (line != null) {
                // The comma (',') is the delimiter.
                String[] attributes = line.split(",");
                books.add(new Book(attributes[0], attributes[2], Integer.parseInt(attributes[1])));
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return books;
    }
}
