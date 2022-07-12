using UnityEngine;

public class Laser : MonoBehaviour
{
    // set the gamestate for the difficulty level
    public PublicInformation gameState;
    // set the base speed
    public float speed = -3.0f ;
    private Rigidbody2D rb;
    private Vector3 laserPos;
   
    
    // Delete the gameObject when the object leaves the screen
    public void OnBecameInvisible()
    {
        Destroy(this.gameObject);
        if (transform.parent != null && transform.parent.childCount == 1)
        {
            Destroy(transform.parent.gameObject);
        }
    }

    void Start()
    {
        rb = this.GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        // set the velocity according to the difficulty level and fly horizontally
        rb.velocity = new Vector2(speed-(float)(gameState.getAdditionalSpeed()*0.5) , 0);
        laserPos = Camera.main.WorldToScreenPoint(transform.position);
        
    }
}