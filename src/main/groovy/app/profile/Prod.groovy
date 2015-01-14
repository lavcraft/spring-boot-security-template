package app.profile;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tolkv on 1/14/2015.
 */
@Target([ElementType.METHOD, ElementType.FIELD, ElementType.TYPE])
@Retention(RetentionPolicy.RUNTIME)
@Profile("prod")
public @interface Prod {
}
