using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

[CustomEditor(typeof(MeshGenerator))]

// Generate Button to generate Meshes
public class MeshGeneratorEditor : Editor
{
    public override void OnInspectorGUI()
    {
        base.OnInspectorGUI();
        if (GUILayout.Button("Generate"))
        {
            var tgt = this.target as MeshGenerator;
            if (tgt == null)
            {
                return;
            }

            tgt.Generate();
        }
    }
}
