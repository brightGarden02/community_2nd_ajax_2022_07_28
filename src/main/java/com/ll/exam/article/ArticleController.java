package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.dto.ArticleDto;
import com.ll.exam.util.Ut;

import java.util.List;

/**
 * ArticleController class 역할
 * 1. 게시물 리스트를 보여준다.
 */

public class ArticleController {

    private ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }


    // 리스트를 보여준다.
    public void showList(Rq rq) {

//        rq.appendBody("게시물 리스트");

//        List<ArticleDto> articleDtos = new ArrayList<>();
//        articleDtos.add(new ArticleDto(7, "제목 7", "내용 7"));
//        articleDtos.add(new ArticleDto(6, "제목 6", "내용 6"));
//        articleDtos.add(new ArticleDto(5, "제목 5", "내용 5"));
//        articleDtos.add(new ArticleDto(4, "제목 4", "내용 4"));
//        articleDtos.add(new ArticleDto(3, "제목 3", "내용 3"));
//        articleDtos.add(new ArticleDto(2, "제목 2", "내용 2"));
//        articleDtos.add(new ArticleDto(1, "제목 1", "내용 1"));

        List<ArticleDto> articleDtos = articleService.findAll();

        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");


    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

//        rq.appendBody("<div>title : %s</div>".formatted(title));
//        rq.appendBody("<div>body : %s</div>".formatted(body));

        if (title.length() == 0) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if (body.length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }


        long id = articleService.write(title, body);
//        rq.println("%d번 게시물이 생성 되었습니다.".formatted(id));
        rq.replace("/usr/article/detail/free/%d".formatted(id), "%d번 게시물이 생성 되었습니다.".formatted(id));

    }


    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if(id == 0) {
//            rq.println("번호를 입력해주세요.");
            rq.historyBack("번호를 입력해주세요.");
            return;
        }


        ArticleDto articleDto = articleService.findById(id);

        if(articleDto == null) {
//            rq.println("해당 글이 존재하지 않습니다.");
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void deleteList(Rq rq) {

        long id = rq.getLongPathValueByIndex(1, 0);

        if(id == 0) {
//            rq.println("번호를 입력해주세요.");
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if(articleDto == null) {
//            rq.println("해당 글이 존재하지 않습니다.");
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.delete(id);

//        rq.appendBody("%d번 게시물이 삭제 되었습니다.".formatted(id));
//        rq.println("<div>%번 게시물이 삭제되었습니다.</div>".formatted(id));
//        rq.println("<div><a href=\"/usr/article/list/free\">리스트로 이동</a></div>".formatted(id));
        rq.replace("/usr/article/list/free", "%d번 게시물이 삭제되었습니다.".formatted(id));


    }

    public void showModify(Rq rq) {

        long id = rq.getLongPathValueByIndex(1, 0);

        if(id == 0){
//            rq.println("번호를 입력해주세요.");
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if(articleDto == null){
//            rq.println("해당 글이 존재하지 않습니다.");
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/modify");

    }

    public void doModify(Rq rq) {

        long id = rq.getLongPathValueByIndex(1, 0);
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

//        rq.appendBody("<div>id : %d</div>".formatted(id));
//        rq.appendBody("<div>title : %s</div>".formatted(title));
//        rq.appendBody("<div>body : %s</div>".formatted(body));

        articleService.modify(id, title, body);
//        rq.println("<div>%번 게시물이 수정되었습니다.</div>".formatted(id));
//        rq.println("<div><a href=\"/usr/article/detail/free\">수정된 글로 이동</a></div>".formatted(id));

        // 브라우저에게 해당 URI로 이동하는 자바스크립트를 전송해주세요.
        // 혹시 그 전에 전할 메세지가 있다면 alert 로 표시되도록 자바스크립트를 구성해주세요.
        rq.replace("/usr/article/detail/free/%d".formatted(id), "%d번 게시물이 수정되었습니다.".formatted(id));


    }

    public void getArticles(Rq rq) {

        List<ArticleDto> articleDtos = articleService.findAll();

//        String jsonStr = Ut.json.toStr(articleDtos, "");
//        rq.println(jsonStr);

        rq.json(articleDtos);
    }
}
