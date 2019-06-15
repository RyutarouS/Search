import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To get a relavent score between two word.
 * this is following the Levenshtein distance.
 */
public class Relevantsearch {
    /**
     * @param strA
     * @param strB
     * @return score
     */
    public static double isRelevantScore(String strA, String strB) {
        double score = 100;
        double numToOptimize = Math.max(strA.length(), strB.length());
        String[] arrayStrA = strA.split("");
        String[] arrayStrB = strB.split("");
//        //test
//        System.out.println("start score :" + score);

        //strAとstrBを一文字ずつ比べていく
        //strBの最初の文字をstrAと一文字ずつ比較していき
        //合致したらtmpListの合致したstrAの文字と同じインディックスに加えていく。
        //次のループは加えた文字のインデクスの次のインディックスから
        //最後にtmpListとstrAの一致していない文字数だけスコアを追加する。

        //最初のループはstrBの先頭の文字を0～strB.length()-1の数取り除いたcheckStrBとstrAとを比較していく
        for (int i = 0; i < strB.length(); i++) {
//            String[] tmpList = new String[strA.length()];
//            int initIndex = 0;
//            int startIndex = 0;
//            int endIndex = arrayStrA.length - 1;
//            int breakflag = 0;
//            int preScore = 0;

            //test
           // System.out.println("i" + i +"\n" + Arrays.toString(checkStrB));

            //jはcheckStrBのindex
            //先頭の文字がstrAと一文字もヒットしなかったらbraekしてcheckStrBを作り直す
//            for (int j = 0; j < checkStrB.length; j++) {
////                //Test
////                System.out.println("initIndex :" + initIndex +"\n" +
////                        "startIndex : " + startIndex + "\n" +
////                        "endIndex : " + endIndex + "\n" +
////                        "breakFlag :" + breakflag +"\n" +
////                        "j :" + j);
//
//
//                //検索の開始インデックスがarrayStrAの大きさを超えたらbreak
//                //StrAの長さ超えた文字は削除するのでその分preScoreに加算
//                if (startIndex > arrayStrA.length -1) {
//                    preScore += checkStrB.length - j;
//                    //Test
//                    System.out.println("add point 1    +" + (checkStrB.length - j));
//                    break;
//                }
//
//                //下のループでstrBの一文字目からStrAの文字にも合致しなかったら
//                // このループを抜ける
//                if (breakflag == -1) {
//                    break;
//                }
//                if (j != 0) {
//                    endIndex = initIndex + j;
//                    if (endIndex > arrayStrA.length - 1) {
//                        endIndex = arrayStrA.length - 1;
//                    }
//                }
//                //arrayStrB[j]とstrAの文字を一文字づつ比べていく
//                //検索開始位置は位置が決定したインデックスの次のインディックスから
//                //本来その文字が入る予定のインデックスまで
//                for (int h = startIndex; h <= arrayStrA.length -1; h++) {
//
//                    //test
//                    System.out.println("h :" + h);
//                    if (arrayStrA[h].equals(checkStrB[j])) {
//                        if (j == 0) {
//                            initIndex = h;
//                        }
//                        //test
//                        if(initIndex+j-h >= 0) {
//                            System.out.println("add point 2    +" + (initIndex + j - h));
//                            preScore += initIndex + j - h;
//                        }
//                        startIndex = h + 1;
//                        tmpList[h] = checkStrB[j];
//                        //test
//                        System.out.println("check point : 1");
//                        break;
//                    }
//                    if (h == strA.length() - 1 && j == 0) {
////                        //test
////                        System.out.println("breakflag");
//                        breakflag = -1;
//                    }
//                }
//            }
//
//            //checkStrBの先頭から何もヒットしなかったから以下の処理をスキップ
//            if (breakflag == -1) {
//                continue;
//            }
//
//            //StrAの前方にあふれた文字は削除するのでその分加算
//            if(i - initIndex > 0){
////                    //test
////                    System.out.println("add point 3    + " + (i - initIndex)) ;
//                preScore += (i - initIndex);
//            }
//
//            //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
//            for (int j = 0; j < strA.length(); j++) {
//
//                //まずtmpListのnullをカウント（エラー対策）
//                if (tmpList[j] == null) {
//                    //test
//                    System.out.println("add point 4    + 1") ;
//                    preScore += 1;
//                    //test
//                    System.out.println("2 preScore: " + preScore);
//                    System.out.println("2 score :" + score);
//                    continue;
//                }
//                if (!(tmpList[j].equals(arrayStrA[j]))) {
//                    //test
//                    System.out.println("add point 5   +1");
//                    //test
//                    System.out.println("2 preScore: " + preScore);
//                    preScore += 1;
//                }
//            }
//
//            //test
//            System.out.println("1 preScore: " + preScore);
//            System.out.println("1 score :" + score);
            String[] checkStrB = Arrays.copyOfRange(arrayStrB, i, strB.length());
            int preScore = withOverlap(checkStrB,arrayStrA,i);
            int preScoreWithout = withoutOverlap(checkStrB,arrayStrA,i);
            if(preScore > preScoreWithout){
                preScore = preScoreWithout;
            }
            if (preScore < score) {
                score = preScore;
            }
//            //test
//            System.out.println("preScore: " + preScore);
//            System.out.println("score :" + score);
        }
        //標準化
        score = score/numToOptimize;
        return score;
}
    public static int withOverlap(String[] checkStrB, String[] arrayStrA, int i){
        String[] tmpList = new String[arrayStrA.length];
        int initIndex = 0;
        int startIndex = 0;
        int preScore = 0;
        for (int j = 0; j < checkStrB.length; j++) {
//                //Test
//                System.out.println("initIndex :" + initIndex +"\n" +
//                        "startIndex : " + startIndex + "\n" +
//                        "endIndex : " + endIndex + "\n" +
//                        "breakFlag :" + breakflag +"\n" +
//                        "j :" + j);


            //検索の開始インデックスがarrayStrAの大きさを超えたらbreak
            //StrAの長さ超えた文字は削除するのでその分preScoreに加算
            if (startIndex > arrayStrA.length -1) {
                preScore += checkStrB.length - j;
//                //Test
//                System.out.println("add point 1    +" + (checkStrB.length - j));
                break;
            }
            //arrayStrB[j]とstrAの文字を一文字づつ比べていく
            //検索開始位置は位置が決定したインデックスの次のインディックスから
            //本来その文字が入る予定のインデックスまで
            for (int h = startIndex; h <= arrayStrA.length -1; h++) {
//                //test
//                System.out.println("h :" + h);
                if (arrayStrA[h].equals(checkStrB[j])) {
                    if (j == 0) {
                        initIndex = h;
                    }
                    if(initIndex+j-h >= 0) {
//                        //test
//                        System.out.println("add point 2    +" + (initIndex + j - h));
                        preScore += initIndex + j - h;
                    }
                    startIndex = h + 1;
                    tmpList[h] = checkStrB[j];
//                    //test
//                    System.out.println("check point : 1");
                    break;
                }
                if (h == arrayStrA.length - 1 && j == 0) {
//                        //test
//                        System.out.println("breakflag");
                    return 100;
                }
            }
        }
        //StrAの前方にあふれた文字は削除するのでその分加算
        if(i - initIndex > 0){
//                    //test
//                    System.out.println("add point 3    + " + (i - initIndex)) ;
            preScore += (i - initIndex);
        }
        //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
        for (int j = 0; j < arrayStrA.length; j++) {
            //まずtmpListのnullをカウント（エラー対策）
            if (tmpList[j] == null) {
//                //test
//                System.out.println("add point 4    + 1") ;
                preScore += 1;
//                //test
//                System.out.println("2 preScore: " + preScore);
                continue;
            }
            if (!(tmpList[j].equals(arrayStrA[j]))) {
//                //test
//                System.out.println("add point 5   +1");
//                //test
//                System.out.println("2 preScore: " + preScore);
                preScore += 1;
            }
        }
        return preScore;

    }
    public static int withoutOverlap(String[] checkStrB, String[] arrayStrA, int i){
        String[] tmpList = new String[arrayStrA.length];
        int initIndex = 0;
        int startIndex = 0;
        int endIndex = arrayStrA.length - 1;
        int preScore = 0;
        for (int j = 0; j < checkStrB.length; j++) {
//                //Test
//                System.out.println("initIndex :" + initIndex +"\n" +
//                        "startIndex : " + startIndex + "\n" +
//                        "endIndex : " + endIndex + "\n" +
//                        "breakFlag :" + breakflag +"\n" +
//                        "j :" + j);
            //検索の開始インデックスがarrayStrAの大きさを超えたらbreak
            //StrAの長さ超えた文字は削除するのでその分preScoreに加算
            if (startIndex > arrayStrA.length -1) {
                preScore += checkStrB.length - j;
//                //Test
//                System.out.println("add point 1    +" + (checkStrB.length - j));
                break;
            }

            if (j != 0) {
                endIndex = initIndex + j;
                if (endIndex > arrayStrA.length - 1) {
                    endIndex = arrayStrA.length - 1;
                }
            }
            //arrayStrB[j]とstrAの文字を一文字づつ比べていく
            //検索開始位置は位置が決定したインデックスの次のインディックスから
            //本来その文字が入る予定のインデックスまで
            for (int h = startIndex; h <= endIndex; h++) {
//                //test
//                System.out.println("h :" + h);
                if (arrayStrA[h].equals(checkStrB[j])) {
                    if (j == 0) {
                        initIndex = h;
                    }
                    if(initIndex+j-h >= 0) {
//                        //test
//                        System.out.println("add point 2    +" + (initIndex + j - h));
                        preScore += initIndex + j - h;
                    }
                    startIndex = h + 1;
                    tmpList[h] = checkStrB[j];
//                    //test
//                    System.out.println("check point : 1");
                    break;
                }
                if (h == arrayStrA.length - 1 && j == 0) {
//                        //test
//                        System.out.println("breakflag");
                    return 100;
                }
            }
        }

        //StrAの前方にあふれた文字は削除するのでその分加算
        if(i - initIndex > 0){
//                    //test
//                    System.out.println("add point 3    + " + (i - initIndex)) ;
            preScore += (i - initIndex);
        }

        //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
        for (int j = 0; j < arrayStrA.length; j++) {
            //まずtmpListのnullをカウント（エラー対策）
            if (tmpList[j] == null) {
//                //test
//                System.out.println("add point 4    + 1") ;
                preScore += 1;
                continue;
            }
            if (!(tmpList[j].equals(arrayStrA[j]))) {
//                //test
//                System.out.println("add point 5   +1");
                preScore += 1;
            }
        }
        return preScore;
    }

    public static List<String> sortByRelevent(){
        List<String> sortedList = new ArrayList<>();
        return sortedList;
    }
}
