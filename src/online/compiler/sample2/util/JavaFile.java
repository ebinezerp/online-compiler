package online.compiler.sample2.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaFile {

	private String sourceCode;
	private String filePath;
	private String filename;
	private boolean isFileCreated;

	public JavaFile(String sourceCode, String filePath) {
		this.sourceCode = sourceCode;
		this.filePath = filePath;
		this.filename = getFileName();
	}

	public String getFileName() {
		return new SourceCodeParse(sourceCode).getClassName();
	}

	public void createFile() throws IOException {
		File sourceCodeFile = new File(filePath + filename + ".java");
		BufferedWriter writer = new BufferedWriter(new FileWriter(sourceCodeFile));
		writer.write(sourceCode);
		writer.flush();
		writer.close();
		isFileCreated = true;
	}

	public String compile() throws IOException {

		if (!isFileCreated) {
			createFile();
		}
		Runtime runtime = Runtime.getRuntime();
		Process proccess1 = runtime.exec("cmd.exe /c javac " + filename + ".java", null, new File(filePath));
		String line;
		StringBuffer message = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(proccess1.getErrorStream()));
		while ((line = br.readLine()) != null) {
			message.append(line.trim());
			message.append("\n");
		}
		return message.toString();
	}

	public String run() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process proccess = runtime.exec("cmd.exe /c java " + filename, null, new File(filePath));
		BufferedReader br = new BufferedReader(new InputStreamReader(proccess.getInputStream()));
		String line;
		StringBuffer message = new StringBuffer();
		while ((line = br.readLine()) != null) {
			message.append(line);
			message.append("\n");
		}

		br = new BufferedReader(new InputStreamReader(proccess.getErrorStream()));
		while ((line = br.readLine()) != null) {
			message.append("line");
			message.append("\n");
		}
		return message.toString();
	}

}
