package kata.vendingMachine;

import java.math.BigDecimal;
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
import kata.vendingMachine.coinReturn.ICoinReturn;
import kata.vendingMachine.messageDisplay.IMessageDisplay;
import kata.vendingMachine.productManager.IProductManager;
import kata.vendingMachine.productStock.IProductStock;

public class VendingMachine 
{
	private static final BigDecimal NO_COINS = new BigDecimal("0.00");
	private static final BigDecimal NICKEL = new BigDecimal("0.05");
	private static final BigDecimal DIME = new BigDecimal("0.10");
	private static final BigDecimal QUARTER = new BigDecimal("0.25");
	
	private BigDecimal currentCoinAmount = NO_COINS;
	
	
	private IProduct dispensedProduct = null;
	
	private IMessageDisplay messageDisplay;
	
	private IProductManager productManager;
	
	private ICoinReturn coinReturn;
	
	private IProductStock productStock;
	
	@Inject
	public VendingMachine(IMessageDisplay messageDisplay, IProductManager productManager,
			ICoinReturn coinReturn, IProductStock productStock)
	{
		this.messageDisplay = messageDisplay;
		this.productManager = productManager;
		this.coinReturn = coinReturn;
		this.productStock = productStock;
	}
	
	public String getDisplayMessage()
	{
		return messageDisplay.getMessage();
	}
	
	public ArrayList<ICoin> getCoinReturn()
	{
		return coinReturn.getCoinsReturned();
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
			coinReturn.addCoin(coin);
		}
	}
	
	public void selectProduct(ProductType product)
	{
		BigDecimal price = productManager.getItemPrice(product);
		if(price.compareTo(currentCoinAmount) <= 0)
		{
			if(!productStock.hasProductInStock(product))
			{
				
			}
			else
			{
				dispensedProduct = productStock.getProduct(product);
				coinReturn.addChange(currentCoinAmount.subtract(dispensedProduct.getPrice()));
				currentCoinAmount = NO_COINS;
				messageDisplay.completeTransaction();
			}
		}
		else
		{
			messageDisplay.reportPrice(price);
		}
	}
	
	public IProduct getDispensedProduct()
	{
		return dispensedProduct;
	}
	
	private void addMoney(BigDecimal money)
	{
		currentCoinAmount = currentCoinAmount.add(money);
		messageDisplay.addMoney(money);
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

	public void returnCoins()
	{
		coinReturn.addChange(currentCoinAmount);
		currentCoinAmount = NO_COINS;
		messageDisplay.cancelTransaction();
	}


}
