using UnityEngine;

    public class MenuStateMachine : StateMachine<MenuTransitions> 
    {

    [field: SerializeField] public StateHandler MainMenuHandler { get; private set; }
    [field: SerializeField] public StateHandler SelectLevelHandler { get; private set; }
    [field: SerializeField] public StateHandler GameHandler { get; private set; }
    [field: SerializeField] public StateHandler PauseHandler { get; private set; }
    [field: SerializeField] public StateHandler DeathHandler { get; private set; }

    private void Awake() 
    {

        // Main Menu -> Select Level
        AddTransition(MainMenuHandler, SelectLevelHandler, MenuTransitions.LevelSelectionSelected);
        
        // Select Level -> Game
        AddTransition(SelectLevelHandler, GameHandler, MenuTransitions.LevelSelected); 

        // Game -> Pause
        AddTransition(GameHandler, PauseHandler, MenuTransitions.GamePaused);

        // Pause -> Game
        AddTransition(PauseHandler, GameHandler, MenuTransitions.GameContinued);

        // Game -> Death
        AddTransition(GameHandler, DeathHandler, MenuTransitions.PlayerDead);

    }
}