import relevent.serch.Relevantsearch;

public class Main {
    public static void main (String[] args){
        String strA = "abc";
        String strB = "abe";

        String[] arrayStrA = strA.split("");
        String[] arrayStrB = strB.split("");

        System.out.println(222222222);
       System.out.println(Relevantsearch.isRelevantScore(strA,strB));
    }
}
