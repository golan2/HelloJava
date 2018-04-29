package strings;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    03/01/2012 09:24:56
 * <B>Since:</B>       BSM 9.1
 * <B>Description:</B>
 * findNeedleInHaystack
 * </pre>
 */
public class FindSubstring {

  public static void main(String[] args) {
      String s = "1234567890";
      final String substring = s.substring(3, 15);
      System.out.println("["+substring+"]");

      //System.out.println(findNeedleInHaystackImproved("HP Software", "tw?*?e") );
  }

  /**
   * Find the first instance of a string in another string
   *
   * @param haystack string in which we search
   * @param needle substring to be searched for inside needle
   * @return the index to the first occurrence of needle in haystack, or a 0 if needle is not part of haystack.
   */
  private static int findNeedleInHaystack(char[] haystack, char[] needle) {
    for(int i = 0; i < haystack.length; i++ ) {
      for(int j = 0; j < needle.length && i+j < haystack.length; j++ ) {
        if(needle[j] != haystack[i+j]) {
          break;
        } else if (j == needle.length-1) {
          return i;
        }
      }
    }
    return   -1;
  }

  private static int findNeedleInHaystack(String haystack, String needle) {
    for(int i = 0; i < haystack.length(); i++ ) {
      for(int j = 0; j < needle.length() && i+j < haystack.length(); j++ ) {
        if(needle.charAt(j) != haystack.charAt(i+j)) {
          break;
        } else if (j == needle.length()-1) {
          return i;
        }
      }
    }
    return -1;
  }

  private static int findNeedleInHaystackImproved(String haystack, String needle) {
    return findNeedleInHaystackImproved(haystack.toCharArray(), needle.toCharArray());
  }

  private static int findNeedleInHaystackImproved(char[] haystack, char[] needle) {
    for(int h = 0; h < haystack.length; h++ ) {
      final int index = match(haystack, needle, h);
      if (index !=-1) {
        return index;
      }
    }
    return -1;
  }

  private static int match(char[] haystack, char[] needle, int index) {
    int n = 0;      //index to needle
    int h = index;  //index to haystack

    while (h<haystack.length && n<needle.length) {
      if (needle[n]=='?') {
        n++;
        h++;
      }
      else if (needle[n]=='*') {
        n++;
        //the "*" is at the end of the needle then whatever comes next in haystack is ok.
        if (n==needle.length) return h;

        char nextChar = needle[n];  //this is the char that after the "*"
        while (h<haystack.length && haystack[h]!=nextChar) {
          h++;
        }

        //now either haystack is over or we found nextChar at haystack[h]
        if (h==haystack.length) return -1;

        //else continue to loop and search for the rest of the needle inside the haystack
        n++;
        h++;
      }
      else {
        if (needle[n]!=haystack[h]) return -1;
        n++;
        h++;        
      }
    }

    //it is either that needle is over or that haystack is over
    if (h==haystack.length && n<needle.length) return -1;    //haystack is over before we found a match
    return index;   //needle is over and we found all its characters in the haystack
  }
}
