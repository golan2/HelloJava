/*
 * (c) Copyright 2012 Hewlett-Packard Development Company, L.P. 
 * --------------------------------------------------------------------------
 * This file contains proprietary trade secrets of Hewlett Packard Company.
 * No part of this file may be reproduced or transmitted in any form or by
 * any means, electronic or mechanical, including photocopying and
 * recording, for any purpose without the expressed written permission of
 * Hewlett Packard Company.
 *//*
 * Created on Jul 20, 2004
 */


package com.mercury.diagnostics.common.util;


import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.io.*;


/**
 * This class represents the version for a software
 * component, which can be set with a representative
 * class and then accessed anywhere within that
 * component.
 * 
 * @author MReissig
 */
public class Version {

    public static void main(String[] args) {
        Version.initVersionFromFile(new File("C:\\Users\\golaniz\\Documents\\Izik\\Java\\Projects\\123Test\\static_content\\diag\\version\\version.txt"));
        System.out.println(Version.getMajorMinorVersion());
    }

  /**
   * This is a 'magic' probe group used for probing the server.  Probes in this
   * group are reported in the magic customer 'Mercury System' and are not shown
   * in system health.
   */
  public static final String MERCURY_SYSTEM_GROUP = "Mercury System";

  /**
   * A user-visible version number for this release, like "6.5.3.222", or "Development"
   */
  private static String version = "<unknown>";
  private static boolean isDevVersion = false;
  public static final String DEV_VERSION = "Development";
  public static final String DEV_DEFAULT_UNDERLYING_VERSION = "dev.unknown";

  /**
   * A best-we-can-do unique version number for this release, that will use the build number
   * for official builds (6.5.3.222) or the internal dev build date (dev.20061004_1652).
   */
  private static String underlyingVersion = DEV_DEFAULT_UNDERLYING_VERSION;

  /**
   * Retrieve the version number from the specified
   * file.  This should be called only once per software component
   *
   * If the file is not found or the version contains dev, or no '.'s
   * then we will set the version to Development.
   *
   * @see com.mercury.diagnostics.common.util.Version#getVersion()
   *
   * @param file the version file
   * @return a String representing the version.
   */
  public synchronized static String initVersionFromFile(File file) {
    try {
      Properties props = new Properties();

      props.load(new FileInputStream(file));
      underlyingVersion = props.getProperty("version", DEV_DEFAULT_UNDERLYING_VERSION);
      version = getVersionFromUnderlyingVersion(underlyingVersion);
    }
    catch (Exception e) {
      version = DEV_VERSION;
      underlyingVersion = DEV_DEFAULT_UNDERLYING_VERSION;
    }

    if (version.equals(DEV_VERSION)) {
      isDevVersion = true;
    }

    return version;
  }


  /**
   * Force a particular version. Useful for test cases.
   * 
   * @param version
   *          - version to set
   */
  public synchronized static void initVersion(String version) {
    isDevVersion = version.equals(DEV_VERSION);
    Version.version = version;
    Version.underlyingVersion = version;
  }

  /**
   * Retrieve the version after passing in a class
   * from which to set it.  This should be called only once
   * per software component.
   * 
   * @see com.mercury.diagnostics.common.util.Version#getVersion()
   *
   * @param c the Class from which to set the version
   * @return a String representing the version.
   */
  public synchronized static String initVersionForClass(Class c) {

    Package p = null;

    if (c != null) {
      p = c.getPackage();
    }

    if (p != null && p.getImplementationVersion() != null) {
      underlyingVersion = p.getImplementationVersion();
      version = getVersionFromUnderlyingVersion(underlyingVersion);
    }
    else {
      version = DEV_VERSION;
      underlyingVersion = DEV_DEFAULT_UNDERLYING_VERSION;
    }

    if (version.equals(DEV_VERSION)) {
      isDevVersion = true;
    }

    return version;
  }



  /**
   * Get the actual version String we want to use from the
   * string passed in.  Basically we want all development versions
   * to be "Development" and not dev_20051010, etc.
   *
   * If the version string doesn't contain a "." or it
   * contains "dev", then it's some sort of non-release version.
   */
  private static String getVersionFromUnderlyingVersion(String version) {
    if (version == null || -1 == version.indexOf(".") || version.indexOf("dev") != -1) {
      return DEV_VERSION;
    }

    return version;
  }



  /**
   * Retrieve the version.  This should be called
   * after the version has been set from a representative
   * class.
   * <p>
   * It's a little unclean that this isn't synchronized,
   * but the value never changes after startup and this is
   * called quite frequently.
   *
   * @see com.mercury.diagnostics.common.util.Version#initVersionForClass(Class)
   * 
   * @return a String representing the version.
   */
  public static String getVersion() {
    return version;
  }



  /**
   * Returns a best-we-can-do unique version number for this release, that will use the build number
   * for official builds (6.5.3.222) or the internal dev build date (dev.20061004_1652).
   */
  public static String getUnderlyingVersion() {
    return underlyingVersion;
  }



  /**
   * Test if the currently running instance is a development
   * instance or production.
   * <p>
   * It's a little unclean that this isn't synchronized,
   * but the value never changes after startup and this is
   * called very frequently.
   *
   * @return true if running a development instance.
   */
  public static boolean isDevelopmentVersion() {
    return isDevVersion;
  }



  /**
   * Test the versionString's major and minor attributes
   * against the passed versionMajor and versionMinor to 
   * see if its lesser version.
   */
  public static boolean isLessThan(String versionString,
    int versionMajor,
    int versionMinor) {
        
    if (versionString != null &&
      !versionString.trim().equals("") &&
      !versionString.equals(DEV_VERSION)) {

      int[] versionNumbers = getVersionNumbers(versionString);

      // Check for no version, which has been defined (by our unit test)
      //  to not be less than whatever is passed in...
      if ((0 == versionNumbers[0]) && (0 == versionNumbers[1]) &&
        (0 == versionNumbers[2]) && (0 == versionNumbers[3])) {
        return false;
      }

      if (versionNumbers[0] <= versionMajor) {
        if (versionNumbers[1] < versionMinor) {
          return true;
        }
      }
    }

    return false;
    
  }



  /**
   * Returns an array of the version numbers, where the first element
   *  is the major number, the second is the minor number. The third
   *  element, if present, is the "release" number, and the fourth
   *  element, if present, is the build number.
   */
  public static int[] getVersionNumbers(String versionString) {

    int[] versionNums = new int[4];

    versionNums[0] = 0;
    versionNums[1] = 0;
    versionNums[2] = 0;
    versionNums[3] = 0;

    if (versionString != null &&
      !versionString.trim().equals("") &&
      !versionString.equals(DEV_VERSION)) {

      StringTokenizer st = new StringTokenizer(versionString, ".");

      // Major number
      try {
        versionNums[0] = Integer.parseInt(st.nextToken());
      }
      catch (NumberFormatException nfe) {
        versionNums[0] = 0;
      }
      catch (NoSuchElementException nsee) {
        versionNums[0] = 0;
      }

      // Minor number
      if (st.hasMoreTokens()) {
        try {
          versionNums[1] = Integer.parseInt(st.nextToken());
        }
        catch (NumberFormatException nfe) {
          versionNums[1] = 0;
        }
        catch (NoSuchElementException nsee) {
          versionNums[1] = 0;
        }
      }

      // Release number
      if (st.hasMoreTokens()) {
        try {
          versionNums[2] = Integer.parseInt(st.nextToken());
        }
        catch (NumberFormatException nfe) {
          versionNums[2] = 0;
        }
        catch (NoSuchElementException nsee) {
          versionNums[2] = 0;
        }
      }

      // Build number
      if (st.hasMoreTokens()) {
        try {
          versionNums[3] = Integer.parseInt(st.nextToken());
        }
        catch (NumberFormatException nfe) {
          versionNums[3] = 0;
        }
        catch (NoSuchElementException nsee) {
          versionNums[3] = 0;
        }
      }

    }

    return versionNums;
  }



  /**
   * Returns an array of the version numbers from a string parsed by the passed tokens.
   *
   * IMPORTANT:  This method is used to parse JVM versions so don't make any assumptions
   *             that its only used for our version numbers.
   *
   * @param versionString version string to parse
   * @param tokens tokens to parse on
   * @param size minimum number of version part numbers to return.  If version doesn't contain
   *             part, then 0 will be returned for that part.  If size is less than the number
   *             of parse version parts, then the returned array length is adjusted to parsed
   *             size.
   */
  public static int[] getVersionNumbers(String versionString, String tokens, int size) {

    if (versionString != null &&
      !versionString.trim().equals("")) {

      StringTokenizer st = new StringTokenizer(versionString, tokens);

      int[] versionNums = new int[Math.max(size, st.countTokens())];

      int idx = 0;

      while (st.hasMoreTokens()) {
        try {
          versionNums[idx++] = Integer.parseInt(st.nextToken());
        }
        catch (NumberFormatException nfe) {
        }
        catch (NoSuchElementException nsee) {
        }
      }

      return versionNums;
    }
    else {
      return new int[size];
    }

  }
  
  

  /**
   *  If the first version string is less than the second
   *  version string return true. null is assumed to be 0.0.0.0. 
   */
  public static boolean isLessThan(String versionString1, String versionString2) {

    int[] version1 = padOrTrimTo4(getVersionNumbers(versionString1));
    int[] version2 = padOrTrimTo4(getVersionNumbers(versionString2));
    
    for (int i = 0; i < version1.length; ++i) {
      
      if (version1[i] != version2[i]) {
        return version1[i] < version2[i];
      }      
    }    
    // must be equal   
    return false;    
  }
  
  /**
   *  If the first version string is less than the second
   *  version string return true. null is assumed to be 0.0.0.0. 
   */
  public static boolean isGreaterThanOrEqual(String versionString1, String versionString2) {

    int[] version1 = padOrTrimTo4(getVersionNumbers(versionString1));
    int[] version2 = padOrTrimTo4(getVersionNumbers(versionString2));
    
    for (int i = 0; i < version1.length; ++i) {
      
      if (version1[i] != version2[i]) {
        return version1[i] > version2[i];
      }      
    }    
    // must be equal   
    return true;    
  }
 


  /**
   *  Pad an int array to 4 with zeros if
   *  lenght < 4 or trim array to 5
   */
  private static int[]padOrTrimTo4(int[]array) {
    if (array.length != 4) {
      int[] intArray = {0, 0, 0, 0};

      System.arraycopy(array, 0, intArray, 0, 4);
      return intArray;
    }
    return array;    
  }
  


  /**
   * Return the version number as a major.minor only. If it's a
   * development version, or unknown, a blank is returned.
   * @return major.minor version, or blank
   */
  public static String getMajorMinorVersion() {

    String mmVersion = "";
		
    // if it's not a dev version built it
    if (!isDevVersion) {
			
      mmVersion = version;
			
      // get the index after the major
      int i = mmVersion.indexOf(".");

      if (i > 0 && i < mmVersion.length()) {
				
        // get the index after the minor
        i = mmVersion.indexOf(".", i + 1);
        if (i > 0 && i < mmVersion.length()) {

          mmVersion = mmVersion.substring(0, i);
        }
      }
    }

    return mmVersion;
  }
}
