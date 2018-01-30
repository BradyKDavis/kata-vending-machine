package kata.vendingMachine.coinStock;

import java.math.BigInteger;

import kata.coins.ICoin;

public interface ICoinStock
{
	boolean canMakeChange();
	
	void addCoin(ICoin coin);
}
