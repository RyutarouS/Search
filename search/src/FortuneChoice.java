import java.util.Random;


public class FortuneChoice {

    public static final String DAIKYOU = "大凶";
    public static final String KYOU = "凶";
    public static final String KITI = "吉";
    public static final String SUEKITI = "末吉";
    public static final String TYUKITI = "中吉";
    public static final String DAIKITI = "大吉";

    private UserInfo info = new UserInfo();

    public String choice () {

        // info.～と書いてあるところをmock化したい。
        String name = info.getName(1);
        String lank = info.getLank(1);
        String sex = info.getSex(1);

        Random rnd = new Random();

        int choice = rnd.nextInt(100)+1;
        String fortune =  "";

        if(choice <= 10) {
            fortune = DAIKYOU;
        } else if (choice > 10 && choice <= 30) {
            fortune = KYOU;
        } else if (choice > 30 && choice <= 50) {
            fortune = KITI;
        } else if (choice > 50 && choice <= 70) {
            fortune = SUEKITI;
        } else if (choice > 70 && choice <= 90) {
            fortune = TYUKITI;
        } else if (choice > 90 && choice <= 100) {
            fortune = DAIKITI;
        }

        if(isBlank(name)) {
            name = "あなた";
        } else {
            name = name + "さん";
        }

        if(isBlank(sex)) {
            sex = "人間";
        }

        if(isBlank(lank)) {
            lank = "一般会員";
        }

        return lank + "の," + "性別は" + sex + "の," + name + "の運勢は、," + fortune + "です。";
    }

    public static boolean isBlank (String str) {

        if(str.isEmpty()) {
            return true;
        }

        if(str.indexOf("　") > -1) {
            return true;
        } else if(str.indexOf(" ") > -1) {
            return true;
        }

        return false;
    }
}
