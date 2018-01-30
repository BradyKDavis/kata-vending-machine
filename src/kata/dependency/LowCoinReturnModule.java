package kata.dependency;

import java.util.ArrayList;
import java.util.List;

import kata.coins.ICoin;
import kata.vendingMachine.coinReader.ICoinReader;
import kata.vendingMachine.coinReader.KataCoinReader;
import kata.vendingMachine.coinReturn.CoinReturn;
import kata.vendingMachine.coinReturn.ICoinReturn;
import kata.vendingMachine.coinStock.ICoinStock;
import kata.vendingMachine.coinStock.InitialLoadedCoins;
import kata.vendingMachine.coinStock.LoadableCoinStock;
import kata.vendingMachine.messageDisplay.IMessageDisplay;
import kata.vendingMachine.messageDisplay.MessageDisplay;
import kata.vendingMachine.productManager.IProductManager;
import kata.vendingMachine.productManager.KataProductManager;
import kata.vendingMachine.productStock.IProductStock;
import kata.vendingMachine.productStock.KataProductStock;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class LowCoinReturnModule extends AbstractModule
{
	ArrayList<ICoin> initialCoins;
	public LowCoinReturnModule(ArrayList<ICoin> initialCoins)
	{
		super();
		this.initialCoins = initialCoins;
	}
	
	@Override
	protected void configure()
	{
		bind(new TypeLiteral<List<ICoin>>(){}).annotatedWith(InitialLoadedCoins.class).toInstance(initialCoins);
		
		bind(ICoinReader.class).to(KataCoinReader.class);
		
		bind(ICoinStock.class).to(LoadableCoinStock.class);
		
		bind(IProductStock.class).to(KataProductStock.class);
		
		bind(IMessageDisplay.class).to(MessageDisplay.class);
		
		bind(IProductManager.class).to(KataProductManager.class);
		
		bind(ICoinReturn.class).to(CoinReturn.class);
	}

}
