package org.jvm.demo.processor;

import lombok.extern.slf4j.Slf4j;
import org.jvm.processor.lombok.annotations.Data;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yuan
 * @date 2019/12/20
 */
@Slf4j
@SupportedAnnotationTypes({"org.jvm.processor.lombok.annotations.Data"})
public class EventInterceptorProcessorImpl extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }
        Filer filer = processingEnv.getFiler();

        List<String> serviceList = new ArrayList(8);
        for (Element e : roundEnv.getElementsAnnotatedWith(Data.class)) {
            Data annotation = e.getAnnotation(Data.class);
            if (annotation == null) {
                continue;
            }
            if (!e.getKind().isClass() && !e.getKind().isInterface()) {
                continue;
            }
            TypeElement type = (TypeElement) e;
            serviceList.add(annotation.hashCode() + "=" + type.getQualifiedName().toString());
        }

        if (serviceList.isEmpty()) {
            return false;
        }

        String fileName = "marketing-event.def";
        try {
            FileObject f = filer.createResource(StandardLocation.CLASS_OUTPUT, "", fileName);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(f.openOutputStream(), Charset.defaultCharset()));
            for (int i = 0; i < serviceList.size(); i++) {
                String service = serviceList.get(i);
                pw.println(service);
                if (log.isDebugEnabled()) {
                    log.debug("[" + fileName + "] " + service);
                }
            }
            pw.close();
        } catch (IOException x) {
            processingEnv.getMessager()
                    .printMessage(Diagnostic.Kind.ERROR, String.format("failed to write file %s: %s", fileName, x.getMessage()));
        }

        return false;
    }

}
