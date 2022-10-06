import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {

    String path = "";
    String monthFile = "";

    ArrayList<StringMonthlyReport> monthReport = new ArrayList<>();

    private String readFileContentsOrNull() {
         for (int i = 1; i <= 3; i++) {
            path = "/Users/averin/dev/java-sprint2-hw/resources/m.20210" + i + ".csv";
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

    void splitMonthFile (String month) {
        String [] splitedMonthFileToLine = monthFile.split(System.lineSeparator());
        for(int i = 1; i < splitedMonthFileToLine.length; i++){
            String [] linesContent = splitedMonthFileToLine[i].split(",");
                StringMonthlyReport stringMonthlyReport = new StringMonthlyReport(month, linesContent[0], linesContent[1], linesContent[2], linesContent[3]);
                monthReport.add(stringMonthlyReport);
            }
        }

       int  monthFindExpensesOrIncomePerMonth (String monthNumber, String TRUEorFALSE) {
           int amount = 0;
        for(StringMonthlyReport object : monthReport) {
            if(object.month.equals(monthNumber)) {
                if(object.isExpense.equals(TRUEorFALSE)) {
                    amount += Integer.parseInt(object.sumOfOne) * Integer.parseInt(object.quantity);
                }
            }
        }
        return amount;
        }

        void monthFindMostIncomeItem (String monthNumber, String TRUEorFALSE) {
            int maxAmount = 0;
            int amount = 0;

            // Находим maxAmount среди всех строк конкретного месяца
            for (StringMonthlyReport object : monthReport) {
                if (object.month.equals(monthNumber)) {
                    if (object.isExpense.equals(TRUEorFALSE)) {
                        amount = Integer.parseInt(object.sumOfOne) * Integer.parseInt(object.quantity);
                    }
                    if (amount > maxAmount) {
                        maxAmount = amount;
                    }
                }
            }

            // проходим по всем строкам месяца и находим ту которая ==  maxAmount
            for (StringMonthlyReport object : monthReport) {
                if (object.month.equals(monthNumber)) {
                    if (object.isExpense.equals(TRUEorFALSE)) {
                        amount = Integer.parseInt(object.sumOfOne) * Integer.parseInt(object.quantity);
                        if (amount == maxAmount) {
                            System.out.println("Самый прибыльный товар в этом месяце - " + object.itemName + ". Сумма прибыли составляет - " + maxAmount);
                        }
                    }
                }
            }
        }

        void monthFindBiggestExpense (String monthNumber, String TRUEorFALSE) {
        int amount;
        int biggestAmount = 0;

        // Находим biggestAmount
        for(StringMonthlyReport object : monthReport) {
            if(object.month.equals(monthNumber)) {
                if(object.isExpense.equals(TRUEorFALSE)) {
                    amount = Integer.parseInt(object.sumOfOne) * Integer.parseInt(object.quantity);
                    if(amount > biggestAmount)
                        biggestAmount = amount;
                }
            }
        }

        // проходим по всем строкам месяца и находим ту которая == biggestAmount
        for(StringMonthlyReport object : monthReport) {
            if(object.month.equals(monthNumber)) {
                if(object.isExpense.equals(TRUEorFALSE)) {
                    amount = Integer.parseInt(object.sumOfOne) * Integer.parseInt(object.quantity);
                    if(amount == biggestAmount) {
                        System.out.println("Самая большая трата в этом месяце - " + object.itemName + ". Сумма траты составляет - " + biggestAmount);
                    }
                }

            }
        }
        }

        void printStatisticForMont () {
        for(int i = 1; i <=3; i++) {
            if(i == 1) {
                System.out.println("Статистика за Январь: ");
            } else if(i == 2) {
                System.out.println("Статистика за Февраль: ");
            } else {
                System.out.println("Статистика за Март: ");
            }
            monthFindMostIncomeItem("" + i, "FALSE");
            monthFindBiggestExpense("" +i, "TRUE");
        }
        }

    void goMonth () {
        readFileContentsOrNull();
        System.out.println("Месячные отчеты к работе готовы!");
    }

}
