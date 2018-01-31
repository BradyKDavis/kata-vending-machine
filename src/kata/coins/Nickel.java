package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Nickel extends AbstractCoin
{
	private static final String NAME = "nickel";
	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.NICKEL;
	}

	@Override
	public CoinWeight getCoinWeight() 
	{
		return CoinWeight.NICKEL;
	}

	@Override 
	public String toString()
	{
		return NAME;
	}
	
}
