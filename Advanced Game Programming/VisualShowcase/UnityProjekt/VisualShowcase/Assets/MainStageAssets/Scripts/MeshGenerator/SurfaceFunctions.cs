using UnityEngine;
using static UnityEngine.Mathf;
public class SurfaceFunctions : MeshFunction
{
    public Vector2 UMinMax => new Vector2(0, 1);
    
    public Vector2 VMinMax => new Vector2(0, 2 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(50, 50);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

      u * Cos(v),
      u * Sin(v),
      v

    );
}


public class SurfaceFunctions2 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(-1 * PI, 1 * PI);
    
    public Vector2 VMinMax => new Vector2(-1 * PI, 1 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(150, 25);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

        u * 1 * Cos(v) * Sin(u),
        u * Cos(u) * Cos(v),
        -u * Sin(v)

    );
}

public class SurfaceFunctions_Lucas_1 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(-1 * PI, 2 * PI);
    
    public Vector2 VMinMax => new Vector2(-1 * PI, 2 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(100, 100);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

        u * 1 * Cos(v) * Sin(v),
        u * Cos(u) * Cos(u),
        -u * Sin(u)

    );
}

public class SurfaceFunctions_Lucas_2 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(1, 4 * PI);
    
    public Vector2 VMinMax => new Vector2(1, 4 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(100, 100);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

      u * Cos(v),
      u * Sin(v),
      v

    );
}

public class SurfaceFunctions_Lucas_3 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(-1 * PI, 2 * PI);
    
    public Vector2 VMinMax => new Vector2(-1 * PI, 2 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(100, 800);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

        u * Cos(v) * Sin(v),
        u * Cos(u) * Cos(u),
        u * Sin(v)

    );
}

public class SurfaceFunctions_Maximilian_1 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(-PI, PI);
    
    public Vector2 VMinMax => new Vector2(-PI , 2*PI);
    
    public Vector2Int Subdivisions => new Vector2Int(100, 800);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (


        Sin(v) + 2 * Sin(2*u),
        Cos(v) - 2 * Cos(2*u),
        - Sin(3*v)

    );
}

public class SurfaceFunctions_Maximilian_2 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(-PI, PI);
    
    public Vector2 VMinMax => new Vector2(-PI , PI);
    
    public Vector2Int Subdivisions => new Vector2Int(100, 800);

    public Vector3 Vertex(float u, float v) => new Vector3(

        
        Sin(u * v) + 2 * Sin(2*(u * v)),
        Cos(u * v) - 2 * Cos(2*(u * v)),
        - Sin(3*(u * v))

    );
}


public class SurfaceFunctions_Maximilian_3 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(0, 12.4f);
    
    public Vector2 VMinMax => new Vector2(0.1f, 2);
    
    public Vector2Int Subdivisions => new Vector2Int(100, 20);

    public Vector3 Vertex(float u, float v) => new Vector3(

        
        Cos(u)*Sin(v) ,
        Sin(u)*Sin(v),
        Cos(v)+Log(Tan(v/2))+0.2f*u

    );
}


public class SurfaceFunctions_Colin_1 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(0, 2 * PI);
    
    public Vector2 VMinMax => new Vector2(0 , 2 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(50, 50);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

        (2 + Cos(u)) * Cos(v),
        (2 + Cos(u)) * Sin(v),
        2 + Sin(u)

    );
}

public class SurfaceFunctions_Colin_2 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(0, 2*PI);
    
    public Vector2 VMinMax => new Vector2(0, 6);
    
    public Vector2Int Subdivisions => new Vector2Int(50, 50);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

        v * Cos(u),
        v * Sin(u),
        v + Sin(3*v) / 3 -1

    );
}



public class SurfaceFunctions_Colin_3 : MeshFunction
{
    public Vector2 UMinMax => new Vector2(0 * PI, 1);
    
    public Vector2 VMinMax => new Vector2(0 * PI, 2 * PI);
    
    public Vector2Int Subdivisions => new Vector2Int(50, 50);
    
    public Vector3 Vertex(float u, float v) => new Vector3 (

        u * Cos(v),
        u * Sin(v),
        u * u * Cos(2*v)

    );
}
