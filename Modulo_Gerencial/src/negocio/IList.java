package negocio;

import java.util.Iterator;

public interface IList<E> {
	public Iterator<E> iterate_list();
	public boolean addObjToList(E obj);
}
