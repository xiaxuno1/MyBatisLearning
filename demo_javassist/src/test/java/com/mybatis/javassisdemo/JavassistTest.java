package com.mybatis.javassisdemo;

import com.mybatis.dao.AccountDao;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;


public class JavassistTest {
    public JavassistTest() throws InstantiationException, IllegalAccessException {
    }

    @Test
    public void testGenerateAccountDaoImpl() throws Exception{
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类
        CtClass ctClass = pool.makeClass("com.mybatis.dao.Impl.AccountDaoImpl" );
        //制造接口
        CtClass ctInterface = pool.makeInterface("com.mybatis.dao.AccountDao");
        //实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中所有的方法
        // 获取接口中所有的方法，利用类的反射机制，获取方法的属性名称等
        // Method 类是 Java 反射 API 的一部分，用于描述和操作类的方法。
        Method[] methods = AccountDao.class.getDeclaredMethods();//获取所有声明的方法()
        //遍历实现抽象类的所有方法，这是一个匿名类，实现了遍历
        Arrays.stream(methods).forEach(method -> {
            // method是接口中的抽象方法
            // 把method抽象方法给实现了。
            try {
                // public void delete(){}
                // public int update(String actno, Double balance){}
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public "); // 追加修饰符列表
                methodCode.append(method.getReturnType().getName()); // 追加返回值类型
                methodCode.append(" ");
                methodCode.append(method.getName()); //追加方法名
                methodCode.append("(");
                // 拼接参数 String actno, Double balance，
                //这是一个 Class 对象的数组，用于存储方法参数的类型。
                // Class<?> 表示这是一个可以存储任何类型的 Class 对象的数组。
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName()); //如String
                    methodCode.append(" ");
                    methodCode.append("arg" + i);
                    if(i != parameterTypes.length - 1){ //多个参数，结尾判断
                        methodCode.append(",");
                    }
                }
                methodCode.append("){System.out.println(11111); ");
                // 动态的添加return语句
                String returnTypeSimpleName = method.getReturnType().getSimpleName();
                if ("void".equals(returnTypeSimpleName)) {

                }else if("int".equals(returnTypeSimpleName)){
                    methodCode.append("return 1;");
                }else if("String".equals(returnTypeSimpleName)){
                    methodCode.append("return \"hello\";");
                }
                methodCode.append("}");
                System.out.println(methodCode);
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    // 在内存中生成class，并且加载到JVM当中
    Class<?> clazz = ctClass.toClass();
    // 创建对象
    AccountDao accountDao = (AccountDao) clazz.newInstance();
    // 调用方法
        accountDao.insert("aaaaa");
        accountDao.delete();
        accountDao.update("aaaa", 1000.0);
        accountDao.selectByActno("aaaa");
}

}
/*
make(String methodCode, CtClass ctClass): 这是 CtMethod 类的一个静态方法，用于根据提供的代码和类创建一个新的 CtMethod 实例。
        methodCode.toString(): 这个参数是一个字符串，包含了方法的源代码。
        它通常是一个方法的完整定义，例如 "public void myMethod() { System.out.println(\"Hello\"); }"。
ctClass: 这是一个 CtClass 对象，表示你希望将新方法添加到的类。
CtClass 是 javassist 中的一个类，代表一个 Java 类的字节码。
*/

