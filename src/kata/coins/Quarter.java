package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Quarter extends AbstractCoin 
{
	private static final String NAME = "quarter";
	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.QUARTER;
	}

	@Override
	public CoinWeight getCoinWeight() 
	{
		return CoinWeight.QUARTER;
	}
	
	@Override 
	public String toString()
	{
		return NAME;
	}

}
