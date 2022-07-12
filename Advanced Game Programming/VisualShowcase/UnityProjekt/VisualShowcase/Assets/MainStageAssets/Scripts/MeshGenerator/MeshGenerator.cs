using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using UnityEditor;
using UnityEngine;

public interface MeshFunction
{
    Vector2 UMinMax { get; }

    Vector2 VMinMax { get; }

    Vector2Int Subdivisions { get; }

    Vector3 Vertex(float u, float v);
}

public enum MeshType
{
    SurfaceFunctions,
    SurfaceFunctions2,
    SurfaceFunctions_Lucas_1,
    SurfaceFunctions_Lucas_2,
    SurfaceFunctions_Lucas_3,
	SurfaceFunctions_Maximilian_1,
	SurfaceFunctions_Maximilian_2,
	SurfaceFunctions_Maximilian_3,
    SurfaceFunctions_Maximilian_4,
    SurfaceFunctions_Maximilian_5,
    SurfaceFunctions_Colin_1,
    SurfaceFunctions_Colin_2,
    SurfaceFunctions_Colin_3
}

public class MeshGenerator : MonoBehaviour
{
    [SerializeField] private Mesh generatedMesh;
    [SerializeField] private MeshType meshType;
    [SerializeField, Range(1, 5)] private float generationTime;

    private void Start()
    {
        Generate();
    }

    public void Generate()
    {
        Generate(SelectMeshFunction());
    }


    // Functions to choose from in Generator
    private MeshFunction SelectMeshFunction() => meshType switch
    {
           MeshType.SurfaceFunctions => new SurfaceFunctions(),
           MeshType.SurfaceFunctions2 => new SurfaceFunctions2(),
           MeshType.SurfaceFunctions_Lucas_1 => new SurfaceFunctions_Lucas_1(),
           MeshType.SurfaceFunctions_Lucas_2 => new SurfaceFunctions_Lucas_2(),
           MeshType.SurfaceFunctions_Lucas_3 => new SurfaceFunctions_Lucas_3(),
		   MeshType.SurfaceFunctions_Maximilian_1 => new SurfaceFunctions_Maximilian_1(),    
           MeshType.SurfaceFunctions_Maximilian_2 => new SurfaceFunctions_Maximilian_2(),
           MeshType.SurfaceFunctions_Maximilian_3 => new SurfaceFunctions_Maximilian_3(),
           MeshType.SurfaceFunctions_Colin_1 => new SurfaceFunctions_Colin_1(),
           MeshType.SurfaceFunctions_Colin_2 => new SurfaceFunctions_Colin_2(),
           MeshType.SurfaceFunctions_Colin_3 => new SurfaceFunctions_Colin_3(),
        
           _ => throw new ArgumentOutOfRangeException(nameof(meshType))
    };

    private void Generate(MeshFunction meshFunction)
    {
        generatedMesh = new Mesh();
        generatedMesh.name = meshFunction.GetType().Name;

        var subdivisions = meshFunction.Subdivisions;
        var vertexSize = subdivisions + new Vector2Int(1, 1);

        var vertices = new Vector3[vertexSize.x * vertexSize.y];
        var uvs = new Vector2[vertices.Length];

        var uDelta = meshFunction.UMinMax.y - meshFunction.UMinMax.x;
        var vDelta = meshFunction.VMinMax.y - meshFunction.VMinMax.x;

        
        //Set points for meshes
        for (var y = 0; y < vertexSize.y; y++)
        {
            var v = (1f / subdivisions.y) * y;

            for (var x = 0; x < vertexSize.x; x++)
            {
                var u = (1f / subdivisions.x) * x;
                var scaledUv = new Vector2(u * uDelta - meshFunction.UMinMax.x, v * vDelta - meshFunction.VMinMax.x);
                var vertex = meshFunction.Vertex(scaledUv.x, scaledUv.y);

                var arrayIndex = x + y * vertexSize.x;
                vertices[arrayIndex] = vertex;
                uvs[arrayIndex] = new Vector2(u, v);

               
            }
        }

        // Connect Points as triangles
        var triangles = new int[subdivisions.x * subdivisions.y * 6];
        for (var i = 0; i < subdivisions.x * subdivisions.y; i += 1)
        {
            var triangleIndex = (i % subdivisions.x) + (i / subdivisions.x) * vertexSize.x;
            var indexer = i * 6;

            triangles[indexer + 0] = triangleIndex;
            triangles[indexer + 1] = triangleIndex + subdivisions.x + 1;
            triangles[indexer + 2] = triangleIndex + 1;

            triangles[indexer + 3] = triangleIndex + 1;
            triangles[indexer + 4] = triangleIndex + subdivisions.x + 1;
            triangles[indexer + 5] = triangleIndex + subdivisions.x + 2;
            
        }

        generatedMesh.vertices = vertices;
        generatedMesh.uv = uvs;
        generatedMesh.triangles = triangles;

        generatedMesh.RecalculateBounds();
        generatedMesh.RecalculateNormals();
        generatedMesh.RecalculateTangents();

        GetComponent<MeshFilter>().mesh = generatedMesh;
    }
}
