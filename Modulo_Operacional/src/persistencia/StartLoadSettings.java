package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StartLoadSettings implements IStartLoadSettings {
	private File file_dados_cartoes, file_dados_parquimetro;
	private List<String[]> list_dados_cartoes;
	private Map<String, String> map_dados_parquimetro;
	
	public StartLoadSettings() {
		try {
			this.file_dados_cartoes = new File("dados_cartoes.csv");
			this.file_dados_parquimetro = new File("dados_parquimetro.csv");
			this.list_dados_cartoes = new LinkedList<>();
			this.map_dados_parquimetro = new HashMap<>();
			this.load_dados_cartoes();
			this.load_dados_parquimetro();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String[]> getDados_Cartoes() {
		return this.list_dados_cartoes;
	}

	@Override
	public Map<String, String> getDados_Parquimetro() {
		return this.map_dados_parquimetro;
	}
	
	private void load_dados_cartoes() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.file_dados_cartoes))) {
			String line;
			br.readLine(); // skip header line
			
			while ((line = br.readLine()) != null)
				this.list_dados_cartoes.add(line.split(";"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	private void load_dados_parquimetro() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.file_dados_parquimetro))) {
			String line;
			String[] header = br.readLine().split(";");
			
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] splited_line = line.split(";");
				
				for (int i = 0; i < header.length; i++)
					this.map_dados_parquimetro.put(header[i], splited_line[i]);
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
}
