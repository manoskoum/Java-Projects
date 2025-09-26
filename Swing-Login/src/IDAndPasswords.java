import java.util.HashMap;

public class IDAndPasswords {

    HashMap<String,String> loginInfo= new HashMap<>();

    IDAndPasswords(){

        loginInfo.put("Bro","pizza");
        loginInfo.put("new","pizza1");
        loginInfo.put("NEW","pizza2");
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
