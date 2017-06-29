package by.htp.library.domain;

public class Book {
	private int id;
	private String nazvanie;
	private String avtor;

	public Book() {

	}

	public Book(int id,  String avtor, String nazvanie) {
		super();
		this.id = id;
		this.avtor =avtor;
		this.nazvanie =nazvanie;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avtor == null) ? 0 : avtor.hashCode());
		result = prime * result + id;
		result = prime * result + ((nazvanie == null) ? 0 : nazvanie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (avtor == null) {
			if (other.avtor != null)
				return false;
		} else if (!avtor.equals(other.avtor))
			return false;
		if (id != other.id)
			return false;
		if (nazvanie == null) {
			if (other.nazvanie != null)
				return false;
		} else if (!nazvanie.equals(other.nazvanie))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazvanie() {
		return nazvanie;
	}

	public void setNazvanie(String nazvanie) {
		this.nazvanie = nazvanie;
	}

	public String getAvtor() {
		return avtor;
	}

	public void setAvtor(String avtor) {
		this.avtor = avtor;
	}

	public String toString() {
		return this.avtor + " " + this.nazvanie;
	}

}
