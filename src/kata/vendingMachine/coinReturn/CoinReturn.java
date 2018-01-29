package kata.vendingMachine.coinReturn;

import java.math.BigDecimal;
import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Quarter;

public class CoinReturn implements ICoinReturn
{
	private static final BigDecimal QUARTER_VALUE = new BigDecimal("0.25");
	private static final BigDecimal DIME_VALUE = new BigDecimal("0.10");
	private static final BigDecimal NICKEL_VALUE = new BigDecimal("0.05");
	
	private ArrayList<ICoin> coins;
	
	public CoinReturn()
	{
		coins = new ArrayList<ICoin>();
	}
	
	@Override
	public ArrayList<ICoin> getCoinsReturned()
	{
		return coins;
	}

	@Override
	public void addCoin(ICoin coin)
	{
		coins.add(coin);
	}
	
	@Override
	public void addChange(BigDecimal value)
	{
		while(value.compareTo(QUARTER_VALUE) >= 0)
		{
			value = value.subtract(QUARTER_VALUE);
			coins.add(new Quarter());
			
		}
		while(value.compareTo(DIME_VALUE) >= 0)
		{
			value = value.subtract(DIME_VALUE);
			coins.add(new Dime());
		}
		while(value.compareTo(NICKEL_VALUE) >= 0)
		{
			value = value.subtract(NICKEL_VALUE);
			coins.add(new Nickel());
		}
	}

}
