package de.domisum.discordbot;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class DomisumDiscordBot implements DomisumDiscordBotFacade
{

	// REFERENCES
	private final IDiscordClient discordClient;


	// INIT
	public DomisumDiscordBot(String apiToken)
	{
		discordClient = DiscordBotException.executeWrappedReturn(()->createClient(apiToken));
	}

	private static IDiscordClient createClient(String token)
	{
		IDiscordClient discordClient = new ClientBuilder().withToken(token).login();
		while(!discordClient.isReady())
			Thread.yield();

		return discordClient;
	}

	@Override public void close()
	{
		DiscordBotException.executeWrapped(discordClient::logout);
	}


	// FACADE
	@Override public void sendMessage(long channelId, String message)
	{
		DiscordBotException.executeWrapped(()->discordClient.getChannelByID(channelId).sendMessage(message));
	}

}
