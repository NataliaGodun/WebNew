package by.htp.library.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.connection.pool.ConnectionPool;
import by.htp.connection.pool.ConnectionPoolException;
import by.htp.connection.pool.ConnectionPoolFactory;
import by.htp.library.command.Command;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final CommandProvider PROVIDER=new CommandProvider();  
    private static final String COMMAND="command";  
   
    public Controller() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp =ObjectCPFactory.getConnectionPool();
		
			try {
				cp.initPoolData();
			} catch (ConnectionPoolException e) {
				throw new CanNotCreateSource(e);
			}
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName=request.getParameter(COMMAND);
		
		Command command=PROVIDER.getCommand(commandName);
		command.execute(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName=request.getParameter(COMMAND);
		
		Command command=PROVIDER.getCommand(commandName);
		command.execute(request, response);
		
	}
	public void destroy(){
		super.destroy();
		ConnectionPoolFactory ObjectCPFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool cp =ObjectCPFactory.getConnectionPool();
		cp.dispose();
	}

}
