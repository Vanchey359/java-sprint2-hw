import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {

    ArrayList<MonthlyReportRecord> monthReport = new ArrayList<>();
    String monthFile = "";

    private String readFileContentsOrNull() {
         for (int i = 1; i <= 3; i++) {
           String path = "./resources/m.20210" + i + ".csv";
            try {
                 monthFile = Files.readString(Path.of(path));

                 String j = Integer.toString(i);
                 splitMonthFile(j);

            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
                monthFile = null;
            }
        }
        return monthFile;
    }

    void splitMonthFile(String month) {
        String[] splitedMonthFileToLine = monthFile.split(System.lineSeparator());
        for (int i = 1; i < splitedMonthFileToLine.length; i++) {
            String[] linesContent = splitedMonthFileToLine[i].split(",");
                MonthlyReportRecord stringMonthlyReport = new MonthlyReportRecord(month, linesContent[0], linesContent[1], linesContent[2], linesContent[3]);
                monthReport.add(stringMonthlyReport);
            }
        }

       int  monthFindIncomePerMonth(String monthNumber) {
           int income = 0;
        for (MonthlyReportRecord reportRecord : monthReport) {
            if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals("FALSE")) {
                    income += Integer.parseInt(reportRecord.sumOfOne) * Integer.parseInt(reportRecord.quantity);
            }
        }
        return income;
        }

    int  monthFindExpensesPerMonth(String monthNumber) {
        int expenses = 0;
        for (MonthlyReportRecord reportRecord : monthReport) {
            if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals("TRUE")) {
                expenses += Integer.parseInt(reportRecord.sumOfOne) * Integer.parseInt(reportRecord.quantity);
            }
        }
        return expenses;
    }

        int monthFindIncomeMaxAmount(String monthNumber, String TRUEorFALSE) {
            int maxAmount = 0;
            int amount = 0;

            for (MonthlyReportRecord reportRecord : monthReport) {
                if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals(TRUEorFALSE)) {
                        amount = Integer.parseInt(reportRecord.sumOfOne) * Integer.parseInt(reportRecord.quantity);
                    if (amount > maxAmount) {
                        maxAmount = amount;
                    }
                }
            }
            return maxAmount;
        }

        void monthFindMostIncomeItem(String monthNumber, String TRUEorFALSE) {
        int maxAmount = monthFindIncomeMaxAmount(monthNumber, TRUEorFALSE);

            for (MonthlyReportRecord reportRecord : monthReport) {
                if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals(TRUEorFALSE)) {
                       int  amount = Integer.parseInt(reportRecord.sumOfOne) * Integer.parseInt(reportRecord.quantity);
                        if (amount == maxAmount) {
                            System.out.println("Самый прибыльный товар в этом месяце - " + reportRecord.itemName + ". Сумма прибыли составляет - " + maxAmount);
                        }
                }
            }
        }

        int monthFindBiggestExpenseAmount(String monthNumber, String TRUEorFALSE) {
            int amount;
            int biggestAmount = 0;

            for (MonthlyReportRecord reportRecord : monthReport) {
                if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals(TRUEorFALSE)) {
                        amount = Integer.parseInt(reportRecord.sumOfOne) * Integer.parseInt(reportRecord.quantity);
                        if (amount > biggestAmount) {
                            biggestAmount = amount;
                        }
                }
            }
            return biggestAmount;
        }

        void monthFindBiggestExpenseItem(String monthNumber, String TRUEorFALSE) {
        int biggestAmount = monthFindBiggestExpenseAmount(monthNumber, TRUEorFALSE);

        for (MonthlyReportRecord reportRecord : monthReport) {
            if (reportRecord.monthName.equals(monthNumber) && reportRecord.isExpense.equals(TRUEorFALSE)) {
                    int amount = Integer.parseInt(reportRecord.sumOfOne) * Integer.parseInt(reportRecord.quantity);
                    if (amount == biggestAmount) {
                        System.out.println("Самая большая трата в этом месяце - " + reportRecord.itemName + ". Сумма траты составляет - " + biggestAmount);
                    }
            }
        }
        }

        void printStatisticForMont() {
        for (int i = 1; i <=3; i++) {
            if (i == 1) {
                System.out.println("Статистика за Январь: ");
            } else if (i == 2) {
                System.out.println("Статистика за Февраль: ");
            } else {
                System.out.println("Статистика за Март: ");
            }
            monthFindMostIncomeItem("" + i, "FALSE");
            monthFindBiggestExpenseItem("" +i, "TRUE");
        }
        }

    void processMonthReport() {
        readFileContentsOrNull();
        System.out.println("Месячные отчеты к работе готовы!");
    }

}
