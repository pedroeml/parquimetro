package persistencia;

import java.util.List;
import java.util.Map;

public interface IStartLoadSettings {
	/**
	 * Retorna uma lista com cada posição contendo um array de Strings.
	 * Cada elemento do array contém os respectivos dados dos cartões do arquivo texto.
	 */
	public List<String[]> getDados_Cartoes();
	/**
	 * Retorna um Map de String para String. A key é uma das palavras da primeira linha do arquivo texto.
	 * O value é o respectivo dado da segunda linha do arquivo texto.
	 */
	public Map<String, String> getDados_Parquimetro();
}
