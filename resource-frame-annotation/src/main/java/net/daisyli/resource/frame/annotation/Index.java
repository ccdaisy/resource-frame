package net.daisyli.resource.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * Define the index of a class.
 * 
 * @author lichengcheng
 * @since 2010-09-04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Index {

	/**
	 * "sdid","groupId" for example
	 */
	String[] value();

	/**
	 * whether the index is a unique index
	 */
	boolean unique() default false;
}
