package com.mihai.project.library.controller;

import com.mihai.project.library.dto.book.TagDTO;
import com.mihai.project.library.dto.book.update.TagDTOID;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.service.tag.TagService;
import com.mihai.project.library.util.dtoentity.TagDTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(path = "tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagDTOEntityConverter converter;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTag(@RequestBody TagDTO tagDTO) {
        Tag tag = tagService.addTag(converter.fromDTOToTag(tagDTO));
        if (tag == null) {
            return tagExist(tagDTO.getName());
        }
        return new ResponseEntity(converter.fromTagToDTOID(tag), HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeTag(@RequestParam int id) {
        if (tagService.removeTag(id)) {
            return new ResponseEntity("deleted", HttpStatus.OK);
        }
        return noTagWithId(id);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTag(@RequestBody TagDTOID tagDTOID) {
        Tag tag = tagService.updateTag(converter.fromDtoIdToTag(tagDTOID));
        if (tag == null) {
           return noTagWithId(tagDTOID.getId());
        }
        return new ResponseEntity(tagDTOID, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDTOID>> queryTags() {
        return new ResponseEntity<>(converter.fromTagsToDTOID(tagService.queryTags()), HttpStatus.OK);
    }

    @GetMapping(value = "/single-tag-by-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryTagByName(@RequestParam @NotBlank String name) {
        Tag tag = tagService.queryTagByName(name);
        if (tag == null) {
            return new ResponseEntity("No tag with name " + name, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(converter.fromTagToDTOID(tag), HttpStatus.OK);
    }

    @GetMapping(value = "/single-tag-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryTagById(@RequestParam int id) {
        Tag tag = tagService.queryTagById(id);
        if (tag == null) {
            return noTagWithId(id);
        }
        return new ResponseEntity(converter.fromTagToDTOID(tag), HttpStatus.OK);
    }

    @GetMapping(value = "/search-tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchTags(@RequestParam String characters) {
        return new ResponseEntity(converter.fromTagsToDTOID(tagService.queryTagsLike(characters)), HttpStatus.OK);
    }



    /**
     * Separate class in all controllers
     **/
    private ResponseEntity tagExist(String name) {
        return new ResponseEntity("Tag " + name + " exist", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity noTagWithId(int id) {
        return new ResponseEntity("No tag with id " + id, HttpStatus.BAD_REQUEST);
    }

}
