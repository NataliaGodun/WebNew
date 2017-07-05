package by.htp.library.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.library.command.Command;


public class Exit implements Command {
	private static final String INDEX_JSP ="index.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		session.invalidate();
		RequestDispatcher dispatcher=request.getRequestDispatcher(INDEX_JSP);
		
		dispatcher.forward(request, response);

	}

}
