package net.daisyli.resource.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * the annotated field will not proceesed by the resource
 * manager
 * @author lichengcheng
 * @since 2010-09-08
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {

}
