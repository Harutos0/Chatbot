import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.Random;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {

  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();

  public static void main(String[] args) {
    //String hi = "amazing";
    //System.out.println(sentimentVal(hi));
    // double x = totalSentiment("1960s!");
    int y = starRating("SimpleReview.txt");
    // System.out.println(x);
    System.out.println(y);
    // String a = "1960s!";
    // if (a.substring(a.length()-1, a.length()).equals("!")){
    //   a = a.substring(0, a.length()-1);
    //   double temp1 = sentimentVal(a);
    //   System.out.println(temp1*10);
    // }

    // String a = removePunctuations("I've now, been using.");
    // System.out.println(a);
    //String a = fakeReview2("SimpleReview.txt");
    // String b = fakeReview("SimpleReview.txt");
    // System.out.println(a);
  }

  private static final String SPACE = " ";

  static {
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while (input.hasNextLine()) {
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0], Double.parseDouble(temp[1]));
        // System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }

    // read in the positive adjectives in postiveAdjectives.txt
    try {
      Scanner input = new Scanner(new File("SimpleReview.txt"));
      while (input.hasNextLine()) {
        String temp = input.nextLine().trim();
        String[] arrOfStr = temp.split(" ", -2);
        System.out.println(arrOfStr);
        // posAdjectives.add(temp);
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }
    try {
      Scanner input = new Scanner(new File("SimpleReview.txt"));
      while (input.hasNextLine()) {
        String temp = input.nextLine().trim();
        String[] arrOfStr = temp.split(" ", -2);
        System.out.println(arrOfStr);
        // posAdjectives.add(temp);
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }

    // read in the negative adjectives in negativeAdjectives.txt
    try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while (input.hasNextLine()) {
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }
  }

  /**
   * returns a string containing all of the text in fileName (including
   * punctuation),
   * with words separated by a single space
   */
  public static String textToString(String fileName) {
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));

      // add 'words' in the file to the string, separated by a single space
      while (input.hasNext()) {
        temp = temp + input.next() + " ";
      }
      input.close();

    } catch (Exception e) {
      System.out.println("Unable to locate " + fileName);
    }
    // make sure to remove any additional space that may have been added at the end
    // of the string.
    return temp.trim();
  }

  /**
   * @returns the sentiment value of word as a number between -1 (very negative)
   *          to 1 (very positive sentiment)
   */
  public static double sentimentVal(String word) {
    try {
      return sentiment.get(word.toLowerCase());
    } catch (Exception e) {
      return 0;
    }
  }

  public static double totalSentiment(String fileName) {
    List<String> arrayList = new ArrayList<>();
    double score = 0;
    try {
      Scanner input = new Scanner(new File(fileName));
      while (input.hasNextLine()) {
        String temp = input.nextLine().trim();
        String[] arrOfStr = temp.split(" ", 100000000);
        for (String a : arrOfStr) {
          if (a.substring(a.length()-1, a.length()).equals("!")){
            a = a.substring(0, a.length()-1);
            double temp1 = sentimentVal(a);
            score += temp1*10;
            // System.out.println(temp1*10);
          }
          else{
            removePunctuations(a);
            arrayList.add(a);
          }
          // System.out.println(a);
        }
      }
      input.close();
    }

    catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }

    for (int i = 0; i < arrayList.size(); i++) {
      score += sentimentVal(arrayList.get(i));
      //System.out.println(score);
    }
    return score;
  }

  public static int starRating(String fileName)
  {
    int star = 0; 
    double x = totalSentiment("SimpleReview.txt") -12;
    System.out.println(x);
    if (x<-9){
      return star = -10;
    }
    else if (-9<=x && x<-8){
      return star = -9;
    }
    else if (-8<=x && x<-7){
      return star = -8;
    }
    else if (-7<=x && x<-5){
      return star = -7;
    }
    else if (-5<=x && x<-4){
      return star = -6;
    }
    else if (-4<=x && x<-3){
      return star = -5;
    }
    else if (-3<=x && x<-2){
      return star = -4;
    }
    else if (-2<=x && x<-1){
      return star = -3;
    }
    else if (-1<=x && x<0){
      return star = -2;
    }
    else if (0<=x && x<1){
      return star = -1;
    }
    else if (1<=x && x<2){
      return star = 0;
    }
    else if (2<=x && x<3){
      return star = 1;
    }
    else if (3<=x && x<4){
      return star = 2;
    }
    else if (4<=x && x<5){
      return star = 3;
    }
    else if (5<=x && x<6){
      return star = 4;
    }
    else if (6<=x && x<7){
      return star = 5;
    }
    else if (8<=x && x<9){
      return star = 6;
    }
    else if (9<=x && x<10){
      return star = 7;
    }
    else if (10<=x && x<11){
      return star = 8;
    }
    else if (11<=x && x<12){
      return star = 9;
    }
    else if (12<=x){
      return star = 10;
    }
    return 0;
  }
  
  public static String fakeReview(String fileName) {
    List<String> pos = new ArrayList<>();
    List<String> neg = new ArrayList<>();
    String str = "";
    Random rand = new Random();
    try {
      Scanner input1 = new Scanner(new File(fileName));
      Scanner input2 = new Scanner(new File("positiveAdjectives.txt"));
      Scanner input3 = new Scanner(new File("negativeAdjectives.txt"));
      while (input1.hasNextLine()) {
        String temp1 = input1.nextLine().trim();
        String[] arrOfStr = temp1.split(" ", 100000000);
        //System.out.println(arrOfStr);
        while (input2.hasNextLine()) {
          String temp2 = input2.nextLine().trim();
          pos.add(temp2);
          //System.out.println(pos);
        }
        while (input3.hasNextLine()) {
          String temp3 = input3.nextLine().trim();
          neg.add(temp3);
          //System.out.println(neg);
        }

        // System.out.println(arrOfStr);
        // posAdjectives.add(temp);
        for (String a : arrOfStr) {
          try {
            if (a.substring(0,1).equals("*")){
              if (sentimentVal(a.substring(1)) >= 0){
                int int_random = rand.nextInt(0,2); 
                //System.out.println(int_random);
                a = pos.get(int_random);
              }
              if (sentimentVal(a.substring(1)) < 0){
                int int_random = rand.nextInt(0,2); 
                //System.out.println(int_random);
                a = neg.get(int_random);
              }
            }
            int index = a.indexOf('.');
            a = a.substring(0, index) + a.substring(index + 1);
            index = a.indexOf(',');
            a = a.substring(0, index) + a.substring(index + 1);
            index = a.indexOf('\'');
            a = a.substring(0, index) + a.substring(index + 1);
            
          } 
          catch (Exception e) {
            int b = 0;
          }
          str = str + a + " ";
          // System.out.println(a);
        }
      }
      input1.close();
      input2.close();
    }

    catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }

    return str;
  }

  public static String fakeReview2(String fileName) {
    List<String> pos = new ArrayList<>();
    List<String> neg = new ArrayList<>();
    String str = "";
    Random rand = new Random();
    try {
      Scanner input1 = new Scanner(new File(fileName));
      Scanner input2 = new Scanner(new File("positiveAdjectives.txt"));
      Scanner input3 = new Scanner(new File("negativeAdjectives.txt"));
      while (input1.hasNextLine()) {
        String temp1 = input1.nextLine().trim();
        String[] arrOfStr = temp1.split(" ", 100000000);
        //System.out.println(arrOfStr);
        while (input2.hasNextLine()) {
          String temp2 = input2.nextLine().trim();
          pos.add(temp2);
          //System.out.println(pos);
        }
        while (input3.hasNextLine()) {
          String temp3 = input3.nextLine().trim();
          neg.add(temp3);
          //System.out.println(neg);
        }

        // System.out.println(arrOfStr);
        // posAdjectives.add(temp);
        for (String a : arrOfStr) {
          try {
            if (a.substring(0,1).equals("*")){
              if (sentimentVal(a.substring(1)) >= 0){
                int int_random = rand.nextInt(0,2); 
                //System.out.println(int_random);
                a = pos.get(int_random);
              }
              if (sentimentVal(a.substring(1)) < 0){
                int int_random = rand.nextInt(0,2); 
                //System.out.println(int_random);
                a = neg.get(int_random);
              }
            }
            int index = a.indexOf('.');
            a = a.substring(0, index) + a.substring(index + 1);
            index = a.indexOf(',');
            a = a.substring(0, index) + a.substring(index + 1);
            index = a.indexOf('\'');
            a = a.substring(0, index) + a.substring(index + 1);
            
          } 
          catch (Exception e) {
            int b = 0;
          }
          str = str + a + " ";
          // System.out.println(a);
        }
      }
      input1.close();
      input2.close();
    }

    catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }

    return str;
  }



  /**
   * Returns the ending punctuation of a string, or the empty string if there is
   * none
   */
  public static String getPunctuation(String word) {
    String punc = "";
    for (int i = word.length() - 1; i >= 0; i--) {
      if (!Character.isLetterOrDigit(word.charAt(i))) {
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }

  /**
   * Returns the word after removing any beginning or ending punctuation
   */
  // public static String removePunctuation(String word) {
  //   int i = 0;
  //   while (word.length() > i) {
  //     if (Character.isAlphabetic(word.charAt(i))){
  //       word += word.substring(i,i+1);
  //     }
  //     else {
  //       continue;
  //     }
  //     // word = word.substring(1);
  //     i++;
  //   }
  //   // while (word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length() - 1))) {
  //   //   word = word.substring(0, word.length() - 1);
  //   // }
  //   return word;
  // }

  public static String removePunctuations(String source) {
		return source.replaceAll("\\p{IsPunctuation}", "");
	}

  /**
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and
   * returns it.
   */
  public static String randomPositiveAdj() {
    int index = (int) (Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }

  /**
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and
   * returns it.
   */
  public static String randomNegativeAdj() {
    int index = (int) (Math.random() * negAdjectives.size());
    return negAdjectives.get(index);

  }

  /**
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective() {
    boolean positive = Math.random() < .5;
    if (positive) {
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }
}
