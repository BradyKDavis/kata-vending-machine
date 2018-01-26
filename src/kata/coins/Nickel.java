package kata.coins;

public class Nickel implements ICoin {

	@Override
	public CoinSize getCoinSize() 
	{
		return CoinSize.NICKEL;
	}

}
