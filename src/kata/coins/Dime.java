package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Dime extends AbstractCoin
{

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.DIME;
	}

	@Override
	public CoinWeight getCoinWeight() 
	{
		return CoinWeight.DIME;
	}

}
