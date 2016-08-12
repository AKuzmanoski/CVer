package com.cver.team.services;


import com.cver.team.model.data.Image;

public interface ImageService {

    Image createNewImage(Image image);

    Image deleteImage(Image image);

    Image getImageByURL(String URL);

}
