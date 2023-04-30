import java.util.ArrayList;
import java.util.Enumeration;

/*
Player class will have a Scoring variable to access calculateScore method
 */
public class Scoring {
    //static ints will be used when indicated which neighbouring tile you are checking
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;

    //for naming clarity when calling checkDiagonalNeighbour
    public static final int UP_LEFT = 1;
    public static final int UP_RIGHT = 2;
    public static final int DOWN_RIGHT = 3;
    public static final int DOWN_LEFT = 4;

    public int [] cardChoices = new int[5]; //stores the random card numbers which were selected for each animal for the game.

    /*
    In code walk-through video: mention that we would have had 4 cards for EVERY animal
    but that we ran out of time to implement this.
    We have 4 cards for bear and hawk, use those an example of how all of them
    would have ideally been.
    */

   //picks random scoring card number for each animal and displays the rules for that card
    public void displayScoringRules(){
        Tile t = new Tile();
        System.out.println("How you can score for each wildlife token: ");

        //assigning random card number for each wildlife.
        int bear, elk, salmon, hawk, fox;
        bear = t.randomIndex(4);
        elk = t.randomIndex(4); //don't have 4 scoring cards for elk yet
        //elk = 0;
        salmon = t.randomIndex(4);
        hawk = t.randomIndex(4);
        fox = t.randomIndex(4);

        cardChoices[0] = bear;
        cardChoices[1] = elk;
        cardChoices[2] = salmon;
        cardChoices[3] = hawk;
        cardChoices[4] = fox; //the chosen card numbers will be stored in this array which we will use as input to calculate the scores later

        //displaying rules for the corresponding card
        bearScoring(bear);
        elkScoring(elk);
        salmonScoring(salmon);
        hawkScoring(hawk);
        foxScoring(fox);
    }

    public int calculateScore(Player player){
        int score = calculateWildlife(player) + player.getNatureTokens() + calculateHabitat(player);
        return score;
    }
    public int calculateWildlife(Player player){
        int score = 0;
        score += calculateBear(cardChoices[0], player);
        score += calculateElk(cardChoices[1], player);
        score += calculateSalmon(cardChoices[2], player);
        score += calculateHawk(cardChoices[3], player);
        score += calculateFox(cardChoices[4], player);
        return score;
    }
    //to be completed
    //calculate habitat function for cascadia board game
    public int calculateHabitat(Player player){
        int score = 0;
        Tile[][] t = player.getBoard().getStorage();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(t[i][j] != null){
                    if(t[i][j].getHabitat(Enum.Terrain.FOREST, Enum.Wildlife.BEAR) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.FOREST, Enum.Wildlife.ELK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.FOREST, Enum.Wildlife.FOX) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.FOREST, Enum.Wildlife.HAWK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.FOREST, Enum.Wildlife.SALMON) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.MOUNTAIN, Enum.Wildlife.BEAR) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.MOUNTAIN, Enum.Wildlife.ELK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.MOUNTAIN, Enum.Wildlife.FOX) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.MOUNTAIN, Enum.Wildlife.HAWK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.MOUNTAIN, Enum.Wildlife.SALMON) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.RIVER, Enum.Wildlife.BEAR) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.RIVER, Enum.Wildlife.ELK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.RIVER, Enum.Wildlife.FOX) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.RIVER, Enum.Wildlife.HAWK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.RIVER, Enum.Wildlife.SALMON) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.WETLAND, Enum.Wildlife.BEAR) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.WETLAND, Enum.Wildlife.ELK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.WETLAND, Enum.Wildlife.FOX) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.WETLAND, Enum.Wildlife.HAWK) == 1){
                        score += 1;
                    }
                    if(t[i][j].getHabitat(Enum.Terrain.WETLAND, Enum.Wildlife.SALMON) == 1){
                        score += 1;
                    }
                }
            }
        }
        return score;
    }





    //compares wildlife tokens on neighbours directly next to/above/below
    public boolean checkNeighbour(Player player, int i, int j, int direction) {

        Tile[][] t = player.getBoard().getStorage();

        //just i edge case:
        if (i == 0 && j > 0 && j < 10) {
            //up
            if (direction == 1) return false;
            //right
            if (direction == 2) {
                if (t[i][j + 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j + 1].getWildlife1()) return true;

            }
            //down
            if (direction == 3) {
                if (t[i + 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i + 1][j].getWildlife1()) return true;
            }
            //left
            if (direction == 4) {
                if (t[i][j - 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j - 1].getWildlife1()) return true;
            }
        }

        if (i == 9 && j > 0 && j < 10) {
            //up
            if (direction == 1) {
                if (t[i - 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i - 1][j].getWildlife1()) return true;
            }
            //right
            if (direction == 2) {
                if (t[i][j + 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j + 1].getWildlife1()) return true;
            }
            //down
            if (direction == 3) return false;
            //left
            if (direction == 4) {
                if (t[i][j - 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j - 1].getWildlife1()) return true;
            }
        }

        //i and j edge cases:
        if (i == 0 && j == 0) {
            //up
            if (direction == 1) return false;
            //right
            if (direction == 2) {
                if (t[i][j + 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j + 1].getWildlife1()) return true;
            }
            //down
            if (direction == 3) {
                if (t[i + 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i + 1][j].getWildlife1()) return true;
            }
            //left
            if (direction == 4) return false;
        }

        if (i == 0 && j == 10) {
            //up
            if (direction == 1) return false;
            //right
            if (direction == 2) return false;
            //down
            if (direction == 3) {
                if (t[i + 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i + 1][j].getWildlife1()) return true;
            }
            //left
            if (direction == 4) {
                if (t[i][j - 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j - 1].getWildlife1()) return true;
            }
        }

        if (i == 9 && j == 0) {
            //up
            if (direction == 1) {
                if (t[i - 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i - 1][j].getWildlife1()) return true;
            }
            //right
            if (direction == 2) {
                if (t[i][j + 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j + 1].getWildlife1()) return true;
            }
            //down
            if (direction == 3) return false;
            //left
            if (direction == 4) return false;
        }

        if (i == 9 && j == 10) {
            //up
            if (direction == 1) {
                if (t[i - 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i - 1][j].getWildlife1()) return true;
            }
            //right
            if (direction == 2) return false;
            //down
            if (direction == 3) return false;
            //left
            if (direction == 4) {
                if (t[i][j - 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j - 1].getWildlife1()) return true;
            }
        }

        //just j edge case
        if (j == 0 && i > 0 && i < 9) {
            //up
            if (direction == 1) {
                if (t[i - 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i - 1][j].getWildlife1()) return true;
            }
            //right
            if (direction == 2) {
                if (t[i][j + 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j + 1].getWildlife1()) return true;
            }
            //down
            if (direction == 3) {
                if (t[i + 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i + 1][j].getWildlife1()) return true;
            }
            //left
            if (direction == 4) return false;
        }

        if (j == 10 && i > 0 && i < 9) {
            if (direction == 1) {
                if (t[i - 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i - 1][j].getWildlife1()) return true;
            }
            if (direction == 2) return false;

            if (direction == 3) {
                if (t[i + 1][j] == null) return false;
                if (t[i][j].getWildlife1() == t[i + 1][j].getWildlife1()) return true;
            }
            if (direction == 4) {
                if (t[i][j - 1] == null) return false;
                if (t[i][j].getWildlife1() == t[i][j - 1].getWildlife1()) return true;
            }
        }

//normal cases:
        //up
        if (direction == 1) {
            if (t[i - 1][j] == null) return false;
            if (t[i][j].getWildlife1() == t[i - 1][j].getWildlife1()) return true;
        }
        //right
        if (direction == 2) {
            if (t[i][j + 1] == null) return false;
            if (t[i][j].getWildlife1() == t[i][j + 1].getWildlife1()) return true;
        }
        //down
        if (direction == 3) {
            if (t[i + 1][j] == null) return false;
            if (t[i][j].getWildlife1() == t[i + 1][j].getWildlife1()) return true;
        }
        //left
        if (direction == 4) {
            if (t[i][j - 1] == null) return false;
            if (t[i][j].getWildlife1() == t[i][j - 1].getWildlife1()) return true;
        }
        return false;
    }
    //compares wildlife tokens on neighbours touching each corner of tile
    public boolean checkDiagonalNeighbour(Player player, int i, int j, int direction){
        Tile [][] t = player.getBoard().getStorage();

        //just i edge case:
        if(i==0 && j>0 && j<10){
            //up-left
            if(direction == 1)return false;
            //up-right
            if(direction == 2)return false;
            //down-right
            if(direction == 3){
                if(t[i+1][j+1] == null) return false;
                if(t[i][j].getWildlife1() == t[i+1][j+1].getWildlife1()) return true;
            }
            //down-left
            if(direction == 4){
                if(t[i+1][j-1] == null) return false;
                if(t[i][j].getWildlife1() == t[i+1][j-1].getWildlife1()) return true;
            }
        }

        if(i==9 && j>0 && j<10){
            //up-left
            if(direction == 1){
                if(t[i-1][j-1] == null) return false;
                if(t[i][j].getWildlife1() == t[i-1][j-1].getWildlife1()) return true;
            }
            //up-right
            if(direction == 2){
                if(t[i-1][j+1] == null) return false;
                if(t[i][j].getWildlife1() == t[i-1][j+1].getWildlife1()) return true;
            }
            //down-right
            if(direction == 3)return false;
            //down-left
            if(direction == 4)return false;
        }

        //i and j edge cases:
        if(i==0 && j==0){
            //up-left
            if(direction == 1) return false;
            //up-right
            if(direction == 2)return false;
            //down-right
            if(direction == 3){
                if(t[i+1][j+1] == null) return false;
                if(t[i][j].getWildlife1() == t[i+1][j+1].getWildlife1()) return true;
            }
            //down-left
            if(direction == 4) return false;
        }

        if(i==0 && j==10){
            //up-left
            if(direction == 1) return false;
            //up-right
            if(direction == 2) return false;
            //down-right
            if(direction == 3) return false;
            //down-left
            if(direction == 4){
                if(t[i+1][j-1] == null) return false;
                if(t[i][j].getWildlife1() == t[i+1][j-1].getWildlife1())return true;
            }
        }

        if(i==9 && j==0){
            //up-left
            if(direction == 1) return false;
            //up-right
            if(direction == 2){
                if(t[i-1][j+1] == null) return false;
                if(t[i][j].getWildlife1() == t[i-1][j+1].getWildlife1())return true;
            }
            //down-right
            if(direction == 3)return false;
            //down-left
            if(direction == 4)return false;
        }

        if(i==9 && j==10){
            //up-left
            if(direction == 1){
                if(t[i-1][j-1] == null)return false;
                if(t[i][j].getWildlife1() == t[i-1][j-1].getWildlife1())return true;
            }
            //up-right
            if(direction == 2)return false;
            //down -right
            if(direction == 3)return false;
            //down-left
            if(direction == 4)return false;
        }

        //just j edge case
        if(j==0 && i>0 && i<9){
            //up-left
            if(direction == 1)return false;
            //up-right
            if(direction == 2){
                if(t[i-1][j+1] == null) return false;
                if(t[i][j].getWildlife1() == t[i-1][j+1].getWildlife1())return true;
            }
            //down-right
            if(direction == 3){
                if(t[i+1][j+1] == null)return false;
                if(t[i][j].getWildlife1() == t[i+1][j+1].getWildlife1()) return true;
            }
            //down-left
            if(direction == 4)return false;
        }

        if(j==10 && i>0 && i<9){
            //up-left
            if(direction == 1){
                if(t[i-1][j-1] == null) return false;
                if(t[i][j].getWildlife1() == t[i-1][j-1].getWildlife1())return true;
            }
            //up-right
            if(direction == 2)return false;
            //down-right
            if(direction == 3)return false;
            //down-left
            if(direction == 4){
                if(t[i+1][j-1] == null)return false;
                if(t[i][j].getWildlife1() == t[i+1][j-1].getWildlife1())return true;
            }
        }

        //normal cases:
        //up-left
        if(direction == 1){
            //System.out.println("1");
            if(t[i-1][j-1] == null) return false;
            if(t[i][j].getWildlife1() == t[i-1][j-1].getWildlife1())return true;
        }
        //up-right
        if(direction == 2){
            //System.out.println("2");
            if(t[i-1][j+1] == null) return false;
            if(t[i][j].getWildlife1() == t[i-1][j+1].getWildlife1())return true;
        }
        //down-right
        if(direction == 3){
            //System.out.println("3");
            if(t[i+1][j+1] == null) return false;
            if(t[i][j].getWildlife1() == t[i+1][j+1].getWildlife1())return true;
        }
        //down-left
        if(direction == 4){
            //System.out.println("4");
            if(t[i+1][j-1] == null)return false;
            if(t[i][j].getWildlife1() == t[i+1][j-1].getWildlife1())return true;
        }
        return false;
    }

    public void salmonScoring(int card){
        System.out.println("\n\nTo score for SALMON:");
        System.out.println("Salmon score for creating runs of salmon!" +
                "\nA run defined such that the corners of the tiles are touching. " +
                "A salmon can be adjacent to no more than 2 other salmon in order for it to be considered a run.");
        if(card ==0){
            System.out.println("Score for each run, based on size, up to a maximum size of 6!");
            System.out.println("\nFor 1 salmon score 2 points!\n" +
                    "For 2 salmon score 5 points!\n" +
                    "For 3 salmon score 8 points!\n" +
                    "For 4 salmon score 12 points!\n" +
                    "For 5 salmon score 16 points!\n" +
                    "For 6+ salmon score 20 points!");
        }
        if(card ==1){
            System.out.println("Score for each run, based on size, up to a maximum size of 5!");
            System.out.println("\nFor 1 salmon score 2 points!\n" +
                    "For 2 salmon score 4 points!\n" +
                    "For 3 salmon score 9 points!\n" +
                    "For 4 salmon score 11 points!\n" +
                    "For 5+ salmon score 17 points!");
        }
        if (card == 2){
            System.out.println("Score for each run, based on size, between 3 and 5");
            System.out.println("\nFor 3 salmon score 10 points!" +
                    "\nFor 4 salmon score 12 points!" +
                    "\nFor 5 salmon score 15 points!");
        }
        if(card ==3){
            System.out.println("Score for each run of salmon, one point for each salmon plus one point for each adjacent animal token!");
        }
    }
    public void foxScoring(int card){
        System.out.println("Foxes score for being adjacent to other animals! Foxes score either individually or in pairs, and each fox or fox pair is\n" +
                "independently scored, with an increasing number of points, depending on whether conditions are met in any of the\n" +
                "adjacent habitat spaces.");
        if (card == 0) {
            System.out.println("\nScore 1 point for each fox that is not part of a chain.");
        }
        if (card == 1) {
            System.out.println("\nScore 1 point for each fox that is not part of a chain.\n" +
                    "Score 2 points for each chain of 2 foxes.\n" +
                    "Score 5 points for each chain of 3 or more foxes.");
        }
        if (card == 2) {
            System.out.println("\nScore 1 point for each fox that is not part of a chain.\n" +
                    "Score 3 points for each chain of 3 or more foxes.");
        }
        if (card == 3) {
            System.out.println("\nScore 1 point for each fox that is not part of a chain.\n" +
                    "Score 4 points for each chain of 4 or more foxes.\n" +
                    "Score 1 point for each rabbit adjacent to your foxes (regardless of whether or not the rabbit is part of a chain).");
        }
    }

//BEARS:
    public void bearScoring(int card) {
        System.out.println("\n\nTo score for BEARS:");
        if (card == 0) {
            System.out.println("Score increasing numbers of points based on the total number of pairs of bears!");
            System.out.println("Score 4 points for each pair!\n");
        }
        if (card == 1) {
            System.out.println("Score 10 points for each group of exactly 3 bears organised in an L shape!");
        }
        if (card == 2) {
            System.out.println("Score for each group of bears 1-3 in size!\n");
            System.out.println("\nA group of 1 consists of a single bear!You will score 2 points!" +
                    "\nA group of 2 consists of 2 bears directly next to each-other! (Note the corners of 2 bears touching does not count)" +
                    "You will score 5 points!" +
                    "\nA group of 3 consists of 3 bears organised in an L shape!You will score 8 points!");
        }
        if (card == 3) {
            System.out.println("Score for each group of bears 2-4 in size!");
            System.out.println("\nA group of 2 consists of 2 bears directly next to each-other! (Note the corners of 2 bears touching does not count)" +
                    "You will score 5 points" +
                    "\nA group of 3 consists of 3 bears organised in an L shape! You will score 8 points!" +
                    "\nA group of 4 consists of 2 groups of 2, one on top of the other! You will score 13 points!");
        }
        System.out.println("\n(Note that in order to score, there cannot be neighbouring bears that are not included in the group being scored)");
    }

    public int calculateBear(int card, Player player) {
        int score = 0;
        Tile[][] t = player.getBoard().getStorage();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                if (t[i][j] != null && t[i][j].getWildlife1() == Enum.Wildlife.BEAR && t[i][j].getWildlife2() == null && t[i][j].getWildlife3() == null) {
                    if (card == 0) score += bear1(player, i, j);
                    if (card == 1) score += bear2(player, i, j);
                    if (card == 2) score += bear3(player, i, j);
                    if (card == 3) score += bear4(player, i, j);
                }
            }
        }
        return score;
    }

    //edit bear1 to account for different score each pair.
    public int bear1(Player player, int i, int j) {
        int score = 0;
        Tile[][] board = player.getBoard().getStorage();
        //to avoid double counting pairs: only check right neighbour and neighbour below
        if (!checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, DOWN) && !checkNeighbour(player, i, j, LEFT)) {
            if (!checkNeighbour(player, i, j + 1, UP) && !checkNeighbour(player, i, j + 1, RIGHT) && !checkNeighbour(player, i, j + 1, DOWN)) {
                score += 4;
            }
        }
        if (!checkNeighbour(player, i, j, UP) && !checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && !checkNeighbour(player, i, j, LEFT)) {
            if (!checkNeighbour(player, i + 1, j, LEFT) && !checkNeighbour(player, i + 1, j, DOWN) && !checkNeighbour(player, i + 1, j, RIGHT)) {
                score += 4;
            }
        }
        return score;
    }
    public int bear2(Player player, int i, int j) {
        int score = 0;
        Tile[][] board = player.getBoard().storage;

        if (checkNeighbour(player, i, j, UP) && !checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i, j, LEFT)) {
            if (!checkNeighbour(player, i, j - 1, LEFT) && !checkNeighbour(player, i, j - 1, UP) && !checkNeighbour(player, i, j - 1, DOWN)) {
                if (!checkNeighbour(player, i - 1, j, UP) && !checkNeighbour(player, i - 1, j, RIGHT)) {
                    score += 10;
                }
            }
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, DOWN) && !checkNeighbour(player, i, j, LEFT)) {
            if (!checkNeighbour(player, i, j + 1, UP) && !checkNeighbour(player, i, j + 1, RIGHT) && !checkNeighbour(player, i, j + 1, DOWN)) {
                if (!checkNeighbour(player, i - 1, j, LEFT) && !checkNeighbour(player, i - 1, j, UP)) {
                    score += 10;
                }
            }
        }
        if (!checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && !checkNeighbour(player, i, j, LEFT)) {
            if (!checkNeighbour(player, i, j + 1, UP) && !checkNeighbour(player, i, j + 1, RIGHT) && !checkNeighbour(player, i, j + 1, DOWN)) {
                if (!checkNeighbour(player, i + 1, j, LEFT) && !checkNeighbour(player, i + 1, j, DOWN)) {
                    score += 10;
                }
            }
        }
        if (!checkNeighbour(player, i, j, UP) && !checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i, j, LEFT)) {
            if (!checkNeighbour(player, i + 1, j, LEFT) && !checkNeighbour(player, i + 1, j, DOWN) && !checkNeighbour(player, i + 1, j, RIGHT)) {
                if (!checkNeighbour(player, i, j - 1, UP) && !checkNeighbour(player, i, j - 1, LEFT)) {
                    score += 10;
                }
            }
        }

        return score;
    }
    public int bear3(Player player, int i, int j) {
        int score = 0;
        if (!checkNeighbour(player, i, j, UP) && !checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, DOWN) && !checkNeighbour(player, i, j, LEFT)) {
            score = 2;
        }
        if (bear1(player, i, j) != 0 && bear2(player, i, j) == 0) {
            System.out.println("Two bear" + i + j);
            score = 5;
        }
        if (bear1(player, i, j) == 0 && bear2(player, i, j) != 0) {
            System.out.println("Three bear" + i + j);
            score = 8;
        }
        return score;
    }
    public int bear4(Player player, int i, int j) {
        int score = 0;
        if (bear1(player, i, j) != 0 && bear2(player, i, j) == 0) {
            System.out.println("Two bear" + i + j);
            score = 5;
        }
        if (bear1(player, i, j) == 0 && bear2(player, i, j) != 0) {
            System.out.println("Three bear" + i + j);
            score = 8;
        }
        if (bear3(player, i, j) == 0) {
            if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
                System.out.println("Four bear" + i + j);
                score = 13;
            }
        }
        return score;
    }

//ELK:
    public void elkScoring(int card) {
        System.out.println("\n\nTo score for ELK:");
        if (card == 0) {
            System.out.println("Score for groups in straight lines!");
            System.out.println("\nFor 1 elk score 2 points!\n" +
                    "For 2 elk score 5 points!\n" +
                    "For 3 elk score 9 points!\n");
        }
        if (card == 1) {
            System.out.println("Score for groups in particular shapes:" +
                    "\nFor a singular elk score 2 points!" +
                    "\nFor 2 elk directly beside each-other score 5 points!" +
                    "\nFor 3 elk in an L shape score 9 points!");
        }
        if (card == 2) {
            System.out.println("Score an increasing number of points (based on size) for each contiguous group of elk!\nThe group can be of any shape!");
            System.out.println("\nFor 1 elk score 2 points!" +
                    "\nFor 2 elk score 4 points!" +
                    "\nFor 3 elk score 7 points!" +
                    "\nFor 4 elk score 10 points");
        }
        if (card == 3) {
            System.out.println("Score for groups in a circular formation!\n\t(essentially 2 pairs, one on top of the other");
            System.out.println("\nFor 1 elk score 2 points!" +
                    "\nFor 2 elk score 5 points!" +
                    "\nFor 3 elk score 8 points!" +
                    "\nFor a full circle of 4 elk score 12 points!");
        }
        System.out.println("Note that groups of elk CAN be beside each-other. However each elk can only be counted as part of 1 group");
    }
//    public int calculateElk(int card, Player player) {
//        int score = 0;
//        Tile t[][] = player.getBoard().getStorage();
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 10; j++) {
//                if (t[i][j].getWildlife1() == Enum.Wildlife.ELK && t[i][j].getWildlife2() == null && t[i][j].getWildlife3() == null) {
//                    if (card == 0) score += elk1(player, i, j);
//                }
//            }
//        }
//
//        return score;
//    }
    public int elk1(Player player, int i, int j) {
        int score = 0;
        int count = 2;
        if (checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, LEFT)) {
            score += count;
            while (checkNeighbour(player, i, j, RIGHT)) {
                count++;
                score += count;
                j++;
            }
        } else if (!checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, LEFT)) {
            score = 2;
        }
        return score;
    }
    public int elk2(Player player, int i, int j){
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, LEFT)) {
            score = 5;
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, DOWN)) {
            score = 5;
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, RIGHT)) {
            score = 9;
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, LEFT)) {
            score = 9;
        }
        if (checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i, j, RIGHT)) {
            score = 9;
        }
        if (checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i, j, LEFT)) {
            score = 9;
        }
        return score;
    }

    public int elk3(Player player, int i, int j){
        int score = 0;
        int count = 2;
        if (checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, LEFT)) {
            score += count;
            while (checkNeighbour(player, i, j, RIGHT)) {
                count++;
                score += count;
                j++;
            }
        } else if (!checkNeighbour(player, i, j, RIGHT) && !checkNeighbour(player, i, j, LEFT)) {
            score = 2;
        }
        return score;
    }

    public int elk4(Player player, int i, int j){
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, LEFT)) {
            score = 5;
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, DOWN)) {
            score = 5;
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, RIGHT)) {
            score = 8;
        }
        if (checkNeighbour(player, i, j, UP) && checkNeighbour(player, i, j, LEFT)) {
            score = 8;
        }
        if (checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i, j, RIGHT)) {
            score = 8;
        }
        if (checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i, j, LEFT)) {
            score = 8;
        }
        return score;
    }




//NEED TO FINISH IMPLEMENTATION FOR SALMON AND FOX:

//SALMON:
    public int calculateSalmon(int card, Player player){
            //complete the code
        int score = 0;
        Tile[][] t = player.getBoard().getStorage();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                if (t[i][j] != null && t[i][j].getWildlife1() == Enum.Wildlife.SALMON && t[i][j].getWildlife2() == null && t[i][j].getWildlife3() == null) {
                    if (card == 0) score += salmon1(player, i, j);
                    if (card == 1) score += salmon2(player, i, j);
                    if (card == 2) score += salmon3(player, i, j);
                    if (card == 3) score += salmon4(player, i, j);
                }
            }
        }
        return score;
    }

    public int calculateElk(int card, Player player){
        int score = 0;
        Tile[][] t = player.getBoard().getStorage();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                if (t[i][j] != null && t[i][j].getWildlife1() == Enum.Wildlife.ELK && t[i][j].getWildlife2() == null && t[i][j].getWildlife3() == null) {
                    if (card == 0) score += elk1(player, i, j);
                    if (card == 1) score += elk2(player, i, j);
                    if (card == 2) score += elk3(player, i, j);
                    if (card == 3) score += elk4(player, i, j);
                }
            }
        }
        return score;
    }

    public int salmon1(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            System.out.println("Four salmon" + i + j);
            score = 13;
        }
        return score;
    }
    public int salmon2(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            System.out.println("Four salmon" + i + j);
            score = 13;
        }
        return score;
    }

    public int salmon3(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            System.out.println("Four salmon" + i + j);
            score = 13;
        }
        return score;
    }

    public int salmon4(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            System.out.println("Four salmon" + i + j);
            score = 13;
        }
        return score;
    }

//FOXES:

    public int calculateFox(int card, Player player) {
        int score = 0;
        Tile[][] t = player.getBoard().getStorage();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                if (t[i][j] != null && t[i][j].getWildlife1() == Enum.Wildlife.FOX && t[i][j].getWildlife2() == null && t[i][j].getWildlife3() == null) {
                    if (card == 0) score += fox1(player, i, j);
                    if (card == 1) score += fox2(player, i, j);
                    if (card == 2) score += fox3(player, i, j);
                    if (card == 3) score += fox4(player, i, j);
                }
            }
        }
        return score;
    }
    public int fox1(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            score = 5;
        }
        return score;
    }
    public int fox2(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            score = 5;
        }
        return score;
    }
    public int fox3(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            score = 5;
        }
        return score;
    }
    public int fox4(Player player, int i, int j) {
        int score = 0;
        if (checkNeighbour(player, i, j, RIGHT) && checkNeighbour(player, i, j, DOWN) && checkNeighbour(player, i + 1, j, RIGHT)) {
            score = 5;
        }
        return score;
    }



//HAWKS:

    //only want to count score for tiles on which tokens have been PLACED (need to find way to account for tiles with one wildlife anyway)
    public void hawkScoring(int card) {
        System.out.println("\n\nTo score for HAWKS:");
        System.out.println("Hawks score for spreading out over the landscape. Hawks can score for either each hawk, each pair of hawks,\n" +
                "or for lines of sight between hawks. A line of sight is a straight line from flat side to flat side of the hexagons, as\n" +
                "pictured. A line of sight is only interrupted by the presence of another hawk ");
        if (card == 0) {
            System.out.println("\nScore points for each hawk that is not adjacent to another hawk!");
            System.out.println("\nScore 2 points for each hawk!");
        }
        if (card == 1) {
            System.out.println("\nScore an increasing number of points for each hawk that is not adjacent to another hawk" +
                    "\nand also has a direct line of sigh to another hawk.");
            System.out.println("\nScore 5 points for each pair!");
        }
        if (card == 2) {
            System.out.println("\nScore 3 points for each line of sight between 2 hawks!\n\t(Multiple lines of sight can be associated with the same hawk)");
        }
        if (card == 3) {
            System.out.println("\nScore for each pair of hawks, an increasing number of points based on the " +
                    "number of unique animal types between them (not including other hawks)! Each hawk may only be part of one pair.");
            System.out.println("\nFor 4 points for each animal between them!");
        }
    }
    public int calculateHawk(int card, Player player){
        int score = 0;
        Tile t[][] = player.getBoard().getStorage();
        for(int i=0; i<9; i++){
            for(int j=0; j<10; j++){
                if(t[i][j] != null && t[i][j].getWildlife1() == Enum.Wildlife.HAWK && t[i][j].getWildlife2() == null && t[i][j].getWildlife3() == null){
                    if(card == 0) score += hawk1(player, i, j);
                    if(card == 1) score += hawk2(player, i, j);
                    if(card == 2) score += hawk3(player, i, j);
                    if(card == 3) score += hawk4(player, i, j);
                }
            }
        }
        return score;
    }
    public int hawk1(Player player, int i, int j){
        int score =0;
        if(!checkNeighbour(player, i,j, LEFT) && !checkNeighbour(player, i,j,UP) && !checkNeighbour(player, i,j,RIGHT) && !checkNeighbour(player, i,j,DOWN)){
            score = 2;
        }
        return score;
    }
    public int hawk2(Player player, int i, int j){
        int score = 0;
        Tile [][] t = player.getBoard().getStorage();
        //only scan right and downwards to avoid counting pairs twice:
        if(hawk1(player,i,j) != 0){
            for(int k=1; k<10-j; k++){
                if(t[i][j+k] != null && t[i][j+k].getWildlife1() == Enum.Wildlife.HAWK){
                    score+= 5;
                    break;
                }
            }
            for(int k=1; k<9-i; k++){
                if(t[i+k][j] != null && t[i+k][j].getWildlife1() == Enum.Wildlife.HAWK){
                    score += 5;
                    break;
                }
            }
        }

        return score;
    }
    public int hawk3(Player player, int i, int j){
        int score = 0;
        Tile [][] t = player.getBoard().getStorage();
        //checking right: hawk right next to i,j does not count
        if(!checkNeighbour(player, i, j, RIGHT)){
            //loop which iterates from found element to edge of board
            for(int k=1; k<10-j; k++){
                if(t[i][j+k] != null && t[i][j+k].getWildlife1() == Enum.Wildlife.HAWK){
                    score+= 5;
                    break;
                }
            }
        }
        ////checking below: hawk right below i,j does not count
        if(!checkNeighbour(player, i,j,DOWN)){
            //loop which iterates from found element to bottom of board
            for(int k=1; k<9-i; k++){
                if(t[i+k][j] != null && t[i+k][j].getWildlife1() == Enum.Wildlife.HAWK){
                    score += 5;
                    break;
                }
            }
        }
        return score;
    }
    public int hawk4(Player player, int i, int j){
        int score = 0;
        Tile [][] t = player.getBoard().getStorage();
        //checking right: hawk right next to i,j does not count
        if(!checkNeighbour(player, i, j, RIGHT)){
            int animal = 0;
            for(int k=1; k<10-j; k++){
                if(t[i][j+k] != null && t[i][j+k].getWildlife1() != Enum.Wildlife.HAWK){
                    animal += 1; //this should only happen if line of sight is found
                }
                if(t[i][j+k] != null && t[i][j+k].getWildlife1() == Enum.Wildlife.HAWK){
                    score+= (4*animal);
                    break; //when hawk is found, searching stops as each hawk can only be part of one pair
                }
            }
        }
        //hawk directly beside each-other does not count as line of sight
        if(!checkNeighbour(player,i,j,DOWN)){
            int animal = 0;
            for(int k=1; k<9-i; k++){
                if(t[i][j+k] != null && t[i][j+k].getWildlife1() != Enum.Wildlife.HAWK){
                    animal += 1; //this should only happen if line of sight is found
                }
                if(t[i+k][j] != null && t[i+k][j].getWildlife1() == Enum.Wildlife.HAWK){
                    score += (4*animal);
                    break;
                }
            }
        }
        return score;
    }
}