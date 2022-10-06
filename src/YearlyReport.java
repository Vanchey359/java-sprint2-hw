import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {


    String path = "/Users/averin/dev/java-sprint2-hw/resources/y.2021.csv";
    String yearFile = "";

    ArrayList<StringYearlyReport> yearReport = new ArrayList<>();


    private String readFileContentsOrNull (String path) {
        try {
            return yearFile = Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return yearFile = null;
        }
    }

    void splitYearFile() {
        String [] lines = yearFile.split(System.lineSeparator());
        for(int i = 1; i < lines.length; i++) {
            String [] linesContent = lines[i].split(",");
                StringYearlyReport stringYearlyReport = new StringYearlyReport(linesContent[0], linesContent[1], linesContent[2]);
                yearReport.add(stringYearlyReport);
        }
    }

    int yearFindExpensesOrIncomePerMonth(String monthNumber, String trueORfalse) {
        int amount = 0;
        for(StringYearlyReport object : yearReport) {
            if(object.month.equals(monthNumber)) {
                if(object.isExpense.equals(trueORfalse)) {
                    amount += Integer.parseInt(object.amount);
                }
            }
        }
        return amount;
    }

    void printStatisticForYear() {

        int allYearExpenses = 0;
        int allYearIncome = 0;

        for(int i = 1; i <= 3; i++) {
            int yearExpensesPerMonth = yearFindExpensesOrIncomePerMonth("0" + i, "true");
            int yearIncomePerMonth = yearFindExpensesOrIncomePerMonth("0" + i, "false");
            System.out.println("Месяц " + i + " - Доход составил " + (yearIncomePerMonth - yearExpensesPerMonth));

            allYearExpenses += yearExpensesPerMonth;
            allYearIncome += yearIncomePerMonth;
        }

        System.out.println("Средний расход за все месяцы в году составил: " + (allYearExpenses / 3));
        System.out.println("Средний доход за все месяцы в году составил: " + (allYearIncome / 3));
    }

    void goYear () {
        readFileContentsOrNull(path);
         splitYearFile();
        System.out.println("Годовые отчеты к работе готовы!");
    }
}
