import game.engine.Engine;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        int rand_int = ThreadLocalRandom.current().nextInt();
        String szam = Integer.toString(rand_int);
        String[] args1 = {"15","game.gmk.GomokuGame",szam,"15","15","0.1","2000","game.gmk.players.GreedyPlayer","Agent"};
        try{
            Engine.main(args1);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
