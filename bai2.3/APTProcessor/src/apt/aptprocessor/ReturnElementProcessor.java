package apt.aptprocessor;
import java.util.Set;
 
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic.Kind;
 
@SupportedAnnotationTypes("apt.ann.ReturnElement")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ReturnElementProcessor extends AbstractProcessor {
 
   private Messager messager;
 
   @Override
   public void init(ProcessingEnvironment env) {
       env.getFiler();
       messager = env.getMessager();
   }
 
   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
	   for (TypeElement anno : annotations) {           
           Set<? extends Element> element = env.getElementsAnnotatedWith(anno);
           for (Element e : element) {
               if (e.getKind() != ElementKind.METHOD) {
                   messager.printMessage(Kind.ERROR, "@ReturnElement using for method only ", e);
               } 
               else {
                   ExecutableElement method = (ExecutableElement) e;
                   TypeMirror retType = method.getReturnType();
                   if (!int.class.getName().equals(retType.toString()))
                       messager.printMessage(Kind.ERROR, "Method using @ReturnElement must return int", e);
               }
           }
       }
       return true;
   }
}