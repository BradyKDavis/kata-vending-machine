package kata.tests;

import static org.junit.Assert.*;
import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.inject.Guice;
import com.google.inject.Injector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExactChangeOnlyTestSuite
{
	private Injector injector;
	
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;
	
	//System under test
	private VendingMachine vendingMachine;
	
	@Before
	public void setUp() throws Exception
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
	}

	@Test
	public void testAThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeFiveCents()
	{
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}

}
