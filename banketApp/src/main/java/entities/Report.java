package entities;

public class Report {
	private String titleStatistic;
	private int profit;

	public String getTitleStatistic() {
		return titleStatistic;
	}

	public void setTitleStatistic(String titleStatistic) {
		this.titleStatistic = titleStatistic;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + profit;
		result = prime * result + ((titleStatistic == null) ? 0 : titleStatistic.hashCode());
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
		Report other = (Report) obj;
		if (profit != other.profit)
			return false;
		if (titleStatistic == null) {
			if (other.titleStatistic != null)
				return false;
		} else if (!titleStatistic.equals(other.titleStatistic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Report [titleStatistic=" + titleStatistic + ", profit=" + profit + "]";
	}
}
