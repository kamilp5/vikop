package io.spring.vikop.common;

import io.spring.vikop.comment.Comment;
import io.spring.vikop.comment.CommentService;
import io.spring.vikop.user.UserService;
import io.spring.vikop.vote.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MainActivityService<R extends MainActivity, T extends ActivityDto> extends BaseActivityService<R> {

    protected MainActivityRepository<R> repository;
    protected Mapper<R, T> mapper;
    protected CommentService<R> commentService;

    public void setCommentService(CommentService<R> commentService) {
        this.commentService = commentService;
    }

    public MainActivityService(MainActivityRepository<R> repository,
                               UserService userService,
                               Mapper<R, T> mapper) {
        super(repository, userService);
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<T> getPage(Pageable pageable) {
        Page<R> page = repository.findAll(pageable);
        setUserVotes(page.getContent());
        return page.map(mapper::toDto);
    }

    public Page<T> getPageByVotesCountLast24h(Pageable pageable) {
        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());
        Page<R> page = repository.findAllByPublishingDateBetweenOrderByVotesCountDesc(from, to, pageable);
        setUserVotes(page.getContent());
        return page.map(mapper::toDto);
    }

    public List<T> getAllByUserId(Long id) {
        List<R> activities = repository.findAllByUserId(id);
        setUserVotes(activities);
        return mapper.toDtoList(activities);
    }

    public List<T> getVotedByUser(Long userId) {
        List<Vote> votes = voteService.getVotes(userId);
        List<R> activities = repository.findAllById(votes.stream()
                .map(Vote::getActivityId)
                .collect(Collectors.toList()));
        setUserVotes(activities);
        return mapper.toDtoList(activities);
    }

    public List<T> getAllByTagName(String tagName) {
        Set<R> activities = repository.findAllByTagNameList(Collections.singletonList(tagName));
        setUserVotes(activities);
        return mapper.toDtoList(activities);
    }

    public List<T> getAllByUserFollowedTags() {
        List<String> tagsNames = userService.getLoggedUser().getFollowedTagsAsListOfStrings();
        if (tagsNames.size() == 0) {
            return Collections.emptyList();
        }
        Set<R> activities = repository.findAllByTagNameList(tagsNames);
        setUserVotes(activities);
        return mapper.toDtoList(activities);
    }

    protected void setUserVotes(Collection<? extends MainActivity> activities) {
        super.findAndMatchUserVotes(activities);
        if (activities.size() != 0) {
            List<Comment> comments = new ArrayList<>();
            activities.forEach(a -> comments.addAll(a.getComments()));
            commentService.findAndMatchUserVotes(comments);
        }
    }

    protected List<R> getActivitiesWithUserComments(Long userId) {
        List<Comment> comments = commentService.getUserComments(userId);
        List<R> activities = new ArrayList<>();
        Map<Long, List<Comment>> activitiesIdsWithComments = new HashMap<>();
        for (Comment comment : comments) {
            if (activitiesIdsWithComments.containsKey(comment.getActivityId())) {
                activitiesIdsWithComments.get(comment.getActivityId()).add(comment);
            } else {
                activitiesIdsWithComments.put(comment.getActivityId(), Arrays.asList(comment));
            }
        }
        for (Map.Entry<Long, List<Comment>> e : activitiesIdsWithComments.entrySet()) {
            R activity = repository.getOne(e.getKey());
            activity.setComments(e.getValue());
            activities.add(activity);
        }
        activities.sort(Comparator.comparing(R::getPublishingDate).reversed().thenComparing(R::getId));
        setUserVotes(activities);
        return activities;
    }
}
