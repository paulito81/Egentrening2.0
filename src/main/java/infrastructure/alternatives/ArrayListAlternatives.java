package infrastructure.alternatives;

import javax.enterprise.inject.Alternative;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Paul on 24.10.2015.
 */
@Alternative
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})

public @interface ArrayListAlternatives {
}
