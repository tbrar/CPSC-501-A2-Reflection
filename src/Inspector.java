
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
    	
    	Class temp = c;
    	while(temp.getSuperclass() != null) { // superclasses
    		temp = temp.getSuperclass();
    		inspectClass(temp, obj, recursive, depth+1);
    	}
    	
    	Class<?>[] interfaces = c.getInterfaces();
    	for(int i = 0; i < interfaces.length; i++) {
    		inspectClass(interfaces[i], obj, recursive, depth+1);
    	}
    }
}
