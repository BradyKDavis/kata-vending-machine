package kata.vendingMachine.messageDisplay;

public interface IMessageDisplay
{
	String getMessage();
	
	void addMoney(Float value);
	
	void completeTransaction();
	
	void reportPrice(Double price);
}
