import java.util.Arrays;

/**
 * To get a relavent score between two word.
 * this is following the Levenshtein distance.
 */
public class Relaventsearch {
    /**
     *
     * @param strA
     * @param strB
     * @return score
     */
    public static double isRelaventScore(String strA , String strB){
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
        for(int i = 0; i < strB.length(); i++ ){
            String[] tmpList = new String[strA.length()];
            System.out.println("i"+i);
            int cnt = 0;
            int breakpoint = 0;
            int preScore = i;
            //test pre
            System.out.println("preScore: " + preScore);
            String[] checkStrB = Arrays.copyOfRange(arrayStrB,i,strB.length());

            for(int j = 0; j < checkStrB.length; j++){
                //int setIndex = -1;
                if(cnt == arrayStrA.length){
                    preScore += checkStrB.length - j;
                    break;
                }
                int muchIndexOfStrB = -1;
                //下のループでstrBの一文字目からStrAの文字にも合致しなかったら
                // このループを抜ける
                if(breakpoint == -1){
                    break;
                }

                for(int h = cnt;  h < strA.length(); h++ ){
                    if(arrayStrA[h].equals(checkStrB[j])){
                        if(muchIndexOfStrB != -1){
                            preScore += (j - muchIndexOfStrB -1);
                        }
                        tmpList[h] = checkStrB[j];
                        cnt = h;
                        muchIndexOfStrB = j;
                        //setIndex = h;
                        break;
                    }
                    if(h == strA.length()-1 && j ==0){
                        breakpoint = -1;
                    }
                    if(h == (strA.length() -1)){
                        tmpList[setIndex+1] = "";
                    }

                }
            }
            for(int j = 0; j < strA.length(); j++){
                if(tmpList[j] == null){
                    //test 2
                    System.out.println("2: +1");
                    preScore += 1;
                    continue;
                }
                if(!(tmpList[j].equals(arrayStrA[j]))){
                    //test 3
                    System.out.println("3: +1");
                    preScore += 1;
                }
            }
            //test
            System.out.println("score :" + preScore);
            if(preScore < score){
                score = preScore;
            }

        }
        //score = score/numToOptimize;
        return score;
    }
}
