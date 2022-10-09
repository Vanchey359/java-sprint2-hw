import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {

    ArrayList<YearlyReportRecord> yearReport = new ArrayList<>();

    private void readFileContentsOrNull() {
        String path = "./resources/y.2021.csv";
        String yearFile;
        try {
            yearFile = Files.readString(Path.of(path));
            String[] lines = yearFile.split(System.lineSeparator());
            for (int i = 1; i < lines.length; i++) {
                String[] linesContent = lines[i].split(",");
                YearlyReportRecord stringYearlyReport = new YearlyReportRecord(linesContent[0], linesContent[1], linesContent[2]);
                yearReport.add(stringYearlyReport);
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
        }
    }

    int yearFindExpensesPerMonth(String monthNumber) {
        int expenses = 0;
        for (YearlyReportRecord reportRecord : yearReport) {
            if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals("true")) {
                expenses += Integer.parseInt(reportRecord.amount);
            }
        }
        return expenses;
    }

    int yearFindIncomePerMonth(String monthNumber) {
        int income = 0;
        for (YearlyReportRecord reportRecord : yearReport) {
            if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals("false")) {
                income += Integer.parseInt(reportRecord.amount);
            }
        }
        return income;
    }

    void printStatisticForYear() {

        int allYearExpenses = 0;
        int allYearIncome = 0;

        for (int i = 1; i <= 3; i++) {
            int yearExpensesPerMonth = yearFindExpensesPerMonth("0" + i);
            int yearIncomePerMonth = yearFindIncomePerMonth("0" + i);
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
