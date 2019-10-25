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
}
class TestClass{
	int[] testArray = {1,2,3};
	
}

interface TestInterface{
	
	
}
