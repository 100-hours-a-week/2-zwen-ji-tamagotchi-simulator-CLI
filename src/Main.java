import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DeviceInterface D = new DeviceInterface();
    }
}

class DeviceInterface{
    final String deviceStatrImage = """
            ⠀⠀⣠⡴⠶⠒⠶⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⡰⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⢠⡞⠋⠀⠀⠀⠀⠀⠙⢧⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠎⠁⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⣀⠀⠀⠀
            ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠋⠉⠈⠉⠉⠳⣦⠀
            ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠋⠀⠀⢹⣀⠤⣀⠀⠀⠀⠀⣰⠏⠀⠀⠀⠀⠀⠀⠀⠘⣇
            ⠸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠖⠀⠀⠀⠀⠀⢸⡀⡀⠀⠈⠁⠀⠈⢷⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿
            ⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀⠀⠀⠀⠀⠰⢁⠀⡈⠢⠀⠀⠀⢳⡐⠀⠀⠀⣀⡤⠋⠀⢀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡏
            ⠀⠀⠻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⢦⣄⡀⠀⠀⠀⠀⠚⠀⠀⠀⠀⠀⠈⠧⠖⠋⠉⠁⠀⠀⢀⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠀
            ⠀⠀⠀⠘⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢙⠿⠖⠛⠛⠋⠉⠉⠙⠛⠓⠶⠤⣄⡀⠀⠀⠀⣀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀
            ⠀⠀⠀⠀⠀⠘⠳⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⣤⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠳⢦⣄⣀⡠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠞⠁⠀⠀⠀⠀
            ⠀⠀⠀⠀⡴⠋⠉⠑⣆⠀⠀⢀⡟⠁⠀⠀⠊⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡤⠾⠋⠀⠀⠀⠀⠀⠀⠀
            ⣠⠚⠉⠲⠇⠀⠀⠀⢸⠀⠀⣼⠀⠠⠠⠀⠑⠊⠀⠀⠀⠀⣄⣀⠀⠀⡀⠀⠀⠀⠀⡌⡡⡇⠀⠈⢟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⣇⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀⢻⡀⠄⠀⠀⠨⠀⠀⠀⠀⠀⢸⡄⢀⡟⠀⠀⠀⠀⠀⠈⠒⠁⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠈⠲⠤⣄⣀⡀⠀⡜⠀⠀⠀⠈⠳⣦⣾⠉⢳⡄⠀⠀⠀⠀⠀⠳⠊⠀⠀⠀⠀⠀⠀⠇⠀⠀⠀⢐⢸⠇⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠘⡇⠀⠀⠹⡦⢤⣤⣀⣀⣀⡀⠀⠀⠀⠀⢀⣤⠶⠶⣦⢀⣤⠏⠀⠀⢈⠀⣈⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠋⠉⠙⢿⡄⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠻⠋⠀⠀⢀⡟⠉⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⢀⠀⢀⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣤⡞⠁⠀⢠⢞⡛⡝⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⠀⡏⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⢿⢸⠰⣌⠻⡒⢖⢆⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠙⣆⡀⠈⠁⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⢦⣀⡿⠀⠀⠀⠘⡎⡓⢬⠱⣉⡮⣺⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠒⠒⠚⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⠀⢻⠃⠀⠀⠀⠀⢷⠽⠶⠗⠓⠊⠁⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡆⠀⠉⣶⠦⠤⢤⣤⣤⠤⠴⢿⡀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠲⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠋⠉⠀⠀⠀⠀⠀⠀⠀⡠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠠⢠⢣⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢍⢎⣱⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠒⠣⠁⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠊⠐⠺⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";

    protected final int CONSOLE_WIDTH = 48;
    protected final String CONSOLE_COLOR = "\u001B[36m";
    protected final String RESET = "\u001B[0m";
    protected final String BOLD = "\u001B[1m";
    protected final String DIM = "\u001B[2m";
    BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    String currMessage;
    String currImg;
    Pet pet;
    CommandHandler c =new CommandHandler();
    private boolean isWaitingMode = false;
    //생성자
    DeviceInterface() throws IOException, InterruptedException {
        bootDevice();
        runningDevice();
    }

    /* console output ===========================================================================================*/
    public void setCurrMessage(String currMessage) {
        this.currMessage = currMessage;
    }

    public void setCurrImg(String currImg) {
        this.currImg = currImg;
    }

    void printScreen() throws IOException {
        clearConsole();
        loadBody();
        loadFooter();
        this.buffer.flush();
    }

    void clearConsole() throws IOException{
        for (int i=0 ; i<80 ; i+=1 ) {
            this.buffer.write("\n");
        }
    }

    void loadBody() throws IOException{
        String color;
        if(this.isWaitingMode){
            color = RESET;
        }
        else {
            color = CONSOLE_COLOR;
        }
        this.buffer.write(color+currImg+RESET);
        this.buffer.write('\n');
    }


    void loadFooter() throws IOException{
        String color;
        if(this.isWaitingMode){
            color = RESET;
        }
        else {
            color = CONSOLE_COLOR;
        }
        String topFrame = color+"╭"+ "─".repeat(CONSOLE_WIDTH)+ "╮"+RESET;
        String bottomFrame = color+"╰"+ "─".repeat(CONSOLE_WIDTH) + "╯"+RESET;
        int padding = (50-currMessage.length())/2;

        this.buffer.write(topFrame+'\n');

        boolean levelDisplay =this.isWaitingMode && pet.getLevel() != 0;
        if(levelDisplay){
            this.buffer.write("  Lv."+pet.getLevel());
            this.buffer.write('\n');

        }

        //construct message
        this.buffer.write(" ".repeat(padding));
        this.buffer.write(color+currMessage+RESET);
        this.buffer.write('\n');

        if(levelDisplay){
            this.buffer.write('\n');
        }

        this.buffer.write(bottomFrame+'\n');
        this.buffer.write('\n');
        //construct commands
        if(this.isWaitingMode){
            for(int i=0 ; i < pet.getCommandCount(); i++){
                this.buffer.write(DIM+"▶  "+ (i + 1) +": ");
                this.buffer.write( pet.getCommandNameList().get(i)+RESET);
                this.buffer.write("\n\n");
            }
            this.buffer.write("─".repeat(CONSOLE_WIDTH+2)+"\n\n");
            this.buffer.write("      View Pet's Status (s)          Quit(q)\n");
        }
        //bottom margin space
        this.buffer.write('\n');
    }

    void showStatus(HatchedPet pet) throws IOException{
        clearConsole();
        this.buffer.write("╭"+ "─".repeat(CONSOLE_WIDTH)+ "╮\n\n");
        this.buffer.write("   Lv."+pet.getLevel()+" ".repeat(24)+"Exp : "+pet.getExp()+"\n\n\n");
        this.buffer.write("   아픔 : "+pet.getSickness()+"\n\n");
        this.buffer.write("   배부른 정도 : "+pet.getSatisfaction()+"\n\n");
        this.buffer.write("   행복도 : "+pet.getHappiness()+"\n\n");
        this.buffer.write("╰"+ "─".repeat(CONSOLE_WIDTH) + "╯");
        this.buffer.flush();
    }

    void showStatus(Egg pet) throws IOException{
        clearConsole();
        this.buffer.write("부화까지" +(100-pet.getHatchProgress())+"\n");
        this.buffer.flush();
    }


    /*Device Running ===========================================================================================*/
    private void bootDevice() throws IOException {
        //Divice Init
        this.setCurrImg(deviceStatrImage);
        this.setCurrMessage("press any button to start");

        printScreen();
        c.moveOn();

        //pet init

        pet =new Egg();
        c = new CommandHandler(pet.getCommandCount(),pet.getCommandList());
        pet.updatePetState(false);
        this.setCurrMessage(pet.getCurrMessage());
        this.setCurrImg(pet.getCurrImage());
        isWaitingMode = true;
        printScreen();
    }

    private void runningDevice() throws IOException, InterruptedException {
        while(true){
            //커맨드 입력
            c.input();
            boolean isActioned =false;
            if (c.isValidCommand()) {
                //Quit
                if (c.getUserInput().equalsIgnoreCase("q")){
                    quitDevice();
                }
                // View Pet's Status
                else if (c.getUserInput().equalsIgnoreCase("s")){
                    this.isWaitingMode = false;
                    if(pet instanceof HatchedPet){
                        showStatus((HatchedPet) pet);
                    }
                    else if(pet instanceof Egg){
                        showStatus((Egg) pet);
                    }
                    c.moveOn();
                }
                else{
                    // Action to Pet
                    c.processCommand();
                    this.isWaitingMode = false;
                    this.setCurrImg(pet.getCurrImage());
                    this.setCurrMessage(pet.getCurrMessage());
                    printScreen();
                    c.moveOn();
                    isActioned = true;

                    // specialEventOccur !!
                    if(pet.checkSpecialEvent()) {
                        if (pet.getSupportedEvents().contains(pet.eventType)) {
                            pet.handleSpecialEvent();

                            this.setCurrMessage(pet.getCurrMessage());
                            this.setCurrImg(pet.getCurrImage());
                            this.isWaitingMode = false;
                            printScreen();
                            c.moveOn();

                            if(pet.eventType == Pet.EventType.HATCH || pet.eventType == Pet.EventType.EVOLVE){
                                pet = pet.createNewPet();
                                c = new CommandHandler(pet.getCommandCount(), pet.getCommandList());
                                this.setCurrMessage(pet.getCurrMessage());
                                this.setCurrImg(pet.getCurrImage());
                                this.isWaitingMode = false;
                                printScreen();
                                c.moveOn();
                                isActioned = false;
                            }
                        }
                    }
                }
            }
            else{
                this.isWaitingMode = false;
                this.setCurrImg(pet.getDontKnowImage());
                this.setCurrMessage("유효하지 않은 입력!");
                printScreen();
                c.moveOn();
            }

            // 디폴트 화면 출력..
            pet.updatePetState(isActioned);
            this.setCurrMessage(pet.getCurrMessage());
            this.setCurrImg(pet.getCurrImage());
            this.isWaitingMode = true;
            printScreen();
        }
    }

    private void quitDevice() throws IOException{
        this.buffer.close();
        System.out.println("디바이스를 종료합니다.");
        System.exit(0);
    }
}

class CommandHandler{
    String userInput;
    int num;
    Scanner sc = new Scanner(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int commandCount = 0;
    List<Runnable> commandList = new ArrayList<>();


    CommandHandler() {}

    CommandHandler(int count, List<Runnable> list){
        commandCount = count;
        commandList = list;
    }
    //press any button 에 사용
    public void moveOn() throws IOException {
        System.out.println(" ".repeat(48)+ "enter ▼\n");
        //System.out.print("확인↵");
        reader.readLine();
    }

    public void input(){
        this.userInput = sc.nextLine();
    }

    public String getUserInput() {
        return userInput;
    }

    void processCommand(){
        commandList.get(num-1).run();
    }

    boolean isValidCommand(){
        if(userInput == null || userInput.trim().isEmpty()){
            return false;
        }
        userInput= userInput.trim();

        if (userInput.equalsIgnoreCase("q") || userInput.equalsIgnoreCase("s")){
            return true;
        }

        try {
            num = Integer.parseInt(userInput);

            if(num <=commandCount && num >=0){
                return true;
            }
        }catch (NumberFormatException e) {
            return false;
        }
        return false;

    }
}

abstract class Pet {
    protected String currMessage;
    protected String currImage;

    protected int level;
    protected int exp;

    Random random = new Random();

    int commandCount;
    List<String> commandNameList = new ArrayList<>();
    List<Runnable> commandList = new ArrayList<>();

    EventType eventType = null;

    enum EventType {
        HATCH,      // 부화 이벤트
        LEVEL_UP,
        SPAWN,
        EVOLVE
    }

    abstract public String getDontKnowImage();

    /* getters, setters ============================================================================================*/
    void setCurrMessage(String currMessage) {
        this.currMessage = currMessage;
    }
    public String getCurrMessage() {
        return currMessage;
    }
    void setCurrImage(String currImage) {
        this.currImage = currImage;
    }
    public String getCurrImage() {
        return currImage;
    }
    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getCommandCount() {
        return commandCount;
    }
    public List<Runnable> getCommandList() {
        return commandList;
    }
    public List<String> getCommandNameList() {
        return commandNameList;
    }

    abstract Set<EventType> getSupportedEvents();

    abstract void updatePetState(boolean isActioned);
    abstract void handleSpecialEvent();
    abstract boolean checkSpecialEvent();

    Pet createNewPet(){
        return null;
    }
}

abstract class HatchedPet extends Pet{
    int satisfaction = 60;
    int happiness = 40;
    boolean sickness = false;

    final int STAT_INCREASE_PARAM = 10;
    final int EXP_INCREASE_PARAM = 10;
    //생성자.


    HatchedPet() {
        super();
        level =1;
        commandList.add(this::feeding);
        commandList.add(this::grooming);
        commandList.add(this::healing);
        commandNameList.add("Feed");
        commandNameList.add("Grooming");
        commandNameList.add("Heal");
        commandCount=3;
    }


    public int getSatisfaction() {
        return satisfaction;
    }
    public int getHappiness() {
        return happiness;
    }
    public boolean getSickness(){
        return sickness;
    }

    int randomIncreaseValue(int num){
        return random.nextInt(num);
    }

    /* States ===================================================================================================*/
    @Override
    void updatePetState(boolean isActioned) {
        if(isActioned){
            petStatDecrease();
        }
        evalPetState();
    }
    void evalPetState(){
        if(sickness){
            triggerSicknessProblem();
        }
        else{
            if (happiness <= 40 || satisfaction <= 40) {
                if (happiness < satisfaction) {
                    triggerHappinessProblem();
                } else {
                    triggerSatisfactionProblem();
                }
            } else {
                setNormalPetState();
            }
        }
    }
    void petStatDecrease(){
        if(random.nextInt(10)<1){
            sickness = true;
        }
        happiness -=(randomIncreaseValue(3)+1);
        satisfaction -=(randomIncreaseValue(3)+1);
    }

    /* Actions ====================================================================================================*/
    abstract void feeding();
    abstract void grooming();
    abstract void healing();

    /* Special Event ==============================================================================================*/

    @Override
    abstract Set<EventType> getSupportedEvents();

    @Override
    abstract boolean checkSpecialEvent();

    @Override
    abstract void handleSpecialEvent();


    boolean checkLevelUp(){
        if(exp>= 100){
            return true;
        }
        return false;
    }

    abstract void triggerSicknessProblem();
    abstract void triggerHappinessProblem();
    abstract void triggerSatisfactionProblem();
    abstract void setNormalPetState();
    abstract void levelUpEvent();
}


class MikuLv1 extends HatchedPet{
    String defaultImage =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠁⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠀⠳⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣄⣃⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠑⢦⠀⡴⠊⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡶⣷⠖⠂⠉⠀⠀⠀⠀⠀⠀⠉⠑⠢⣄⠀⣾⣿⡟⠈⠐⠁⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡀⡀⠀⠀⠀⣾⢋⠁⠀⢠⠃⠀⠀⠀⢀⠀⢄⠀⠀⠀⠀⣹⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⡘⠁⠐⡄⠀⡘⠁⠎⠀⠀⡘⠒⠒⠚⢰⠀⠉⣁⠠⡀⠀⠀⣿⣷⠏⠉⠲⡀⠀⠀⠀⠀⠰⠒⢆⠀⠀
                    ⠀⠀⠀⠰⢈⠀⠀⠀⢈⡽⠀⢸⠀⡠⢞⡃⠀⠐⠀⡞⢫⠁⠀⠀⡇⠀⠀⣿⣿⠠⠈⠀⠱⡀⠀⠀⠠⠃⠀⠈⠂⠀
                    ⠀⠀⠀⠀⠀⢡⠀⣰⢹⠃⠀⢸⠎⣀⣠⣇⠀⠠⢰⢁⠤⣻⣤⣰⠁⠀⠀⣿⡿⠀⡂⠁⠀⢇⠀⠠⢄⠀⠀⢀⡠⠇
                    ⠀⠀⠀⠀⠀⠀⠒⠊⣼⢀⡄⢻⡞⢠⣽⣿⢦⡀⡏⠀⢰⣿⣿⣿⠄⠀⢰⣟⡳⠐⡅⠀⠀⢸⠀⠀⠀⢃⠀⠸⠀⠀
                    ⠀⢠⠀⡄⠀⠀⠀⠀⣿⢚⡇⣸⠀⠸⠝⠉⠀⠉⠂⠀⠓⠐⠊⠇⣀⠈⢸⣿⡇⠀⠀⠀⠀⠘⡆⠀⠀⠈⠀⠂⠀⠀
                    ⣐⠁⠀⠈⣂⠀⠀⢰⠇⣸⡇⠱⡀⠀⠀⠀⡠⠄⠐⠀⢢⠀⠚⠉⡛⢠⣿⣿⠆⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀
                    ⠀⢱⠀⠞⠁⠀⠀⣼⠀⠉⡟⠢⢟⣢⣄⣀⡑⢤⡀⠀⣊⣠⣴⣾⡵⠋⠈⢫⠂⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠉⠀⠀⠀⢰⠇⠀⠀⢧⣤⣼⠸⣿⣿⡟⢩⣋⠶⣛⠻⡅⠉⠦⡀⠀⢸⠀⠀⠀⠀⠀⠀⠈⡄⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀⠸⣿⣿⢀⣿⣿⣿⡏⠹⠀⣇⠀⢼⣷⣴⣿⣶⣼⠀⠀⠀⠀⠀⠀⠀⢃⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠰⠁⠀⠀⠀⠀⣿⡟⣸⡟⠝⢩⠀⡆⠀⢹⠀⠈⣿⣿⡼⣿⣿⡀⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⢀⠃⠀⠀⠀⠀⠀⠈⢉⣼⡁⢀⡞⢀⣇⠠⢸⡀⠀⢹⡛⠚⠿⣿⡇⠰⠀⠀⠀⢀⠀⠀⢱⡀⠀⠀⠀⠀
                    ⠀⠀⠀⢀⠎⠀⠀⠠⠀⠀⠀⢀⡎⢿⣇⣼⠁⠘⣟⠀⣨⡇⠀⣠⣇⢹⣶⣬⡇⠀⠄⠀⠀⠘⡄⠀⠀⣇⠀⠀⠀⠀
                    ⠀⠀⢀⡎⠀⠀⢀⠁⠀⠀⠀⡜⠀⣿⡚⢿⣿⣶⣿⣿⣿⣴⣾⡉⢿⣋⣿⠀⠀⡀⠂⠀⠀⢀⠱⠀⠀⢸⡀⠀⠀⠀
                    ⠀⢀⡞⠀⠠⠀⢂⠈⢀⠀⠸⠁⢰⠇⠈⡶⢋⣉⣙⣿⣉⣉⠗⠛⢤⠙⣿⠀⠐⡀⢁⠀⠂⠄⠠⢀⠠⢸⡇⠀⠀⠀
                    ⠀⣼⡃⠠⠁⡐⡀⠌⢀⠈⠄⢂⡾⠀⢠⡃⠄⢠⡟⠀⠈⢻⣄⣐⣿⡟⠻⡀⢁⠀⠂⡈⠄⠈⠄⠠⢀⣸⠇⠀⠀⠀
                    ⠀⢹⣇⠀⠂⠄⢧⠐⢀⢈⣴⠟⠁⠀⣾⢿⣿⡿⠁⠀⠀⠈⣿⡿⣿⣤⠀⠱⣄⠈⡐⠀⠄⠡⠈⠠⣠⡏⠀⠀⠀⠀
                    ⠀⠀⠙⢦⡈⠠⠘⣶⠒⠛⠁⠀⠀⢸⣿⣿⣿⠇⠀⠀⠀⠀⢹⣿⣿⡟⠀⠀⠈⢻⠄⢈⠀⢂⣡⡴⠋⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠉⠒⠤⢜⣦⡀⠀⠀⠀⣾⣿⣿⡋⠀⠀⠀⠀⠀⠀⣿⣿⣧⡇⢠⣴⣡⠤⠶⠞⠋⠁⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠏⠗⠀⠀⠀⠀⠀⠀⠀⠘⠻⠽⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    protected String dontKnowImage = """
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠒⠉⠉⠉⠒⠤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⢀⡤⠤⡀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⡟⠀⢀⡗⠀⠀⢰⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠁⢠⡜⠁⠀⣠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠤⠤⠤⢄⡀⠀⠀
            ⠀⠀⢀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠸⣷⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢇⣀⣀⣀⠀⠈⢢⡀
            ⢀⡔⠉⠀⠀⠀⠀⠙⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡤⠴⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠌⠁⠀⢈⡷⠀⠀⡇
            ⡜⠀⢠⡞⠙⡆⠀⠀⢸⡆⠀⠀⠀⠀⠀⠀⠀⡀⠀⢠⠒⠓⡦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠖⠓⠚⠉⠀⠀⢀⡇
            ⡇⠀⣸⠀⢀⡇⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⡎⢇⠀⠈⠒⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠘⣏⡀⣀⡤⠤⠤⠤⠔⠊⠀
            ⠈⠉⠁⠰⢛⣧⠀⠈⣇⠀⠀⠀⠀⠀⠀⠀⠐⢈⣷⠤⠠⠄⣀⡀⠀⠀⢠⣄⡀⠀⠀⠺⣁⡹⠄⠋⠁⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠘⣧⠴⠛⠀⠀⠀⠀⠀⠀⡠⠂⠁⠀⣀⠀⠀⠀⡀⠑⢤⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⣶⠊⢲⠀⠀⣾⣷⡖⢀⠐⢰⠂⠀⢸⠤⠒⠠⠠⡀⠻⡿⢢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠀⠀⣹⣿⠀⢀⠐⢻⡄⠀⢸⠓⣄⣀⣰⡇⠀⠹⡀⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠁⡇⠀⢾⣠⣿⢷⡄⢸⠀⢋⣿⣿⣷⡄⠀⡇⠀⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠇⠀⡇⠀⢸⣯⣿⣿⡷⢌⡆⠀⣿⣿⡿⡅⢰⣧⠀⠸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⣷⡆⢸⣷⠘⠶⠋⠀⢀⠀⠈⠁⠀⣷⡼⠃⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⢻⠷⡀⣿⢄⡀⠀⢀⣘⡷⠤⣴⣾⡿⠁⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠰⠉⣿⡄⠈⣩⢿⣹⣳⣤⡍⢹⣷⡀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⠀⢠⠀⠀⠉⣟⠀⣰⠁⡜⡸⢘⣼⣿⠈⣿⣿⡄⠀⠀⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡇⢀⠆⠀⠀⢀⣧⣴⣧⡽⣶⡇⠠⣻⣿⢀⣿⣿⡇⠀⠀⠀⠀⢣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⢀⡠⠔⠊⢀⡷⠚⠀⢀⣠⣼⣿⣿⣿⠃⢟⠃⢀⠇⠻⢾⠋⠙⣄⠂⠀⠐⠆⡀⠉⠒⠢⢄⡀⠀⠀⠀⠀⠀
            ⠀⠀⠀⢀⠔⠁⠀⢠⠴⠋⠀⠰⣎⣥⣶⣿⣿⣿⣧⣀⣨⣦⣾⣀⣠⣼⣧⢤⡈⠓⠤⣄⡠⠈⠑⢤⠀⠀⠈⢦⠀⠀⠀⠀
            ⠀⠀⠀⢺⡀⠘⠄⢯⡤⠤⠬⠤⠽⠿⢿⣿⣿⠢⣽⡛⠿⢿⣋⣻⡭⣛⣻⡋⠁⠀⠀⠀⠈⠉⠓⢤⡇⠀⢂⢸⠃⠀⠀⠀
            ⠀⠀⠀⠀⠉⠒⠤⠬⢷⡦⠀⠀⠀⠀⠀⣿⣿⣷⣌⡾⠷⠾⣷⣦⣼⣿⣿⠟⠂⠀⠀⠀⠀⠀⠤⢮⠥⠴⠚⠉⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠋⠁⠀⠀⠀⠹⢿⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";


    protected String spawnImage =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⢡⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⢀⡠⠒⠈⠉⠉⠉⠉⠒⠢⢄⡀⠀⣴⣶⣤⣀⣱⣥⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⢈⡿⠿⠛⣩⠗⠀⠀⠀⠉⠑⠢⡀⠀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠃⡘⢧⣉⡁⠀⠀⣼⠀⠀⠉⢀⡇⠀⠀⠀⢸⣿⣿⣿⣄⡠⠤⠤⠒⠒⠲⠤⠤⣀⠀⠀⠀⠀
                    ⠀⠀⢀⠎⠀⠀⠀⠀⠀⠀⠀⣏⣤⠶⣿⡟⢩⠟⢁⡴⠚⢻⠀⠀⢀⣾⠤⠀⠀⠀⣾⣿⡟⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⣄⠀⠀
                    ⠀⠀⡜⠀⠀⠀⠀⠀⠀⠀⢠⣿⠀⡼⢫⠙⣿⣺⣿⠛⠲⣦⣧⡀⣸⣼⣤⣀⠗⠀⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⠀
                    ⠀⠀⣗⠀⠀⠀⣄⠀⠀⠀⠀⢿⣾⠁⠀⣿⠁⡿⡏⠐⢋⠁⠀⠈⠛⢧⣈⠏⠀⣸⡛⡿⠿⠓⠒⠦⠖⠶⣶⣦⣀⡆⠀⠀⠀⠀⠈⡆
                    ⠀⠀⢹⡐⡄⠀⠈⢧⠀⠀⠀⠈⢯⠀⢸⡏⢸⣥⣿⡄⠀⢏⠉⠒⡗⢚⠟⠀⣴⣿⡿⢁⣀⠤⣾⡷⠀⠀⠈⡿⠋⠀⠀⠀⠀⠀⠀⡇
                    ⠀⠀⠈⢧⠘⠀⠠⠈⣧⠀⠀⠀⠈⣆⣼⢀⣿⣿⣿⣿⠓⠒⣿⣾⣴⣯⡴⣿⣿⣿⣿⠏⣿⡞⠉⠁⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⡇
                    ⠀⠀⠀⠈⠣⡈⢀⠁⢸⡀⠀⠀⠀⠘⡏⠉⠁⠉⠈⠉⠑⣶⣿⣻⠯⣟⣀⡇⣿⣿⣿⠀⣿⠀⠀⢀⠔⠁⠀⠀⠀⢀⠀⠀⡀⠄⢱⠁
                    ⠀⠀⠀⢠⠖⠛⣆⢈⢸⠁⠀⠀⠀⠀⢳⠀⠀⠀⠀⠀⣰⣿⠁⡞⠴⡏⠀⠉⠉⠉⠉⠙⠁⠀⣠⠋⠀⠀⠀⢀⡴⠋⢀⠂⠄⢢⠃⠀
                    ⢀⠖⠉⠁⠀⠀⠘⣾⠂⠀⠀⠀⠀⠀⡞⠣⡀⠀⢀⡼⢻⠏⢰⠁⠀⣇⣀⣤⣄⠀⠀⠀⠀⡰⠁⠀⠀⠀⢀⡞⠀⡐⠠⣀⡶⠋⠀⠀
                    ⠸⡄⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⣰⠃⠀⣹⠶⢾⣧⣼⣷⣇⣠⣴⣿⣿⣿⡁⠀⠀⠀⢠⠇⠀⠀⠀⠀⡞⢀⣰⠴⠛⠁⠀⠀⠀⠀
                    ⠀⠈⠓⠠⢤⡴⠋⠀⢀⣀⡠⠴⠋⠁⠀⠀⠙⢿⡾⠿⢿⣿⡿⠿⢛⣱⣿⡟⠀⠀⢀⡠⠚⡇⠀⠀⠀⠀⣷⠛⠳⢄⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠉⠉⠉⣉⠀⣀⠀⠀⠀⠀⠀⠀⠘⢻⠓⣒⣶⣶⣾⣿⣿⣋⡤⠔⠊⠁⠀⠀⠘⠦⡀⠀⠀⢻⡀⠀⠘⣆⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡰⠊⠀⠀⠀⠙⢤⠀⠀⠀⠀⠀⠘⣿⣿⣿⠑⠻⢿⣿⣯⡄⠀⠀⠀⠀⠀⠀⠀⢸⠉⠓⠒⠛⠀⠀⠀⠘⢆⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⢳⠀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⢻⣿⣿⡄⠀⠀⠈⠛⠁⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀⠀
                    ⠀⠀⠀⠀⠀⢠⠜⠃⠀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠈⣿⣿⡇⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠘⠒⠤⠄⠄⣀⣀⣀⠜⠁⠀⠀⠀
                    ⠀⠀⠀⠀⠀⡏⠀⠀⠀⠀⠀⠀⠀⠀⣠⠇⠀⠀⠀⠀⠘⠟⠁⠀⠀⢀⡎⠁⠀⠸⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠣⡀⠀⠀⠀⢀⡀⠴⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠙⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠈⠁⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⢄⣀⠀⠀⢀⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    protected  String levelUpImage1 =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⢀⣀⠴⡄⠀⠀⠀⠀⠀⢀⣀⡤⠤⢶⢐⠒⣮⣑⠒⣩⠙⠖⠤⠔⢡⠄⡗⠠⢴⢂⣱⡶⢊⡄⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠘⡄⠀⣨⣿⡦⡔⢊⡯⣍⣰⠛⢆⢻⠉⢀⡤⢿⠀⣟⠁⣸⠀⣼⣿⢀⣨⣷⠘⡿⠆⠁⡞⢉⣯⡑⡤⡀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⡷⢹⠛⡀⢰⠉⢿⣇⠘⠏⢳⣻⠎⣠⠆⣙⢚⣠⣍⣤⣜⣚⣧⢁⠘⢷⠻⣀⠷⡄⡼⢡⠏⣸⣣⡶⣍⠰⠄⣀⡄⠀⠈
                    ⠀⠀⠀⠘⢷⠘⣄⠛⡈⠳⠞⣨⠞⠛⠬⠭⠖⠁⠀⠀⠀⠀⠀⠀⣠⣼⠀⠀⠀⠀⠉⠉⠉⠑⠢⣘⡚⠱⠋⢠⠇⣼⠒⢈⡅⢠⡰
                    ⠀⠀⠀⠀⠀⠑⢌⣙⣁⠜⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢹⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠓⢍⡑⠚⡤⢊⣴⡏⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⣀⡨⠦⠷⠤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢗⠒⠋⠸⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣷⠖⠉⠀⠀⠀⠀⠀⠀⠈⠑⢤⣤⣶⣧⠀⠀⠀⠀⠀⠀⠀⠀⡆⣀⠀⣷⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠋⢿⡿⠃⢀⠄⠀⣼⠀⠀⠀⡤⠀⢠⡀⢻⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡛⢆⠀⠀⠀⠀⠀⠀⡼⠁⠀⣼⠃⠀⣎⢀⣴⣇⠀⠀⢀⣇⠀⠀⡇⢸⣿⠁⠹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⢠⠃⠀⠉⢒⡆⠀⠀⡸⠀⠀⢰⡟⢰⠀⡟⠉⠀⢻⡀⠀⢸⠙⢧⣸⠇⠀⣧⠀⠀⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⡀⠀⠀⠀⠀⠓⠠⡀⢰⠃⠀⠀⢰⠃⠀⠀⣿⣧⠄⣷⢿⠋⠒⠂⠳⡀⠹⠒⠦⡿⠀⠀⡗⠀⠀⢸⠀⠀⠀⠀⠀⠤⣀⠤⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠈⠾⠀⠀⢠⠃⠀⠀⢰⡿⠿⡆⢳⠀⠀⡤⠤⠤⠬⢿⡀⠾⡇⢸⣼⡇⠀⠀⢸⠀⠀⠀⠀⠀⠄⠀⠐⠀⠀⠀
                    ⠀⠀⠀⠀⣀⣠⢤⡄⠀⠀⣰⠃⠀⠀⠀⣼⠁⠀⠘⢿⣧⢄⣇⠀⠀⠀⢀⡇⣠⣇⡿⠛⠃⠀⠀⢸⡄⠀⠀⠀⠈⠀⠠⠈⠁⠀⠀
                    ⠀⠀⠀⠀⡄⠀⠀⣱⠄⡰⠁⠀⠀⠀⣠⣿⠀⠀⠀⠈⢻⡿⢿⣿⢿⡿⣿⣿⣿⣿⠀⡠⠔⠒⠒⣆⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠐⠚⢀⠜⠀⠀⠀⢀⡼⣽⣿⣇⠀⠀⠀⠘⣷⠎⡹⢲⣿⣇⡹⣿⡿⠊⠀⠀⠀⠀⡏⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡰⠁⠀⠀⠀⢀⡞⣹⣿⡿⢋⣦⡀⠀⣸⡏⣴⡇⢨⠁⢻⣷⡿⠁⠀⠀⠀⢀⡼⠃⠘⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⢀⡞⠀⡀⠀⢀⣠⠞⠀⠌⣹⠟⠉⠁⠈⠉⡽⠀⢿⠁⢸⠀⢸⠋⣇⠀⠀⣀⣴⣿⡃⠀⠀⠹⡀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⣼⠀⠄⠁⢠⡏⢀⢂⠉⣰⠏⠀⠀⠀⢀⣾⣇⣀⣬⣶⣇⠀⣸⣆⠈⠹⡟⠳⣽⣿⠁⠀⠀⠀⢱⡀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⢯⠀⠌⠆⡿⠀⠌⢠⡼⠋⠀⠀⠀⠀⠘⢍⠿⠿⡿⠿⣿⣿⣗⣻⣆⠀⢷⠠⠀⠄⢳⡄⠀⡄⠀⣇⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠈⠻⣄⡀⢿⡞⠚⠋⠀⠀⠀⠀⠀⠀⢠⡞⢍⣁⡿⢲⠈⢑⣾⠳⠞⠁⠘⣆⠉⠰⢸⠇⡠⢰⠀⡞⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠉⠚⠳⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⡶⠊⠀⢸⣦⣽⡇⠀⠀⠀⠀⠈⠓⢦⡟⠐⢁⣸⠴⠃⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠟⠉⠀⠀⠀⢸⣿⣿⠃⠀⠀⠀⠀⠀⠐⠟⠖⠚⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";

    protected String evolvingImage = """
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠐⠀⠐⠈⡙⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠣⡀⠀⠀⠀⠀⢰⠁⠂⠙⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡊⠀⠀⠀⠀⠀⠀⠀⠐⡄⠀⠀⠀⢆⠀⠀⠀⣱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠒⠆⠀⢀⠶⠋⠉⣁⣀⣀⡀⡴⢉⠖⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠲⠳⣄⡀⠀⡇⠀⠀⠀⢓⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠶⡄⠀⠆⠱⣮⢀⠀⠀⠀⣀⠿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⢀⠀⠀⢀⠠⠄⠤⠠⠄⠶⢧⡃⠀⠘⡄⢳⡎⡓⠤⡞⢉⡱⠒⠂⠒⠂⠒⠠⢄⠀⠀⢠⡀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⢸⣿⣧⡴⠋⠀⠀⠀⠀⠀⠀⢀⡈⠳⣦⢧⣸⠁⡇⢠⡟⠁⡀⠀⠀⡀⠀⡀⠀⠈⢷⣶⣿⣿⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⢠⠋⠙⢿⣷⡀⠀⢸⠀⠈⡇⠈⠈⢃⡀⠹⡆⠸⣀⢧⠏⣀⣠⠁⠀⢀⡃⠀⣱⠀⠀⣾⣿⠋⠉⢡⠀⠀⠀⠀
            ⠀⠀⠀⠀⡆⠀⠀⠘⣿⡇⠀⢸⡈⡽⠿⡀⠀⠿⠛⣄⠹⡄⢀⡎⣀⠞⠓⠇⠀⡜⠻⣅⡼⠀⢀⣿⡇⠀⠀⠈⡆⠀⠀⠀
            ⠀⠀⠀⢰⠀⠀⠀⠈⣿⣷⠀⢨⣏⣀⣠⣅⠌⢠⣀⣽⣱⣿⣾⣿⢹⠤⠤⠘⢲⡤⠤⢤⣧⠀⣸⣿⡇⠀⠀⠀⡇⠀⠀⠀
            ⠀⠀⠀⠘⠀⠀⠀⢠⣿⢿⣆⠸⠀⢠⠤⢀⣀⠀⠀⣸⣿⠃⠘⢹⡿⡀⠀⢀⠠⠄⢢⠀⡘⣠⣿⢿⠄⠀⠀⠀⠇⠀⠀⠀
            ⠀⠀⠀⠈⠀⠀⠀⡜⣿⠈⠉⣻⣧⣄⣢⣤⣮⣤⣶⣿⡌⢿⣿⠋⣷⣿⣶⡼⣶⣴⣧⣴⣿⡉⠁⢸⠘⡆⠀⠀⢣⠀⠀⠀
            ⠀⠀⠀⡐⠀⠀⣸⠁⣿⣤⢎⢁⠿⣜⢻⣟⣱⣿⣿⣿⣷⡘⣿⣸⣿⣿⣿⣷⣽⢿⢹⡒⢇⣩⣷⣼⡆⠹⡄⠀⠸⡄⠀⠀
            ⠀⡠⠒⢠⠆⢰⣧⣾⣿⣿⣿⠏⠀⠌⢸⢸⢹⠃⠈⠉⠝⢻⡿⠉⠁⠀⠈⡿⢩⠰⠀⠇⠈⢿⣿⣿⣿⡷⢷⡀⢂⠈⠢⡀
            ⢠⠁⢀⡎⠐⠻⣤⠹⣿⣿⡟⠀⢼⠂⡜⢎⠀⡇⠀⠀⢰⠈⣇⢼⠀⠀⢀⡇⢸⠹⡇⢸⠀⠘⣿⣿⠟⣠⠞⠓⠈⣆⠀⢳
            ⠘⡄⢸⢡⡄⠂⣬⣷⢼⣿⣄⣠⣰⣏⣁⣾⠀⢃⠀⠀⡞⠀⣿⠘⣆⠀⢸⠃⢸⣄⣨⣯⣄⣮⣏⠻⢶⡧⣄⠢⣅⢸⠀⡟
            ⠀⠙⣤⢸⡷⠋⠁⣼⣿⣍⣻⣛⠿⡿⢛⡉⣆⠈⢦⡼⢁⣰⠷⣄⠹⣾⠋⠀⠫⣙⣛⢟⣛⣻⣭⣿⣬⡽⠈⠙⣗⣠⠞⠀
            ⠀⠀⠀⠉⠓⠀⠈⠛⠙⠛⠛⢻⣤⣰⠏⠳⠺⠠⠾⠒⠋⠁⠀⠈⠉⠛⠂⠀⠀⠈⢣⣀⣿⠈⠙⠉⠉⠁⠀⠀⠋⠁⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    /* Action Image ==============================================================================================*/
    protected String feedingImage =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⠉⠈⠉⠉⢈⡝⠛⠛⠙⠉⢣⢽⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⢈⠟⠀⠀⠀⠀⢨⣞⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠉⠉⠉⠉⠉⠈⠉⠉⠉⠙⠓⠚⠁⢠⡞⠉⣷⠤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⢀⡤⠖⠶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠟⠉⠀⠀⣀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⡔⠋⠃⠀⠴⠷⣆⠀⠀⠀⠀⠀⠀⠀⡠⢒⡏⠀⠀⠀⠀⠀⠀⠀⠓⠪⣅⣀⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠳⣤⠆⠀⣄⣠⠞⠀⠀⠀⠀⠀⠀⠀⢇⢎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⣀⣠⡶⠟⢷⡀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠙⠦⠴⠋⠁⠀⠀⠀⠀⢀⣤⣄⠀⣈⠷⠔⠒⠒⠒⠒⠤⢄⡀⠀⠀⠀⠀⠀⠀⢸⠃⢉⠁⠀⠼⢷⡄⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⡿⠛⠉⠀⡠⠀⠀⠀⠀⠀⠀⠀⠈⣳⣶⣿⣷⡄⠀⠈⢳⡖⠀⢠⣀⣠⠇⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⢀⣾⣿⠟⠀⠀⠀⡸⠀⠄⠀⣰⠀⠄⠀⠀⣼⣿⣿⡿⠋⠙⢦⠀⠈⠳⠤⠞⠉⠁⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⢀⡼⠅⠰⠛⢒⣿⣿⠏⠀⠀⣰⠈⡇⠀⠀⢰⢳⠀⠀⡆⢼⣿⣿⡿⢀⡀⠙⢆⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠸⣄⡠⠀⢰⣾⢡⣏⡄⢠⠋⠁⠀⡇⠀⡰⠃⠤⣧⠀⡇⠘⣿⣿⠀⠈⡇⠀⠈⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⣀⡀⠀⠀⠀⠉⠒⠋⡏⠾⢙⡃⣦⡤⣔⡀⠘⣤⠁⣀⠀⠀⠳⠁⠀⢹⡿⠀⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⢠⡞⠁⢫⣦⣄⠀⠀⠀⢸⠀⠋⣼⠐⡇⠀⠀⠀⠀⠘⠌⠉⠓⠢⡜⠀⠀⣾⡇⠀⠀⠀⠀⠀⢨⣁⣀⣀⠀⠀⠀⠀⠀⠀
                    ⢰⠟⠁⠀⠀⠀⢰⡇⠀⠀⢸⠀⠀⣿⡇⢡⣀⠈⣒⠤⢆⠀⠀⠀⠾⠆⢀⣼⣿⠀⠀⠀⠀⠀⠀⢸⡗⠁⠹⡷⣤⡀⠀⠀⠀
                    ⠘⠦⣴⠂⠀⣻⠏⠀⠀⠀⢸⠀⠀⢸⡹⣏⢸⡿⠴⣧⣤⣄⣤⣤⡞⡠⠊⠉⡁⠀⠀⠀⠀⠀⠀⢸⠉⠀⠀⠀⣠⡇⠀⠀⠀
                    ⠀⠀⠈⠛⠚⠁⠀⠀⠀⠀⠐⠀⠀⠀⠇⢀⡾⢣⡔⢿⡋⣏⡽⢿⠋⠀⠀⠀⠅⠀⠀⠀⠀⠀⠀⢸⢶⠆⠀⣸⠋⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⢠⡿⠓⡿⢀⣿⣏⢹⣤⡏⠀⠀⠀⢨⠀⠀⠀⠀⠀⠀⠀⢸⠀⠙⠚⠁⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⢀⢾⠃⢸⢁⣾⣯⣿⡿⠋⡇⠀⠀⠀⡄⠀⠀⠀⢰⡀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⢀⣀⠴⣺⠁⠀⡤⢋⡎⠀⣸⠿⡛⢹⠋⠀⠀⢰⠀⠀⠀⡇⠀⠀⠀⢰⠑⣄⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⣀⠤⠒⠉⡁⠠⠘⠡⠔⢋⣀⡞⠀⢀⣿⣶⣷⣿⡄⠀⣀⣼⣦⣤⣠⡟⣄⠀⠀⠀⢧⠀⠙⠂⠹⢄⡀⠀⠀⠀⠀⠀⠀
                    ⠀⡞⣀⣀⢦⣡⣐⣿⣿⣿⣿⣿⡿⢁⡀⠸⢿⣿⡟⠻⡿⣿⣿⣍⣯⠕⢾⣿⠈⠢⣄⠀⠈⠳⡄⠁⠄⠂⠉⠓⠦⣄⠀⠀⠀
                    ⠀⠋⠁⠀⠀⠀⠀⠉⠛⠛⠛⠻⡅⠼⠓⠛⣤⣞⠃⡈⢱⡹⢿⣛⣛⠖⠁⠀⢣⠀⡀⠉⠒⠀⠠⢈⠠⠈⡐⠠⢀⠀⠳⣄⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣾⣿⣿⣦⠼⠉⠀⠉⠀⠀⠀⠀⠀⠀⠑⠤⣁⡂⢁⠂⠄⣠⣁⡤⠴⠦⠦⠤⣘⡆
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣻⣿⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠈⠁""";
    protected String groomingImage = """
             ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠶⣷⡄⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡏⠀⠘⠛⢳⡄⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⢩⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣤⣠⡴⠟⠀⠀⠀⠀
            ⠀⠀⠀⠀⣀⣠⣀⠀⠀⠀⠀⠀⠀⠀⠀⠐⢄⣦⠠⠄⠒⠈⠁⠉⠈⠁⠒⠠⢄⡀⢠⣶⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⣤⠖⠚⠃⠀⢹⣇⠀⠀⠀⠀⣴⣿⣿⡦⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⡿⠋⠙⢢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠐⣇⠀⠀⠀⠀⣸⡇⠀⠀⢀⡜⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⠈⣿⣿⡇⢀⠆⠀⢳⠀⠀⠀⠀⠀⠀⢀⣀⣄⠀⠀
            ⠀⠉⠳⠦⣤⡼⠋⠀⠀⢀⠎⠀⡿⠃⠠⠀⣴⠀⣀⣀⡀⠀⡖⠀⠤⢴⠊⠀⠀⢿⣿⣿⡁⠀⠀⠀⠘⡆⠀⠀⠀⠀⢰⠋⠀⢸⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⢠⠃⠰⠁⠀⢨⠀⠀⠀⠈⢱⣯⠀⠀⠀⣃⠀⠀⠸⣿⣿⡦⠀⠀⠀⠀⢳⠀⠀⠀⠀⢸⠀⠀⠈⠐⣤
            ⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⡇⠀⠀⠀⣠⡿⠗⠐⠒⠀⠸⡏⠓⢦⣄⠈⡇⠀⠀⢿⣿⡗⠀⠀⠀⡀⠘⡆⠀⠀⠀⠀⠙⠲⠤⠞⠁
            ⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⢸⢀⡇⠀⢺⣹⠀⠈⢆⠀⠀⠀⡇⠀⣀⣽⢧⣾⠀⠀⣸⣿⠇⠠⠔⢂⡇⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⣽⠞⠓⠒⠙⠛⠒⢤⣄⡑⢤⣠⣾⣏⠉⠀⠈⠉⠉⠒⠿⠯⠤⠴⠴⠚⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡇⡀⠸⠄⠀⠀⠀⠀⡰⠛⣷⠀⡴⢙⡟⠋⢑⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠂⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡇⠘⠂⡀⠡⠈⠄⣡⡇⢠⡉⢦⢡⠎⢀⣴⣿⡿⣷⡠⣀⠀⠀⠀⠀⠀⠀⢀⡔⠀⣸⠆⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⢳⡀⠂⠐⣀⣵⣾⣿⣧⡜⣇⠸⢸⠀⣿⣿⣯⣙⢾⡿⣄⠉⠒⠦⠤⠴⠒⠋⠀⣰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⣿⣿⣿⠿⢋⡽⠋⢀⠈⡆⣿⣿⣿⣿⣯⣹⣿⠷⢄⣠⢀⡄⣐⡤⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣭⣴⡾⠋⠁⠀⢸⡀⠈⠉⠛⠿⣿⣿⣿⡿⠃⠀⠀⠉⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠜⠁⣰⠃⠀⠀⢸⣿⡄⠀⠀⠀⠀⠈⢙⠙⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⠀⣲⠁⠀⠀⢀⡿⣿⡋⠢⡀⠀⠀⠀⠀⡇⠈⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡎⢀⠰⡇⠀⠀⠀⣾⡁⢻⣀⣀⠽⢦⠀⢄⠀⠸⢀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠠⢸⠀⡀⠀⡼⢿⣷⣿⣿⣷⣄⣀⣷⠀⠠⢈⠀⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧⠐⠘⠀⠁⡼⠉⠛⣉⣉⡹⣏⣙⣛⣿⠇⢀⠂⢠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⡈⠠⢱⠃⠀⠀⣼⣍⣐⡟⠀⠀⡽⢀⣠⡶⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠒⠬⠇⠀⢠⣿⣿⣿⠁⠀⠘⢳⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡇⠀⠀⠀⢘⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⠂⠀⠀⠀⠸⣛⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";

    protected String healingImage = """
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠔⠉⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠘⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠌⢀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠄⠀⢢⠀⠀⠀⣞⣢⠐⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠒⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠑⠚⢀⠠⡼⠃⠙⢦⣤⠀⢁⠀⠀⠀⠀⣠⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡗⢻⣄⢀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢶⡀⣠⠞⠓⠣⠴⠀⠀⠀⢸⢸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠲⣄⡰⠁⡐⠀⠀⠀⠀⠔⢁⡸
            ⠀⠀⠀⠀⠀⠀⠀⣠⢆⠀⠀⠀⠀⢷⡉⠀⠀⠀⠀⠀⠀⠀⣈⠢⠧⠤⠤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⢫⡀⠊⠀⠀⡀⠈⢀⠔⠁⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⢸⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡶⠂⠉⠀⠀⠀⠀⠀⠀⠈⠑⠤⣼⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⡠⠐⠁⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⡐⠀⠘⠓⣦⢤⡀⠀⠀⢀⡔⣻⠋⠀⠀⢀⣸⠀⠀⠀⣆⡀⢀⠀⡀⠙⣿⠷⡄⠀⠀⠀⠀⠘⢠⡾⢧⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠉⣇⣸⠋⠁⣀⡀⠀⠀⡞⢠⠃⠀⡇⠉⣼⣻⠀⠀⠠⡟⣄⣀⠸⡁⠀⠘⡆⠙⡄⠀⠀⢠⣴⡚⠁⠈⣳⣄⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠸⠧⠤⠄⠀⠇⠀⠸⠀⡼⠀⠀⣧⡞⣽⣿⢆⠀⠀⡇⣽⣿⢿⡄⠀⠀⡇⠀⢣⠀⠀⠀⠀⠈⢧⡼⠁⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⡥⡀⠀⠀⠀⠀⡇⠀⣿⡀⢄⡿⠰⣟⣻⠌⠓⣄⣿⣋⢻⣶⣇⠀⢸⠇⠀⢸⠀⠀⠀⠀⢎⠉⢉⠀⠀⠀⠀⡆
            ⠀⠀⠀⠀⠀⠀⠀⢀⡶⣣⠀⠀⠀⠀⡇⠀⣿⡇⢸⣿⠆⠉⢠⠄⠐⠠⠬⢅⠉⠋⡅⢠⡿⠀⠀⠈⡆⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠁
            ⠀⠀⠀⠀⠀⠠⠺⣯⡀⢨⡏⠆⠀⢀⠁⠀⠛⢽⣨⡿⠆⠀⠘⢤⠻⠳⣠⠂⠀⣰⢡⡞⠁⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠘⠁⢙⡎⠀⠀⠀⡸⠀⠀⠀⠘⢻⣷⣤⣼⡗⢹⣴⣤⡇⣶⣾⣿⢿⡇⠀⠀⠀⠀⢱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠂⠄⠂⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⡏⣿⣿⣿⡇⢸⣿⣿⡇⣽⣿⣿⢿⡆⠀⠀⠀⠀⠈⢇⠀⠀⠀⢀⠄⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠠⠂⠢⠀⡠⠃⠀⠀⢀⠀⢸⢱⠏⠻⣿⣧⢸⣿⣿⡇⣿⣿⠏⠈⣇⠀⠀⢳⠀⠀⠈⢦⠀⠀⠐⠄⠀⠠⡀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠈⢀⠔⢁⡜⠁⠀⠀⠀⡞⠀⡎⣸⠀⠀⢈⡿⣾⣧⢻⣞⣿⡅⠀⠀⢯⡄⠀⢈⡇⠀⠀⠀⠑⡄⠀⠀⠐⢀⠀⠢⠀
            ⠀⠀⠀⠀⠀⢀⠔⠁⢠⠋⢰⠀⠀⠀⢀⡇⠘⢠⠏⠠⠴⣿⣉⢻⡋⢐⡏⣨⣿⣄⠀⠸⡜⠀⠀⢹⠀⠀⠀⠀⠈⣆⠀⠀⠀⠀⠒⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⡞⢀⢸⠀⠀⠀⢸⠂⢤⠟⠀⠀⠑⢝⠳⢿⣿⣿⣿⠿⣽⢿⠇⠀⠙⣤⠁⢸⡃⠀⠀⠀⢀⢿⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⢻⠀⠈⢧⠀⠀⢸⡴⠋⠀⠀⠀⠀⠀⢹⠉⠃⣖⠘⢲⡟⠉⠀⠀⠀⠈⠲⡼⠀⠀⠀⣠⠎⣼⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⢤⣀⠓⠄⡈⢦⡀⠀⠀⠀⠀⠀⠘⣦⣤⣿⣤⣾⠀⠀⠀⠀⠀⢀⡤⠃⢀⠔⣊⣡⠜⠁⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠑⠋⠉⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠈⠉⠙⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    // Problem Image =============================================================================================*/
    protected String sickImage =
            """
                                                                        \s
                                                                         \s
                                                                         \s
                                                                          \s
                                                                          \s
                                                                          \s
                                                                          \s
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                          \s
                    ⠀⠀⠀⠀⠀⠐⠢⢾⠐⡄⢣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢊⡍⢉⡐⡡⠌⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⢣⠘⡄⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠡⢊⡰⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⡐⠆⣳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠢⣘⡠⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢚⣭⣤⡄⠀⠀⡴⠚⢦⡀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣟⣿⣿⡦⠔⢧⡜⠉⠉⣉⠉⠉⠑⠒⠤⣼⣿⣿⣿⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠊⢸⡿⠋⠁⠀⠀⠀⠓⠀⠀⡀⠙⠦⢀⠀⠀⢺⣿⣿⡏⠉⠉⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⢀⠞⠁⡐⠀⠀⠙⠓⠒⠊⠁⠉⠀⠀⠀⠑⠀⢸⣿⣿⣧⠀⠑⣄⠘⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⡌⠀⢀⠎⠀⢠⠃⢀⡴⢟⠀⠀⡀⢰⡆⠈⠁⠀⠀⠀⠀⡿⣿⣿⠇⠀⢸⠀⠈⢻⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡘⠀⠀⡜⢀⠀⣸⡸⠫⢀⣨⠀⠀⠄⡸⠘⣆⠀⠀⡆⠀⠀⣆⣉⣻⠀⠀⠀⠃⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⣀⠴⠁⠀⢰⣧⡏⠀⡟⠧⡀⠀⠀⢣⠀⠀⡏⠉⠈⢢⣸⠁⠀⢀⣿⢿⣿⠀⠀⠀⠀⠀⠀⠈⣇⢀⡠⠤⠼⢯⡉⠑⢦⠀
                    ⠀⡠⠒⠉⠀⠀⠀⠀⠸⡁⡇⠀⡇⠀⠈⠲⣆⠀⠳⢬⣧⠴⠒⠉⡝⠀⠠⢸⣿⣾⣿⠀⠀⠀⠀⠀⠀⠀⠘⣇⠠⠐⠀⠂⡁⠄⡀⢷
                    ⡞⠀⠠⠀⠀⠀⠀⠀⠀⠀⢷⢸⠁⠒⠊⠉⠀⠀⠠⡾⠿⢷⠤⣼⢴⡎⢀⣿⣿⡟⢹⡇⡀⠀⠀⡀⠀⠀⠀⠙⢦⣀⠁⠀⠀⠀⠀⡾
                    ⠳⢌⣀⣐⣠⣀⣄⣀⣀⣬⣼⣎⣦⣀⡀⢐⠉⠉⠘⠻⣤⣼⠉⣿⣾⣴⣿⠛⢉⣇⠤⢷⡙⢦⠀⠱⣄⠀⠀⠀⠀⠀⠀⠀⢀⣠⠞⠁
                    ⠀⠀⠀⠉⠀⠀⢀⣠⠾⠟⠻⣿⣿⠟⠉⠉⠉⠁⠀⠛⣿⣿⡄⢹⣿⣿⡿⠖⠋⠀⠀⠀⠉⠲⠴⠦⠜⠓⠒⠒⠒⠒⠊⠉⠉⠀⠀⠀
                    ⠀⠀⠀⠀⢠⡮⠵⠿⠤⠼⠖⠋⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⣼⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    protected  String sadImage =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠟⢙⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⠀⢀⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣎⣠⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⢚⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣤⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣶⣖⣀⣀⡬⠯⠥⠴⠦⠤⠤⢄⣀⠀⠀⠀⣀⣀⡀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⡿⠛⠉⢀⡄⠀⠀⠀⠀⠀⠀⠀⠈⠉⠓⢶⣿⣿⣷⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠁⠀⠀⣠⠏⠀⢀⣀⣀⣀⠀⠀⠀⠀⠒⣄⣼⣿⡿⠛⠳⣄⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠋⠀⠀⠀⢠⣃⠀⠀⠀⢇⠀⠀⠀⠀⢤⠀⠀⢸⣿⣿⠃⠀⠀⠈⢧⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠁⢀⣀⢀⣴⣿⠀⠀⠀⠀⣸⣄⠀⢀⣀⣸⠀⠀⢾⣿⣿⠀⠀⠀⠀⢸⡇
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⡁⠀⠀⢠⠏⠀⢯⠀⠀⠀⠀⡏⢳⡀⠀⠀⡭⠀⠀⠈⢻⣿⠀⠀⠀⠀⠀⠃
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣁⡇⠀⣴⣧⣤⣴⣾⡄⠀⠀⢸⣭⣽⣷⣦⣴⡧⠀⠀⠀⢠⡇⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⢻⠁⢰⡇⡿⠛⢻⠀⠳⡄⠀⠊⢸⠛⠛⣻⡿⠀⠀⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠘⡇⢸⠁⠙⠓⠋⠀⠀⠙⠶⡆⠈⠙⠋⡹⠁⠀⠀⠀⣼⣿⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⣰⠋⠉⠈⠉⢢⡀⠀⠀⠀⠀⣇⠘⣆⠀⠀⠀⠠⠴⠤⠀⠀⠀⢀⣜⣁⣠⠞⠁⣴⢻⡏⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⣧⠀⠀⠀⠀⢸⡄⢸⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⢀⣾⣷⣾⡇⠀⠀⠀⠀⠀⠀⠀
                    ⠀⡴⠋⠉⠓⣤⡄⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⣸⢷⣤⠙⠃⠉⠳⡦⢤⣀⣀⠀⣀⣠⣾⣾⣭⡽⠛⢹⠇⠀⠀⠀⠀⠀⠀⠀
                    ⢸⠁⠀⠀⠀⠈⠇⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⣿⢧⡉⠀⠀⠀⢸⣿⣿⣏⣽⡟⠉⣻⣇⠀⠙⠁⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀
                    ⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡉⠀⠀⠀⠀⡇⠀⠉⠘⢦⡀⢨⣿⠟⣿⢤⡿⠟⡿⠹⡆⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠃⠀⠀⠀⠀⠈⣾⡏⢰⠃⠀⡷⣶⡇⠈⣷⡀⠀⠀⡇⠀⢀⠃⢸⠀⠀⠀⠀
                    ⠀⠰⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠇⠀⠀⠀⢰⡇⠀⠀⠀⠀⣼⣼⠀⡎⠀⢰⠀⠀⣏⣁⣹⡇⠀⢸⠃⠀⢰⠀⠘⡄⠀⠀⠀
                    ⠀⠀⠙⡄⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀⢸⠁⠀⠀⢀⣾⣿⡇⣼⣅⠀⠘⠀⠀⣿⣿⣿⣧⠀⣸⠀⠀⡞⠀⠀⢱⠀⠀⠀
                    ⠀⠀⠀⠘⡆⠀⠀⠀⠀⡀⠀⢸⠀⠀⠀⠀⠘⠀⠀⢀⣾⣿⣿⢰⣿⡧⠀⢠⠀⠀⣿⣿⣿⣿⡄⡏⠀⠀⡇⠀⠀⠈⡇⠀⠀
                    ⠀⠀⠀⠀⢹⠈⠠⢒⠂⠀⠀⣾⠀⠀⠀⠀⢀⠀⢠⣾⣿⣿⡇⠘⣇⠀⠀⣸⠀⠀⣿⡏⠿⣿⣷⠃⠀⢸⣧⠀⠀⠀⠀⠀⠀""";
    protected String hungryImage =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡃⠀⠀⠇⠀⠀⠀⡃⠀⠀⠀
                    ⡀⠀⠀⠀⠀⡇⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠅⠀⠀⡇⠀⠀⠀⡅⠀⠀⠀
                    ⠆⠀⠀⠀⠀⡆⠀⠀⠀⡇⠀⠀⠀⠀⠃⠀⠀⠀⠀⡠⣄⠀⣀⡠⠤⠤⠤⠄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠇⠀⠀⠀⠆⠀⠀⠀
                    ⡃⠀⠀⠀⠀⡅⠀⠀⠀⡇⠀⠀⠀⠀⠃⠀⠀⠀⠀⣥⡟⠉⠀⠀⠀⠀⠀⠀⠀⠈⠓⢤⣾⣧⠀⠀⠀⠀⠀⠀⠀⠀⡃⠀⠀⠀
                    ⡅⠀⠀⠀⠀⡃⠀⠀⠀⡇⠀⠀⠀⠀⠁⢀⣤⣤⠊⠈⣷⡀⠀⠀⠀⠀⠀⠠⠤⠀⠈⢸⣿⣇⣀⡀⠀⠀⠀⠀⠀⠀⡅⠀⠀⠀
                    ⠂⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⢸⡿⠁⠀⢰⠁⠈⠁⠀⠀⡀⣌⠇⠀⠐⠀⢸⣿⣿⠀⠉⠓⡄⠀⠀⠀⠀⠆⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠁⠀⠀⣿⠀⠤⠤⢴⡎⠉⠀⠀⠰⡄⠀⠀⣿⣿⡀⠂⠀⠸⡀⠀⠀⠀⠁⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⢠⠒⠒⠲⡄⠀⠀⠀⢸⡥⠀⠀⢠⠛⡄⠀⠀⠸⡷⣄⠀⠀⠀⡧⠀⠀⣽⣿⡇⡸⠀⠀⢣⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⢠⠒⠓⢦⢸⠀⠀⠀⡇⠀⠀⠀⣏⡞⠀⢀⠏⠀⠱⡀⠘⢆⢇⠈⠷⠦⣴⣟⠀⠀⣞⣿⠇⡇⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⢹⠀⠀⠀⠻⡄⠀⠀⡇⠀⠀⠀⡇⣏⠀⢸⡆⠤⠐⠛⠢⢄⣈⣆⣀⠀⠀⡼⠀⠀⣿⣿⢰⠃⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⢣⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⡇⢯⣾⠀⢹⡀⣄⣀⠶⠀⠀⠉⠛⠛⠉⠟⡇⢠⣯⡏⡎⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⢣⠀⠀⠀⠀⠀⡇⠀⠀⠀⠇⢸⡟⢧⠈⢧⣀⠀⢀⣜⠊⣉⡀⣠⣴⣿⣧⠞⢸⠁⠀⠀⠀⠀⠀⢱⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⢃⠀⠀⡀⢀⠇⠀⠀⢀⠂⠈⠁⠻⡳⣼⣿⣿⠋⠹⣿⣿⢿⣛⡟⢻⡁⠀⡼⡐⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠸⠘⣀⠙⢺⠀⠀⠀⢸⠀⠀⠀⠀⠉⢸⣿⢹⣷⣿⠉⡓⢸⡽⣷⣀⢧⣀⡇⠁⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡁⠀⠁⢸⠀⠀⠀⡆⠀⠀⠀⠀⠀⣿⣿⢸⣿⡇⢠⠃⢘⡀⢸⣶⣾⣿⠁⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠄⠀⠀⢸⠀⠀⢀⠃⠀⠀⠀⠀⠀⢃⢻⣼⡿⢀⣿⠂⠰⠀⠐⣿⣿⣿⡀⠅⠀⠀⡀⠀⠀⠸⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⡂⠀⠀⡆⠀⠀⡜⠀⡰⠀⠀⡄⠀⢌⠀⣿⣧⠀⢣⣀⡼⠁⠀⣿⣷⣿⣷⡆⠀⠀⣇⠀⠀⠈⡇⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠁⠀⠀⡇⠀⡰⠁⡼⠁⠀⠀⡿⠀⡇⢰⡟⠿⣷⣿⣿⣷⣶⣾⣿⠟⢋⢽⢧⠀⠀⡿⡀⠀⠀⢇⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⣼⠀⠀⠀⣇⠔⢡⠞⠁⠀⠀⣸⠇⢰⠁⣸⠉⠹⡗⠂⢿⣭⠭⠝⢺⣿⣻⠉⠈⣇⠀⠀⢣⠀⠀⠘⡄⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⢹⡅⠒⠊⠁⠀⠀⠀⠀⠀⣠⠇⠀⠄⣠⠇⠀⠀⣧⣠⣽⣿⣇⣒⣺⠁⠘⡇⢀⠘⢆⠐⡀⠳⡀⠀⠙⣄⠀⠀⠀
                    ⠀⠀⠀⠀⠀⡄⠈⠑⢤⣀⣀⣀⣀⣤⠞⣁⣀⡬⠖⠉⠀⠀⠀⢹⣿⣿⣠⣿⣿⡏⠀⠀⠘⢦⡀⠈⠳⢥⡀⠉⠦⡄⠀⠉⠒⣤
                    ⠀⠀⠀⠀⠀⠣⣀⣀⡼⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣺⣿⣿⠇⠀⠀⠀⠀⠈⠲⠤⠦⠽⠷⠖⠒⠒⠚⠉⠀
                    ⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠛⠉⠻⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    // 생성자.
    MikuLv1(){
        super();
        this.commandNameList.set(0,"충전시키기");
        this.commandNameList.set(1,"놀아주기");
        this.commandNameList.set(2,"치료하기");
        this.setCurrImage(this.spawnImage);
        this.setCurrMessage("미쿠가 탄생했습니다.");
        this.eventType = EventType.SPAWN;
    }

    /* States =================================================================================================*/
    @Override
    void triggerSicknessProblem(){
        setCurrImage(sickImage);
        setCurrMessage("미쿠가 아픈 것 같습니다.");
    }
    @Override
    void triggerHappinessProblem(){
        setCurrImage(sadImage);
        setCurrMessage("미쿠가 심심해 합니다.");
    }
    @Override
    void triggerSatisfactionProblem(){
        setCurrImage(hungryImage);
        setCurrMessage("미쿠가 배고파하는 것 같습니다.");
    }
    @Override
    void setNormalPetState(){
        setCurrImage(this.defaultImage);
        setCurrMessage("미쿠가 기다리고 있습니다.");
    }

    @Override
    public String getDontKnowImage() {
        return dontKnowImage;
    }

    /* Actions ===========================================================================================*/
    @Override
    void feeding(){
        this.setCurrImage(this.feedingImage);
        int statIncreaseAmount = randomIncreaseValue(super.EXP_INCREASE_PARAM) + 5;
        exp += statIncreaseAmount;
        satisfaction  += randomIncreaseValue(super.STAT_INCREASE_PARAM)+1;
        this.setCurrMessage("미쿠를 충전해 줬다. (+" + String.valueOf(statIncreaseAmount) + " exp)");
    }

    @Override
    void grooming(){
        this.setCurrImage(this.groomingImage);
        int statIncreaseAmount = randomIncreaseValue(super.EXP_INCREASE_PARAM) + 5;
        exp += statIncreaseAmount;
        happiness += randomIncreaseValue(super.STAT_INCREASE_PARAM)+1;
        this.setCurrMessage("미쿠와 놀아주었다. (+" + statIncreaseAmount+ " exp)");
    }

    @Override
    void healing(){
        this.setCurrImage(this.healingImage);
        sickness = false;
        this.setCurrMessage("미쿠를 치료했다.");
    }

    /* Special Event ===========================================================================================*/
    @Override
    Set<EventType> getSupportedEvents() {
        return Set.of(EventType.EVOLVE, EventType.LEVEL_UP);
    }

    @Override
    boolean checkSpecialEvent() {
        if (checkLevelUp()){
            eventType = EventType.LEVEL_UP;
            if(checkEvolve()){
                eventType = EventType.EVOLVE;
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    void handleSpecialEvent(){
        switch (eventType){
            case EVOLVE -> levelUpEvent();
            case LEVEL_UP -> levelUpEvent();
        }
    }

    @Override
    void levelUpEvent(){
        this.setCurrImage(this.levelUpImage1);
        level+=1;
        this.setCurrMessage("미쿠가 Lv."+level+"이 되었습니다 !!");
        exp = 0;
    }


    /* 고유 Events ===================================================================================================*/
    protected boolean checkEvolve(){
        return this.level == 2;
    }
    @Override
    Pet createNewPet() {
        return new MikuLv2();
    }
}


class MikuLv2 extends MikuLv1{
    protected String singingImage= """
            ⠀⠀⠀⡀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⡠⢊⡅⠘⢃⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠰⢡⡞⠳⠄⢸⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⡜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⡇⠴⠶⡶⠀⢻⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⣠⠄⠈⣳⠼⠔⠒⠒⠤⢤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠈⠑⢆⣃⣽⣉⡴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣐⣿⣿⠟⠉⠀⢀⠖⠁⠀⠀⠀⠀⠙⢶⣀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⡔⢊⢙⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⣿⠟⠁⠀⢀⣠⠋⠀⠀⢀⡀⠀⠀⠀⠀⢙⣿⣿⡆⠀⠀⠀⠀⠀
            ⢠⠇⠸⣤⡤⠄⡣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡽⠁⣰⣠⠔⢋⠇⠀⠀⢀⡏⠀⠠⠀⠀⠀⣸⣿⣿⣇⠀⠀⠀⠀⠀
            ⠸⡀⢻⠉⣙⣠⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡤⠀⡏⠀⠀⢸⠀⠀⣠⠋⢣⠀⠀⣠⠆⠀⣿⠋⠀⠈⢆⠀⠀⠀⠀
            ⠀⢃⠺⠶⠤⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⢠⠇⡜⠈⠓⠶⠔⢇⣰⠁⠀⠘⢧⡴⠁⠀⣸⣿⠀⠀⠀⠘⡆⠀⠀⠀
            ⠀⠀⠉⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⣸⢀⣧⣀⡀⡠⢄⣈⠃⠮⣄⣀⠎⠀⠀⡴⢛⡏⠀⠀⠀⠀⣇⠀⠀⠀
            ⠀⠀⡠⠤⠖⠢⡄⠀⠀⠀⠀⠀⠀⠀⠀⡘⠀⡿⠻⣾⣯⣿⣇⠀⢀⡱⣠⣜⣅⢀⣠⣮⣤⡿⡇⠀⠀⠀⠀⡹⠀⠀⠀
            ⢀⠎⣴⠃⢲⡇⡇⠀⠀⠀⠀⠀⠀⠀⢠⠁⣼⣤⣴⣿⣽⢿⡿⣿⣧⣴⣤⣴⣶⣟⠻⠋⠀⠀⡇⠀⠀⠀⠀⢱⠀⠀⠀
            ⡏⡾⢛⠣⣰⡇⡇⠀⠀⠀⠀⠀⠀⠀⠆⣼⣿⡿⣽⣿⣧⠟⡿⣙⡧⣤⣟⠉⠻⢷⣦⣄⣀⢰⡃⠀⠀⠀⠀⠸⡀⠀⠀
            ⠓⢤⣿⣲⡜⣃⡇⠀⠀⠀⠀⠀⠀⡜⠰⣿⣟⣾⡿⣿⠃⡜⠀⡟⠖⢨⡟⠳⣿⣼⣿⣿⣿⣿⠓⡟⡆⠀⠀⠀⣇⠀⠀
            ⡖⣀⣉⠃⣤⠸⡄⠀⠀⠀⠀⠀⣸⠁⠀⢩⡉⠁⣰⣿⣾⠀⢸⠀⠀⢰⠁⠀⠈⠙⢿⣿⣿⡏⢰⠛⠁⠀⠀⠀⢸⠂⠀
            ⠱⡤⢹⠃⣽⠖⢸⡀⠀⠀⠀⢰⢃⠀⠀⠀⠀⣰⡏⣻⠋⢀⡏⠀⠀⡜⠀⠀⠀⠀⠀⠙⢿⣶⠋⠀⠀⠀⠀⠀⠘⡆⠀
            ⠀⢡⢈⡀⠉⢼⡁⠀⠀⠀⢀⡏⢸⡀⠀⠀⢠⣿⣿⣿⣿⣟⢤⣠⣤⣧⣀⠀⠀⠀⠀⠀⠀⢸⠀⠀⡀⠀⠀⠀⠀⢳⠀
            ⠀⠀⡘⠷⠖⢂⡅⠀⠀⠀⢸⡇⠨⡇⠀⠀⠙⢾⣛⣻⡟⠛⠛⣿⣻⡽⢾⠀⠀⠀⠀⠀⠀⢸⠀⠀⡃⠀⠀⠀⠀⠈⣆
            ⠀⠀⠈⠉⠉⠀⠀⠀⠀⠀⠈⢧⡀⢻⡀⠀⣸⠀⣧⠀⢈⣿⡏⠀⢈⣿⠁⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⢹
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⣘⠂⢽⡿⢷⣤⣤⡏⢻⣌⣤⣿⠀⠀⠀⠀⠀⠀⠀⠘⣆⠠⢁⠀⠀⠀⠀⠀⣼
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠚⠿⣿⣿⣿⡆⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠙⢆⣹⠀⠀⠀⢀⡴⠋
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⡇⠀⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⢃⣀⠤⠖⠉⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠫⠽⠆⠀⠘⠺⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀""";

    protected String dancingImage = """
            ⠀⠀⠀⠀⠀⢀⣠⣤⠾⠶⡒⠒⢶⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⣠⡾⠛⠉⠀⣀⡀⠑⡄⠀⠀⠉⠌⠵⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⢠⣾⣋⡤⠐⢻⠉⠀⠀⠀⠹⡄⠀⠀⠈⢆⠈⢿⣤⣀⢀⣴⡆⠀⠀⠀⠀⠀⣠⣄⣤⣖⡀⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⢠⡿⠉⠈⠁⠀⢀⣄⠠⠄⠐⠐⠻⡀⠀⠀⠘⣄⡈⢻⣿⡍⣙⣦⣄⠀⠀⠀⠀⠹⣿⣷⡿⢻⡎⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⣾⠁⣀⢰⡖⠊⠉⠇⠀⠀⠀⠀⠀⢣⠀⠀⠀⢿⠃⠈⢿⡽⠁⠈⠉⠀⠀⠀⠀⠸⡶⣿⣶⢟⠜⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⣿⠛⣷⠾⠀⠀⠀⢰⠀⠀⠀⠀⢀⣈⡤⠔⠚⢼⡇⢀⡯⠥⠖⠒⠒⠤⢤⣀⠀⢠⣽⡾⠓⠋⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⡄
            ⡷⠒⢾⠀⣀⢀⣀⠤⡶⠐⠂⠉⠁⠀⢣⢠⣾⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠈⢙⣿⣿⠟⠓⢄⠀⠀⠀⠀⢀⡠⣴⠾⣵⢏⠇
            ⠸⣾⠉⠉⠹⡀⠀⠀⠸⡀⠀⠀⠀⠀⡼⣿⠟⠁⡐⠀⠀⠀⠀⠀⢠⠀⠀⠀⣸⣿⠁⠀⠀⠀⢳⡀⠀⠀⢨⢾⣿⣿⢏⡆⠀
            ⠀⠹⣧⡀⠀⠱⣄⠀⠀⣱⡀⠤⠔⢾⢁⠏⠀⠀⡁⠀⠀⢠⠀⠀⠈⡄⠀⠀⢿⣿⠀⠂⠀⠀⠀⢇⠀⠀⠀⠛⠽⠍⣲⠇⠀
            ⠀⠀⢻⠻⣦⣤⡟⢆⠀⠀⠱⡀⢀⠇⡜⠀⢀⡜⡇⠀⠀⢘⠳⠤⣔⢇⠀⠀⠈⢿⡄⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⢀⡈⢿⠛⠦⠴⠦⠤⠼⡞⠀⠃⢀⡏⠁⠙⢄⠀⢸⠀⠀⠀⢻⠀⠀⢸⣾⡇⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠁⠈⠺⣷⠀⠀⠀⠀⢰⠁⠀⣀⡜⢇⣠⠤⠌⠣⣼⡀⠋⠉⣟⠀⠀⢸⣿⡇⠀⠀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⢸⠿⢇⢘⠀⠀⢠⠔⠒⠂⢢⠰⠛⠇⠀⣿⠿⡇⠀⠀⠀⠀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀
            ⠀⠴⣤⠤⡀⠀⠀⠀⠀⢠⠃⠀⠀⠳⣀⣈⠛⣦⣀⣀⠣⠀⠀⠜⠀⣠⡆⡸⠁⠀⠇⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀
            ⠀⢸⡹⣷⣎⠆⠀⠀⡰⠃⠀⠀⠀⠀⣼⡿⢰⣿⣿⢏⡏⣩⣟⣧⠞⢿⡿⠁⠀⠀⢸⠐⡀⠀⠀⠀⠀⠀⠸⡄⠀⠀⠀⠀⠀
            ⠀⢾⣶⣿⡀⠃⠀⡐⠁⠀⠀⠀⠀⡀⠹⠧⣾⠿⠛⠛⠉⢻⠙⡈⣷⠃⢼⡀⠀⠀⠀⡇⢘⣤⣄⣀⣴⣷⡀⠘⣄⠀⠀⠀⠀
            ⠀⠪⠿⠿⠃⠀⡰⠁⠀⠀⠀⠀⢰⠀⠀⢰⠃⠀⠀⠀⠀⠘⢆⣦⠋⢙⣿⡿⣄⣀⣀⣰⣼⣿⣿⡟⠉⣿⠇⠀⠈⢂⠀⠀⠀
            ⠀⠀⠀⠀⠀⢀⠇⠀⠀⢠⡀⠀⠄⠀⢠⠇⠀⠀⠀⠀⠀⢀⣼⣋⠒⠺⢿⣿⣤⣽⠿⣽⣿⢳⣿⣶⡾⠃⠀⠀⠀⠀⢇⠀⠀
            ⠀⠀⠀⠀⠀⠸⡀⠀⠀⠘⠇⠀⠀⡰⠃⠀⠀⠀⠀⠀⠀⢸⡀⣀⣿⣶⣤⣉⡿⢛⡶⠁⠀⢣⡉⠉⠀⠀⠀⠀⠀⠀⢈⠀⠀
            ⠀⠀⠀⠀⠀⠀⢣⠀⠀⠀⠈⠀⡼⠁⠀⠀⠀⣤⡤⠀⠀⣼⡺⢍⣉⣟⡉⠭⣷⣏⣀⣀⣀⠀⢱⠀⢀⠜⠀⠀⠀⣠⠋⠀⠀
            ⠀⠀⠀⠀⠀⠀⠄⠑⠄⡀⠀⠀⢇⠀⠀⠀⣰⠃⠀⠀⠀⣿⣌⣹⠏⢸⡃⣈⡽⠀⠀⠀⠘⣤⠞⠐⢁⣀⣤⡔⠊⠀⠀⠀⠀
            ⠀⠀⠀⠀⠶⠃⠀⠀⠀⣨⠋⠀⠛⠂⠀⣰⠋⠀⠀⠀⢸⣿⣿⠏⠀⢀⣿⣿⡇⠀⠀⠀⠈⠹⡁⠈⠁⠀⠈⠻⣦⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⣀⣠⠞⠁⠀⠀⠀⠀⣸⠁⠀⠀⠀⢠⣿⣿⡟⠀⠀⢸⣿⣿⡇⠀⠀⠀⠀⠀⢳⣄⡀⠀⠀⢀⣙⣇⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠟⠁⠀⠀⠀⠀⠀⡰⠥⠤⠤⠤⠤⠈⢹⠯⠤⠤⠄⠼⠿⠟⠇⠀⠀⠀⠀⠀⠀⢿⢇⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⡔⠁⠀⠀⠀⠀⠀⢀⣜⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠈⢞⡄⠀⠀⠀⠀⠀⠀⠀""";

    MikuLv2() {
        super();
        level = 3;
        this.setCurrImage(evolvingImage);
        this.setCurrMessage("미쿠가 특별한 능력을 얻었다! ");
        this.eventType = EventType.EVOLVE;
        commandList.add(this::danceTraining);
        commandList.add(this::vocalTraining);
        commandNameList.add("춤 연습 시키기");
        commandNameList.add("보컬 트레이닝");
        commandCount =5;
    }

    @Override
    Set<EventType> getSupportedEvents() {
        return Set.of(EventType.LEVEL_UP);
    }

    void danceTraining(){
        this.setCurrImage(this.singingImage);
        int statIncreaseAmount = randomIncreaseValue(super.EXP_INCREASE_PARAM*2) + 5;
        exp +=statIncreaseAmount;
        happiness += randomIncreaseValue(super.STAT_INCREASE_PARAM)-7;
        satisfaction += randomIncreaseValue(super.STAT_INCREASE_PARAM)-3;
        this.setCurrMessage("미쿠가 춤을 연습한다. (+"+statIncreaseAmount+" exp");
    }

    void vocalTraining(){
        this.setCurrImage(this.dancingImage);
        int statIncreaseAmount = randomIncreaseValue(super.EXP_INCREASE_PARAM*2) + 5;
        exp +=statIncreaseAmount;
        happiness += randomIncreaseValue(super.STAT_INCREASE_PARAM)-3;
        satisfaction += randomIncreaseValue(super.STAT_INCREASE_PARAM)-7;
        this.setCurrMessage("미쿠가 노래를 부른다. (+"+statIncreaseAmount+" exp)");
    }

}



class Egg extends Pet{
    int hatchProgress;
    String toBe = "Miku";

    String defaultImage =
            """
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡤⠾⠛⠛⠛⠛⠷⢦⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⣠⡶⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠳⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⢠⡞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⡀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⢀⣴⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀⠀⠀⠀⠀
                    ⠀⠀⠀⢠⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡆⠀⠀⠀⠀
                    ⠀⠀⢀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡄⠀⠀⠀
                    ⠀⠀⣾⠒⠒⠢⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠤⣄⠀⠀⠀⠀⠀⠀⠀⠀⢿⡄⠀⠀
                    ⠀⣸⠃⠀⠀⠀⠀⠈⠀⠄⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⢱⠀⠀⣀⡠⠤⠤⢄⣸⣷⠀⠀
                    ⢀⡟⠀⠀⠀⠀⠀⠀⠀⠰⡀⠀⠀⠀⠀⠀⠐⠲⠤⠤⠔⠃⣠⠊⠁⠀⠀⠀⠀⢻⣿⣆⠀
                    ⢸⡇⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⢸⣿⣿⡄
                    ⣾⠙⠢⠤⠤⠤⠤⠤⠒⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡇⠀⠀⠀⠀⠀⠀⣾⣿⣿⡀
                    ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⡄⠀⠀⠀⠀⢰⣿⣿⣿⡇
                    ⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣦⠀⠀⢀⣿⣿⣿⣿⡇
                    ⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⣾⣿⣿⣿⣿⠇
                    ⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⠿⠛⣿⡏⠀
                    ⠀⠹⣀⡤⠤⠤⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠃⠂⣼⡿⠁⠀
                    ⠀⠀⠻⣷⡀⠀⠀⠀⠈⠉⠒⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⡀⠈⣴⡿⠁⠀⠀
                    ⠀⠀⠀⠙⢷⣤⡀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠊⠁⠀⣀⣼⠟⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠙⢿⣿⣷⣒⣶⣶⣶⠇⠀⠀⣀⡀⠤⢐⠊⠁⠀⢀⣠⣾⠟⠁⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠈⠛⠿⣿⣉⡁⠉⠉⠁⠀⡀⣀⣥⣥⣴⣾⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠉⠉⠉⠉⠉⠙⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""";
    protected String hatchingImage= """
            ⠀⠀⠒⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⢠⠀⢸⠀⠀⠀⠀⠀⠀⠀⡞⡸⠁⠀⠀⠀⡼⠁⠀⢀⡞⠁⢀
            ⡀⠀⠀⠳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢠⡀⠀⢠⠈⠀⡸⠀⠀⠀⠀⠀⠀⡸⢱⠃⠀⠀⠀⣸⠁⠀⢠⠏⠀⣠⠃
            ⠑⣄⠀⠀⠱⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣮⠅⠀⢸⡆⠀⡇⠀⠀⠀⠀⠀⢀⣇⠇⠀⠀⠀⣰⠃⠀⡰⠃⢀⡴⠁⠀
            ⠀⠈⢂⠀⠀⠙⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠃⠈⣿⠀⠀⠈⡇⢠⠇⠀⠀⠀⠀⠀⡾⡜⠀⠀⠀⣴⠃⠀⡼⠁⢀⠞⠀⠀⠀
            ⠀⠀⠀⠢⡀⠀⠘⣄⠀⠀⠀⠀⠀⠀⠀⠀⠠⠋⠀⠀⠀⡇⠀⠀⡀⡇⢸⠀⠀⠀⠀⠀⣺⢱⠃⠀⠀⣼⠃⠀⡜⠀⣰⠋⠀⢀⢀⡀
            ⠀⠀⠀⠀⠑⣄⠀⠘⢆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⢀⠀⠀⠃⣸⠀⡏⠙⢢⣠⢇⡏⠀⠀⣴⠃⢀⡞⢀⡜⠁⠀⠀⣰⠟⠀
            ⠀⠀⠀⠀⠀⠈⢦⠀⠈⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⠀⠀⢠⠀⡏⠀⠘⠒⢚⡟⡞⠀⠀⣼⠁⢠⠎⢠⠞⠀⠀⣠⠞⠁⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠱⡄⠈⢣⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⢸⣶⠀⢸⢀⡇⠀⠀⠀⡼⣸⠁⠀⡰⠁⣰⠋⡰⠋⠀⢀⡾⠃⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠘⢆⠀⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢸⠀⢸⢸⠀⠀⠀⢰⢳⠃⠀⣰⠃⡴⢃⡜⠁⠀⡰⠋⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢣⡀⠱⡄⠀⠀⠀⠀⠀⠀⠀⢸⣸⡀⡌⢸⠀⠀⢀⣯⡏⠀⣰⢃⡼⣡⠎⠀⢀⠞⠁⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⣄⠙⡄⠀⠀⢀⣴⡶⣾⣿⠿⠻⡗⣿⣶⣤⣼⡞⠀⣰⢃⡞⡴⠃⢀⡴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⠘⣦⢚⡽⠋⣟⣹⠟⠀⠀⢹⡏⠛⢧⣉⣷⣼⢣⢞⡜⠁⣠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⢈⡗⠀⡾⢀⣠⡟⠳⡀⠀⠀⠀⣹⣦⠤⡾⢯⣁⢿⠏⢀⠜⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠋⠀⣰⠀⢯⠁⠀⠀⠹⣦⣀⡞⠉⠀⠀⠀⠀⠙⠃⠀⢯⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠁⡠⠊⠀⠀⠀⠁⠀⠀⠀⠀⠀⠉⢳⡀⠀⠀⠀⠀⠀⠀⠀⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡃⠀⠀⠀⠀⠀⠀⠀⠀⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⣿⡀⠀⢦⠀⠀⠀⢀⣀⣀⡀⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧⣸⠁⣀⠝⠀⡴⠊⠁⠀⣿⣿⣾⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⢀⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠉⠁⠀⡜⠀⠀⠀⢀⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⣘⢠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢇⠀⠀⠀⢸⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⢾⠀⠈⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆⠀⢠⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣾⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠈⠘⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⡇⠀⣀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⣸⣡⠞⠁⡀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠏⠀⢀⡔⠁⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣆⠈⠁⠀⠀⠐⠒⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠣⡀⠀⠀⠀⠀⠀⠈⠑⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠃⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⡀⣀⠀⣠⣶⡿⠀⠀⠀⠀⠀⠀⠀⠀⠂⣠⡴⠋⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠯⣭⣉⣀⣀⣀⣀⣀⣠⡤⠴⠖⠋⢁⡤⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠀⠀⡴⠁⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠖⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠦⣤⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢇⣠⡏⠀⠀⠀⠀⠀""";
    Egg() {
        super();
        commandList.add(this::warmingEgg);
        commandNameList.add("warm");
        commandCount=1;
    }
    @Override
    public String getDontKnowImage() {
        return defaultImage;
    }

    @Override
    void updatePetState(boolean isActioned) {
        this.setCurrImage(this.defaultImage); //Todo
        this.setCurrMessage("알이 놓여 있습니다."); //Todo
    }

    public int getHatchProgress() {
        return hatchProgress;
    }

    /* Action =====================================================================================================*/
    void warmingEgg(){
        this.setCurrImage(this.defaultImage);
        // TOdo 난수 생성
        int statIncreaseAmount = random.nextInt(40)+1 ;
        hatchProgress += statIncreaseAmount;
        this.setCurrMessage("알을 따듯하게 만들어줬다. (+" + String.valueOf(statIncreaseAmount) + ")");
    }

    /* Special Event =============================================================================================*/
    @Override
    boolean checkSpecialEvent() {
       if(checkHatch()){
           eventType = EventType.HATCH;
           return true;
       }
       return false;
    }
    @Override
    void handleSpecialEvent(){
        switch (eventType){
            case HATCH : {
                hatch();
            }
            // Todo : (Later) Add More Events
        }
    }


    @Override
    Set<EventType> getSupportedEvents() {
        return Set.of(EventType.HATCH);
    }


    boolean checkHatch(){
        if (hatchProgress >= 100) {
            return true;
        }
        return false;
    }
    void hatch(){
        this.setCurrImage(hatchingImage);
        this.setCurrMessage("알이 부화하고 있다.");
    }

    @Override
    Pet createNewPet(){
        switch (toBe){
            case "Miku" : {
                return new MikuLv1();
            }
            // Todo : (Later) Add Some New Products
        }
        return null;
    }
}

