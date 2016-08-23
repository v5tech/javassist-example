package net.aimeizi;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

/**
 * charles
 */
public class App {
    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        // 装载目标jar文件
        classPool.insertClassPath("C:\\Users\\Administrator\\Desktop\\charles.jar");
        // 装载目标Class
        CtClass ctClass = classPool.get("com.xk72.charles.License");

        // 获取无参构造
        CtConstructor ctConstructor = ctClass.getDeclaredConstructor(null);
        ctConstructor.setBody("{}");

        // 获取有参构造
        ctConstructor = ctClass.getDeclaredConstructor(new CtClass[]{classPool.get("java.lang.String"), classPool.get("java.lang.String")});
        ctConstructor.setBody("{this.d = \"aimeizi.net\";this.c = true;}");

        // 获取无参a方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("a", null);
        ctMethod.setBody("return true;");

        // 获取有参a方法，参数类型为(String,String)
        ctMethod = ctClass.getDeclaredMethod("a", new CtClass[]{classPool.get("java.lang.String"), classPool.get("java.lang.String")});
        ctMethod.setBody("return \"aimeizi.net\";");

        // 获取无参b方法
        ctMethod = ctClass.getDeclaredMethod("b", null);
        ctMethod.setBody("return \"aimeizi.net\";");

        // 获取无参c方法
        ctMethod = ctClass.getDeclaredMethod("c", null);
        ctMethod.setBody("return true;");

        // 获取无参d方法
        ctMethod = ctClass.getDeclaredMethod("d", null);
        ctMethod.setBody("return \"aimeizi.net\";");

        // 写出到文件
        ctClass.writeFile();
    }
}
