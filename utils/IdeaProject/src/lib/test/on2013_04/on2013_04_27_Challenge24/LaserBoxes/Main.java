package lib.test.on2013_04.on2013_04_27_Challenge24.LaserBoxes;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_04/on2013_04_27_Challenge24/LaserBoxes/LaserBoxes.task"))
			Assert.fail();
	}
}
