package kata.vendingMachine.coinReader;

import kata.coins.ICoin;
import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinType;
import kata.coins.enums.CoinWeight;

public class KataCoinReader implements ICoinReader
{
	@Override
	public CoinType getCoinType(ICoin coin)
	{
		if(isNickel(coin))
		{
			return CoinType.NICKEL;
		}
		if(isDime(coin))
		{
			return CoinType.DIME;
		}
		if(isQuarter(coin))
		{
			return CoinType.QUARTER;
		}
		return CoinType.OTHER;
	}
	
	private boolean isNickel(ICoin coin)
	{
		return coin.getCoinSize() == CoinSize.NICKEL && coin.getCoinWeight() == CoinWeight.NICKEL;
	}
	
	private boolean isDime(ICoin coin)
	{
		return coin.getCoinSize() == CoinSize.DIME && coin.getCoinWeight() == CoinWeight.DIME;
	}
	
	private boolean isQuarter(ICoin coin)
	{
		return coin.getCoinSize() == CoinSize.QUARTER && coin.getCoinWeight() == CoinWeight.QUARTER;
	}

}
