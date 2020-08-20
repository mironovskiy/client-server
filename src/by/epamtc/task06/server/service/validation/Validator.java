package by.epamtc.task06.server.service.validation;

public class Validator {

    public static boolean checkValueForNegativity(int value){
        if (value < 0){
            return false;
        }
        return true;
    }
}
