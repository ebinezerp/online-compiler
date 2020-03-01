package online.compiler.sample2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.compiler.sample2.util.InputFile;
import online.compiler.sample2.util.JavaFile;

@WebServlet("/run")
public class RunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String compilestatus = request.getParameter("compilestatus");
		if (compilestatus.trim().length() == 0 || !Boolean.valueOf(compilestatus)) {
			request.setAttribute("message", "Compile Progarm");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			JavaFile javaFile = (JavaFile) request.getSession().getAttribute("javaFile");
			InputFile inputFile = new InputFile(javaFile.getPath());
			inputFile.writeInput(request.getParameter("input"));
			String message = javaFile.run();
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
