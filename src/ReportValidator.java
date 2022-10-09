public class ReportValidator {

    public void compareExpense(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (int i = 1; i <= 3; i++) {
            int monthExpensesPerMonth = monthlyReport.monthFindExpensesPerMonth("" + i);
            int yearExpensesPerMonth = yearlyReport.yearFindExpensesPerMonth("0" + i);
            if (monthExpensesPerMonth != yearExpensesPerMonth) {
                System.out.println("Месяц " + i + " - траты в месячном отчете не соответствуют тратам в годовом отчете.");
            }
            }
        System.out.println("Сверка трат успешно заверешна!");
        }


    public void compareIncome(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (int i = 1; i <=3; i++) {
            int monthIncomePerMonth = monthlyReport.monthFindIncomePerMonth("" + i);
            int yearIncomePerMonth = yearlyReport.yearFindIncomePerMonth("0" + i);
            if (monthIncomePerMonth != yearIncomePerMonth) {
                System.out.println("Месяц " + i + " - доходы в месячном отчете не соответствуют доходам в годовом отчете.");
            }
        }
        System.out.println("Сверка доходов успешно завершена!");
    }

    public void compare(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        compareExpense(monthlyReport, yearlyReport);
        System.out.println("");
        compareIncome(monthlyReport, yearlyReport);
    }
}
