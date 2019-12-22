package com.jbj616.springboot.web.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import com.jbj616.springboot.domain.posts.Posts;
import com.jbj616.springboot.domain.posts.PostsRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_블러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                                 .title(title)
                                 .content(content)
                                 .author("jbj616@gmail.com")
                                 .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                                 .title("title")
                                 .content("content")
                                 .author("author")
                                 .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out
            .println(">>>>>> createDate= " + posts.getCreateTime() + ", modifiedDate= " + posts.getModifiedDate());

        assertThat(posts.getCreateTime()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }
}
