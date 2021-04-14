package io.spring.vikop.tag;

import io.spring.vikop.tag.exception.TagNotFoundException;
import io.spring.vikop.user.User;
import io.spring.vikop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;
    private UserService userService;

    @Autowired
    public TagService(TagRepository tagRepository,
                      UserService userService) {
        this.tagRepository = tagRepository;
        this.userService = userService;
    }

    Tag getTagByName(String tagName) {
        return tagRepository.findByName(tagName).orElseThrow(() -> {
            throw new TagNotFoundException(tagName);
        });
    }

    public List<Tag> getTagsFromDBOrSave(List<Tag> tags) {
        tags.forEach(t -> t.setName(removeTagSymbol(t.getName()).toLowerCase()));
        List<Tag> result = new ArrayList<>();
        for(Tag t: tags){
            Tag tag = tagRepository.findByName(t.getName())
                    .orElseGet(() -> tagRepository.save(t));
            result.add(tag);
        }
        return result;
    }

    public List<Tag> getTagsFromContent(String content) {
        content = content.replace("\n", " ");
        content = content.replace("\t", " ");
        String[] words = content.split(" ");
        List<Tag> foundTags = new ArrayList<>();
        Tag tag;
        for (String word : words) {
            if (word.startsWith("#")) {
                word = removeTagSymbol(word).toLowerCase();
                tag = new Tag();
                tag.setName(word);
                if (!foundTags.contains(tag)) {
                    foundTags.add(tag);
                }
            }
        }
        return foundTags;
    }

    private String removeTagSymbol(String tag) {
        return tag.replace("#", "");
    }

    int followTag(String tagName) {
        User loggedUser = userService.getLoggedUser();
        Tag tag = getTagByName(tagName);
        loggedUser.followTag(tag);
        tag.increaseFollowCount();
        userService.updateUser(loggedUser);
        return tag.getFollowCount();
    }

    int unfollowTag(String tagName) {
        User loggedUser = userService.getLoggedUser();
        Tag tag = getTagByName(tagName);
        loggedUser.unfollowTag(tag);
        tag.decreaseFollowCount();
        userService.updateUser(loggedUser);
        return tag.getFollowCount();
    }
}
