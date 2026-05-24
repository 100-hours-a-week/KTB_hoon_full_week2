package view;

import content.Content;
import enums.menu.ContentMenuOption;
import enums.menu.MenuOption;
import java.util.List;

public class OutputView {

    public static void printMainScreen() {
        printHeader();
        printMenu();
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void printContentList(List<Content> contents) {
        System.out.println();
        System.out.println("[ All Contents ]");
        printContentTable(contents);
    }

    public static void printContent(Content content) {
        printContentTableHeader();
        printContentRow(1, content);
        printContentTableFooter();
        System.out.println();
    }

    public static void printGoodbye() {
        System.out.println("안녕히가세요!");
    }

    public static void printInvalidMenuNumber(){
        System.out.println("올바르지 않는 번호입니다.");
    }

    public static void printContentPlayStatus(Content content){
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│            ▶  N O W   P L A Y I N G  ▶           │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf ("│  🎬 Title       : %-29s │%n", truncate(content.getName(), 29));
        System.out.printf ("│  📝 Description : %-29s │%n", truncate(content.getDescription(), 29));
        System.out.printf ("│  🔞 Age Rating  : %-29s │%n", content.getAgeRating().getMinAge() + "+");
        System.out.printf ("│  ⏱  Running Time: %-28s │%n", content.getRunningTime() + " min");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();
    }

    public static void printContentStopStatus(Content content) {
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│            ⏹   S T O P P E D   ⏹                  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf ("│  🎬 Title       : %-29s │%n", truncate(content.getName(), 29));
        System.out.printf ("│  ⏱  Watched     : %-28s │%n", "재생 정보가 저장되었습니다");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();
    }

    public static void printContentProgress(Content content, int currentMinutes) {
        int total = content.getRunningTime();
        int percent = (int) ((currentMinutes * 100.0) / total);
        String bar = buildProgressBar(currentMinutes, total, 8);

        System.out.printf("[▶ %s] %s %d/%d분 (%d%%)%n",
                content.getName(), bar, currentMinutes, total, percent);
    }

    private static String buildProgressBar(int current, int total, int width) {
        int filled = (int) ((current / (double) total) * width);
        if (filled > width) filled = width;
        if (filled < 0) filled = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filled; i++) sb.append("█");
        for (int i = filled; i < width; i++) sb.append("░");
        return sb.toString();
    }

    private static void printHeader() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("                 🎬  N E T F L I X  🎬              ");
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }

    // 헤더 출력
    private static void printContentTableHeader() {
        System.out.println("┌─────┬─────┬────────────────────────┬────────────┬────────────┐");
        System.out.printf("│ %-3s │ %-3s │ %-22s │ %-10s │ %-6s  │%n", "No.", "ID", "Title", "Genre", "AgeRating");
        System.out.println("├─────┼─────┼────────────────────────┼────────────┼────────────┤");
    }

    // 푸터 출력
    private static void printContentTableFooter() {
        System.out.println("└─────┴─────┴────────────────────────┴────────────┴────────────┘");
    }

    // 개별 row 출력 (No. 포함)
    private static void printContentRow(int no, Content content) {
        System.out.printf("│ %-3d │ %-3d │ %-22s │ %-10s │ %-6s     │%n",
                no,
                content.getId(),
                truncate(content.getName(), 22),
                content.getGenre(),
                content.getAgeRating().getMinAge());
    }

    // 다중 출력
    private static void printContentTable(List<Content> contents) {
        printContentTableHeader();
        if (contents.isEmpty()) {
            System.out.println("│            (No contents registered)                          │");
        } else {
            for (int i = 0; i < contents.size(); i++) {
                printContentRow(i + 1, contents.get(i));
            }
        }
        printContentTableFooter();
        System.out.println("Total: " + contents.size() + " contents");
        System.out.println();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("─────────────── MENU ───────────────");
        MenuOption[] options = MenuOption.values();
        for (MenuOption option : options) {
            System.out.printf("  [%d] %s%n", option.getCode(), option.getLabel());
        }
        System.out.println("─────────────────────────────────────");
    }

    private static String truncate(String s, int maxLength) {
        if (s.length() <= maxLength) {
            return s;
        }
        return s.substring(0, maxLength - 3) + "...";
    }
}