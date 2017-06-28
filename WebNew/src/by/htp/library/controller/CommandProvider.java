package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.command.Command;
import by.htp.library.command.impl.Authorization;
import by.htp.library.command.impl.Registration;


public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new Authorization());
		commands.put(CommandName.REGISTRATION, new Registration());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
