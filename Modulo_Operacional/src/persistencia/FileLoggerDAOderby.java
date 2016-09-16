package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import negocio.ILogger;

public class FileLoggerDAOderby implements IFileLoggerDAO {
	private ILogger logger;
	
	public FileLoggerDAOderby(ILogger logger) {
		this.logger = logger;
	}
	
	@Override
	public void armazenarLog() {
		File file_log = new File("log.csv");
		Path file = file_log.toPath();
		Charset charset = Charset.forName("UTF-8");
		String s = this.logger.getLog();
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
		    writer.write(s, 0, s.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}

}
