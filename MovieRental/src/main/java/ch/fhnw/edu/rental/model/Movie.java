package ch.fhnw.edu.rental.model;

import java.util.Date;
import java.util.Objects;

public class Movie {
	private Long id;
	
	private final String title;
	private final Date releaseDate;
	private boolean rented;
	private PriceCategory priceCategory;

	public Movie(String title, Date releaseDate, PriceCategory priceCategory) throws NullPointerException {
		if ((title == null) || (releaseDate == null) || (priceCategory == null)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		this.title = title;
		this.releaseDate = releaseDate;
		this.priceCategory = priceCategory;
		this.rented = false;
	}
	
	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public String getTitle() {
		return title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Movie)) {
			return false;
		}
		Movie m2 = (Movie) obj;
        if (this.id != null && m2.id != null) {
            return Objects.equals(this.id, m2.id);
        } else {
            return Objects.equals(this.title, m2.title) && Objects.equals(this.releaseDate, m2.releaseDate);
        }
	}

	@Override
	public int hashCode() {
		return 31*title.hashCode() + releaseDate.hashCode();
	}
}
