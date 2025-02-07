package system;

public class Command {
    private final String description;
    private final Runnable action;

    public Command(String description, Runnable action){
        this.description = description;
        this.action = action;
    }
    public void execute() {
        action.run();
    }

    public String getDescription() {
        return description;
    }
    int convertToIndex(int userNumber) {
        return userNumber - 1;
    }
}