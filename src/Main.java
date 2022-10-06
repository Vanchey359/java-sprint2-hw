import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Comparator comparator = new Comparator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.goMonth();
            } else if (command == 2) {
                yearlyReport.goYear();
            } else if (command == 3) {
                if(monthlyReport.monthReport.size() != 0 && yearlyReport.yearReport.size() != 0) {
                    comparator.compare(monthlyReport, yearlyReport);
                } else {
                    System.out.println("Перед сравнением требуется считать месячные и годовой отчеты!");
                }
            } else if (command == 4) {
                monthlyReport.printStatisticForMont();
            } else if (command == 5) {
                yearlyReport.printStatisticForYear();
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выйти из приложения");
    }
}
