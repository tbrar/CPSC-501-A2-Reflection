import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

public class InspectorTest {

	@Test
	public void nameTest() {
		Inspector inspector = new Inspector();
		TestClass test = new TestClass();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output));
	    inspector.printClassName(test.getClass());
	    String expected = "CLASS NAME: TestClass\r\n";
	    String actual = output.toString();
	    assertEquals(expected, actual);
	}
	
	@Test
	public void nameTest2() {
		Inspector inspector = new Inspector();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output));
	    inspector.printClassName(TestInterface.class);
	    String expected = "INTERFACE NAME: TestInterface\r\n";
	    String actual = output.toString();
	    assertEquals(expected, actual);
	}
	
	@Test
	public void fieldTest() {
		Inspector inspector = new Inspector();
		TestClass test = new TestClass();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output));
	    inspector.printFields(test.getClass(), test, false, 0, false);
	    String expected = "----START FIELDS----\r\nFIELD 0: val\r\nTYPE: int\r\nMODIFERS: [final]\r\nVALUE: 3\r\n----END FIELDS----\r\n";
	    String actual = output.toString();
	    assertEquals(expected, actual);
	}
	
	@Test
	public void methodTest() {
		Inspector inspector = new Inspector();
		TestClass test = new TestClass();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(output));
	    inspector.printMethods(test.getClass(), 0);
	    String expected = "----START METHODS----\r\nMETHOD 0: myMethod\r\nEXCEPTION TYPES: [class java.io.FileNotFoundException]\r\nPARAMETER TYPES: [int]\r\nRETURN TYPE: [class java.lang.String]\r\nMODIFERS: [public]\r\n----END METHODS----\r\n";
	    String actual = output.toString();
	    assertEquals(expected, actual);
	}
}
class TestClass{
	final int val = 3;
	
	public String myMethod(int whatever) throws FileNotFoundException{
		return "Hello";
	}
	
}

interface TestInterface{
	
	
}
