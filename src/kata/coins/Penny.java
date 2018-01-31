package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Penny extends AbstractCoin
{
	private static final String NAME = "penny";
	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.OTHER;
	}

	@Override
	public CoinWeight getCoinWeight() {
		return CoinWeight.OTHER;
	}
	
	@Override 
	public String toString()
	{
		return NAME;
	}

}
