package api;

import org.json.JSONObject;

import java.io.IOException;

public class GeneratorAPI {
    public String generateUsername() {
        WebAPI webapi = new WebAPI();

        JSONObject json = null;
        try {
            json = webapi.readJsonFromUrl("https://api.namefake.com/");
        } catch (IOException e) { }

        return json.get("username").toString();
    }

    public String generatePassword() {
        WebAPI webapi = new WebAPI();

        String pwdText = "";
        try {
            pwdText = webapi.readTextFromUrl("https://www.passwordrandom.com/query?command=password&format=text&count=1");
        } catch (IOException e) { pwdText = ""; }

        return pwdText;
    }
}
