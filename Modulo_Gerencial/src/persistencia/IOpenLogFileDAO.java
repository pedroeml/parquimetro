package persistencia;

import java.util.List;

public interface IOpenLogFileDAO {
	
	/**
	 * Retorna uma lista de array de String. Cada posição da lista contém uma linha do log.
	 * Cada posição do array de String contém uma String obtida a partir do split do separador da formatação do log.
	 */
	public List<String[]> getLogFileData();
}
