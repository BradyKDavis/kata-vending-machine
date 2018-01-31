package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.FakeDime;
import kata.coins.FakeNickel;
import kata.coins.FakeQuarter;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Penny;
import kata.coins.Quarter;
import kata.dependency.KataDependencyModule;
import kata.vendingMachine.VendingMachine;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class AcceptCoinsTestSuite 
{
	private Injector injector;
	
	//System Under Test
	private VendingMachine vendingMachine;
	
	private Nickel nickel;
	private Dime dime;
	private Quarter quarter;
	private Penny penny;
	private FakeNickel fakeNickel;
	private FakeQuarter fakeQuarter;
	private FakeDime fakeDime;
	
	@Before
	public void setUp()
	{
		injector = Guice.createInjector(new KataDependencyModule());
		vendingMachine = injector.getInstance(VendingMachine.class);
		nickel = new Nickel();
		dime = new Dime();
		quarter = new Quarter();
		penny = new Penny();
		fakeNickel = new FakeNickel();
		fakeDime = new FakeDime();
		fakeQuarter = new FakeQuarter();
	}
	
	@Test
	public void whenVendingMachineHasNoCoinsInsertedDisplayShowsINSERTCOIN() 
	{
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineAcceptsANickelItUpdatesDisplayToShow5Cents()
	{
		vendingMachine.insertCoin(nickel);
		assertEquals("0.05", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void whenVendingMachineAcceptsADimeItUpdatesDisplayToShow10Cents()
	{
		vendingMachine.insertCoin(dime);
		assertEquals("0.10", vendingMachine.getDisplayMessage());
	}

	
	@Test
	public void whenVendingMachineAcceptsAQuarterItUpdatesDisplayToShow25Cents()
	{
		vendingMachine.insertCoin(quarter);
		assertEquals("0.25", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineAcceptsMultipleDifferentCoinsAndUpdatesDisplayToCorrectTotal()
	{
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(quarter);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(nickel);
		vendingMachine.insertCoin(dime);
		vendingMachine.insertCoin(quarter);
		assertEquals("1.10", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineDoesNotAcceptPenniesAndDoesNotIncreaseInValueWhenPennyAdded()
	{
		vendingMachine.insertCoin(penny);
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineDoesNotAcceptFakeQuarter()
	{
		vendingMachine.insertCoin(fakeQuarter);
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineDoesNotAcceptFakeNickel()
	{
		vendingMachine.insertCoin(fakeNickel);
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachineDoesNotAcceptFakeDime()
	{
		vendingMachine.insertCoin(fakeDime);
		assertEquals("INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void vendingMachinePlacesUnacceptedCoinsIntoTheCoinReturn()
	{
		ArrayList<ICoin> coins = new ArrayList<ICoin>();
		coins.add(fakeQuarter);
		coins.add(fakeDime);
		coins.add(fakeNickel);
		coins.add(penny);
		for(ICoin coin : coins)
		{
			vendingMachine.insertCoin(coin);
		}
		vendingMachine.insertCoin(nickel);
		assertArrayEquals(coins.toArray(), vendingMachine.getCoinReturn().toArray());
	}
}
