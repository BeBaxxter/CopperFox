using UnityEngine;

public class Drones : MonoBehaviour{

    // set the gamestate for the difficulty level
    public PublicInformation gameState;
    // set the base speed
    public float speed = -3.0f ;
    private Rigidbody2D rb;
    private Vector3 dronePos;
    
    // Delete the gameObject when the object leaves the screen
    public void OnBecameInvisible()
    {
        Destroy(this.gameObject);
    }

    void Start()
    {
        rb = this.GetComponent<Rigidbody2D>();
    }

    void FixedUpdate()
    {
        // set the velocity according to the difficulty level and fly in a sinus wave
        rb.velocity = new Vector2(speed-gameState.getAdditionalSpeed(), 2 * Mathf.Sin(Time.time * 3));

        dronePos = Camera.main.WorldToScreenPoint(transform.position);
    }
}