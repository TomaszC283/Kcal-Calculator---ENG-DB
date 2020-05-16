package TomaszC283.main.java;

public class DailyProducts {

	private int ID;
	private String Date;
	private String ProductName;
	private double Carbo;
	private double Whey;
	private double Fats;
	@SuppressWarnings("unused")
	private double Kcal;
	private double Weight;
	
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public double getKcal() {
		return Kcal = ((Carbo+Whey)*4+(Fats*9));
	}
	public void setKcal(double kcal) {
		Kcal = kcal;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public double getCarbo() {
		return Carbo;
	}
	public void setCarbo(double carbo) {
		Carbo = carbo;
	}
	public double getWhey() {
		return Whey;
	}
	public void setWhey(double whey) {
		Whey = whey;
	}
	public double getFats() {
		return Fats;
	}
	public void setFats(double fats) {
		Fats = fats;
	}
}
