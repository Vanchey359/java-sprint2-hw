public class MonthlyReportRecord {

    int month;
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthlyReportRecord(int monthName, String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.month = monthName;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}

