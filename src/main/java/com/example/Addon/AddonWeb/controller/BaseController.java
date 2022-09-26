package com.example.Addon.AddonWeb.controller;

import com.example.Addon.AddonWeb.service.ServiceRegistry;
import com.example.Addon.AddonWeb.utils.FileUploadHelper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
public abstract class BaseController {

    @Autowired
    private ServiceRegistry serviceRegistry;

    @Autowired
    private FileUploadHelper fileUploadHelper;


}
