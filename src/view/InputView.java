package view;

import dto.ContentAddReqDto;
import dto.LicensedMovieAddReqDto;
import dto.OriginalMovieAddReqDto;
import dto.SeriesAddReqDto;
import enums.AgeRating;
import enums.ContentTypeOption;
import enums.Genre;
import enums.SeriesType;

import enums.menu.ContentMenuOption;
import java.time.LocalDate;
import java.util.Scanner;
import validator.InputValidator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readMenu() {
        return readPositiveInt("선택");
    }

    public static int selectContentId() {
        return readPositiveInt("컨텐츠 ID");
    }

    public static ContentMenuOption selectContentDisplayOption(){
        while (true) {
            ContentMenuOption[] ops = ContentMenuOption.values();
            System.out.println("─────────────── ▶ ⏸ ───────────────");
            for(ContentMenuOption op : ops){
                System.out.printf("  [%d] %s%n", op.getCode(), op.getLabel());
            }
            System.out.println("─────────────────────────────────────");
            int choice = readPositiveInt("선택");
            try {
                return ContentMenuOption.fromCode(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ContentTypeOption readContentType() {
        while (true) {
            System.out.println();
            System.out.println("─── 컨텐츠 종류 선택 ───");
            for (ContentTypeOption type : ContentTypeOption.values()) {
                System.out.printf("  [%d] %s%n", type.getCode(), type.getLabel());
            }
            int choice = readPositiveIntAllowZero("선택");
            try {
                return ContentTypeOption.fromCode(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ContentAddReqDto readContentInfo() {
        String name = readString("제목");
        Genre genre = readGenre();
        AgeRating ageRating = readAgeRating();
        int runningTime = readPositiveInt("재생 시간 (분)");
        String description = readString("설명");
        return new ContentAddReqDto(name, genre, ageRating, runningTime, description);
    }

    public static OriginalMovieAddReqDto readOriginalMovieInfo(ContentAddReqDto contentInfo) {
        int releaseYear = readPositiveInt("개봉 연도");
        String distributor = readString("배급사");
        LocalDate releaseDate = readDate("릴리즈 날짜/시간");
        return new OriginalMovieAddReqDto(contentInfo, releaseYear, distributor, releaseDate);
    }

    public static LicensedMovieAddReqDto readLicensedMovieInfo(ContentAddReqDto contentInfo) {
        int releaseYear = readPositiveInt("개봉 연도");
        String distributor = readString("배급사");
        LocalDate startDate = readDate("라이센스 시작일");
        LocalDate endDate = readDate("라이센스 종료일");
        return new LicensedMovieAddReqDto(contentInfo, releaseYear, distributor, startDate, endDate);
    }

    public static SeriesAddReqDto readSeriesInfo(ContentAddReqDto contentInfo) {
        int seasonNumber = readPositiveInt("시즌 수");
        int episodeNumber = readPositiveInt("총 에피소드 수");
        SeriesType seriesType = readSeriesType();
        return new SeriesAddReqDto(contentInfo, seasonNumber, episodeNumber, seriesType);
    }

    private static SeriesType readSeriesType() {
        while (true) {
            System.out.println();
            System.out.println("─── 시리즈 종류 선택 ───");
            for (SeriesType seriesType : SeriesType.values()) {
                System.out.printf("  [%d] %s%n", seriesType.getCode(), seriesType.getLabel());
            }
            int choice = readPositiveInt("선택");
            try {
                return SeriesType.fromCode(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Genre readGenre() {
        while (true) {
            System.out.println();
            System.out.println("─── 장르 선택 ───");
            for (Genre genre : Genre.values()) {
                System.out.printf("  [%d] %s%n", genre.getCode(), genre.getLabel());
            }
            int choice = readPositiveInt("선택");
            try {
                return Genre.fromCode(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static AgeRating readAgeRating() {
        while (true) {
            System.out.println();
            System.out.println("─── 시청 등급 선택 ───");
            for (AgeRating rating : AgeRating.values()) {
                System.out.printf("  [%d] %s%n", rating.getCode(), rating.getMinAge());
            }
            int choice = readPositiveInt("선택");
            try {
                return AgeRating.fromCode(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " >> ");
            String input = scanner.nextLine().trim();
            try {
                InputValidator.validateDate(input, prompt);
                return LocalDate.parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt + " >> ");
            String line = scanner.nextLine().trim();
            try {
                InputValidator.validateNotBlank(line, prompt);
                return line;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt + " >> ");
            String line = scanner.nextLine().trim();
            try {
                int input = Integer.parseInt(line);
                InputValidator.validatePositive(input, prompt);
                return input;
            } catch (NumberFormatException e) {
                System.out.println(prompt + "은(는) 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int readPositiveIntAllowZero(String prompt) {
        while (true) {
            System.out.print(prompt + " >> ");
            String line = scanner.nextLine().trim();
            try {
                int input = Integer.parseInt(line);
                if (input < 0) {
                    System.out.println(prompt + "은(는) 0 이상이어야 합니다.");
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println(prompt + "은(는) 숫자여야 합니다.");
            }
        }
    }
}