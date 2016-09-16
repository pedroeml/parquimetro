package negocio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ColecaoParquimetro {
	private Map<String, Parquimetro> map_parquimetro; // key: id do parquímetro, value: objeto parquímetro
	
	public ColecaoParquimetro() {
		this.map_parquimetro = new HashMap<>();
	}
	
	protected Map<String, Parquimetro> getMap_parquimetro() {
		return map_parquimetro;
	}
	
	public Parquimetro getParquimetro(String id_parquimetro) {
		if (id_parquimetro == null)
			throw new IllegalArgumentException("A String em argumento é null!");
		return this.map_parquimetro.get(id_parquimetro);
	}
	
	public boolean addParquimetro(String key, Parquimetro parquimetro) {
		if (this.map_parquimetro.containsKey(key))
			return false;
		this.map_parquimetro.put(key, parquimetro);
		return true;
	}
	
	public Collection<Parquimetro> values() {
		return this.map_parquimetro.values();
	}
}
