package kata.vendingMachine.coinStock;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

import com.google.inject.Inject;

import kata.coins.ICoin;
import kata.coins.enums.CoinType;
import kata.vendingMachine.coinReader.ICoinReader;

public class BaseCoinStock implements ICoinStock
{
	private static final BigDecimal MAX_TO_CHANGE = new BigDecimal(".50");
	
	private static final BigDecimal NICKEL_VALUE = new BigDecimal("0.05");
	private static final BigDecimal DIME_VALUE = new BigDecimal("0.10");
	private static final BigDecimal QUARTER_VALUE = new BigDecimal("0.25");
	
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
		return hasChangeForCents(DIME_VALUE);
	}
	
	private boolean hasChangeForCents(BigDecimal amount)
	{
		if(amount.equals(new BigDecimal("0")))
		{
			return true;
		}
		if(amount.equals(NICKEL_VALUE))
		{
			return !nickels.empty();
		}
		else if(amount.equals(DIME_VALUE))
		{
			return (!dimes.empty()) 
					&& hasChangeForCents(amount.subtract(NICKEL_VALUE));
		}
		return false;
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
