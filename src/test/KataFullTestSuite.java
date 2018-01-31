package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

public class KataFullTestSuite
{

	@RunWith(Suite.class)
	@SuiteClasses({
				AcceptCoinsTestSuite.class,
				MakeChangeTestSuite.class,
				SelectProductTestSuite.class,
				SoldOutTestSuite.class,
				ExactChangeOnlyTestSuite.class
				})
	public class RunTestSuite 
	{
	}

}
