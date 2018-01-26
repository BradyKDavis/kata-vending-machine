package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class Penny implements ICoin 
{

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.OTHER;
	}

	@Override
	public CoinWeight getCoinWeight() {
		// TODO Auto-generated method stub
		return null;
	}

}