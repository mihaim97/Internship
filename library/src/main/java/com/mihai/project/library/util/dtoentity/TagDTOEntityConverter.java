package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.book.TagDTO;
import com.mihai.project.library.dto.book.update.TagDTOID;
import com.mihai.project.library.entity.book.Tag;

import java.util.List;

public interface TagDTOEntityConverter {

    TagDTO fromTagToDTO(Tag tag);

    Tag fromDTOToTag(TagDTO tagDTO);

    TagDTOID fromTagToDTOID(Tag tag);

    Tag fromDtoIdToTag(TagDTOID tagDTOID);

    List<TagDTOID> fromTagsToDTOID(List<Tag> tags);
}
