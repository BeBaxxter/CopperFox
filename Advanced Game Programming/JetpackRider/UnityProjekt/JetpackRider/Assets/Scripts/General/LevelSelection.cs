using UnityEngine;

namespace General
{
    public class LevelSelection :MonoBehaviour

    {
        [SerializeField] private PublicInformation gameState;
        [SerializeField] private GameObject level1;
        [SerializeField] private GameObject level2;
        [SerializeField] private GameObject level3;
        
        public void setLevel(int level)
        {
            gameState.level = level;
        }
        
        public void setLevel1()
        {
            setLevel(1);
            // activate Background level 1
            level1.SetActive(true);
            level2.SetActive(false);
            level3.SetActive(false);
            
        }
        
        public void setLevel2()
        {
            setLevel(2);
            // activate Background level 2
            level1.SetActive(false);
            level2.SetActive(true);
            level3.SetActive(false);
        }
        
        public void setLevel3()
        {
            setLevel(3);
            // activate Background level 3
            level1.SetActive(false);
            level2.SetActive(false);
            level3.SetActive(true);
        }
        
        
    }
}