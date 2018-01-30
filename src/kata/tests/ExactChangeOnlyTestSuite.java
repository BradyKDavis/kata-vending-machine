package kata.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.dependency.LowCoinReturnModule;
import kata.products.enums.ProductType;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeTwentyFiveCents()
	{
		coins.add(dime);
		coins.add(nickel);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineIsFedEnoughCoinsToProduceChangeItReadsInsertCoin()
	{
		coins.add(dime);
		coins.add(quarter);
		coins.add(nickel);
		//cannot make 20 cents
		setVendingMachineWithCoins();
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(nickel);
		
		vendingMachine.selectProduct(ProductType.CHIPS);
		assertNotNull(vendingMachine.getDispensedProduct());
		//should be able to make 20 cents now that change has dispensed
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test public void whenVendingMachineIsNotFedEnoughCoinsToProduceChangeItReadsExactChangeOnly()
	{
		coins.add(dime);
		coins.add(dime);
		coins.add(quarter);
		//cannot make 5 or 15 cents
		setVendingMachineWithCoins();
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.selectProduct(ProductType.COLA);
		assertNotNull(vendingMachine.getDispensedProduct());
		//still can not make 5 or 15 cents
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	private void setVendingMachineWithCoins()
	{
		injector = Guice.createInjector(new LowCoinReturnModule(coins));
		vendingMachine = injector.getInstance(VendingMachine.class);
	}
	
	

}
