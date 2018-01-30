package kata.vendingMachine;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.google.inject.Inject;

import kata.coins.ICoin;
import kata.coins.enums.CoinType;
import kata.products.IProduct;
import kata.products.enums.ProductType;
import kata.vendingMachine.coinReader.ICoinReader;
import kata.vendingMachine.coinStock.ICoinStock;
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
	
	private ArrayList<ICoin> coinReturn;
	
	private IProductStock productStock;
	
	private ICoinReader coinReader;
	
	private ICoinStock coinStock;
	
	@Inject
	public VendingMachine(IMessageDisplay messageDisplay, IProductManager productManager,
			IProductStock productStock, ICoinReader coinReader, ICoinStock coinStock)
	{
		this.messageDisplay = messageDisplay;
		this.productManager = productManager;
		this.productStock = productStock;
		this.coinReader = coinReader;
		this.coinStock = coinStock;
		coinReturn = new ArrayList<ICoin>();
		setChangeAvailability();
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
		CoinType type = coinReader.getCoinType(coin);
		if(type != CoinType.OTHER)
		{
			coinStock.addCoin(coin);
		}
		else
		{
			coinReturn.add(coin);
		}
		if(type == CoinType.NICKEL)
		{
			addMoney(NICKEL);
		}
		else if(type == CoinType.DIME)
		{
			addMoney(DIME);
		}
		else if(type == CoinType.QUARTER)
		{
			addMoney(QUARTER);
		}
	}
	
	public void selectProduct(ProductType product)
	{
		BigDecimal price = productManager.getItemPrice(product);
		if(!productStock.hasProductInStock(product))
		{
			messageDisplay.reportSoldOut();
		}
		else if(price.compareTo(currentCoinAmount) <= 0)
		{
			if(currentCoinAmount.compareTo(price) > 0)
			{
				if(coinStock.canMakeChange())
				{	
					BigDecimal change = currentCoinAmount.subtract(price);
					finalizeTransaction(product);
					ArrayList<ICoin> coins = coinStock.getChangeForAmount(change);
					coinReturn.addAll(coins);
				}
			}
			else
			{
				finalizeTransaction(product);
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

	public void returnCoins()
	{
		ArrayList<ICoin> coins = coinStock.getChangeForAmount(currentCoinAmount);
		coinReturn.addAll(coins);
		currentCoinAmount = NO_COINS;
		messageDisplay.cancelTransaction();
	}

	private void setChangeAvailability()
	{
		boolean value = coinStock.canMakeChange();
		messageDisplay.reportCanOfferChange(value);
	}
	
	private void finalizeTransaction(ProductType product)
	{
		dispensedProduct = productStock.getProduct(product);
		currentCoinAmount = NO_COINS;
		messageDisplay.completeTransaction();
		setChangeAvailability();
	}

}
