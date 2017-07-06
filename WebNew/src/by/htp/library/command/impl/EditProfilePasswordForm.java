package by.htp.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;

public class EditProfilePasswordForm implements Command {

	private static final String EDIT_PASSWORD_JSP ="WEB-INF/jsp/editProfilePasswordForm.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(EDIT_PASSWORD_JSP);
		
		dispatcher.forward(request, response);
	}

}
