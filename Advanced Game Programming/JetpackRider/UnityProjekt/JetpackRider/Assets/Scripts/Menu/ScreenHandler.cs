using UnityEngine;

public class ScreenHandler : StateHandler
{
    [SerializeField] private string handlerName;
    [SerializeField] private CanvasGroup canvasGroup;
    public override string Name => handlerName;

    public override void OnEnter<T>(T transition)
    {
        canvasGroup.alpha = 1;
        canvasGroup.interactable = true;
        canvasGroup.blocksRaycasts = true;
    }

    public override void OnExit<T>(T transition)
    {
        canvasGroup.alpha = 0;
        canvasGroup.interactable = false;
        canvasGroup.blocksRaycasts = false;
    }   

    private void Awake() {

        OnExit(MenuTransitions.MainMenuSelected);
                
    } 

    public void PlayerDeath() {
        OnEnter(MenuTransitions.PlayerDead);
    }
}