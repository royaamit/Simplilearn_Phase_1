package final01.view;

import final01.util.StringHelper;
import final01.controller.ControllerInterface;
import final01.menu.MenuItem;
import final01.model.ModelInterface;
import final01.view.observers.AddingObserver;
import final01.view.observers.DeletingObserver;
import final01.view.observers.FIleListObserver;
import final01.view.observers.SearchObserver;

import java.io.File;
import java.util.List;

//implement observer pattern
public class View implements AddingObserver, DeletingObserver, FIleListObserver, SearchObserver,ViewInterface {

    //root of menu tree
    private MenuItem rootMenuItem;

    //current composite in menu tree
    private MenuItem currentComposite;

    //implement strategy pattern
    private ControllerInterface controller;
    private ModelInterface model;




    public View(ControllerInterface controller, ModelInterface model) {
        this.controller = controller;
        this.model = model;
        rootMenuItem=new MenuItem("root",null);
        currentComposite=rootMenuItem;

        model.registerObserver((AddingObserver) this);
        model.registerObserver((DeletingObserver) this);
        model.registerObserver((SearchObserver) this);
        model.registerObserver((FIleListObserver) this);

    }

    public MenuItem getCurrentComposite() {
        return currentComposite;
    }

    public void setCurrentComposite(MenuItem currentComposite) {
        this.currentComposite = currentComposite;
    }

    @Override
    public void showMenu(){
        System.out.println("Enter number of item:");
        for (int i = 0; i <currentComposite.menuItems.size() ; i++) {
            System.out.println(i+1+". "+currentComposite.menuItems.get(i).getCommandName());
        }
    }

    public void menuLevelUp(){
        setCurrentComposite(getCurrentComposite().getParentMenuItem());
        showMenu();
    }

    @Override
    public void selectAndPerformOperation(){
        try {
            int numberOfCommand=Integer.parseInt(StringHelper.readString());

            //illegal number
            if (numberOfCommand<0||numberOfCommand>currentComposite.menuItems.size()){
                throw new Exception();
            }

            MenuItem selectedMenuItem=currentComposite.menuItems.get(numberOfCommand-1);
            if (selectedMenuItem.isComposite()){
                //This is composite menu item
                currentComposite=selectedMenuItem;
            }
            //implement composite pattern
            selectedMenuItem.execute();
            selectAndPerformOperation();

            //other errors
        } catch (Exception e) {
            System.out.println("Invalid item selected. Try again.");
            selectAndPerformOperation();
        }

    }

    //implement command pattern
    //all menu items included command interface
    @Override
    public void buildMenuStructure(){

        //Adding root menu
        rootMenuItem.menuItems.clear();
        rootMenuItem.addMenuNode(new MenuItem("Mention Path of Directory you want go",controller::setRootDirectory));
        rootMenuItem.addMenuNode(new MenuItem("Show file list in working directory",controller::getFileList));

        MenuItem businesOperation=new MenuItem("Business operation",this::showMenu);
        rootMenuItem.addMenuNode(businesOperation);
        rootMenuItem.addMenuNode(new MenuItem("Exit program",controller::exit));

        //Adding business operation menu
        businesOperation.addMenuNode(new MenuItem("Add file", controller::addFile));
        businesOperation.addMenuNode(new MenuItem("Delete file", controller::deleteFile));
        businesOperation.addMenuNode(new MenuItem("Search file", controller::searchFile));
        businesOperation.addMenuNode(new MenuItem("Return to previous menu", this::menuLevelUp));


    }

    //implements observation

    @Override
    public void updateAddingState(String fileName, boolean isAdded) {
        if (isAdded)
            System.out.println("File "+fileName+" has been successfully added");
        else
            System.out.println("Sorry. File "+fileName+" has not been added");
    }

    @Override
    public void updateDeletingState(String fileName, boolean isDeleted) {

        if (isDeleted)
            System.out.println("File "+fileName+" has been successfully deleted");
        else
            System.out.println("Sorry. File "+fileName+" has not been deleted");
    }

    @Override
    public void updateFileList(List<File> fileList) {

        System.out.println("Printing file list in directory " + model.getRootPath()+" in ascending order");

        for (File file : fileList) {
            System.out.println(file);
        }

        System.out.println("---------------------------------");

    }

    @Override
    public void updateSearchingState(String fileName, boolean isFounded) {
        if (isFounded)
            System.out.println("File "+fileName+" has been successfully founded");
        else
            System.out.println("File "+fileName+" has not been founded");
    }




}
