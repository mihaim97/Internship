package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.book.TagDTO;
import com.mihai.project.library.dto.book.update.TagDTOID;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.MyObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagDTOEntityConverterImpl implements TagDTOEntityConverter {

    @Override
    public TagDTO fromTagToDTO(Tag tag) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        return mapper.map(tag, TagDTO.class);
    }

    @Override
    public Tag fromDTOToTag(TagDTO tagDTO) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        return mapper.map(tagDTO, Tag.class);
    }

    @Override
    public TagDTOID fromTagToDTOID(Tag tag) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        return mapper.map(tag, TagDTOID.class);
    }

    @Override
    public List<TagDTOID> fromTagsToDTOID(List<Tag> tags) {
        System.out.println(tags);
        if (tags != null && !tags.isEmpty()) {
            List<TagDTOID> tagsToReturn = new ArrayList<>();
            ModelMapper mapper = MyObjectMapper.getMapper();
            tags.stream().forEach(tag -> {
                tagsToReturn.add(mapper.map(tag, TagDTOID.class));
            });
            return tagsToReturn;
        }
        return null;
    }
}
