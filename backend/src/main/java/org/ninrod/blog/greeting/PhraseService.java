package org.ninrod.blog.greeting;

import org.springframework.stereotype.Service;

@Service
public class PhraseService {
    public String phrase() {
        return "Hello, ";
    }
}
