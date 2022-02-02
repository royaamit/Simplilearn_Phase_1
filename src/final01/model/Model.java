package final01.model;

import final01.view.observers.AddingObserver;
import final01.view.observers.DeletingObserver;
import final01.view.observers.FIleListObserver;
import final01.view.observers.SearchObserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//The model doesn't know anything about controller and view
//but the model informs observers about changes in its state

public class Model implements ModelInterface{

    private ArrayList<AddingObserver> addingObservers=new ArrayList<>();
    private ArrayList<DeletingObserver> deletingObservers=new ArrayList<>();
    private ArrayList<SearchObserver> searchObservers=new ArrayList<>();
    private ArrayList<FIleListObserver> fIleListObservers=new ArrayList<>();

    private Path rootPath;

    public Model(Path rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public Path getRootPath() {
        return rootPath;
    }


    //++Main logic begin
    public void setRootPath(String rootPath) {
        Path path = null;

        try {
            path = Paths.get(rootPath);
            setRootPath(path);
        } catch (Exception e) {
            System.out.println("Fail. Specified path is not exist");
            return;
        }
     }


    public void setRootPath(Path rootPath) {

        if (!Files.exists(rootPath)) {
            System.out.println("Fail. Specified path is not exist.");
            return;
        }

        if (!Files.isDirectory(rootPath)) {
            System.out.println("Fail. Specified path is not to Directory path.");
            return;
        }

        this.rootPath = rootPath;
        System.out.println("New working directory is "+rootPath+"\n");
    }



    @Override
    public void addFile(String stringPath) {

        Path file=Paths.get(stringPath);
        try {
            Files.createFile(rootPath.resolve(file));
            notifyAddingObservers(stringPath, true);
        } catch (IOException e) {
            notifyAddingObservers(stringPath, false);
        }

    }

    @Override
    public void deleteFile(String stringPath) {

        Path file=Paths.get(stringPath);
        try {
            Files.delete(rootPath.resolve(file));
            notifyDeletingObservers(stringPath, true);
        } catch (IOException e) {
            notifyDeletingObservers(stringPath, false);
        }
    }

    @Override
    public void searchFile(String stringPath) {

        Path file=Paths.get(stringPath);
        Boolean result=Files.exists(rootPath.resolve(file));
        notifySearchObservers(stringPath, result);
    }



    @Override
    public void getFileList(){

        File dir = new File(rootPath.toString());
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        Collections.sort(lst);
        notifyFIleListObservers(lst);

    }

    //--Main logic end


   //Work with observers;
    private void notifyAddingObservers(String fileName, Boolean isAdded){
        for (AddingObserver addingObserver : addingObservers) {
            addingObserver.updateAddingState(fileName, isAdded);
        }
    }

    private void notifyDeletingObservers(String fileName, Boolean isDeleted){
        for (DeletingObserver deletingObserver : deletingObservers) {
            deletingObserver.updateDeletingState(fileName, isDeleted);
        }
    }

    private void notifyFIleListObservers(List<File> filesList){
        for (FIleListObserver fIleListObserver : fIleListObservers) {
            fIleListObserver.updateFileList(filesList);
        }
    }

    private void notifySearchObservers(String fileName, Boolean isFound){
        for (SearchObserver searchObserver : searchObservers) {
            searchObserver.updateSearchingState(fileName, isFound);
        }
    }

    @Override
    public void registerObserver(AddingObserver o) {
        addingObservers.add(o);
    }

    @Override
    public void removeObserver(AddingObserver o) {
        addingObservers.remove(o);
    }

    @Override
    public void registerObserver(DeletingObserver o) {
        deletingObservers.add(o);
    }

    @Override
    public void removeObserver(DeletingObserver o) {
        deletingObservers.remove(o);
    }

    @Override
    public void registerObserver(SearchObserver o) {
        searchObservers.add(o);
    }

    @Override
    public void removeObserver(SearchObserver o) {
        searchObservers.remove(o);
    }

    @Override
    public void registerObserver(FIleListObserver o) {
        fIleListObservers.add(o);
    }

    @Override
    public void removeObserver(FIleListObserver o) {
        fIleListObservers.remove(o);
    }
 }
