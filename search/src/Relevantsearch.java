import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To get a relavent score between two word.
 * this is following the Levenshtein distance.
 */
public class Relevantsearch {
    /**
     * 二つの文字を比べて一致度を返す
     *
     * @param strA 文字A
     * @param strB 文字B
     * @return score
     */
    public static double isRelevantScore(String strA, String strB) {
        double score = 100;
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
            String[] checkStrB = Arrays.copyOfRange(arrayStrB, i, strB.length());
            int preScore = withOverlap(checkStrB,arrayStrA,i);
            int preScoreWithout = withoutOverlap(checkStrB,arrayStrA,i);
            if(preScore > preScoreWithout){
                preScore = preScoreWithout;
            }
            if (preScore < score) {
                score = preScore;
            }
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
            if (startIndex > arrayStrA.length -1) {
                preScore += checkStrB.length - j;
                break;
            }
            for (int h = startIndex; h <= arrayStrA.length -1; h++) {
                if (arrayStrA[h].equals(checkStrB[j])) {
                    if (j == 0) {
                        initIndex = h;
                    }
                    if(initIndex+j-h >= 0) {
                        preScore += initIndex + j - h;
                    }
                    startIndex = h + 1;
                    tmpList[h] = checkStrB[j];
                    break;
                }
                if (h == arrayStrA.length - 1 && j == 0) {
                    return 100;
                }
            }
        }
        //StrAの前方にあふれた文字は削除するのでその分加算
        if(i - initIndex > 0){
            preScore += (i - initIndex);
        }
        //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
        for (int j = 0; j < arrayStrA.length; j++) {
            //まずtmpListのnullをカウント（エラー対策）
            if (tmpList[j] == null) {
                preScore += 1;
                continue;
            }
            if (!(tmpList[j].equals(arrayStrA[j]))) {
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
            //検索の開始インデックスがarrayStrAの大きさを超えたらbreak
            //StrAの長さ超えた文字は削除するのでその分preScoreに加算
            if (startIndex > arrayStrA.length -1) {
                preScore += checkStrB.length - j;
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
                if (arrayStrA[h].equals(checkStrB[j])) {
                    if (j == 0) {
                        initIndex = h;
                    }
                    if(initIndex+j-h >= 0) {
                        preScore += initIndex + j - h;
                    }
                    startIndex = h + 1;
                    tmpList[h] = checkStrB[j];
                    break;
                }
                if (h == arrayStrA.length - 1 && j == 0) {
                    return 100;
                }
            }
        }

        //StrAの前方にあふれた文字は削除するのでその分加算
        if(i - initIndex > 0){
            preScore += (i - initIndex);
        }

        //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
        for (int j = 0; j < arrayStrA.length; j++) {
            //まずtmpListのnullをカウント（エラー対策）
            if (tmpList[j] == null) {
                preScore += 1;
                continue;
            }
            if (!(tmpList[j].equals(arrayStrA[j]))) {
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
