package net.daisyli.resource.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * Define the collection of an entity.
 * 
 * @author lichengcheng
 * @since 2010-09-04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {

	/**
	 * define the collection of a entity
	 * collection for mongoDB
	 * table for MySQL
	 * destination for jms
	 * etc.
	 */
	String value() default "";
}
