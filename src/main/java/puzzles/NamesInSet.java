package puzzles;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    09/12/13 00:54
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class NamesInSet {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final String first, last;
    public NamesInSet(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof NamesInSet)) { return false; }

        NamesInSet that = (NamesInSet) o;

        if (!first.equals(that.first)) { return false; }
        if (!last.equals(that.last)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + last.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NamesInSet{" +
            "first='" + first + '\'' +
            ", last='" + last + '\'' +
            '}';
    }



    public static void main(String[] args) throws Exception {
        tryWithResources("C:\\Users\\golaniz\\Desktop\\444.zip", "C:\\Users\\golaniz\\Desktop\\111\\444.txt");
    }

    public static void tryWithResources(String zipFileName, String outputFileName) throws java.io.IOException {

        java.nio.file.Path outputFilePath = Paths.get(outputFileName);

        // Open zip file and create output file with
        // try-with-resources statement

        try (

            ZipFile zf = new ZipFile(zipFileName);
            BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)
        ) {
            writer.write("BEGIN"+LINE_SEPARATOR);
            // Enumerate each entry
            Enumeration entries = zf.entries();
            while (entries.hasMoreElements()) {
                // Get the entry name and write it to the output file
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String zipEntryName = zipEntry.getName() + LINE_SEPARATOR;
                writer.write(zipEntryName, 0, zipEntryName.length());
                System.out.println(zipEntryName);
            }
            writer.write("END");
        }


    }
}

