package kata.vendingMachine.coinStock;

import com.google.inject.Inject;

import kata.coins.Dime;
import kata.coins.Nickel;
import kata.coins.Quarter;
import kata.vendingMachine.coinReader.ICoinReader;

public class PreloadedCoinStock extends BaseCoinStock
{
	private static final int beginningCoinBalance = 10;
	
	@Inject
	public PreloadedCoinStock(ICoinReader reader)
	{
		super(reader);
		preloadCoins();
	}
	
	private void preloadCoins()
	{
		for(int i = 0; i < beginningCoinBalance; i++)
		{
			addCoin(new Nickel());
			addCoin(new Dime());
			addCoin(new Quarter());
		}
	}
}
