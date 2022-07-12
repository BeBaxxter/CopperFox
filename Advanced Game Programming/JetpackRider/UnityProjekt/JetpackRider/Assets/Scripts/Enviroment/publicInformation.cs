using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

//change to gamestate
public class PublicInformation : MonoBehaviour
{
    public  SaveGameManager SaveGameManager;
    public CoinManager CoinManager;

     private void Start()
     {
         SaveGameManager = new SaveGameManager(this);
         
     }

    public  int level;

    public  float getAdditionalSpeed()
    {
        //speed at level one is still the same
        float additionalSpeed = (float) ((level - 1) * 1);
        return  additionalSpeed;
    }
    
    public  int getDistance()
    {
        double timeSinceStart = Time.time;
        double distance = timeSinceStart*3;
        return (int)distance;
    }
}
