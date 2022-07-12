using UnityEngine;
using UnityEngine.UI;

namespace Enviroment
{
    public class HighScoreUIWriter : MonoBehaviour
    {
        [SerializeField] private Text distanceText;
        [SerializeField] private PublicInformation gameState;
        private void Update()
        {
            int distance = gameState.SaveGameManager.getHighScore();
            distanceText.text = distance.ToString()+"m";

        }
    }
}