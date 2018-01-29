package kata.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import kata.coins.ICoin;
import kata.dependency.KataDependencyModule;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ReturnCoinsTestSuite
{
	private Injector injector;
	
	//System under test
	private VendingMachine vendingMachine;
	
	@Before
	public void setUp() throws Exception
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
	}

	@Test
	public void whenReturnCoinsPressedWithNoCoinsInsertedNoCoinsReturned()
	{
		ArrayList<ICoin> expected = new ArrayList<ICoin>();
		ArrayList<ICoin> actual = vendingMachine.returnCoins();
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

}
