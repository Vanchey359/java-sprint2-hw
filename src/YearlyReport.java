import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {

    ArrayList<YearlyReportRecord> yearReport = new ArrayList<>();

    private void readFileContentsOrNull() {
        String path = "./resources/y.2021.csv";
        try {
            String yearFile = Files.readString(Path.of(path));
            String[] lines = yearFile.split(System.lineSeparator());
            for (int i = 1; i < lines.length; i++) {
                String[] linesContent = lines[i].split(",");
                int month = Integer.parseInt(linesContent[0]);
                int amount = Integer.parseInt(linesContent[1]);
                boolean isExpense = Boolean.parseBoolean(linesContent[2]);
                YearlyReportRecord stringYearlyReport = new YearlyReportRecord(month, amount, isExpense);
                yearReport.add(stringYearlyReport);
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
        }
    }

    int yearFindExpensesPerMonth(int month) {
        int expenses = 0;
        for (YearlyReportRecord reportRecord : yearReport) {
            if (reportRecord.month == month && reportRecord.isExpense) {
                expenses += reportRecord.amount;
            }
        }
        return expenses;
    }

    int yearFindIncomePerMonth(int month) {
        int income = 0;
        for (YearlyReportRecord reportRecord : yearReport) {
            if (reportRecord.month == month && !reportRecord.isExpense) {
                income += reportRecord.amount;
            }
        }
        return income;
    }

    void printStatisticForYear() {

        int allYearExpenses = 0;
        int allYearIncome = 0;

        for (int i = 1; i <= 3; i++) {
            int yearExpensesPerMonth = yearFindExpensesPerMonth(i);
            int yearIncomePerMonth = yearFindIncomePerMonth(i);
            System.out.println("Месяц " + i + " - Доход составил " + (yearIncomePerMonth - yearExpensesPerMonth));

            allYearExpenses += yearExpensesPerMonth;
            allYearIncome += yearIncomePerMonth;
        }

        System.out.println("Средний расход за все месяцы в году составил: " + (allYearExpenses / 3));
        System.out.println("Средний доход за все месяцы в году составил: " + (allYearIncome / 3));
    }

    void processYearReport() {
        readFileContentsOrNull();
        System.out.println("Годовые отчеты к работе готовы!");
    }
}
