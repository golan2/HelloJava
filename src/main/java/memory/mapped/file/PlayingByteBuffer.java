package memory.mapped.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    13/06/14 08:12
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class PlayingByteBuffer {
    private static final int MAX_BUFFER = 1080000000;
    private static final long MEGA_BYTE = 1024*1024;


    int bytesRead;
    FileChannel inChannel;
    FileChannel outChannel;
    ByteBuffer buffer;

    byte[] buf = new byte[100];

    public static void main(String[] args) throws IOException {
        new PlayingByteBuffer().testRegEx();
    }

    private void testRegEx() {

        String s = "ANT_HOME=c:\\local_p\\ant\\1.6.1\\multi-local-platform";
        Pattern pattern = Pattern.compile("local");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println("S=["+matcher.start()+"] E=["+matcher.end()+"] ");
        }
    }

    private void readWrite() throws IOException {
        RandomAccessFile rFile = null;
        RandomAccessFile wFile = null;

        try {
            rFile = new RandomAccessFile("C:\\Users\\golaniz\\Videos\\Movies\\_good\\111.srt", "rw");
            inChannel = rFile.getChannel();
            MappedByteBuffer map = inChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());


            //Pattern pattern = Pattern.compile("*LOCAL*");
            //Matcher matcher = pattern.matcher(map.asCharBuffer());
            //matcher.matches()


            map.position(100);
            map.put("QWERTY".getBytes());
            map.force();

            //for (int i = 0; i < map.remaining(); i++) {
            //    byte b = buf[i];
            //
            //}

            //wFile = new RandomAccessFile("C:\\Users\\golaniz\\Videos\\Movies\\_good\\Fast Five (2011).izik", "rw");
            //outChannel = wFile.getChannel();
            //outChannel.map(FileChannel.MapMode.READ_WRITE, 0, MAX_BUFFER);




        }
        finally {
            if (rFile!=null) rFile.close();
            if (wFile!=null) wFile.close();
            if (inChannel!=null) inChannel.close();
            if (outChannel!=null) outChannel.close();
        }


    }

    private void go() throws IOException {

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("C:\\Users\\golaniz\\Videos\\Movies\\_good\\Fast Five (2011).avi", "r");
            //RandomAccessFile bFile = new RandomAccessFile("C:\\Users\\golaniz\\Videos\\Movies\\_good\\Fast Five (2011).izik", "rw");

            inChannel = aFile.getChannel();
            buffer = ByteBuffer.allocate(MAX_BUFFER);

            bytesRead = inChannel.read(buffer);
            printStats();

            System.out.println("buffer.flip();");
            buffer.flip();
            printStats();

            int count = 0;
            while (count++<3 && buffer.hasRemaining()) {
                buffer.get();
                printStats();
            }

            System.out.println("buffer.clear();");
            buffer.clear();
            printStats();

            System.out.println("inChannel.read");
            bytesRead = inChannel.read(buffer);
            printStats();
        }
        finally {
            if (aFile!=null) aFile.close();
        }

    }

    private void printStats() throws IOException {
        System.out.println("bytesRead=[" + bytesRead + "] inChannel.position=[" + inChannel.position() + "] buffer.position=[" + buffer.position() + "] buffer.capacity=[" + buffer.capacity() + "] buffer.limit=[" + buffer.limit() + "] buffer.limit=[" + Runtime.getRuntime().freeMemory()/MEGA_BYTE + "] ");
    }

}
