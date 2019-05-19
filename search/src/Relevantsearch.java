import java.util.Arrays;

/**
 * To get a relavent score between two word.
 * this is following the Levenshtein distance.
 */
public class Relaventsearch {
    /**
     * @param strA
     * @param strB
     * @return score
     */
    public static double isReleventScore(String strA, String strB) {
        double score = 100;
        //In order to optimize the scores,
        // the scores are divided by the number of longer characters between two words.
        double numToOptimize = Math.max(strA.length(), strB.length());

        String[] arrayStrA = strA.split("");
        String[] arrayStrB = strB.split("");


        //strAとstrBを一文字ずつ比べていく
        //strBの最初の文字をstrAと一文字ずつ比較していき
        //合致したらtmpListの合致したstrAの文字と同じインディックスに加えていく。
        //次のループは加えた文字のインデクスの次のインディックスから
        //最後にtmpListとstrAの一致していない文字数だけスコアを追加する。

        //最初のループはstrBの先頭の文字を0～strB.length()-1の数取り除いたcheckStrBとstrAとを比較していく
        for (int i = 0; i < strB.length(); i++) {
            String[] tmpList = new String[strA.length()];

            int initIndex = 0;
            int startIndex = 0;
            int endIndex = arrayStrA.length - 1;
            int breakflag = 0;
            int preScore = 0;
            String[] checkStrB = Arrays.copyOfRange(arrayStrB, i, strB.length());

            //test
            System.out.println("i" + i +"\n" + Arrays.toString(checkStrB));

            //jはcheckStrBのindex
            //先頭の文字がstrAと一文字もヒットしなかったらbraekしてcheckStrBを作り直す
            for (int j = 0; j < checkStrB.length; j++) {

                //Test
                System.out.println("initIndex :" + initIndex +"\n" +
                        "startIndex : " + startIndex + "\n" +
                        "endIndex : " + endIndex + "\n" +
                        "breakFlag :" + breakflag +"\n" +
                        "j :" + j);

                //検索の開始インデックスがarrayStrAの大きさを超えたらbreak
                //StrAの長さ超えた文字は削除するのでその分preScoreに加算
                if (startIndex > arrayStrA.length -1) {
                    preScore += checkStrB.length - j;
                    //Test
                    System.out.println("add point 1    +" + (checkStrB.length - j));
                    break;
                }

                //下のループでstrBの一文字目からStrAの文字にも合致しなかったら
                // このループを抜ける
                if (breakflag == -1) {
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
                    //test
                    System.out.println("h :" + h);
                    if (arrayStrA[h].equals(checkStrB[j])) {
                        if (j == 0) {
                            initIndex = h;
                        }
                        //test
                        System.out.println("add point 2    +" + (initIndex+j-h));
                        preScore += initIndex+j-h;
                        startIndex = h + 1;
                        tmpList[h] = checkStrB[j];

                        //test
                        System.out.println("check point : 1");
                        break;
                    }
                    if (h == strA.length() - 1 && j == 0) {
                        System.out.println("breakflag");
                        breakflag = -1;
                    }
                }
            }

            //checkStrBの先頭から何もヒットしなかったから以下の処理をスキップ
            if (breakflag == -1) {
                continue;
            }

            //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
            for (int j = 0; j < strA.length(); j++) {
                //StrAの前方にあふれた文字は削除するのでその分加算
                if(i - initIndex > 0){

                    //test
                    System.out.println("add point 3    + " + (i - initIndex)) ;

                    preScore += (i - initIndex);
                }
                //まずtmpListのnullをカウント（エラー対策）
                if (tmpList[j] == null) {

                    //test
                    System.out.println("add point 4    + 1") ;

                    preScore += 1;
                    continue;
                }
                if (!(tmpList[j].equals(arrayStrA[j]))) {

                    //test
                    System.out.println("add point 5   +1");

                    preScore += 1;
                }
            }


            if (preScore < score) {
                score = preScore;
            }

//            //test
//            System.out.println("preScore: " + preScore);
//            System.out.println("score :" + score);

        }
        //score = score/numToOptimize;
        return score;
}
    public static String[] sortByRelevent()
}   
