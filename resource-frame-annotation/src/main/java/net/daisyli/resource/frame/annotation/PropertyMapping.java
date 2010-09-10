package net.daisyli.resource.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * defines the mapping field for the property
 * @author lichengcheng
 * @since 2010-09-08
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyMapping {

	String value();
}
