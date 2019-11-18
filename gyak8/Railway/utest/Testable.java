package utest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.Set;
import java.util.HashSet;

class AssertionFailed extends Error {
	AssertionFailed(String m) {
		super(m);
	}
}

class APICheckFailed extends Error {
    APICheckFailed(String m) {
        super(m);
    }
}

public abstract class Testable {
	public int score() {
		return 0;
	}

	public int penalty() {
		return 0;
	}

	public String className() {
		return "";
	}

	public Object[] expectedMethods() throws Exception {
		return new Object[] {};
	}

	public Object[] expectedFields() throws Exception {
		return new Object[] {};
	}

	abstract public String description();

	abstract public void assertion() throws Exception;

	boolean ok;
	String reason;
	String apiError;
	String missing;

	TestResult run(boolean verbose) {
		missing = null;
		ok = true;
		try {
			assertion();
			String className = className();
			if (className != null && !className.isEmpty())
				checkAPI(className(), expectedMethods(), expectedFields());
		} catch (APICheckFailed e) {
			apiError = e.getMessage();
			ok = false;
		} catch (NoSuchMethodError | NoSuchMethodException e) {
			ok = false;
			missing = e.getMessage();
		} catch (NoClassDefFoundError | ClassNotFoundException e) {
			ok = false;
			missing = e.getMessage();
		} catch (InvocationTargetException e) {
			reason = resolveException(e.getCause());
			ok = false;
		} catch (AssertionFailed e) {
			reason = e.getMessage();
			ok = false;
		} catch (Exception e) {
			reason = resolveException(e);
			ok = false;
		}
		if (ok) {
			int s = score();
			if (verbose)
				System.out.println((s > 0) ? String.format("  OK (%d pont)", s) : "  OK");
			return new TestResult(s, null, null);
		} else {
			if (missing == null && apiError == null) {
				int p = -penalty();
				if (verbose)
					if (reason != null)
						System.err.println((p < 0) ? String.format("  HIBA: %s (%d pont)", reason, p) : String.format("  HIBA: %s", reason));
					else
						System.err.println((p < 0) ? String.format("  HIBAS (%d pont)", p) : "	HIBAS!");
				return new TestResult(p, reason, null);
			} else if (missing != null) {
				if (verbose)
					System.out.println(String.format("  ... hianyzik: %s", missing));
				return new TestResult(0, null, missing);
			} else if (apiError != null) {
				if (verbose)
					System.err.println("  HIBA: " + apiError);
				return new TestResult(0, null, apiError);
			}
			return new TestResult(0, null, null);
		}
	}

	public void check(String message, boolean test) {
		if (!test)
			throw new AssertionFailed(message);
	}

	static String resolveException(Throwable e) {
		String message = "Kivetel keletkezett a futas soran: " + e.getClass();
		System.err.println("  HIBA: " + message);
		System.err.println("	    Uzenet: " + e.getMessage());
		System.err.println("");
		System.err.println("	    Stack trace:");
		StackTraceElement[] st = e.getStackTrace();
		for (int i = 0; i < st.length; ++i) {
			System.err.println("	      " + st[i]);
		}
		return message;
	}

	private static ByteArrayOutputStream outs = new ByteArrayOutputStream();
	private static InputStream systemIn = System.in;
	private static PrintStream systemOut = System.out;

	public static Class<?>[] getTypes(Object... objs) {
		Class<?>[] otypes = new Class<?>[objs.length];
		for (int i = 0; i < objs.length; ++i) {
			Class<?> c = objs[i].getClass();
			// "Manual unboxing"
			if (c == Integer.class)
				c = int.class;
			else if (c == Boolean.class)
				c = boolean.class;
			else if (c == Character.class)
				c = char.class;
			else if (c == Short.class)
				c = short.class;
			else if (c == Long.class)
				c = long.class;
			else if (c == Byte.class)
				c = byte.class;
			else if (c == Float.class)
				c = float.class;
			else if (c == Double.class)
				c = double.class;
			else
				c = objs[i].getClass();
			otypes[i] = c;
		}
		return otypes;
	}

	public static Object staticCall(String method, Object... parameters) throws Exception {
		Class<?>[] ptypes = getTypes(parameters);
		Method m = loadStaticMethod(method, ptypes);
		return m.invoke(null, parameters);
	}

	public static Object call(Object obj, String method, Object... parameters) throws Exception {
		Class<?>[] ptypes = getTypes(parameters);
		Method m = loadMethod(obj.getClass(), method, ptypes);
		return m.invoke(obj, parameters);
	}

	public static Object spawn(String className, Object... parameters) throws Exception {
		Constructor<?> c = loadConstructor(className, parameters);
		return c.newInstance(parameters);
	}

	public static Object fieldValue(String fieldName, Object obj) throws Exception {
		Field f = loadField(obj.getClass(), fieldName);
		return f.get(obj);
	}

	public static Object staticFieldValue(String fieldName) throws Exception {
		Field f = loadStaticField(fieldName);
		return f.get(null);
	}

	public static Class<?> loadClass(String className) throws Exception {
		try {
			return Class.forName(className);
		} catch (Exception e) {
			throw new ClassNotFoundException(String.format("Nem toltheto be a(z) %s osztaly.", className));
		}
	}

	public static Constructor<?> loadConstructor(String className, Object... parameters) throws Exception {
		Class<?>[] ptypes = getTypes(parameters);
		return loadConstructor(className, ptypes);
	}

	public static Constructor<?> loadConstructor(String className, Class<?>... arguments) throws Exception {
		Class<?> cl = loadClass(className);
		try {
			return cl.getDeclaredConstructor(arguments);
		} catch (Exception e) {
			throw new NoSuchMethodException(
					String.format("Nem talalhato a(z) %s osztaly konstruktora, amely a (%s) parametereket fogadja el.",
							className, joinClasses(arguments)));
		}
	}

	public static Method loadMethod(Class<?> methodClass, String methodName, Class<?>... arguments) throws Exception {
		try {
			return methodClass.getDeclaredMethod(methodName, arguments);
		} catch (Exception e) {
			throw new NoSuchMethodException(
					String.format("Nem erheto el a(z) %s(%s) metodus.", methodName, joinClasses(arguments)));
		}
	}
	
	public static Method[] loadAllMethods(Class<?> methodClass, String methodName, Class<?>... arguments) throws Exception {
		loadMethod(methodClass, methodName, arguments); // throws exception if there is no such method
		return Stream.of(methodClass.getDeclaredMethods()).filter(m -> m.getName().equals(methodName))
			.filter(m -> Arrays.equals(m.getParameterTypes(), arguments)).toArray(Method[]::new);
	}


	public static Field loadField(Class<?> fieldClass, String fieldName) throws Exception {
		try {
			return fieldClass.getDeclaredField(fieldName);
		} catch (Exception e) {
			throw new NoSuchFieldException(String.format("Nem erheto el a(z) %s adattag.", fieldName));
		}
	}

	private static String[] splitName(String name) {
		String x[] = name.split("\\.");
		String c = "";
		for (int i = 0; i < x.length - 2; ++i)
			c += (x[i] + ".");
		c += x[x.length - 2];
		String m = x[x.length - 1];
		return (new String[] { c, m });
	}

	public static Method loadMethod(String methodName, Class<?>... arguments) throws Exception {
		String[] x = splitName(methodName);
		Class<?> methodClass = loadClass(x[0]);
		return loadMethod(methodClass, x[1], arguments);
	}

	public static Method[] loadAllMethods(String methodName, Class<?>... arguments) throws Exception {
		String[] x = splitName(methodName);
		Class<?> methodClass = loadClass(x[0]);
		return loadAllMethods(methodClass, x[1], arguments);
	}

	public static Method loadMethodWithReturnType(Class<?> returnType, String methodName, Class<?>... arguments)
			throws Exception {
		Method[] result = loadAllMethods(methodName, arguments);
		if (returnType == null) {
			return result[0];
		} else {
			for (Method m : result) {
				if (m.getReturnType() == returnType) {
					return m;					
				}
			}
		}
		throw new NoSuchMethodException(String.format("Nem %s a visszateresi tipusa a(z) %s(%s) metodusnak.",
			returnType.getName(), methodName, joinClasses(arguments)));
	}

	public static Method method(Class<?> returnType, String methodName, Class<?>... arguments) throws Exception {
		return loadMethodWithReturnType(returnType, methodName, arguments);
	}

	public static Method method(String methodName, Class<?>... arguments) throws Exception {
		return loadMethod(methodName, arguments);
	}

	public static Method optionalMethod(Class<?> returnType, String methodName, Class<?>... arguments)
			throws Exception {
		try {
			return loadMethodWithReturnType(returnType, methodName, arguments);
		} catch (NoSuchMethodError | NoSuchMethodException e) {
			return null;
		}
	}

	public static Method optionalMethod(String methodName, Class<?>... arguments) throws Exception {
		return optionalMethod(null, methodName, arguments);
	}

	public static Constructor<?> constructor(String constructorName, Class<?>... arguments) throws Exception {
		return loadConstructor(constructorName, arguments);
	}

	public static Constructor<?> optionalConstructor(String constructorName, Class<?>... arguments) throws Exception {
		try {
			return constructor(constructorName, arguments);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	public static Field loadField(String fieldName) throws Exception {
		String[] x = splitName(fieldName);
		Class<?> fieldClass = loadClass(x[0]);
		return loadField(fieldClass, x[1]);
	}

	public static Field field(String fieldName) throws Exception {
		return loadField(fieldName);
	}

	public static Field optionalField(String fieldName) throws Exception {
		try {
			return field(fieldName);
		} catch (Exception e) {
			return null;
		}
	}

	public static Method loadStaticMethodWithReturnType(Class<?> returnType, String methodName, Class<?>... arguments)
			throws Exception {
		Method result = loadMethodWithReturnType(returnType, methodName, arguments);
		if (Modifier.isStatic(result.getModifiers())) {
			return result;
		} else {
			throw new NoSuchMethodException(String.format("A(z) %s metodus nem statikus.", methodName));
		}
	}

	public static Method loadStaticMethod(String methodName, Class<?>... arguments) throws Exception {
		return loadMethodWithReturnType(null, methodName, arguments);
	}

	public static Method staticMethod(Class<?> returnType, String methodName, Class<?>... arguments) throws Exception {
		return loadStaticMethodWithReturnType(returnType, methodName, arguments);
	}

	public static Method staticMethod(String methodName, Class<?>... arguments) throws Exception {
		return staticMethod(null, methodName, arguments);
	}

	public static Method optionalStaticMethod(Class<?> returnType, String methodName, Class<?>... arguments)
			throws Exception {
		try {
			return staticMethod(returnType, methodName, arguments);
		} catch (NoSuchMethodError | NoSuchMethodException e) {
			return null;
		}
	}

	public static Method optionalStaticMethod(String methodName, Class<?>... arguments) throws Exception {
		return optionalStaticMethod(null, methodName, arguments);
	}

	public static Field loadStaticField(String fieldName) throws Exception {
		Field result = loadField(fieldName);
		if (Modifier.isStatic(result.getModifiers())) {
			return result;
		} else {
			throw new NoSuchFieldException(String.format("A(z) %s adattag nem statikus.", fieldName));
		}
	}

	public static Field staticField(String fieldName) throws Exception {
		return loadStaticField(fieldName);
	}

	public static Field optionalStaticField(String fieldName) throws Exception {
		try {
			return staticField(fieldName);
		} catch (NoSuchFieldError | NoSuchFieldException e) {
			return null;
		}
	}

	public static void checkAPI(String className, Object[] expectedMethods, Object[] expectedFields) throws Exception {
		Class<?> cl = loadClass(className);
		Set<Method> methods = new HashSet<Method>();
		methods.addAll(Arrays.asList(cl.getDeclaredMethods()));
		methods.removeAll(Arrays.asList(expectedMethods));
                methods.removeIf(m -> Modifier.isPrivate(m.getModifiers()));
                if (cl.isEnum()) {
                    methods.remove(cl.getDeclaredMethod("values"));
                    methods.remove(cl.getDeclaredMethod("valueOf", String.class));
                }

                Set<Constructor> constructors = new HashSet<Constructor>();
                constructors.addAll(Arrays.asList(cl.getConstructors()));
                constructors.removeAll(Arrays.asList(expectedMethods));
		if (!methods.isEmpty() || !constructors.isEmpty()) {
			String msg = "";
			msg += String.format(
					"A(z) %s osztaly olyan nem rejtett metodusokat tartalmaz, amelyet nem kert a\nfeladat:\n\n",
					className);
			for (Method m : methods) {
				msg += m.toString() + "\n";
                        }
			for (Constructor c : constructors) {
				msg += c.toString() + "\n";
			}
			throw new APICheckFailed(msg);
		}
		Set<Field> fields = new HashSet<Field>();
		fields.addAll(Arrays.asList(cl.getDeclaredFields()));
		fields.removeAll(Arrays.asList(expectedFields));
                fields.removeIf(f -> Modifier.isPrivate(f.getModifiers()));
                if (cl.isEnum()) {
                	try {
                		fields.remove(cl.getDeclaredField("$VALUES"));
                	} catch (Exception e) {
                	}
                }
		if (!fields.isEmpty()) {
			String msg = "";
			msg += String.format("A(z) %s osztaly olyan nem rejtett mezoket tartalmaz, amelyet nem kert a\nfeladat:\n\n",
					     className);
			for (Field m : fields) {
				msg += m.toString() + "\n";
			}
			throw new APICheckFailed(msg);
		}
	}

	public static boolean isAbstractClass(String className) {
		try {
			return Modifier.isAbstract(Class.forName(className).getModifiers());
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
	public static Class<?> getSuperClass(String className) {
		try {
			return Class.forName(className).getSuperclass();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}		
	}

	public static boolean doesImplementInterface(String className, Class<?> ifc) {
		try {
			return ifc.isAssignableFrom(Class.forName(className));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}		
	}
	
	public static void feedSystemIn(String input) {
		InputStream ins = new ByteArrayInputStream(input.getBytes());
		System.setIn(ins);
	}

	public static void releaseSystemIn() {
		System.setIn(systemIn);
	}

	public static void grabSystemOut() {
		outs = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outs));
	}

	public static String getSystemOut() {
		return outs.toString();
	}

	public static void releaseSystemOut() {
		System.setOut(systemOut);
	}

	public static String joinClasses(Class<?>... classes) {
		return join(", ", Stream.of(classes).map(Class::getName).toArray(String[]::new));
	}

	public static String join(String delimiter, String... substrings) {
		if (substrings.length == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < (substrings.length - 1); ++i) {
			sb.append(substrings[i]);
			sb.append(delimiter);
		}
		sb.append(substrings[substrings.length - 1]);
		return sb.toString();
	}
}
