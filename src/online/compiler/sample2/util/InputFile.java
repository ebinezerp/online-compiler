package online.compiler.sample2.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InputFile {

	private File file;
	private String path;

	public InputFile(String path) throws IOException {
		this.path = path;
		this.file = new File(this.path + "/input.txt");
		if (!this.file.exists()) {
			this.file.createNewFile();
		}
	}

	public File getInputFile() {
		return file;
	}

	public void writeInput(String input) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		bufferedWriter.write(input);
		bufferedWriter.flush();
		bufferedWriter.close();
	}

}
