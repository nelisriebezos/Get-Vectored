package nelisriebezos.getvectored.util;


import nelisriebezos.getvectored.util.exception.ClassPathResourceNotFound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ResourceReader {
    private ResourceReader() {}

    public static String readClassPathResourceAsString(String resourceName) throws ClassPathResourceNotFound {
        try {
            return readInputStream(getClassPathResource(resourceName));
        } catch (IOException e) {
            throw new ClassPathResourceNotFound(resourceName, e);
        }
    }

    private static String readInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, UTF_8))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static InputStream getClassPathResource(String resourceName) throws IOException {
        InputStream inputStream = getClassPathInputStream(resourceName);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourceName);
        }
        return inputStream;
    }

    private static InputStream getClassPathInputStream(String resourceName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(resourceName);
    }
}
