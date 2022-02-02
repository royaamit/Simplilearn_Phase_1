package final01.menu;

import java.util.ArrayList;

//implement composite pattern
public class MenuItem implements CommandInterface {

    private MenuItem parentMenuItem;
    private String commandName;

    public String getCommandName() {
        return commandName;
    }

    public CommandInterface command;
    public ArrayList<MenuItem> menuItems;



    public MenuItem(String commandName, CommandInterface command) {
        this.commandName=commandName;
        this.command=command;
        menuItems=new ArrayList<>();
    }


    public boolean isComposite(){
        return this.menuItems.size()>0;
    }

    public void addMenuNode(MenuItem menuItem){
        menuItems.add(menuItem);
        menuItem.setParentMenuItem(this);
    }


    @Override
    public void execute() {
        command.execute();
    }

    public void setParentMenuItem(MenuItem parentMenuItem) {
        this.parentMenuItem = parentMenuItem;
    }

    public MenuItem getParentMenuItem() {
        return parentMenuItem;
    }
}
