package kata.vendingMachine.productManager;

import java.math.BigDecimal;
import java.util.HashMap;

import kata.products.CandyProduct;
import kata.products.ChipProduct;
import kata.products.ColaProduct;
import kata.products.IProduct;
import kata.products.enums.ProductType;

public class KataProductManager implements IProductManager
{
	private HashMap<ProductType, IProduct> products;
	
	public KataProductManager()
	{
		buildProductsList();
	}
	
	public BigDecimal getItemPrice(ProductType productType)
	{
		BigDecimal price = new BigDecimal("0.00");
		IProduct product = products.get(productType);
		if(product != null)
		{
			price = product.getPrice();
		}
		return price;
	}
	
	private void buildProductsList()
	{
		products = new HashMap<ProductType, IProduct>();
		products.put(ProductType.CANDY, new CandyProduct());
		products.put(ProductType.CHIPS, new ChipProduct());
		products.put(ProductType.COLA, new ColaProduct());
	}
}
