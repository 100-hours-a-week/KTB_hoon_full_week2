import content.Content;
import dto.ContentAddReqDto;
import enums.ContentTypeOption;
import enums.menu.MenuOption;
import exception.ContentNotFoundException;
import java.util.List;
import repository.ContentRepository;
import service.NetflixService;
import view.InputView;
import view.OutputView;

public class Main {

    private static final ContentRepository repository = new ContentRepository();
    private static final NetflixService service = new NetflixService(repository);

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
        List<Content> contents = service.handleContentsView();
        OutputView.printContentList(contents);
    }

    private static void handleContentDetail() {
        while(true){
            try{
                int contentId = InputView.selectContentId();
                Content content = service.getContent(contentId);
                OutputView.printContent(content);
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
                service.addOriginalMovie(InputView.readOriginalMovieInfo(contentAddRequest));
                break;
            case LICENSED_MOVIE:
                service.addLicensedMovie(InputView.readLicensedMovieInfo(contentAddRequest));
                break;
            case SERIES:
                service.addSeries(InputView.readSeriesInfo(contentAddRequest));
                break;
            case CANCEL:
                OutputView.printMessage("취소되었습니다");
                break;
        }
    }
}