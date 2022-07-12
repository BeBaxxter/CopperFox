using UnityEngine;
using UnityEngine.UI;

namespace Enviroment
{
    public class DistanceUIWriter : MonoBehaviour
    {
        [SerializeField] private Text distanceText;
        [SerializeField] private PublicInformation gameState;
        private void Update()
        {
            int distance = gameState.getDistance();
            distanceText.text = distance.ToString()+"m";

        }
    }
}