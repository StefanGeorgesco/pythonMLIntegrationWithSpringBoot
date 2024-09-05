package kia.shamaei.serverapp.service.pretrainModel;

public interface PretrainModelService {
    String recogniseImage(byte[] image , String nameOfSavedModel);
    String savedTempImage(byte[] image);
}
