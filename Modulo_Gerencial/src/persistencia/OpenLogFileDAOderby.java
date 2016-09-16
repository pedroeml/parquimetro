package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OpenLogFileDAOderby implements IOpenLogFileDAO {
	private File file;
	private List<String[]> lista;
	
	public OpenLogFileDAOderby(File file) {
		this.file = file;
		this.lista = new LinkedList<>();
		this.load_data();
	}
	
	private void load_data() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
			String line;
			br.readLine(); // skip header line
			
			while ((line = br.readLine()) != null)
				this.lista.add(line.split(";"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String[]> getLogFileData() {
		return lista;
	}

}
