package game;


import game.View.ProgramWindow;

public class MainApp {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller();
        ProgramWindow programWindow = new ProgramWindow(controller);
        controller.setView(programWindow);






        programWindow.pack();
        programWindow.setLocationRelativeTo(null);
        programWindow.setVisible(true);
    }
}
