package kata.coins;

public class Quarter implements ICoin {

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.QUARTER;
	}

}
