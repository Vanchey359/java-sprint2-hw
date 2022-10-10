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
                 splitMonthFile(i);
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
                monthFile = null;
            }
        }
        return monthFile;
    }

    void splitMonthFile(int month) {
        String[] splitedMonthFileToLine = monthFile.split(System.lineSeparator());
        for (int i = 1; i < splitedMonthFileToLine.length; i++) {
            String[] linesContent = splitedMonthFileToLine[i].split(",");
            boolean isExpense = Boolean.parseBoolean(linesContent[1]);
            int quantity = Integer.parseInt(linesContent[2]);
            int sumOfOne = Integer.parseInt(linesContent[3]);
                MonthlyReportRecord stringMonthlyReport = new MonthlyReportRecord(month, linesContent[0], isExpense, quantity, sumOfOne);
                monthReport.add(stringMonthlyReport);
            }
        }

       int  monthFindIncomePerMonth(int month) {
           int income = 0;
        for (MonthlyReportRecord reportRecord : monthReport) {
            if (reportRecord.month == month && !reportRecord.isExpense) {
                    income += reportRecord.sumOfOne * reportRecord.quantity;
            }
        }
        return income;
        }

    int  monthFindExpensesPerMonth(int month) {
        int expenses = 0;
        for (MonthlyReportRecord reportRecord : monthReport) {
            if (reportRecord.month == month && reportRecord.isExpense) {
                expenses += reportRecord.sumOfOne * reportRecord.quantity;
            }
        }
        return expenses;
    }

        int monthFindIncomeMaxAmount(int month) {
            int maxAmount = 0;
            int amount;

            for (MonthlyReportRecord reportRecord : monthReport) {
                if (reportRecord.month == month && !reportRecord.isExpense) {
                        amount = reportRecord.sumOfOne * reportRecord.quantity;
                    if (amount > maxAmount) {
                        maxAmount = amount;
                    }
                }
            }
            return maxAmount;
        }

        void monthFindMostIncomeItem(int month) {
        int maxAmount = monthFindIncomeMaxAmount(month);

            for (MonthlyReportRecord reportRecord : monthReport) {
                if (reportRecord.month == month && !reportRecord.isExpense) {
                       int  amount = reportRecord.sumOfOne * reportRecord.quantity;
                        if (amount == maxAmount) {
                            System.out.println("Самый прибыльный товар в этом месяце - " + reportRecord.itemName + ". Сумма прибыли составляет - " + maxAmount);
                        }
                }
            }
        }

        int monthFindBiggestExpenseAmount(int month) {
            int amount;
            int biggestAmount = 0;

            for (MonthlyReportRecord reportRecord : monthReport) {
                if (reportRecord.month == month && reportRecord.isExpense) {
                        amount = reportRecord.sumOfOne * reportRecord.quantity;
                        if (amount > biggestAmount) {
                            biggestAmount = amount;
                        }
                }
            }
            return biggestAmount;
        }

        void monthFindBiggestExpenseItem(int month) {
        int biggestAmount = monthFindBiggestExpenseAmount(month);

        for (MonthlyReportRecord reportRecord : monthReport) {
            if (reportRecord.month == month && reportRecord.isExpense) {
                    int amount = reportRecord.sumOfOne * reportRecord.quantity;
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
            monthFindMostIncomeItem(i);
            monthFindBiggestExpenseItem(i);
        }
        }

    void processMonthReport() {
        readFileContentsOrNull();
        System.out.println("Месячные отчеты к работе готовы!");
    }

}
