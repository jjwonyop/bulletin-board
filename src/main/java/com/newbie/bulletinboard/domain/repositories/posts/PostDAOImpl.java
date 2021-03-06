package com.newbie.bulletinboard.domain.repositories.posts;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostDAOImpl implements PostDAO {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public Optional<PostVO> getPostInformation(PostVO postVO) {
        return postRepository.findById(postVO.getPostId());
    }

    @Override
    public PostVO insertPost(PostVO postVO) {
        postVO.setCreateDate(new Date());

        return postRepository.save(postVO);
    }

    @Override
    public PostVO updatePost(PostVO postVO) {
        postMapper.updateContents(postVO);

        return postRepository.findById(postVO.getPostId()).orElseGet(PostVO::new);
    }

    @Override
    public List<PostVO> getPostList(int page, int size) {
        var all = postRepository.findAll(PageRequest.of(page, size));
        return all.getContent();
    }

    @Override
    public PostVO deletePost(PostVO postVO) {
        postRepository.deleteById(postVO.getPostId());
        return null;
    }
}
