package kata.vendingMachine.coinStock;

import java.math.BigInteger;
import java.util.Stack;

import com.google.inject.Inject;

import kata.coins.ICoin;
import kata.coins.enums.CoinType;
import kata.vendingMachine.coinReader.ICoinReader;

public class BaseCoinStock implements ICoinStock
{
	protected Stack<ICoin> nickels;
	protected Stack<ICoin> dimes;
	protected Stack<ICoin> quarters;
	
	protected ICoinReader reader;
	
	@Inject
	public BaseCoinStock(ICoinReader reader)
	{
		this.reader = reader;
		nickels = new Stack<ICoin>();
		dimes = new Stack<ICoin>();
		quarters = new Stack<ICoin>();
	}
	
	
	@Override
	public boolean canMakeChange()
	{
		return !nickels.empty();
	}


	@Override
	public void addCoin(ICoin coin)
	{
		CoinType type = reader.getCoinType(coin);
		if(type == CoinType.NICKEL)
		{
			nickels.push(coin);
		}
		if(type == CoinType.DIME)
		{
			dimes.push(coin);
		}
		if(type == CoinType.QUARTER)
		{
			quarters.push(coin);
		}
	}

}
