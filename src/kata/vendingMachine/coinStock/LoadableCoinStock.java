package kata.vendingMachine.coinStock;

import java.util.ArrayList;

import com.google.inject.Inject;

import kata.coins.ICoin;
import kata.vendingMachine.coinReader.ICoinReader;

public class LoadableCoinStock extends BaseCoinStock
{
	@Inject
	public LoadableCoinStock(ICoinReader reader,ArrayList<ICoin> initialCoins)
	{
		super(reader);
		readInitialCoins(initialCoins);
	}
	
	private void readInitialCoins(ArrayList<ICoin> coins)
	{
		for(ICoin coin : coins)
		{
			addCoin(coin);
		}
	}

}
