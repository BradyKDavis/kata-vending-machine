package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public interface ICoin 
{
	CoinSize getCoinSize();
	
	CoinWeight getCoinWeight();
}
