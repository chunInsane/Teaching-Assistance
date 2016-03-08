package cn.edu.nuc.acmicpc.common.util;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * global validate service
 */
public class ValidateUtil {

    public static Map<String, String> fieldErrorsToMap(List<FieldError> fieldErrors) {
        Map<String, String> result = new HashMap<>();
        if (fieldErrors != null && fieldErrors.size() > 0) {
            for (FieldError fieldError : fieldErrors) {
                result.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        return result;
    }
}
