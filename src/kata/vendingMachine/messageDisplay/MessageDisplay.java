package kata.vendingMachine.messageDisplay;

import java.text.DecimalFormat;

public class MessageDisplay implements IMessageDisplay
{
	private static final String INSERT_COINS = "INSERT COIN";
	private static final String THANK_YOU = "THANK YOU";
	private static final Float ZERO = new Float(0);
	
	private float currentMoney = ZERO;
	
	private String message = INSERT_COINS;
	
	private DecimalFormat currencyFormat = new DecimalFormat("0.00");
	
	@Override
	public String getMessage()
	{
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
		message = THANK_YOU;
	}

}
