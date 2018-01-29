package kata.vendingMachine.coinReturn;

import java.math.BigDecimal;
import java.util.ArrayList;

import kata.coins.ICoin;

public interface ICoinReturn
{
	ArrayList<ICoin> getCoinsReturned();
	
	void addCoin(ICoin coin);
	
	void addChange(BigDecimal change);
}
