package online.compiler.sample2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.compiler.sample2.util.JavaFile;
import online.compiler.sample2.util.SourceCodeParse;

@WebServlet("/run")
public class RunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sourceCode = request.getParameter("sourcecode");
		String compilestatus = request.getParameter("compilestatus");
		if (compilestatus.trim().length() == 0 || !Boolean.valueOf(compilestatus)) {
			request.setAttribute("message", "Compile Progarm");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {

			JavaFile javaFile = (JavaFile) request.getAttribute("javaFile");
			String message = javaFile.run();
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
