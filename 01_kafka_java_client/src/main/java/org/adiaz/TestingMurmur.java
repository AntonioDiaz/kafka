package org.adiaz;

import org.apache.commons.codec.digest.MurmurHash2;

public class TestingMurmur {

  public static void main(String[] args) {
    extracted("a");
    extracted("b");
    extracted("c");
    extracted("q");
    extracted("233333");
    extracted("");
    extracted("d");
    extracted("e");
  }

  private static void extracted(String input) {
    int a = MurmurHash2.hash32(input);
    System.out.printf("murmur of %s -> %s\n", input, Math.abs(a % 2));
  }


}
