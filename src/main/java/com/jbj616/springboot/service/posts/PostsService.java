package com.jbj616.springboot.service.posts;

import com.jbj616.springboot.domain.posts.Posts;
import com.jbj616.springboot.domain.posts.PostsRepository;
import com.jbj616.springboot.web.dto.PostsListResponseDto;
import com.jbj616.springboot.web.dto.PostsResponseDto;
import com.jbj616.springboot.web.dto.PostsSaveRequestDto;
import com.jbj616.springboot.web.dto.PostsUpdateRequestDto;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id= " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDecs() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id= " + id));

        postsRepository.delete(posts);
    }
}
