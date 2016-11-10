package anno;

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes({"anno.CommentIsString"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class CommentIsStringProcessor extends AbstractProcessor {
	protected JavacElements utilities;
	protected TreeMaker treeMaker;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		System.err.println("init!!!!!!!!!!!!!!!!");
		Messager messager = processingEnv.getMessager();
		messager.printMessage(Diagnostic.Kind.NOTE, "WOW INIT--------------------");
		JavacProcessingEnvironment javacProcessingEnv = (JavacProcessingEnvironment) processingEnv;
		utilities = javacProcessingEnv.getElementUtils();
		treeMaker = TreeMaker.instance(javacProcessingEnv.getContext());
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.err.println("process!!!!!!!!!!!!!!!!");
		Messager messager = processingEnv.getMessager();
		messager.printMessage(Diagnostic.Kind.NOTE, "PROCESS--------------------");
		Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(CommentIsString.class);
		for (Element annotatedElement : annotatedElements) {
			String docComment = utilities.getDocComment(annotatedElement);
			JCTree.JCLiteral literal = treeMaker.Literal(docComment!=null ? docComment : "");
			JCTree elementTree = utilities.getTree(annotatedElement);
			if ( elementTree instanceof JCTree.JCVariableDecl ) {
				((JCTree.JCVariableDecl)elementTree).init = literal;
			}
			else if ( elementTree instanceof JCTree.JCMethodDecl ) {
				JCTree.JCStatement[] statements = new JCTree.JCStatement[1];
				statements[0] = treeMaker.Return(literal);
				JCTree.JCBlock body = treeMaker.Block(0, List.from(statements));
				((JCTree.JCMethodDecl)elementTree).body = body;
			}
		}
		return true;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
}
