using UnityEngine;

public class CoinController : MonoBehaviour
{
	[SerializeField] private SpriteRenderer coinSprite;
    [SerializeField] private Rigidbody2D coinCigidbody; 
	private Vector2 newVelocity;
	public PublicInformation gameState;

	private int worth = 1;
	public float speed = -3.0f ;
	private Vector2 screenBounds;
    
    // Start is called before the first frame update
    void Start()
    {
	    // set the initial speed
	    coinCigidbody.velocity = new Vector2(speed, 0);
    }

    void Update()
    {
	    worth = gameState.level;
    }
    
    
    // Delete the gameObject when the object leaves the screen
    public void OnBecameInvisible()
    {
	    Destroy(this.gameObject);
    }
    
    void FixedUpdate()
    {
	    // set the velocity according to the difficulty level
		coinCigidbody.velocity = new Vector2(speed -gameState.getAdditionalSpeed(), 0);
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
		// destroy on contact with player
        if (other.gameObject.CompareTag("Player"))
        {	
	        gameState.CoinManager.addAmount(worth);
			Destroy(this.gameObject);
        }
    }

    
}
