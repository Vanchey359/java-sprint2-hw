import java.util.Scanner;

public class Main {
    private static final int END_PROGRAM = 0;
    private static final int GET_MONTHLY_REPORTS = 1;
    private static final int GET_YEARLY_REPORT = 2;
    private static final int COMPARE_REPORTS = 3;
    private static final int PRINT_INFO_MONTHLY_REPORT = 4;
    private static final int PRINT_INFO_YEARLY_REPORT = 5;

    public static void main(String[] args) {
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        ReportValidator reportValidator = new ReportValidator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == GET_MONTHLY_REPORTS) {
                monthlyReport.processMonthReport();
            } else if (command == GET_YEARLY_REPORT) {
                yearlyReport.processYearReport();
            } else if (command == COMPARE_REPORTS) {
                if (monthlyReport.monthReport.size() != 0 && yearlyReport.yearReport.size() != 0) {
                    reportValidator.compare(monthlyReport, yearlyReport);
                } else {
                    System.out.println("Перед сравнением требуется считать месячные и годовой отчеты!");
                }
            } else if (command == PRINT_INFO_MONTHLY_REPORT) {
                if (monthlyReport.monthReport.size() != 0) {
                    monthlyReport.printStatisticForMont();
                } else {
                    System.out.println("Что бы вывести статистику - сначала считайте месячные отчеты!");
                }
            } else if (command == PRINT_INFO_YEARLY_REPORT) {
                if (yearlyReport.yearReport.size() != 0) {
                    yearlyReport.printStatisticForYear();
                } else {
                    System.out.println("Что бы вывести статистику - сначала считайте годовой отчет!");
                }
            } else if (command == END_PROGRAM) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выйти из приложения");
    }
}
