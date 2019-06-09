package relevent.serch;

import java.util.Arrays;

/**
 * To get a relavent score between two word.
 * this is following the Levenshtein distance.
 */
public class Relevantsearch {
    /**
     * strAとStrBで比較する
     * 値が低いほうが一致度が高い
     *
     * @param strA　比較する文字A
     * @param strB　比較する文字B
     * @return score
     */
    public static double isReleventScore(String strA, String strB) {
        double score = 100;
        //In order to optimize the scores,
        // the scores are divided by the number of longer characters
        // between two words.
        //However I have to consider this way is the best or not.
        double numToOptimize = Math.max(strA.length(), strB.length());

        String[] arrayStrA = strA.split("");
        String[] arrayStrB = strB.split("");


        //strAとstrBを一文字ずつ比べていく
        //strBの最初の文字をstrAと一文字ずつ比較していき
        //合致したらtmpListの合致したstrAの文字と同じインディックスに加えていく。
        //次のループは加えた文字のインデクスの次のインディックスから
        //最後にtmpListとstrAの一致していない文字数だけスコアを追加する。

        //最初のループはstrBの先頭の文字を0～strB.length()-1の数
        // 取り除いたcheckStrBとstrAとを比較していく
        //checkStrBの先頭の文字はarrayStrB[i]
        for (int i = 0; i < strB.length(); i++) {
            String[] tmpList = new String[strA.length()];

            /** checkStrBの先頭の文字がヒットしたStrAのインデックス*/
            int initIndex = 0;
            /** checkStrBの次の文字がStrAのどの文字から比較するを示したインデックス*/
            int startIndex = 0;
            /** checkStrBが比較する最後のインデックス（StrAの最後の文字のインデックス）*/
            int endIndex = arrayStrA.length - 1;
            /** checkStrBの１文字目がヒットしなかったらtrue*/
            boolean breakflag = false;
            /**  今回の比較パターンでのスコア*/
            int preScore = 0;
            /** arrayStrB[i]からarrayStrBの最後の文字までを切り取った配列*/
            String[] checkStrB = Arrays.copyOfRange(arrayStrB, i, strB.length());

            //jはcheckStrBのindex
            //checkStrBの先頭の文字がstrAと一文字もヒットしなかったら
            // braekしてcheckStrBを作り直す
            //StrA[startIndex]の文字から比較する
            for (int j = 0; j < checkStrB.length; j++) {

                //Test
//                System.out.println("initIndex :" + initIndex + "\n" +
//                        "startIndex : " + startIndex + "\n" +
//                        "endIndex : " + endIndex + "\n" +
//                        "breakFlag :" + breakflag + "\n" +
//                        "j :" + j);

                //検索の開始インデックスがStrAの大きさを超えたらbreak
                if (startIndex > arrayStrA.length - 1) {
                    //StrAの長さ超えた文字は削除するのでその分preScoreに加算
                    preScore += checkStrB.length - j;
                    break;
                }

                //下のループでstrBの一文字目からStrAの文字にも合致しなかったら
                // このループを抜ける
                if (breakflag) {
                    break;
                }

                //checkStrBの最初文字がヒットした位置からCheckStrBを
                //並べたときに本来その文字が入るべき場所までを検索する。
                //TODO  これがいるかどうかを調べる
//                if (j != 0) {
//                    endIndex = (endIndex > arrayStrA.length - 1) ?
//                            arrayStrA.length - 1 : initIndex + j;
//                }


                //arrayStrB[j]とstrAの文字を一文字づつ比べていく
                //検索開始位置は位置が決定したインデックスの次のインディックスから
                //本来その文字が入る予定のインデックスまで
                for (int h = startIndex; h <= endIndex; h++) {
                    if (arrayStrA[h].equals(checkStrB[j])) {
                        if (j == 0) {
                            initIndex = h;
                        }
                        //削除する間に入る文字の分スコアに加算
                        preScore += initIndex + j - h > 0 ?
                                initIndex + j - h : 0;
//                        preScore += initIndex + Math.abs(j - h);
                        //次比較を開始する位置を指定
                        startIndex = h + 1;
                        //ヒットした文字をリストに格納
                        tmpList[h] = checkStrB[j];
                        break;
                    }
                    if (h == strA.length() - 1 && j == 0) {
                        breakflag = true;
                    }
                }
            }

            //checkStrBの先頭から何もヒットしなかったから以下の処理をスキップ
            if (breakflag) {
                continue;
            }

            //tmpListとstrAを一文字ずつ比べて異なる数だけpreScoreを＋1
            for (int j = 0; j < strA.length(); j++) {
                //StrAの前方にあふれた文字は削除するのでその分加算
                if (i - initIndex > 0) {
                    preScore += (i - initIndex);
                }
                //まずtmpListのnullをカウント（エラー対策）
                if (tmpList[j] == null) {
                    preScore += 1;
                    continue;
                }
                if (!(tmpList[j].equals(arrayStrA[j]))) {
                    preScore += 1;
                }
            }
            //今までのスコアより低いスコアを保持
            if (preScore < score) {
                score = preScore;
            }
        }
        //score = score/numToOptimize;
        return score;
    }
}
