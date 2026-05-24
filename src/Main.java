import content.Content;
import content.player.BasicPlayer;
import content.player.Player;
import dto.ContentAddReqDto;
import enums.ContentTypeOption;
import enums.menu.ContentMenuOption;
import enums.menu.MenuOption;
import exception.ContentNotFoundException;
import java.util.List;
import repository.ContentRepository;
import repository.PlayHistoryRepository;
import service.ContentService;
import service.PlayService;
import view.InputView;
import view.OutputView;

public class Main {

    private static final Player player = new BasicPlayer();
    private static final ContentRepository contentRepository = new ContentRepository();
    private static final PlayHistoryRepository playHistoryRepository = new PlayHistoryRepository();
    private static final ContentService contentService = new ContentService(contentRepository);
    private static final PlayService playService = new PlayService(playHistoryRepository, player);

    public static void main(String[] args) {
        while (true) {
            OutputView.printMainScreen();
            MenuOption menu = MenuOption.fromCode(InputView.readMenu());
            switch (menu) {
                case VIEW_CONTENTS: handleContentList();   break;
                case VIEW_CONTENT_INFO: handleContentDetail(); break;
                case ADD_CONTENTS: handleAddContent();    break;
                case EXIT: OutputView.printGoodbye(); return;
                default: OutputView.printInvalidMenuNumber();
            }
        }
    }

    private static void handleContentList() {
        List<Content> contents = contentService.handleContentsView();
        OutputView.printContentList(contents);
    }

    private static void handleContentDetail() {
        while(true){
            try{
                int contentId = InputView.selectContentId();
                Content content = contentService.getContent(contentId);
                OutputView.printContent(content);
                while (true){
                    ContentMenuOption op = InputView.selectContentDisplayOption();
                    if (op == ContentMenuOption.PLAY) {
                        playService.playContent(content);
                    }
                    break;
                }
                return;
            }catch (ContentNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleAddContent() {
        ContentTypeOption option = InputView.readContentType();
        ContentAddReqDto contentAddRequest = InputView.readContentInfo();
        switch (option) {
            case ORIGINAL_MOVIE:
                contentService.addOriginalMovie(InputView.readOriginalMovieInfo(contentAddRequest));
                break;
            case LICENSED_MOVIE:
                contentService.addLicensedMovie(InputView.readLicensedMovieInfo(contentAddRequest));
                break;
            case SERIES:
                contentService.addSeries(InputView.readSeriesInfo(contentAddRequest));
                break;
            case CANCEL:
                OutputView.printMessage("취소되었습니다");
                break;
        }
    }
}