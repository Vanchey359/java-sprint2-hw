public class ReportValidator {

    private void validateExpense(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (int i = 1; i <= 3; i++) {
            int monthExpensesPerMonth = monthlyReport.monthFindExpensesPerMonth(i);
            int yearExpensesPerMonth = yearlyReport.yearFindExpensesPerMonth(i);
            if (monthExpensesPerMonth != yearExpensesPerMonth) {
                System.out.println("Месяц " + i + " - траты в месячном отчете не соответствуют тратам в годовом отчете.");
            }
            }
        System.out.println("Сверка трат успешно заверешна!");
        }


    private void validateIncome(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (int i = 1; i <=3; i++) {
            int monthIncomePerMonth = monthlyReport.monthFindIncomePerMonth(i);
            int yearIncomePerMonth = yearlyReport.yearFindIncomePerMonth(i);
            if (monthIncomePerMonth != yearIncomePerMonth) {
                System.out.println("Месяц " + i + " - доходы в месячном отчете не соответствуют доходам в годовом отчете.");
            }
        }
        System.out.println("Сверка доходов успешно завершена!");
    }

    public void validate(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        validateExpense(monthlyReport, yearlyReport);
        validateIncome(monthlyReport, yearlyReport);
    }
}
