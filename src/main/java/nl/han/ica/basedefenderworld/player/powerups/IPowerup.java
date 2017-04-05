package nl.han.ica.basedefenderworld.player.powerups;

public interface IPowerup {


    /**
     * This function is executed when the player decides to use a power up.
     * The function increases the the progress from the selected powerup, decreases
     * the amount of unclaimed power ups and makes sure the powerup is executed.
     * @see PowerupHandler
     */
    void powerup();

    /**
     * @see PowerupHandler
     * @see nl.han.ica.basedefenderworld.dashboard.PowerupBoard
     * @return currentProgress
     */
    int getProgress();
}
