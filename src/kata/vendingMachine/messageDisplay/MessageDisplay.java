package kata.vendingMachine.messageDisplay;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MessageDisplay implements IMessageDisplay
{
	private static final String INSERT_COIN = "INSERT COIN";
	private static final String THANK_YOU = "THANK YOU";
	private static final String SOLD_OUT = "SOLD OUT";
	private static final String PRICE = "PRICE ";
	private static final BigDecimal ZERO = new BigDecimal("0.00");
	
	private BigDecimal currentMoney = ZERO;
	
	private String message = INSERT_COIN;
	
	private DecimalFormat currencyFormat = new DecimalFormat("0.00");
	
	private boolean respondToUserInteraction = false;
	
	@Override
	public String getMessage()
	{
		if(respondToUserInteraction)
		{
			String currentMessage = message;
			if(currentMoney.compareTo(ZERO) > 0)
			{
				message = currencyFormat.format(currentMoney);
			}
			else
			{
				message = INSERT_COIN;
			}
			return currentMessage;
		}
		return message;
	}

	@Override
	public void addMoney(BigDecimal value)
	{
		currentMoney = currentMoney.add(value);
		message = currencyFormat.format(currentMoney);
	}
	
	public void completeTransaction()
	{
		currentMoney = ZERO;
		respondToUserInteraction = true;
		message = THANK_YOU;
	}
	
	public void reportPrice(BigDecimal price)
	{
		message = PRICE + currencyFormat.format(price);
		respondToUserInteraction = true;
	}

	@Override
	public void cancelTransaction()
	{
		currentMoney = ZERO;
		message = INSERT_COIN;
	}

	@Override
	public void reportSoldOut()
	{
		message = SOLD_OUT;
	}

}
