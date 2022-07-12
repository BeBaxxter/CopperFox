using UnityEngine;

public class RocketController : MonoBehaviour
{
    public PublicInformation gameState;
    [SerializeField] private SpriteRenderer rocketSprite;
    [SerializeField] private Rigidbody2D rocketRigidbody; 
    [SerializeField] private GameObject warningPrefab;
    private GameObject objectToSpawn; 
    private Vector2 newVelocity;
    private float time ;
    
	
    public float speed = -3.0f ;
    private Vector2 screenBounds;

    private bool move=false;

    void Start()
    {
        screenBounds = Camera.main.ScreenToViewportPoint(new Vector3(Screen.width, Screen.height, Camera.main.transform.position.z));
        showWarning();
        time =  Time.time;
    }

    void Update()
    {
        if (time + 2 < Time.time )
        {
            move = true;
        }
    }

    private void FixedUpdate()
    {

        if (move)
        {
            Destroy(objectToSpawn.gameObject);
            rocketRigidbody.velocity = new Vector2(speed -gameState.getAdditionalSpeed(), 0);
            screenBounds =
                Camera.main.ScreenToViewportPoint(new Vector3(Screen.width, Screen.height,
                    Camera.main.transform.position.z));
            
        }
        
        if (transform.position.x <-10)
        {
            Destroy(this.gameObject);
        }
         
    }

    private void showWarning()
    {
        float yPosition = rocketRigidbody.position.y;
        float xPosition = Screen.width;
        
        objectToSpawn = Instantiate(warningPrefab) as GameObject;
        objectToSpawn.transform.position = new Vector2(screenBounds.x * 8, this.transform.position.y);
    }
}
