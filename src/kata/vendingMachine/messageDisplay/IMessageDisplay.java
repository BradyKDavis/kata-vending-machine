package kata.vendingMachine.messageDisplay;

import java.math.BigDecimal;

public interface IMessageDisplay
{
	String getMessage();
	
	void addMoney(BigDecimal money);
	
	void completeTransaction();
	
	void reportPrice(BigDecimal price);
}
