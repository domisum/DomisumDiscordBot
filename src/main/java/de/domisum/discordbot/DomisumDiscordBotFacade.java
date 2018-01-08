package de.domisum.discordbot;

import java.io.Closeable;

public interface DomisumDiscordBotFacade extends Closeable
{

	// INIT
	static DomisumDiscordBotFacade fromApiToken(String apiToken)
	{
		return new DomisumDiscordBot(apiToken);
	}

	@Override void close();


	// FACADE
	void sendMessage(long channelId, String message);

}
