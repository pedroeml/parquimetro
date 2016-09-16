package negocio;

public class Assembler_forLogger {
	private ILogger logger;
	
	public Assembler_forLogger() {
		this.logger = new FileLogger();
	}
	
	public ILogger getLogger() {
		return logger;
	}
}
