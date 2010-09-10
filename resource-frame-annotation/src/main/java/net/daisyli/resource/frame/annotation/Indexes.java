package net.daisyli.resource.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * defines a set of index
 * 
 * <pre>
 * {@code
 * 
 * @Indexes( {
 * 	@Index({ "sdid","fsdid" }),
 * })
 * 
 * }
 * </pre>
 * 
 * @author lichengcheng
 * @since 2010-09-08
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Indexes {

	/**
	 * a set of indexes for a field for example:
	 */
	Index[] value();
}
