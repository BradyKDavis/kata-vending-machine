package kata.vendingMachine;

import java.text.DecimalFormat;
import java.util.ArrayList;

import kata.coins.ICoin;
import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;
import kata.products.ChipProduct;
import kata.products.ColaProduct;
import kata.products.IProduct;
import kata.products.enums.ProductType;

public class VendingMachine 
{
	private static final String INSERT_COIN = "INSERT COIN";
	private static final Float NO_COINS = new Float(0);
	private static final Float NICKEL = new Float(.05);
	private static final Float DIME = new Float(.10);
	private static final Float QUARTER = new Float(.25);
	
	private DecimalFormat currencyFormat = new DecimalFormat("0.00");
	
	private Float currentCoinAmount = NO_COINS;
	
	private ArrayList<ICoin> coinReturn;
	
	private IProduct dispensedProduct = null;
	
	public VendingMachine()
	{
		coinReturn = new ArrayList<ICoin>();
	}
	
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
	
	public ArrayList<ICoin> getCoinReturn()
	{
		return coinReturn;
	}
	
	public void insertCoin(ICoin coin)
	{
		if(isNickel(coin))
		{
			currentCoinAmount += NICKEL;
		}
		else if(isDime(coin))
		{
			currentCoinAmount += DIME;
		}
		else if(isQuarter(coin))
		{
			currentCoinAmount += QUARTER;
		}
		else
		{
			coinReturn.add(coin);
		}
	}
	
	public void selectProduct(ProductType product)
	{
		if(product == ProductType.COLA)
		{
			dispensedProduct = new ColaProduct();
		}
		else if(product == ProductType.CHIPS)
		{
			dispensedProduct = new ChipProduct();
		}
	}
	
	public IProduct getDispensedProduct()
	{
		return dispensedProduct;
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
