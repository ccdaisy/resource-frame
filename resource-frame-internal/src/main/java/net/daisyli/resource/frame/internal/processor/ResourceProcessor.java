package net.daisyli.resource.frame.internal.processor;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic.Kind;

import net.daisyli.resource.frame.annotation.Resource;
import net.daisyli.resource.frame.internal.utils.StringUtils;

@SupportedAnnotationTypes("net.daisyli.resource.frame.annotation.Resource")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ResourceProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Messager messager = processingEnv.getMessager();

		messager.printMessage(Kind.NOTE, "Begin to process");
		Set<? extends Element> elements = roundEnv
				.getElementsAnnotatedWith(Resource.class);

		if (elements.size() > 0) {
			List<ResourceObjectElement> annotatedElements = new ArrayList<ResourceObjectElement>();

			for (Element ele : elements) {
				ResourceObjectElement resourceElement = new ResourceObjectElement(
						ele);
				annotatedElements.add(resourceElement);
			}
			try {
				generateManagerClass(processingEnv.getFiler(),
						annotatedElements);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * 
	 * @param annotatedElements
	 * @throws IOException
	 */
	protected void generateManagerClass(Filer sourceFiler,
			List<ResourceObjectElement> annotatedElements) throws IOException {

		writeSource(sourceFiler, annotatedElements);

	}

	/**
	 * 
	 * @param writer
	 * @param annotatedElements
	 * @throws IOException
	 */
	protected void writeSource(Filer filer,
			List<ResourceObjectElement> annotatedElements) throws IOException {
		for (ResourceObjectElement resourceObjectElement : annotatedElements) {
			System.out.println("Processing class: "
					+ resourceObjectElement.getSimpleClassName());
			System.out.println(resourceObjectElement.getFields());

			JavaFileObject sourceFile = filer
					.createSourceFile(resourceObjectElement.getPackageName()
							+ ".element."
							+ resourceObjectElement.getSimpleClassName()
							+ "Element");
			Writer writer = sourceFile.openWriter();
			writer.write(StringUtils.asLine(0, "package ",
					resourceObjectElement.getPackageName() + ".element", ";"));

			writer.write(StringUtils.asLine(0, "import "
					+ StringUtils.splitAtLast(this.getClass().getPackage()
							.getName(), ".")[0] + ".Element;"));

			// @Generated({...})

			writer.write(StringUtils.asLine(0,
					"import javax.annotation.Generated;"));
			writer.write(StringUtils.asLine(0, "@Generated(", "value=",
					StringUtils.quote(resourceObjectElement
							.getSimpleClassName()
							+ "Element"), ",", "date=", StringUtils
							.quote(new SimpleDateFormat(
									"yyyy-MM-dd'T'HH:mm:ss.SSSZ")
									.format(new Date())), ")"));
			writer.write(StringUtils.asLine(0, "public enum "
					+ resourceObjectElement.getSimpleClassName()
					+ "Element implements Element {"));
			for (String field : resourceObjectElement.getFields()) {

				writer.write(StringUtils.asLine(4, field + ","));
			}
			writer.write(StringUtils.asLine(0, "}"));
			writer.flush();
			writer.close();
		}
	}

}
