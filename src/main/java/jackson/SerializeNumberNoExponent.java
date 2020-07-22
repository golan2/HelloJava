package jackson;

import org.json.JSONObject;

import java.math.BigDecimal;

public class SerializeNumberNoExponent {

    private static final String WEIRD_NUMBER = "111111111.333333333";

    public static void main(String[] args) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("BigDecimal", new BigDecimal(WEIRD_NUMBER));
        jsonObject.put("Float", new Float(WEIRD_NUMBER));
        jsonObject.put("Double", new Double(WEIRD_NUMBER));
        System.out.println(jsonObject.toString());
    }
}
