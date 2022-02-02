package final01.controller;

import final01.util.StringHelper;
import final01.model.Model;
import final01.model.ModelInterface;



public class Controller implements ControllerInterface {

    //strategy pattern
    private ModelInterface model;


    public Controller(ModelInterface model) {
        this.model = model;
    }


    private String readFileName(){
        System.out.println("Enter file name");
        return StringHelper.readString();
    }


    //The controller receives additional information and delegates the execution of the model methods

    @Override
    public void addFile() {
        model.addFile(readFileName());
    }

    @Override
    public void deleteFile() {
        model.deleteFile(readFileName());
    }

    @Override
    public void searchFile() {
        model.searchFile(readFileName());
    }

    @Override
    public void getFileList() {
        model.getFileList();
    }

    @Override
    public void setRootDirectory() {
        System.out.println("Enter path to main directory");
        String source= StringHelper.readString();
        model.setRootPath(source);
    }

    @Override
    public void exit() {
        System.exit(0);
    }




}
