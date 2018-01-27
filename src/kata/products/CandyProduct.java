package kata.products;

import kata.products.enums.ProductType;

public class CandyProduct implements IProduct
{

	@Override
	public ProductType getProductType()
	{
		return ProductType.CANDY;
	}

}
