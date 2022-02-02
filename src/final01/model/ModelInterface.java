package final01.model;

import final01.view.observers.AddingObserver;
import final01.view.observers.DeletingObserver;
import final01.view.observers.FIleListObserver;
import final01.view.observers.SearchObserver;
import java.nio.file.Path;


//implement strategy pattern
public interface ModelInterface {

    void addFile(String path);
    void deleteFile(String path);
    void searchFile(String path);
    void getFileList();

    Path getRootPath();
    void setRootPath(String source);



    void registerObserver(AddingObserver o);
    void removeObserver(AddingObserver o);

    void registerObserver(DeletingObserver o);
    void removeObserver(DeletingObserver o);

    void registerObserver(SearchObserver o);
    void removeObserver(SearchObserver o);

    void registerObserver(FIleListObserver o);
    void removeObserver(FIleListObserver o);


}
