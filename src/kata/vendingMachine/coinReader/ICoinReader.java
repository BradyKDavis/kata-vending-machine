package kata.vendingMachine.coinReader;

import kata.coins.ICoin;
import kata.coins.enums.CoinType;

public interface ICoinReader
{
	CoinType getCoinType(ICoin coin);
}
