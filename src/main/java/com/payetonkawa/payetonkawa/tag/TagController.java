package com.payetonkawa.payetonkawa.tag;


import lombok.Builder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${myATS.frontend.url}")
@RestController
@Builder
@RequestMapping("/api/v1/")
public class TagController {
	private ITagService tagService;

	@GetMapping("/tags")
	public List<Tag> getAllTags(){
		return tagService.getAllTags();
	}

}
