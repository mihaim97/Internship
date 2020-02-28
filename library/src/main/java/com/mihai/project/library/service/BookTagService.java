package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Tag;

public interface BookTagService {
    Tag querySingleTahForBookValidation(String name);
}
