package entities;

public class Order {
	private int id;
	private int idPlace;
	private int idClient;
	private int idMc;
	private int idEventType;
	private String place;
	private String fio;
	private String mobile;
	private String date;
	private String mc;
	private int countGuests;
	private int budget;
	private String eventType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public int getCountGuests() {
		return countGuests;
	}

	public void setCountGuests(int countGuests) {
		this.countGuests = countGuests;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getIdPlace() {
		return idPlace;
	}

	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdMc() {
		return idMc;
	}

	public void setIdMc(int idMc) {
		this.idMc = idMc;
	}

	public int getIdEventType() {
		return idEventType;
	}

	public void setIdEventType(int idEventType) {
		this.idEventType = idEventType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + budget;
		result = prime * result + countGuests;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((fio == null) ? 0 : fio.hashCode());
		result = prime * result + id;
		result = prime * result + idClient;
		result = prime * result + idEventType;
		result = prime * result + idMc;
		result = prime * result + idPlace;
		result = prime * result + ((mc == null) ? 0 : mc.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
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
		Order other = (Order) obj;
		if (budget != other.budget)
			return false;
		if (countGuests != other.countGuests)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (fio == null) {
			if (other.fio != null)
				return false;
		} else if (!fio.equals(other.fio))
			return false;
		if (id != other.id)
			return false;
		if (idClient != other.idClient)
			return false;
		if (idEventType != other.idEventType)
			return false;
		if (idMc != other.idMc)
			return false;
		if (idPlace != other.idPlace)
			return false;
		if (mc == null) {
			if (other.mc != null)
				return false;
		} else if (!mc.equals(other.mc))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", idPlace=" + idPlace + ", idClient=" + idClient + ", idMc=" + idMc
				+ ", idEventType=" + idEventType + ", place=" + place + ", fio=" + fio + ", mobile=" + mobile
				+ ", date=" + date + ", mc=" + mc + ", countGuests=" + countGuests + ", budget=" + budget
				+ ", eventType=" + eventType + "]";
	}
}
