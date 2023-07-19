package jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.IOException;

public class JsonWithProblematicField {

    public static void main(String[] args) throws JsonProcessingException {
        String s = "{\n" +
                "    \"serialNumber\": 6046722230200091,\n" +
                "    \"fox3FwVersion\": avl_3.18.0_rc8\n" +
                "}\n";


        final Pojo aaa = new ObjectMapper().readValue(s, Pojo.class);

        System.out.println(aaa);
    }

    @Data
    private static class Pojo {
        private long serialNumber;
        @JsonDeserialize(using = UnquotedStringDeserializer.class)
        private String fox3FwVersion;
    }



    public static class UnquotedStringDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return jp.getText();
        }
    }

}
