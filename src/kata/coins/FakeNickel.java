package kata.coins;

import kata.coins.enums.CoinSize;
import kata.coins.enums.CoinWeight;

public class FakeNickel implements ICoin {

	@Override
	public CoinSize getCoinSize() {
		return CoinSize.OTHER;
	}

	@Override
	public CoinWeight getCoinWeight() {
		return CoinWeight.NICKEL;
	}

}
