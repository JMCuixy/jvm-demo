package org.jvm.processor.name.check;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;
import java.util.stream.IntStream;

/**
 * 程序名称规范的编译器插件
 * 如果命名不规范，将会输出一个编译器的 WARNING 信息
 * <p>
 * javac -encoding UTF-8 com/sun/processor/NameChecker.java
 *
 * @author : cuixiuyin
 * @date : 2019/12/26
 */
public class NameChecker {

    private final Messager messager;

    private NameCheckScanner nameCheckScanner = new NameCheckScanner();


    public NameChecker(ProcessingEnvironment processingEnv) {
        this.messager = processingEnv.getMessager();
    }

    /**
     * 对 Java 程序命名进行检查
     * - 类或接口：符合驼式命名法，首字母大写
     * - 方法：符合驼式命名法，首字母小写
     * - 字段：
     * [- 类、实例变量：符合驼式命名法，首字母小写
     * [- 常量：要求全部大写
     *
     * @param element
     */
    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    /**
     * 名称检查器实现类，继承自 JDK1.8 中新提供的 ElementScanner8
     * <p>
     * 将会以 Visitor 模式访问抽象语法树的中的元素
     */
    private class NameCheckScanner extends ElementScanner8<Void, Void> {

        /**
         * 检查类命名
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(), aVoid);
            checkCamelCase(e, true);
            return super.visitType(e, aVoid);
        }

        /**
         * 检查方法命名
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (e.getKind() == ElementKind.METHOD) {
                Name simpleName = e.getSimpleName();
                if (simpleName.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Diagnostic.Kind.WARNING, e.getClass().getName() + "一个普通方法" + simpleName + "不应当与类名冲突，避免与构造函数产生混淆", e);
                }
                checkCamelCase(e, false);
            }
            return super.visitExecutable(e, aVoid);
        }

        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            // 如果这个 Variable 是枚举或常量，则按大写命名检查，否则按照驼式命名法规则检查
            if (e.getKind() == ElementKind.ENUM || e.getConstantValue() != null || heuristicallyConstant(e)) {
                checkAllCaps(e);
            } else {
                checkCamelCase(e, false);
            }
            return super.visitVariable(e, aVoid);
        }

        /**
         * 判断一个变量是否是常量
         */
        private boolean heuristicallyConstant(VariableElement e) {
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                return true;
            }
            if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                return true;
            }
            return false;

        }

        /**
         * 检查传入的元素是否符合驼式命名法，如果不符合，则输出警告信息
         *
         * @param e           元素
         * @param initialCaps 首字母是否要大写
         */
        private void checkCamelCase(Element e, boolean initialCaps) {
            Name simpleName = e.getSimpleName();
            // 命名前面一位是否大写
            boolean previousUpper = false;
            // 常规命名（首字母大写或者小写）
            boolean conventional = true;
            IntStream codePoints = simpleName.codePoints();
            int firstCodePoint = codePoints.findFirst().getAsInt();
            // 首字母大写
            if (Character.isUpperCase(firstCodePoint)) {
                if (!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称：" + simpleName + "应当以小写字母开头", e);
                    return;
                }
                previousUpper = true;
            }
            // 首字母小写
            else if (Character.isLowerCase(firstCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "名称：" + simpleName + "应当以大写字母开头", e);
                    return;
                }
            } else {
                // 首字母既非大写也非小写，直接 WARNNING
                conventional = false;
            }

            if (conventional) {
                int[] ints = simpleName.codePoints().toArray();
                for (int i = 1; i < ints.length; i++) {
                    if (Character.isUpperCase(ints[i])) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    } else {
                        previousUpper = false;
                    }
                }
            }
            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称" + simpleName + "应当符合驼式命名法（Camel Case Names）", e);
            }
        }


        /**
         * 大写命名检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母
         *
         * @param e
         */
        private void checkAllCaps(VariableElement e) {
            Name simpleName = e.getSimpleName();
            IntStream codePoints = simpleName.codePoints();
            // 是否符合常规（第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母）
            boolean conventional = true;
            int firstCodePoint = codePoints.findFirst().getAsInt();
            if (!Character.isUpperCase(firstCodePoint)) {
                conventional = false;
            } else {
                // 前一个下划线
                boolean previousUnderscore = false;
                int[] ints = simpleName.codePoints().toArray();
                for (int i = 1; i < ints.length; i++) {
                    int cp = ints[i];
                    if (cp == (int) '_') {
                        if (previousUnderscore) {
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) || Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }
            if (!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "常量" + simpleName + "应当全部以大写字母或下划线命名，并且以字母开头", e);
            }
        }
    }
}
