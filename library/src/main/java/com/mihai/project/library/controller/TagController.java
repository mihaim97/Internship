package com.mihai.project.library.controller;

import com.mihai.project.library.dto.book.TagDTO;
import com.mihai.project.library.dto.book.update.TagDTOID;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.service.TagService;
import com.mihai.project.library.util.dtoentity.TagDTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagDTOEntityConverter converter;

    @PostMapping("/add")
    public ResponseEntity addTag(@RequestBody TagDTO tagDTO){
        Tag tag = tagService.addTag(converter.fromDTOToTag(tagDTO));
        if(tag == null){
           return tagExist(tagDTO.getName());
        }
        return new ResponseEntity(converter.fromTagToDTOID(tag), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeTag(@RequestParam int id){
        if(tagService.removeTag(id)){
            return new ResponseEntity("deleted", HttpStatus.OK);
        }
        return noTagToDelete(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateTag(){
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TagDTOID>> queryTags(){
        return new ResponseEntity<>(converter.fromTagsToDTOID(tagService.queryTags()), HttpStatus.OK);
    }

    @GetMapping("/single-tag")
    public ResponseEntity queryTag(){
        return null;
    }

    /** Separate class in all controllers **/
    private ResponseEntity tagExist(String name){
        return new ResponseEntity("Tag " + name + " exist", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity noTagToDelete(int id){
        return new ResponseEntity("No tag with id " + id , HttpStatus.BAD_REQUEST);
    }

}
