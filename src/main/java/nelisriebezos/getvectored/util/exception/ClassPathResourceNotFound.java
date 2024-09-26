package nelisriebezos.getvectored.util.exception;

public class ClassPathResourceNotFound extends Throwable {
    public ClassPathResourceNotFound(String classPath, Throwable cause) {
        super("Resource was not found on classPath: " + classPath, cause);
    }
}
