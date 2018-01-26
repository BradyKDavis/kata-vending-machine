package kata.vendingMachine;

import java.text.DecimalFormat;

import kata.coins.CoinSize;
import kata.coins.ICoin;

public class VendingMachine 
{
	private static final String INSERT_COIN = "INSERT COIN";
	private static final Float NO_COINS = new Float(0);
	private static final Float NICKEL = new Float(.05);
	private static final Float DIME = new Float(.10);
	
	private DecimalFormat currencyFormat = new DecimalFormat("0.00");
	
	private Float currentCoinAmount = NO_COINS;
	
	public String getDisplayMessage()
	{
		if(currentCoinAmount == NO_COINS)
		{
			return INSERT_COIN;
		}
		else
		{
			return currencyFormat.format(currentCoinAmount);
		}
	}
	
	public void insertCoin(ICoin coin)
	{
		if(coin.getCoinSize() == CoinSize.NICKEL)
		{
			currentCoinAmount += NICKEL;
		}
		else if(coin.getCoinSize() == CoinSize.DIME)
		{
			currentCoinAmount += DIME;
		}
	}
}
