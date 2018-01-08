package de.domisum.discordbot;

import sx.blah.discord.util.DiscordException;

import java.util.function.Supplier;

public final class DiscordBotException extends RuntimeException
{

	// INIT
	private DiscordBotException(Throwable cause)
	{
		super(cause);
	}


	// WRAPPING
	static <T> T executeWrappedReturn(Supplier<T> run)
	{
		try
		{
			return run.get();
		}
		catch(DiscordException e)
		{
			throw new DiscordBotException(e);
		}
	}

	static void executeWrapped(Runnable run)
	{
		executeWrappedReturn(()->
		{
			run.run();
			return null;
		});
	}

}
