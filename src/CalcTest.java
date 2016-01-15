import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;

public class CalcTest {
	//private Fraction f1;

	/*@Before
	public void setUp() {
		Fraction f1 = new Fraction(0);
	}*/

	@Test //initial test
	public void test1() {
		Fraction f1 = new Fraction(8);
		Fraction f2 = Calc.calculate("3 + 5");
		assertTrue(f1.equals(f2));
	}
	
	@Test
	public void test2() {
		Fraction f1 = new Fraction(25);
		Fraction f2 = Calc.calculate("10 + 15");
		assertTrue(f1.equals(f2));
	}
	
	@Test
	public void test3() {
		Fraction f1 = new Fraction(1111);
		Fraction f2 = Calc.calculate("1 + 10 + 100 + 1000");
		assertTrue(f1.equals(f2));
	}
	
	@Test //TODO BUG!!!
	public void test4() {
		Fraction f1 = new Fraction(1111);
		Fraction f2 = Calc.calculate("1 + 10 +100+1000");
		assertTrue(f1.equals(f2));
	}
	
	@Test //TODO BUG!!!
	public void test5() {
		Fraction f1 = new Fraction(1);
		Fraction f2 = Calc.calculate("1");
		assertTrue(f1.equals(f2));
	}
	
	@Test //TODO BUG!!! (out put has to be: 155.5) (or not bug?)
	public void test6() {
		Fraction f1 = new Fraction(311, 2);
		Fraction f2 = Calc.calculate("3 + 50 + 1 + 1 + 100 + 1/2");
		assertTrue(f1.equals(f2));
	}
	
	@Test //TODO BUG!!!
	public void test7() {
		Fraction f1 = new Fraction(1);
		Fraction f2 = Calc.calculate("1/2 + 1/2");
		assertTrue(f1.equals(f2));
	}
	
	@Test
	public void test8() {
		Fraction f1 = new Fraction(5, 4);
		Fraction f2 = Calc.calculate("1/2 + 3/4");
		assertTrue(f1.equals(f2));
	}
	
	

}
