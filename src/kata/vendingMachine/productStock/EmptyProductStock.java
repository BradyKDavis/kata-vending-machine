package kata.vendingMachine.productStock;

public class EmptyProductStock extends AbstractProductStock
{
	private static final int INITIAL_STOCK = 0;
	@Override
	protected int initialStock()
	{
		return INITIAL_STOCK;
	}

}
