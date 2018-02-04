package test.java;

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
		//arrange
		coins.add(dime);
		coins.add(quarter);
		//user can put 1.05 using quarters and dimes but cannot get a nickel back
		setVendingMachineWithCoins();
		
		//act
		//assert
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeTenCents()
	{
		//arrange
		coins.add(nickel);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		//act
		//assert
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeFifteenCents()
	{
		//arrange
		coins.add(dime);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		//act
		//assert
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeTwentyCents()
	{
		//arrange
		coins.add(dime);
		coins.add(nickel);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		//act
		//assert
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void testThatVendingMachineDisplaysExactChangeWhenMachineCannotChangeTwentyFiveCents()
	{
		//arrange
		coins.add(dime);
		coins.add(nickel);
		coins.add(quarter);
		setVendingMachineWithCoins();
		
		//act
		//assert
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineIsFedEnoughCoinsToProduceChangeItReadsInsertCoin()
	{
		//arrange
		coins.add(dime);
		coins.add(quarter);
		coins.add(nickel);
		//cannot make 20 cents
		setVendingMachineWithCoins();
		
		//act
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(nickel);
		
		vendingMachine.selectProduct(ProductType.CHIPS);
		
		//assert
		assertNotNull(vendingMachine.getDispensedProduct());
		//should be able to make 20 cents now that change has dispensed
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test 
	public void whenVendingMachineIsNotFedEnoughCoinsToProduceChangeItReadsExactChangeOnly()
	{
		//arrange
		coins.add(dime);
		coins.add(dime);
		coins.add(quarter);
		//cannot make 5 or 15 cents
		setVendingMachineWithCoins();
		
		//act
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.selectProduct(ProductType.COLA);
		
		//assert
		assertNotNull(vendingMachine.getDispensedProduct());
		//still can not make 5 or 15 cents
		assertEquals("THANK YOU", vendingMachine.getDisplayMessage());
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineCannotMakeChangeAndReturnCoinsPressedOnEnoughChangeInsertedExactChangeIsRead()
	{
		//arrange
		coins.add(nickel);
		coins.add(dime);
		coins.add(quarter);
		//cannot make 20 cents
		setVendingMachineWithCoins();
		
		//act
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(nickel);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.returnCoins();
		
		//assert
		assertEquals("EXACT CHANGE ONLY", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineCannotMakeChangeAndSomebodyInsertsMoreThanAvailableToChangeTheMachineWilNotTryToMakeChangeAndWillNotDispenseProduct()
	{
		//arrange
		coins.add(dime);
		coins.add(quarter);
		//cannot change 5 or 15
		setVendingMachineWithCoins();
		
		//act
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(dime);
		vendingMachine.selectProduct(ProductType.CHIPS);
		
		//assert
		assertNull(vendingMachine.getDispensedProduct());
	}
	
	@Test
	public void whenVendingMachineCannotMakeChangeAndExactChangeIsInsertedThenItemIsDispensed()
	{
		//arrange
		coins.add(nickel);
		setVendingMachineWithCoins();
		
		//act
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(nickel);
		vendingMachine.selectProduct(ProductType.CANDY);
		
		//assert
		assertEquals(ProductType.CANDY, vendingMachine.getDispensedProduct().getProductType());
	}
	
	//convenience method
	private void setVendingMachineWithCoins()
	{
		injector = Guice.createInjector(new LowCoinReturnModule(coins));
		vendingMachine = injector.getInstance(VendingMachine.class);
	}
	
	

}
