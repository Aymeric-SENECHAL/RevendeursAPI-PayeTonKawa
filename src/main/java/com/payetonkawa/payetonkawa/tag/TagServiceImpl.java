package com.payetonkawa.payetonkawa.tag;

import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class TagServiceImpl implements ITagService {
	private final TagRepository tagRepository;

	@Override
	public List<Tag> getAllTags() {
		return tagRepository.findAll();
	}

}

