package file.scanner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    12/12/2014 11:04
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 * Scan all sub folders to find files that are the same.
 *
 * TODO: What is considered as a "same file"
 * TODO: Use EhCache instead of map
 *
 * </pre>
 */
public class DuplicateFiles {

  public static void main(String[] args) throws IOException {
    Path rootFolder = Paths.get("C:\\Users\\golaniz\\Desktop\\DuplicateFiles");
    DuplicateVisitor visitor = new DuplicateVisitor();
    Files.walkFileTree(rootFolder, visitor);
    visitor.removeNonDuplicates();
    Map<String, List<String>> duplications = visitor.getFiles();

    for (List<String> dups : duplications.values()) {
      for (String dup : dups) {
        System.out.println(dup);
      }
      System.out.println("----");
    }

  }

  private static class DuplicateVisitor implements FileVisitor<Path> {
    private final Map<String, List<String>> files = new HashMap<>();  // { key = file short name ; value = list of paths that contain a file with this name }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
      return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
      String shortName = file.getFileName().toString();   //name with extension without path

      List<String> paths = this.files.get(shortName);
      boolean addBack = false;
      if (paths==null) {
        paths = new ArrayList<>();
        addBack = true;
      }
      paths.add(file.toString());
      if (addBack) {
        this.files.put(shortName, paths);
      }
      return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
      return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
      return FileVisitResult.CONTINUE;
    }

    public void removeNonDuplicates() {
      Set<String> fileNames = this.files.keySet();
      Iterator<String> iterator = fileNames.iterator();
      while (iterator.hasNext()) {
        String fileName = iterator.next();
        if (this.files.get(fileName).size()<2) {
          iterator.remove();
        }
      }
    }

    public Map<String, List<String>> getFiles() {
      return files;
    }
  }
}
