package kata.coins;

public abstract class AbstractCoin implements ICoin
{
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
		{
			return true;
		}
		
		if(!(o instanceof AbstractCoin))
		{
			return false;
		}
		
		AbstractCoin c = (AbstractCoin) o;
		
		if(c.getCoinSize() == this.getCoinSize() && c.getCoinWeight() == c.getCoinWeight())
		{
			return true;
		}
		
		return false;
	}
}
