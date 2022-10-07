import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {

    ArrayList<YearlyReportRecord> yearReport = new ArrayList<>();
    String yearFile = "";

    private String readFileContentsOrNull() {
        String path = "./resources/y.2021.csv";
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    void splitYearFile() {
        String[] lines = yearFile.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            String[] linesContent = lines[i].split(",");
                YearlyReportRecord stringYearlyReport = new YearlyReportRecord(linesContent[0], linesContent[1], linesContent[2]);
                yearReport.add(stringYearlyReport);
        }
    }

    int yearFindExpensesOrIncomePerMonth(String monthNumber, String trueORfalse) {
        int amount = 0;
        for (YearlyReportRecord object : yearReport) {
            if (object.monthName.equals(monthNumber)) {
                if (object.isExpense.equals(trueORfalse)) {
                    amount += Integer.parseInt(object.amount);
                }
            }
        }
        return amount;
    }

    void printStatisticForYear() {

        int allYearExpenses = 0;
        int allYearIncome = 0;

        for (int i = 1; i <= 3; i++) {
            int yearExpensesPerMonth = yearFindExpensesOrIncomePerMonth("0" + i, "true");
            int yearIncomePerMonth = yearFindExpensesOrIncomePerMonth("0" + i, "false");
            System.out.println("Месяц " + i + " - Доход составил " + (yearIncomePerMonth - yearExpensesPerMonth));

            allYearExpenses += yearExpensesPerMonth;
            allYearIncome += yearIncomePerMonth;
        }

        System.out.println("Средний расход за все месяцы в году составил: " + (allYearExpenses / 3));
        System.out.println("Средний доход за все месяцы в году составил: " + (allYearIncome / 3));
    }

    void yearlyReportProcessing() {
        yearFile = readFileContentsOrNull();
        splitYearFile();
        System.out.println("Годовые отчеты к работе готовы!");
    }
}
