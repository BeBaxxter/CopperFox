using UnityEngine;

// adds lifepoints to the game and calls methods of WinLoose to crate a win and loose screen
public class LifeTracker : MonoBehaviour
{
    // life points with valid range
    [SerializeField][Range(0, 2)] private int lifePoints;
    [SerializeField] private CanvasGroup deathScreen;
    [SerializeField] private PublicInformation gameState;


    //turn off all lifepoint images that are not needed 
    public void Start()
    {
        lifePoints = 1;
    }
        
    //return the current lifePoints
    public int getLifePoints()
    {
        return lifePoints;

    }

    // called when there is a change to the lifepoints of the player
    public void changeLifePoints(int change)
    {
        
        //change the actual LifePoints
        lifePoints += change;

        //check for game over condition
        if (lifePoints <= 0)
        {
            gameState.SaveGameManager.SaveThisGame();
            Time.timeScale = 0;
            deathScreen.alpha = 1;
            deathScreen.interactable = true;
            deathScreen.blocksRaycasts = true;  
            
        }
                 
    }
}
