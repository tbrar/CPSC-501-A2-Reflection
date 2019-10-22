import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	System.out.println();
    	for(int i = 0; i < depth; i++) {
    		System.out.print('\t');
    	}
    	if(c.isInterface() == false) {
    		System.out.println("CLASS NAME: " + c.getName());
    	}
    	else {
    		System.out.println("INTERFACE NAME: " + c.getName());
    	}
    	
    	Constructor<?>[] cons = c.getConstructors();
    	for(int i = 0; i < cons.length; i++) {
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("CONSTRUCTOR " + i + ": " + cons[i].getName());
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
        	Class<?>[] types = cons[i].getParameterTypes();
        	System.out.println("TYPES: " + Arrays.asList(types));
        	
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
        	int mods = cons[i].getModifiers();
        	System.out.println("MODIFIERS: " + Modifier.toString(mods));
        	
    	}
    	
    	Method[] meths = c.getDeclaredMethods();
    	for(int i = 0; i < meths.length; i++) {
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("METHOD " + i + ": " + meths[i].getName());
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("EXCEPTION TYPES: " + Arrays.asList(meths[i].getExceptionTypes()));
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("PARAMETER TYPES: " + Arrays.asList(meths[i].getParameterTypes()));
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("RETURN TYPE: " + Arrays.asList(meths[i].getReturnType()));
    		
    		for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("MODIFERS: " + Arrays.asList(Modifier.toString(meths[i].getModifiers())));
    	}
    	
    	Field[] fields = c.getDeclaredFields();
    	for(int i = 0; i < fields.length; i++) {
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("FIELD " + i + ": " + fields[i].getName());
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
    		System.out.println("TYPE: " + fields[i].getType());
    	}
    	
    	Class temp = c;
    	while(temp.getSuperclass() != null) {
    		temp = temp.getSuperclass();
    		inspectClass(temp, obj, recursive, depth+1);
    	}
    	
    	Class<?>[] interfaces = c.getInterfaces();
    	for(int i = 0; i < interfaces.length; i++) {
    		inspectClass(interfaces[i], obj, recursive, depth+1);
    	}
    }
}
