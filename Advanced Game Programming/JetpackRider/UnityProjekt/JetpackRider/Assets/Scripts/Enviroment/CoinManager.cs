using System;
using UnityEngine;
using UnityEngine.UI;

public class CoinManager : MonoBehaviour
{
    private int money = 0;
    [SerializeField] private Text[] texts;
    [SerializeField] private PublicInformation gameState;
    
    public void addAmount(int amount)
    {
        money += amount;
        foreach (var text in texts)
        {
            text.text = $"{money}";
        }
    }

    public int getMoney()
    {
        return money;
    }
}
