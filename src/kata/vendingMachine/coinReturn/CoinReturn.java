package kata.vendingMachine.coinReturn;

import java.util.ArrayList;

import kata.coins.Dime;
import kata.coins.ICoin;
import kata.coins.Nickel;
import kata.coins.Quarter;

public class CoinReturn implements ICoinReturn
{
	private static final Double QUARTER_VALUE = new Double(.25);
	private static final Double DIME_VALUE = new Double(.10);
	private static final Double NICKEL_VALUE = new Double(.05);
	
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
	public void addChange(Double value)
	{
		while(value >= QUARTER_VALUE)
		{
			value -= QUARTER_VALUE;
			coins.add(new Quarter());
			
		}
		while(value >= DIME_VALUE)
		{
			value -= DIME_VALUE;
			coins.add(new Dime());
		}
		while(value >= NICKEL_VALUE)
		{
			value -= NICKEL_VALUE;
			coins.add(new Nickel());
		}
	}

}
