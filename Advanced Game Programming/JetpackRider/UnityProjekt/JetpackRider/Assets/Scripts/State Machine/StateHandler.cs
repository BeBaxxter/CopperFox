using UnityEngine;
using System;

// A regular state handler does not need optional payload and just cares about transitioned in
public interface IStateHandler 
{

    string Name { get; }

    void OnEnter<T>(T transition) where T : Enum;

    void OnExit<T>(T transition) where  T : Enum;

}

public abstract class StateHandler : MonoBehaviour, IStateHandler 
{

    public virtual string Name { get => (GetType().Name); }

    public abstract void OnEnter<T>(T transition) where T : Enum;

    public abstract void OnExit<T>(T transition)  where T : Enum;    
}