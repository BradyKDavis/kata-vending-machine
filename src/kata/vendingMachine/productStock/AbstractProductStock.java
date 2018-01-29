package kata.vendingMachine.productStock;

import java.util.HashMap;
import java.util.Stack;

import com.google.inject.Inject;

import kata.products.CandyProduct;
import kata.products.ChipProduct;
import kata.products.ColaProduct;
import kata.products.IProduct;
import kata.products.enums.ProductType;

public abstract class AbstractProductStock implements IProductStock
{
	protected abstract int initialStock();
	
	private HashMap<ProductType, Stack<IProduct>> stock;
	
	@Inject
	public AbstractProductStock()
	{
		createInitialStock(initialStock());
	}
	
	@Override
	public boolean hasProductInStock(ProductType productType)
	{
		return !(stock.get(productType).isEmpty());
	}

	@Override
	public IProduct getProduct(ProductType productType)
	{
		return stock.get(productType).pop();
	}
	
	private void createInitialStock(int initialStock)
	{
		stock = new HashMap<ProductType, Stack<IProduct>>();
		stock.put(ProductType.CANDY, new Stack<IProduct>());
		stock.put(ProductType.COLA, new Stack<IProduct>());
		stock.put(ProductType.CHIPS, new Stack<IProduct>());
		for(int i = 0; i < initialStock; i++)
		{
			stock.get(ProductType.CANDY).add(new CandyProduct());
			stock.get(ProductType.COLA).add(new ColaProduct());
			stock.get(ProductType.CHIPS).add(new ChipProduct());
		}
	}

}
