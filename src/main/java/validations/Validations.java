package validations;

import lombok.Data;

import javax.validation.constraints.Min;

public class Validations {


    public static void main(String[] args) {
        new MrBin().setValue(2);
    }


    @Data
    private static class MrBin {
        @Min(value = 1, message = "At least one")
        private int value;
    }
}
