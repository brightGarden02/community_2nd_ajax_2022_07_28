package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ll.exam.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {

    @Test
    void assertJ_asserThat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    void ObjectMapper__objToJsonStr(){

        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");

        String jsonStr = Ut.json.toStr(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());
    }

    @Test
    void ObjectMapper__jsonStrToObj() {

        ArticleDto articleDtoOrigin = new ArticleDto(1, "제목", "내용");
        String jsonStr = Ut.json.toStr(articleDtoOrigin, "");

       ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);


        assertThat(articleDtoOrigin).isEqualTo(articleDtoFromJson);

    }


}
