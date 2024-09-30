package nelisriebezos.getvectored.util;

import lombok.NonNull;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern PARAMETER_PATTERN = Pattern.compile("\\$\\{(.+?)\\}");

    public static String replaceArguments(@NonNull String messageTemplate, @NonNull Map<String, String> arguments) {
        StringBuffer result = new StringBuffer();
        Matcher matcher = PARAMETER_PATTERN.matcher(messageTemplate);

        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = arguments.containsKey(key) ? arguments.get(key) : matcher.group(0);
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static String getSimpleString(@NonNull String fullname) {
        int lastDot = fullname.lastIndexOf('.');
        return fullname.substring(lastDot + 1);
    }

    public static String convertSpacesToDashes(String input) {
        return input.replace(' ', '-');
    }
}

