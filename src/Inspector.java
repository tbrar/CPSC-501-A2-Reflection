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
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
    		
    		System.out.println("MODIFERS: " + Arrays.asList(Modifier.toString(fields[i].getModifiers())));
    		
    		try {
    			fields[i].setAccessible(true);
				Object field = fields[i].get(obj);
	    		if(field != null) {
					System.out.println("VALUE: " + field);
	    		
					if(recursive == true && !(fields[i].getType().isPrimitive())) {
						inspectClass(fields[i].getType(), field, recursive, depth+1);
					}
	    		}
			} catch (IllegalArgumentException | IllegalAccessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    	
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}

    		if(fields[i].getType().isArray() && !(fields[i].getType().isPrimitive())) {
				try {
					for(int j = 0; j < Array.getLength(fields[i].get(obj)); j++) {
						System.out.print(Array.get(fields[i].get(obj), j) + " ");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
    	}
    	
    	Class<?>[] interfaces = c.getInterfaces();
    	for(int i = 0; i < interfaces.length; i++) {
    		inspectClass(interfaces[i], obj, recursive, depth+1);
    	}
    	
    	Class temp = c;
    	while(temp.getSuperclass() != null) {
    		temp = temp.getSuperclass();
    		inspectClass(temp, obj, recursive, depth+1);
    	}
    	
    }
}
