package kata.vendingMachine.productStock;

public class KataProductStock extends AbstractProductStock
{
	private static final int INITIAL_STOCK = 20;
	@Override
	protected int initialStock()
	{
		return INITIAL_STOCK;
	}

}
