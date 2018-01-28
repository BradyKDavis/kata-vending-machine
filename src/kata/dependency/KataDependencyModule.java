package kata.dependency;

import kata.vendingMachine.messageDisplay.IMessageDisplay;
import kata.vendingMachine.messageDisplay.MessageDisplay;

import com.google.inject.AbstractModule;

public class KataDependencyModule extends AbstractModule
{

	@Override
	protected void configure()
	{
		bind(IMessageDisplay.class).to(MessageDisplay.class);
	}

}
