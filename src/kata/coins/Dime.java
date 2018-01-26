package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Dime implements ICoin {

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.DIME;
	}

	@Override
	public CoinWeight getCoinWeight() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
