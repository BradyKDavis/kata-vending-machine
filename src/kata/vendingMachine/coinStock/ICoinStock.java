package kata.vendingMachine.coinStock;

import java.math.BigDecimal;
import java.util.ArrayList;

import kata.coins.ICoin;

public interface ICoinStock
{
	boolean canMakeChange();
	
	void addCoin(ICoin coin);
	
	ArrayList<ICoin> getChangeForAmount(BigDecimal amount);
}
