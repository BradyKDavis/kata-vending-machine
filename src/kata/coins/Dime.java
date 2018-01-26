package kata.coins;

public class Dime implements ICoin {

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.DIME;
	}

}
