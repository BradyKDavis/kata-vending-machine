package kata.coins;

public class Penny implements ICoin 
{

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.OTHER;
	}

}
