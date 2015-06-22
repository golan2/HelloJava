package liat.lost.dir;

import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    22/12/13 21:19
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class FileRecognizer {

    private static final byte[] JPG = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, (byte) 0x00, (byte) 0x10, };


    public static void main(String[] args) {
        try
        {

            File dir = new File("C:\\Users\\golaniz\\Desktop\\LIAT.Lost.Dir");
            Tika tika = new Tika();
            for (File file : dir.listFiles()) {
                String fileType = tika.detect(file);
                System.out.println(file.getName() + ", " + fileType);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private static String findPrefix(File file) throws IOException {
        byte[] buffer = readFromFile(file);
        StringBuilder result = new StringBuilder();
        for (byte b : buffer) {
            result.append(String.format("%02X ", b));
        }
        result.append("\n");
        for (byte b : JPG) {
            result.append(String.format("%02X ", b));
        }
        return result.toString();
    }

    private static byte[] readFromFile(File file) throws IOException {
        byte[] buffer;
        try (FileInputStream fis = new FileInputStream(file)) {
            buffer = new byte[20];
            //noinspection ResultOfMethodCallIgnored
            fis.read(buffer);
        }

        return buffer;
    }

    private static boolean prefixArray(byte[] buffer, byte[] prefix) {
        for (int i = 0; i < prefix.length; i++) {
            if (buffer[i]!=prefix[i])
                return false;

        }
        return true;
    }


}
