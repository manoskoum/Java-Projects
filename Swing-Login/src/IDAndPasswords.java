import java.util.HashMap;

public class IDAndPasswords {

    HashMap<String,String> loginInfo= new HashMap<>();

    IDAndPasswords(){

        loginInfo.put("User","pass");
        loginInfo.put("Manos","manos");
        loginInfo.put("new","new");
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}

