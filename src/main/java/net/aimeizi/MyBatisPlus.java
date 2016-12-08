package net.aimeizi;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * MyBatisPlus
 */
public class MyBatisPlus {

    public static void main(String[] args) throws Exception {

        ClassPool classPool = ClassPool.getDefault();

        // 装载目标jar文件
        classPool.insertClassPath("C:\\Users\\Administrator\\Downloads\\mybatis_plus.jar");

        // 装载JavaService
        CtClass ctClass = classPool.get("com.seventh7.mybatis.service.JavaService");

        // 获取无参stop方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("stop", null);
        ctMethod.setBody("{}");

        // 装载JavaUtils
        CtClass javaUtilsClass = classPool.get("com.seventh7.mybatis.util.JavaUtils");

        // 获取无参refValid方法
        CtMethod refValidMethod = javaUtilsClass.getDeclaredMethod("refValid", null);
        refValidMethod.setBody("{valid = true;validated = true;return valid;}");

        // 装载RefProject
        CtClass refProjectClass = classPool.get("com.seventh7.mybatis.ref.RefProject");

        // 获取无参initComponent方法
        CtMethod initComponentMethod = refProjectClass.getDeclaredMethod("initComponent", null);
        initComponentMethod.setBody("{}");
        // 获取无参notifyLicenseInvalid方法
        CtMethod notifyLicenseInvalidMethod = refProjectClass.getDeclaredMethod("notifyLicenseInvalid", null);
        notifyLicenseInvalidMethod.setBody("{}");


        // 装载ActivationDriver
        CtClass activationDriverClass = classPool.get("com.seventh7.mybatis.ref.license.ActivationDriver");
        // 获取String类型参数activate方法
        CtMethod activateMethod = activationDriverClass.getDeclaredMethod("activate", new CtClass[]{classPool.get("java.lang.String")});
        activateMethod.setBody("{return com.seventh7.mybatis.ref.license.ActivationResult.success(new com.seventh7.mybatis.ref.license.LicenseData(\"ameizi\",\"ameizi\"));}");

        // 装载CmProject
        CtClass cmProjectClass = classPool.get("com.seventh7.mybatis.ref.CmProject");
        // 获取无参initComponent方法
        initComponentMethod = cmProjectClass.getDeclaredMethod("initComponent", null);
        initComponentMethod.setBody("{}");

        // 写出到文件
        ctClass.writeFile();
        javaUtilsClass.writeFile();
        refProjectClass.writeFile();
        activationDriverClass.writeFile();
        cmProjectClass.writeFile();
    }

}
