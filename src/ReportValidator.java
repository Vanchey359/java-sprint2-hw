public class ReportValidator {

    public void compareExpense(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (int i = 1; i <= 3; i++) {
            int monthExpensesPerMonth = monthlyReport.monthFindExpensesOrIncomePerMonth("" + i, "TRUE");
            int yearExpensesPerMonth = yearlyReport.yearFindExpensesOrIncomePerMonth("0" + i, "true");
            if (monthExpensesPerMonth != yearExpensesPerMonth) {
                System.out.println("Месяц " + i + " - траты в месячном отчете не соответствуют тратам в годовом отчете.");
            } else {
                System.out.println("Сверка трат за месяц " + i + " успешно завершена!");
            }
        }
    }

    public void compareIncome(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        for (int i = 1; i <=3; i++) {
            int monthIncomePerMonth = monthlyReport.monthFindExpensesOrIncomePerMonth("" + i, "FALSE");
            int yearIncomePerMonth = yearlyReport.yearFindExpensesOrIncomePerMonth("0" + i, "false");
            if (monthIncomePerMonth != yearIncomePerMonth) {
                System.out.println("Месяц " + i + " - доходы в месячном отчете не соответствуют доходам в годовом отчете.");
            } else {
                System.out.println("Сверка доходов за месяц " + i + " успешно завершена!");
            }
        }
    }

    public void compare(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        compareExpense(monthlyReport, yearlyReport);
        System.out.println("");
        compareIncome(monthlyReport, yearlyReport);
    }
}
