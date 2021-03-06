package controlador.sesion;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import servicios.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = -2299770028212892712L;
	private LoginService loginService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Usuario user = loginService.login(username, password);
		
		if (user != null) {
			req.getSession().setAttribute("usuario", user);
			resp.sendRedirect("index.jsp");
		} else {
			req.setAttribute("flash", "Error al iniciar sesion. Comprueba el usuario y la contraseña.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(req, resp);
		}
		
	}

}
