public class Main {
    public static void main (String[] args){
        String strA = "abcd";
        String strB = "bccd";

        String[] arrayStrA = strA.split("");
        String[] arrayStrB = strB.split("");

    //    System.out.println(222222222);
        System.out.println(Relevantsearch.isRelevantScore(strA,strB));

    }
}
