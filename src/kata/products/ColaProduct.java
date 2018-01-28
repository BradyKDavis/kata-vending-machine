package kata.products;

import kata.products.enums.ProductType;

public class ColaProduct implements IProduct
{
	private final Double price = new Double(1);
	@Override
	public ProductType getProductType()
	{
		return ProductType.COLA;
	}

	@Override
	public Double getPrice()
	{
		return price;
	}

}
