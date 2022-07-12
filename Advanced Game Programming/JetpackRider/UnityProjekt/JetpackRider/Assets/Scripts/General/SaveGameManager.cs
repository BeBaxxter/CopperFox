using System.IO;
using UnityEngine;

public class SaveGameManager 
{
    private PublicInformation gameState;
    private  string subPath = "SaveFiles/SaveGameFile";
    public SaveGame saveGame;
 
    public SaveGameManager( PublicInformation gameState)
    {
        saveGame =  LoadThisGame(subPath);
        this.gameState = gameState;
    }

    public void SaveThisGame()
    {
        saveGame.coins +=gameState.CoinManager.getMoney();
        if (saveGame.maxDistance < gameState.getDistance())
        {
            saveGame.maxDistance = gameState.getDistance();
        }
            
        SaveThisGame(saveGame, subPath);
    }
    
    private static void SaveThisGame(SaveGame data, string subPath)
    {
        
        var jsonString = JsonUtility.ToJson(data);
        var fullPath = Path.Combine(Application.dataPath, subPath);
        
        using (var streamWriter = File.CreateText(fullPath)) {
            streamWriter.Write(jsonString);
        }
    }

    private static SaveGame LoadThisGame(string subPath)
    {
        var fullPath = Path.Combine(Application.dataPath, subPath);
        using (var streamReader = File.OpenText(fullPath))
        {
            var jsonString = streamReader.ReadToEnd();
            return JsonUtility.FromJson<SaveGame>(jsonString);
        }
    }

    public int getHighScore()
    {
        return (int) saveGame.maxDistance;
    }

}
