package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.command.Command;
import by.htp.library.command.impl.AddNewBook;
import by.htp.library.command.impl.Authorization;
import by.htp.library.command.impl.DeleteBook;
import by.htp.library.command.impl.EditProfileName;
import by.htp.library.command.impl.EditProfileNameForm;
import by.htp.library.command.impl.Exit;
import by.htp.library.command.impl.Registration;
import by.htp.library.command.impl.ShowAddNewBookForm;
import by.htp.library.command.impl.ViewAllBooks;
import by.htp.library.command.impl.ShowDeleteBookForm;
import by.htp.library.command.impl.ShowEditProfileForm;
import by.htp.library.command.impl.ViewBook;


public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new Authorization());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.SHOWADDNEWBOOKFORM, new ShowAddNewBookForm());
		commands.put(CommandName.ADDNEWBOOK, new AddNewBook());
		commands.put(CommandName.VIEWBOOK, new ViewBook());
		commands.put(CommandName.SHOWDELETEBOOKFORM, new ShowDeleteBookForm());
		commands.put(CommandName.DELETEBOOK, new DeleteBook());
		commands.put(CommandName.EXIT, new Exit());
		commands.put(CommandName.VIEWALLBOOKS, new ViewAllBooks());
		commands.put(CommandName.SHOWEDITPROFILEFORM, new ShowEditProfileForm());
		commands.put(CommandName.EDITPROFILENAMEFORM, new EditProfileNameForm());
		commands.put(CommandName.EDITPROFILENAME, new EditProfileName());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
