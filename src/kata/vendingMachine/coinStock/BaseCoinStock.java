package kata.vendingMachine.coinStock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.google.inject.Inject;

import kata.coins.ICoin;
import kata.coins.enums.CoinType;
import kata.vendingMachine.coinReader.ICoinReader;

public class BaseCoinStock implements ICoinStock
{
	private static final BigDecimal ZERO = new BigDecimal("0");
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
		return hasChangeForCentsRecursive(QUARTER_VALUE);
	}
	
	private boolean hasChangeForCentsRecursive(BigDecimal amount)
	{
		if(amount.equals(ZERO))
		{
			return true;
		}
		if(amount.equals(NICKEL_VALUE))
		{
			return !nickels.empty();
		}
		else if(amount.compareTo(DIME_VALUE) >= 0)
		{
			BigDecimal cents = amount;
			int i = 0;
			while(cents.compareTo(QUARTER_VALUE) >= 0 && i < dimes.size())
			{
				cents = cents.subtract(QUARTER_VALUE);
				i++;
			}
			while(cents.compareTo(DIME_VALUE) >= 0 && i < dimes.size())
			{
				cents = cents.subtract(DIME_VALUE);
				i++;
			}
			i = 0;
			while(cents.compareTo(NICKEL_VALUE) >= 0 && i < nickels.size())
			{
				cents = cents.subtract(NICKEL_VALUE);
				i++;
			}
			return cents.compareTo(ZERO) == 0 && hasChangeForCentsRecursive(amount.subtract(NICKEL_VALUE));
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


	@Override
	public ArrayList<ICoin> getChangeForAmount(BigDecimal amount)
	{
		ArrayList<ICoin> coins = new ArrayList<ICoin>();
		while(amount.compareTo(QUARTER_VALUE) >= 0)
		{
			amount = amount.subtract(QUARTER_VALUE);
			coins.add(quarters.pop());
		}
		while(amount.compareTo(DIME_VALUE) >= 0)
		{
			amount = amount.subtract(DIME_VALUE);
			coins.add(dimes.pop());
		}
		while(amount.compareTo(NICKEL_VALUE) >= 0)
		{
			amount = amount.subtract(NICKEL_VALUE);
			coins.add(nickels.pop());
		}
		return coins;
	}

}
