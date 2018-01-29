package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Nickel extends AbstractCoin
{

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

}
