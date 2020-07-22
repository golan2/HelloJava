package jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

public class ConstructorWithMapOfFields {

    public static void main(String[] args) throws JsonProcessingException {
        final String json = " [{\"izik\":\"zbeck\", \"yuval\":\"king\"}]";
        System.out.println(json);

        final ObjectMapper objectMapper = new ObjectMapper();
        final Example[] example = objectMapper.readValue(json, Example[].class);
        System.out.println(Arrays.toString(example));
    }

    public static class Example {
        @JsonProperty("izik")
        public String izik;
        @JsonProperty("yuval")
        public String yuval;

        @Override
        public String toString() {
            return "Example{" +
                    "izik='" + izik + '\'' +
                    ", yuval='" + yuval + '\'' +
                    '}';
        }
    }

}