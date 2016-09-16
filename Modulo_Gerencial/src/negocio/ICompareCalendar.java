package negocio;

public interface ICompareCalendar<E> {
	public boolean isSameYear(E obj);
	public boolean isSameMonth(E obj);
	public boolean isSameDayOfMonth(E obj);
	public boolean isSameDayOfWeek(E obj);
}
