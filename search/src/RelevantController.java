import lombok.Getter;

/**
 * サーバーから文字列を受け取って関連度を{@link Relevantsearch}に渡すクラス
 *
 * @Author ryu
 */
@Getter
public class RelevantController {
    private double score;

    /**
     * コンストラクタ
     */
    public RelevantController (){
    }

    public void giveToSearch(){
        DataContainer container = new DataContainer();
        String strA = container.getStrA();
        String strB = container.getStrB();

        double score = Relevantsearch.isRelevantScore(strA, strB);
        this.score = score;


    }
}
