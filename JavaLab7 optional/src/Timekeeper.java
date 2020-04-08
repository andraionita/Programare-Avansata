/**
 * @Author: Ionita Andra, grupa A7
 * In clasa aceasta am implementat Timekeeperul care seteaza o anumita durata
 * pentru un joc. IAr in cazul in care timpul exipra, jocul se termina.
 */


public class Timekeeper implements Runnable {
    public volatile boolean stop = false; 
    private int seconds = 0;
    private int maxSeconds; //durata maxima a unui joc
    private boolean gameOver = false;

    public Timekeeper(int howLong) {
        this.maxSeconds = howLong;
    }

    public void stopGame() {
        this.stop = true;
    }

    public boolean isGameOver(){
        return this.gameOver;
    }

    public int getSeconds(){
        return this.seconds;
    }

    @Override
    public void run() {
        while(seconds<maxSeconds && !gameOver) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
            ++seconds;
        }
        gameOver = true;
    }
}
