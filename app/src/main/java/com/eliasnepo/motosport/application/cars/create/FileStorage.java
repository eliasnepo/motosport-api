package com.eliasnepo.motosport.application.cars.create;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface FileStorage {
    URL uploadFile(MultipartFile file);
}
