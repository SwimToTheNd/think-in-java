package com.me.thinkinginjava.chapter0_javabase.java_reflection;

/**
 * java????????????
 * <p>
 * 1.
 * ????????->?????????jvm.dll->????java?????->???????????????????->????BootstrapLoader????->????????java??????
 * <p>
 * 2. BootstrapLoader -????-> Launcher -?????????-> ExtClassLoader -????->
 * AppClassLoader -????-> ???????main()???????????
 * <p>
 * 3. ??????class??????????????????$???????.class
 * 
 * @author BloodFly
 *
 */
public class Test_Bootstrap_ClassLoader {

	// java?????????????????
	static {
		System.out.println("BootstrapLoader prepare!");
	}

	// ??????
	public static void main(String[] args) {

		// ????????????
		ClassLoader classLoader = Test_Bootstrap_ClassLoader.class.getClassLoader();
		// ?????????????AppClassLoader
		System.out.println("?????????" + classLoader);
		// AppClassLoader???????????ExtClassLoader
		System.out.println("??????????????????" + classLoader.getParent());
		// Launcher?????????BootstrpLoader
		System.out.println("???????????????????????????" + classLoader.getParent().getParent());
	}

}
