using System.Collections;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.UI;
using Debug = UnityEngine.Debug;

public class SpawnObjects : MonoBehaviour
{
    // Get all prefabs
    [SerializeField] private PublicInformation gameState;
    [SerializeField] private GameObject dronePrefab;
    [SerializeField] private GameObject laserPrefab;
    [SerializeField] private GameObject coinPrefab;
    [SerializeField] private GameObject coinCirclePrefab;
    [SerializeField] private GameObject laserHurdlePrefab;
    [SerializeField] private GameObject laserHolePrefab;
    [SerializeField] private GameObject laserGroupTopPrefab;
    [SerializeField] private GameObject laserGroupBottomPrefab;
    [SerializeField] private GameObject rocketPrefab;
    
    // Get text field for the chosen seed
    [SerializeField] private Text seedInput;
    
    // base time for respawning
    private float respawnTime = 0.8f;
    private Vector2 screenBounds;
    
    // Getter for the seed
    private void GetSeed()
    {
        Random.InitState(int.Parse(seedInput.text));

    }

    // method to spawn a random prefab
    private float SpawnObject()
    {
        // randomizer for different prefabs
        float chance = Random.Range(0f, 1.0f);
        
        if (chance < 0.05)
        {
            // spawns laser group 
            GameObject objectToSpawn = Instantiate(laserHurdlePrefab) as GameObject;
            foreach (var laser in  objectToSpawn.GetComponentsInChildren<Laser>())
            {
                laser.gameState=gameState;
            }
            objectToSpawn.transform.position  = new Vector2(screenBounds.x * 15,objectToSpawn.transform.position.y);
            return respawnTime * Random.Range(1.0f, 2.0f) * 3.5f;

        }
        if (chance < 0.1)
        {
            // spawns laser group at the ceiling
            GameObject objectToSpawn = Instantiate(laserGroupTopPrefab) as GameObject;
            foreach (var laser in  objectToSpawn.GetComponentsInChildren<Laser>())
            {
                laser.gameState=gameState;
            }
            objectToSpawn.transform.position  = new Vector2(screenBounds.x * 15,objectToSpawn.transform.position.y);
            return respawnTime * Random.Range(1.0f, 2.0f) * 3.5f;

        }
        if (chance < 0.15)
        {
            // spawns laser group at the floor
            GameObject objectToSpawn = Instantiate(laserGroupBottomPrefab) as GameObject;
            foreach (var laser in  objectToSpawn.GetComponentsInChildren<Laser>())
            {
                laser.gameState=gameState;
            }
            objectToSpawn.transform.position  = new Vector2(screenBounds.x * 15,objectToSpawn.transform.position.y);
            return respawnTime * Random.Range(1.0f, 2.0f) * 3.5f;

        }
        else if (chance <0.2)
        {
            // spawns laser group at the ceiling and the floor
            GameObject objectToSpawn = Instantiate(laserHolePrefab) as GameObject;
            foreach (var laser in  objectToSpawn.GetComponentsInChildren<Laser>())
            {
                laser.gameState=gameState;
            }
            objectToSpawn.transform.position  = new Vector2(screenBounds.x * 10, objectToSpawn.transform.position.y);
            return respawnTime* Random.Range(1.00f, 2.0f) * 1.2f;
        }
        else if (chance <0.3)
        {
            // spawns a drone
            GameObject objectToSpawn = Instantiate(dronePrefab) as GameObject;
            objectToSpawn.GetComponent<Drones>().gameState=gameState;
            objectToSpawn.transform.position  = new Vector2(screenBounds.x * 10, Random.Range(-screenBounds.y*3, screenBounds.y*3));
            return respawnTime* Random.Range(1.0f, 2.0f);
        }
        else if (chance <0.5)
        {
            // spawns a rocket
            GameObject objectToSpawn = Instantiate(rocketPrefab) as GameObject;
            objectToSpawn.GetComponent<RocketController>().gameState=gameState;
            objectToSpawn.transform.position  = new Vector2(screenBounds.x * 10, Random.Range(-screenBounds.y*3, screenBounds.y*3));
            return respawnTime* Random.Range(1.0f, 2.0f);
        }
        else if (chance < 0.6)
        {
            // spawns a group of coins
            GameObject objectToSpawn = Instantiate(coinCirclePrefab) as GameObject;
            foreach (var coinController in  objectToSpawn.GetComponentsInChildren<CoinController>())
            {
                coinController.gameState=gameState;
            }
            objectToSpawn.transform.position = new Vector2(screenBounds.x * 10, Random.Range(-screenBounds.y*3, screenBounds.y*3));
            return respawnTime* Random.Range(1.0f, 2.0f);
        }
        else if (chance <0.8)
        {
            // spawns a single coin
            GameObject objectToSpawn = Instantiate(coinPrefab) as GameObject;
            objectToSpawn.GetComponent<CoinController>().gameState=gameState;
            objectToSpawn.transform.position = new Vector2(screenBounds.x * 10, Random.Range(-screenBounds.y*3, screenBounds.y*3));
            return respawnTime* Random.Range(1.0f, 2.0f);
        }
        else if (chance <= 1)
        {
            // spawns a laser
            GameObject objectToSpawn = Instantiate(laserPrefab) as GameObject;
            objectToSpawn.GetComponent<Laser>().gameState=gameState;
            objectToSpawn.transform.position = new Vector2(screenBounds.x * 11, Random.Range(-screenBounds.y*3, screenBounds.y*3));
            objectToSpawn.transform.Rotate(new Vector3(0,0,Random.Range(0, 360)));
            return respawnTime * Random.Range(1.0f, 2.0f)* 1.6f;
        }
        
        // return in case something gets wrong
        return respawnTime* Random.Range(1.0f, 2.0f);
        
    }

    // method to StartSpawning will be executed when level is selected
    public void StartSpawning()
    {
        GetSeed();
        screenBounds = Camera.main.ScreenToViewportPoint(new Vector3(Screen.width, Screen.height, Camera.main.transform.position.z));
        StartCoroutine(SpawnWave());
    }

    // IENumerator to yield a return to wait a certain time after every spawn, waitTime is based on the prefab
    IEnumerator SpawnWave()
    {
        var waitTime = 10.0f;
        
        // spawns enemies endlessly 
        while (true)
        {
            yield return null;
            waitTime = SpawnObject();
            yield return new WaitForSeconds(waitTime);
        }
    }
}
