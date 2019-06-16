public class UserInfo {

    public String getName(Integer id) {
        String name = "";

        if(id == 1) {
            name = "福田";
        } else if(id == 2) {
            name = "Alex";
        } else {
            name = "mock";
        }
        return name;
    }

    public String getSex(Integer sexId) {
        String sex = "";

        if(sexId == 1) {
            sex = "男";
        } else if(sexId == 2) {
            sex = "女";
        } else {
            sex = "人間";
        }
        return sex;
    }

    public String getLank(Integer lankId) {
        String lank = "";

        if(lankId == 1) {
            lank = "一般会員";
        } else if(lankId == 2) {
            lank = "プレミアム会員";
        }
        return lank;
    }
}
