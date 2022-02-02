package final01;

import final01.controller.Controller;
import final01.model.Model;
import final01.view.View;

import java.nio.file.Paths;

public class Solution {

    public static void main(String[] args) {

        System.out.println("Welcome to the program - Virtual Key for Your Repositories!!! \nDeveloper is Mamatha S \n");

        Model model=new Model(Paths.get("D:\\Test folder"));
        Controller controller=new Controller(model);
        View view=new View(controller, model);

        //start
        view.buildMenuStructure();
        view.showMenu();
        view.selectAndPerformOperation();
    }

}
