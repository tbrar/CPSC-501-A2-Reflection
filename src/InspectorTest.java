import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
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
}
class TestClass{
	final int val = 3;
	
}

interface TestInterface{
	
	
}
