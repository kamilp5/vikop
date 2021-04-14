package io.spring.vikop.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{tagName}")
    public Tag getTagByName(@PathVariable String tagName) {
        return tagService.getTagByName(tagName);
    }

    @PostMapping("/follow")
    public int followTag(@RequestBody String tagName) {
        return tagService.followTag(tagName);
    }

    @PostMapping("/unfollow")
    public int unfollowTag(@RequestBody String tag) {
        return tagService.unfollowTag(tag);
    }

}
