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
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("#####START CLASS INSPECTION#####");
    	for(int i = 0; i < depth; i++) {
    		System.out.print('\t');
    	}
    	if(c.isInterface() == false) {
    		System.out.println("CLASS NAME: " + c.getName());
    	}
    	else {
    		System.out.println("INTERFACE NAME: " + c.getName());
    	}
    	System.out.println();
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----START CONSTRUCTORS----");
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
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----END CONSTRUCTORS----");
    	System.out.println();
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----START METHODS----");
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
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----END METHODS----");
    	System.out.println();
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----START FIELDS----");
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
    		
        	for(int j = 0; j < depth; j++) {
        		System.out.print('\t');
        	}
        	
			try {
				fields[i].setAccessible(true);
				Object field = fields[i].get(obj);
	    		if(field != null) {
					System.out.println("VALUE: " + field);
	    		
					if(recursive == true && !(fields[i].getType().isPrimitive())) {
						inspectClass(fields[i].getType(), field, recursive, depth+1);
					}
	    		}
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

    		if(fields[i].getType().isArray()) {
				try {
					fields[i].setAccessible(true);
		        	System.out.println("COMPONENT TYPE: " + fields[i].get(obj).getClass().getComponentType());
		        	for(int j = 0; j < depth; j++) {
		        		System.out.print('\t');
		        	}
		        	System.out.println("ARRAY LENGTH: " + Array.getLength(fields[i].get(obj)));
		        	System.out.print("ARRAY CONTENTS: ");
					for(int j = 0; j < Array.getLength(fields[i].get(obj)); j++) {
						System.out.print(Array.get(fields[i].get(obj), j) + " ");
						if(recursive == true && Array.get(fields[i].get(obj), j) != null && !fields[i].get(obj).getClass().getComponentType().isPrimitive()) {
							inspectClass(Array.get(fields[i].get(obj), j).getClass(), Array.get(fields[i].get(obj), j), recursive, depth+1);
						}
					}
					System.out.println();
					System.out.println();
		    		
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
    	}
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----END FIELDS----");
    	System.out.println();
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----START INTERFACES----");
    	Class<?>[] interfaces = c.getInterfaces();
    	for(int i = 0; i < interfaces.length; i++) {
    		inspectClass(interfaces[i], obj, recursive, depth+1);
    	}
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----END INTERFACES----");
		System.out.println();
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----START SUPERCLASSES----");
    	Class temp = c;
    	while(temp.getSuperclass() != null) {
    		temp = temp.getSuperclass();
    		inspectClass(temp, obj, recursive, depth+1);
    	}
    	
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("----END SUPERCLASSES----");
		System.out.println();
		
    	for(int j = 0; j < depth; j++) {
    		System.out.print('\t');
    	}
    	
		System.out.println("####END CLASS INSPECTION####");
    }
	

}
