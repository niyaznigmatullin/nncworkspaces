package lib.test.on2013_03.on2013_03_03_.A;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/lib/test/on2013_03/on2013_03_03_/A/A.task"))
			Assert.fail();
	}
}
