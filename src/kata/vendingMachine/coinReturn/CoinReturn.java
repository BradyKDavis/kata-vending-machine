package kata.vendingMachine.coinReturn;

import java.util.ArrayList;

import kata.coins.ICoin;

public class CoinReturn implements ICoinReturn
{
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

}
