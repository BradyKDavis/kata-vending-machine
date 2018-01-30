package kata.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.dependency.LowCoinReturnModule;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ExactChangeOnlyTestSuite
{
	private Injector injector;
	
	private ArrayList<ICoin> coins;
	
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;
	
	//System under test
	private VendingMachine vendingMachine;
	
	@Before
	public void setUp() throws Exception
	{
		coins = new ArrayList<ICoin>();
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
	}

	@Test
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeFiveCents()
	{
		coins.add(dime);
		coins.add(quarter);
		setVendingMachineWithCoins();
		//user can put 1.05 using quarters and dimes but cannot get a nickel back
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeTenCents()
	{
		coins.add(nickel);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeFifteenCents()
	{
		coins.add(dime);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeTwentyCents()
	{
		coins.add(dime);
		coins.add(nickel);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	private void setVendingMachineWithCoins()
	{
		injector = Guice.createInjector(new LowCoinReturnModule(coins));
		vendingMachine = injector.getInstance(VendingMachine.class);
	}

}
