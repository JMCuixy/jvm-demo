package org.jvm.processor.name.check;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 命名检查的自定义插入式注解处理器
 * <p>
 * 可以把注解处理器理解成插件，在这些插件里面可以读取、修改、添加抽象语法树中的任意元素。
 * 如果这些插件在处理注解期间对语法树进行了修改，编译器将回到解析和填充符号表的过程重新处理
 * <p>
 * javac -encoding UTF-8 com/sun/processor/NameCheckProcessor.java
 *
 * @author : cuixiuyin
 * @date : 2019/12/26
 */
// 可以用 "*" 表示支持所有的 Annotations
@SupportedAnnotationTypes("*")
// 可以处理哪些版本的 java 代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

    private NameChecker nameChecker;

    /**
     * 初始化名称检查插件
     *
     * @param processingEnv 代表了注解处理器框架的提供的一个上下文环境。
     *                      要创建新的代码、向编译器输出信息、获取其他工具类等都需要用到这个实例变量
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * 对输入的语法树的各个节点进行检查
     *
     * @param annotations 注解处理器所要处理的注解集合
     * @param roundEnv    当前这个 Round 中的语法树节点
     * @return true/false 表示该次 Round 的语法树是否发生变化，是否需要重新编译
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.checkNames(element);
            }
        }
        return false;
    }
}
