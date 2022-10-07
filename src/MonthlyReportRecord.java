public class MonthlyReportRecord {

    String monthName;
    String itemName;
    String isExpense;
    String quantity;
    String sumOfOne;

    public MonthlyReportRecord(String monthName, String itemName, String isExpense, String quantity, String sumOfOne) {
        this.monthName = monthName;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}

