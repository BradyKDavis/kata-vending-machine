package kata.dependency;

import kata.vendingMachine.coinReader.ICoinReader;
import kata.vendingMachine.coinReader.KataCoinReader;
import kata.vendingMachine.coinReturn.CoinReturn;
import kata.vendingMachine.coinReturn.ICoinReturn;
import kata.vendingMachine.coinStock.ICoinStock;
import kata.vendingMachine.coinStock.PreloadedCoinStock;
import kata.vendingMachine.messageDisplay.IMessageDisplay;
import kata.vendingMachine.messageDisplay.MessageDisplay;
import kata.vendingMachine.productManager.IProductManager;
import kata.vendingMachine.productManager.KataProductManager;
import kata.vendingMachine.productStock.IProductStock;
import kata.vendingMachine.productStock.KataProductStock;

import com.google.inject.AbstractModule;

public class KataDependencyModule extends AbstractModule
{

	@Override
	protected void configure()
	{
		bind(ICoinReader.class).to(KataCoinReader.class);
		
		bind(IProductStock.class).to(KataProductStock.class);
		
		bind(IMessageDisplay.class).to(MessageDisplay.class);
		
		bind(IProductManager.class).to(KataProductManager.class);
		
		bind(ICoinReturn.class).to(CoinReturn.class);
	}

}
