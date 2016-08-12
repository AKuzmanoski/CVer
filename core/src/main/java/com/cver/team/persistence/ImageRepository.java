package com.cver.team.persistence;

import com.cver.team.model.data.Image;
import com.cver.team.model.entity.Person;


public interface ImageRepository {

    Image createNewImage(Image image, Person owner, String pictureUse);

    Image deleteImage(Image image);

    Image getImageByURL(String URL);

}
