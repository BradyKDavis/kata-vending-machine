package kata.vendingMachine.coinReturn;

import java.util.ArrayList;

import kata.coins.ICoin;

public interface ICoinReturn
{
	ArrayList<ICoin> getCoinsReturned();
	
	void addCoin(ICoin coin);
	
	void addChange(Double value);
}
