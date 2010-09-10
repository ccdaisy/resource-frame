/**
 * 
 */
package net.daisyli.resource.frame.internal.processor;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import net.daisyli.resource.frame.annotation.Ignore;
import net.daisyli.resource.frame.internal.utils.StringUtils;

/**
 * A wrapper object represents a element, provides straight forward interfaces
 * for downstream use.
 * 
 * @author SunNing
 * 
 * @since Aug 18, 2010
 */
public class ResourceObjectElement {

	private String simpleClassName;

	private String packageName;

	private List<String> fields = new ArrayList<String>();

	/**
	 * construct a object element with a java model
	 * 
	 * @param ele
	 */
	public ResourceObjectElement(Element ele) {
		String[] fullClassName = StringUtils.splitAtLast(ele.toString(), ".");

		setPackageName(fullClassName[0]);
		setSimpleClassName(fullClassName[1]);

		List<? extends Element> enclosedElements = ele.getEnclosedElements();
		for (Element e : enclosedElements) {
			if (e.getKind() == ElementKind.FIELD
					&& e.getAnnotation(Ignore.class) == null) {
				fields.add(e.toString());
			}
		}
	}

	public ResourceObjectElement() {
	}

	public String getSimpleClassName() {
		return simpleClassName;
	}

	public void setSimpleClassName(String simpleClassName) {
		this.simpleClassName = simpleClassName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

}
