package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Quarter implements ICoin 
{

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

}
