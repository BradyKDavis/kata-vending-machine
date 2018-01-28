package kata.vendingMachine;

import java.util.ArrayList;

import com.google.inject.Inject;

import kata.coins.ICoin;
import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;
import kata.products.CandyProduct;
import kata.products.ChipProduct;
import kata.products.ColaProduct;
import kata.products.IProduct;
import kata.products.enums.ProductType;
import kata.vendingMachine.messageDisplay.IMessageDisplay;
import kata.vendingMachine.productManager.IProductManager;

public class VendingMachine 
{
	private static final Float NO_COINS = new Float(0);
	private static final Float NICKEL = new Float(.05);
	private static final Float DIME = new Float(.10);
	private static final Float QUARTER = new Float(.25);
	
	private Float currentCoinAmount = NO_COINS;
	
	private ArrayList<ICoin> coinReturn;
	
	private IProduct dispensedProduct = null;
	
	private IMessageDisplay messageDisplay;
	
	private IProductManager productManager;
	
	@Inject
	public VendingMachine(IMessageDisplay messageDisplay, IProductManager productManager)
	{
		this.messageDisplay = messageDisplay;
		this.productManager = productManager;
		coinReturn = new ArrayList<ICoin>();
	}
	
	public String getDisplayMessage()
	{
		return messageDisplay.getMessage();
	}
	
	public ArrayList<ICoin> getCoinReturn()
	{
		return coinReturn;
	}
	
	public void insertCoin(ICoin coin)
	{
		if(isNickel(coin))
		{
			addMoney(NICKEL);
		}
		else if(isDime(coin))
		{
			addMoney(DIME);
		}
		else if(isQuarter(coin))
		{
			addMoney(QUARTER);
		}
		else
		{
			coinReturn.add(coin);
		}
	}
	
	public void selectProduct(ProductType product)
	{
		if(productManager.getItemPrice(product) <= currentCoinAmount)
		{
			if(product == ProductType.COLA)
			{
				dispensedProduct = new ColaProduct();
			}
			else if(product == ProductType.CHIPS)
			{
				dispensedProduct = new ChipProduct();
			}
			else if(product == ProductType.CANDY)
			{
				dispensedProduct = new CandyProduct();
			}
			messageDisplay.completeTransaction();
		}
	}
	
	public IProduct getDispensedProduct()
	{
		return dispensedProduct;
	}
	
	private void addMoney(Float value)
	{
		currentCoinAmount += value;
		messageDisplay.addMoney(value);
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
