package TomaszC283.main.java;

public class DailyProducts {

	private int ID;
	private String Data;
	private int ProductID;
	private double Carbo;
	private double Whey;
	private double Fat;
	private double Kcal;
	private int MealNo;
	
	public double getKcal() {
		return Kcal = ((Carbo+Whey)*4+(Fat*9));
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
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
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
	public double getFat() {
		return Fat;
	}
	public void setFat(double fat) {
		Fat = fat;
	}
	public int getMealNo() {
		return MealNo;
	}
	public void setMealNo(int mealNo) {
		MealNo = mealNo;
	}
}
