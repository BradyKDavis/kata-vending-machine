package kata.products;

import kata.products.enums.ProductType;

public class CandyProduct implements IProduct
{
	private final Double price = new Double(.65);
	@Override
	public ProductType getProductType()
	{
		return ProductType.CANDY;
	}

	@Override
	public Double getPrice()
	{
		return price;
	}

}
