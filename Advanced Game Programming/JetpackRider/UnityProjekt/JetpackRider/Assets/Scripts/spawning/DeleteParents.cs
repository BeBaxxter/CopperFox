using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeleteParents : MonoBehaviour
{
   

    // Parent Group of Prefabs will be deleted if they contain no children
    void Update()
    {
        if (transform.childCount == 0)
        {
            Debug.Log(transform.childCount);
            Destroy(transform.gameObject);
        }
    }
}