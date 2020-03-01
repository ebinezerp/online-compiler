package online.compiler.sample2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.compiler.sample2.util.JavaFile;

@WebServlet("/compile")
public class CompileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String filePath;

	@Override
	public void init() throws ServletException {
		super.init();
		filePath = getServletContext().getInitParameter("filePath");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sourceCode = request.getParameter("sourcecode");
		if (sourceCode.contains("System.in")) {
			String inputFileName = "input.txt";
			sourceCode = sourceCode.replaceAll("\"System.in\"", "new File(\"" + inputFileName + "\")");
		}

		JavaFile javaFile = new JavaFile(sourceCode, filePath);
		String message = javaFile.compile();
		if (message.length() <= 0) {
			request.setAttribute("compilestatus", true);
			request.getSession().setAttribute("javaFile", javaFile);
			message = "Successfully Compiled";
		} else {
			request.setAttribute("compilestatus", false);
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
