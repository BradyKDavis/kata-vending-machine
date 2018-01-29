package kata.vendingMachine.messageDisplay;

import java.text.DecimalFormat;

public class MessageDisplay implements IMessageDisplay
{
	private static final String INSERT_COINS = "INSERT COIN";
	private static final String THANK_YOU = "THANK YOU";
	private static final String PRICE = "PRICE ";
	private static final Double ZERO = new Double(0);
	
	private Double currentMoney = ZERO;
	
	private String message = INSERT_COINS;
	
	private DecimalFormat currencyFormat = new DecimalFormat("0.00");
	
	private boolean respondToUserInteraction = false;
	
	@Override
	public String getMessage()
	{
		if(respondToUserInteraction)
		{
			String currentMessage = message;
			if(currentMoney > ZERO)
			{
				message = currencyFormat.format(currentMoney);
			}
			else
			{
				message = INSERT_COINS;
			}
			return currentMessage;
		}
		return message;
	}

	@Override
	public void addMoney(Float value)
	{
		currentMoney += value;
		message = currencyFormat.format(currentMoney);
	}
	
	public void completeTransaction()
	{
		currentMoney = ZERO;
		respondToUserInteraction = true;
		message = THANK_YOU;
	}
	
	public void reportPrice(Double price)
	{
		message = PRICE + currencyFormat.format(price);
		respondToUserInteraction = true;
	}

}
